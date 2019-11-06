package rugbeats;

public class CharacterSelectionModel {
    private int []PlayerStates;
    CharacterSelectionModel(){
        PlayerStates= new int[]{0, 0};

    }
    int[] getPlayerStates(){
        return PlayerStates;
    }

    void setPlayerStates(int Playerindex){
        System.out.println("model running");
         if(PlayerStates[Playerindex]==0){
             PlayerStates[Playerindex]=1;
         }
         else{PlayerStates[Playerindex]=0;}
    }

}
