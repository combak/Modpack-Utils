package de.alaoli.games.minecraft.mods.modpackutils.client.event.handler.webservices;

import java.util.concurrent.Future;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices.IssueCallbackEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices.SendIssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.network.github.IssueCallback;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GithubEventHandler
{
	/********************************************************************************
	 * Attributes
	 ********************************************************************************/
	
	private static class LazyHolder
	{
		public static final GithubEventHandler INSTANCE = new GithubEventHandler();
	}
	
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
					if( event.callback.player != null )
					{
						event.callback.player.sendMessage(new TextComponentString(event.callback.getResponse().getStatusText()));
					}
				}
				break;
				
			case IssueCallback.STATE_FAILED :
				if( event.callback.player != null )
				{
					event.callback.player.sendMessage(new TextComponentString(event.callback.getException().getMessage()));
				}
				break;
				
			case IssueCallback.STATE_CANCELLED :
				break;
		}
	}
	
	@SubscribeEvent
	public void onSendIssueEvent( SendIssueEvent event )
	{
		this.pendingIssue = event.issue;
		
		Future<HttpResponse<JsonNode>> response = Unirest.post(Settings.webservices.url + "/issue" )
			.header( "Accept", "application/json" )
			.header( "Content-Type", "application/json" )
			//.body( event.issue.serialize().toString() )
			.asJsonAsync( new IssueCallback( event.player, event.issue ) );	
	}
}
