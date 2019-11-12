package rugbeats.animation;

import javafx.scene.image.Image;

public class FrameAnim {
  Image[] _frames;
  private float _duration;

  //  public FrameAnim(float a) {
//    _duration = a;
//  }
  public FrameAnim(Image[] f, float d) {
    _frames = f;
    _duration = d;
  }

  public void setDuration(float dur) {
    _duration = dur;
  }

  public Image getFrame(float time) {
    return _frames[(int) ((time % _duration) * _frames.length / _duration)];
  }

  public int testFrame(float t) {
    return (int) ((t % _duration) * _frames.length / _duration);
  }

  public double getDuration() {
    return _duration;
  }
}
