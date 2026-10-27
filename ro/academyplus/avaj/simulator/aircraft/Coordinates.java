package ro.academyplus.avaj.simulator.aircraft;

public class Coordinates
{
	private static final int	MIN_HEIGHT	= 0;
	private static final int	MAX_HEIGHT	= 100;

	private int	longitude;
	private int	latitude;
	private int	height;

	Coordinates(int p_longitude, int p_latitude, int p_height)
	{
		this.longitude	= p_longitude;
		this.latitude	= p_latitude;
		this.height		= Math.max(MIN_HEIGHT, Math.min(MAX_HEIGHT, p_height));
	}

	public int	getLongitude()
	{
		return this.longitude;
	}

	public int	getLatitude()
	{
		return this.latitude;
	}

	public int	getHeight()
	{
		return this.height;
	}
}
