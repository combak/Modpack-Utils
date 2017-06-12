package de.alaoli.games.minecraft.mods.modpackutils.common.config;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;

public class ChangelogSection implements Section 
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/
	
	/**
	 * Enables changelog view
	 */
	public static boolean enabled = true;
	
	/**
	 * Changelog file name.
	 */
	public static String file = "CHANGELOG.md";
	
    /******************************************************************************************
     * Method - Implements Section
     ******************************************************************************************/
	
	@Override
	public String getSectionName() 
	{
		return "Changelog";
	}

    /******************************************************************************************
     * Method - Implements JsonSerializable
     ******************************************************************************************/
	
	@Override
	public JsonValue serialize() throws DataException { return null; /*Read only*/ }

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "ChangelogSection isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		if( obj.get( "enabled" ) != null )
		{
			if( !obj.get( "enabled" ).isBoolean() ) { throw new DataException( "ChangelogSection 'enabled' isn't an boolean." ); }
			
			enabled = obj.get( "enabled" ).asBoolean();
		}
		
		if( obj.get( "file" ) != null )
		{
			if( !obj.get( "file" ).isString() ) { throw new DataException( "ChangelogSection 'file' isn't a string." ); }
			
			file = obj.get( "file" ).asString();
		}
	}
}
