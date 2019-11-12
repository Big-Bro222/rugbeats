package rugbeats;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import rugbeats.animation.AniManager;

public class Main extends Application {
  private Scene[] _scenes = new Scene[5];
  int currentScene = 0;
  Stage _stage;
  GamePage gamePage;
//  GameoverScene gameoverScene;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    super.init();
    GLOBAL.fxinstance = this;
    AniManager.getInstance().loadImages();
    AudioManager.getInstance().loadMusic();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    _stage = primaryStage;
    Group dRoot = new Group();
    Canvas canvas = new Canvas(512, 256);
    dRoot.getChildren().add(canvas);
    StartPage startPage = new StartPage(this);
    gamePage = new GamePage(this);

//    gameoverScene = new GameoverScene();
//    gameoverScene.setApp(this);

    _scenes[0] = startPage.getScene();
    _scenes[1] = new Scene(new Characterselection(), GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    _scenes[2] = new Scene(new MazeGenerator(), GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    _scenes[3] = gamePage.getScene();
    _scenes[4] = new Scene(new GameoverScene(this), GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    for (int i = 0; i < 4; i++) {
      _scenes[i].setOnMouseClicked(event -> {
        if (event.getButton()== MouseButton.MIDDLE){

          nextScene();
        }
      });
    }

    for (int i = 0; i < _scenes.length; i++) {
      _scenes[i].getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
    }

    primaryStage.setScene(_scenes[0]);
    primaryStage.setTitle("Rugbeats");
    //primaryStage.setResizable(false);
    primaryStage.show();
    AudioManager.getInstance().play(1);
  }

  void gotoGame() {
    currentScene = 3;
    gamePage.initGame();
    _stage.setScene(_scenes[currentScene]);
    System.out.println("loaded scene: " + currentScene);
  }

  public void nextScene() {
    currentScene++;
    if (currentScene == 3) {
      gamePage.initGame();
    }
    _stage.setScene(_scenes[currentScene]);
    System.out.println("loaded scene: " + currentScene);
  }
}
