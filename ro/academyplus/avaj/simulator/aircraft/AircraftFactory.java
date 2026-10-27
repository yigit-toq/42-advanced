package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.exception.UnknownAircraftTypeException;

public final class AircraftFactory
{
	private static final AircraftFactory	instance	= new AircraftFactory();

	private long	nextId	= 1;

	private AircraftFactory()
	{
	}

	public static AircraftFactory	getFactory()
	{
		return instance;
	}

	public Flyable	newAircraft(String p_type, String p_name, Coordinates p_coordinates)
		throws UnknownAircraftTypeException
	{
		Flyable	aircraft	= switch (p_type)
		{
			case Baloon.TYPE		->	new Baloon		(this.nextId, p_name, p_coordinates);
			case JetPlane.TYPE		->	new JetPlane	(this.nextId, p_name, p_coordinates);
			case Helicopter.TYPE	->	new Helicopter	(this.nextId, p_name, p_coordinates);
			default					->	throw new UnknownAircraftTypeException(p_type);
		};

		this.nextId++;

		return aircraft;
	}

	public Flyable	newAircraft(String p_type, String p_name, int p_longitude, int p_latitude, int p_height)
		throws UnknownAircraftTypeException
	{
		return newAircraft(p_type, p_name, new Coordinates(p_longitude, p_latitude, p_height));
	}
}
