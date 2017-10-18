package de.alaoli.games.minecraft.mods.modpackutils.proxy;

import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;

import de.alaoli.games.minecraft.mods.lib.common.Config;
import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.config.Section;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.ModpackUtils;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.ChangelogSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.CommandSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.ModpackSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.WebservicesSection;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy implements Initialize 
{
    /******************************************************************************************
     * Method - Implements Initialize
     ******************************************************************************************/
	
	@Override
	public void preInit( FMLPreInitializationEvent event ) throws IOException, DataException 
	{
		StringJoiner file  = new StringJoiner( File.separator)
			.add( event.getModConfigurationDirectory().toString() )
			.add( ModpackUtils.MODID + ".json" );
		
		Config.factory( file.toString(), new Section[] { 
			new ModpackSection(), 
			new ChangelogSection(),
			new CommandSection(),
			new WebservicesSection()
		});
	}

	@Override
	public void init( FMLInitializationEvent event ) throws IOException, DataException {}

	@Override
	public void postInit( FMLPostInitializationEvent event ) throws IOException, DataException {}
}
