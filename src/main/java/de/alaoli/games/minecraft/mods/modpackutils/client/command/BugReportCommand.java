package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import de.alaoli.games.minecraft.mods.lib.common.command.Arguments;
import de.alaoli.games.minecraft.mods.lib.common.command.Command;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.OpenIssueGuiEvent;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

public class BugReportCommand extends Command
{
	public BugReportCommand( Command parent )
	{
		super( parent );
	
	}

	/********************************************************************************
	 * Method - Implement ICommand
	 ********************************************************************************/
	
	@Override
	public String getCommandName() 
	{
		return "bugreport";
	}

	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender ) 
	{
		return true;
	}
	
	@Override
	public String getCommandUsage( ICommandSender sender ) 
	{
		return super.getCommandUsage( sender ) + " <title> <description>";
	}
	
	/********************************************************************************
	 * Method - Implement Command
	 ********************************************************************************/
	
	@Override
	public void processCommand( Arguments args )
	{
		EntityPlayer player = (args.senderIsEntityPlayer) ? (EntityPlayer)args.sender : null;
		
		MinecraftForge.EVENT_BUS.post( new OpenIssueGuiEvent( player ) );
	}

}
