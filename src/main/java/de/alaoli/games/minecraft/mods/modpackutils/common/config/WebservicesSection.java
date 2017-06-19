package de.alaoli.games.minecraft.mods.modpackutils.common.config;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.config.SectionGroup;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices.GithubSection;

public class WebservicesSection extends SectionGroup 
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/
	
	/**
	 * Enables webservice function (requires modpack-webservice server)
	 */
	public static boolean enabled = false;
	
	/**
	 * Webservice server url
	 */
	public static String url = "";

    /******************************************************************************************
     * Method
     ******************************************************************************************/
	
	public WebservicesSection()
	{
		this.addNode( new GithubSection() );
	}
	
    /******************************************************************************************
     * Method - Implements Section
     ******************************************************************************************/
	
	@Override
	public String getNodeName() 
	{
		return "webservices";
	}

    /******************************************************************************************
     * Method - Implements JsonSerializable
     ******************************************************************************************/
	
	@Override
	public JsonValue serialize() throws DataException { return null; /*Read only*/ }

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		super.deserialize( json );
		
		if( !json.isObject() ) { throw new DataException( "Webservices isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		if( obj.get( "enabled" ) != null )
		{
			if( !obj.get( "enabled" ).isBoolean() ) { throw new DataException( "Webservices 'enabled' isn't an boolean." ); }
			
			enabled = obj.get( "enabled" ).asBoolean();
		}
		
		if( obj.get( "url" ) != null )
		{
			if( !obj.get( "url" ).isString() ) { throw new DataException( "Webservices 'url' isn't a string." ); }
			
			url = obj.get( "url" ).asString();
		}
	}
}
