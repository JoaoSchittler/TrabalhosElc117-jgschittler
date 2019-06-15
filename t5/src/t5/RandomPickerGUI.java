package t5;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class RandomPickerGUI extends Application implements View{
	private Stage stage;

	private AppController controller = null;
	
	private MenuBar menus = new MenuBar();
	
		private final Menu menuFile = new Menu("File");
			private MenuItem itemOpen = new MenuItem("Open");
				private final FileChooser fileChooser = new FileChooser();
			private MenuItem itemExit = new MenuItem("Exit");
				
		private final Menu menuHelp = new Menu("Help");
			private MenuItem itemAbout = new MenuItem("About");

	private TextArea txtarea = new TextArea();
	private Button bshuffle = new Button("Shuffle");
	private Button bnext = new Button("Next");
	
	private ArrayList<String> shuffledlist = null;
	
	public void start(final Stage stage) {
		this.stage = stage;
		controller = new AppController(this);
		itemOpen.setOnAction(e -> {controller.showFileChosser();});
		fileChooser.setTitle("Select txt file");
		bshuffle.setOnAction(e -> {controller.shuffle(txtarea.getText());});
		bnext.setOnAction(e -> { controller.putelelement(shuffledlist); });
		
		itemExit.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		        System.out.println("Saiu do programa");
		        Platform.exit();
		        stage.close();
		    }
		});
		txtarea.setOnKeyTyped(e -> {controller.checkifcanshuffle(txtarea.getText());});
		itemAbout.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	//Temporário
		        System.out.println("RandomPickerGUI, feito por João Gabriel Schittler");	        
		        fillTextArea("RandomPickerGUI, feito por João Gabriel Schittler");
		    }
		});
		
		bshuffle.setDisable(true);
		bnext.setDisable(true);
		
		menuFile.getItems().addAll(itemOpen,itemExit);
		menuHelp.getItems().add(itemAbout);
		menus.getMenus().addAll(menuFile,menuHelp);
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setAlignment(Pos.TOP_LEFT);
		
		HBox hb = new HBox();
		hb.setSpacing(30);
		hb.setAlignment(Pos.BOTTOM_CENTER);
		
		hb.getChildren().addAll(bshuffle,bnext);
		vb.getChildren().addAll(menus,txtarea,hb);
		
	    Scene scene = new Scene(vb, 400, 300);
	    stage.setScene(scene);
	    stage.setOnCloseRequest(e -> Platform.exit());
	    stage.show();
		
	}
	public static void main(String[]args) {
		Application.launch(args);
	}
	@Override
	public void enableFileChooser() {
		File file = fileChooser.showOpenDialog(stage);
		if(file != null)
			controller.showFile(file);

	}
	@Override
	public void fillTextArea(String s) {
		txtarea.clear();
		txtarea.appendText(s);
		bshuffle.setDisable(false);
		if(s.contains(System.getProperty("line.separator"))) {
			System.out.println("Contains barra n");
			bnext.setDisable(false);
		}
	}
	@Override
	public void setShuffledList(ArrayList<String> l) {
		shuffledlist = l;
	}
	@Override
	public void disableNextButton() {
		bnext.setDisable(true);
	}
	@Override
	public void enableNextButton() {
		bnext.setDisable(false);
	}
	@Override
	public void enableShuffle() {
		bshuffle.setDisable(false);
		
	}
	
}
