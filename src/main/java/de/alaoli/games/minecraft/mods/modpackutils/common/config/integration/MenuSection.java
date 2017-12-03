/* *************************************************************************************************************
 * Copyright (c) 2017 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************ */
package de.alaoli.games.minecraft.mods.modpackutils.common.config.integration;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;

import java.util.Optional;

public class MenuSection implements Section
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	/**
	 * Enables MainMenu integration
	 */
	public static boolean mainEnabled = true;

	/**
	 * Enables OptionsMenu integration
	 */
	public static boolean optionsEnabled = true;

	/**
	 * Changelog button id
	 */
	public static int changelogButtonId = 1000;

	/**
	 * Bug Report button id
	 */
	public static int bugreportButtonId = 1001;

	/* **************************************************************************************************************
	 * Method - Implement Section
	 ************************************************************************************************************** */

	@Override
	public Optional<String> getComponentName() 
	{
		return Optional.of( "optionsmenu" );
	}

	/* **************************************************************************************************************
	 * Method - Implement JsonSerializable
	 ************************************************************************************************************** */

	@Override
	public JsonValue serialize() throws DataException { return null; /*Read only*/ }

	@Override
	public void deserialize( JsonValue json ) throws DataException 
	{
		if( !json.isObject() ) { throw new DataException( "Integration - OptionsMenu isn't a JsonObject." ); }
		
		JsonObject obj = json.asObject();
		
		if( obj.get( "mainEnabled" ) != null )
		{
			if( !obj.get( "mainEnabled" ).isBoolean() ) { throw new DataException( "Integration - Menu 'mainEnabled' isn't an boolean." ); }

			mainEnabled = obj.get( "mainEnabled" ).asBoolean();
		}

		if( obj.get( "optionsEnabled" ) != null )
		{
			if( !obj.get( "mainEoptionsEnablednabled" ).isBoolean() ) { throw new DataException( "Integration - Menu 'optionsEnabled' isn't an boolean." ); }

			mainEnabled = obj.get( "optionsEnabled" ).asBoolean();
		}

		if( obj.get( "changelogButtonId" ) != null )
		{
			if( !obj.get( "changelogButtonId" ).isNumber() ) { throw new DataException( "Integration - Menu 'changelogButtonId' isn't a number." ); }

			changelogButtonId = obj.get( "changelogButtonId" ).asInt();
		}

		if( obj.get( "bugreportButtonId" ) != null )
		{
			if( !obj.get( "bugreportButtonId" ).isNumber() ) { throw new DataException( "Integration - Menu  'bugreportButtonId' isn't a number." ); }

			bugreportButtonId = obj.get( "bugreportButtonId" ).asInt();
		}

		if(  changelogButtonId == bugreportButtonId ) {
			throw new DataException("Integration - Menu 'changelogButtonId' and 'bugreportButtonId' can't be identical.");
		}
	}
}
