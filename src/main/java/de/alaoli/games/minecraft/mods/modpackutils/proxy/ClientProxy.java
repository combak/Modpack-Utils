package de.alaoli.games.minecraft.mods.modpackutils.proxy;

import java.io.IOException;

import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.BugReportCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.ChangelogCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.MPUCommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.ChangelogEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.github.GithubEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.ChangelogSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.WebservicesSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices.GithubSection;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements Initialize 
{
    /******************************************************************************************
     * Method - Implements Initialize
     ******************************************************************************************/
	
	@Override
	public void preInit( FMLPreInitializationEvent event ) throws IOException, DataException {}

	@Override
	public void init( FMLInitializationEvent event ) throws IOException, DataException 
	{
		CommandGroup commands = new MPUCommandGroup();
		
		if( ChangelogSection.enabled )
		{
			ChangelogEventHandler.register();
			commands.addNode( new ChangelogCommand( commands ) );
		}
		
		if( WebservicesSection.enabled && GithubSection.enabled )
		{
			GithubEventHandler.register();
			commands.addNode( new BugReportCommand( commands ) );
			
		}
		ClientCommandHandler.instance.registerCommand( commands );
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) throws IOException, DataException {}

}
