package rugbeats;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


import java.util.ArrayList;

public class clothChanger extends VBox{

    private VBox p1_hat;
    private HBox c;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private int index;
    private EventHandler changeimg1;
    private StackPane p1_hatimg;
    private ArrayList <ImageView> hatimg;
    private Polygon triangle;
    private ImageView uparrow;
    private ImageView downarrow;
    private Image uparrows_clicked;
    private Image downarrows_clicked;
    private Image uparrows;
    private Image downarrows;
    public clothChanger(ImageView img1,ImageView img2,ImageView img3) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        uparrows_clicked=new Image("rugbeats/img/uparrow_clicked.png");
        downarrows_clicked=new Image("rugbeats/img/downarrow_clicked.png");
        uparrows=new Image("rugbeats/img/uparrow.png");
        downarrows=new Image("rugbeats/img/downarrow.png");
        getclothbox(img1,img2,img3);
        clicked();
    }

    //generate the switch bar for the character
    //To do:change the mouseclick event into key event.
    public void getclothbox(ImageView img1,ImageView img2,ImageView img3){
        hatimg= new ArrayList<>();
        hatimg.add(img1);
        hatimg.add(img2);
        hatimg.add(img3);
        p1_hatimg =new StackPane(hatimg.get(0));

        int imgsize=100;
        img1.setFitHeight(imgsize);
        img1.setFitWidth(imgsize);
        img2.setFitHeight(imgsize);
        img2.setFitWidth(imgsize);
        img3.setFitHeight(imgsize);
        img3.setFitWidth(imgsize);
        uparrow= new ImageView();
        uparrow.setImage(uparrows);
        uparrow.setPreserveRatio(true);
        uparrow.setFitWidth(100);
        Button triangleup= new Button("",uparrow);
        triangleup.setStyle("-fx-background-insets:0.0;"+"-fx-background-color: transparent;");


        downarrow= new ImageView();
        downarrow.setImage(downarrows);
        downarrow.setPreserveRatio(true);
        downarrow.setFitWidth(100);
        Button triangledown= new Button("",downarrow);
        triangledown.setStyle("-fx-background-insets:0.0;"+"-fx-background-color: transparent;");


        this.getChildren().addAll(triangleup,p1_hatimg,triangledown);


    }

//up button hold
    public void up_to_downChangeimg() {
        p1_hatimg.getChildren().clear();
        ImageView index0= hatimg.get(0);
        ImageView index1= hatimg.get(1);
        ImageView index2= hatimg.get(2);
        hatimg.set(0,index1);
        hatimg.set(1,index2);
        hatimg.set(2,index0);
        uparrow.setImage(uparrows_clicked);
        p1_hatimg.getChildren().addAll(hatimg.get(0));

    }
    public void clicked() {
        System.out.println("success");
    }
//down button hold
    public void down_to_upChangeimg() {
        p1_hatimg.getChildren().clear();
        System.out.println("change view");
        ImageView index0= hatimg.get(0);
        ImageView index1= hatimg.get(1);
        ImageView index2= hatimg.get(2);
        hatimg.set(0,index2);
        hatimg.set(1,index0);
        hatimg.set(2,index1);
        downarrow.setImage(downarrows_clicked);
        p1_hatimg.getChildren().addAll(hatimg.get(0));

    }
    public void upkeyrelease(){
        uparrow.setImage(uparrows);
    }
    public void downkeyrelease(){
        downarrow.setImage(downarrows);
    }

    public void setSelectionBorder(){
        p1_hatimg.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
    }

}
