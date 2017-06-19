package de.alaoli.games.minecraft.mods.modpackutils;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import de.alaoli.games.minecraft.mods.lib.common.Config;
import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.command.CommandGroup;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.BugReportCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.ChangelogCommand;
import de.alaoli.games.minecraft.mods.modpackutils.client.command.MPUCommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.ChangelogEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.github.GithubEventHandler;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.ChangelogSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.CommandSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.WebservicesSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices.GithubSection;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod( modid = ModpackUtils.MODID, version = ModpackUtils.VERSION, clientSideOnly = true )
public class ModpackUtils implements Initialize
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/

    public static final String MODID = "modpackutils";
    public static final String VERSION = "0.1.0";

    /******************************************************************************************
     * Method - Implements Initialize
     ******************************************************************************************/
    
	@Override
    @EventHandler 
	public void preInit( FMLPreInitializationEvent event ) throws IOException, DataException 
	{
		
		StringJoiner file  = new StringJoiner( File.separator)
			.add( event.getModConfigurationDirectory().toString() )
			.add( MODID + ".json" );
		Config config = new Config();
		config.addNode( new ChangelogSection() );
		config.addNode( new CommandSection() );
		config.addNode( new WebservicesSection() );
		config.setSavePath( file.toString() );
		config.load();
		config.cleanup();
	}
	
	@Override
    @EventHandler 
	public void init( FMLInitializationEvent event ) throws IOException, DataException 
	{
		CommandGroup commands = new MPUCommandGroup();
		
		if( ChangelogSection.enabled )
		{
			ChangelogEventHandler.register();
			commands.add( new ChangelogCommand( commands ) );
		}
		
		if( WebservicesSection.enabled && GithubSection.enabled )
		{
			GithubEventHandler.register();
			commands.add( new BugReportCommand( commands ) );
			
		}
		ClientCommandHandler.instance.registerCommand( commands );
	}
	
	@Override
	public void postInit( FMLPostInitializationEvent event ) throws IOException, DataException {}
}
