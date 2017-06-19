package de.alaoli.games.minecraft.mods.modpackutils.client.gui.github;

import java.io.IOException;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.github.SendIssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.client.gui.TextAreaGui;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class IssueGui extends GuiScreen 
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/
	
	private static final int SEND_BUTTON = 10;
	private static final int CANCEL_BUTTON = 20;
	
	private static final String LABEL_NAME = "Name: ";
	private static final String LABEL_TITLE = "Title: ";
	private static final String LABEL_DESCRIPTION = "Description: ";
	
	private EntityPlayer player;
	private Issue pendingIssue;
	
	private GuiTextField nameTextField;
	private GuiTextField titleTextField;
	private TextAreaGui descriptionTextArea;
	
	private GuiButton sendButton;
	private GuiButton cancelButton;
	
	/******************************************************************************************
	 * Method 
	 ******************************************************************************************/
    
    FontRenderer getFontRenderer()
    {
        return fontRendererObj;       
    }
    
    public void setPlayer( EntityPlayer player )
    {
    	this.player = player;
    }
    
    public void setPendingIssue( Issue pendingIssue )
    {
    	this.pendingIssue = pendingIssue;
    }
    
    public String getPlayerName()
    {
    	return (this.player != null) ? this.player.getName() : "";
    }
    
    public void close()
    {
		this.mc.displayGuiScreen( null );

        if ( this.mc.currentScreen == null )
        {
            this.mc.setIngameFocus();
        }
    }
    
	/******************************************************************************************
	 * Method - Implement GuiScreen 
	 ******************************************************************************************/

	@Override
	public void initGui() 
	{
		super.initGui();
		
		int textFieldWidth = this.width - this.fontRendererObj.getStringWidth( LABEL_DESCRIPTION ) - 30;
		int textFieldHeight = 15;
		int textFieldPosX = this.width - textFieldWidth - 10;
		int textFieldPosY = 30;
		
		this.nameTextField = new GuiTextField( 1, this.fontRendererObj, textFieldPosX, textFieldPosY, textFieldWidth, textFieldHeight );
		this.nameTextField.setMaxStringLength(100);
        this.nameTextField.setEnabled( false );
        this.nameTextField.setText( this.getPlayerName() );
        
        textFieldPosY = textFieldPosY + textFieldHeight + 5;
        
        this.titleTextField = new GuiTextField( 2, this.fontRendererObj, textFieldPosX, textFieldPosY, textFieldWidth, textFieldHeight );
		this.titleTextField.setMaxStringLength(100);
		this.titleTextField.setFocused( true );
		
		textFieldPosY = textFieldPosY + textFieldHeight + 5;
		
        this.descriptionTextArea = new TextAreaGui( 3, this.fontRendererObj, textFieldPosX, textFieldPosY, textFieldWidth, 140 );
		this.descriptionTextArea.setMaxStringLength(5000);		
		
		textFieldPosY = textFieldPosY + 145;
		
		this.sendButton = new GuiButton( SEND_BUTTON, this.width/2-105, textFieldPosY, "Send" );
		this.sendButton.setWidth( 100 );
		
		this.cancelButton = new GuiButton( CANCEL_BUTTON, this.width/2+5, textFieldPosY, "Cancel" );
		this.cancelButton.setWidth( 100 );

		this.addButton( this.sendButton );
		this.addButton( this.cancelButton );
		
		//Restore text
		if( this.pendingIssue != null )
		{
			this.titleTextField.setText( this.pendingIssue.title );
			this.descriptionTextArea.setText( this.pendingIssue.description );
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException 
	{
		super.keyTyped( typedChar, keyCode );
		this.nameTextField.textboxKeyTyped( typedChar, keyCode );
		this.titleTextField.textboxKeyTyped( typedChar, keyCode );
		this.descriptionTextArea.textboxKeyTyped( typedChar, keyCode );
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException 
	{
		super.mouseClicked( mouseX, mouseY, mouseButton );
		this.nameTextField.mouseClicked( mouseX, mouseY, mouseButton );
		this.titleTextField.mouseClicked( mouseX, mouseY, mouseButton );
		this.descriptionTextArea.mouseClicked( mouseX, mouseY, mouseButton );
	}

	@Override
	protected void actionPerformed( GuiButton button ) throws IOException 
	{
		switch( button.id )
		{
			case SEND_BUTTON :
				Issue issue = new Issue(
					this.getPlayerName(),
					this.titleTextField.getText(),
					this.descriptionTextArea.getText()
				);
				MinecraftForge.EVENT_BUS.post( new SendIssueEvent( this.player, issue ) );
				this.close();
				break;
				
			case CANCEL_BUTTON :
			default:
				this.close();
				break;
		}
	}

	@Override
	public void updateScreen() 
	{
		super.updateScreen();
		this.nameTextField.updateCursorCounter();
		this.titleTextField.updateCursorCounter();
		this.descriptionTextArea.updateCursorCounter();
	}

	@Override
	public void drawScreen( int mouseX, int mouseY, float partialTicks )
	{
		String line = "Bug Report";
        
		this.drawDefaultBackground();
		
		GlStateManager.pushMatrix();
        {
            GlStateManager.translate( (width / 2) - this.fontRendererObj.getStringWidth( line ), 10, 0 );
            GlStateManager.scale( 1.5f, 1.5f, 1.5f );
            this.fontRendererObj.drawString( line , 0, 0, 0xffffff );
        }
        GlStateManager.popMatrix();

        int textPosY = 33;
        
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate( 10, textPosY, 0 );
            this.fontRendererObj.drawString( LABEL_NAME, 0, 0, 0xffffff );
        }
        GlStateManager.popMatrix();
        
        this.nameTextField.drawTextBox();
        textPosY = textPosY + 20;
        
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate( 10, textPosY, 0 );
            this.fontRendererObj.drawString( LABEL_TITLE, 0, 0, 0xffffff );
        }
        GlStateManager.popMatrix();
        
        this.titleTextField.drawTextBox();
        textPosY = textPosY + 20;
        
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate( 10, textPosY, 0 );
            this.fontRendererObj.drawString( LABEL_DESCRIPTION, 0, 0, 0xffffff );
        }
        GlStateManager.popMatrix();
        
        this.descriptionTextArea.drawTextBox();
        
        this.sendButton.drawButton( this.mc, mouseX, mouseY );
        this.cancelButton.drawButton( this.mc, mouseX, mouseY );
	}
}
