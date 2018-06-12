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
package de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices;

import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import de.alaoli.games.minecraft.mods.modpackutils.client.network.github.IssueCallback;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.Settings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class GithubEventHandler
{
	/* **************************************************************************************************************
	 * Attribute
	 ************************************************************************************************************** */

	private static GithubEventHandler INSTANCE;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */
	
	private GithubEventHandler() {}

	public static void register()
	{
		if( INSTANCE == null )
		{
			INSTANCE = new GithubEventHandler();
		}
		MinecraftForge.EVENT_BUS.register( INSTANCE );
	}

	/* **************************************************************************************************************
	 * Method - MinecraftForge Events
	 ************************************************************************************************************** */

	@SubscribeEvent
	public void onIssueEventCallback( IssueEvent.Callback event )
	{
		/*
		 * @TODO Handle events
		 */
		switch( event.getCallback().getState() )
		{
			case IssueCallback.STATE_COMPLETE :
				break;
				
			case IssueCallback.STATE_FAILED :
				break;
				
			case IssueCallback.STATE_CANCELLED :
				break;
		}
	}
	
	@SubscribeEvent
	public void onIssueEventSend( IssueEvent.Send event )
	{
		Gson gson = new GsonBuilder().create();

		Future<HttpResponse<JsonNode>> response = Unirest.post(Settings.webservices.url + "/issue" )
			.header( "Accept", "application/json" )
			.header( "Content-Type", "application/json" )
			.body( gson.toJson( event.getIssue() ) )
			.asJsonAsync( new IssueCallback( event.getIssue() ) );
	}
}