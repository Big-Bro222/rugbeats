package rugbeats;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Group dRoot = new Group();

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(dRoot, 300, 275));
//        primaryStage.setFullScreen(true);

    Canvas canvas = new Canvas(512, 256);

    dRoot.getChildren().add(canvas);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    final long startNanoTime = System.nanoTime();

    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        double t = (currentNanoTime - startNanoTime) / 1000000000.0;

        double x = 232 + 128 * Math.cos(t);
        double y = 232 + 128 * Math.sin(t);

        // background image clears canvas
//            gc.drawImage( space, 0, 0 );
//            gc.drawImage( earth, x, y );
//            gc.drawImage( sun, 196, 196 );
        gc.fillRect(0, 0, 55, 233);
      }
    }.start();
    Button changeScene = new Button("to character scene");



    Characterselection characterscene = new Characterselection();

    changeScene.setOnMouseClicked(event -> {
      System.out.println("clicked");
    });
    dRoot.getChildren().add(changeScene);
    primaryStage.setScene(new Scene(characterscene, 800, 500));
    primaryStage.setTitle("characterscene");
    primaryStage.show();





  }
}
