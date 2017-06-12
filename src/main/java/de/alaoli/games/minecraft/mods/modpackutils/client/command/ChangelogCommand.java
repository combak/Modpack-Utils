package de.alaoli.games.minecraft.mods.modpackutils.client.command;

import de.alaoli.games.minecraft.mods.lib.common.command.Arguments;
import de.alaoli.games.minecraft.mods.lib.common.command.Command;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.OpenChangelogGuiEvent;
import jline.internal.Log;
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
		Log.info( "bla" );
		MinecraftForge.EVENT_BUS.post( new OpenChangelogGuiEvent() );
		/*
		
		
		if( file.exists() )
		{
			try 
			{
				String line;
				BufferedReader reader = new BufferedReader( new FileReader( file ) );
				
				while( ( line = reader.readLine() ) != null  )
				{
					args.sender.addChatMessage( new TextComponentString( line ));
				}
				reader.close();
			}
			catch( IOException e ) 
			{
				throw new CommandException( "Changelog not found.", e );
			}
		}
		else
		{
			args.sender.addChatMessage( new TextComponentString( "No changelog found." ));
		}
		*/
	}

}
