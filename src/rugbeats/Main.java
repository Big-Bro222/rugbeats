package rugbeats;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Main extends Application {
  private Scene[] _scenes = new Scene[2];
  int currentScene = 0;
  Stage _stage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    super.init();


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
    primaryStage.setScene(startPage.getScene());
    primaryStage.setTitle("Rugbeats");
    primaryStage.show();

    _scenes[0] = startPage.getScene();
    _scenes[1] =  new Scene(new Characterselection(), GLOBAL.gWindowW, GLOBAL.gWindowH);
}




  public void nextScene(){
    currentScene++;

    _stage.setScene(_scenes[currentScene]);
    System.out.println("loaded scene: "+currentScene);
  }
}
