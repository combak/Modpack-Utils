/* *************************************************************************************************************
 * Copyright (c) 2018 DerOli82 <https://github.com/DerOli82>
 *
 * This program is free software: you can redistribute it and/or toBuilder
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a toBuilder of the GNU Lesser General Public License
 * along with this program.  If not, see:
 *
 * https://www.gnu.org/licenses/lgpl-3.0.html
 ************************************************************************************************************* */
package de.alaoli.games.minecraft.mods.modpackutils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Mod( modid = Const.Mod.ID, name = Const.Mod.NAME, version = Const.Mod.VERSION, clientSideOnly = true )
public final class ModpackUtils
{
    /* **************************************************************************************************************
     * Attribute
	 ************************************************************************************************************** */

    @SidedProxy( clientSide = Const.Mod.PROXY_CLIENT, serverSide = Const.Mod.PROXY_COMMON )
    public static Proxy proxy;
    public static Logger logger;

	/* **************************************************************************************************************
	 * Method
	 ************************************************************************************************************** */

	public static  Optional<Logger> getLogger()
	{
		return Optional.of( logger );
	}

    /* **************************************************************************************************************
     * Method - Events
	 ************************************************************************************************************** */

	@EventHandler
	public void onFMLPreInitializationEvent(FMLPreInitializationEvent event )
	{
		logger = event.getModLog();
	}

    @EventHandler 
	public void onFMLInitializationEvent( FMLInitializationEvent event )
	{
		proxy.onFMLInitializationEvent( event );
	}
}