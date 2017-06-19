package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import de.alaoli.games.minecraft.mods.lib.common.command.Arguments;
import de.alaoli.games.minecraft.mods.lib.common.command.Command;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.OpenChangelogGuiEvent;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

public class ChangelogCommand extends Command
{
	public ChangelogCommand( Command parent )
	{
		super( parent );
	
	}

	/******************************************************************************************
	 * Method - Implement ICommand
	 ******************************************************************************************/
	
	@Override
	public String getCommandName() 
	{
		return "changelog";
	}

	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender ) 
	{
		return true;
	}
	
	/******************************************************************************************
	 * Method - Implement Command
	 ******************************************************************************************/
	
	@Override
	public void processCommand( Arguments args )
	{
		MinecraftForge.EVENT_BUS.post( new OpenChangelogGuiEvent() );
	}

}
