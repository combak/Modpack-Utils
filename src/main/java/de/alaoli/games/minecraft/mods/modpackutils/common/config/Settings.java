/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
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
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.modpackutils.common.config;

import de.alaoli.games.minecraft.mods.modpackutils.Const;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Config( modid = Const.Mod.ID, category = Const.Mod.ID )
public final class Settings
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    @Config.LangKey( Const.Lang.CONFIG_MENU )
    public static Menu menu = new Menu();

    @Config.LangKey( Const.Lang.CONFIG_COMMAND )
    public static Command command = new Command();

    @Config.LangKey( Const.Lang.CHANGELOG )
    public static Changelog changelog = new Changelog();

    @Config.LangKey( Const.Lang.CONFIG_WEBSERVICES )
    public static Webservices webservices = new Webservices();

    /* **************************************************************************************************************
     * Subclass - Menu
     ************************************************************************************************************** */

    public static class Menu
    {
        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.CONFIG_MENU_MAIN_ENABLED )
        @Config.Comment( "Enables the main menu button integration." )
        public boolean mainEnabled = true;

        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.CONFIG_MENU_GAME_ENABLED)
        @Config.Comment( "Enables the game menu button integration." )
        public boolean ingameEnabled = true;

        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.CONFIG_MENU_CHANGELOG_BUTTONID )
        @Config.Comment( "Only change the changelog button id, if there's any id conflict." )
        public int changelogButtonId = 1000;

        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.CONFIG_MENU_BUGREPORT_BUTTONID )
        @Config.Comment( "Only change the bug report button id, if there's any id conflict." )
        public int bugreportButtonId = 1001;
    }

    /* **************************************************************************************************************
     * Subclass - Command
     ************************************************************************************************************** */

    public static class Command
    {
        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.CONFIG_COMMAND_NAME )
        @Config.Comment( "Only change the command name, if there're any conflict." )
        public String name = "mpu";
    }

    /* **************************************************************************************************************
     * Subclass - Changelog
     ************************************************************************************************************** */

    public static class Changelog
    {
        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.ENABLED )
        @Config.Comment( "Enables the changelog gui." )
        public boolean enabled = true;

        /**
         * Changelog file name.
         */
        @Config.LangKey( Const.Lang.CONFIG_CHANGELOG_FILE )
        @Config.Comment( "Path to the changelog file." )
        public String file = "CHANGELOG.md";
    }

    /* **************************************************************************************************************
     * Subclass - Webservices
     ************************************************************************************************************** */

    public static class Webservices
    {
        @Config.RequiresMcRestart
        @Config.LangKey( Const.Lang.ENABLED )
        @Config.Comment( "Enables webservice function, requires modpack-webservice server as backend." )
        public boolean enabled = false;

        @Config.LangKey( Const.Lang.CONFIG_WEBSERVICES_URL )
        @Config.Comment( "Webservice server URL." )
        public String url = "";

        @Config.LangKey( Const.Lang.CONFIG_WEBSERVICES_GITHUB )
        public Github github = new Github();

        /* **************************************************************************************************************
         * Subclass - Github
         ************************************************************************************************************** */

        public static class Github
        {
            @Config.RequiresMcRestart
            @Config.LangKey( Const.Lang.ENABLED )
            @Config.Comment( "Enables webservice github function, requires modpack-webservice server as backend." )
            public boolean enabled = false;

            @Config.LangKey( Const.Lang.CONFIG_WEBSERVICES_GITHUB_REPO )
            @Config.Comment( "Repository token." )
            public String repository = "";
        }
    }

    /* **************************************************************************************************************
     * Subclass - Sync
     ************************************************************************************************************** */

    @Mod.EventBusSubscriber( modid = Const.Mod.ID )
    public static class SyncHandler
    {
        @SubscribeEvent
        public static void onConfigChangedEvent( ConfigChangedEvent.OnConfigChangedEvent event )
        {
            if( event.getModID().equals( Const.Mod.ID ) )
            {
                ConfigManager.sync( Const.Mod.ID, Config.Type.INSTANCE );
            }
        }
    }

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public static boolean isBugreportEnabled()
    {
        return webservices.enabled && webservices.github.enabled;
    }

    public static boolean isAnyFeatureAvailable()
    {
        return changelog.enabled || isBugreportEnabled();
    }
}