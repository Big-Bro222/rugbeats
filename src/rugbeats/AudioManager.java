package rugbeats;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {
  int musicIndex = 0;
  public static AudioManager _instance;
  long startTime;
  float _elapsedSec;
  Media[] music = new Media[4];
  int[] bpm = {106, 170, 170, 90};
  float[] _err = {0.3f, 0.2f, 0.25f, 0.18f};
  int musicLen = 4;
  float _offset;
  MediaPlayer[] mPlayer = new MediaPlayer[4];

  private AudioManager() {
  }

  public static synchronized AudioManager getInstance() {
    if (_instance == null) {
      _instance = new AudioManager();
    }
    return _instance;
  }

  public int getCurrentBPM() {
    return bpm[musicIndex];
  }

  void loadMusic() {
    for (int i = 0; i < music.length; i++) {
      music[i] = null;
      mPlayer[i] = null;
      music[i] = new Media(getClass().getResource("bgm" + (i + 1) + ".mp3").toExternalForm());
      mPlayer[i] = new MediaPlayer(music[i]);
    }
  }

  void play(int index) {
    musicIndex = index;
    play();
  }

  void play() {
    for (int i = 0; i < musicLen; i++) {
      mPlayer[i].stop();
    }
    mPlayer[musicIndex].play();
    startTime = System.nanoTime();
    System.out.println("playing bgm " + musicIndex+" starttime "+startTime);
  }

  boolean checkOnBeat() {
    getBeatOffset();
    boolean onBeat = _offset < _err[musicIndex] || _offset > (1 - _err[musicIndex]);
    System.out.print(onBeat);
    System.out.println("____" + _offset);
    return onBeat;
  }

  float getBeatOffset() {
    _elapsedSec = (System.nanoTime() - startTime) / 1000000000f;
    System.out.println("elapsed "+_elapsedSec);
    float currentBeat = _elapsedSec * bpm[musicIndex] / 60f;
    _offset = currentBeat % 1;
    return _offset;
  }
}
