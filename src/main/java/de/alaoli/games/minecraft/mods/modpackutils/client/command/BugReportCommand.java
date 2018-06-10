/* *************************************************************************************************************
 * Copyright (c) 2018 DerOli82 <https://github.com/DerOli82>
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
package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import de.alaoli.games.minecraft.mods.lib.common.command.Arguments;
import de.alaoli.games.minecraft.mods.lib.common.command.Command;

import de.alaoli.games.minecraft.mods.lib.ui.screen.ScreenManager;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.BugreportScreen;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
public class BugReportCommand extends Command
{
	/* **************************************************************************************************************
	 * Method - Implement ICommand
	 ************************************************************************************************************** */

	@Override
	public String getName() 
	{
		return "bugreport";
	}

	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender ) 
	{
		return true;
	}

	/* **************************************************************************************************************
	 * Method - Implement Command
	 ************************************************************************************************************** */

	@Override
	public void execute( Arguments args )
	{
		EntityPlayer player = (args.senderIsEntityPlayer) ? (EntityPlayer)args.sender : null;

		try
		{
			ScreenManager.show( BugreportScreen.class );
		}
		catch( InstantiationException | IllegalAccessException e )
		{
			e.printStackTrace();
		}
	}
}