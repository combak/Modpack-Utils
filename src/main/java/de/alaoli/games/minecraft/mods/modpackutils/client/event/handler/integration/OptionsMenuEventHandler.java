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
package de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.integration;

import de.alaoli.games.minecraft.mods.modpackutils.common.config.integration.MenuSection;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class OptionsMenuEventHandler extends MenuEventHandler
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

    private static class LazyHolder
    {
        private static final OptionsMenuEventHandler INSTANCE = new OptionsMenuEventHandler();
    }

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

    private OptionsMenuEventHandler() {}

    public static void register()
    {
        MinecraftForge.EVENT_BUS.register( OptionsMenuEventHandler.LazyHolder.INSTANCE );
    }

    /* **************************************************************************************************************
	 * Method - MinecraftForge Events
	 ************************************************************************************************************** */

    @SubscribeEvent
    public void initEvent( GuiScreenEvent.InitGuiEvent event )
    {
        GuiScreen screen = event.getGui();

        if( screen instanceof GuiIngameMenu )
        {
            List<GuiButton> buttons = event.getButtonList();

            GuiButton changelogButton = buttons.stream()
                .filter( button -> button.id == MenuSection.changelogButtonId )
                .reduce( (button,otherButton) -> {
                    throw new IllegalStateException( "Duplicated button id '" + MenuSection.changelogButtonId + "' found." ); })
                .orElseGet( () -> initChangelogButton( buttons ) );

            GuiButton bugreportButton = buttons.stream()
                .filter( button -> button.id == MenuSection.bugreportButtonId )
                .reduce( (button,otherButton) -> {
                    throw new IllegalStateException( "Duplicated button id '" + MenuSection.bugreportButtonId + "' found." ); })
                .orElseGet( () -> initBugreportButton( buttons ) );

            changelogButton.x = screen.width/2 - 202;
            changelogButton.y = screen.height/4 + 80;

            bugreportButton.x = screen.width/2 + 104;
            bugreportButton.y = screen.height/4 + 80;
        }
    }
}