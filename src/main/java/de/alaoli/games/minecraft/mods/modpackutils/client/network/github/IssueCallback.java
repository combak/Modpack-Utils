/* *************************************************************************************************************
 * Copyright (c) 2017 - 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.modpackutils.client.network.github;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices.IssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;

@Getter
public final class IssueCallback implements Callback<JsonNode>
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */
	
	public static final int STATE_PENDING = 0;
	public static final int STATE_COMPLETE = 1;
	public static final int STATE_FAILED = 2;
	public static final int STATE_CANCELLED = 3;

	private final Issue issue;
    private int state = STATE_PENDING;
    private HttpResponse<JsonNode> response;
    private UnirestException exception;

    /* **************************************************************************************************************
     * Method
     ************************************************************************************************************** */

    public IssueCallback( Issue issue )
    {
        this.issue = issue;
    }

    /* **************************************************************************************************************
     * Method - Implements Callback<JsonNode>
     ************************************************************************************************************** */
	
	@Override
	public void completed( HttpResponse<JsonNode> response ) 
	{
		this.state = STATE_COMPLETE;
		this.response = response;
		
		MinecraftForge.EVENT_BUS.post( new IssueEvent.Callback( this ) );
	}

	@Override
	public void failed( UnirestException exception )
	{
		this.state = STATE_FAILED;
		this.exception = exception;
		
		MinecraftForge.EVENT_BUS.post( new IssueEvent.Callback( this ) );
	}

	@Override
	public void cancelled() 
	{
		this.state = STATE_CANCELLED;
		
		MinecraftForge.EVENT_BUS.post( new IssueEvent.Callback( this ) );
	}
}