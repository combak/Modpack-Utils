package de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices;

import java.util.Optional;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;

public class GithubSection implements Section 
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/
	
	/**
	 * Enables github webservice function (requires modpack-webservice server)
	 */
	public static boolean enabled = false;
	
	/**
	 * Repository name
	 */
	public static String repository = "";
	
    /******************************************************************************************
     * Method - Implements Section
     ******************************************************************************************/

	@Override
	public Optional<String> getComponentName() 
	{
		return Optional.of( "github" );
	}
	
    /******************************************************************************************
     * Method - Implements JsonSerializable
     ******************************************************************************************/
	
	@Override
	public JsonValue serialize() throws DataException { return null; /*Read only*/ }

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "Webservice - Github isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		if( obj.get( "enabled" ) != null )
		{
			if( !obj.get( "enabled" ).isBoolean() ) { throw new DataException( "Webservice - Github 'enabled' isn't an boolean." ); }
			
			enabled = obj.get( "enabled" ).asBoolean();
		}
		
		if( obj.get( "repository" ) != null )
		{
			if( !obj.get( "repository" ).isString() ) { throw new DataException( "Webservice - Github 'repository' isn't a string." ); }
			
			repository = obj.get( "repository" ).asString();
		}
	}
}
