package de.alaoli.games.minecraft.mods.modpackutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log 
{
	/******************************************************************************************
	 * Attributes
	 ******************************************************************************************/
	
	private static final Logger LOGGER = LogManager.getLogger( ModpackUtils.MODID );
	
	/******************************************************************************************
	 * Methods
	 ******************************************************************************************/

	/**
	 * Logs a message object with the INFO level.
	 * 
	 * @param String
	 */
	public static void info( String msg )
	{
		LOGGER.info( msg );
	}
	
	/**
	 * Logs a message object with the WARN level.
	 * 
	 * @param String
	 */
	public static void warn( String msg )
	{
		LOGGER.warn( msg );
	}
	
	/**
	 * Logs a message object with the ERROR level.
	 * 
	 * @param String
	 */
	public static void error( String msg )
	{
		LOGGER.error( msg );
	}	
}
