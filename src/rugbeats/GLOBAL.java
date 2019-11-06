package rugbeats;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


class GLOBAL {
  // Constants
  static final float HW_RATIO=5.0f/9;
  static final int WINDOW_W=1200;
  static final int WINDOW_H= (int) (WINDOW_W*HW_RATIO);
  static final int GRID_SIZE=50;
  static final int GRID_COLS=WINDOW_W/GRID_SIZE;
  static final int GRID_ROWS=WINDOW_H/GRID_SIZE;
  static final Color YELLOW=new Color(249.0/255,195.0/255,44.0/255,1);
  static final Color START_CLR=new Color(37.0/255,37.0/255,37.0/255,1);
  static final Color END_CLR=new Color(45.0/255,199.0/255,255.0/255,1);
<<<<<<< HEAD
  static int MAZE_STATE[][];
  static List<ImageView> gPlayer1Img= new ArrayList<>(2);
  static List<ImageView> gPlayer2Img= new ArrayList<>(2);
=======

>>>>>>> 21a61c9e5f7bdafd46c09e71fa51c99c9ef61363
  // Global Variables
  static int gTest=3;

  static Main fxinstance;
  static int MAZE_STATE[][];
  static int gWindowW = 1200;
  static int gWindowH = (int) (gWindowW * HW_RATIO);
  static int gGridCols = gWindowW / GRID_SIZE;
  static int gGridRows = gWindowH / GRID_SIZE;

  public static void updateParams() {
    gWindowH = (int) (gWindowW * HW_RATIO);
    gGridCols = gWindowW / GRID_SIZE;
    gGridRows = gWindowH / GRID_SIZE;
  }
}
