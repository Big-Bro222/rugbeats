package rugbeats;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;



import java.util.ArrayList;
import java.util.List;

public class Characterselection extends GridPane {
    //hardcode Image
    Image CharacterImg1= new Image("rugbeats/img/c1.png");
    Image CharacterImg2= new Image("rugbeats/img/c2.png");
    Image CharacterImg3= new Image("rugbeats/img/c3.png");
    Image WeaponImg1= new Image("rugbeats/img/w1.png");
    Image WeaponImg2= new Image("rugbeats/img/w2.png");
    Image WeaponImg3= new Image("rugbeats/img/w3.png");
    List<Image> CharacterImgList = new ArrayList<>();
    List<Image> WeaponImgList= new ArrayList<>();

    clothChanger Player1_Character;
    clothChanger Player2_Character;
    HBox Character_p1=new HBox();
    clothChanger Player1_Weapon;
    clothChanger Player2_Weapon;


    HBox Character_p2=new HBox();

    private CharacterSelectionController CScontroller;
    private CharacterSelectionModel Csmodel;

    public Characterselection(){
        Csmodel=new CharacterSelectionModel();
        CScontroller= new CharacterSelectionController(Csmodel);
        //hardcode to setup imgList
        CharacterImgList.add(CharacterImg1);
        CharacterImgList.add(CharacterImg2);
        CharacterImgList.add(CharacterImg3);
        WeaponImgList.add(WeaponImg1);
        WeaponImgList.add(WeaponImg2);
        WeaponImgList.add(WeaponImg3);

        Player1_Character= new clothChanger(CharacterImgList);
        Player2_Character= new clothChanger(CharacterImgList);
        Player1_Weapon= new clothChanger(WeaponImgList);
        Player2_Weapon= new clothChanger(WeaponImgList);
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

            GLOBAL.gPlayer1Img.add(0,Player1_Character.getImg());
            GLOBAL.gPlayer1Img.add(1,Player1_Weapon.getImg());
            GLOBAL.gPlayer2Img.add(0,Player2_Character.getImg());
            GLOBAL.gPlayer2Img.add(1,Player2_Weapon.getImg());
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

        //setup adaptive layout
        this.setHgrow(p2,Priority.ALWAYS);
        this.setHgrow(Character_p2,Priority.NEVER);
        this.setVgrow(next,Priority.ALWAYS);
        this.setHgrow(next,Priority.ALWAYS);
        GridPane.setHalignment(p1, HPos.CENTER);
        GridPane.setHalignment(p2, HPos.CENTER);
        GridPane.setHalignment(Character_p1, HPos.CENTER);
        GridPane.setHalignment(Character_p2, HPos.CENTER);
        Player1_Character.setSelectionBorder();
        Player2_Character.setSelectionBorder();



        setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                next.setStyle("-fx-base:orange");
            }
            if(event.getCode()==KeyCode.A||event.getCode()==KeyCode.D){
               CScontroller.setPlayerStates(0);
               int[]PlayerStates =Csmodel.getPlayerStates();
               System.out.println(PlayerStates[0]);
               if(PlayerStates[0]==0){
                   Player1_Character.setSelectionBorder();
                   Player1_Weapon.cancelSelectionBorder();}
               else {
                   Player1_Character.cancelSelectionBorder();
                   Player1_Weapon.setSelectionBorder();
               }

            }

            if(event.getCode()==KeyCode.LEFT||event.getCode()==KeyCode.RIGHT){

                CScontroller.setPlayerStates(1);
                int[]PlayerStates =Csmodel.getPlayerStates();
                System.out.println(PlayerStates[1]);
                if(PlayerStates[1]==0){
                    Player2_Character.setSelectionBorder();
                    Player2_Weapon.cancelSelectionBorder();}
                else {
                    Player2_Character.cancelSelectionBorder();
                    Player2_Weapon.setSelectionBorder();
                }


            }
            int[]PlayerStates =Csmodel.getPlayerStates();
            if(event.getCode()==KeyCode.S){
                if(PlayerStates[0]==0){
                    Player1_Character.PressDownChangeimg();
                }
                else{Player1_Weapon.PressDownChangeimg();}
            }
            if(event.getCode()==KeyCode.W){
                if(PlayerStates[0]==0){
                    Player1_Character.PressUpChangeimg();
                }
                else{Player1_Weapon.PressUpChangeimg();}
            };
            if(event.getCode()==KeyCode.DOWN){
                if(PlayerStates[1]==0){
                    Player2_Character.PressDownChangeimg();
                }
                else{Player2_Weapon.PressDownChangeimg();}
            };
            if(event.getCode()==KeyCode.UP){
                if(PlayerStates[1]==0){
                    Player2_Character.PressUpChangeimg();
                }
                else{Player2_Weapon.PressUpChangeimg();}
            };
        });

        setOnKeyReleased(event -> {
            if(event.getCode()==KeyCode.ENTER){
                GLOBAL.gPlayer1Img.add(0,Player1_Character.getImg());
                GLOBAL.gPlayer1Img.add(1,Player1_Weapon.getImg());
                GLOBAL.gPlayer2Img.add(0,Player2_Character.getImg());
                GLOBAL.gPlayer2Img.add(1,Player2_Weapon.getImg());
                GLOBAL.fxinstance.nextScene();
            }
            if(event.getCode()==KeyCode.S){
                Player1_Character.ReleaseDownChangeimg();
                Player1_Weapon.ReleaseDownChangeimg();
            };
            if(event.getCode()==KeyCode.W){
                Player1_Character.ReleaseUpChangeimg();
                Player1_Weapon.ReleaseUpChangeimg();
            };
            if(event.getCode()==KeyCode.DOWN){
                Player2_Character.ReleaseDownChangeimg();
                Player2_Weapon.ReleaseDownChangeimg();
            };
            if(event.getCode()==KeyCode.UP){
                Player2_Character.ReleaseUpChangeimg();
                Player2_Weapon.ReleaseUpChangeimg();
            };
        });


        this.setGridLinesVisible(false);

    }








}
