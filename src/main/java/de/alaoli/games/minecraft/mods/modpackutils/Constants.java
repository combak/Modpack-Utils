/* *************************************************************************************************************
 * Copyright (c) 2018 DerOli82 <https://github.com/DerOli82>
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

import net.minecraftforge.fml.common.Mod;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public final class Constants
{
    public static final class Mod
    {
        public static final String ID = "modpackutils";
        public static final String NAME = "ComBak Modpack Utils";
        public static final String VERSION = "0.2.0";

        public static final String PROXY_COMMON = "de.alaoli.games.minecraft.mods.modpackutils.common.CommonProxy";
        public static final String PROXY_CLIENT = "de.alaoli.games.minecraft.mods.modpackutils.client.ClientProxy";

        private Mod() {}
    }

    public static final class Lang {

        public static final String ENABLED = "modpackutils:enabled";
        public static final String CHANGELOG = "modpackutils:changelog";
        public static final String BUGREPORT = "modpackutils:bugreport";

        public static final String CONFIG_MENU = "modpackutils:config.menu";
        public static final String CONFIG_MENU_MAIN_ENABLED = "modpackutils:config.menu.main_enabled";
        public static final String CONFIG_MENU_INGAME_ENABLED = "modpackutils:config.menu.ingame_enabled";
        public static final String CONFIG_MENU_CHANGELOG_BUTTONID = "modpackutils:config.menu.changelog_buttonid";
        public static final String CONFIG_MENU_BUGREPORT_BUTTONID = "modpackutils:config.menu.bugreport_buttonid";

        public static final String CONFIG_COMMAND = "modpackutils:config.command";
        public static final String CONFIG_COMMAND_NAME = "modpackutils:config.command.name";

        public static final String CONFIG_CHANGELOG_FILE = "modpackutils:config.changelog.file";

        public static final String CONFIG_WEBSERVICES = "modpackutils:config.webservices";
        public static final String CONFIG_WEBSERVICES_URL = "modpackutils:config.webservices.url";
        public static final String CONFIG_WEBSERVICES_GITHUB = "modpackutils:config.webservices.github";
        public static final String CONFIG_WEBSERVICES_GITHUB_REPO = "modpackutils:config.webservices.github.repo";

        private Lang() {}
    }
    private Constants() {}
}
