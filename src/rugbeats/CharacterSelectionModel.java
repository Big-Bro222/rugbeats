package rugbeats;

public class CharacterSelectionModel {
  int p1 = 0, p1w = 0, p2 = 0, p2w = 0;
  int pLen = 6, wLen = 6;
  private int[] PlayerStates;

  CharacterSelectionModel() {
    PlayerStates = new int[]{0, 0};
  }

  int[] getPlayerStates() {
    return PlayerStates;
  }


  void setPlayerStates(int Playerindex) {
    System.out.println("model running");
    if (PlayerStates[Playerindex] == 0) {
      PlayerStates[Playerindex] = 1;
    } else {
      PlayerStates[Playerindex] = 0;
    }
  }

}
