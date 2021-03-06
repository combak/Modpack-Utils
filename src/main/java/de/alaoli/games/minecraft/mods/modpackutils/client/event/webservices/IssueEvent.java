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

import de.alaoli.games.minecraft.mods.modpackutils.client.network.github.IssueCallback;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author DerOli82 <https://github.com/DerOli82>
 */
@Getter
@AllArgsConstructor
public abstract class IssueEvent extends Event
{
    /* **************************************************************************************************************
     * Attribute
     ************************************************************************************************************** */

    private final Issue issue;

    /* **************************************************************************************************************
     * Subclass - Send Event
     ************************************************************************************************************** */

    public static class Send extends IssueEvent
    {
        public Send( Issue issue )
        {
            super( issue );
        }
    }

    /* **************************************************************************************************************
     * Subclass - Callback Event
     ************************************************************************************************************** */

    @Getter
    public static class Callback extends IssueEvent
    {
        private final IssueCallback callback;

        public Callback( IssueCallback callback )
        {
            super( callback.getIssue() );

            this.callback = callback;
        }
    }
}