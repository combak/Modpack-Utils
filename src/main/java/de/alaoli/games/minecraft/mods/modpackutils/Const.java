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
package de.alaoli.games.minecraft.mods.modpackutils;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Const
{
    /* **************************************************************************************************************
     * Subclass - Mod
     ************************************************************************************************************** */

    public static final class Mod
    {
        public static final String ID = "modpackutils";
        public static final String NAME = "ComBak Modpack Utils";
        public static final String VERSION = "0.2.2";

        public static final String PROXY_COMMON = "de.alaoli.games.minecraft.mods.modpackutils.common.CommonProxy";
        public static final String PROXY_CLIENT = "de.alaoli.games.minecraft.mods.modpackutils.client.ClientProxy";

        private Mod() {}
    }

    /* **************************************************************************************************************
     * Subclass - Lang
     ************************************************************************************************************** */

    public static final class Lang
    {
        public static final String ENABLED = "modpackutils:gui.enabled";
        public static final String CHANGELOG = "modpackutils:gui.changelog";
        public static final String BUGREPORT = "modpackutils:gui.bugreport";

        public static final String CONFIG_MENU = "modpackutils:gui.config.menu";
        public static final String CONFIG_MENU_MAIN_ENABLED = "modpackutils:gui.config.menu.main_enabled";
        public static final String CONFIG_MENU_GAME_ENABLED = "modpackutils:gui.config.menu.ingame_enabled";
        public static final String CONFIG_MENU_CHANGELOG_BUTTONID = "modpackutils:gui.config.menu.changelog_buttonid";
        public static final String CONFIG_MENU_BUGREPORT_BUTTONID = "modpackutils:gui.config.menu.bugreport_buttonid";

        public static final String CONFIG_COMMAND = "modpackutils:gui.config.command";
        public static final String CONFIG_COMMAND_NAME = "modpackutils:gui.config.command.name";

        public static final String CONFIG_CHANGELOG_FILE = "modpackutils:gui.config.changelog.file";

        public static final String CONFIG_WEBSERVICES = "modpackutils:gui.config.webservices";
        public static final String CONFIG_WEBSERVICES_URL = "modpackutils:gui.config.webservices.url";
        public static final String CONFIG_WEBSERVICES_GITHUB = "modpackutils:gui.config.webservices.github";
        public static final String CONFIG_WEBSERVICES_GITHUB_REPO = "modpackutils:gui.config.webservices.github.repo";

        /* **************************************************************************************************************
         * Subclass - Bugreport
         ************************************************************************************************************** */

        public static final class Bugreport
        {
            public static final String NAME_LABEL = "modpackutils:gui.bugreport.name.label";
            public static final String NAME_PLACEHOLDER = "modpackutils:gui.bugreport.name.placeholder";

            public static final String TITLE_LABEL = "modpackutils:gui.bugreport.title.label";
            public static final String TITLE_PLACEHOLDER = "modpackutils:gui.bugreport.title.placeholder";

            public static final String DESC_LABEL = "modpackutils:gui.bugreport.desc.label";
            public static final String DESC_PLACEHOLDER = "modpackutils:gui.bugreport.desc.placeholder";

            public static final String SEND = "modpackutils:gui.bugreport.send";
            public static final String CANCEL = "modpackutils:gui.bugreport.cancel";

            private Bugreport() {}
        }

        /* **************************************************************************************************************
         * Subclass - Changelog
         ************************************************************************************************************** */

        public static final class Changelog
        {
            public static final String FILE_NOT_FOUND = "modpackutils:gui.changelog.notfound";
            public static final String FILE_IS_EMPTY = "modpackutils:gui.changelog.isempty";
            private Changelog() {}
        }
        private Lang() {}
    }
    private Const() {}
}