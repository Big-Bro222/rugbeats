package rugbeats;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.util.Arrays;

public class MazeGenerator extends BorderPane {
    private Boolean mousehold;
    private int row;
    private int col;
    private Image pen;
    private GridPane mazegird;
    private BackgroundImage floor1;
    private BackgroundImage floor2;
    private BackgroundImage wall;

    public MazeGenerator(){
        pen=new Image("rugbeats/img/pen.png");
        mousehold=false;
        row=GLOBAL.GRID_ROWS;
        col=GLOBAL.GRID_COLS;
//        row=5;
//        col=7;
        mazegird=new GridPane();
        floor1= new BackgroundImage(new Image("rugbeats/img/floor_1.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        floor2= new BackgroundImage(new Image("rugbeats/img/floor_2.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        wall= new BackgroundImage(new Image("rugbeats/img/wall.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        initalUI();
    }
    public void initalUI(){

         setOnKeyReleased(e->{
             if(e.getCode()==KeyCode.ENTER)
             {GLOBAL.fxinstance.nextScene();}
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
                if((i+j)%2==0)
                {
                btn.setBackground(new Background(floor1));
                }
                else {
                    btn.setBackground(new Background(floor2));
                }
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
                            btn.setBackground(new Background(wall));
                            buttonnum[ro][co]=1;
                        }

                        event.consume();
                    }
                });
                btn.setOnDragExited(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* mouse moved away, remove the graphical cues */

                        event.consume();
                    }
                });
                btn.setOnMousePressed(event -> {
                    if(buttonnum[ro][co]==0){
                        btn.setBackground(new Background(wall));
                        buttonnum[ro][co]=1;
                    }
                    else{

                        if((ro+co)%2==0)
                        {btn.setBackground(new Background(floor1));}
                        else {btn.setBackground(new Background(floor2));}
                        buttonnum[ro][co]=0;
                    }
                });

                mazegird.add( btn, j,i);
                GLOBAL.MAZE_STATE=buttonnum;
            }
        ImageView KeyInstruction= new ImageView(new Image("rugbeats/img/Key_instruction.png"));
        ImageView MouseInstruction= new ImageView(new Image("rugbeats/img/Mouse_instruction.png"));
        HBox instruction= new HBox(MouseInstruction,KeyInstruction);
        instruction.setId("instructions");
        this.setTop(instruction);
        this.setCenter(mazegird);

    }
}
