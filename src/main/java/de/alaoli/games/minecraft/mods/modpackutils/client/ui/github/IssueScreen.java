package de.alaoli.games.minecraft.mods.modpackutils.client.ui.github;

import java.util.Optional;
import de.alaoli.games.minecraft.mods.lib.ui.Screen;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.Background;
import de.alaoli.games.minecraft.mods.lib.ui.drawable.Border;
import de.alaoli.games.minecraft.mods.lib.ui.element.Button;
import de.alaoli.games.minecraft.mods.lib.ui.element.Label;
import de.alaoli.games.minecraft.mods.lib.ui.element.TextArea;
import de.alaoli.games.minecraft.mods.lib.ui.element.TextField;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.ui.layout.BorderPane;
import de.alaoli.games.minecraft.mods.lib.ui.layout.Pane;
import de.alaoli.games.minecraft.mods.lib.ui.layout.VBox;
import de.alaoli.games.minecraft.mods.lib.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.ui.util.Colors;
import de.alaoli.games.minecraft.mods.lib.ui.element.state.State;
import de.alaoli.games.minecraft.mods.modpackutils.client.event.webservices.SendIssueEvent;
import de.alaoli.games.minecraft.mods.modpackutils.common.data.github.Issue;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class IssueScreen extends Screen<IssueScreen> 
{
	/******************************************************************************************
	 * Attribute 
	 ******************************************************************************************/
	
	private EntityPlayer player;
	private Issue issue;

	private BorderPane borderPane;
	private VBox titlePane;
	private Label titleText;
	private Pane formPane;
	private Label nameLabel;
	private TextField name;

	private Label titleLabel;
	private TextField title;

	private Label descriptionLabel;
	private TextArea description;

	private Button sendButton;
	private Button cancelButton;

	/******************************************************************************************
	 * Method 
	 ******************************************************************************************/

	public Optional<EntityPlayer> getPlayer()
	{
		return Optional.ofNullable( this.player );
	}
	
	public IssueScreen setPlayer( EntityPlayer player )
	{
		this.player = player;
		
		return this;
	}
	
	public Optional<Issue> getIssue()
	{
		return Optional.ofNullable( this.issue );
	}
	
	public IssueScreen setIssue( Issue issue )
	{
		this.issue = issue;
		
		return this;
	}
	
	/******************************************************************************************
	 * Method - Implements Layout
	 ******************************************************************************************/

	@Override
	public void doLayout()
	{
		int fieldWidth = this.width-185;
		int centerX = (this.width-60)/2;

		TextStyle textStyle = new TextStyle().setColor( Colors.factory( Color.WHITE ) );
		BoxStyle paneStyle = new BoxStyle()
			.setBackground( new Background( Colors.factory( 0.5f, Color.BLACK ) ) )
			.setBorder( new Border( Colors.factory( Color.DARKGRAY ) ) );
		BoxStyle boxStyle = new BoxStyle()
			.setBackground( new Background( Colors.factory( Color.BLACK ) ) )
			.setBorder( new Border( Colors.factory( Color.DARKGRAY ) ) );

		StateableStyle<BoxStyle> fieldBoxStyle = new StateableStyle<BoxStyle>()
			.add( State.NONE, boxStyle )
			.add( State.HOVERED, boxStyle.extend().setBorder( new Border( Colors.factory( Color.GRAY ) ) ))
			.add( State.FOCUSED, boxStyle.extend().setBorder( new Border( Colors.factory( Color.WHITE ) ) ));

		StateableStyle<TextStyle> fieldTextStyle = new StateableStyle<TextStyle>()
			.add( State.NONE, textStyle.extend().setAlign( Align.LEFT ) )
			.add( State.DISABLED, textStyle.extend().setColor( Colors.factory( Color.DARKGRAY ) ) );

		StateableStyle<TextStyle> fieldTextAreaStyle = new StateableStyle<TextStyle>()
				.add( State.NONE, textStyle.extend().setAlign( Align.TOPLEFT) )
				.add( State.DISABLED, textStyle.extend().setColor( Colors.factory( Color.DARKGRAY ) ) );

		StateableStyle<TextStyle> buttonTextStyle = new StateableStyle<TextStyle>()
				.add( State.NONE, textStyle.extend().setAlign( Align.CENTER) );

		this.titleText = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.title" ) )
			.setSize( 100, 15 )
			.setTextStyle( textStyle.extend().setAlign( Align.CENTER ) );
		
		this.titlePane = new VBox()
			.setHeight( 15 )
			.addElement( this.titleText )
			.setBoxStyle( paneStyle.extend()
				.setAlign( Align.CENTER )
				.setBorder( new Border( Colors.factory( Color.DARKGRAY ) )
				.hide( true, true, true, false )));
		
		this.nameLabel = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.label.name" ) )
			.setBounds( 10,10, 100, 15 )
			.setTextStyle( textStyle );

		this.name = new TextField()
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.name" ) )
			.setText( (this.player != null ) ? this.player.getDisplayNameString() : "" )
			.setBounds( 115,10, fieldWidth, 15 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle );

		this.titleLabel = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.label.title" ) )
			.setBounds( 10,30, 100, 15 )
			.setTextStyle( textStyle );

		this.title = new TextField()
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.title" ) )
			.setBounds( 115,30, fieldWidth, 15 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle );

		this.descriptionLabel = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.label.description" ) )
			.setBounds( 10,50, 100, 15 )
			.setTextStyle( textStyle );

		this.description = new TextArea()
			.setMaxLines( 10 )
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.description" ) )
			.setBounds( 115,50, fieldWidth, 100 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextAreaStyle );

		this.sendButton = new Button()
			.setText( I18n.format( "modpackutils:gui.bugreport.button.send" ) )
			.setBounds( centerX-110,170, 100, 20 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( buttonTextStyle )
			.onMouseClicked( button -> {
				Issue issue = new Issue(
					this.name.getText().orElse( "" ),
					this.title.getText().orElse( ""),
					this.description.getText().orElse( "" )
				);
				MinecraftForge.EVENT_BUS.post( new SendIssueEvent( this.player, issue ) );
				this.close();
			});

		this.cancelButton = new Button()
			.setText( I18n.format( "modpackutils:gui.bugreport.button.cancel" ) )
			.setBounds( centerX+10,170, 100, 20 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( buttonTextStyle )
			.onMouseClicked( button -> this.close() );

		this.formPane = new Pane()
			.add( this.nameLabel )
			.add( this.name )
			.add( this.titleLabel )
			.add( this.title )
			.add( this.descriptionLabel )
			.add( this.description )
			.add( this.sendButton )
			.add( this.cancelButton );

		this.borderPane = new BorderPane()
			.setBorder( Align.TOP, this.titlePane )
			.setBorder( Align.CENTER, this.formPane )
			.setBoxStyle( paneStyle.extend()
				.setMargin( 10, 30, 30, 10 ) );

		this.addListener( this.name )
			.addListener( this.title )
			.addListener( this.description )
			.addListener( this.sendButton )
			.addListener( this.cancelButton)
			.setLayout( this.borderPane );
	}
}
