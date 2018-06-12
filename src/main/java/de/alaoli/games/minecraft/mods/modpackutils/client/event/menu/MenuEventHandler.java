/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or copy
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
package de.alaoli.games.minecraft.mods.modpackutils.client.event.menu;


import de.alaoli.games.minecraft.mods.lib.ui.screen.ScreenManager;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.ChangelogScreen;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.BugreportScreen;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
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

	public static void register()
    {
        if( Settings.menu.mainEnabled ) { Main.register(); }
        if( Settings.menu.ingameEnabled ) { Game.register(); }
    }

    private static GuiButton initChangelogButton(List<GuiButton> buttons)
    {
        GuiButton button = new GuiButton(
            Settings.menu.changelogButtonId, 0, 0,
            I18n.format( "modpackutils:gui.changelog" ) );
        button.setWidth( 98 );
        buttons.add( button );

        return button;
    }

    private static GuiButton initBugreportButton(List<GuiButton> buttons)
    {
        GuiButton button = new GuiButton(
            Settings.menu.bugreportButtonId, 0, 0,
            I18n.format( "modpackutils:gui.bugreport" ) );
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

            try
            {
                if( button.id == Settings.menu.changelogButtonId )
                {
                    ScreenManager.show( ChangelogScreen.class );
                }
                else if( button.id == Settings.menu.bugreportButtonId )
                {
                    ScreenManager.show( BugreportScreen.class );
                }
            }
            catch( InstantiationException | IllegalAccessException e )
            {
                e.printStackTrace();
            }
        }
    }

    /* **************************************************************************************************************
     * Subclass - MainMenu
     ************************************************************************************************************** */

    private static final class Main extends MenuEventHandler
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        private static Main INSTANCE;

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        private Main() {}
        public static void register()
        {
            if( INSTANCE == null )
            {
                INSTANCE = new Main();
            }
            MinecraftForge.EVENT_BUS.register( INSTANCE );
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

                if( Settings.changelog.enabled )
                {
                    GuiButton changelogButton = getChangelogButton( buttons );

                    changelogButton.x = screen.width / 2 - 202;
                    changelogButton.y = screen.height / 4 + 96;
                }

                if( Settings.isBugreportEnabled() )
                {
                    GuiButton bugreportButton = getBugreportButton( buttons );

                    bugreportButton.x = screen.width / 2 + 104;
                    bugreportButton.y = screen.height / 4 + 96;
                }
            }
        }
    }

    /* **************************************************************************************************************
     * Subclass - GameMenu
     ************************************************************************************************************** */

    private static final class Game extends MenuEventHandler
    {
        /* **************************************************************************************************************
         * Attribute
         ************************************************************************************************************** */

        private static Game INSTANCE;

        /* **************************************************************************************************************
         * Method
         ************************************************************************************************************** */

        private Game() {}
        public static void register()
        {
            if( INSTANCE == null )
            {
                INSTANCE = new Game();
            }
            MinecraftForge.EVENT_BUS.register( INSTANCE );
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

                if( Settings.changelog.enabled )
                {
                    GuiButton changelogButton = getChangelogButton( buttons );

                    changelogButton.x = screen.width / 2 - 202;
                    changelogButton.y = screen.height / 4 + 80;
                }

                if( Settings.isBugreportEnabled() )
                {
                    GuiButton bugreportButton = getBugreportButton( buttons );

                    bugreportButton.x = screen.width / 2 + 104;
                    bugreportButton.y = screen.height / 4 + 80;
                }
            }
        }
    }
}