/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.modpackutils.client.ui;

import java.util.Optional;

import de.alaoli.games.minecraft.mods.lib.ui.component.*;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.KeyboardListener;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseEvent;
import de.alaoli.games.minecraft.mods.lib.ui.event.MouseListener;
import de.alaoli.games.minecraft.mods.lib.ui.screen.Screen;
import de.alaoli.games.minecraft.mods.lib.ui.screen.ScreenManager;
import de.alaoli.games.minecraft.mods.lib.ui.state.State;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.modpackutils.Const;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices.SendIssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class BugreportScreen extends Screen implements MouseListener, KeyboardListener
{
	/* **************************************************************************************************************
	 * Attribute 
	 ************************************************************************************************************** */
	
	private EntityPlayer player;
	private Issue issue;

	private Pane pane;

	private Label labelHeadTitle;

	private TextField textFieldName;
	private TextField textFieldTitle;
	private TextArea textAreaDesc;

	private Button buttonSend;
	private Button buttonCancel;

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

	public BugreportScreen setPlayer( EntityPlayer player )
	{
		this.player = player;
		
		return this;
	}

	public Optional<Issue> getIssue()
	{
		return Optional.ofNullable( this.issue );
	}
	
	public BugreportScreen setIssue( Issue issue )
	{
		this.issue = issue;
		
		return this;
	}

	private void validate()
	{
		if( ( !this.textFieldName.getText().isEmpty() ) &&
			( !this.textFieldTitle.getText().isEmpty() ) &&
			( !this.textAreaDesc.getText().isEmpty() ) )
		{
			this.buttonSend.setDisable( false );
		}
		else
		{
			this.buttonSend.setDisable( true );
		}
	}

	private void sendIssue()
	{
		Issue issue = new Issue(
			this.textFieldName.getText().toString(),
			this.textFieldTitle.getText().toString(),
			this.textAreaDesc.getText().toString()
		);
		MinecraftForge.EVENT_BUS.post( new SendIssueEvent( this.player, issue ) );
	}

	/* **************************************************************************************************************
	 * Method - Implements Screen
	 ************************************************************************************************************** */

	@Override
	public void create()
	{
		this.labelHeadTitle = Components.buildLabel()
			.withText( Const.Lang.BUGREPORT )
			.withTextStyle().withState( State.NONE ).withAlign( Align.CENTER ).withShadow().done().done()
		.build();

		Label labelName = Components.buildLabel()
				.withText(Const.Lang.Bugreport.NAME_LABEL)
				.withRegion().withBounds(10, 30, 100, 15).done()
				.build();

		Label labelTitle = Components.buildLabel()
				.withText(Const.Lang.Bugreport.TITLE_LABEL)
				.withRegion().withBounds(10, 50, 100, 15).done()
				.build();

		Label labelDesc = Components.buildLabel()
				.withText(Const.Lang.Bugreport.DESC_LABEL)
				.withRegion().withBounds(10, 70, 100, 15).done()
				.build();

		this.textFieldName = Components.buildTextField()
			.withPlaceholder( Const.Lang.Bugreport.NAME_PLACEHOLDER )
			.withText( this.getPlayerName(), false )
			.withRegion().withBounds( 115,30, 100, 15 ).done()
		.build();

		this.textFieldTitle = Components.buildTextField()
			.withPlaceholder( Const.Lang.Bugreport.TITLE_PLACEHOLDER )
			.withRegion().withBounds( 115,50, 100, 15 ).done()
		.build();

		this.textAreaDesc = Components.buildTextArea()
			.withPlaceholder( Const.Lang.Bugreport.DESC_PLACEHOLDER )
			.withRegion().withBounds( 115, 70, 100, 100 ).done()
			.withMaxLength( 320 )
		.build();

		this.buttonSend = Components.buildButton()
			.withText( Const.Lang.Bugreport.SEND )
			.withRegion().withBounds( 0,0, 100, 15 ).done()
		.build();

		this.buttonCancel = Components.buildButton()
			.withText( Const.Lang.Bugreport.CANCEL )
			.withRegion().withBounds( 0,0, 100, 15 ).done()
		.build();

		this.pane = Components.buildPane().build();
		this.pane.addComponent(
			this.labelHeadTitle,

			labelName, this.textFieldName,
			labelTitle, this.textFieldTitle,
			labelDesc, this.textAreaDesc,

			this.buttonSend, this.buttonCancel
		);
		this.addListener( this.textFieldName );
		this.addListener( this.textFieldTitle );
		this.addListener( this.textAreaDesc );

		this.addListener( this.buttonSend );
		this.addListener( this.buttonCancel );

		this.buttonSend.addListener( this );
		this.buttonCancel.addListener( this );

		this.buttonSend.setDisable( true );
	}

	@Override
	public void show()
	{
		this.addComponent( this.pane );
	}

	@Override
	public void resize( int width, int height )
	{
		int fieldWidth = width-185,
			centerX = width/2;

		this.pane.setRegion( this.getRegion() );
		this.labelHeadTitle.setRegion( this.getRegion().toBuilder().withDimensions( width, 15 ).build() );

		this.textFieldTitle.setRegion( this.textFieldTitle.getRegion().toBuilder().withWidth( fieldWidth ).build() );
		this.textFieldName.setRegion( this.textFieldName.getRegion().toBuilder().withWidth( fieldWidth ).build() );
		this.textAreaDesc.setRegion( this.textAreaDesc.getRegion().toBuilder().withWidth( fieldWidth ).build() );

		this.buttonSend.setRegion( this.buttonSend.getRegion().toBuilder().withPosition( centerX - 105, height - 30 ).build() );
		this.buttonCancel.setRegion( this.buttonCancel.getRegion().toBuilder().withPosition( centerX + 5, height - 30 ).build() );
	}

	@Override
	public void hide()
	{
		this.removeComponent( this.pane );
	}

	/* **************************************************************************************************************
	 * Method - Implements MouseListener
	 ************************************************************************************************************** */

	@Override
	public void onMouseClick( MouseEvent.Click event )
	{
		if( event.getSrcComponent() == this.buttonCancel )
		{
			ScreenManager.hide();
		}
		else if( event.getSrcComponent() == this.buttonSend )
		{
			this.sendIssue();
			ScreenManager.hide();
		}
	}

	/* **************************************************************************************************************
	 * Method - Implements KeyboardListener
	 ************************************************************************************************************** */

	@Override
	public void onKeyPressed( KeyboardEvent event )
	{
		if( this.textFieldName.isFocused() || this.textFieldTitle.isFocused() || this.textAreaDesc.isFocused() )
		{
			this.validate();
		}
	}
}
