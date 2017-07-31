package de.alaoli.games.minecraft.mods.modpackutils.client.event.handler;

import de.alaoli.games.minecraft.mods.modpackutils.client.event.OpenChangelogGuiEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.ChangelogScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChangelogEventHandler 
{
	/********************************************************************************
	 * Attributes
	 ********************************************************************************/
	
	private static class LazyHolder
	{
		public static final ChangelogEventHandler INSTANCE = new ChangelogEventHandler();
	}
	
	private OpenChangelogGuiEvent event;
	
	
	/********************************************************************************
	 * Method
	 ********************************************************************************/
	
	private ChangelogEventHandler() {}
	
	public static ChangelogEventHandler getInstance()
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
		if( ( this.event != null ) &&
			( event.phase == TickEvent.Phase.START ) &&
			( !Minecraft.getMinecraft().isGamePaused() ) && 
			( Minecraft.getMinecraft().thePlayer != null ) )
		{
			Minecraft.getMinecraft().displayGuiScreen( new ChangelogScreen() );
			this.event = null;
		}
		
	}
	
	@SubscribeEvent
	@SideOnly( value = Side.CLIENT )
	public void triggerOpenGuiEvent( OpenChangelogGuiEvent event )
	{
		this.event = event;
	}
}
