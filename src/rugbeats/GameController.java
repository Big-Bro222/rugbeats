package rugbeats;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameController {
  String[] imgNames = {"rugbeats/img/floor_1.png", "rugbeats/img/wall/hole.png", "rugbeats/img/wall/mid.png"
          , "rugbeats/img/wall/horizontal.png", "rugbeats/img/wall/vertical.png", "rugbeats/img/wall/tl.png"
          , "rugbeats/img/wall/tr.png", "rugbeats/img/wall/bl.png", "rugbeats/img/wall/br.png"
          , "rugbeats/img/wall/top.png", "rugbeats/img/wall/bottom.png"};
  Image[] mapImgs = new Image[11];
  Image[] laneImgs = new Image[4];
  Image home, chest;
  GameModel _model;
  AudioManager _aM;
  Player _p1;
  Player _p2;
  Scene _scene;
  Canvas _canvas;
  GraphicsContext _gc;
  AnimationTimer _animator;
  Main _app;
  int gridSize = GLOBAL.GRID_SIZE;
  long startNanoTime;
  float elapsedSecond;

  public void setApp(Main app){
    _app=app;
  }
  void bindModel(GameModel m) {
    _model = m;
  }

  void bindScene(Scene s) {
    _scene = s;
    _scene.setOnKeyPressed(this::update);
    _scene.setOnKeyReleased(this::update);
  }

  void bindCanvas(Canvas c) {
    _canvas = c;
    _gc = _canvas.getGraphicsContext2D();
  }

  GameController() {
    loadImg();
    _animator = new AnimationTimer() {
      public void handle(long currentNanoTime) {
        elapsedSecond = (currentNanoTime - startNanoTime) / 1000000000;
        draw(elapsedSecond);
      }
    };
  }

  private void loadImg() {
    // wall img
    for (int i = 0; i < mapImgs.length; i++) {
      System.out.println(i + "---  " + imgNames[i]);
      mapImgs[i] = new Image(imgNames[i]);
    }
    // lane img
    for (int i = 0; i < 4; i++) {
      laneImgs[i] = new Image("rugbeats/img/floor_" + (i + 1) + ".png");
    }
    home = new Image("rugbeats/img/home.png");
    chest = new Image("rugbeats/img/chest.png");
  }

  void initGame() {
    _p1 = new Player("Morty", 0, 0);
    _p2 = new Player("Rick", GLOBAL.GRID_COLS - 1, GLOBAL.GRID_ROWS - 1);
    _p1.setAnim(GLOBAL.p1);
    _p1.setWeapon(GLOBAL.pw1);
    _p2.setAnim(GLOBAL.p2);
    _p2.setWeapon(GLOBAL.pw2);
    _model.calcMap(GLOBAL.MAZE_STATE);
    _p1.bindModel(_model);
    _p2.bindModel(_model);
    _p1.bindController(this);
    _p2.bindController(this);

    _p2.setKeys(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
    _animator.start();
    startNanoTime = System.nanoTime();
    AudioManager.getInstance().play((int) (Math.random() * 2));
  }

  private void update(KeyEvent evt) {
    _p1.handleKey(evt);
    _p2.handleKey(evt);
  }

  private void draw(float elapsedSec) {
    drawMap();
    _p1.draw(_gc);
    _p2.draw(_gc);
    drawBeat(_gc, elapsedSec);
    _gc.drawImage(chest, _model.chestX * gridSize + (int) (0.2f * gridSize)
            , _model.chestY * gridSize + (int) (0.2f * gridSize) - gridSize / 4
            , gridSize * 0.6f, gridSize * 0.6f);
  }

  public void drawBeat(GraphicsContext gc, float elapsedSec) {
    int beatTop = GLOBAL.GRID_ROWS * gridSize;
    //bg
    gc.setFill(Color.BLACK);
    gc.fillRect(0, GLOBAL.GRID_ROWS * gridSize, GLOBAL.WINDOW_W, GLOBAL.BEAT_H);
    int cX = GLOBAL.WINDOW_W / 2;
    int margin = gridSize * 2;
//    _gc.setFill(new Color(0.2, 0.8, 0.8, 1));
    AudioManager.getInstance().getBeatOffset();
    for (int i = 1; i <= 5; i++) {
      gc.setFill(new Color(0.2, 0.8, 0.8, 1.2 - i / 5.0f));
      float minusOffset = -i * margin - GLOBAL.BEAT_W / 2 + AudioManager.getInstance()._offset * margin;
      float plusOffset = i * margin - GLOBAL.BEAT_W / 2 - AudioManager.getInstance()._offset * margin;
      gc.fillRoundRect(cX + minusOffset
              , beatTop - 5 + i, GLOBAL.BEAT_W, GLOBAL.BEAT_H + 5 - i, 3, 3);
      gc.fillRoundRect(cX + plusOffset
              , beatTop - 5 + i, GLOBAL.BEAT_W, GLOBAL.BEAT_H + 5 - i, 3, 3);
    }
  }

  private void drawMap() {
    for (int r = 0; r < GLOBAL.GRID_ROWS; r++) {
      for (int c = 0; c < GLOBAL.GRID_COLS; c++) {
        Cell cell = _model.mapTable[r][c];
        switch (cell._type) {
          case Lane:
            _gc.drawImage(laneImgs[((r + c) % 2 == 0) ? 0 : 1]
                    , cell._x * gridSize, cell._y * gridSize, gridSize, gridSize);
            break;
          default:
            _gc.drawImage(mapImgs[cell._type.ordinal()]
                    , cell._x * gridSize, cell._y * gridSize, gridSize, gridSize);
            break;
        }
      }
    }
    // homes
    _gc.drawImage(home, 0, 0, gridSize, gridSize);
    _gc.drawImage(home
            , (GLOBAL.GRID_COLS - 1) * gridSize, (GLOBAL.GRID_ROWS - 1) * gridSize, gridSize, gridSize);
  }
}
