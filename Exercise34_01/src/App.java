import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;

public class App extends Application{
 
    @Override 
    public void start(Stage primaryStage) throws SQLException{
        //Grid Pane for GUI
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5, 5, 5, 5));

        //Text Fields for each value 
        Text errorText = new Text("");
        pane.add(errorText, 0, 0);

        TextField tf_id = new TextField();
        pane.add(new Text("ID:"), 0, 1);
        pane.add(tf_id,1,1);

        TextField tf_last = new TextField();
        pane.add(new Text("Last Name:"), 0, 2);
        pane.add(tf_last,1,2);

        TextField tf_first = new TextField();
        pane.add(new Text("First Name:"), 2, 2);
        pane.add(tf_first,3,2);

        TextField tf_mi = new TextField();
        pane.add(new Text("MI:"), 4, 2);
        pane.add(tf_mi,5,2);

        TextField tf_address = new TextField();
        pane.add(new Text("Address:"), 0, 3);
        pane.add(tf_address,1,3);

        TextField tf_city = new TextField();
        pane.add(new Text("City:"), 0, 4);
        pane.add(tf_city,1,4);

        TextField tf_State = new TextField();
        pane.add(new Text("State:"), 2, 4);
        pane.add(tf_State,3,4);

        TextField tf_Telephone = new TextField();
        pane.add(new Text("Telephone:"), 0, 5);
        pane.add(tf_Telephone,1,5);

        //Buttons
        Button btnView = new Button("View");
        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        Button btnClear = new Button("Clear");

        pane.add(btnView, 1,6);
        pane.add(btnInsert, 2,6);
        pane.add(btnUpdate, 3,6);
        pane.add(btnClear, 4,6);

        //Connecting to database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/exercise3401","root","");
        Statement statement = connection.createStatement();

        //View a entry based on ID
        btnView.setOnAction(e -> {
            String id = tf_id.getText();
            try {
                ResultSet resultSet = statement.executeQuery(
                    "select lastName, firstName, mi, address, city, state, telephone from staff where id = "+ id
                );

                while (resultSet.next()){
                    tf_last.setText(resultSet.getString(1));
                    tf_first.setText(resultSet.getString(2));
                    tf_mi.setText(resultSet.getString(3));
                    tf_address.setText(resultSet.getString(4));
                    tf_city.setText(resultSet.getString(5));
                    tf_State.setText(resultSet.getString(6));
                    tf_Telephone.setText(resultSet.getString(7));
                }
                errorText.setText("");
                
            } catch (SQLException e1) {
                errorText.setText("Record not found.");
                e1.printStackTrace();
            }
        });

        //Clear all text fields
        btnClear.setOnAction(e ->{
            tf_id.setText("");
            tf_last.setText("");
            tf_first.setText("");
            tf_mi.setText("");
            tf_address.setText("");
            tf_city.setText("");
            tf_State.setText("");
            tf_Telephone.setText("");
        });

        //Update entry based on ID
        btnUpdate.setOnAction(e ->{
            String id = tf_id.getText();
            System.out.println(tf_address.getText());
            try {

                String update = "update staff set lastName = '" + tf_last.getText() + "', firstName = '" + tf_first.getText() +
                        "', mi = '" + tf_mi.getText() + "', address = '" + tf_address.getText() + "', city = '" + tf_city.getText() +
                        "', state = '" + tf_State.getText() + "', telephone = '" + tf_Telephone.getText() + "' where id = '" + id + "'";

                statement.executeUpdate(update);

            } catch(SQLException e1){
                errorText.setText("Record not found.");
                e1.printStackTrace();
            }
        });

        //Insert new entry
        btnInsert.setOnAction(e->{
            try {

                String insert = "insert into staff (id, lastName, firstName, mi, address, city, state, telephone) values ('" + 
                tf_id.getText() + "', '" +
                tf_last.getText() + "', '" + tf_first.getText() +
                        "', '" + tf_mi.getText() + "', '" + tf_address.getText() + "', '" + tf_city.getText() +
                        "', '" + tf_State.getText() + "', '" + tf_Telephone.getText() +"')";

                statement.executeUpdate(insert);
                

            } catch(SQLException e1){
                errorText.setText("Record not found.");
                e1.printStackTrace();
            }
        });

        //creating the scene
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene); 
        primaryStage.show();

    }
    public static void main(String[] args) throws Exception {
       launch(args);
    }
}
