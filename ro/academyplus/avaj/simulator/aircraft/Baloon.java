package ro.academyplus.avaj.simulator.aircraft;

import static ro.academyplus.avaj.simulator.weather.WeatherProvider.FOG;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.RAIN;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.SNOW;
import static ro.academyplus.avaj.simulator.weather.WeatherProvider.SUN;

public class Baloon extends Aircraft
{
	public static final String	TYPE	= "Balloon";

	public Baloon(long p_id, String p_name, Coordinates p_coordinate)
	{
		super(p_id, p_name, p_coordinate);
	}

	@Override
	public void	updateConditions()
	{
		String	weather	= this.weatherTower.getWeather(this.coordinates);

		switch (weather)
		{
			case SUN	->	fly(+2, 0,  +4,	"Let's enjoy the good weather and take some pics.");
			case RAIN	->	fly( 0, 0,  -5,	"Damn you rain! You messed up my balloon.");
			case FOG	->	fly( 0, 0,  -3,	"Fog? Fine. Nobody can see the patch on my balloon anyway.");
			case SNOW	->	fly( 0, 0, -15,	"It's snowing. We're gonna crash.");
			default		->	throw new IllegalStateException("Unknown weather: " + weather);
		}
	}

	@Override
	protected String	getType()
	{
		return TYPE;
	}
}
