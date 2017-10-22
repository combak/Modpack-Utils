package de.alaoli.games.minecraft.mods.modpackutils.client.event.handler;

import de.alaoli.games.minecraft.mods.lib.common.ui.Screen;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.OpenScreenEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ScreenEventHandler 
{
	/********************************************************************************
	 * Attributes
	 ********************************************************************************/
	
	private static class LazyHolder
	{
		public static final ScreenEventHandler INSTANCE = new ScreenEventHandler();
	}
	
	private Screen screen;
	
	/********************************************************************************
	 * Method
	 ********************************************************************************/
	
	private ScreenEventHandler() {}
	
	public static ScreenEventHandler getInstance()
	{
		return LazyHolder.INSTANCE;
	}
	
	public static void register()
	{
		MinecraftForge.EVENT_BUS.register( LazyHolder.INSTANCE );
	}

	/********************************************************************************
	 * Method - MinecraftForge Events
	 ********************************************************************************/
	
	@SubscribeEvent
	@SideOnly( value = Side.CLIENT )
	public void openGuiEvent( ClientTickEvent event )
	{
		if( ( this.screen != null ) &&
			( event.phase == TickEvent.Phase.START ) &&
			( !Minecraft.getMinecraft().isGamePaused() ) && 
			( Minecraft.getMinecraft().player != null ) )
		{
			Minecraft.getMinecraft().displayGuiScreen( this.screen );
			this.screen = null;
		}
	}
	
	@SubscribeEvent
	@SideOnly( value = Side.CLIENT )
	public void onOpenGuiEvent( OpenScreenEvent event )
	{
		this.screen = event.screen;
	}	
}