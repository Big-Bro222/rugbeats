package rugbeats.animation;

import javafx.scene.image.Image;

public class FrameAnim {
  Image[] _frames;
  double _duration;

  public FrameAnim(int a) {
    _duration = a;
  }

  public FrameAnim(Image[] f, double d) {
    _frames = f;  // 什么时候用 this???
    _duration = d;
  }

  public Image getFrame(double time) {
    return _frames[(int) ((time % _duration) * _frames.length / _duration)];
  }

  public double getDuration() {
    return _duration;
  }
}
