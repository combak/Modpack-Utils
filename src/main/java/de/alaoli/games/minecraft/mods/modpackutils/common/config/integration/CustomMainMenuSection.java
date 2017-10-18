package de.alaoli.games.minecraft.mods.modpackutils.common.config.integration;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;

import java.util.Optional;

public class CustomMainMenuSection implements Section
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/

	public static final String MODID = "custommainmenu";

	/**
	 * Enables CustomMainMenu integration
	 */
	public static boolean enabled = true;

	/**
	 * Changelog button id
	 */
	public static int changelogButtonId = 1000;

	/**
	 * Bug Report button id
	 */
	public static int bugreportButtonId = 1001;

    /******************************************************************************************
     * Method - Implements Section
     ******************************************************************************************/

	@Override
	public Optional<String> getComponentName() 
	{
		return Optional.of( MODID );
	}
	
    /******************************************************************************************
     * Method - Implements JsonSerializable
     ******************************************************************************************/
	
	@Override
	public JsonValue serialize() throws DataException { return null; /*Read only*/ }

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "Integration - CustomMainMenu isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		if( obj.get( "enabled" ) != null )
		{
			if( !obj.get( "enabled" ).isBoolean() ) { throw new DataException( "Integration - CustomMainMenu 'enabled' isn't an boolean." ); }
			
			enabled = obj.get( "enabled" ).asBoolean();
		}
		
		if( obj.get( "changelogButtonId" ) != null )
		{
			if( !obj.get( "changelogButtonId" ).isNumber() ) { throw new DataException( "Integration - CustomMainMenu 'changelogButtonId' isn't a number." ); }

			changelogButtonId = obj.get( "changelogButtonId" ).asInt();
		}

		if( obj.get( "bugreportButtonId" ) != null )
		{
			if( !obj.get( "bugreportButtonId" ).isNumber() ) { throw new DataException( "Integration - CustomMainMenu  'bugreportButtonId' isn't a number." ); }

			bugreportButtonId = obj.get( "bugreportButtonId" ).asInt();
		}

		if(  changelogButtonId == bugreportButtonId ) {
			throw new DataException("Integration - CustomMainMenu 'changelogButtonId' and 'bugreportButtonId' can't be identical.");
		}
	}
}
