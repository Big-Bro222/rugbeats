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
    VBox p1_hat= new VBox();
    VBox p1_cloth= new VBox();
    HBox Character_p1=new HBox();

    VBox p2_hat= new VBox();
    VBox p2_cloth= new VBox();
    HBox Character_p2=new HBox();

    private CharacterSelectionController CScontroller;
    private CharacterSelectionModel Csmodel;

    public Characterselection(){
        Csmodel=new CharacterSelectionModel();
        CScontroller= new CharacterSelectionController(Csmodel);
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
        p1_hat=new clothChanger(p1_hatimg1,p1_hatimg2,p1_hatimg3);
        p1_cloth=new clothChanger(p1_clothimg1,p1_clothimg2,p1_clothimg3);
        Character_p1=new Character_layout();
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
        next.setOnMouseClicked(e->{
            GLOBAL.fxinstance.nextScene();
        });
        GridPane.setHalignment(next, HPos.CENTER);
        this.add(next,2,1);

        p2_hat=new clothChanger(p2_hatimg1,p2_hatimg2,p2_hatimg3);
        p2_cloth=new clothChanger(p2_clothimg1,p2_clothimg2,p2_clothimg3);
        Character_p2=new Character_layout();
        Label p2= new Label("P2");
        this.add(p2,0,2);
        this.add(p2_hat,1,2);
        this.add(p2_cloth,3,2);
        this.add(Character_p2,4,2);

        CScontroller.setSelectionBorderView((clothChanger) p1_hat);
        CScontroller.setSelectionBorderView((clothChanger)p2_hat);

        setOnKeyPressed(event -> {

            if (event.getCode()==KeyCode.A){
                CScontroller.setAppearenceModep1();
            }
            if (event.getCode()==KeyCode.D){
                CScontroller.setAppearenceModep1();
            }

            if(Csmodel.getAppearenceModep1()==0){
                CScontroller.cancelSelectionBorderView((clothChanger)p1_cloth);
                keyDown(event,(clothChanger)p1_hat);
                CScontroller.setSelectionBorderView((clothChanger) p1_hat);
            }
            else{
                CScontroller.cancelSelectionBorderView((clothChanger)p1_hat);
                keyDown(event,(clothChanger)p1_cloth);
                CScontroller.setSelectionBorderView((clothChanger) p1_cloth);
            }




        });

        setOnKeyReleased(event -> {
            switch (Csmodel.getAppearenceModep1()){
                case 0:keyUp(event,(clothChanger)p1_hat);
                case 1:keyUp(event,(clothChanger)p1_cloth);
            }



            if(event.getCode()==KeyCode.UP){
                CScontroller.upkeyreleaseView((clothChanger) p2_hat);
            }
            if(event.getCode()==KeyCode.DOWN){
                CScontroller.downkeyreleaseView((clothChanger) p2_hat);
            }
        });
        //setup adaptive layout
        this.setHgrow(p2,Priority.ALWAYS);
        this.setHgrow(Character_p2,Priority.NEVER);
        this.setVgrow(next,Priority.ALWAYS);
        this.setHgrow(next,Priority.ALWAYS);
        GridPane.setHalignment(p1, HPos.CENTER);
        GridPane.setHalignment(p2, HPos.CENTER);
        GridPane.setHalignment(Character_p1, HPos.CENTER);
        GridPane.setHalignment(Character_p2, HPos.CENTER);



        this.setGridLinesVisible(false);

    }

    private void keyDown(KeyEvent event, clothChanger clothChanger){
        if (event.getCode() == KeyCode.W) {
            CScontroller.setCharactermode();
            CScontroller.up_to_downChangeView(clothChanger);
        }
        if (event.getCode() == KeyCode.S) {
            CScontroller.setCharactermode();
            CScontroller.down_to_upChangeView(clothChanger);
        }

    }
     private void keyUp(KeyEvent event, clothChanger clothChanger){
         if(event.getCode()==KeyCode.W){
             CScontroller.upkeyreleaseView(clothChanger);
         }
         if(event.getCode()==KeyCode.S){
             CScontroller.downkeyreleaseView(clothChanger);
         }
     }





}
