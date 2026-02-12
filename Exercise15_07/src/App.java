import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {
  @Override 
  public void start(Stage primaryStage) {
    //creating the circle
    Circle circle = new Circle();
    circle.setCenterX(100);
    circle.setCenterY(100);
    circle.setRadius(50);
    circle.setStroke(Color.BLACK); 
    circle.setFill(Color.WHITE);
    
    // creating the pane
    Pane pane = new Pane();
    pane.getChildren().add(circle);
    
    // creating the scene
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setScene(scene); 
    primaryStage.show(); 

    //change color to black when mouse is clicked
    pane.setOnMousePressed(e -> {
        circle.setFill(Color.BLACK);
    });

    //change color to white when mouse is released
    pane.setOnMouseReleased(e -> {
        circle.setFill(Color.WHITE);
    });
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}