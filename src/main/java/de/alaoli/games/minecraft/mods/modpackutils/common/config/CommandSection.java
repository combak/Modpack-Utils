package de.alaoli.games.minecraft.mods.modpackutils.common.config;

import java.util.List;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import scala.actors.threadpool.Arrays;

public class CommandSection implements Section 
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/
	
	/**
	 * Command name
	 */
	public static String command = "mpu";
	
	/**
	 * Changelog file name.
	 */
	public static List commandAliases = Arrays.asList( new String[] {"mp","modpack" } );
	
    /******************************************************************************************
     * Method - Implements Section
     ******************************************************************************************/
	
	@Override
	public String getSectionName() 
	{
		return "Command";
	}

    /******************************************************************************************
     * Method - Implements JsonSerializable
     ******************************************************************************************/
	
	@Override
	public JsonValue serialize() throws DataException { return null; /*Read only*/ }

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "CommandSection isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		if( obj.get( "command" ) != null )
		{
			if( !obj.get( "command" ).isString() ) { throw new DataException( "CommandSection 'command' isn't a string." ); }
			
			command = obj.get( "command" ).asString();
		}
		
		if( obj.get( "commandAliases" ) != null )
		{
			if( !obj.get( "commandAliases" ).isArray() ) { throw new DataException( "CommandSection 'commandAliases' isn't an array." ); }
			
			commandAliases = obj.get( "commandAliases" ).asArray().values();
		}
	}
}