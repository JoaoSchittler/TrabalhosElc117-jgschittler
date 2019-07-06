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
	private RequesterThread requester;
	private AppController control = new AppController(this);
	private File filetorequestfrom;
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
			filetorequestfrom = file;
	}
	@Override
	public synchronized void fillTable(ArrayList<String> urlList) {
		TableColumn<DataEntry,String> colunaurl = new TableColumn<>();
		table.getColumns().add(colunaurl);
		colunaurl.setCellValueFactory(new PropertyValueFactory<DataEntry,String>("numcommits"));	
		
		ArrayList<DataEntry> dataList = new ArrayList<DataEntry>();
		requester = new RequesterThread(dataList,filetorequestfrom);
		requester.start();
		try {this.wait();} catch (InterruptedException e) {}
		table.setItems(FXCollections.observableArrayList(dataList));
	}
	
}	
