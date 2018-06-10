package de.alaoli.games.minecraft.mods.modpackutils.common.data.github;

import com.google.gson.annotations.Expose;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;

public class Issue
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	/**
	 * The token of the related repository.
	 */
	@Expose( deserialize = false )
	private final String repository = Settings.webservices.github.repository;
	
	/**
	 * The author of the issue.
	 */
	@Expose( deserialize = false )
	private final String name;
	
	/**
	 * The title of the issue
	 */
	@Expose( deserialize = false )
	private final String title;
	
	/**
	 * The contents of the issue.
	 */
	@Expose( deserialize = false )
	private final String description;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public Issue( String name, String title, String description )
	{
		this.name = name;
		this.title = title;
		this.description = description;
	}

	public String getRepository()
	{
		return this.repository;
	}

	public String getName()
	{
		return this.name;
	}

	public String getTitle()
	{
		return this.title;
	}

	public String getDescription()
	{
		return this.description;
	}
}