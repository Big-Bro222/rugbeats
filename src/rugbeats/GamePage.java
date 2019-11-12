package rugbeats;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GamePage {
  private Main _app;
  private Canvas _canvas =new Canvas();
  private GameModel _model=new GameModel();
  private Scene _scene;
  private Pane _root =new Pane();
  public GameController _controller= new GameController();

  GamePage(Main app){
    _app=app;
    init();

  }

  void init(){
    System.out.println("init game page");
    //init ui
    _root.getChildren().add(_canvas);
    _canvas.widthProperty().bind(_root.widthProperty());
    _canvas.heightProperty().bind(_root.heightProperty());
    _scene = new Scene(_root, GLOBAL.WINDOW_W, GLOBAL.WINDOW_H);// preferred geometry

    _controller.bindCanvas(_canvas);
    _controller.bindModel(_model);
    _controller.bindScene(_scene);
  }
  Scene getScene(){
    return _scene;
  }
  void initGame(){
    _controller.initGame();
  }

}
