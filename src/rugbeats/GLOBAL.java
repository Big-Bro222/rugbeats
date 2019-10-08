package rugbeats;

import javafx.scene.paint.Color;

class GLOBAL {
  // Constants
  static final float HW_RATIO=5.0f/9;
  static final int WINDOW_W=1200;
  static final int WINDOW_H= (int) (WINDOW_W*HW_RATIO);
  static final int GRID_SIZE=20;
  static final int GRID_COLS=WINDOW_W/GRID_SIZE;
  static final int GRID_ROWS=WINDOW_H/GRID_SIZE;
  static final Color YELLOW=new Color(249.0/255,195.0/255,44.0/255,1);
  static final Color START_CLR=new Color(37.0/255,37.0/255,37.0/255,1);
  static final Color END_CLR=new Color(45.0/255,199.0/255,255.0/255,1);
  // Global Variables
  static int gTest=3;
}
