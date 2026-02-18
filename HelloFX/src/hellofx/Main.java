package hellofx;

import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.SQLException;

//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;

public class Main{

    //@Override
    //public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 400, 300));
        //primaryStage.show();
    //}


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Class.forName("JDBCDriverClass");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table Temp (col1 char(5), col2 char(5))");

    }
}