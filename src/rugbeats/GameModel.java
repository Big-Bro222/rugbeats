package rugbeats;

/**
 * 0 lane
 * 100-Player1 if it's player, then it must be lane
 * 200-Player2
 * <p>
 * 1  horizontal wall
 * 2 vertical wall
 * 3 top-left wall corner
 * 4 top-right wall corner
 * 5 bottom-left wall corner
 * 6 bottom-right wall corner
 */
enum GridType {
  Lane,
  Hole,
  MWall, // middle,
  HWall,
  VWall,
  TLCW,
  TRCW,
  BLCW,
  BRCW,
  Top,
  Bottom
}

class Cell {
  int _x, _y;
  GridType _type;

  Cell(int x, int y, GridType t) {
    _x = x;
    _y = y;
    _type = t;
  }
}

public class GameModel {
  private int _cols = GLOBAL.GRID_COLS;
  private int _rows = GLOBAL.GRID_ROWS;
  Cell[][] mapTable = new Cell[_rows][_cols];
  int[][] _maze = new int[_rows][_cols];
  int chestX = _cols / 2, chestY = _rows / 2;

  GameModel() {
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _cols; j++) {
        _maze[i][j] = 0;
        mapTable[i][j] = new Cell(j, i, GridType.Lane);
      }
    }
  }

  void initGame() {
    chestX = _cols / 2;
    chestY = _rows / 2;
  }

  void calcChestPos() {
    int newX = chestX + (int) (Math.random() * 2 + 2) * (Math.random() > 0.5 ? 1 : -1);
    int newY = chestY + (int) (Math.random() * 2 + 2) * (Math.random() > 0.5 ? 1 : -1);
    if (insideCheck(newX, newY) && !testWall(newX, newY)) {
      chestX = newX;
      chestY = newY;
    } else {
      calcChestPos();
    }
  }

  void calcMap(int[][] maze) {
    _maze = maze;
    for (int r = 0; r < _rows; r++) {
      for (int c = 0; c < _cols; c++) {
        mapTable[r][c] = new Cell(c, r, getWallType(r, c));
      }
    }
    calcChestPos();
  }

  private GridType getWallType(int row, int col) {
    boolean isWall = (_maze[row][col] == 1);
    if (!isWall) {
      return GridType.Lane;
    }
    boolean t = false, d = false, l = false, r = false;
    //y-1
    t = testWall(col, row - 1);
    //y+1
    d = testWall(col, row + 1);
    //x-1
    l = testWall(col - 1, row);
    //x+1
    r = testWall(col + 1, row);
    // middle wall
    if (t && d && l && r) {
      return GridType.VWall;
    }
    // vertical
    else if (t && d) {
      return GridType.VWall;
    }
    // horizontal
    else if (l && r) {
      return GridType.HWall;
    }
    // corner
    else if (r && d && !t && !l) {
      return GridType.TLCW;
    } else if (l && d && !t && !r) {
      return GridType.TRCW;
    } else if (t && l && !d && !r) {
      return GridType.BRCW;
    } else if (t && r && !l && !d) {
      return GridType.BLCW;
    }
    // bottom
    else if (t && !d && !l && !r) {
      return GridType.Bottom;
    }
    //top
    else if (d && !t && !l && !r) {
      return GridType.Top;
    }
    //left right
    else if (l || r) {
      return GridType.HWall;
    }
    // hole
    else {
      System.out.println(col + "  " + row);
      System.out.println("t" + t + "  d" + d + "  l" + l + "  r" + r);
      return GridType.Hole;
    }
  }

  boolean testWall(int gridX, int gridY) {
    return insideCheck(gridX, gridY) && (_maze[gridY][gridX] == 1);
  }

  boolean insideCheck(int gridX, int gridY) {
    return gridX >= 0 && gridX < _cols
            && gridY >= 0 && gridY < _rows;
  }


}
