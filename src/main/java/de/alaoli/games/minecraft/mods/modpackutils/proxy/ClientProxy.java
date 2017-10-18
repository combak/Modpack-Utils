package de.alaoli.games.minecraft.mods.modpackutils.proxy;

import java.io.IOException;

import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.BugReportCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.ChangelogCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.MPUCommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.ScreenEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.webservices.GithubEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.client.integration.custommainmenu.event.handler.CustomMainMenuEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.ChangelogSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.WebservicesSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.integration.CustomMainMenuSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices.GithubSection;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy
{
    /******************************************************************************************
     * Method - Implement CommonProxy
     ******************************************************************************************/

	@Override
	public void init( FMLInitializationEvent event ) throws IOException, DataException 
	{
		CommandGroup commands = new MPUCommandGroup();
		
		if( ChangelogSection.enabled )
		{
			commands.addComponent( new ChangelogCommand() );
		}
		
		if( WebservicesSection.enabled && GithubSection.enabled )
		{
			GithubEventHandler.register();
			commands.addComponent( new BugReportCommand() );
			
		}
		ClientCommandHandler.instance.registerCommand( commands );
	
		ScreenEventHandler.register();
	}
}
