package de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.github;

import java.util.concurrent.Future;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.IssueCallbackEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.OpenIssueGuiEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.SendIssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.network.github.IssueCallback;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.github.IssueGui;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.WebservicesSection;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GithubEventHandler
{
	/********************************************************************************
	 * Attributes
	 ********************************************************************************/
	
	private static class LazyHolder
	{
		public static final GithubEventHandler INSTANCE = new GithubEventHandler();
	}
	
	private OpenIssueGuiEvent event;
	private Issue pendingIssue;
	
	/********************************************************************************
	 * Method
	 ********************************************************************************/
	
	private GithubEventHandler() {}
	
	public static GithubEventHandler getInstance()
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
	public void onIssueCallback( IssueCallbackEvent event )
	{
		switch( event.callback.getState() )
		{
			case IssueCallback.STATE_COMPLETE :
				HttpResponse<JsonNode> response = event.callback.getResponse();
				
				if( response.getStatus() == 201 )
				{
					
					this.pendingIssue = null;
				}
				else
				{
					
				}
				break;
				
			case IssueCallback.STATE_FAILED :
				event.callback.player.sendMessage( new TextComponentString( event.callback.getException().getMessage() ) );
				break;
				
			case IssueCallback.STATE_CANCELLED :
				break;
		}
	}
	
	@SubscribeEvent
	@SideOnly( value = Side.CLIENT )
	public void onSendIssueEvent( SendIssueEvent event )
	{
		this.pendingIssue = event.issue;
		
		Future<HttpResponse<JsonNode>> response = Unirest.post( WebservicesSection.url + "/issue" )
			.header( "Accept", "application/json" )
			.header( "Content-Type", "application/json" )
			.body( event.issue.serialize().toString() )
			.asJsonAsync( new IssueCallback( event.player, event.issue ) );	
	}
	
	@SubscribeEvent
	@SideOnly( value = Side.CLIENT )
	public void openGuiEvent( ClientTickEvent event )
	{
		if( ( this.event != null ) &&
			( event.phase == TickEvent.Phase.START ) &&
			( !Minecraft.getMinecraft().isGamePaused() ) && 
			( Minecraft.getMinecraft().player != null ) )
		{
			Minecraft.getMinecraft().displayGuiScreen( new IssueGui( this.event.player, this.pendingIssue ) );
			this.event = null;
		}
		
	}
	
	@SubscribeEvent
	@SideOnly( value = Side.CLIENT )
	public void triggerOpenGuiEvent( OpenIssueGuiEvent event )
	{
		this.event = event;
	}	
}
