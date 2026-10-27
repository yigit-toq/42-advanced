package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.aircraft.Coordinates;

public final class WeatherProvider
{
	public static final String	RAIN	= "RAIN";
	public static final String	FOG		= "FOG";
	public static final String	SUN		= "SUN";
	public static final String	SNOW	= "SNOW";

	private static final WeatherProvider	instance	= new WeatherProvider();

	private String[]	weather	= { RAIN, FOG, SUN, SNOW };

	private long		cycle;

	private WeatherProvider()
	{
	}

	public static WeatherProvider	getProvider()
	{
		return instance;
	}

	public String	getCurrentWeather(Coordinates p_coordinates)
	{
		long	hash	= p_coordinates.getLongitude()	* 73856093L
						^ p_coordinates.getLatitude()	* 19349663L
						^ p_coordinates.getHeight()		* 83492791L
						^ this.cycle					* 2654435761L;

		hash ^= hash >>> 33;
		hash *= 0xff51afd7ed558ccdL;
		hash ^= hash >>> 33;

		return this.weather[(int) Math.floorMod(hash, (long) this.weather.length)];
	}

	public void	nextCycle()
	{
		this.cycle++;
	}
}
