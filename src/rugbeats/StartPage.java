package rugbeats;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


class StartPage {
  private Scene scene;
  private StackPane root = new StackPane();
  private Canvas canvas = new Canvas();
  GraphicsContext gc = canvas.getGraphicsContext2D();
  Player player1 = new Player("Alex", 22, 2);
  Player player2 = new Player("Morty", 14, 4);

  Scene getScene() {
    return scene;
  }

  StartPage() {
    // init game components
    player1.setKeys(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
    init();// init GUI
  }

  void init() {
    root.getChildren().add(new Button("Hi"));
    root.getChildren().add(canvas);
    canvas.widthProperty().bind(root.widthProperty());
    canvas.heightProperty().bind(root.heightProperty());
    scene = new Scene(root, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);// preferred geometry
    scene.setOnKeyPressed(evt -> {
      System.out.println(evt);
      update(evt);
    });
    scene.setOnKeyReleased(evt -> {
      update(evt);
    });
    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        draw();
      }
    }.start();
  }

  void update(KeyEvent kevt) {
    player2.handleKey(kevt);
    player1.handleKey(kevt);
  }

  void draw() {
    // clamp should be added
    gc.setFill(Utils.colorInterplator(Color.WHEAT, Color.BLUE, player1.getDist(player2.getX(), player2.getY())/GLOBAL.WINDOW_W));
    gc.fillRect(0, 0, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    gc.setFill(Color.RED);
    player1.draw(gc);
    player2.draw(gc);
    gc.lineTo(player1.getX(), player1.getY());
    gc.lineTo(player2.getX(), player2.getY());
  }


}
