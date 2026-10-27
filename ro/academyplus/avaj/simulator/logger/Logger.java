package ro.academyplus.avaj.simulator.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ro.academyplus.avaj.simulator.exception.OutputFileException;

public final class Logger
{
	private static final Logger	instance	= new Logger();

	private PrintWriter	writer;
	private String		path;

	private Logger()
	{
	}

	public static Logger	getLogger()
	{
		return instance;
	}

	public void	open(String p_path) throws OutputFileException
	{
		try
		{
			this.writer	= new PrintWriter(new BufferedWriter(new FileWriter(p_path)));
			this.path	= p_path;
		}
		catch (IOException e)
		{
			throw new OutputFileException(p_path, e.getMessage());
		}
	}

	public void	log(String p_message)
	{
		this.writer.print(p_message + "\n");
	}

	public void	close() throws OutputFileException
	{
		boolean	failed	= this.writer.checkError();

		this.writer.close();

		if (failed || this.writer.checkError())
		{
			throw new OutputFileException(this.path, "write failed");
		}
	}
}
