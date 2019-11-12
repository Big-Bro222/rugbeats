package rugbeats;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Utils {
  /**
   * Interpolate color from c1 to c2 with progress
   *
   * @param c1
   * @param c2
   * @param progress
   * @return
   */
  public static Color colorInterplator(Color c1, Color c2, float progress) {
    if (progress < 0 || progress > 1) {
      throw new Error("progress should in range [0,1]");
    }
    float revProg = 1 - progress;
    return new Color((c2.getRed() * progress + c1.getRed() * revProg)
            , (c2.getGreen() * progress + c1.getGreen() * revProg)
            , (c2.getBlue() * progress + c1.getBlue() * revProg)
            , 1
    );
  }

  public static String hexColor(Color c) {
    return Integer.toHexString((int) (c.getRed() * 255))
            + Integer.toHexString((int) (c.getGreen() * 255))
            + Integer.toHexString((int) (c.getBlue() * 255))
            + Integer.toHexString((int) (c.getOpacity() * 255))
            ;

  }

  public static float clamp(float value, float start, float end) {
    return Math.max(start, Math.min(value, end));
  }

  public static Image imgScale(Image source, int targetWidth, int targetHeight, boolean preserveRatio, boolean smooth) {
    ImageView imageView = new ImageView(source);
    imageView.setPreserveRatio(preserveRatio);
    imageView.setSmooth(smooth);
    imageView.setFitWidth(targetWidth);
    imageView.setFitHeight(targetHeight);
    return imageView.snapshot(null, null);
  }


}
