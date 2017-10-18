package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import java.util.List;

import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.CommandSection;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class MPUCommandGroup extends CommandGroup
{
	/********************************************************************************
	 * Method - Implement ICommand
	 ********************************************************************************/
	
	@Override
	public String getName() 
	{
		return CommandSection.command;
	}

	@Override
	public List<String> getAliases()
	{
		List<String> list = super.getAliases();
		list.addAll( CommandSection.commandAliases );
		
		return list;
	}

	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender ) 
	{
		return true;
	}
}
