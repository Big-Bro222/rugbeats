package rugbeats;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


class StartPage {
  Image[] imgs = new Image[3];
  //  float[] transparency = {1, 1};
  private Main _app;
  private Scene _scene;
  private Pane root = new Pane();
  private Canvas canvas = new Canvas();
  private Label startLbl = new Label("Images: 0x72.itch.io\n" +
          "Music: icons8 & musmus");
  GraphicsContext gc = canvas.getGraphicsContext2D();
  Player player1 = new Player("Ricky", 2, 0);
  Player player2 = new Player("Morty", GLOBAL.GRID_COLS - 3, GLOBAL.GRID_ROWS - 4);


  private float progress = 0;
  private float newProgress = 0;

  Scene getScene() {
    return _scene;
  }

  StartPage() {
    // load images
    String[] fnames = {"rugbeats/img/wasd.png", "rugbeats/img/udlr.png", "rugbeats/img/guide.png"};
    for (int i = 0; i < fnames.length; i++) {
      imgs[i] = new Image(fnames[i]);
    }
    // init game components
    player2.setKeys(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
    player1.setFreeMode(true);
    player2.setFreeMode(true);
    player2.setAnim((int) (Math.random() * 6));
    player2.setWeapon((int) (Math.random() * 6));
    player1.setAnim((int) (Math.random() * 6));
    player1.setWeapon((int) (Math.random() * 6));
    init();// init GUI
  }

  StartPage(Main app) {
    this();
    _app = app;
  }

  void init() {
    System.out.println("init start page");
    root.getChildren().add(canvas);
//    root.getChildren().add(startBtn);
    startLbl.setStyle("-fx-font-size:18;");
    startLbl.setTextFill(Color.WHITE);
//    startLbl.setAlignment(Pos.CENTER);
    startLbl.setLayoutY(GLOBAL.WINDOW_H - 100);
    startLbl.setLayoutX(30);
    root.getChildren().add(startLbl);

    canvas.widthProperty().bind(root.widthProperty());
    canvas.heightProperty().bind(root.heightProperty());
    _scene = new Scene(root, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);// preferred geometry
    _scene.setOnKeyPressed(evt -> {
      update(evt);
    });
    _scene.setOnKeyReleased(evt -> {
      update(evt);
    });
    final long startNanoTime = System.nanoTime();// not used yet
    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        if (Math.abs(progress - newProgress) > 0.03f) {
          progress += (newProgress - progress) / 100;
          progress = Utils.clamp(progress, 0, 1);
        }
        draw();
      }
    }.start();
    // calc initial progress
    newProgress = Utils.clamp(1 -
                    player1.getDist(player2.getX(), player2.getY()) / GLOBAL.WINDOW_W
            , 0, 1);
  }

  void update(KeyEvent kevt) {
    if (kevt.getCode() == KeyCode.SPACE) {
      _app.nextScene();
    }
    if (newProgress > 0.95) {
      _app.nextScene();
    }

    player2.handleKey(kevt);
    player1.handleKey(kevt);
    newProgress = Utils.clamp(1 -
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
    gc.setFill(Utils.colorInterplator(GLOBAL.START_CLR, GLOBAL.END_CLR, progress));
    gc.fillRect(0, 0, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);
//    gc.setFill(Color.RED);
    player1.draw(gc);
    player2.draw(gc);
    gc.drawImage(imgs[0], player1.getX() - imgs[0].getWidth() / 2+GLOBAL.GRID_SIZE/3, player1.getY() + 50);
    gc.drawImage(imgs[1], player2.getX() - imgs[0].getWidth() / 2+GLOBAL.GRID_SIZE/3, player2.getY() + 50);
    gc.drawImage(imgs[2], GLOBAL.WINDOW_W / 2 - imgs[2].getWidth() / 2, 50);
    _app.gamePage._controller.drawBeat(gc, AudioManager.getInstance()._elapsedSec);
//    gc.lineTo(player1.getX(), player1.getY());
//    gc.lineTo(player2.getX(), player2.getY());
  }


}
