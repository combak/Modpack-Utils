/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.modpackutils.common.data.github;

import com.google.gson.annotations.Expose;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Getter
@AllArgsConstructor
public final class Issue
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
}