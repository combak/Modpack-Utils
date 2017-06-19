package de.alaoli.games.minecraft.mods.modpackutils.client.event.github;

import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class SendIssueEvent extends Event
{
	public final EntityPlayer player;
	public final Issue issue;
	
	public SendIssueEvent( EntityPlayer player, Issue issue )
	{
		this.player = player;
		this.issue = issue;
	}
}
