package rugbeats;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import rugbeats.animation.AniManager;
import rugbeats.animation.AnimName;

import java.util.ArrayList;
import java.util.List;

public class Characterselection extends GridPane {

  //hardcode Image
  Image CharacterImg1 = new Image("rugbeats/img/c1.png");
  Image CharacterImg2 = new Image("rugbeats/img/c2.png");
  Image CharacterImg3 = new Image("rugbeats/img/c3.png");
  Image WeaponImg1 = new Image("rugbeats/img/w1.png");
  Image WeaponImg2 = new Image("rugbeats/img/w2.png");
  Image WeaponImg3 = new Image("rugbeats/img/w3.png");
  List<Image> CharacterImgList = new ArrayList<>();
  List<Image> WeaponImgList = new ArrayList<>();

  clothChanger Player1_Character;
  clothChanger Player2_Character;
  Character_layout Character_p1;
  Character_layout Character_p2;
  clothChanger Player1_Weapon;
  clothChanger Player2_Weapon;


  private CharacterSelectionController CScontroller;
  private CharacterSelectionModel Csmodel;

  public Characterselection() {
    GLOBAL.gPlayer1Img.add(0, new ImageView(CharacterImg1));
    GLOBAL.gPlayer2Img.add(0, new ImageView(CharacterImg1));
    GLOBAL.gPlayer1Img.add(1, new ImageView(WeaponImg1));
    GLOBAL.gPlayer2Img.add(1, new ImageView(WeaponImg1));
    Csmodel = new CharacterSelectionModel();
    CScontroller = new CharacterSelectionController(Csmodel);
    //hardcode to setup imgList
    for(AnimName name :AnimName.values()){
//      CharacterImgList.add(new Image("rugbeats/img/hero/"++".png"));
      CharacterImgList.add(AniManager.getInstance().getFrame(name));
    }
    for (int i = 0; i < 6; i++) {
      WeaponImgList.add(AniManager.getInstance().getWeapon(i));
    }
//    CharacterImgList.add(AniManager.getInstance().getFrame(Ani));
//    CharacterImgList.add(CharacterImg2);
//    CharacterImgList.add(CharacterImg3);
//    WeaponImgList.add(WeaponImg1);
//    WeaponImgList.add(WeaponImg2);
//    WeaponImgList.add(WeaponImg3);

    Player1_Character = new clothChanger(CharacterImgList);
    Player2_Character = new clothChanger(CharacterImgList);
    Player1_Weapon = new clothChanger(WeaponImgList);
    Player2_Weapon = new clothChanger(WeaponImgList);
//final character layout
    Character_p1 = new Character_layout(CharacterImg1, WeaponImg1);
    Character_p2 = new Character_layout(CharacterImg1, WeaponImg1);
    this.setId("Characterselectionbg");
    initializeUI();

  }

  public void initializeUI() {

    Text title = new Text("Create Character");
    title.setStyle("-fx-font-size:50;-fx-fill:red;");
    GridPane.setHalignment(title, HPos.CENTER);


    int playericon = 50;
    // set up arraylist for imageview

    ImageView p1 = new ImageView(new Image("rugbeats/img/P1 label.png"));
    p1.setFitWidth(playericon);
    p1.setFitHeight(playericon);
    this.setHgap(70);

    this.setPadding(new Insets(30, 50, 30, 30));
    this.setStyle("-fx-base: #bfe7ff;");
    this.add(p1, 0, 0);
    this.add(Player1_Character, 1, 0);
    this.add(Player1_Weapon, 3, 0);
    this.add(Character_p1, 4, 0);
    Button next = new Button();
    next.setStyle("-fx-background-image:url(\"rugbeats/img/next_unclicked.png\");"
            + "-fx-background-insets: 0;"
            + "-fx-base:Transparent;" +
            "-fx-background-repeat: stretch;");
    next.setPrefSize(154, 68);
    Label instruction = new Label("Press Enter to continue");
    instruction.setFont(new Font("Arial", 20));
    instruction.setTextFill(Color.RED);
    VBox nextstep = new VBox(next, instruction);
    nextstep.setAlignment(Pos.CENTER);
    GridPane.setHalignment(nextstep, HPos.CENTER);
    this.add(nextstep, 2, 1);


    ImageView p2 = new ImageView(new Image("rugbeats/img/p2_label.png"));
    p2.setFitWidth(playericon);
    p2.setFitHeight(playericon);
    this.add(p2, 0, 2);
    this.add(Player2_Character, 1, 2);
    this.add(Player2_Weapon, 3, 2);
    this.add(Character_p2, 4, 2);

    //setup adaptive layout
    this.setHgrow(p2, Priority.ALWAYS);
    this.setHgrow(Character_p2, Priority.NEVER);
    this.setVgrow(next, Priority.ALWAYS);
    this.setHgrow(next, Priority.ALWAYS);
    GridPane.setHalignment(p1, HPos.CENTER);
    GridPane.setHalignment(p2, HPos.CENTER);
    GridPane.setHalignment(Character_p1, HPos.CENTER);
    GridPane.setHalignment(Character_p2, HPos.CENTER);
    Player1_Character.setSelectionBorder();
    Player2_Character.setSelectionBorder();
    Player1_Weapon.cancelSelectionBorder();
    Player2_Weapon.cancelSelectionBorder();


    setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        next.setStyle("-fx-background-image:url(\"rugbeats/img/next_clicked.png\");"
                + "-fx-background-insets: 0;"
                + "-fx-base:Transparent;"
                + "-fx-background-repeat: stretch;");

      }
      if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
        CScontroller.setPlayerStates(0);
        int[] PlayerStates = Csmodel.getPlayerStates();
        System.out.println(PlayerStates[0]);
        if (PlayerStates[0] == 0) {
          Player1_Character.setSelectionBorder();
          Player1_Weapon.cancelSelectionBorder();
        } else {
          Player1_Character.cancelSelectionBorder();
          Player1_Weapon.setSelectionBorder();
        }

      }

      if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {

        CScontroller.setPlayerStates(1);
        int[] PlayerStates = Csmodel.getPlayerStates();
        System.out.println(PlayerStates[1]);
        if (PlayerStates[1] == 0) {
          Player2_Character.setSelectionBorder();
          Player2_Weapon.cancelSelectionBorder();
        } else {
          Player2_Character.cancelSelectionBorder();
          Player2_Weapon.setSelectionBorder();
        }


      }
      int[] PlayerStates = Csmodel.getPlayerStates();
      if (event.getCode() == KeyCode.S) {
        if (PlayerStates[0] == 0) {
          Csmodel.p1++;
          if (Csmodel.p1 >= Csmodel.pLen - 1) {
            Csmodel.p1 = 0;
          }
          Player1_Character.PressDownChangeimg();
          GLOBAL.gPlayer1Img.add(0, Player1_Character.getImg());
          Character_p1.setselectCharacter(GLOBAL.gPlayer1Img.get(0).getImage());
        } else {
          Csmodel.p1w++;
          if (Csmodel.p1w >= Csmodel.wLen - 1) {
            Csmodel.p1w = 0;
          }
          Player1_Weapon.PressDownChangeimg();
          GLOBAL.gPlayer1Img.add(1, Player1_Weapon.getImg());
          Character_p1.setselectWeapon(GLOBAL.gPlayer1Img.get(1).getImage());
        }
      }
      if (event.getCode() == KeyCode.W) {
        if (PlayerStates[0] == 0) {
          Csmodel.p1--;
          if (Csmodel.p1 < 0) {
            Csmodel.p1 = Csmodel.pLen - 1;
          }
          Player1_Character.PressUpChangeimg();
          GLOBAL.gPlayer1Img.add(0, Player1_Character.getImg());
          Character_p1.setselectCharacter(GLOBAL.gPlayer1Img.get(0).getImage());
        } else {
          Csmodel.p1w--;
          if (Csmodel.p1w < 0) {
            Csmodel.p1w = Csmodel.wLen - 1;
          }
          Player1_Weapon.PressUpChangeimg();
          GLOBAL.gPlayer1Img.add(1, Player1_Weapon.getImg());
          Character_p1.setselectWeapon(GLOBAL.gPlayer1Img.get(1).getImage());
        }
      }
      if (event.getCode() == KeyCode.DOWN) {
        if (PlayerStates[1] == 0) {
          Csmodel.p2++;
          if (Csmodel.p2 >= Csmodel.pLen - 1) {
            Csmodel.p2 = 0;
          }
          Player2_Character.PressDownChangeimg();
          GLOBAL.gPlayer2Img.add(0, Player2_Character.getImg());
          Character_p2.setselectCharacter(GLOBAL.gPlayer2Img.get(0).getImage());
        } else {
          Csmodel.p2w++;
          if (Csmodel.p2w >= Csmodel.wLen - 1) {
            Csmodel.p2w = 0;
          }
          Player2_Weapon.PressDownChangeimg();
          GLOBAL.gPlayer2Img.add(1, Player2_Weapon.getImg());
          Character_p2.setselectWeapon(GLOBAL.gPlayer2Img.get(1).getImage());
        }
      }
      ;
      if (event.getCode() == KeyCode.UP) {
        if (PlayerStates[1] == 0) {
          Csmodel.p2--;
          if (Csmodel.p2 < 0) {
            Csmodel.p2 = Csmodel.pLen - 1;
          }
          Player2_Character.PressUpChangeimg();
          GLOBAL.gPlayer2Img.add(0, Player2_Character.getImg());
          Character_p2.setselectCharacter(GLOBAL.gPlayer2Img.get(0).getImage());
        } else {
          Csmodel.p2w--;
          if (Csmodel.p2w < 0) {
            Csmodel.p2w = Csmodel.wLen - 1;
          }
          Player2_Weapon.PressUpChangeimg();
          GLOBAL.gPlayer2Img.add(1, Player2_Weapon.getImg());
          Character_p2.setselectWeapon(GLOBAL.gPlayer2Img.get(1).getImage());
        }
      }
      ;
    });

    setOnKeyReleased(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        GLOBAL.p1 = Csmodel.p1;
        GLOBAL.p2 = Csmodel.p2;
        GLOBAL.pw1 = Csmodel.p1w;
        GLOBAL.pw2 = Csmodel.p2w;
        System.out.println("+++++++++++++++");
        System.out.println(GLOBAL.p1);
        System.out.println(GLOBAL.p2);
        System.out.println(GLOBAL.pw1);
        System.out.println(GLOBAL.pw2);
        GLOBAL.fxinstance.nextScene();
      }
      if (event.getCode() == KeyCode.S) {
        Player1_Character.ReleaseDownChangeimg();
        Player1_Weapon.ReleaseDownChangeimg();
      }
      ;
      if (event.getCode() == KeyCode.W) {
        Player1_Character.ReleaseUpChangeimg();
        Player1_Weapon.ReleaseUpChangeimg();
      }
      ;
      if (event.getCode() == KeyCode.DOWN) {
        Player2_Character.ReleaseDownChangeimg();
        Player2_Weapon.ReleaseDownChangeimg();
      }
      ;
      if (event.getCode() == KeyCode.UP) {
        Player2_Character.ReleaseUpChangeimg();
        Player2_Weapon.ReleaseUpChangeimg();
      }
      ;
    });


    this.setGridLinesVisible(true);

  }


}
