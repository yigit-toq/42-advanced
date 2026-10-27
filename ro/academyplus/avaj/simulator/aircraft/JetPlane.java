package ro.academyplus.avaj.simulator.aircraft;

import static ro.academyplus.avaj.simulator.weather.WeatherProvider.FOG;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.RAIN;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.SNOW;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.SUN;

public class JetPlane extends Aircraft
{
	public static final String	TYPE	= "JetPlane";

	public JetPlane(long p_id, String p_name, Coordinates p_coordinate)
	{
		super(p_id, p_name, p_coordinate);
	}

	@Override
	public void	updateConditions()
	{
		String	weather	= this.weatherTower.getWeather(this.coordinates);

		switch (weather)
		{
			case SUN	->	fly(0, +10, +2,	"Sunglasses on, autopilot on, coffee on. Life is good.");
			case RAIN	->	fly(0,  +5,  0,	"It's raining. Better watch out for lightnings.");
			case FOG	->	fly(0,  +1,  0,	"Fog everywhere. Flying by instruments and pure vibes.");
			case SNOW	->	fly(0,   0, -7,	"OMG! Winter is coming! Deicing, deicing, deicing!");
			default		->	throw new IllegalStateException("Unknown weather: " + weather);
		}
	}

	@Override
	protected String	getType()
	{
		return TYPE;
	}
}
