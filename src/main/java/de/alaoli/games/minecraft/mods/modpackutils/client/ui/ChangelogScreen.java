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
package de.alaoli.games.minecraft.mods.modpackutils.client.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.ui.component.Components;
import de.alaoli.games.minecraft.mods.lib.ui.component.ListText;
import de.alaoli.games.minecraft.mods.lib.ui.screen.Screen;
import de.alaoli.games.minecraft.mods.modpackutils.Const;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import jline.internal.Log;
import net.minecraft.client.resources.I18n;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class ChangelogScreen extends Screen//extends Screen
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */

	private ListText listChangelog;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	private List<String> getChangelog()
	{
		List<String> result;

		try
		{
			result = Files.lines( Paths.get( Settings.changelog.file ) ).collect( Collectors.toList() );
		}
		catch ( IOException e )
		{
			result = new ArrayList<>();
			result.add( I18n.format( Const.Lang.Changelog.FILE_NOT_FOUND ) );

			Log.error( "Can't load '" + Settings.changelog.file + "': " + e.getMessage() );
		}

		if( result.isEmpty() )
		{
			result.add( I18n.format( Const.Lang.Changelog.FILE_IS_EMPTY ) );
		}
		return result;
	}

	@Override
	public void create()
	{
		this.listChangelog = Components.buildListText()
			.withEntries( this.getChangelog() )
		.build();
	}

	@Override
	public void show()
	{
		this.addComponent( this.listChangelog );
	}

	@Override
	public void resize( int width, int height )
	{
		this.listChangelog.setRegion( this.listChangelog.getRegion().toBuilder().withDimensions( width, height ).build() );
	}

	@Override
	public void hide() {}
}