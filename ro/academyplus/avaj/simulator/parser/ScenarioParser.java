package ro.academyplus.avaj.simulator.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ro.academyplus.avaj.simulator.exception.InvalidScenarioException;
import ro.academyplus.avaj.simulator.exception.ScenarioFileException;

public final class ScenarioParser
{
	private static final String	INTEGER_PATTERN	= "[+-]?[0-9]+";
	private static final int	AIRCRAFT_FIELDS	= 5;
	private static final String	UTF8_BOM		= "﻿";

	private ScenarioParser()
	{
	}

	public static Scenario	parse(String p_path) throws ScenarioFileException, InvalidScenarioException
	{
		List<String>		lines		= read(p_path);
		List<AircraftSpec>	aircrafts	= new ArrayList<>();

		int	index	= skipBlankLines(lines, 0);

		if (index == lines.size())
		{
			throw new InvalidScenarioException("scenario file is empty");
		}

		int	iterations	= parseIterations(index + 1, lines.get(index));

		for (index++; index < lines.size(); index++)
		{
			if (!lines.get(index).isBlank())
			{
				aircrafts.add(parseAircraft(index + 1, lines.get(index)));
			}
		}

		if (aircrafts.isEmpty())
		{
			throw new InvalidScenarioException("scenario file does not define any aircraft");
		}
		return new Scenario(iterations, aircrafts);
	}

	private static List<String>	read(String p_path) throws ScenarioFileException
	{
		try
		{
			List<String>	lines	= Files.readAllLines(Path.of(p_path));

			if (!lines.isEmpty() && lines.get(0).startsWith(UTF8_BOM))
			{
				lines.set(0, lines.get(0).substring(UTF8_BOM.length()));
			}
			return lines;
		}
		catch (IOException | InvalidPathException e)
		{
			throw new ScenarioFileException(p_path, describe(e));
		}
	}

	private static int	parseIterations(int p_line, String p_content) throws InvalidScenarioException
	{
		String[]	tokens	= p_content.trim().split("\\s+");

		if (tokens.length != 1)
		{
			throw new InvalidScenarioException(p_line, "expected a single simulation count, got '" + p_content.trim() + "'");
		}

		int	iterations	= parseInteger(p_line, "simulation count", tokens[0]);

		if (iterations <= 0)
		{
			throw new InvalidScenarioException(p_line, "simulation count must be positive, got " + iterations);
		}
		return iterations;
	}

	private static AircraftSpec	parseAircraft(int p_line, String p_content) throws InvalidScenarioException
	{
		String[]	tokens	= p_content.trim().split("\\s+");

		if (tokens.length != AIRCRAFT_FIELDS)
		{
			throw new InvalidScenarioException(p_line, "expected 'TYPE NAME LONGITUDE LATITUDE HEIGHT', got '" + p_content.trim() + "'");
		}

		int	longitude	= parseInteger(p_line, "longitude",	tokens[2]);
		int	latitude	= parseInteger(p_line, "latitude",	tokens[3]);
		int	height		= parseInteger(p_line, "height",	tokens[4]);

		if (longitude <= 0)
		{
			throw new InvalidScenarioException(p_line, "longitude must be positive, got " + longitude);
		}
		if (latitude <= 0)
		{
			throw new InvalidScenarioException(p_line, "latitude must be positive, got " + latitude);
		}
		if (height < 0)
		{
			throw new InvalidScenarioException(p_line, "height cannot be negative, got " + height);
		}
		return new AircraftSpec(p_line, tokens[0], tokens[1], longitude, latitude, height);
	}

	private static int	parseInteger(int p_line, String p_field, String p_token) throws InvalidScenarioException
	{
		if (!p_token.matches(INTEGER_PATTERN))
		{
			throw new InvalidScenarioException(p_line, p_field + " must be an integer, got '" + p_token + "'");
		}

		try
		{
			return Integer.parseInt(p_token);
		}
		catch (NumberFormatException e)
		{
			throw new InvalidScenarioException(p_line, p_field + " is out of range, got '" + p_token + "'");
		}
	}

	private static int	skipBlankLines(List<String> p_lines, int p_index)
	{
		while (p_index < p_lines.size() && p_lines.get(p_index).isBlank())
		{
			p_index++;
		}
		return p_index;
	}

	private static String	describe(Exception p_exception)
	{
		if (p_exception instanceof java.nio.file.NoSuchFileException)
		{
			return "no such file";
		}
		if (p_exception instanceof java.nio.file.AccessDeniedException)
		{
			return "permission denied";
		}
		if (p_exception instanceof java.nio.charset.CharacterCodingException)
		{
			return "file is not valid text";
		}
		return p_exception.getMessage() != null ? p_exception.getMessage() : p_exception.getClass().getSimpleName();
	}
}
