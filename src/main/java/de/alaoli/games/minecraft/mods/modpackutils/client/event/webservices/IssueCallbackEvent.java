package de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices;

import de.alaoli.games.minecraft.mods.modpackutils.client.network.github.IssueCallback;
import net.minecraftforge.fml.common.eventhandler.Event;

public class IssueCallbackEvent extends Event
{
	public final IssueCallback callback;
	
	public IssueCallbackEvent( IssueCallback callback )
	{
		this.callback = callback;
	}
}
