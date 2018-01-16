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
package de.alaoli.games.minecraft.mods.modpackutils.client.ui.github;

import java.util.Optional;
import de.alaoli.games.minecraft.mods.lib.ui.Screen;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.Border;
import de.alaoli.games.minecraft.mods.lib.ui.element.*;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.*;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.layout.BorderPane;
import de.alaoli.games.minecraft.mods.lib.ui.layout.Pane;
import de.alaoli.games.minecraft.mods.lib.ui.layout.VBox;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices.SendIssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class IssueScreen extends Screen
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */
	
	private EntityPlayer player;
	private Issue issue;

	private final BorderPane borderPane = new BorderPane();
	private final VBox titlePane = new VBox();
	private final Label titleText = new Label();
	private final Pane formPane = new Pane();

	private final Label nameLabel = new Label();
	private final TextField name = new TextField();

	private final Label titleLabel = new Label();
	private final TextField title = new TextField();

	private final Label descriptionLabel = new Label();
	private final TextArea description = new TextArea();

	private final Button sendButton = new Button();
	private final Button cancelButton = new Button();

	/* **************************************************************************************************************
	 * Method 
	 ************************************************************************************************************** */

	private String getPlayerName()
	{
		if( this.player != null )
		{
			return this.player.getDisplayNameString();
		}
		else
		{
			return Minecraft.getMinecraft().getSession().getUsername();
		}
	}

	public Optional<EntityPlayer> getPlayer()
	{
		return Optional.ofNullable( this.player );
	}

	public IssueScreen setPlayer( EntityPlayer player )
	{
		this.player = player;
		
		return this;
	}

	public Optional<Issue> getIssue()
	{
		return Optional.ofNullable( this.issue );
	}
	
	public IssueScreen setIssue( Issue issue )
	{
		this.issue = issue;
		
		return this;
	}

	private void validate()
	{
		if( ( !this.name.getText().orElse( "" ).isEmpty() ) &&
			( !this.title.getText().orElse( "" ).isEmpty() ) &&
			( !this.description.getText().orElse( "" ).isEmpty() ) )
		{
			this.sendButton.setDisable( false );
		}
		else
		{
			this.sendButton.setDisable( true );
		}
	}

	private void sendIssue()
	{
		Issue issue = new Issue(
			this.name.getText().orElse( "" ),
			this.title.getText().orElse( ""),
			this.description.getText().orElse( "" )
		);
		MinecraftForge.EVENT_BUS.post( new SendIssueEvent( this.player, issue ) );
	}

	/* **************************************************************************************************************
	 * Method - Implements Screen
	 ************************************************************************************************************** */

	@Override
	public void init()
	{
		int fieldWidth = this.width-185,
			centerX = this.width/2;
		BoxStyle boxStyle = Defaults.getBoxStyles().getOptions();
		TextStyle labelStyle = Defaults.getTextStyles().getText();

		this.titleText
			.setText( I18n.format( "modpackutils:gui.bugreport.title" ) )
			.setTextStyle( Defaults.getTextStyles().getTitle() )
			.setSize( 100, 15 );

		this.titlePane
			.addElement( this.titleText )
			.setBoxStyle( boxStyle.extend()
				.setAlign( Align.CENTER )
				.setBorder( new Border( Colors.factory( Color.DARKGRAY ) ).hide( false, false, false, true )))
			.setHeight( 15 );

		this.nameLabel
			.setText( I18n.format( "modpackutils:gui.bugreport.label.name" ) )
			.setTextStyle( labelStyle )
			.setBounds( 10,10, 100, 15 );

		this.name
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.name" ) )
			.setText( this.getPlayerName() )
			.onKeyPressed( key -> this.validate() )
			.setBounds(115,10, fieldWidth, 15  );

		this.titleLabel
			.setText( I18n.format( "modpackutils:gui.bugreport.label.title" ) )
			.setTextStyle( labelStyle )
			.setBounds( 10,30, 100, 15 );

		this.title
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.title" ) )
			.setMaxLength( 100 )
			.onKeyPressed( key -> this.validate() )
			.setBounds(115,30, fieldWidth, 15  );

		this.descriptionLabel
			.setText( I18n.format( "modpackutils:gui.bugreport.label.description" ) )
			.setTextStyle( labelStyle )
			.setBounds( 10,50, 100, 15 );

		this.description
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.description" ) )
			.setMaxLength( 5000 )
			.onKeyPressed( key -> this.validate() )
			.setBounds(115,50, fieldWidth, 100 );

		this.sendButton
			.setDisable( true )
			.setText( I18n.format( "modpackutils:gui.bugreport.button.send" ) )
			.onMouseClicked( mouse -> {
				if( ( mouse.button == MouseEvent.BUTTON_LEFT ) &&
					( !this.sendButton.isDisabled() ) )
				{
					this.sendIssue();
					this.close();
				}
			})
			.setBounds(centerX-110,170, 100, 20 );

		this.cancelButton
			.setText( I18n.format( "modpackutils:gui.bugreport.button.cancel" ) )
			.onMouseClicked( mouse -> { if( mouse.button == MouseEvent.BUTTON_LEFT ) { this.close(); } } )
			.setBounds( centerX-110,170, 100, 20 );

		this.formPane
			.setBoxStyle( boxStyle.extend().setBorder(Defaults.getDrawables().getBorder() ) )
			.add( this.nameLabel )
			.add( this.name )
			.add( this.titleLabel )
			.add( this.title )
			.add( this.descriptionLabel)
			.add( this.description )
			.add( this.sendButton )
			.add( this.cancelButton );

		this.borderPane
			.setBorder( Align.TOP, this.titlePane )
			.setBorder( Align.CENTER, this.formPane )
			.setBoxStyle( new BoxStyling().setMargin( 10, 30, 30, 10 ) );

		this.setLayout( this.borderPane )
			.addListener( this.name )
			.addListener( this.title )
			.addListener( this.description )
			.addListener( this.sendButton )
			.addListener( this.cancelButton );
	}

	/* **************************************************************************************************************
	 * Method - Implements Layout
	 ************************************************************************************************************** */

	@Override
	public void layout()
	{
		int fieldWidth = this.width-185;
		int centerX = this.width/2;

		this.name.setSize( fieldWidth, 15  );
		this.title.setSize( fieldWidth, 15 );
		this.description.setSize( fieldWidth, 100 );
		this.sendButton.setLocation( centerX-110,170 );
		this.cancelButton.setLocation( centerX+10,170 );
	}
}
