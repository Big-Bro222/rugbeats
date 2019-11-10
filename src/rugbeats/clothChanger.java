package rugbeats;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import java.util.Arrays;
import java.util.List;

public class clothChanger extends VBox{

    private StackPane ImgStack;
    private List<Image> ImgList;
    private ImageView uparrow;
    private ImageView downarrow;
    private Image uparrow_clicked;
    private Image downarrow_clicked;
    private Image uparrowImg;
    private Image downarrowImg;
    private ImageView ImgStackView;
    int imgsize;
    public clothChanger(List<Image> ImgList) {
        imgsize=70;
        this.ImgList=ImgList;
        uparrow_clicked=new Image("rugbeats/img/Up_arrow_Clickedhdpi.png");
        downarrow_clicked=new Image("rugbeats/img/Down_arrow_Clickedhdpi.png");
        uparrowImg=new Image("rugbeats/img/Up_arrow_Unclickedhdpi.png");
        downarrowImg=new Image("rugbeats/img/Down_arrow_Unclickedhdpi.png");
        getclothbox(ImgList);
    }

    //generate the switch bar for the character
    //To do:change the mouseclick event into key event.
    public void getclothbox(List<Image> ImgList){

        ImgStackView=new ImageView(ImgList.get(0));
        ImgStackView.setFitHeight(imgsize);
        ImgStackView.setPreserveRatio(true);

        ImgStack =new StackPane(ImgStackView);
        ImgStack.setPadding(new Insets(20,0,20,0));
       // ImgStack.setPrefWidth(imgsize);




        uparrow= new ImageView();
        uparrow.setImage(uparrowImg);
        uparrow.setPreserveRatio(true);
        uparrow.setFitWidth(150);
        Button triangleup= new Button("",uparrow);
        triangleup.setStyle("-fx-background-insets:0.0;"+"-fx-background-color: transparent;");


        downarrow= new ImageView();
        downarrow.setImage(downarrowImg);
        downarrow.setPreserveRatio(true);
        downarrow.setFitWidth(150);
        Button triangledown= new Button("",downarrow);
        triangledown.setStyle("-fx-background-insets:0.0;"+"-fx-background-color: transparent;");

        this.getChildren().addAll(triangleup,ImgStack,triangledown);
    }

    public void PressDownChangeimg() {
        ImgStack.getChildren().clear();
        List<Image> ImgstorageList=ImgList;
        Image TemporaryImg= ImgList.get(0);
        for(int i=0;i<ImgList.size()-1;i++){
               ImgstorageList.set(i,ImgList.get(i+1));
        }
        ImgstorageList.set(ImgList.size()-1,TemporaryImg);

        ImgList=ImgstorageList;
        ImgStackView=new ImageView(ImgList.get(0));
        ImgStackView.setFitHeight(imgsize);
        ImgStackView.setPreserveRatio(true);
        ImgStack.getChildren().add(ImgStackView);

        downarrow.setImage(downarrow_clicked);
    }

    public void PressUpChangeimg() {
        ImgStack.getChildren().clear();
        List<Image> ImgstorageList=ImgList;
        Image TemporaryImg= ImgList.get(ImgList.size()-1);
        for(int i=ImgList.size()-1;i>0;i--){
            ImgstorageList.set(i,ImgList.get(i-1));
        }
        ImgstorageList.set(0,TemporaryImg);

        ImgList=ImgstorageList;
        ImgStackView=new ImageView(ImgList.get(0));
        ImgStackView.setFitHeight(imgsize);
        ImgStackView.setPreserveRatio(true);
        ImgStack.getChildren().add(ImgStackView);

        uparrow.setImage(uparrow_clicked);
    }

    public void ReleaseUpChangeimg(){
        uparrow.setImage(uparrowImg);
    }
    public void ReleaseDownChangeimg(){
        downarrow.setImage(downarrowImg);
    }
    public void setSelectionBorder(){
        BackgroundImage bgselected= new BackgroundImage(new Image("rugbeats/img/Backgroundhdpi.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        ImgStack.setBackground(new Background(bgselected));
        //ImgStack.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
    }
    public void cancelSelectionBorder(){
        //ImgStack.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
        BackgroundImage bgselected= new BackgroundImage(new Image("rugbeats/img/Background_selectedhdpi.png"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        ImgStack.setBackground(new Background(bgselected));
    }

    public ImageView getImg()
    {
        return ImgStackView;
    }



}
