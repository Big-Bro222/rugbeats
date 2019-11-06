package rugbeats;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class MazeGenerator extends GridPane {
    private Boolean mousehold;
    private int row;
    private int col;
    private Image pen;
    public MazeGenerator(){
        pen=new Image("rugbeats/img/pen.png");
        mousehold=false;
        row=GLOBAL.GRID_ROWS;
        col=GLOBAL.GRID_COLS;
//        row=5;
//        col=7;
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


        for ( int i=0;i<row; ++i)
            for ( int j=0; j<col;++j){
                Button btn = new Button();
                btn.setPrefSize(GLOBAL.GRID_SIZE, GLOBAL.GRID_SIZE);
                int ro=i;
                int co=j;

                btn.setOnDragDetected(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        /* drag was detected, start a drag-and-drop gesture*/
                        /* allow any transfer mode */
                        Dragboard db = btn.startDragAndDrop(TransferMode.ANY);

                        /* Put a string on a dragboard */
                        ClipboardContent content = new ClipboardContent();
                        content.putImage(pen);
                        db.setContent(content);

                        event.consume();
                    }
                });

                btn.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the target */
                        /* accept it only if it is not dragged from the same node
                         * and if it has a string data */
                        if (event.getGestureSource() != btn &&
                                event.getDragboard().hasImage()) {
                            /* allow for both copying and moving, whatever user chooses */
                            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        }

                        event.consume();
                    }
                });
                btn.setOnDragEntered(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* the drag-and-drop gesture entered the target */
                        /* show to the user that it is an actual gesture target */
                        if (event.getGestureSource() != btn &&
                                event.getDragboard().hasImage()) {
                            btn.setStyle("-fx-base:orange;");
                            buttonnum[ro][co]=1;
                        }

                        event.consume();
                    }
                });
                btn.setOnDragExited(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* mouse moved away, remove the graphical cues */

<<<<<<< HEAD
                        event.consume();
=======
                        for(int o=0; o<row; ++o){
                           System.out.println(Arrays.toString(buttonnum[o])); }
>>>>>>> 21a61c9e5f7bdafd46c09e71fa51c99c9ef61363
                    }
                });
                btn.setOnMousePressed(event -> {
                    if(buttonnum[ro][co]==0){
                        btn.setStyle("-fx-base:orange;");
                        buttonnum[ro][co]=1;
                    }
                    else{btn.setStyle("-fx-base:white;");
                        buttonnum[ro][co]=0;
                    }
                });

                this.add( btn, j,i);
                GLOBAL.MAZE_STATE=buttonnum;
            }


    }
}
