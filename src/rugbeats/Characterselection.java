package rugbeats;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Characterselection extends GridPane {
    clothChanger cc;
    VBox p1_hat= new VBox();
    VBox p1_cloth= new VBox();
    HBox Character_p1=new HBox();

    VBox p2_hat= new VBox();
    VBox p2_cloth= new VBox();
    HBox Character_p2=new HBox();
    public Characterselection(){
        super();
        cc= new clothChanger();
        initializeUI();
    }
    public void initializeUI(){

        Text title= new Text("Create Character");
        title.setStyle("-fx-font-size:50;-fx-fill:red;");
        GridPane.setHalignment(title, HPos.CENTER);



        // set up arraylist for imageview
        ImageView p1_hatimg1= new ImageView(new Image("rugbeats/img/c1_hat.png"));
        ImageView p1_hatimg2= new ImageView(new Image("rugbeats/img/c2_hat.png"));
        ImageView p1_hatimg3= new ImageView(new Image("rugbeats/img/c3_hat.png"));
        ImageView p1_clothimg1= new ImageView(new Image("rugbeats/img/c1_cloth.png"));
        ImageView p1_clothimg2= new ImageView(new Image("rugbeats/img/c2_cloth.png"));
        ImageView p1_clothimg3= new ImageView(new Image("rugbeats/img/c3_cloth.png"));

        ImageView p2_hatimg1= new ImageView(new Image("rugbeats/img/c1_hat.png"));
        ImageView p2_hatimg2= new ImageView(new Image("rugbeats/img/c2_hat.png"));
        ImageView p2_hatimg3= new ImageView(new Image("rugbeats/img/c3_hat.png"));
        ImageView p2_clothimg1= new ImageView(new Image("rugbeats/img/c1_cloth.png"));
        ImageView p2_clothimg2= new ImageView(new Image("rugbeats/img/c2_cloth.png"));
        ImageView p2_clothimg3= new ImageView(new Image("rugbeats/img/c3_cloth.png"));
        Character_p1=cc.getCharacter();
        p1_hat=cc.getclothbox(p1_hatimg1,p1_hatimg2,p1_hatimg3);
        p1_cloth=cc.getclothbox(p1_clothimg1,p1_clothimg2,p1_clothimg3);

        Label p1= new Label("P1");
        this.setHgap(100);
        this.setPadding(new Insets(30,0,10,30));
        this.setStyle("-fx-base: #bfe7ff;");
        this.add(p1,0,0);
        this.add(p1_hat,1,0);
        this.add(p1_cloth,3,0);
        this.add(Character_p1,4,0);
        Button next= new Button("next");
        next.setStyle("-fx-base:#4C8FFB;" +
                "-fx-font-size:15;");
        next.setPrefSize(100,50);

        GridPane.setHalignment(next, HPos.CENTER);
        this.add(next,2,1);
        Character_p2=cc.getCharacter();
        p2_hat=cc.getclothbox(p2_hatimg1,p2_hatimg2,p2_hatimg3);
        p2_cloth=cc.getclothbox(p2_clothimg1,p2_clothimg2,p2_clothimg3);
        Label p2= new Label("P2");
        this.add(p2,0,2);
        this.add(p2_hat,1,2);
        this.add(p2_cloth,3,2);
        this.add(Character_p2,4,2);
        this.setHgrow(p2,Priority.ALWAYS);
        this.setHgrow(Character_p2,Priority.NEVER);
        this.setVgrow(next,Priority.ALWAYS);
        this.setHgrow(next,Priority.ALWAYS);
        GridPane.setHalignment(p1, HPos.CENTER);
        GridPane.setHalignment(p2, HPos.CENTER);
        GridPane.setHalignment(Character_p1, HPos.CENTER);
        GridPane.setHalignment(Character_p2, HPos.CENTER);
        this.setGridLinesVisible(true);

    }




}
