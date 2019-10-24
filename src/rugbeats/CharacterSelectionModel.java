package rugbeats;

public class CharacterSelectionModel {
    private int Charactermode;
    private int AppearenceMode;
    CharacterSelectionModel(){
         Charactermode=0;
        AppearenceMode=0;
    }

    public void setCharactermode(){
        if(Charactermode<3){Charactermode++;}
        else{Charactermode=0;}
    }
    public void setAppearenceMode(){
        if(AppearenceMode<1){AppearenceMode++;}
        else{AppearenceMode=0;}
        System.out.println(AppearenceMode);
    }
    public int getAppearenceMode(){
        return AppearenceMode;
    }

    public int getCharactermode(){
        return Charactermode;
    }
}
