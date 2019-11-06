package rugbeats;

public class CharacterSelectionController {
    private CharacterSelectionModel model;
    public CharacterSelectionController(CharacterSelectionModel model){
     this.model=model;
    }

    public void setPlayerStates(int Playerindex){model.setPlayerStates(Playerindex);}



}
