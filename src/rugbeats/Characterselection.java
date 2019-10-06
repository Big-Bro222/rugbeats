package rugbeats;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Characterselection extends GridPane {
    public Characterselection(){
        super();
        initializeUI();
    }
    public void initializeUI(){
        Text title= new Text("Create Character");
        title.setStyle("-fx-font-size:50;-fx-fill:red;");
        GridPane.setHalignment(title, HPos.CENTER);
        this.add(title,0,0);

        HBox p1_selectionBar = new HBox();
        ImageView p1_hatimg= new ImageView(new Image("rugbeats/img/c1_hat.png"));
        ImageView p1_faceimg= new ImageView(new Image("rugbeats/img/c1_face.png"));
        ImageView p1_clothimg= new ImageView(new Image("rugbeats/img/c1_cloth.png"));
        ImageView character1_img= new ImageView(new Image("rugbeats/img/c1.png"));
        int imgsize=100;
        p1_hatimg.setFitHeight(imgsize);
        p1_hatimg.setFitWidth(imgsize);
        Group g = new Group();

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                0.0, 40.0,
                100.0, 40.0,
                50.0, 0.0 });

        Group ggg = new Group();
        polygon.setRotate(180);

        g.getChildren().add(polygon);
        VBox p1_hat= new VBox(g,p1_hatimg,ggg);

        p1_faceimg.setFitHeight(imgsize);
        p1_faceimg.setFitWidth(imgsize);
        p1_clothimg.setFitHeight(imgsize);
        p1_clothimg.setFitWidth(imgsize);
        character1_img.setFitHeight(imgsize);
        character1_img.setFitWidth(imgsize);

        Label p1= new Label("P1");
        p1_selectionBar.setSpacing(50);
        p1_selectionBar.setPadding(new Insets(10,10,10,10));
        p1_selectionBar.setAlignment(Pos.CENTER);
        p1_selectionBar.getChildren().addAll(p1,p1_hat,p1_faceimg,p1_clothimg,character1_img);
        this.add(p1_selectionBar,0,1);

        Button next= new Button("next");
        GridPane.setHalignment(next, HPos.CENTER);
        this.add(next,0,2);

        HBox p2_selectionBar = new HBox();
        ImageView p2_hat= new ImageView(new Image("rugbeats/img/sun.jpg"));
        ImageView p2_face= new ImageView(new Image("rugbeats/img/sun.jpg"));
        ImageView p2_cloth= new ImageView(new Image("rugbeats/img/sun.jpg"));
        ImageView character2_img= new ImageView(new Image("rugbeats/img/c2.png"));

        p2_hat.setFitHeight(imgsize);
        p2_hat.setFitWidth(imgsize);
        p2_face.setFitHeight(imgsize);
        p2_face.setFitWidth(imgsize);
        p2_cloth.setFitHeight(imgsize);
        p2_cloth.setFitWidth(imgsize);
        character2_img.setFitHeight(imgsize);
        character2_img.setFitWidth(imgsize);

        Label p2= new Label("P2");
        p2_selectionBar.setSpacing(50);
        p2_selectionBar.setPadding(new Insets(10,10,10,10));
        p2_selectionBar.setAlignment(Pos.CENTER);
        p2_selectionBar.getChildren().addAll(p2,p2_hat,p2_face,p2_cloth,character2_img);
        this.add(p2_selectionBar,0,3);
        //FlowPane layout= new FlowPane(p1_selectionBar,next,p2_selectionBar);





    }
}
