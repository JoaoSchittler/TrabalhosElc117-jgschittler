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
import javafx.scene.image.ImageView;
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
		itemSource.setOnAction(e -> {
			System.out.println(urlstr);
			this.makeSourceChangerWindow();
		});
		itemExit.setOnAction(e -> Platform.exit());
		itemAbout.setOnAction(e -> control.showAbout());
	}
	private void setMenus() {
		menuHelp.getItems().add(itemAbout);
		menuFile.getItems().addAll(itemReload,itemSource,itemExit);
		menus.getMenus().addAll(menuFile,menuHelp);
	}
	private void setBaseTableColumns(TableView<TableData> table) {
		TableColumn<TableData,String> B = new TableColumn<TableData, String>("ANO");
		TableColumn<TableData,String> C = new TableColumn<TableData, String>("PROVA");
		TableColumn<TableData,String> D = new TableColumn<TableData, String>("TIPO QUESTÃO");
		TableColumn<TableData,String> E = new TableColumn<TableData, String>("ID QUESTÃO");
		TableColumn<TableData,String> F = new TableColumn<TableData, String>("OBJETO");
		TableColumn<TableData,String> I = new TableColumn<TableData, String>("ACERTOS CURSO");
		TableColumn<TableData,String> J = new TableColumn<TableData, String>("ACERTOS REGIAO");
		TableColumn<TableData,String> K = new TableColumn<TableData, String>("ACERTOS BRASIL");
		TableColumn<TableData,String> L = new TableColumn<TableData, String>("ACERTOS DIF");
		
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
		 String imgUrl = selectedIndexData.getUrlcrop();

		 //Tabela detalhada
		 TableView<TableData> detailedTable = new TableView<>();
		 this.setBaseTableColumns(detailedTable);
		 TableColumn<TableData,String> H = new TableColumn<>();
		 H.setCellValueFactory(new PropertyValueFactory<TableData,String>("gabarito"));
		 detailedTable.getColumns().add(H);
		 detailedTable.setItems(FXCollections.observableArrayList(selectedIndexData));
		 
		 VBox vb = new VBox();
		 vb.getChildren().add(detailedTable);

		 if(imgUrl.length()>2) {
			 ImageView imagem = new ImageView(imgUrl);
			 Button displayImageButton = new Button("Display Image");
			 displayImageButton.setOnAction(e -> {
			 	vb.getChildren().add(imagem);
			 	displayImageButton.setDisable(true);
			 });
			 vb.getChildren().add(displayImageButton);
		 }

		 Scene detailedScene = new Scene(vb,800,600);
         Stage newWindow = new Stage();
         newWindow.setTitle("Visualização Detalhada de Questão");
         newWindow.setScene(detailedScene);
         newWindow.initModality(Modality.WINDOW_MODAL);
         newWindow.initOwner(primarystage);
         newWindow.setX(primarystage.getX());
         newWindow.setY(primarystage.getY());
         newWindow.show();
		
	}
	@Override
	public void makeAboutWindow() {
		VBox vb = new VBox();
		Label aboutLabel = new Label("EnadeUFSMExplorer, feito por João Gabriel da Cunha Schittler");
		vb.getChildren().add(aboutLabel);
		
		Scene aboutScene = new Scene(vb,400,100);
		
		Stage newWindow = new Stage();
        newWindow.setTitle("About");
        newWindow.setScene(aboutScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(primarystage);
        newWindow.setX(primarystage.getX());
        newWindow.setY(primarystage.getY());
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
	            newWindow.close();
	         }
	      });

		vb.getChildren().addAll(txtfld, confirmButton);
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

		for(int i =1; i < data.size();i++) {
			columndata = FXCollections.observableArrayList(data.get(i));
			dadostabela.add(new TableData(columndata));
		}
		
		
	}
	@Override
	public void endApplication() {
		Platform.exit();
		
	}
	
	
}
