package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.logger.Logger;

public abstract class Aircraft extends Flyable
{
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinate)
	{
		this.id				= p_id;
		this.name			= p_name;
		this.coordinates	= p_coordinate;
	}

	protected abstract String	getType();

	protected void	fly(int p_longitude, int p_latitude, int p_height, String p_message)
	{
		Logger.getLogger().log(this + ": " + p_message);

		this.coordinates = new Coordinates(
			shift(this.coordinates.getLongitude(),	p_longitude),
			shift(this.coordinates.getLatitude(),	p_latitude),
			shift(this.coordinates.getHeight(),		p_height));

		if (this.coordinates.getHeight() == 0)
		{
			land();
		}
	}

	private void	land()
	{
		Logger.getLogger().log(this + " landing.");

		this.weatherTower.unregister(this);
	}

	private static int	shift(int p_value, int p_delta)
	{
		return (int) Math.min((long) p_value + p_delta, Integer.MAX_VALUE);
	}

	@Override
	public String	toString()
	{
		return getType() + "#" + this.name + "(" + this.id + ")";
	}
}
