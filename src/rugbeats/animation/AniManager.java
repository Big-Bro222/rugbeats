package rugbeats.animation;

import javafx.scene.image.Image;

import java.util.HashMap;


/**
 * Use this Class to load resources for all animation
 * All animation should also be accessed through this Class
 **/
public class AniManager {
  private HashMap<AnimName, FrameAnim> _anims;
  //singleton
  private static AniManager _instance;
  private long _startTime;
  private Image[] _weapons = new Image[6];

  private AniManager() {
    _startTime = System.nanoTime();
  }

  private Image[] animLoader(String name, int len) {
    Image[] l = new Image[len];
    for (int i = 0; i < len; i++) {
      l[i] = new Image("rugbeats/img/hero/" + name + (i + 1) + ".png");
    }
    return l;
  }

  public static synchronized AniManager getInstance() {
    if (_instance == null) {
      _instance = new AniManager();
    }
    return _instance;
  }
public Image getWeapon(int i){
    return _weapons[i];
}
  public void loadImages() {
    _anims = new HashMap<AnimName, FrameAnim>();
    _anims.put(AnimName.Big, new FrameAnim(animLoader("big", 4), 0.5f));
    _anims.put(AnimName.Wiz, new FrameAnim(animLoader("wiz", 4), 0.5f));
    _anims.put(AnimName.Wood, new FrameAnim(animLoader("wood", 4), 0.5f));
    _anims.put(AnimName.Knight, new FrameAnim(animLoader("knight", 4), 0.5f));
    _anims.put(AnimName.Man, new FrameAnim(animLoader("man", 4), 0.5f));
    _anims.put(AnimName.Ninja, new FrameAnim(animLoader("ninja", 4), 0.5f));
    for (int i = 0; i < 6; i++) {
      _weapons[i]=new Image("rugbeats/img/w"+(i+1)+".png");
    }
  }

  public AnimName getAnimName(int index){
    AnimName name;
    switch (index) {
      case 0:
        name = AnimName.Big;
        break;
      case 1:
        name = AnimName.Knight;
        break;
      case 2:
        name = AnimName.Man;
        break;
      case 3:
        name = AnimName.Ninja;
        break;
      case 4:
        name = AnimName.Wood;
        break;
      case 5:
        name = AnimName.Wiz;
        break;
      default:
        name = AnimName.Big;
        break;
    }
    return name;
  }

  public Image getFrame(AnimName name) {
    return _anims.get(name).getFrame((System.nanoTime() - _startTime) / 1000000000.0f);
  }
}
