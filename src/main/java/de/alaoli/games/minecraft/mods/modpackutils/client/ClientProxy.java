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
package de.alaoli.games.minecraft.mods.modpackutils.client;

import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.BugReportCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.ChangelogCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.MPUCommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.integration.IngameMenuEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.integration.MainMenuEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.webservices.GithubEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.common.CommonProxy;

import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class ClientProxy extends CommonProxy
{
	/* **************************************************************************************************************
	 * Method - Implement Proxy
	 ************************************************************************************************************** */

	@Override
	public void onFMLInitializationEvent( FMLInitializationEvent event )
	{
		super.onFMLInitializationEvent( event );

		CommandGroup commands = new MPUCommandGroup();
		
		if(Settings.changelog.enabled ) { commands.addComponent( new ChangelogCommand() ); }

		if( Settings.webservices.enabled && Settings.webservices.github.enabled )
		{
			GithubEventHandler.register();
			commands.addComponent( new BugReportCommand() );
		}
		ClientCommandHandler.instance.registerCommand( commands );

		if( Settings.menu.mainEnabled ) { MainMenuEventHandler.register(); }
		if( Settings.menu.ingameEnabled ) { IngameMenuEventHandler.register(); }
	}
}