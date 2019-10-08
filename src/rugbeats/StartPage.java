package rugbeats;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


class StartPage {
  private Main _app;
  private Scene _scene;
  private Pane root = new Pane();
  private Button[] p1Keys={new Button("W"),new Button("S"),new Button("A"),new Button("D")};
  private Button[] p2Keys={new Button("▲"),new Button("▼"),new Button("◄"),new Button("►")};
  private Canvas canvas = new Canvas();
  private Button startBtn = new Button("Start Game");
  private Label startLbl=new Label("Stay Closer to Start Game");
  GraphicsContext gc = canvas.getGraphicsContext2D();
  Player player1 = new Player("Morty", GLOBAL.GRID_COLS-8, GLOBAL.GRID_ROWS/2-5);
  Player player2 = new Player("Alex", 8, GLOBAL.GRID_ROWS/2-5);


  private float progress = 0;

  Scene getScene() {
    return _scene;
  }

  StartPage() {
    // init game components
    player1.setKeys(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
    init();// init GUI
  }
  StartPage(Main app){
    this();
    _app=app;
  }

  void init() {
    System.out.println("init start page");
    root.getChildren().add(canvas);
//    root.getChildren().add(startBtn);
    startLbl.setStyle("-fx-font-size:40;");
    root.getChildren().add(startLbl);
    root.getChildren().add(startBtn);
    startBtn.setLayoutX(80);
    p1Keys[3].setLayoutX(20);
    p1Keys[3].setLayoutY(30);

    // put instruction keys
    p1Keys[0].setLayoutX(160); //w
    p1Keys[0].setLayoutY(200);
    p1Keys[1].setLayoutX(160);//d
    p1Keys[1].setLayoutY(230);
    p1Keys[2].setLayoutX(130);//a
    p1Keys[2].setLayoutY(230);
    p1Keys[3].setLayoutX(190);//s
    p1Keys[3].setLayoutY(230);

    p2Keys[0].setLayoutX(160+585);
    p2Keys[0].setLayoutY(200);
    p2Keys[1].setLayoutX(160+585);
    p2Keys[1].setLayoutY(230);
    p2Keys[2].setLayoutX(130+585);
    p2Keys[2].setLayoutY(230);
    p2Keys[3].setLayoutX(190+585);
    p2Keys[3].setLayoutY(230);
    root.getChildren().addAll(p2Keys);
    root.getChildren().addAll(p1Keys);
    canvas.widthProperty().bind(root.widthProperty());
    canvas.heightProperty().bind(root.heightProperty());
    _scene = new Scene(root, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);// preferred geometry
    _scene.setOnKeyPressed(evt -> {
      System.out.println(evt);
      update(evt);
    });
    _scene.setOnKeyReleased(evt -> {
      update(evt);
    });
    final long startNanoTime = System.nanoTime();// not used yet
    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        draw();
      }
    }.start();
    // calc initial progress
    progress = Utils.clamp(1 -
                    player1.getDist(player2.getX(), player2.getY()) / GLOBAL.WINDOW_W
            , 0, 1);
  }

  void update(KeyEvent kevt) {
    if(progress>0.95){
      _app.nextScene();
    }
    player2.handleKey(kevt);
    player1.handleKey(kevt);
    progress = Utils.clamp(1 -
                    player1.getDist(player2.getX(), player2.getY()) / GLOBAL.WINDOW_W
            , 0, 1);
    System.out.println(progress);
    String colorStr = Utils.hexColor(
            new Color(GLOBAL.YELLOW.getRed(), GLOBAL.YELLOW.getGreen(), GLOBAL.YELLOW.getBlue()
                    , progress));
    String style = " -fx-background-color: #"
            + colorStr + " ;";
  }

  void draw() {
    // clamp should be added
    gc.setFill(Utils.colorInterplator(GLOBAL.START_CLR,GLOBAL.END_CLR, progress));
    gc.fillRect(0, 0, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
    gc.setFill(Color.RED);
    player1.draw(gc);
    player2.draw(gc);
    gc.lineTo(player1.getX(), player1.getY());
    gc.lineTo(player2.getX(), player2.getY());
  }


}
