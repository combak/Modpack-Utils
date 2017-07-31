package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import de.alaoli.games.minecraft.mods.lib.common.command.Arguments;
import de.alaoli.games.minecraft.mods.lib.common.command.Command;
import de.alaoli.games.minecraft.mods.lib.common.command.CommandNode;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.OpenIssueGuiEvent;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

public class BugReportCommand extends Command
{
	public BugReportCommand( CommandNode parent )
	{
		super( parent );
	
	}

	/********************************************************************************
	 * Method - Implement ICommand
	 ********************************************************************************/
	
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
	
	/********************************************************************************
	 * Method - Implement Command
	 ********************************************************************************/
	
	@Override
	public void execute( Arguments args )
	{
		EntityPlayer player = (args.senderIsEntityPlayer) ? (EntityPlayer)args.sender : null;
		
		MinecraftForge.EVENT_BUS.post( new OpenIssueGuiEvent( player ) );
	}

}
