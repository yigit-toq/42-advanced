package ro.academyplus.avaj.simulator.tower;

import ro.academyplus.avaj.simulator.aircraft.Coordinates;
import ro.academyplus.avaj.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower
{
	public String	getWeather(Coordinates p_coordinates)
	{
		return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
	}

	public void	changeWeather()
	{
		WeatherProvider.getProvider().nextCycle();

		this.conditionChanged();
	}
}
