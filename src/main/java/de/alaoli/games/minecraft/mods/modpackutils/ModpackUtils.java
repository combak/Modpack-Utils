package de.alaoli.games.minecraft.mods.modpackutils;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import de.alaoli.games.minecraft.mods.lib.common.Config;
import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.command.MPUCommandGroup;
import de.alaoli.games.minecraft.mods.modpackutils.config.ChangelogSection;
import de.alaoli.games.minecraft.mods.modpackutils.config.GithubWebserviceSection;
import de.alaoli.games.minecraft.mods.modpackutils.config.WebservicesSection;
import de.alaoli.games.minecraft.mods.modpackutils.event.handler.github.GithubEventHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
		config.registerSection( new ChangelogSection() );
		config.registerSection( new WebservicesSection() );
		config.registerSection( new GithubWebserviceSection() );
		config.setSavePath( file.toString() );
		config.load();
		config.cleanup();
	}
	
	@Override
    @EventHandler 
	public void init( FMLInitializationEvent event ) throws IOException, DataException 
	{
		ClientCommandHandler.instance.registerCommand( new MPUCommandGroup() );
	}
	
	@Override
	public void postInit( FMLPostInitializationEvent event ) throws IOException, DataException {}
}
