package de.alaoli.games.minecraft.mods.modpackutils.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class ChangelogGui extends GuiScreen 
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/

    private ChangelogScrollingTextGui scrollingText;

	/******************************************************************************************
	 * Method 
	 ******************************************************************************************/
    
    FontRenderer getFontRenderer()
    {
        return fontRendererObj;
    }
    
	/******************************************************************************************
	 * Method - Implement GuiScreen 
	 ******************************************************************************************/

	@Override
	public void initGui() 
	{
		super.initGui();
	
		this.scrollingText = new ChangelogScrollingTextGui( this );
	}

	@Override
	public void drawScreen( int mouseX, int mouseY, float partialTicks )
	{
		String line;
        
		this.drawDefaultBackground();
		
		if( this.scrollingText != null )
		{
			line = "Changelog";
	        this.scrollingText.drawScreen( mouseX, mouseY, partialTicks );
		}
		else
		{
			line = "Changelog not found.";
		}
		GlStateManager.pushMatrix();
        {
            GlStateManager.translate( (width / 2) - this.fontRendererObj.getStringWidth( line ), 10, 0 );
            GlStateManager.scale( 1.5f, 1.5f, 1.5f );
            this.fontRendererObj.drawString( line , 0, 0, 0xffffff );
        }
        GlStateManager.popMatrix();	
	}
}
