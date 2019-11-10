package rugbeats;


import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GameoverScene extends GridPane {
    BackgroundImage quitgame_selected;
    BackgroundImage quitgame_pressed;
    BackgroundImage quitgame_unselected;
    BackgroundImage restartgame_selected;
    BackgroundImage restartgame_unselected;
    BackgroundImage restartgame_pressed;
    Character_layout Character_p1;
    public GameoverScene(){
         this.setId("gameoverScene");
        quitgame_selected= new BackgroundImage(new Image("rugbeats/img/quit game selected.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                 new BackgroundSize(1.0, 1.0, true, true, false, false));

        quitgame_unselected= new BackgroundImage(new Image("rugbeats/img/quit game unselected.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        quitgame_pressed= new BackgroundImage(new Image("rugbeats/img/quit game selected.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        restartgame_pressed= new BackgroundImage(new Image("rugbeats/img/restart game pressed.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        restartgame_unselected= new BackgroundImage(new Image("rugbeats/img/restart game unselected.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        restartgame_selected= new BackgroundImage(new Image("rugbeats/img/restart game selected.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Character_p1=new Character_layout(GLOBAL.gPlayer1Img.get(0).getImage(),GLOBAL.gPlayer1Img.get(1).getImage());
        intialUI();

     }



     void intialUI(){
         Button restartGame= new Button();
         restartGame.setPrefSize(250,70);
         restartGame.setBackground(new Background(restartgame_selected));
         restartGame.setOnMousePressed(event -> {

             restartGame.setBackground(new Background(restartgame_pressed));
         });
         restartGame.setOnMouseReleased(event -> {
             restartGame.setBackground(new Background(restartgame_selected));
             System.out.println("gamescene");
                 }
         );

         Button quitGame= new Button();
         quitGame.setPrefSize(250,70);
         quitGame.setBackground(new Background(quitgame_unselected));
         quitGame.setOnMouseEntered(event -> {
             quitGame.setBackground(new Background(quitgame_selected));
             restartGame.setBackground(new Background(restartgame_unselected));
         });
         quitGame.setOnMouseExited(event -> {
             quitGame.setBackground(new Background(quitgame_unselected));
             restartGame.setBackground(new Background(restartgame_selected));
         });
         quitGame.setOnMousePressed(event -> {
             quitGame.setBackground(new Background(quitgame_pressed));
         });
         quitGame.setOnMousePressed(event -> {
             quitGame.setBackground(new Background(quitgame_pressed));
         });
         quitGame.setOnMouseReleased(event -> {
             Stage stage = (Stage) quitGame.getScene().getWindow();
             // do what you have to do
             stage.close();

         });
         VBox Controlbuttons = new VBox(restartGame,quitGame);
         VBox.setMargin(restartGame,new Insets(30,30,30,30));
         VBox.setMargin(quitGame,new Insets(30,30,30,30));
         Controlbuttons.setAlignment(Pos.CENTER);
        // this.setCenter(Controlbuttons);


         this.add(Controlbuttons,0,1);
         Character_p1.setMaxWidth(200);

         this.setPadding(new Insets(100,100,100,150));
         this.add(Character_p1,0,0);
         ImageView thanks=new ImageView(new Image("rugbeats/img/Thanks.png"));
         GridPane.setHalignment(Character_p1, HPos.CENTER);
         this.setVgap(20);
         this.add(thanks,0,2);
         this.setAlignment(Pos.CENTER);
         //this.setGridLinesVisible(true);




     }

}
