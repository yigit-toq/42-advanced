package ro.academyplus.avaj.simulator.exception;

public class OutputFileException extends SimulatorException
{
	private static final long	serialVersionUID	= 1L;

	public OutputFileException(String p_path, String p_reason)
	{
		super("cannot write output file '" + p_path + "': " + p_reason);
	}
}
