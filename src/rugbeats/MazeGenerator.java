package rugbeats;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class MazeGenerator extends GridPane {
    private Boolean mousehold;
    private int row;
    private int col;
    public MazeGenerator(){

        mousehold=false;
        row=GLOBAL.GRID_ROWS;
        col=GLOBAL.GRID_COLS;
        initalUI();
    }
    public void initalUI(){

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("Enter Pressed");
                mousehold= true;
            }
        });
        this.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("Enter released");
                mousehold= false;
            }
        });
        for ( int i=0;i<row; ++i)
            for ( int j=0; j<col;++j){
                Button btn = new Button();
                btn.setPrefSize(GLOBAL.GRID_SIZE, GLOBAL.GRID_SIZE);

                btn.setOnMouseEntered(e->{
                    if(mousehold==true)
                    {btn.setStyle("-fx-base:#433434");}
                });
                this.add( btn, j,i);

            }

    }
}
