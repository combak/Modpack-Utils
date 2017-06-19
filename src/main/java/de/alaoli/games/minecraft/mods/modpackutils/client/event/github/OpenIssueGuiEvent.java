package de.alaoli.games.minecraft.mods.modpackutils.client.event.github;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class OpenIssueGuiEvent extends Event 
{
	public final EntityPlayer player;
	
	public OpenIssueGuiEvent( EntityPlayer player )
	{
		this.player = player;
	}
}
