package rugbeats;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class Character_layout extends HBox{
    public Character_layout() {
        getCharacter();
    }
      public void getCharacter(){

          Canvas canvas = new Canvas(300, 250);
          GraphicsContext gc = canvas.getGraphicsContext2D();

          gc.setFill(Color.BLUE);
          gc.fillRect(50,50,100,100);
          this.getChildren().add(canvas);
    };
}
