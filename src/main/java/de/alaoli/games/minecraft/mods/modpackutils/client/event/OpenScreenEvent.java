package de.alaoli.games.minecraft.mods.modpackutils.client.event;

import de.alaoli.games.minecraft.mods.lib.ui.Screen;
import net.minecraftforge.fml.common.eventhandler.Event;

public class OpenScreenEvent extends Event 
{
	public final Screen screen;
	
	public OpenScreenEvent( Screen screen )
	{
		super();
		this.screen = screen;
	}
}
