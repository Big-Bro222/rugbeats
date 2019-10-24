package rugbeats;

public class CharacterSelectionController {
    private CharacterSelectionModel model;
    public CharacterSelectionController(CharacterSelectionModel model){
     this.model=model;
    }

    public void setCharactermode(){
        model.setCharactermode();
    }
    public void setAppearenceMode(){model.setAppearenceMode();}

    public void up_to_downChangeView(clothChanger clothChanger){
        clothChanger.up_to_downChangeimg();
    }
    public void down_to_upChangeView(clothChanger clothChanger){
        clothChanger.down_to_upChangeimg();
    }
    public void upkeyreleaseView(clothChanger clothChanger){clothChanger.upkeyrelease();}
    public void downkeyreleaseView(clothChanger clothChanger){clothChanger.downkeyrelease();}
    public void setSelectionBorderView(clothChanger clothChanger){clothChanger.setSelectionBorder();}


}
