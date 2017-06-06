package de.alaoli.games.minecraft.mods.modpackutils.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import de.alaoli.games.minecraft.mods.lib.common.command.Arguments;
import de.alaoli.games.minecraft.mods.lib.common.command.Command;
import de.alaoli.games.minecraft.mods.lib.common.command.CommandException;
import de.alaoli.games.minecraft.mods.modpackutils.config.ChangelogSection;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class ChangelogCommand extends Command
{
	public ChangelogCommand( Command parent )
	{
		super( parent );
	
	}

	/********************************************************************************
	 * Method - Implement ICommand
	 ********************************************************************************/
	
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
	
	/********************************************************************************
	 * Method - Implement Command
	 ********************************************************************************/
	
	@Override
	public void processCommand( Arguments args )
	{
		File file = new File( ChangelogSection.file );
		
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
		
	}

}
