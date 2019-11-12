package rugbeats;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class AudioManager {
  int musicIndex = 0;
  public static AudioManager _instance;
  long startTime;
  float _elapsedSec;
  Media[] music = new Media[4];
  int[] bpm = {106, 170, 170, 90};
  float[] _err = {0.2f, 0.3f, 0.30f, 0.2f};
  float[] initialOffset = {0.55f, 0, 0, 0};
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
      System.out.println(getClass().getResource("bgm" + (i + 1) + ".mp3"));
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
    mPlayer[musicIndex].setOnEndOfMedia(new Runnable() {
      public void run() {
        mPlayer[musicIndex].seek(Duration.ZERO);
      }
    });
    mPlayer[musicIndex].play();
    startTime = System.nanoTime();
    System.out.println("playing bgm " + musicIndex + " starttime " + startTime);
//    mPlayer[0].
  }

  boolean checkOnBeat() {
    getBeatOffset();
    boolean onBeat = _offset < _err[musicIndex] || _offset > (1 - _err[musicIndex]);
//    System.out.print(onBeat);
//    System.out.println("____" + _offset);
    return onBeat;
  }

  float getBeatOffset() {
    _elapsedSec = (System.nanoTime() - startTime) / 1000000000f;
//    System.out.println("elapsed "+_elapsedSec);
    float oneBeatTime = bpm[musicIndex] / 60f;
    float currentBeat = _elapsedSec * oneBeatTime;
    _offset = (currentBeat % 1 + initialOffset[musicIndex])%1;
    return _offset;
  }
}
