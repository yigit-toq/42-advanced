package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.tower.WeatherTower;

public abstract class Flyable
{
	protected WeatherTower	weatherTower;

	public abstract void	updateConditions();

	public void	registerTower(WeatherTower p_tower)
	{
		this.weatherTower = p_tower;

		this.weatherTower.register(this);
	}
}
