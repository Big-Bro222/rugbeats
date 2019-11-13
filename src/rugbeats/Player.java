package rugbeats;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import rugbeats.animation.AniManager;
import rugbeats.animation.AnimName;

public class Player {
  private int x = 10;
  private int y = 10;
  private int w = 30;
  private int h = 50;
  private int gridX = 0;// pos in grid
  private int gridY = 0;
  private int cols = GLOBAL.GRID_COLS;
  private int rows = GLOBAL.GRID_ROWS;
  private int gridSize = GLOBAL.GRID_SIZE;
  private float blockTime=0f;
  private String name;
  private AnimName animName;
  private int weaponIndex = 0;
  //flags
  private boolean _operable = true;// can ope while moving & not on beat
  // variables
  private KeyCode upKey = KeyCode.W;
  private KeyCode leftKey = KeyCode.A;
  private KeyCode downKey = KeyCode.S;
  private KeyCode rightKey = KeyCode.D;
  private boolean[] keyLocked = {false, false, false, false}; // u d l r
  private float lastDrawTime = 0;
  private float[] missOpacity = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  private boolean freeMode = false;
  private boolean hasChest = false;
  private GameModel model;
  private GameController _controller;


  public void bindModel(GameModel m) {
    model = m;
  }

  void bindController(GameController c) {
    _controller = c;
  }

  public void setFreeMode(boolean freeMode) {
    this.freeMode = freeMode;
  }

  public void setOperable(boolean o) {
    _operable = o;
  }

  Player(String name, int gridX, int gridY) {
    this.name = name;
    this.gridX = gridX;
    this.gridY = gridY;
    animName = AnimName.Big;
    updateScreenPos();
  }

  void setAnim(int index) {
   animName= AniManager.getInstance().getAnimName(index);
  }

  void setWeapon(int index) {
    weaponIndex = index;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  void setKeys(KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
    upKey = up;
    downKey = down;
    leftKey = left;
    rightKey = right;
  }

  void draw(GraphicsContext gc) {

    gc.setFill(new Color(1, 1, 1, 1));
    gc.fillText(name, x, y + 7 + gridSize * GLOBAL.PLAYER_SCALE);
    gc.drawImage(AniManager.getInstance().getFrame(animName), x, y, gridSize * GLOBAL.PLAYER_SCALE, gridSize * GLOBAL.PLAYER_SCALE);
    gc.drawImage(AniManager.getInstance().getWeapon(weaponIndex), x, y);
    drawMiss(gc);
  }

  void drawMiss(GraphicsContext gc) {
    if (AudioManager.getInstance()._elapsedSec < lastDrawTime) {
      lastDrawTime = AudioManager.getInstance()._elapsedSec;
    }
    float deltaTime = AudioManager.getInstance()._elapsedSec - lastDrawTime;
    if (blockTime>0){
      blockTime-=deltaTime;
    }
//    System.out.println(deltaTime);
    for (int i = 0; i < missOpacity.length; i++) {
      missOpacity[i] -= deltaTime;
      if (missOpacity[i] < 0) {
        missOpacity[i] = 0;
      }
      if (missOpacity[i] != 0) {
        gc.setFill(new Color(1, 1, 1, missOpacity[i]));
        gc.fillText("MISS", x, y - (1 - missOpacity[i]) * 20);
      }
    }
    lastDrawTime = AudioManager.getInstance()._elapsedSec;
  }

  float getDist(int targetX, int targetY) {
    return (float) Math.sqrt(
            (targetX - x) * (targetX - x)
                    + (targetY - y) * (targetY - y)
    );
  }

  void handleKey(KeyEvent evt) {
    KeyCode code = evt.getCode();
    if (evt.getEventType() == KeyEvent.KEY_RELEASED) {
      if (code == upKey) {
        keyLocked[0] = false;
      } else if (code == downKey) {
        keyLocked[1] = false;
      } else if (code == leftKey) {
        keyLocked[2] = false;
      } else if (code == rightKey) {
        keyLocked[3] = false;
      }
    } else {
      if (code == upKey && !keyLocked[0]) {
        move('u');
        keyLocked[0] = true;
      } else if (code == downKey && !keyLocked[1]) {
        move('d');
        keyLocked[1] = true;
      } else if (code == leftKey && !keyLocked[2]) {
        move('l');
        keyLocked[2] = true;
      } else if (code == rightKey && !keyLocked[3]) {
        move('r');
        keyLocked[3] = true;
      }
    }
  }

  private boolean moveCheck(int gridx, int gridy) {
    if (freeMode) {
      return gridx >= 0 && gridx < cols
              && gridy >= 0 && gridy < rows;
    } else {
      boolean inside = model.insideCheck(gridx, gridy);
      boolean isWall = model.testWall(gridx, gridy);
      boolean notOther = true;
      System.out.println("--------");
      System.out.println(inside);
      System.out.println(isWall);
      System.out.println(notOther);
      if (_controller._p2.name == name) {
        notOther = _controller._p1.gridX != gridx || _controller._p1.gridY != gridy;
        // random place chest
        if (!notOther) {
          if (_controller._p1.hasChest) {
            _controller._p1.hasChest = false;
            model.calcChestPos();
          }
        }
      }
      if (_controller._p1.name == name) {
        notOther = _controller._p2.gridX != gridx || _controller._p2.gridY != gridy;
        // random place chest
        if (!notOther) {
          if (_controller._p2.hasChest) {
            _controller._p2.hasChest = false;
            model.calcChestPos();
          }
        }
      }
      System.out.println(notOther);

      return notOther && inside && (!isWall);
    }
  }

  private void updateScreenPos() {
    x = gridX * gridSize + (int) (0.2f * gridSize);
    y = gridY * gridSize + (int) (0.2f * gridSize);
  }

  private void addMiss() {
    blockTime+=0.3f;
    for (int i = 0; i < missOpacity.length; i++) {
      if (missOpacity[i] == 0) {
        missOpacity[i] = 1;
        return;
      }
    }
  }

  private void move(char dir) {
    if (!AudioManager.getInstance().checkOnBeat()) {
      addMiss();
      return;
    }
    if (blockTime>0){
      return;
    }
    switch (dir) {
      case 'l':
        if (moveCheck(gridX - 1, gridY)) {
          gridX--;
        }
        break;
      case 'r':
        if (moveCheck(gridX + 1, gridY)) {
          gridX++;
        }
        break;
      case 'u':
        if (moveCheck(gridX, gridY - 1)) {
          gridY--;
        }
        break;
      case 'd':
        if (moveCheck(gridX, gridY + 1)) {
          gridY++;
        }
        break;
    }
    if (!freeMode) {
      if (gridX == model.chestX && gridY == model.chestY) {
        hasChest = true;
      }
      if (hasChest) {
        model.chestX = gridX;
        model.chestY = gridY;
      }
    }
    updateScreenPos();
    if (hasChest) {
      if (_controller._p1.name == name) {
        if (gridX == cols - 1 && gridY == rows - 1) {
          _controller._app.nextScene();
          GLOBAL.WINNER=1;
        }
      }
      if (_controller._p2.name == name) {
        if (gridX == 0 && gridY == 0) {
          _controller._app.nextScene();
          GLOBAL.WINNER=2;
        }
      }
    }
  }
}
