import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane; 

public class App extends Application {

    @Override
    public void start(Stage primaryStage){
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5, 5, 5, 5));

        pane.setVgap(5); 
        pane.setHgap(5); 
        Image image1 = new Image("Images/flag1.gif");
        Image image2 = new Image("Images/flag2.gif");
        Image image3 = new Image("Images/flag6.gif");
        Image image4 = new Image("Images/flag7.gif");

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);

        imageView1.setFitHeight(100);
        imageView1.setFitWidth(200);
        pane.add(imageView1, 0, 0);
    
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(200);
        pane.add(imageView2, 1, 0); 
        
        imageView3.setFitHeight(100);
        imageView3.setFitWidth(200);
        pane.add(imageView3, 0, 1); 

        imageView4.setFitHeight(100);
        imageView4.setFitWidth(200);
        pane.add(imageView4, 1, 1);     
    
    
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowImage"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 

    }
    public static void main(String[] args) {
        launch(args);
    }
}
