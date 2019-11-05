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

    ImageView CharacterImg1= new ImageView("rugbeats/img/c1.png");
    ImageView CharacterImg2= new ImageView("rugbeats/img/c2.png");
    ImageView CharacterImg3= new ImageView("rugbeats/img/c3.png");
    ImageView WeaponImg1= new ImageView("rugbeats/img/w1.png");
    ImageView WeaponImg2= new ImageView("rugbeats/img/w2.png");
    ImageView WeaponImg3= new ImageView("rugbeats/img/w3.png");
    clothChanger Player1_Character= new clothChanger(CharacterImg1,CharacterImg2,CharacterImg3);
    clothChanger Player2_Character= new clothChanger(CharacterImg1,CharacterImg2,CharacterImg3);
    HBox Character_p1=new HBox();
    clothChanger Player1_Weapon= new clothChanger(CharacterImg1,CharacterImg2,CharacterImg3);
    clothChanger Player2_Weapon= new clothChanger(CharacterImg1,CharacterImg2,CharacterImg3);


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
        Character_p1=new Character_layout();
        Label p1= new Label("P1");
        this.setHgap(100);
        this.setPadding(new Insets(30,0,10,30));
        this.setStyle("-fx-base: #bfe7ff;");
        this.add(p1,0,0);
        this.add(Player1_Character,1,0);
        this.add(Player1_Weapon,3,0);
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

        Character_p2=new Character_layout();
        Label p2= new Label("P2");
        this.add(p2,0,2);
        this.add(Player2_Character,1,2);
        this.add(Player2_Weapon,3,2);
        this.add(Character_p2,4,2);

        CScontroller.setSelectionBorderView(Player1_Character);
        CScontroller.setSelectionBorderView(Player2_Weapon);

        setOnKeyPressed(event -> {

            if (event.getCode()==KeyCode.A){
                CScontroller.setAppearenceModep1();
            }
            if (event.getCode()==KeyCode.D){
                CScontroller.setAppearenceModep1();
            }

            if(Csmodel.getAppearenceModep1()==0){
                CScontroller.cancelSelectionBorderView(Player1_Weapon);
                keyDown(event,Player1_Character);
                CScontroller.setSelectionBorderView(Player1_Character);
            }
            else{
                CScontroller.cancelSelectionBorderView(Player1_Character);
                keyDown(event,Player1_Weapon);
                CScontroller.setSelectionBorderView(Player1_Weapon);
            }




        });

        setOnKeyReleased(event -> {
            switch (Csmodel.getAppearenceModep1()){
                case 0:keyUp(event,Player1_Character);
                case 1:keyUp(event,Player1_Character);
            }



            if(event.getCode()==KeyCode.UP){
                CScontroller.upkeyreleaseView(Player1_Character);
            }
            if(event.getCode()==KeyCode.DOWN){
                CScontroller.downkeyreleaseView(Player1_Character);
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
