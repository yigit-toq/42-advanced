package ro.academyplus.avaj.simulator.tower;

import java.util.ArrayList;
import java.util.List;

import ro.academyplus.avaj.simulator.aircraft.Flyable;
import ro.academyplus.avaj.simulator.logger.Logger;

public class Tower
{
	private List<Flyable>	observers	= new ArrayList<>();

	public void	register(Flyable p_flyable)
	{
		if (this.observers.contains(p_flyable))
		{
			return ;
		}
		this.observers.add(p_flyable);

		Logger.getLogger().log("Tower says: " + p_flyable + " registered to weather tower.");
	}

	public void	unregister(Flyable p_flyable)
	{
		if (!this.observers.remove(p_flyable))
		{
			return ;
		}
		Logger.getLogger().log("Tower says: " + p_flyable + " unregistered from weather tower.");
	}

	protected void	conditionChanged()
	{
		for (Flyable flyable : new ArrayList<>(this.observers))
		{
			flyable.updateConditions();
		}
	}
}
