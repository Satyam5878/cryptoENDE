package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class cryptoENDE extends Application{

/**
 * Fields related to scene and stage
 * */
	private Stage stage;
	private Scene scene;
	private VBox rootPane;
	private BorderPane borderPane;
	
	/**
	 * Fields of right of Border pane
	 * */
	private VBox bpRightRoot;
	private Accordion accordion;
	private TitledPane encryptTitledPane;
	private VBox encryptTitledPaneVBOX;
	private HBox eHBOX1, eHBOX2, eHBOX3, eHBOX4, eHBOX5, eHBOX6;
	private Label eFileAddrLbl,eDestinationAddrLbl,eLbl3;
	private TextField eFileAddrTxt,eDestinationAddrTxt;
	private Button eFileAddrBrowse, eDestinationAddrBrowse;
	/**
	 * Fields of top o BorderPane
	 */
	private MenuBar menubar;
	private Menu fileMenu,optionMenu,helpMenu;
	private MenuItem exitFileItem,newInstanceFileItem,settingOptionItem,aboutHelpItem;
	

	
	
	public static void main(String[] args){
		Application.launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		/*this.stage = stage;
		
		
		initializeStage();//goto label 1;
		
		
		stage.show();
*/		
		new NewInstance(stage);
		System.out.println("Running");
		
	}
	//label 1
	public void initializeStage(){
		/**
		 * RootPane Start
		 * */
		rootPane = new VBox();
			scene = new Scene(rootPane ,700,500);
			
			/**
			 * BorderPane Start
			 * */
			borderPane = new BorderPane();
				
				setTopPane(); // goto label 2
				setRightPane(); //// goto label 3
				setBottomPane();// goto label 4
				setLeftPane();// goto label 5
				setCenterPane();//goto label 10
			
		    /**
			 * BorderPane End
			 * */
			
			
		rootPane.getChildren().add(borderPane);
		/**
		 * RootPane End
		 * */	
			
		
		
		//this.stage.setResizable(false);
		this.stage.setScene(scene);
		
	}
	//label 2
	private void setTopPane(){
		menubar = new MenuBar();
			fileMenu = new Menu("_File");
				exitFileItem = new MenuItem("_Exit");
				exitFileItem.setAccelerator(new KeyCodeCombination(KeyCode.E,KeyCodeCombination.CONTROL_DOWN));
				exitFileItem.setOnAction(e->{
					System.out.println("Exiting...");
					Platform.exit();
				});
				newInstanceFileItem = new MenuItem("_New Instance");
				newInstanceFileItem.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCodeCombination.CONTROL_DOWN));
				newInstanceFileItem.setOnAction(e->{
					
				});
			fileMenu.getItems().addAll(newInstanceFileItem,exitFileItem);
			optionMenu = new Menu("_Option");
				settingOptionItem = new MenuItem("_Setting");
			optionMenu.getItems().addAll(settingOptionItem);
			helpMenu = new Menu("_Help");
				this.aboutHelpItem = new MenuItem("_About");
			helpMenu.getItems().addAll(aboutHelpItem);
		
		menubar.getMenus().addAll(fileMenu,optionMenu,helpMenu);
		borderPane.setTop(menubar);
		
	}
	private void setBottomPane(){
		
	}
	//label 3
	private void setRightPane(){
		bpRightRoot = new VBox();
		
			//BorderPane.setAlignment(bpRightRoot, Pos.CENTER);
			bpRightRoot.setPadding(new Insets(5));
			setAccordion(); // goto label 6
		borderPane.setRight(bpRightRoot);
		
		//borderPane.siz
	}
	private void setLeftPane(){
		
	}
	//label 10 
	private void setCenterPane(){
		borderPane.setCenter(null);
	}
	// label 6
	private void  setAccordion(){
		accordion = new Accordion();
			setEncryptionAccordion(); // goto label 7
			setDecryptionAccordion(); // got label 8
			setLogAccordion();// goto label 9
		//	accordion.setMaxSize(100, 100);
			accordion.setPrefWidth(400);
		bpRightRoot.getChildren().add(accordion);
	}
	// label 7
	private void setEncryptionAccordion(){
		encryptTitledPaneVBOX = new VBox();
			encryptTitledPaneVBOX.setSpacing(10);	
			eHBOX1 = new HBox();
			    HBox hbox = new HBox();
			    	//hbox.setAlignment(Pos.TOP_LEFT);
				    eFileAddrLbl = new Label("File :");
				    hbox.getChildren().add(eFileAddrLbl);
				eHBOX1.setSpacing(10);
				eHBOX1.setAlignment(Pos.CENTER_RIGHT);
				
					
					eFileAddrTxt = new TextField();
					this.eFileAddrTxt.setPrefWidth(220);
					this.eFileAddrTxt.setEditable(false);
					this.eFileAddrBrowse = new Button("_Browse");
					this.eFileAddrBrowse.setOnAction(e->{
						
						//File chooser code
						
					});
					
					
				eHBOX1.getChildren().addAll(hbox,this.eFileAddrTxt,this.eFileAddrBrowse);
			eHBOX2 = new HBox();
				eHBOX2.setSpacing(10);
				eHBOX2.setAlignment(Pos.CENTER_RIGHT);
					eDestinationAddrLbl = new Label("Destination :");
					eDestinationAddrTxt = new TextField();
					this.eDestinationAddrTxt.setPrefWidth(220);
					this.eDestinationAddrTxt.setEditable(false);
					this.eDestinationAddrBrowse = new Button("_Browse");
					this.eDestinationAddrBrowse.setOnAction(e->{
						
						
						//File chooser code
						
					});
				eHBOX2.getChildren().addAll(eDestinationAddrLbl,eDestinationAddrTxt,eDestinationAddrBrowse);
			eHBOX3 = new HBox();
				eHBOX3.setSpacing(10);
			eHBOX4 = new HBox();
				eHBOX4.setSpacing(10);
			eHBOX5 = new HBox();
				eHBOX5.setSpacing(10);
			eHBOX6 = new HBox();
			
				eHBOX6.setSpacing(10);
		encryptTitledPaneVBOX.getChildren().addAll(eHBOX1,eHBOX2,eHBOX3,eHBOX4,eHBOX5,eHBOX6);
	    encryptTitledPane = new TitledPane("Encryption",encryptTitledPaneVBOX);
		accordion.getPanes().add(encryptTitledPane);
		
	}
	//label  8
	private void setDecryptionAccordion(){
		
	}
	//label 9
	private void setLogAccordion(){
		
	}
	
	
}
// label value 10
