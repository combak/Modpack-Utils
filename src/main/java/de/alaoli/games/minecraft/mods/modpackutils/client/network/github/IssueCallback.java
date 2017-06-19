package de.alaoli.games.minecraft.mods.modpackutils.client.network.github;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.IssueCallbackEvent;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class IssueCallback implements Callback<JsonNode> 
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/
	
	public static final int STATE_PENDING = 0;
	public static final int STATE_COMPLETE = 1;
	public static final int STATE_FAILED = 2;
	public static final int STATE_CANCELLED = 3;
	
	public final EntityPlayer player;
	public final Issue issue;
	
	private int state = STATE_PENDING;
	private HttpResponse<JsonNode> response;
	private UnirestException exception;
	
    /******************************************************************************************
     * Method
     ******************************************************************************************/
	
	public IssueCallback( EntityPlayer player, Issue issue )
	{
		this.player = player;
		this.issue = issue;
	}
	
	public int getState()
	{
		return this.state;
	}
	
    public HttpResponse<JsonNode> getResponse() 
    {
		return this.response;
	}

	public UnirestException getException() 
	{
		return this.exception;
	}

	/******************************************************************************************
     * Method - Implements Callback<JsonNode>
     ******************************************************************************************/
	
	@Override
	public void completed( HttpResponse<JsonNode> response ) 
	{
		this.state = STATE_COMPLETE;
		this.response = response;
		
		MinecraftForge.EVENT_BUS.post( new IssueCallbackEvent( this ) );
	}

	@Override
	public void failed( UnirestException exception )
	{
		this.state = STATE_FAILED;
		this.exception = exception;
		
		MinecraftForge.EVENT_BUS.post( new IssueCallbackEvent( this ) );
	}

	@Override
	public void cancelled() 
	{
		this.state = STATE_CANCELLED;
		
		MinecraftForge.EVENT_BUS.post( new IssueCallbackEvent( this ) );
	}
}
