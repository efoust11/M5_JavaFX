import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalTime;

public class App extends Application{
    @Override 
    public void start(Stage primaryStage) throws SQLException{
        //borderpane for primary stage
        BorderPane borderpane = new BorderPane();

        //hbox on top for message and connect to database button
        HBox topBox = new HBox();
        Text updateText = new Text("");
        Button connectBtn = new Button("Connect to Database");

        topBox.getChildren().addAll(updateText, connectBtn);
        borderpane.setTop(topBox);

        //center area for messages
        Text reportText1 = new Text("");
        Text reportText2 = new Text("");
        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(reportText1, reportText2);
        borderpane.setCenter(centerBox);

        //bottom hbox for batch update and non-batch update
        HBox bottomBox = new HBox();
        Button batchBtn = new Button("Batch Update");
        Button nonBatchBtn = new Button("Non-Batch Update");

        bottomBox.getChildren().addAll(batchBtn, nonBatchBtn);
        borderpane.setBottom(bottomBox);
        
        //dialog box to connect to DB
        VBox dialog = new VBox();
        Text connectionText = new Text("");

        HBox drive = new HBox();
        TextField driveText = new TextField("com.mysql.jdbc.Driver");
        drive.getChildren().addAll(new Text("JDBC Drive:"), driveText);

        HBox url = new HBox();
        TextField urlText = new TextField("jdbc:mysql://localhost/exercise35_01");
        url.getChildren().addAll(new Text("Database URL:"), urlText);

        HBox user = new HBox();
        TextField userText = new TextField("root");
        user.getChildren().addAll(new Text("User Name:"), userText);

        HBox pass = new HBox();
        TextField passText = new TextField("");
        pass.getChildren().addAll(new Text("Password:"), passText);

        Button connectDialogBtn = new Button("Connect to DB");
        Button closeButton = new Button("Close Dialog");

        dialog.getChildren().addAll(connectionText, drive,url,user,pass,connectDialogBtn,closeButton);

        //creating the stage for the dialog box
        Scene dialogScene = new Scene(dialog);
        Stage secondStage = new Stage();
        secondStage.setScene(dialogScene);

        //open dialog
        connectBtn.setOnAction(e -> {
            secondStage.show();
        });

        //close dialog
        closeButton.setOnAction(e -> {
            secondStage.close();
        });

        connectDialogBtn.setOnAction(e -> {
            try {
                //connect to database
                Connection connection = DriverManager.getConnection(urlText.getText(),userText.getText(),passText.getText());
                Statement statement = connection.createStatement();
                connectionText.setText("Connected to " + urlText.getText());
                batchBtn.setOnAction(f -> {
                    //batch update
                    String sqlInsert = "insert into temp (num1, num2, num3) values(";
                    LocalTime startTime = LocalTime.now();
                    for(int i = 0; i<1000;i++){
                        String insert = sqlInsert + Math.random() + "," + Math.random() + "," + Math.random() + ");";
                        try {
                            statement.addBatch(insert);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    try {
                        statement.executeBatch();
                        //record elapsed time for the batch update
                        LocalTime endTime = LocalTime.now();
                        reportText1.setText("Batch Update Complete" + "\nThe elapsed time is " + (endTime.toNanoOfDay() - startTime.toNanoOfDay()) + " nano seconds");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                });
                nonBatchBtn.setOnAction(f -> {
                    //non-batch update
                    String sqlInsert = "insert into temp (num1, num2, num3) values(";
                    LocalTime startTime = LocalTime.now();
                    for(int i = 0; i<1000;i++){
                        String insert = sqlInsert + Math.random() + "," + Math.random() + "," + Math.random() + ");";
                        try {
                            statement.executeQuery(insert);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    //record elapsed time for non-batch update
                    LocalTime endTime = LocalTime.now();
                    reportText2.setText("Non-batch Update Complete" + "\nThe elapsed time is " + (endTime.toNanoOfDay() - startTime.toNanoOfDay()) + " nano seconds");
                });

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            

            
        });

        //creating the scene
        Scene scene = new Scene(borderpane);
        primaryStage.setScene(scene); 
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
