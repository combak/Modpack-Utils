package de.alaoli.games.minecraft.mods.modpackutils.common.data.github;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.lib.common.json.JsonSerializable;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices.GithubSection;

public class Issue implements JsonSerializable
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/

	/**
	 * The token of the related repository.
	 */
	public final String repository = GithubSection.repository;
	
	/**
	 * The author of the issue.
	 */
	public final String name;
	
	/**
	 * The title of the issue
	 */
	public final String title;
	
	/**
	 * The contents of the issue.
	 */
	public final String description;

    /******************************************************************************************
     * Method
     ******************************************************************************************/
	
	public Issue( String title, String description )
	{
		this.name = null;
		this.title = title;
		this.description = description;
	}
	
	public Issue( String name, String title, String description )
	{
		this.name = name;
		this.title = title;
		this.description = description;
	}
	
    /******************************************************************************************
     * Method - Implement JsonSerializable
     ******************************************************************************************/

	@Override
	public JsonValue serialize() throws DataException 
	{
		JsonObject json = new JsonObject();
		
		json.add( "repository", this.repository );
		json.add( "name", this.name );
		json.add( "title", this.title );
		json.add( "description", this.description );
		
		return json;
	}

	@Override
	public void deserialize( JsonValue json ) throws DataException { /* Write only */ }
}