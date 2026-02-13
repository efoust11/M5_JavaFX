import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        //creating text and adjusting font
        Text text = new Text(50, 50, "Show Colors");
        text.setFont(new Font("SansSerif", 16));

        //creating color sliders
        Slider sliderGreen = new Slider(0, 255, 0);
        sliderGreen.setShowTickMarks(true);
        sliderGreen.setShowTickLabels(true);
        sliderGreen.setMajorTickUnit(51);

        Slider sliderRed = new Slider(0, 255, 0);
        sliderRed.setShowTickMarks(true);
        sliderRed.setShowTickLabels(true);
        sliderRed.setMajorTickUnit(51);

        Slider sliderBlue = new Slider(0, 255, 0);
        sliderBlue.setShowTickMarks(true);
        sliderBlue.setShowTickLabels(true);
        sliderBlue.setMajorTickUnit(51);

        //creating opacity slider
        Slider sliderOpacity = new Slider(0.0, 1.0, 1.0);
        sliderOpacity.setShowTickMarks(true);
        sliderOpacity.setShowTickLabels(true);
        sliderOpacity.setMajorTickUnit(0.2);

        //creating vertical box
        VBox vbox = new VBox();
        vbox.getChildren().add(text);
        vbox.getChildren().add(new Text("Red:"));
        vbox.getChildren().add(sliderRed);
        vbox.getChildren().add(new Text("Green:"));
        vbox.getChildren().add(sliderGreen);
        vbox.getChildren().add(new Text("Blue:"));
        vbox.getChildren().add(sliderBlue);
        vbox.getChildren().add(new Text("Opacity:"));
        vbox.getChildren().add(sliderOpacity);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        
        //adding functionality to sliders
        //color and opacity of text will change depending on the current position of the slider
        sliderRed.valueProperty().addListener(ov -> {
                text.setFill(Color.rgb(
                    (int) sliderRed.getValue(),
                    (int) sliderGreen.getValue(),
                    (int) sliderBlue.getValue()));
            }
        );

        sliderGreen.valueProperty().addListener(ov -> {
                text.setFill(Color.rgb(
                    (int) sliderRed.getValue(),
                    (int) sliderGreen.getValue(),
                    (int) sliderBlue.getValue()));
            }
        );

        sliderBlue.valueProperty().addListener(ov -> {
                text.setFill(Color.rgb(
                    (int) sliderRed.getValue(),
                    (int) sliderGreen.getValue(),
                    (int) sliderBlue.getValue()));
            }
        );

        sliderOpacity.valueProperty().addListener(ov -> {
                text.setOpacity(sliderOpacity.getValue());
            }
        );

        // scene and stage
        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setTitle("Slider Demo with Vertical Orientation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
