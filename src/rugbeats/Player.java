package rugbeats;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player {
  private int x = 10;
  private int y = 10;
  private int w = 30;
  private int h = 50;
  private int gridX = 0;// pos in grid
  private int gridY = 0;
  private int cols = 100;
  private int rows = 100;
  private int gridSize = 30;
  private String name;
  //flags
  private boolean operable = true;// can ope while moving & not on beat
  // variables
  private KeyCode upKey = KeyCode.W;
  private KeyCode leftKey = KeyCode.A;
  private KeyCode downKey = KeyCode.S;
  private KeyCode rightKey = KeyCode.D;
  private boolean[] keyLocked = {false, false, false, false}; // u d l r

  Player(String name, int gridX, int gridY) {
    this.name = name;
    this.gridX = gridX;
    this.gridY = gridY;
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
    gc.fillText(name, x, y);
    gc.fillOval(x, y, w, h / 2);
    gc.fillRect(x, y + h / 2, w, h / 2);
  }

  float getDist(int targetX, int targetY) {
    return (float)Math.sqrt(
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

  private boolean insideCheck(int gridx, int gridy) {
    return gridx >= 0 && gridx < cols
            && gridy >= 0 && gridy < rows;
  }

  private void move(char dir) {
    switch (dir) {
      case 'l':
        if (insideCheck(gridX - 1, gridY)) {

          gridX--;
        }
        break;
      case 'r':
        if (insideCheck(gridX + 1, gridY)) {
          gridX++;
        }
        break;
      case 'u':
        if (insideCheck(gridX, gridY - 1)) {
          gridY--;
        }
        break;
      case 'd':
        if (insideCheck(gridX, gridY + 1)) {
          gridY++;
        }
        break;
    }
    x = gridX * gridSize;
    y = gridY * gridSize;
  }
}
