package de.alaoli.games.minecraft.mods.modpackutils;

import java.io.IOException;
import de.alaoli.games.minecraft.mods.lib.common.Initialize;
import de.alaoli.games.minecraft.mods.lib.common.data.DataException;
import de.alaoli.games.minecraft.mods.modpackutils.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
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

    @SidedProxy( 
		clientSide = "de.alaoli.games.minecraft.mods.modpackutils.proxy.ClientProxy",
		serverSide = "de.alaoli.games.minecraft.mods.modpackutils.proxy.ServerProxy"
	)
    public static CommonProxy proxy;
    
    /******************************************************************************************
     * Method - Implements Initialize
     ******************************************************************************************/
    
	@Override
    @EventHandler 
	public void preInit( FMLPreInitializationEvent event ) throws IOException, DataException 
	{
		proxy.preInit( event );
	}
	
	@Override
    @EventHandler 
	public void init( FMLInitializationEvent event ) throws IOException, DataException 
	{		
		proxy.init( event );
	}
	
	@Override
	@EventHandler 
	public void postInit( FMLPostInitializationEvent event ) throws IOException, DataException 
	{
		proxy.postInit( event );
	}
}
