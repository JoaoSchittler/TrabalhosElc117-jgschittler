
package blockviewer;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class BlockViewer extends Application {
    private int position;
    private ArrayList<Image> blocks;
    private ImageView imagetoshow;
    private void setBlocks(){
        Image oak = new Image("carvalho.png");
        Image doak = new Image("carvalhoescuro.png");
        Image bir = new Image("eucalipto.png");
        Image spr = new Image("pinheiro.png");
        Image jun = new Image("selva.png");
        Image aca = new Image("acacia.png");
        blocks.add(oak);
        blocks.add(doak);
        blocks.add(bir);
        blocks.add(spr);
        blocks.add(jun);
        blocks.add(aca);
    }
    private void buttonclick(int way){
        updatePosition(way);
        imagetoshow = new ImageView(blocks.get(position));
    }
    private void updatePosition(int way){
        if(way == 1){
            if(position == blocks.size()-1)
                position = 0;
            else
                position++;
        }
        else{
            if(position == 0)
                position = blocks.size()-1;
            else
                position--;
        }      
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage){
        position = 0;
        Button btl = new Button("<");
        btl.setOnAction(event-> this.buttonclick(-1));     
       /* btl.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
           updatePosition(-1);
           imagetoshow = new ImageView(blocks.get(position));
        }});*/
        Button btr = new Button(">");
        btr.setOnAction(event-> this.buttonclick(1));
        /*btr.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
           updatePosition(1);
           imagetoshow = new ImageView(blocks.get(position));
        }});*/
        imagetoshow = new ImageView(blocks.get(position));
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(btl,imagetoshow,btr);
        stage.setScene(new Scene(hb,400,300));
        stage.show();
    }
}

