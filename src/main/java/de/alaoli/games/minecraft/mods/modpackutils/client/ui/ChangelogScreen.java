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
package de.alaoli.games.minecraft.mods.modpackutils.client.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.ui.Screen;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.Border;
import de.alaoli.games.minecraft.mods.lib.ui.element.Label;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.*;
import de.alaoli.games.minecraft.mods.lib.ui.layout.BorderPane;
import de.alaoli.games.minecraft.mods.lib.ui.layout.VBox;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;
import de.alaoli.games.minecraft.mods.lib.ui.wrapped.ScrollingText;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import jline.internal.Log;
import net.minecraft.client.resources.I18n;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class ChangelogScreen extends Screen
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */
	
	private final BorderPane borderPane = new BorderPane();
	private final VBox titlePane = new VBox();
	private final Label titleText = new Label();
	private final ScrollingText changelogPane  = new ScrollingText();

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
			result.add( I18n.format( "modpackutils:gui.changelog.notfound" ) );

			Log.error( "Can't load '" + Settings.changelog.file + "': " + e.getMessage() );
		}

		if( result.isEmpty() )
		{
			result.add( I18n.format( "modpackutils:gui.changelog.isempty" ) );
		}
		return result;
	}

	/* **************************************************************************************************************
	 * Method - Implement Screen
	 ************************************************************************************************************** */

	@Override
	public void init()
	{
		BoxStyle boxStyle = Defaults.getBoxStyles().getOptions();

		this.titleText
			.setText( I18n.format( "modpackutils:gui.changelog.title" ) )
			.setTextStyle( Defaults.getTextStyles().getTitle() )
			.setSize( 100, 15 );

		this.titlePane
			.setBoxStyle( boxStyle.extend()
				.setBorder( new Border( Colors.factory( Color.DARKGRAY ) ).hide( false, false, false, true ) )
				.setAlign( Align.CENTER ))
			.addElement( this.titleText )
			.setHeight( 15 );

		this.changelogPane
			.setLines( this.getChangelog() )
			.setBoxStyle( boxStyle.extend()
				.setBorder( new Border( Colors.factory( Color.DARKGRAY ) ) )
				.setPadding( 2 ) )
			.setTextStyle( Defaults.getTextStyles().getText() );

		this.borderPane
			.setBorder( Align.TOP, this.titlePane )
			.setBorder( Align.CENTER, this.changelogPane )
			.setBoxStyle( new BoxStyling()
				.setMargin( 10, 30, 30, 10 ));

		this.setLayout( this.borderPane );
	}

	/* **************************************************************************************************************
	 * Method - Implement Layout
	 ************************************************************************************************************** */

	@Override
	public void layout() {}
}