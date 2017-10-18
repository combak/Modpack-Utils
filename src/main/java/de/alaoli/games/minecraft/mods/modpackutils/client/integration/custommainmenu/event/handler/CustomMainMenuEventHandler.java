package de.alaoli.games.minecraft.mods.modpackutils.client.integration.custommainmenu.event.handler;

import de.alaoli.games.minecraft.mods.modpackutils.client.ui.ChangelogScreen;
import de.alaoli.games.minecraft.mods.modpackutils.client.ui.github.IssueScreen;
import de.alaoli.games.minecraft.mods.modpackutils.common.config.integration.CustomMainMenuSection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly( Side.CLIENT )
public class CustomMainMenuEventHandler
{
    /********************************************************************************
     * Attributes
     ********************************************************************************/

    private static class LazyHolder
    {
        public static final CustomMainMenuEventHandler INSTANCE = new CustomMainMenuEventHandler();
    }

    private GuiButton changelogButton;
    private GuiButton bugreportButton;

    /********************************************************************************
     * Method
     ********************************************************************************/

    private CustomMainMenuEventHandler() {}

    public static CustomMainMenuEventHandler getInstance()
    {
        return CustomMainMenuEventHandler.LazyHolder.INSTANCE;
    }

    public static void register()
    {
        MinecraftForge.EVENT_BUS.register( CustomMainMenuEventHandler.LazyHolder.INSTANCE );
    }

    /********************************************************************************
     * Method - MinecraftForge Events
     ********************************************************************************/

    @SubscribeEvent
    public void initEvent( GuiScreenEvent.InitGuiEvent event )
    {
        if( event.getGui() instanceof GuiMainMenu )
        {
            if( this.changelogButton == null )
            {
                this.changelogButton = new GuiButton( CustomMainMenuSection.changelogButtonId,0, 0,"");
            }

            if( this.bugreportButton == null )
            {
                this.bugreportButton = new GuiButton( CustomMainMenuSection.bugreportButtonId,0, 0,"");
            }
            event.getButtonList().add( this.changelogButton );
            event.getButtonList().add( this.bugreportButton );
        }
    }

    @SubscribeEvent
    public void actionPerformed(GuiScreenEvent.ActionPerformedEvent event )
    {
        if( event.getGui() instanceof GuiMainMenu )
        {
            if( event.getButton() == this.changelogButton )
            {
                Minecraft.getMinecraft().displayGuiScreen( new ChangelogScreen() );
            }
            else if( event.getButton() == this.bugreportButton )
            {
                Minecraft.getMinecraft().displayGuiScreen( new IssueScreen().setPlayer( Minecraft.getMinecraft().player) );
            }
        }
    }
}
