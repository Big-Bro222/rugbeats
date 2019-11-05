package rugbeats;

/**
 0 lane
 100-Player1 if it's player, then it must be lane
 200-Player2

 1  horizontal wall
 2 vertical wall
 3 top-left wall corner
 4 top-right wall corner
 5 bottom-left wall corner
 6 bottom-right wall corner
 *
 */
enum GridType{
  Lane,
  HWall,
  VWall,
  TLCW,
  TRCW,
  BLCW,
  BRCW
}

class Cell{
  int _x,_y;
  GridType _type;
}

public class GameModel {
  int _cols=GLOBAL.gGridCols;
  int _rows=GLOBAL.gGridRows;

  Cell[][] mapTable=new Cell[_rows][_cols];

  void init(){
    // todo load map data from file
    for (int i = 0; i <_rows ; i++) {
      for (int j = 0; j < _cols; j++) {
        mapTable[i][j]._x=j;
        mapTable[i][j]._y=i;
        mapTable[i][j]._type=GridType.Lane;
      }
    }
  }
}
