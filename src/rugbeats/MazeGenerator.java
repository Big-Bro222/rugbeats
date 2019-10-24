package rugbeats;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class MazeGenerator extends GridPane {
    private Boolean mousehold;
    private int row;
    private int col;
    public MazeGenerator(){

        mousehold=false;
//        row=GLOBAL.GRID_ROWS;
//        col=GLOBAL.GRID_COLS;
        row=5;
        col=7;
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
        int[][] buttonnum = new int [row][col];

        for (int i=0;i<row; ++i)
            for (int j=0; j<col;++j) {


                buttonnum[i][j]=0;
            }

        for(int i=0; i<row; ++i){
            System.out.println(Arrays.toString(buttonnum[i]));
        }

        System.out.println(buttonnum);

        for ( int i=0;i<row; ++i)
            for ( int j=0; j<col;++j){
                Button btn = new Button();
                btn.setPrefSize(GLOBAL.GRID_SIZE, GLOBAL.GRID_SIZE);
                int ro=i;
                int co=j;
                btn.setOnMouseEntered(e->{
                    if(mousehold==true)
                    {btn.setStyle("-fx-base:#433434");
                     buttonnum[ro][co]=1;


//                        for(int o=0; o<row; ++o){
//                            System.out.println(Arrays.toString(buttonnum[o])); }
                    }
                });
                this.add( btn, j,i);
                GLOBAL.MAZE_STATE=buttonnum;
            }


    }
}
