package t7;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class GitHubAnalyzerGUI extends Application implements AppView {
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
	public void start(Stage primarystage){
		this.primarystage = primarystage;
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

		itemCommitAnalyzer.setOnAction(e -> this.fillTable());
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
		HBox hb  = new HBox();
		Label morecom = new Label();
		morecom.setText("Rep with most commits\n "+control.getRepwithMostCommits(table.getItems()));
		
		Label leastcom = new Label();
		leastcom.setText("Rep with least commits\n "+control.getRepwithLeastCommits(table.getItems()));
		
		Label newcom = new Label();
		newcom.setText("Rep with newest commit\n "+control.getRepwithNewestCommit(table.getItems()));
		
		Label oldcom = new Label();
		oldcom.setText("Rep with newest commit\n "+control.getRepwithOldestCommit(table.getItems()));
		
		hb.getChildren().addAll(morecom,leastcom,newcom,oldcom);
		vb.getChildren().addAll(menus,table,hb);
		return (new Scene(vb, 700, 500));
	}
	
	@Override
	public void enableFileChooser() {
		fileChooser.setTitle("Choose URL File");
		File file = fileChooser.showOpenDialog(primarystage);
		if(file != null)
			filetorequestfrom = file;
	}
	@Override
	public synchronized void fillTable() {
		TableColumn<DataEntry,String> colunaNome = new TableColumn<>("Repositorio");
		TableColumn<DataEntry,String> colunaNcommits = new TableColumn<>("NumCommits");
		TableColumn<DataEntry,String> colunaTammed = new TableColumn<>("TamMedioMsg");
		
		table.getColumns().addAll(colunaNome,colunaNcommits,colunaTammed);
		
		colunaNome.setCellValueFactory(new PropertyValueFactory<DataEntry,String>("repname"));	
		colunaNcommits.setCellValueFactory(new PropertyValueFactory<DataEntry,String>("numcommits"));	
		colunaTammed.setCellValueFactory(new PropertyValueFactory<DataEntry,String>("tammedmsg"));	
		
		ThreadData data = new ThreadData(new ArrayList<DataEntry>());
		
		requester = new RequesterThread(data,filetorequestfrom);

		System.out.println("Starting Requests");

		requester.start();
		data.startrequest();
		table.setItems(FXCollections.observableArrayList(data.repdata));
	}
	
}	
