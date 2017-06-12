package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class MPUCommandGroup extends CommandGroup
{
	/********************************************************************************
	 * Method
	 ********************************************************************************/
	
	public MPUCommandGroup()
	{
		super( null );
	}

	/********************************************************************************
	 * Method - Implement ICommand
	 ********************************************************************************/
	
	@Override
	public String getCommandName() 
	{
		return "mpu";
	}

	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender ) 
	{
		return true;
	}
}
