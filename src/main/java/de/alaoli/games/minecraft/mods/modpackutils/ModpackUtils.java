package de.alaoli.games.minecraft.mods.modpackutils;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import de.alaoli.games.minecraft.mods.lib.common.Config;
import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.config.ChangelogSection;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModpackUtils.MODID, version = ModpackUtils.VERSION)
public class ModpackUtils implements Initialize
{
    public static final String MODID = "modpackutils";
    public static final String VERSION = "0.1.0";
    
    /******************************************************************************************
     * Method - Implements Initialize
     ******************************************************************************************/
    
	@Override
	public void preInit(FMLPreInitializationEvent event) throws IOException, DataException 
	{
		StringJoiner file  = new StringJoiner( File.separator)
			.add( event.getModConfigurationDirectory().toString() )
			.add( MODID + ".json" );
		Config config = new Config();
		config.registerSection( new ChangelogSection() );
		config.setSavePath( file.toString() );
		config.load();
		config.cleanup();
	}
	
	@Override
	public void init(FMLInitializationEvent event) throws IOException, DataException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postInit(FMLPostInitializationEvent event) throws IOException, DataException {
		// TODO Auto-generated method stub
		
	}
}
