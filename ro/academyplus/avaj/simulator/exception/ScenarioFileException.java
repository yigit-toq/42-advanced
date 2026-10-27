package ro.academyplus.avaj.simulator.exception;

public class ScenarioFileException extends SimulatorException
{
	private static final long	serialVersionUID	= 1L;

	public ScenarioFileException(String p_path, String p_reason)
	{
		super("cannot read scenario file '" + p_path + "': " + p_reason);
	}
}
