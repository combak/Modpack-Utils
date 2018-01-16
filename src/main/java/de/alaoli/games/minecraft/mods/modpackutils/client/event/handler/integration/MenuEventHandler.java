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

import de.alaoli.games.minecraft.mods.modpackutils.client.ui.ChangelogScreen;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.github.IssueScreen;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public abstract class MenuEventHandler
{
	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

    private static GuiButton initChangelogButton(List<GuiButton> buttons)
    {
        GuiButton button = new GuiButton(
            Settings.menu.changelogButtonId, 0, 0,
            I18n.format( "modpackutils:gui.changelog.title" ) );
        button.setWidth( 98 );
        buttons.add( button );

        return button;
    }

    private static GuiButton initBugreportButton(List<GuiButton> buttons)
    {
        GuiButton button = new GuiButton(
            Settings.menu.bugreportButtonId, 0, 0,
            I18n.format( "modpackutils:gui.bugreport.title" ) );
        button.setWidth( 98 );
        buttons.add( button );

        return button;
    }

    static GuiButton getChangelogButton( List<GuiButton> buttons )
    {
        return buttons.stream()
            .filter( button -> button.id == Settings.menu.changelogButtonId )
            .reduce( (button, otherButton) -> {
                throw new IllegalStateException( "Duplicated button id '" + Settings.menu.changelogButtonId + "' found." );
            })
            .orElseGet(() -> initChangelogButton( buttons ));
    }

    static GuiButton getBugreportButton( List<GuiButton> buttons )
    {
        return buttons.stream()
            .filter( button -> button.id == Settings.menu.bugreportButtonId )
            .reduce( (button, otherButton) -> {
                throw new IllegalStateException( "Duplicated button id '" + Settings.menu.bugreportButtonId + "' found." );
            })
            .orElseGet(() -> initBugreportButton( buttons ));
    }

    /* **************************************************************************************************************
	 * Method - MinecraftForge Events
	 ************************************************************************************************************** */

    @SubscribeEvent
    public void actionPerformed( GuiScreenEvent.ActionPerformedEvent event )
    {
        GuiScreen screen = event.getGui();

        if( ( screen instanceof GuiMainMenu ) ||
            ( screen instanceof GuiIngameMenu ))
        {
            GuiButton button = event.getButton();

            if( button.id == Settings.menu.changelogButtonId )
            {
                Minecraft.getMinecraft().displayGuiScreen( new ChangelogScreen() );
            }
            else if( button.id == Settings.menu.bugreportButtonId )
            {
                Minecraft.getMinecraft().displayGuiScreen( new IssueScreen() );
            }
        }
    }
}