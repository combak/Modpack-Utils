package de.alaoli.games.minecraft.mods.modpackutils.client.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import de.alaoli.games.minecraft.mods.modpackutils.common.config.ChangelogSection;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

public class ChangelogScrollingTextGui extends GuiScrollingList
{
	/******************************************************************************************
	 * Attribute
	 ******************************************************************************************/

	private final ChangelogGui parent;
	private List<String> lines; 
	
	/******************************************************************************************
	 * Method
	 ******************************************************************************************/
	
	public ChangelogScrollingTextGui( ChangelogGui parent  )
	{
		//Minecraft client, int width, int height, int top, int bottom, int left, int entryHeight, int screenWidth, int screenHeight
		super( parent.mc,  parent.width-10,  parent.height,  30,  parent.height-5,  5,  10,  parent.width,  parent.height );

		this.parent = parent;
		
		try 
		{
			this.lines = Files.lines( Paths.get( ChangelogSection.file ) ).collect( Collectors.toList() );
		} 
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
	}
		
	/******************************************************************************************
	 * Method - Implement GuiScrollingList
	 ******************************************************************************************/
	
	@Override
	protected void drawBackground() {}

	@Override
	protected void elementClicked( int index, boolean doubleClick ) {}
	
	@Override
	protected boolean isSelected( int index ) { return false; }
	
	@Override
	protected int getSize() 
	{
		return (this.lines != null) ? this.lines.size() : 0;
	}
	
	@Override
	protected void drawSlot( int index, int right, int top, int height, Tessellator tess )
	{
		FontRenderer font = this.parent.getFontRenderer();
		String line = (this.lines != null ) ? this.lines.get( index ) : "";
		
		GlStateManager.pushMatrix();
        {
        	GlStateManager.scale( 0.9f, 0.9f, 0.9f );
            font.drawString( font.trimStringToWidth( line, listWidth - 10 ), this.left + 3, top + 2, 0xFFFFFF );
        }
        GlStateManager.popMatrix();		
	}
}
