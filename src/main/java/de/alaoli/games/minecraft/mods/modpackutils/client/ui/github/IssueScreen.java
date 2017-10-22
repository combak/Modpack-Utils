package de.alaoli.games.minecraft.mods.modpackutils.client.ui.github;

import java.util.Optional;
import de.alaoli.games.minecraft.mods.lib.common.ui.Screen;
import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Background;
import de.alaoli.games.minecraft.mods.lib.common.ui.drawable.Border;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.Button;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.Label;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.TextArea;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.TextField;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.BoxStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.StateableStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.element.style.TextStyle;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.BorderPane;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.Pane;
import de.alaoli.games.minecraft.mods.lib.common.ui.layout.VBox;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Align;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Color;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.Colors;
import de.alaoli.games.minecraft.mods.lib.common.ui.util.State;
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
	/*
	private final Label titleText = new Label( "modpackutils:gui.bugreport.title" );
	private final LabeledTextField playerTextField = new LabeledTextField( "playerTextfield", this, "modpackutils:gui.bugreport.label.name" );
	private final LabeledTextField titleTextField =  new LabeledTextField( "titleTextField", this, "modpackutils:gui.bugreport.label.title" );
*/	
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
		int fieldWidth = this.width-145;
		int centerX = (this.width-20)/2;

		Color textColor = Colors.factory( 255, 255, 255 );
		Color borderColor = Colors.factory( 85, 85, 85 );

		TextStyle labelStyle = new TextStyle()
			.setColor( textColor );

		Background bgBlack = new Background( Colors.factory(0,0,0) );

		StateableStyle<BoxStyle> fieldBoxStyle = new StateableStyle<BoxStyle>()
			.add( State.NONE, new BoxStyle()
				.setBackground( bgBlack )
				.setBorder( new Border( borderColor ) ))
			.add( State.HOVERED, new BoxStyle()
				.setBackground( bgBlack )
				.setBorder( new Border( Colors.factory( 170, 170, 170) ) ))
			.add( State.FOCUSED, new BoxStyle()
					.setBackground( bgBlack )
					.setBorder( new Border( textColor ) ));

		StateableStyle<TextStyle> fieldTextStyle = new StateableStyle<TextStyle>()
			.add( State.NONE, labelStyle )
			.add( State.DISABLED, new TextStyle()
				.setColor( borderColor ) );


		this.titleText = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.title" ) )
			.setElementHeight( 15 )
			.setTextStyle( new TextStyle()
				.setColor( textColor )
				.setAlign( Align.CENTER ) );
		
		this.titlePane = new VBox()
			.setElementHeight( 15 )
			.addElement( this.titleText )
			.setBoxStyle( new BoxStyle()
				.setBorder( new Border( borderColor )
					.hide( true )
					.hideBottom( false ) )  );
		
		this.nameLabel = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.label.name" ) )
			.setElementBounds( 10,10, 100, 15 )
			.setTextStyle( labelStyle );

		this.name = new TextField()
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.name" ) )
			.setText( (this.player != null ) ? this.player.getDisplayNameString() : "" )
			.setElementBounds( 115,10, fieldWidth, 15 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle )
			.setDisable( this.player != null );

		this.titleLabel = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.label.title" ) )
			.setElementBounds( 10,30, 100, 15 )
			.setTextStyle( labelStyle );

		this.title = new TextField()
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.title" ) )
			.setElementBounds( 115,30, fieldWidth, 15 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle );

		this.descriptionLabel = new Label()
			.setText( I18n.format( "modpackutils:gui.bugreport.label.description" ) )
			.setElementBounds( 10,50, 100, 15 )
			.setTextStyle( labelStyle );

		this.description = new TextArea()
			.setPlaceholder( I18n.format( "modpackutils:gui.bugreport.placeholder.description" ) )
			.setElementBounds( 115,50, fieldWidth, 100 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle );

		this.sendButton = new Button()
			.setText( I18n.format( "modpackutils:gui.bugreport.button.send" ) )
			.setElementBounds( centerX-110,170, 100, 20 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle )
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
			.setElementBounds( centerX+10,170, 100, 20 )
			.setBoxStyle( fieldBoxStyle )
			.setTextStyle( fieldTextStyle )
			.onMouseClicked( button -> this.close() );

		this.formPane = new Pane()
			.addElement( this.nameLabel )
			.addElement( this.name )
			.addElement( this.titleLabel )
			.addElement( this.title )
			.addElement( this.descriptionLabel )
			.addElement( this.description )
			.addElement( this.sendButton )
			.addElement( this.cancelButton );
				
		this.borderPane = new BorderPane()
			.setBorder( Align.TOP, this.titlePane )
			.setBorder( Align.CENTER, this.formPane )
			.setBoxStyle( new BoxStyle()
				.setBackground( new Background( Colors.factory( 0.5f, 0, 0, 0 ) ) )
				.setBorder( new Border( borderColor ) )
				.setPadding( 10 ) );

		this.addListener( this.name )
			.addListener( this.title )
			.addListener( this.description )
			.addListener( this.sendButton )
			.addListener( this.cancelButton)
			.setLayout( this.borderPane );
	}
}
