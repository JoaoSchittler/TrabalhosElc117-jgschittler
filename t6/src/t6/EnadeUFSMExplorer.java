package t6;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		itemReload.setOnAction(e ->control.fillTable(urlstr, "enade.csv"));
		itemExit.setOnAction(e -> Platform.exit());
		itemAbout.setOnAction(e -> System.out.println("Feito por eu")); // Temporary
	}
	private void setMenus() {
		menuHelp.getItems().add(itemAbout);
		menuFile.getItems().addAll(itemReload,itemSource,itemExit);
		menus.getMenus().addAll(menuFile,menuHelp);
	}
	private void setTable() {
		// Abrir o arquivo e pegar os valores usando csvreader
		control.fillTable(urlstr,"enade.csv");
		// Colocar colunas na tabela
		TableColumn<TableData,String> coluna1 = new TableColumn<TableData, String>();
		coluna1.setCellValueFactory(new PropertyValueFactory<TableData,String>("curso"));
		//Coloca dados na tabela
		table.setItems(dadostabela);
	}
	private void setStage(){
		this.primarystage.setScene(makeScene());
	    this.primarystage.setOnCloseRequest(e -> Platform.exit());
	    this.primarystage.show();
	}
	private Scene makeScene() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(menus,table);
		
		return new Scene(vb, 400, 300);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	@Override
	public void makeQuestionWindow() {
		 Label secondLabel = new Label("I'm a Label on new Window");
		 
         VBox vb2 = new VBox();
         //Colocar dados da linha da tabela escolhida
         vb2.getChildren().add(secondLabel);

         Scene secondScene = new Scene(vb2, 230, 100);

         // New window (Stage)
         Stage newWindow = new Stage();
         newWindow.setTitle("Visualização detalhada");
         newWindow.setScene(secondScene);

         // Specifies the modality for new window.
         newWindow.initModality(Modality.WINDOW_MODAL);

         // Specifies the owner Window (parent) for new window
         newWindow.initOwner(primarystage);

         // Set position of second window, related to primary window.
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
	
}
