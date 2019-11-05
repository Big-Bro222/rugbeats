package rugbeats;

public class CharacterSelectionModel {
    private int Charactermode;
    private int AppearenceModep1;
    private int AppearenceModep2;
    CharacterSelectionModel(){
         Charactermode=0;
        AppearenceModep1=0;
        AppearenceModep2=0;


    }
    public void setCharactermode(){
        if(Charactermode<3){Charactermode++;}
        else{Charactermode=0;}
    }
    public void setAppearenceModep1(){
        if(AppearenceModep1<1){AppearenceModep1++;}
        else{AppearenceModep1=0;}
        System.out.println(AppearenceModep1);
    }
    public int getAppearenceModep1(){
        return AppearenceModep1;
    }
    public void setAppearenceModep2(){
        if(AppearenceModep2<1){AppearenceModep2++;}
        else{AppearenceModep2=0;}
        System.out.println(AppearenceModep2);
    }
    public int getAppearenceModep2(){
        return AppearenceModep2;
    }

    public int getCharactermode(){
        return Charactermode;
    }
}
