package ro.academyplus.avaj.simulator.exception;

public class UnknownAircraftTypeException extends SimulatorException
{
	private static final long	serialVersionUID	= 1L;

	public UnknownAircraftTypeException(String p_type)
	{
		super("unknown aircraft type '" + p_type + "' (expected Balloon, JetPlane or Helicopter)");
	}
}
