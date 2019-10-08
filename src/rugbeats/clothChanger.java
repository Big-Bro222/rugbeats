package rugbeats;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


import java.util.ArrayList;

public class clothChanger {

    private VBox p1_hat;
    private HBox c;
    private ImageView currentimg;
    private int index;
    private EventHandler changeimg1;

    public clothChanger(){
        super();
        p1_hat= new VBox();
        c= new HBox();
        currentimg= new ImageView();
        index=0;


    }

    public VBox getclothbox(ImageView img1,ImageView img2,ImageView img3){
        ArrayList<ImageView> hatimg= new ArrayList<>();
        hatimg.add(img1);
        hatimg.add(img2);
        hatimg.add(img3);
        StackPane p1_hatimg =new StackPane(hatimg.get(0));

        int imgsize=100;
        img1.setFitHeight(imgsize);
        img1.setFitWidth(imgsize);
        img2.setFitHeight(imgsize);
        img2.setFitWidth(imgsize);
        img3.setFitHeight(imgsize);
        img3.setFitWidth(imgsize);
        Group g = new Group();
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                0.0, 40.0,
                100.0, 40.0,
                50.0, 0.0 });
        Group ggg = new Group();
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                0.0, 40.0,
                100.0, 40.0,
                50.0, 0.0 });
        triangle.setSmooth(true);
        triangle.setRotate(180);

        ggg.setOnMouseExited(
                event -> {
                    triangle.setFill(Color.BLACK);}
        );
        ggg.setOnMouseClicked(event -> {
            down_to_upChangeimg(p1_hatimg,hatimg);
        });
        ggg.setOnMouseEntered(
                event -> {
                    triangle.setFill(Color.RED);}
        );
        g.setOnMouseEntered(
                event -> {
                    polygon.setFill(Color.RED);}
        );
        g.setOnMouseExited(
                event -> {
                    polygon.setFill(Color.BLACK);}
        );
        g.setOnMouseClicked(event -> {
            up_to_downChangeimg(p1_hatimg,hatimg);
            currentimg=img1;
        });
        ggg.getChildren().add(triangle);
        g.getChildren().add(polygon);
        p1_hat= new VBox(g,p1_hatimg,ggg);

        return p1_hat;
    }
    public HBox getCharacter(){
        c= new HBox();

        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.fillRect(50,50,100,100);
        gc.drawImage(currentimg.getImage(),0,0);
        c.getChildren().add(canvas);
        return  c;
    };


    public void up_to_downChangeimg(StackPane p1_hatimg,ArrayList<ImageView> hatimg) {
        p1_hatimg.getChildren().clear();
        System.out.println("change view");
        ImageView index0= hatimg.get(0);
        ImageView index1= hatimg.get(1);
        ImageView index2= hatimg.get(2);
        hatimg.set(0,index1);
        hatimg.set(1,index2);
        hatimg.set(2,index0);

        p1_hatimg.getChildren().addAll(hatimg.get(0));

    }
    public void down_to_upChangeimg(StackPane p1_hatimg,ArrayList<ImageView> hatimg) {
        p1_hatimg.getChildren().clear();
        System.out.println("change view");
        ImageView index0= hatimg.get(0);
        ImageView index1= hatimg.get(1);
        ImageView index2= hatimg.get(2);
        hatimg.set(0,index2);
        hatimg.set(1,index0);
        hatimg.set(2,index1);

        p1_hatimg.getChildren().addAll(hatimg.get(0));

    }

}
