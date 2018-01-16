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

import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class MainMenuEventHandler extends MenuEventHandler
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

    private static class LazyHolder
    {
        private static final MainMenuEventHandler INSTANCE = new MainMenuEventHandler();
    }

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

    private MainMenuEventHandler() {}

    public static void register()
    {
        MinecraftForge.EVENT_BUS.register( MainMenuEventHandler.LazyHolder.INSTANCE );
    }

    /* **************************************************************************************************************
	 * Method - MinecraftForge Events
	 ************************************************************************************************************** */

    @SubscribeEvent
    public void initEvent( GuiScreenEvent.InitGuiEvent event )
    {
        GuiScreen screen = event.getGui();

        if( screen instanceof GuiMainMenu )
        {
            List<GuiButton> buttons = event.getButtonList();

            GuiButton changelogButton = buttons.stream()
                .filter( button -> button.id == Settings.menu.changelogButtonId )
                .reduce( (button,otherButton) -> {
                    throw new IllegalStateException( "Duplicated button id '" + Settings.menu.changelogButtonId + "' found." ); })
                .orElseGet( () -> initChangelogButton( buttons ) );

            GuiButton bugreportButton = buttons.stream()
                .filter( button -> button.id == Settings.menu.bugreportButtonId )
                .reduce( (button,otherButton) -> {
                    throw new IllegalStateException( "Duplicated button id '" + Settings.menu.bugreportButtonId + "' found." ); })
                .orElseGet( () -> initBugreportButton( buttons ) );

            changelogButton.x = screen.width/2 - 202;
            changelogButton.y = screen.height/4 + 96;

            bugreportButton.x = screen.width/2 + 104;
            bugreportButton.y = screen.height/4 + 96;
        }
    }
}