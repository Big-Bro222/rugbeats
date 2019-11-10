package rugbeats;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class Character_layout extends HBox{
    private Image Character;
    private Image Weapon;
    private ImageView CharacterView;
    private ImageView WeaponView;
    private int iconsize;
    public Character_layout(Image Character,Image Weapon) {
        this.Character=Character;
        this.Weapon=Weapon;
        iconsize=70;
        getCharacter();
        this.setWidth(10);
        this.setId("Character_layout");
    }


      private void getCharacter(){

          CharacterView=new ImageView(Character);
          CharacterView.setFitHeight(iconsize);
          CharacterView.setPreserveRatio(true);
          WeaponView=new ImageView(Weapon);
          WeaponView.setFitHeight(iconsize);
          WeaponView.setPreserveRatio(true);
          this.setPadding(new Insets(30,30,30,30));
          this.setAlignment(Pos.CENTER);
          this.getChildren().addAll(CharacterView,WeaponView);
    };

    public void setselectCharacter(Image character){

        CharacterView.setImage(character);
    }
    public void setselectWeapon(Image weapon){

        WeaponView.setImage(weapon);
    }



}
