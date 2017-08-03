package de.alaoli.games.minecraft.mods.modpackutils.proxy;

import java.io.IOException;

import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements Initialize 
{
    /******************************************************************************************
     * Method - Implements Initialize
     ******************************************************************************************/
	
	@Override
	public void preInit( FMLPreInitializationEvent event ) throws IOException, DataException {}

	@Override
	public void init( FMLInitializationEvent event ) throws IOException, DataException {}

	@Override
	public void postInit(FMLPostInitializationEvent event) throws IOException, DataException {}

}
