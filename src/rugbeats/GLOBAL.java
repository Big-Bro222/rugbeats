package rugbeats;

import javafx.scene.paint.Color;

class GLOBAL {
  // Constants
  static final float HW_RATIO = 5.0f / 9;
  static final int GRID_SIZE = 20;
  static final Color YELLOW = new Color(249.0 / 255, 195.0 / 255, 44.0 / 255, 1);
  static final Color START_CLR = new Color(37.0 / 255, 37.0 / 255, 37.0 / 255, 1);
  static final Color END_CLR = new Color(45.0 / 255, 199.0 / 255, 255.0 / 255, 1);

  // Global Variables
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
