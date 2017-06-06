package de.alaoli.games.minecraft.mods.modpackutils.command;

import java.util.ArrayList;
import java.util.List;

import de.alaoli.games.minecraft.mods.lib.common.command.Command;
import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.config.ChangelogSection;
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
	
		if( ChangelogSection.enabled )
		{
			this.add( new ChangelogCommand( this ) );
		}
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
