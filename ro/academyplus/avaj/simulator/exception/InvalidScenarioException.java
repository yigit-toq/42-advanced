package ro.academyplus.avaj.simulator.exception;

public class InvalidScenarioException extends SimulatorException
{
	private static final long	serialVersionUID	= 1L;

	public InvalidScenarioException(String p_message)
	{
		super(p_message);
	}

	public InvalidScenarioException(int p_line, String p_message)
	{
		super("line " + p_line + ": " + p_message);
	}
}
