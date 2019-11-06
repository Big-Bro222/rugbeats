package rugbeats.animation;

import java.util.HashMap;

enum AnimName {
  Kid1,
  Kid2,
  Soldier1,
  Soldier2,
  Mage1,
  Mage2,
  Dinosaur1,
  Dinosaur2
}

/**
 * Use this Class to load resources for all animation
 * All animation should also be accessed through this Class
 **/
public class AniManager {
  HashMap<AnimName, FrameAnim> _anims = new HashMap<AnimName, FrameAnim>();

}
