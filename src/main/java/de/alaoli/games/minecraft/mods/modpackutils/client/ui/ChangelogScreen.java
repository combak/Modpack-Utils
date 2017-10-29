package de.alaoli.games.minecraft.mods.modpackutils.client.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.ui.Screen;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.Background;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.Border;
import de.alaoli.games.minecraft.mods.lib.ui.element.Label;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.layout.BorderPane;
import de.alaoli.games.minecraft.mods.lib.ui.layout.VBox;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;
import de.alaoli.games.minecraft.mods.lib.ui.wrapped.ScrollingText;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.ChangelogSection;
import jline.internal.Log;
import net.minecraft.client.resources.I18n;

public class ChangelogScreen extends Screen<ChangelogScreen>
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/
	
	private BorderPane borderPane;
	private VBox titlePane;
	private Label titleText;
	private ScrollingText changelogPane;

	/******************************************************************************************
	 * Method
	 ******************************************************************************************/

	public List<String> getChangelog()
	{
		List<String> result;

		try
		{
			result = Files.lines( Paths.get( ChangelogSection.file ) ).collect( Collectors.toList() );
		}
		catch ( IOException e )
		{
			result = new ArrayList<>();
			result.add( I18n.format( "modpackutils:gui.changelog.notfound" ) );

			Log.error( "Can't load '" + ChangelogSection.file + "': " + e.getMessage() );
		}
		return result;
	}

	/******************************************************************************************
	 * Method - Implement Layout
	 ******************************************************************************************/
    	
	@Override
	public void doLayout()
	{
		BoxStyle boxStyle = new BoxStyle()
			.setBackground( new Background( Colors.factory( 0.5f, Color.BLACK ) ) );
		TextStyle textStyle = new TextStyle()
			.setColor( Colors.factory( Color.WHITE ) );

		this.titleText = new Label()
			.setText( I18n.format( "modpackutils:gui.changelog.title" ) )
			.setSize( 100, 15 )
			.setTextStyle( textStyle.extend().setAlign( Align.CENTER ) );

		this.titlePane = new VBox()
			.setBoxStyle( boxStyle.extend().setAlign( Align.CENTER ) )
			.setHeight( 15 )
			.addElement( this.titleText );

		this.changelogPane = new ScrollingText()
			.setLines( this.getChangelog() )
			.setBoxStyle( boxStyle.extend()
				.setBorder( new Border( Colors.factory( Color.DARKGRAY ) ) )
				.setPadding( 5 ) )
			.setTextStyle( textStyle );

		this.borderPane = new BorderPane()
			.setBorder( Align.TOP, this.titlePane )
			.setBorder( Align.CENTER, this.changelogPane )
			.setBoxStyle( boxStyle.extend()
				.setPadding( 10 )
				.setMargin( 10, 30, 30, 10 ));
		
		this.setLayout( this.borderPane );
	}
}