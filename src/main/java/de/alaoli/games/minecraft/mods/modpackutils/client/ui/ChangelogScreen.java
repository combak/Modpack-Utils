package de.alaoli.games.minecraft.mods.modpackutils.client.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.lib.common.ui.Screen;
import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Background;
import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Border;
import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Drawable;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.Label;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.BorderPane;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.VBox;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Colors;
import de.alaoli.games.minecraft.mods.lib.common.ui.wrapped.ScrollingText;
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
	 * Method - Implement Layout
	 ******************************************************************************************/
    	
	@Override
	public void doLayout()
	{
		List<String> lines = null;
		
		try 
		{
			lines = Files.lines( Paths.get( ChangelogSection.file ) ).collect( Collectors.toList() );
		} 
		catch ( IOException e ) 
		{
			Log.error( "Can't load '" + ChangelogSection.file + "': " + e.getMessage() );
		}		
		Color textColor = Colors.factory( 255,255,255 );
		Color borderColor = Colors.factory( 85,85,85 );
		
		Drawable background = new Background( Colors.factory( 0.5f, 0, 0, 0 ) );
		Drawable border = new Border( borderColor );

		this.titleText = new Label()
			.setText( I18n.format( "modpackutils:gui.changelog.title" ) )
			.setElementHeight( 15 )
			.setTextStyle( new TextStyle()
				.setColor( textColor )
				.setAlign( Align.CENTER ) );

		this.titlePane = new VBox()
			.setBoxStyle( new BoxStyle()
				.setBorder( new Border()
					.hide( true )
					.hideBottom( false )
					.setColor( borderColor ) ) )
			.setElementHeight( 15 )
			.addElement( this.titleText );
		
		this.changelogPane = new ScrollingText()
			.setLines( lines )
			.setBoxStyle( new BoxStyle().setPadding( 5 ) )
			.setTextStyle( new TextStyle()
				.setColor( textColor ) );
		
		this.borderPane = new BorderPane()
			.setBorder( Align.TOP, this.titlePane )
			.setBorder( Align.CENTER, this.changelogPane )
			.setBoxStyle( new BoxStyle()
				.setBackground( background )
				.setBorder( border )
				.setPadding( 10 ) );
		
		this.setLayout( this.borderPane );
	}
}