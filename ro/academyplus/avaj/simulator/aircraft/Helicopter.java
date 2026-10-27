package ro.academyplus.avaj.simulator.aircraft;

import static ro.academyplus.avaj.simulator.weather.WeatherProvider.FOG;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.RAIN;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.SNOW;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.SUN;

public class Helicopter extends Aircraft
{
	public static final String	TYPE	= "Helicopter";

	public Helicopter(long p_id, String p_name, Coordinates p_coordinate)
	{
		super(p_id, p_name, p_coordinate);
	}

	@Override
	public void	updateConditions()
	{
		String	weather	= this.weatherTower.getWeather(this.coordinates);

		switch (weather)
		{
			case SUN	->	fly(+10, 0,  +2,	"This is hot. My rotor just became a ceiling fan.");
			case RAIN	->	fly( +5, 0,   0,	"Rain on the windshield and of course no wipers. Wonderful.");
			case FOG	->	fly( +1, 0,   0,	"Can't see a thing. Hovering and praying.");
			case SNOW	->	fly(  0, 0, -12,	"My rotor is going to freeze!");
			default		->	throw new IllegalStateException("Unknown weather: " + weather);
		}
	}

	@Override
	protected String	getType()
	{
		return TYPE;
	}
}
