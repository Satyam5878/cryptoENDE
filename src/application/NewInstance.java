package application;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class NewInstance {

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
		
		
		/**
		 * Fields of top of BorderPane
		 */
		private MenuBar menubar;
		private Menu fileMenu,optionMenu,helpMenu,settingOptionMenu ,encryptionAlgoMenu;
		private MenuItem exitFileItem,newInstanceFileItem,aboutHelpItem ;
		private RadioMenuItem AES_Item,DES_Item,DES3_Item,RSA_Item;
		private ToggleGroup algoToggle;
		
		/**
		 * Fields of Bottom BorderPane
		 */
		
		private HBox bpBottomRoot,hboxL,hboxR,hboxM;
		private Label leftLbl,middleLbl,rightLbl,leftLbl2,middleLbl2,rightLbl2;
		private Accordion accordion;
	//	private 
		/**
		 * Fields of Center of BorderPane
		 * */
		//Encryption Pane
			private VBox bpCenterRoot;
			private SplitPane splitPane;
			private TitledPane encryptTitledPane;
			private VBox encryptTitledPaneVBOX;
			private HBox eHBOX1, eHBOX2, eHBOX3, eHBOX4, eHBOX5, eHBOX6;
			private Label eFileAddrLbl,eDestinationAddrLbl,eLbl3,eNameAddrLbl,ePasswordAddrLbl;
			private TextField eFileAddrTxt,eDestinationAddrTxt,eNameAddrTxt;
			private PasswordField ePasswordAddrTxt;
			private Button eFileAddrBrowse, eDestinationAddrBrowse,eDoEncryption;
		
		//decrypt Pane 
			private TitledPane decryptTitledPane;
			private VBox decryptTitledPaneVBOX;
			private HBox dHBOX1,dHBOX2,dHBOX3,dHBOX4,dHBOX5,dHBOX6;
			private Label dFileAddrLbl,dDestinationAddrLbl,dNameAddrLbl, dPasswordAddrLbl;
			private TextField dFileAddrTxt,dDestinationAddrTxt,dNameAddrTxt,dPasswordAddrTxt;
			private Button dFileAddrBrowse,dDestinationAddrBrowse,dPasswordAddrBrowse,dDoDecryption;
		//log Pane 
			private TitledPane logTitledPane ;
			private VBox logTitledPaneVBOX;
			private TextArea logTxtArea;
			
		//TablePane
			private VBox tableRootPaneVBOX;
			private HBox tableHBOX;
			private Button tableReloadBtn,tableContentRemoveBtn;
			private Label tableReloadLbl,tableContentRemoveLbl;
		
		
	/**
	 * Field of Data 
	 * */ 
		//Encryption Pane 
		private StringProperty eFileAddrTxtString = new SimpleStringProperty("Choose File here.");
		private StringProperty eDestinationAddrTxtString = new SimpleStringProperty("Choose Destination Directory.");
		private StringProperty eNameAddrTxtString = new SimpleStringProperty("Optional New Name");
		private StringProperty ePasswordAddrTxtString = new SimpleStringProperty("");	
		//Decryption Pane
		private StringProperty dFileAddrTxtString = new SimpleStringProperty("Choose File here.");
		private StringProperty dDestinationAddrTxtString = new SimpleStringProperty("Choose Destination Directory.");
		private StringProperty dNameAddrTxtString= new SimpleStringProperty("");
		private ObjectProperty<File> dPasswordAddrTxtFile;
		private StringProperty dPasswordAddrTxtString = new SimpleStringProperty("");
		//log Pane 
		private StringProperty logTxtAreaString = new SimpleStringProperty("");
		
		//Bottom Border Pane String
		private StringProperty leftLblString = new SimpleStringProperty("None");
		private StringProperty middleLblString = new SimpleStringProperty("None");
		private StringProperty rightLblString = new SimpleStringProperty("None");
		// Algorithm used in MenuItem
		private StringProperty algoUsed = new SimpleStringProperty("AES");
		
		/**
		 * Fields related to Directory setup
		 * */
		private String DEFAULT_PATH = "F:\\Projects\\Eclipse\\TestFile\\cryptoENDE";
		private String ENCRYPTION = "Encrypted Files";
		private String DECRYPTION = "Decrypted Files";
		private String PASSWORD = "Password Files";
		private String FILE_RECORD = "Record Files";
		private String LOG_RECORD = "Log Record Files";
		/**
		 * Fields Related to Table View
		 * */
		private TableView<FileInfo> recordTable;
		
	public NewInstance(Stage stage){
		this.stage = stage;
		
		try {
			directorySetup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		initializeStage();//goto label 1;
		
		
		System.out.println("Running2");
		stage.setMinWidth(740);
		stage.setMinHeight(440);
		stage.show();
		
	}
	public void createDefaultDirectory() throws IOException {
			Files.createDirectories(Paths.get(this.DEFAULT_PATH));
		
		System.out.println("Created Default dir");
	}
	public void createEFileDirectory() throws IOException{
		Files.createDirectories(Paths.get(this.DEFAULT_PATH+"\\"+this.ENCRYPTION));
		
		System.out.println("Created Encryption dir");
	}
	public void createDFileDirectory() throws IOException{
		Files.createDirectories(Paths.get(this.DEFAULT_PATH+"\\"+this.DECRYPTION));
		
		System.out.println("Created Decryption dir");
	}
	public void createPFileDirectory() throws IOException{
		Files.createDirectories(Paths.get(this.DEFAULT_PATH+"\\"+this.PASSWORD));
		
		System.out.println("Created Password dir");
	}
	public void createFileRecordDirectory() throws IOException{
		Files.createDirectories(Paths.get(this.DEFAULT_PATH+"\\"+this.FILE_RECORD));
		
		System.out.println("Created File Record dir");
	}
	public void createLogRecordDirectory() throws IOException{
		Files.createDirectories(Paths.get(this.DEFAULT_PATH+"\\"+this.LOG_RECORD));
		
		System.out.println("Created File Record dir");
	}
	
	public void directorySetup() throws IOException{
		/*if(Files.isDirectory(Paths.get(this.DEFAULT_PATH))){
			if(!Files.isDirectory(Paths.get(this.DEFAULT_PATH+"\\"+this.ENCRYPTION))){
				try {
					Files.createDirectories(Paths.get(this.DEFAULT_PATH+"\\"+this.ENCRYPTION));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("Directory");
			}
		}
		else{
			try {
				Files.createDirectories(Paths.get(this.DEFAULT_PATH));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		if(Files.isDirectory(Paths.get(this.DEFAULT_PATH))){
			if(!Files.isDirectory(Paths.get(this.DEFAULT_PATH+"\\"+this.ENCRYPTION))){
				createEFileDirectory();
			}
			if(!Files.isDirectory(Paths.get(this.DEFAULT_PATH+"\\"+this.DECRYPTION))){
				createDFileDirectory();
			}
			if(!Files.isDirectory(Paths.get(this.DEFAULT_PATH+"\\"+this.PASSWORD))){
				createPFileDirectory();
			}
			if(!Files.isDirectory(Paths.get(this.DEFAULT_PATH+"\\"+this.FILE_RECORD))){
				createFileRecordDirectory();
			}
			if(!Files.isDirectory(Paths.get(this.DEFAULT_PATH+"\\"+this.LOG_RECORD))){
				createLogRecordDirectory();
			}
		}else{
			createDefaultDirectory();
			createEFileDirectory();
			createDFileDirectory();
			createPFileDirectory();
			createFileRecordDirectory();
			createLogRecordDirectory();
		}
	}
	
	public void initializeStage(){
		/**
		 * 
		 * RootPane Start
		 * */
		rootPane = new VBox();
			scene = new Scene(rootPane ,700,400);
			//rootPane.setPadding(new Insets(0,10,10,0));
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
					this.stage.close();
					//Platform.exit();
				});
				newInstanceFileItem = new MenuItem("_New Instance");
				newInstanceFileItem.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCodeCombination.CONTROL_DOWN));
				newInstanceFileItem.setOnAction(e->{
					Stage s = new Stage();
					Screen screen = Screen.getPrimary();
					Rectangle2D rectangle = screen.getVisualBounds();
					System.out.println(rectangle);
					s.setX(((this.stage.getX()+60)>rectangle.getWidth())?100:(this.stage.getX()+50));
					s.setY(((this.stage.getY()+60)>rectangle.getHeight())?100:(this.stage.getY()+50));
					new NewInstance(s);
				});
			fileMenu.getItems().addAll(newInstanceFileItem,exitFileItem);
			optionMenu = new Menu("_Option");
				settingOptionMenu = new Menu("_Setting");
					encryptionAlgoMenu = new Menu("_Algorithm");
						algoToggle = new ToggleGroup();
							
							AES_Item = new RadioMenuItem("AES");
							AES_Item.setText("AES");
							
							AES_Item.setSelected(true);
							AES_Item.setOnAction(e->{
								//Some code goes here
								System.out.println("AES Pressed");
								this.algoUsed.set("AES");
								
							});
							DES_Item = new RadioMenuItem("DES");
							DES_Item.setOnAction(e->{
								//Some code goes here
								System.out.println("DES Pressed");
								this.algoUsed.set("DES");
							});
							DES3_Item = new RadioMenuItem("3 DES");
							DES3_Item.setOnAction(e->{
								System.out.println("DES3_Item");
								this.algoUsed.set("DESede");
							});
							RSA_Item = new RadioMenuItem("RSA");
							RSA_Item.setOnAction(e->{
								System.out.println("RSA_Item");
								this.algoUsed.set("RSA");
							});
							
							this.algoUsed .addListener(e->{
								System.out.println(this.algoUsed.get());
							});
							
							/*if(algoToggle.getSelectedToggle() == null){
								System.out.println("NULL HO");
							}*/
							/*algoUsed.bind(new SimpleStringProperty(((RadioMenuItem)algoToggle.getSelectedToggle()).getText()));
							
							algoUsed.addListener(e->{
								System.out.println(this.algoUsed.get());
							});*/
						algoToggle.getToggles().addAll(AES_Item,DES_Item,DES3_Item,RSA_Item);
							
					encryptionAlgoMenu.getItems().addAll(AES_Item,DES_Item,DES3_Item,RSA_Item);
				settingOptionMenu.getItems().addAll(this.encryptionAlgoMenu);
			optionMenu.getItems().addAll(settingOptionMenu);
			helpMenu = new Menu("_Help");
				this.aboutHelpItem = new MenuItem("_About");
			helpMenu.getItems().addAll(aboutHelpItem);
		
		menubar.getMenus().addAll(fileMenu,optionMenu,helpMenu);
		borderPane.setTop(menubar);
		
	}
	private void setBottomPane(){
		System.out.println("Bottom Pane");
		bpBottomRoot = new HBox();
			bpBottomRoot.setSpacing(20);
			bpBottomRoot.maxWidthProperty().bind(scene.widthProperty().multiply(.90));
			bpBottomRoot.setStyle("-fx-background-color: White");
			hboxL = new HBox();
				hboxL.setSpacing(10);
				hboxL.setAlignment(Pos.CENTER_LEFT);
				//hboxL.minHeightProperty().bind(scene.heightProperty().multiply(.10));
				hboxL.minWidthProperty().bind(scene.widthProperty().multiply(.30));
					leftLbl = new Label("Current Status :");
					leftLbl2 = new Label();
					leftLbl2.textProperty().bindBidirectional(this.leftLblString);
					//leftLbl.minHeightProperty().bind(scene.heightProperty().multiply(.10));
				hboxL.getChildren().addAll(leftLbl,leftLbl2);
			hboxM = new HBox();
				hboxM.setSpacing(10);
				hboxM.setAlignment(Pos.CENTER_LEFT);
				hboxM.minWidthProperty().bind(scene.widthProperty().multiply(.30));
					middleLbl = new Label("Previous Action :");
					middleLbl2 = new Label();
					middleLbl2.textProperty().bind(this.middleLblString);
				hboxM.getChildren().addAll(middleLbl,middleLbl2);
			hboxR = new HBox();
				hboxR.setSpacing(10);
				hboxR.setAlignment(Pos.CENTER_LEFT);
				hboxR.minWidthProperty().bind(scene.widthProperty().multiply(.30));
					rightLbl = new Label("Staus");
					rightLbl2 = new Label();
					rightLbl2.textProperty().bind(this.rightLblString);
				hboxR.getChildren().addAll(rightLbl,rightLbl2);
		bpBottomRoot.getChildren().addAll(hboxL,hboxM,hboxR);
		borderPane.setBottom(bpBottomRoot);
	}
	//label 3
	private void setRightPane(){
		borderPane.setRight(null);
	/*	bpRightRoot = new VBox();
			bpRightRoot.setPrefHeight(500);
			//BorderPane.setAlignment(bpRightRoot, Pos.CENTER);
			bpRightRoot.setPadding(new Insets(5));
			setAccordion(); // goto label 6
		borderPane.setRight(bpRightRoot);*/
		
		//borderPane.siz
	}
	private void setLeftPane(){
		borderPane.setLeft(null);;
	}
	//label 10 
	private void setCenterPane(){
		bpCenterRoot = new VBox();
			bpCenterRoot.minHeightProperty().bind(scene.heightProperty().multiply(.85));
			bpCenterRoot.setPadding(new Insets(5));
			setSplitPane();
			//setAccordion();
		borderPane.setCenter(bpCenterRoot);
		//borderPane.setCenter(null);
	}
	// label 6
	private void setSplitPane(){
		splitPane = new SplitPane();
			splitPane.setPadding(new Insets(2));
			//splitPane.setDividerPositions(.50);
		//	splitPane.setStyle("-fx-border-insets: 5;");
			setTablePane();
			setAccordion();
		bpCenterRoot.getChildren().add(splitPane);
	}
	private void  setAccordion(){
		accordion = new Accordion();
			setEncryptionAccordion(); // goto label 7
			setDecryptionAccordion(); // got label 8
			setLogAccordion();// goto label 9
		//	accordion.setMaxSize(100, 100);
			//accordion.setPrefWidth(200);
		//accordion.se	
		splitPane.getItems().add(accordion);
	}
	
	@SuppressWarnings("unchecked")
	private void setTablePane(){
		tableRootPaneVBOX = new VBox();
		tableRootPaneVBOX.setStyle("-fx-border-insets: 5;");
			tableRootPaneVBOX.minHeightProperty().bind(scene.heightProperty().multiply(.85)/*.add(this.stage.getHeight()/10)*/);
			tableRootPaneVBOX.setSpacing(5);
			
			recordTable = new TableView<FileInfo>(FileInfoUtils.getRecordFiles());
			recordTable.minHeightProperty().bind(scene.heightProperty().multiply(.75));
			recordTable.setPlaceholder(new Label("No File Info Available. "));
			recordTable.setEventDispatcher(null);
			
			tableHBOX = new HBox();
				tableHBOX.setAlignment(Pos.BOTTOM_RIGHT);
				tableHBOX.setSpacing(10);
				tableHBOX.minWidthProperty().bind(scene.widthProperty().multiply(.40));
				
				tableReloadLbl = new Label("Load Records");
				tableReloadLbl.setMinWidth(75);
				//tableReloadLbl.fontProperty().bind(observable);
				tableReloadBtn = new Button("_Reload");
				tableReloadBtn.setMinWidth(70);
				tableReloadBtn.setOnAction(e->{
					//SomeCode goes here
					recordTable.getColumns().remove(0,recordTable.getColumns().size());
					
					recordTable.getColumns().addAll(FileInfoUtils.getOriginalNameCol(),
							 FileInfoUtils.getNewName(),
							 FileInfoUtils.getFileFormat(),
							 FileInfoUtils.getDestinationLocCol(),
						//	 FileInfoUtils.getDestinationLocCol(),
							 FileInfoUtils.getEncryptedOrDecrypted(),
							 FileInfoUtils.getAlgoUsed()
												);
				});	
				tableContentRemoveLbl = new Label("Remove Records");
			//	tableContentRemoveLbl.setMinWidth(70);
				tableContentRemoveBtn = new Button("Remove");
				tableContentRemoveBtn.setMinWidth(70);
				tableContentRemoveBtn.setOnAction(e->{
					recordTable.getColumns().remove(0,recordTable.getColumns().size());
				});
				/*Button Btn = new Button("Btn");
				Btn.setOnAction(e->{
					recordTable.getColumns().remove(0,recordTable.getColumns().size());
				});*/
			tableHBOX.getChildren().addAll(tableReloadLbl/*,Btn*/,tableReloadBtn,tableContentRemoveLbl,tableContentRemoveBtn);
			
			
		tableRootPaneVBOX.getChildren().addAll(tableHBOX,recordTable
				);
		//tableTitledPane = new TitledPane("",tableTitledPaneVBOX );
		//splitPane.setStyle("-fx-border-insets: 5;");
		splitPane.getItems().add(tableRootPaneVBOX);
		
	}
	// label 7
	private void setEncryptionAccordion(){
		encryptTitledPaneVBOX = new VBox();
			encryptTitledPaneVBOX.setSpacing(10);
			//encryptTitledPaneVBOX.setPadding(new Insets(0,10,0,0));
			
			eHBOX1 = new HBox();
				eHBOX1.setSpacing(10);
				eHBOX1.setAlignment(Pos.CENTER_LEFT);
				
					HBox hboxfirst = new HBox();
					hboxfirst.setAlignment(Pos.CENTER_LEFT);
					hboxfirst.setMinWidth(70);
						eFileAddrLbl = new Label("File :");
							//eFileAddrLbl.setMinWidth(70);
					hboxfirst.getChildren().add(eFileAddrLbl);
					eFileAddrTxt = new TextField();
					eFileAddrTxt.textProperty().bind(eFileAddrTxtString);
					//eFileAddrTxt.setPrefWidth(220);
					eFileAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.70));
					eFileAddrTxt.setMinWidth(150);
					eFileAddrTxt.setEditable(false);
					eFileAddrBrowse = new Button("  _Browse  ");
					eFileAddrBrowse.setMinWidth(70);
					eFileAddrBrowse.setOnAction(e->{
						//File chooser code
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("File To Encrypt");
						fileChooser.setInitialDirectory(new File(this.DEFAULT_PATH+"\\"+this.DECRYPTION));
						fileChooser.setInitialFileName("Encrypt.ENDE");
						
						File file = fileChooser.showOpenDialog(stage);
						if(file == null){
							System.out.println("NULL");
							return ;
						}
						eFileAddrTxtString.bind(new SimpleStringProperty(file.toPath().toString()));
						
						
						System.out.println(file.toPath().toString() + "  "+this.eFileAddrTxtString.get());
					});	
				eHBOX1.getChildren().addAll(hboxfirst,eFileAddrTxt,eFileAddrBrowse);
				
			eHBOX2 = new HBox();
				eHBOX2.setSpacing(10);
				eHBOX2.setAlignment(Pos.CENTER_LEFT);
					HBox hboxsecond = new HBox();
					hboxsecond.setAlignment(Pos.CENTER_LEFT);
					hboxsecond.setMinWidth(70);
						eDestinationAddrLbl = new Label("Destination :");
					hboxsecond.getChildren().add(eDestinationAddrLbl);
					eDestinationAddrLbl.setMinWidth(70);
					eDestinationAddrTxt = new TextField();
					eDestinationAddrTxt.textProperty().bind(eDestinationAddrTxtString);
					eDestinationAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.70));
					eDestinationAddrTxt.setMinWidth(150);
				//this.eDestinationAddrTxt.setPrefWidth(220);
					eDestinationAddrTxt.setEditable(false);
					this.eDestinationAddrBrowse = new Button("  B_rowse  ");
					eDestinationAddrBrowse.setMinWidth(70);
					this.eDestinationAddrBrowse.setOnAction(e->{
						//File chooser code
						DirectoryChooser dc = new DirectoryChooser();
						dc.setTitle("Destination Directory");
						dc.setInitialDirectory(new File(this.DEFAULT_PATH+"\\"+this.ENCRYPTION));
						File dir = dc.showDialog(stage);
						if(dir == null){
							System.out.println("NULL");
							return;
						}
						eDestinationAddrTxtString.bind(new SimpleStringProperty(dir.toPath().toString()));
						
						System.out.println(eDestinationAddrTxtString.get());
					});
				eHBOX2.getChildren().addAll(hboxsecond,eDestinationAddrTxt,eDestinationAddrBrowse);
			eHBOX3 = new HBox();
				eHBOX3.setSpacing(10);
				eHBOX3.setAlignment(Pos.CENTER_LEFT);
					HBox hboxthird = new HBox();
					hboxthird.setAlignment(Pos.CENTER_LEFT);
					hboxthird.setMinWidth(70);
						eNameAddrLbl = new Label("New Name :");
					hboxthird.getChildren().add(eNameAddrLbl);
				eNameAddrLbl.setMinWidth(70);
				eNameAddrTxt = new TextField("Optional New Name");
				eNameAddrTxtString.bind(eNameAddrTxt.textProperty());
						//Testing Binding of Name TxtField and Name String
								/*eNameAddrTxtString.addListener(e->{
									System.out.println(eNameAddrTxtString.get());
				
								});
				*/
				/*this.eFileAddrTxtString.addListener(e->{
					String[] a = this.eFileAddrTxtString.get().replaceAll("\\", "/").split("/");
					eNameAddrTxt.textProperty().bind(new SimpleStringProperty(a[a.length-1]));
				});*/
				//a = this.eFileAddrTxtString.get().split("/");
				//eNameAddrTxt.textProperty().bind(new SimpleStringProperty(a[a.length-1]));
				eNameAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.85));
				eNameAddrTxt.setMinWidth(150);
			eHBOX3.getChildren().addAll(hboxthird ,eNameAddrTxt);
				
			eHBOX4 = new HBox();
				eHBOX4.setSpacing(10);
				eHBOX4.setAlignment(Pos.CENTER_LEFT);
					HBox hboxfourth = new HBox();
					hboxfourth.setAlignment(Pos.CENTER_LEFT);
					hboxfourth.setMinWidth(70);
						ePasswordAddrLbl = new Label("Password :");
					hboxfourth.getChildren().add(ePasswordAddrLbl);
					ePasswordAddrLbl.setMinWidth(70);
					ePasswordAddrTxt = new PasswordField();
					ePasswordAddrTxtString.bind(ePasswordAddrTxt.textProperty());
							//Testing Binding of Password TxtField and password String
						/*ePasswordAddrTxtString.addListener(e->{
							
							System.out.println(ePasswordAddrTxtString.get());
						});*/
					ePasswordAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.85));
					ePasswordAddrTxt.setMinWidth(150);
				
					eHBOX4.getChildren().addAll(hboxfourth,ePasswordAddrTxt);
			eHBOX5 = new HBox();
				eHBOX5.setSpacing(10);
				eHBOX5.setAlignment(Pos.CENTER_RIGHT);
					eDoEncryption = new Button("_Encrypt");
					eDoEncryption.setMinWidth(70);
					eDoEncryption.setOnAction(e->{
						//Some code goes here
						//ENDEEngine.encrypt(this);
						if(this.eFileAddrTxtString.get() == "Choose File here."){
							generateMessageDialog("File Address can Not be left as\n        \"Choose File here.\"",this.stage);
						    return;
						}
						if(this.eNameAddrTxtString.get().equals("Optional New Name")){
							generateMessageDialog("If You do not supply new name\n   original name will be used",stage);
						}
						if(this.ePasswordAddrTxtString.get().isEmpty()){
							generateMessageDialog("  If you do not supply password\nsome Random password will used",stage);
						}
						/*else{
							System.out.println(this.ePasswordAddrTxtString.get()+"Aman");
						}
						System.out.println(this.ePasswordAddrTxtString.get()+"Aman");
						*/
					
						/*
*/
						ENDEEngine.encrypt(
								this.eFileAddrTxtString.get(),
								((this.eDestinationAddrTxtString.get().equals("Choose Destination Directory."))?this.DEFAULT_PATH+"\\"+this.ENCRYPTION:this.dDestinationAddrTxtString.get()),
								((this.eNameAddrTxtString.get().equals("Optional New Name"))?this.getNameOfFile(this.eFileAddrTxtString.get()):this.eNameAddrTxtString.get()),
								this.ePasswordAddrTxtString.get(),
								this.algoUsed.get(),
								
								logTxtAreaString,
								leftLblString,
								 middleLblString,
								 rightLblString
								);
						
					});
				eHBOX5.getChildren().add(eDoEncryption);
			eHBOX6 = new HBox();
				eHBOX6.setSpacing(10);
		encryptTitledPaneVBOX.getChildren().addAll(eHBOX1,eHBOX2,eHBOX3,eHBOX4,eHBOX5,eHBOX6);
	    encryptTitledPane = new TitledPane("Encryption",encryptTitledPaneVBOX);
	    encryptTitledPane.setExpanded(true);
	    accordion.setExpandedPane(encryptTitledPane);
		accordion.getPanes().add(encryptTitledPane);
		
	}
	//label  8
	private void setDecryptionAccordion(){
		decryptTitledPaneVBOX = new VBox();
		decryptTitledPaneVBOX.setSpacing(10);	
		dHBOX1 = new HBox();
			dHBOX1.setSpacing(10);
			dHBOX1.setAlignment(Pos.CENTER_LEFT);
				HBox hboxfirst = new HBox();
				hboxfirst.setAlignment(Pos.CENTER_LEFT);
				hboxfirst.setMinWidth(70);
					dFileAddrLbl = new Label("File :");
						//eFileAddrLbl.setMinWidth(70);
				hboxfirst.getChildren().add(dFileAddrLbl);
				dFileAddrTxt = new TextField();
				//eFileAddrTxt.setPrefWidth(220);
				
				dFileAddrTxt.prefWidthProperty().bind(splitPane.widthProperty()
						.multiply(.70));
				dFileAddrTxt.setMinWidth(150);
				dFileAddrTxt.textProperty().bind(this.dFileAddrTxtString);
				
				dFileAddrTxt.setEditable(false);
				dFileAddrBrowse = new Button("  Br_owse  ");
				dFileAddrBrowse.setMinWidth(70);
				dFileAddrBrowse.setOnAction(e->{
					//File chooser code
					
					FileChooser fc = new FileChooser();
					fc.setTitle("File To Decrypt");
					fc.setInitialDirectory(new File(this.DEFAULT_PATH+"\\"+this.ENCRYPTION));
					
					fc.getExtensionFilters().add(new ExtensionFilter("Encrypted Files","*.ENDE","*.ENDE"));
					File file  = fc.showOpenDialog(stage);
					if(file == null){
						System.out.println("NULL");
						return ;
					}
					System.out.println(file.toPath().toString());
					dFileAddrTxtString.bind(new SimpleStringProperty(file.toPath().toString()));
					System.out.println(dFileAddrTxtString.get());
				});	
			dHBOX1.getChildren().addAll(hboxfirst,dFileAddrTxt,dFileAddrBrowse);
			
		dHBOX2 = new HBox();
			dHBOX2.setSpacing(10);
			dHBOX2.setAlignment(Pos.CENTER_LEFT);
				HBox hboxsecond = new HBox();
				hboxsecond.setAlignment(Pos.CENTER_LEFT);
				hboxsecond.setMinWidth(70);
					dDestinationAddrLbl = new Label("Destination :");
				hboxsecond.getChildren().add(dDestinationAddrLbl);
				dDestinationAddrLbl.setMinWidth(70);
				dDestinationAddrTxt = new TextField();
				dDestinationAddrTxt.textProperty().bind(dDestinationAddrTxtString);
				dDestinationAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.70));
				dDestinationAddrTxt.setMinWidth(150);
				dDestinationAddrTxt.setEditable(false);
				dDestinationAddrBrowse = new Button("  Bro_wse  ");
				dDestinationAddrBrowse.setMinWidth(70);
				dDestinationAddrBrowse.setOnAction(e->{
					//File chooser code
					DirectoryChooser dc = new DirectoryChooser();
					dc.setTitle("Destination Directory");
					dc.setInitialDirectory(new File(this.DEFAULT_PATH+"\\"+this.DECRYPTION));
					File dir = dc.showDialog(stage);
					if( dir == null){
						System.out.println("NULL");
						return;
					}
					dDestinationAddrTxtString.bind(new SimpleStringProperty(dir.toPath().toString()));
					
					System.out.println(this.dDestinationAddrTxtString.get());
				});
			dHBOX2.getChildren().addAll(hboxsecond,dDestinationAddrTxt,dDestinationAddrBrowse);
		dHBOX3 = new HBox();
			dHBOX3.setSpacing(10);
			dHBOX3.setAlignment(Pos.CENTER_LEFT);
				HBox hboxthird = new HBox();
				hboxthird.setAlignment(Pos.CENTER_LEFT);
				hboxthird.setMinWidth(70);
					dNameAddrLbl = new Label("New Name :");
				hboxthird.getChildren().add(dNameAddrLbl);
			dNameAddrLbl.setMinWidth(70);
			dNameAddrTxt = new TextField("Optional New Name");
			dNameAddrTxtString.bind(dNameAddrTxt.textProperty());
					 //Testing binding of name Text field and name String
					/*dNameAddrTxtString.addListener(e->{
						System.out.println(dNameAddrTxtString.get());
					});*/
			dNameAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.815));
			dNameAddrTxt.setMinWidth(150);
		dHBOX3.getChildren().addAll(hboxthird ,dNameAddrTxt);
			
		dHBOX4 = new HBox();
			dHBOX4.setSpacing(10);
			dHBOX4.setAlignment(Pos.CENTER_LEFT);
				HBox hboxfourth = new HBox();
				hboxfourth.setAlignment(Pos.CENTER_LEFT);
				hboxfourth.setMinWidth(70);
					dPasswordAddrLbl = new Label("Password :");
				hboxfourth.getChildren().add(dPasswordAddrLbl);
				dPasswordAddrLbl.setMinWidth(70);
				dPasswordAddrTxt = new TextField();
				dPasswordAddrTxt.prefWidthProperty().bind(splitPane.widthProperty().multiply(.70));
				dPasswordAddrTxt.setMinWidth(150);
				dPasswordAddrTxt.textProperty().bind(dPasswordAddrTxtString);
				dPasswordAddrBrowse = new Button("  Brow_se  ");
				dPasswordAddrBrowse.setMinWidth(70);
				dPasswordAddrBrowse.setOnAction(e->{
					//File chooser code
					DirectoryChooser dc = new DirectoryChooser();
					dc.setInitialDirectory(new File(this.DEFAULT_PATH+"\\"+this.PASSWORD));
					dc.setTitle("Password Directory Chooser");
					
					File dir =  dc.showDialog(stage);
					if(dir == null){
						System.out.println("NULL");
						dPasswordAddrTxt.textProperty().bind(new SimpleStringProperty("Password Directory Empty"));
						return;
					}
					dPasswordAddrTxtFile = new SimpleObjectProperty<File>(dir);
					dPasswordAddrTxtString.bind(new SimpleStringProperty(dir.toPath().toString()));
					
					System.out.println(this.dPasswordAddrTxtString.get());
					
				});
				dHBOX4.getChildren().addAll(hboxfourth,dPasswordAddrTxt,dPasswordAddrBrowse);
		dHBOX5 = new HBox();
			dHBOX5.setSpacing(10);
			dHBOX5.setAlignment(Pos.CENTER_RIGHT);
			this.dDoDecryption = new Button("_Decrypt");
				this.dDoDecryption.setMinWidth(70);
				this.dDoDecryption.setOnAction(e->{
					//Some code goes here.
					//ENDEEngine.decrypt(this);
					if(this.dFileAddrTxtString.get() == "Choose File here."){
						generateMessageDialog("File Address can Not be left as\n        \"Choose File here.\"",this.stage);
						//It is Correct Code but for debugging 
						return;
					}
					/*else*/ if(this.dNameAddrTxtString.get() != null){
						generateMessageDialog("If You do not supply new name\n   original name will be used",stage);
					}
					/*else*/ if(this.dPasswordAddrTxtString.get().isEmpty()){
						generateMessageDialog("        Password is Mandatory      \nNo file will be Decrypted without it",stage);
						return;
					}
					/*else{
						System.out.println(this.ePasswordAddrTxtString.get()+"Aman");
					}
					System.out.println(this.ePasswordAddrTxtString.get()+"Aman");
					*/
				
					ENDEEngine.decrypt(
							this.dFileAddrTxtString.get(),
							((this.dDestinationAddrTxtString.get().equals("Choose Destination Directory."))?this.DEFAULT_PATH+"\\"+this.DECRYPTION:this.dDestinationAddrTxtString.get()),
							((this.dNameAddrTxtString.get() != null)?this.dNameAddrTxtString.get():this.getNameOfFile(this.dFileAddrTxtString.get())),
							this.dPasswordAddrTxtString.get()
							
							
							);
				});
			dHBOX5.getChildren().add(this.dDoDecryption);
		dHBOX6 = new HBox();
			dHBOX6.setSpacing(10);
	decryptTitledPaneVBOX.getChildren().addAll(dHBOX1,dHBOX2,dHBOX3,dHBOX4,dHBOX5,dHBOX6);
    decryptTitledPane = new TitledPane("Decryption",decryptTitledPaneVBOX);
    decryptTitledPane.setExpanded(true);
	accordion.getPanes().add(decryptTitledPane);

	}
	//label 9
	private void setLogAccordion(){
		logTitledPaneVBOX = new VBox();
			logTitledPaneVBOX.setSpacing(10);
			Label l = new Label("Log Area");
			logTxtArea = new TextArea();
			logTxtArea.setEditable(false);
		
		
		logTitledPaneVBOX.getChildren().addAll(l,logTxtArea);
		
		logTitledPane = new TitledPane("Log File",logTitledPaneVBOX);
		accordion.getPanes().add(logTitledPane);
	}
	

	public String getNameOfFile(String fileAddr){
		String fileNameArray[] = fileAddr.split("\\\\");
		String fileName = fileNameArray[fileNameArray.length-1];
		return fileName;
		
	}
	public void generateMessageDialog(String s,Stage stage){
		Stage msgDialog = new Stage();
		msgDialog.setTitle("Message Dialog");
		
		VBox root = new VBox();
			root.setStyle("-fx-background-color:White;"
					/*+     "-fx-background-insets: 5 10 10 10; "*/);
			HBox hbox1 = new HBox();
				hbox1.setAlignment(Pos.CENTER);
				
				Label msgLbl = new Label(s);
				msgLbl.setStyle("-fx-text-fill:red;"
						      + "-fx-font-weight:bold;");
				
			hbox1.getChildren().add(msgLbl);
			Label msgLbl1 = new Label();
			Label msgLbl2 = new Label();
			Label msgLbl3 = new Label();
			HBox hbox2 = new HBox();
				hbox2.setAlignment(Pos.CENTER_RIGHT);
				//hbox2.setSpacing(10);
				hbox2.setStyle("-fx-padding:5 ");
				hbox2.setStyle("-fx-background-color:White;"
						+     "-fx-background-insets: 5 10 10 10; ");
				
				Button closeMsgDialogBtn = new Button("    _OK    ");
				hbox2.setStyle("-fx-padding:5 ");
				closeMsgDialogBtn.setOnAction(e->{
					msgDialog.close();
				});
			hbox2.getChildren().addAll(closeMsgDialogBtn);
		root.getChildren().addAll(msgLbl1,msgLbl2,hbox1,msgLbl3,hbox2);
		
		Scene scene = new Scene(root,210,110);
		
		msgDialog.setOnShowing(e->{
			Toolkit.getDefaultToolkit().beep();
		});
		
		msgDialog.setScene(scene);
		msgDialog.setResizable(false);
		msgDialog.initOwner(stage);
		msgDialog.initModality(Modality.WINDOW_MODAL);
		
		msgDialog.showAndWait();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
