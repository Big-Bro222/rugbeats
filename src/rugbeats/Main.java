package rugbeats;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Main extends Application {
  private Scene[] _scenes = new Scene[3];
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
    System.out.println("sfds");

    primaryStage.setScene(_scenes[0]);
    primaryStage.setTitle("Rugbeats");
    primaryStage.show();
//>>>>>>> caef0672099cebba31172561287e5e2c1b9b3faa
}




  public void nextScene(){
    currentScene++;

    _stage.setScene(_scenes[currentScene]);
    System.out.println("loaded scene: "+currentScene);
  }
}
