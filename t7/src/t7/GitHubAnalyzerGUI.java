package t7;
import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GitHubAnalyzerGUI extends Application implements View{
	private Stage primarystage;
	private MenuBar menus = new MenuBar();
	AppController control = new AppController(this);
	private final FileChooser fileChooser = new FileChooser();
	private TableView<DataEntry> table = new TableView<>();
	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void start(Stage arg0){
		setMenus();
		setStage();
	}
	private void setMenus() {
		Menu menufile  = new Menu("File");
		Menu menutools = new Menu("Tools");
		Menu menuhelp  = new Menu("Help");
		MenuItem itemOpen = new MenuItem("Open");
		MenuItem itemExit = new MenuItem("Exit");
		MenuItem itemCommitAnalyzer = new MenuItem("Commit Analyzer");
		MenuItem itemAbout = new MenuItem("About");
		
		itemOpen.setOnAction(e -> control.enableFileChooser());
		itemExit.setOnAction(e -> Platform.exit());
		itemAbout.setOnAction(e -> System.out.println("GitHubAnalyzerGUI feito por 201810078"));
		
		menufile.getItems().addAll(itemOpen,itemExit);
		menutools.getItems().addAll(itemCommitAnalyzer);
		menuhelp.getItems().addAll(itemAbout);
		menus.getMenus().addAll(menufile,menutools,menuhelp);

	}
	private void setStage(){
		this.primarystage.setScene(makeScene());
	    this.primarystage.setOnCloseRequest(e -> Platform.exit());
	    this.primarystage.setTitle("GitHubAnalyzerGUI");
	    this.primarystage.show();
	}
	private Scene makeScene() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setAlignment(Pos.CENTER);
		
		return new Scene(vb, 400, 300);
	}
	
	@Override
	public void enableFileChooser() {
		fileChooser.setTitle("Choose URL File");
		File file = fileChooser.showOpenDialog(primarystage);
		if(file != null)
			control.showUrlsFromFile(file);
	}
	@Override
	public void fillTable(ArrayList<String> urlList) {
		TableColumn<DataEntry,String> colunaurl = new TableColumn<>();
		table.getColumns().add(colunaurl);
		colunaurl.setCellValueFactory(new PropertyValueFactory<DataEntry,String>("url"));	
		
		ArrayList<DataEntry> dataList = new ArrayList<>();
		for(String e:urlList)dataList.add(new DataEntry(e));
		
		table.setItems(FXCollections.observableArrayList(dataList));
	}
	
	public class DataEntry {
	     private SimpleStringProperty  url;
	     private DataEntry(String  url) {
	    	 this.url = new SimpleStringProperty(url);
	     }
	     public SimpleStringProperty  urlProperty() {return url;}
	     public String getUrl() { return url.get(); }
	     public void setUrl(String  url) { this.url.set(url); }
	 
	}
}	
/*
package t6;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnadeUFSMExplorer extends Application implements ViewFunctions{
	private Stage primarystage;
	
	AppController control = new AppController(this);
	
	private MenuBar menus = new MenuBar();
	
	private final Menu menuFile = new Menu("File");
		private MenuItem itemReload = new MenuItem("Reload");
		private MenuItem itemSource = new MenuItem("Source");
			private String urlstr = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";
		private MenuItem itemExit = new MenuItem("Exit");
			
	private final Menu menuHelp = new Menu("Help");
		private MenuItem itemAbout = new MenuItem("About");
	private TableView<TableData> table = new TableView<TableData>();
	private ObservableList<TableData> dadostabela = FXCollections.observableArrayList();

	public void start(Stage stage){
		this.primarystage = stage;
		setItemActions();
		setMenus();
		setTable();
		setStage();
	}
	private void setItemActions() {
		itemReload.setOnAction(e ->control.fillTable(urlstr));
		itemSource.setOnAction(e -> System.out.println(urlstr));
		itemExit.setOnAction(e -> Platform.exit());
		itemAbout.setOnAction(e -> control.showAbout());
	}
	private void setMenus() {
		menuHelp.getItems().add(itemAbout);
		menuFile.getItems().addAll(itemReload,itemSource,itemExit);
		menus.getMenus().addAll(menuFile,menuHelp);
	}
	private void setBaseTableColumns(TableView<TableData> table) {
		TableColumn<TableData,String> B = new TableColumn<TableData, String>();
		TableColumn<TableData,String> C = new TableColumn<TableData, String>();
		TableColumn<TableData,String> D = new TableColumn<TableData, String>();
		TableColumn<TableData,String> E = new TableColumn<TableData, String>();
		TableColumn<TableData,String> F = new TableColumn<TableData, String>();
		TableColumn<TableData,String> I = new TableColumn<TableData, String>();
		TableColumn<TableData,String> J = new TableColumn<TableData, String>();
		TableColumn<TableData,String> K = new TableColumn<TableData, String>();
		TableColumn<TableData,String> L = new TableColumn<TableData, String>();
		
		table.getColumns().addAll(B,C,D,E,F,I,J,K,L);
		
		B.setCellValueFactory(new PropertyValueFactory<TableData,String>("ano"));
		C.setCellValueFactory(new PropertyValueFactory<TableData,String>("prova"));
		D.setCellValueFactory(new PropertyValueFactory<TableData,String>("tipoquestao"));
		E.setCellValueFactory(new PropertyValueFactory<TableData,String>("idquestao"));
		F.setCellValueFactory(new PropertyValueFactory<TableData,String>("objeto"));
		I.setCellValueFactory(new PropertyValueFactory<TableData,String>("acertoscurso"));
		J.setCellValueFactory(new PropertyValueFactory<TableData,String>("acertosregiao"));
		K.setCellValueFactory(new PropertyValueFactory<TableData,String>("acertosbrasil"));
		L.setCellValueFactory(new PropertyValueFactory<TableData,String>("acertosdif"));
		
	}
	private void setTable() {
		// Abrir o arquivo e pegar os valores usando csvreader
		control.fillTable(urlstr);
		// Colocar colunas na tabela
		this.setBaseTableColumns(table);
		//Coloca dados na tabela
		table.setItems(dadostabela);
	}
	private void setStage(){
		this.primarystage.setScene(makeScene());
	    this.primarystage.setOnCloseRequest(e -> Platform.exit());
	    this.primarystage.setTitle("EnadeUFSMExplorer");
	    this.primarystage.show();
	}
	private Scene makeScene() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setAlignment(Pos.CENTER);
		
		//Colocar botões auxiliares em uma hbox
		Button btnselect = new Button("Select Line");
		btnselect.setOnAction(e -> control.displayDetailedLine(table.getSelectionModel().getSelectedIndex()));
		
		HBox hb = new HBox();
		hb.setSpacing(10);
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().add(btnselect);
		
		vb.getChildren().addAll(menus,table,hb);
		
		return new Scene(vb, 400, 300);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void makeDetailedTableWindow(int selectedIndex) {
		 TableData selectedIndexData;
		 if (selectedIndex >= 0 && selectedIndex < dadostabela.size()) {
	         
	         selectedIndexData = dadostabela.get(selectedIndex);
	      }
		 else {
			 return;
		 }
		 //Tabela da segunda Janela
		 TableView<TableData> detailedTable = new TableView<>();
		 
		 this.setBaseTableColumns(detailedTable);
		 TableColumn<TableData,String> H = new TableColumn<>();
		 TableColumn<TableData,String> R = new TableColumn<>();
		 
		 detailedTable.getColumns().addAll(H,R);
		 
		 H.setCellValueFactory(new PropertyValueFactory<TableData,String>("gabarito"));
		 R.setCellValueFactory(new PropertyValueFactory<TableData,String>("urlcrop"));
		 detailedTable.setItems(FXCollections.observableArrayList(selectedIndexData));
		 
		 
         VBox vb = new VBox();
         //Colocar dados da linha da tabela escolhida
         vb.getChildren().add(detailedTable);

         Scene detailedScene = new Scene(vb, 230, 100);

         Stage newWindow = new Stage();
         newWindow.setTitle("Visualização Detalhada de Questão");
         newWindow.setScene(detailedScene);
         newWindow.initModality(Modality.WINDOW_MODAL);
         newWindow.initOwner(primarystage);
         newWindow.setX(primarystage.getX() + 200);
         newWindow.setY(primarystage.getY() + 100);
         newWindow.show();
		
	}
	@Override
	public void makeAboutWindow() {
		VBox vb = new VBox();
		Label aboutLabel = new Label("EnadeUFSMExplorer, feito por João Gabriel da Cunha Schittler");
		vb.getChildren().add(aboutLabel);
		
		Scene aboutScene = new Scene(vb,100,100);
		
		Stage newWindow = new Stage();
        newWindow.setTitle("About");
        newWindow.setScene(aboutScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(primarystage);
        newWindow.setX(primarystage.getX() + 200);
        newWindow.setY(primarystage.getY() + 100);
        newWindow.show();
	}
	@Override
	public void makeSourceChangerWindow() {
		Stage newWindow = new Stage();
		VBox vb = new VBox();
		
		TextField txtfld = new TextField();
		Button confirmButton = new Button("Confirm Source");
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent event) {
	            urlstr = txtfld.getText();
	            control.fillTable(urlstr);
	            newWindow.close();
	         }
	      });

		Scene sourceScene = new Scene(vb,300,100);
        newWindow.setTitle("Change Source");
        newWindow.setScene(sourceScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(primarystage);
        newWindow.setX(primarystage.getX() + 200);
        newWindow.setY(primarystage.getY() + 100);
        newWindow.show();
		
	}
	@Override
	public void setTableList(ArrayList<String[]> data) {
		ObservableList<String> columndata;
		for(int i =0; i < data.size();i++) {
			columndata = FXCollections.observableArrayList(data.get(i));
			dadostabela.add(new TableData(columndata));
		}
		
		
	}
	@Override
	public void endApplication() {
		Platform.exit();
		
	}
	
	
}


*/
