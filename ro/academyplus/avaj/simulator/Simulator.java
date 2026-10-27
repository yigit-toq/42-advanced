package ro.academyplus.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

import ro.academyplus.avaj.simulator.aircraft.AircraftFactory;
import ro.academyplus.avaj.simulator.aircraft.Flyable;
import ro.academyplus.avaj.simulator.exception.InvalidScenarioException;
import ro.academyplus.avaj.simulator.exception.SimulatorException;
import ro.academyplus.avaj.simulator.exception.UnknownAircraftTypeException;
import ro.academyplus.avaj.simulator.logger.Logger;
import ro.academyplus.avaj.simulator.parser.AircraftSpec;
import ro.academyplus.avaj.simulator.parser.Scenario;
import ro.academyplus.avaj.simulator.parser.ScenarioParser;
import ro.academyplus.avaj.simulator.tower.WeatherTower;

public final class Simulator
{
	private static final String	OUTPUT_FILE	= "simulation.txt";

	private static final String	RED			= "\033[1;31m";
	private static final String	RESET		= "\033[0m";

	private Simulator()
	{
	}

	public static void	main(String[] args)
	{
		if (args.length != 1)
		{
			exit("usage: java ro.academyplus.avaj.simulator.Simulator <scenario_file>");
		}

		try
		{
			Scenario		scenario	= ScenarioParser.parse(args[0]);
			List<Flyable>	aircrafts	= createAircrafts(scenario.aircrafts());

			simulate(scenario.iterations(), aircrafts);
		}
		catch (SimulatorException e)
		{
			exit(e.getMessage());
		}
	}

	private static List<Flyable>	createAircrafts(List<AircraftSpec> p_specs) throws InvalidScenarioException
	{
		AircraftFactory	factory		= AircraftFactory.getFactory();
		List<Flyable>	aircrafts	= new ArrayList<>();

		for (AircraftSpec spec : p_specs)
		{
			try
			{
				aircrafts.add(factory.newAircraft(spec.type(), spec.name(), spec.longitude(), spec.latitude(), spec.height()));
			}
			catch (UnknownAircraftTypeException e)
			{
				throw new InvalidScenarioException(spec.line(), e.getMessage());
			}
		}
		return aircrafts;
	}

	private static void	simulate(int p_iterations, List<Flyable> p_aircrafts) throws SimulatorException
	{
		WeatherTower	tower	= new WeatherTower();
		Logger			logger	= Logger.getLogger();

		logger.open(OUTPUT_FILE);

		try
		{
			for (Flyable aircraft : p_aircrafts)
			{
				aircraft.registerTower(tower);
			}
			for (int i = 0; i < p_iterations; i++)
			{
				tower.changeWeather();
			}
		}
		finally
		{
			logger.close();
		}
	}

	private static void	exit(String p_message)
	{
		System.out.println(RED + "Error: " + RESET + p_message);

		System.exit(1);
	}
}
