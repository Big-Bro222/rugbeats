package rugbeats;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
  private Scene[] _scenes = new Scene[4];
  int currentScene = 0;
  Stage _stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    super.init();
    GLOBAL.fxinstance=this;

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    _stage = primaryStage;
    Group dRoot = new Group();
    Canvas canvas = new Canvas(512, 256);
    dRoot.getChildren().add(canvas);
    StartPage startPage = new StartPage(this);

    final long startNanoTime = System.nanoTime();
//    new AnimationTimer() {
//      public void handle(long currentNanoTime) {
//        startPage.draw();
//      }
//    }.start();


    _scenes[0] = startPage.getScene();
//<<<<<<< HEAD
//    _scenes[1] =  new Scene(new Characterselection(), GLOBAL.gWindowW, GLOBAL.gWindowH);
//=======
    _scenes[1] =  new Scene(new Characterselection(), GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    _scenes[2] = new Scene(new MazeGenerator(),GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    _scenes[3] = new Scene(new GameoverScene(),GLOBAL.WINDOW_W,GLOBAL.WINDOW_H);
    System.out.println("sfds");

    for(int i=0;i<_scenes.length;i++){
        _scenes[i].getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    }
    primaryStage.setScene(_scenes[0]);
    primaryStage.setTitle("Rugbeats");
    //primaryStage.setResizable(false);
    primaryStage.show();
}


  public void nextScene(){
    currentScene++;

    _stage.setScene(_scenes[currentScene]);
    System.out.println("loaded scene: "+currentScene);
  }
}
