package de.alaoli.games.minecraft.mods.modpackutils.common.config;

import de.alaoli.games.minecraft.mods.lib.common.config.SectionGroup;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.webservices.GithubSection;

import java.util.Optional;

public class IntegrationSection extends SectionGroup
{
    /******************************************************************************************
     * Attribute
     ******************************************************************************************/

    /******************************************************************************************
     * Method
     ******************************************************************************************/

	public IntegrationSection()
	{
		this.addComponent( new GithubSection() );
	}
	
    /******************************************************************************************
     * Method - Implements Section
     ******************************************************************************************/

	@Override
	public Optional<String> getComponentName() 
	{
		return Optional.of( "integration" );
	}	

}
