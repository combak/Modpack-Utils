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
package de.alaoli.games.minecraft.mods.modpackutils.client.event;

import de.alaoli.games.minecraft.mods.lib.ui.Screen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

/**
 * Only required for opening screens with commands.
 *
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class ScreenEventHandler 
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private static class LazyHolder
	{
		private static final ScreenEventHandler INSTANCE = new ScreenEventHandler();
	}
	private Screen screen;
	
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */
	
	private ScreenEventHandler() {}

	public static void register()
	{
		MinecraftForge.EVENT_BUS.register( LazyHolder.INSTANCE );
	}

	/* **************************************************************************************************************
	 * Method - MinecraftForge Events
	 ************************************************************************************************************** */

	@SubscribeEvent
	public void openScreenEvent( ClientTickEvent event )
	{
		if( ( this.screen != null ) &&
			( event.phase == TickEvent.Phase.START ) &&
			( !Minecraft.getMinecraft().isGamePaused() ) && 
			( Minecraft.getMinecraft().player != null ) )
		{
			Minecraft.getMinecraft().displayGuiScreen( this.screen );
			this.screen = null;
		}
	}
	
	@SubscribeEvent
	public void onOpenScreenEvent( OpenScreenEvent event )
	{
		this.screen = event.screen;
	}	
}