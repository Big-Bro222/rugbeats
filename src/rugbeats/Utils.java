package rugbeats;

import javafx.scene.paint.Color;

public class Utils {
  public static Color colorInterplator(Color c1, Color c2, float progress) {
    if (progress < 0 || progress > 1) {
      throw new Error("progress should in range [0,1]");
    }
    float revProg = 1 - progress;
    return new Color((c1.getRed() * progress + c2.getRed() * revProg)
            , (c1.getGreen() * progress + c2.getGreen() * revProg)
            , (c1.getBlue() * progress)
            ,1
    );
  }
}
