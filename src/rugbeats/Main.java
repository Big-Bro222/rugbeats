package rugbeats;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    StartPage startPage=new StartPage();
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Group dRoot = new Group();
    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(dRoot, 300, 275));

//        primaryStage.setFullScreen(true);

    Canvas canvas = new Canvas(512, 256);
    dRoot.getChildren().add(canvas);
    final long startNanoTime = System.nanoTime();

//    new AnimationTimer() {
//      public void handle(long currentNanoTime) {
//        startPage.draw();
//      }
//    }.start();

    primaryStage.setScene(startPage.getScene());
    primaryStage.show();
  }
}
