package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("signInScreen.fxml"));
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
//        DatabaseAPI databaseAPI = new DatabaseAPI();
//        System.out.println(databaseAPI.read("select name from customer;").getString("name"));
//        databaseAPI.write("insert INTO customer (cid, name, email, mobileNumber) VALUEs (6,\"Anas\",\"Anas\",050023456);");
//        refresh();
    }

//    private static void refresh() {
//        try {
//            DatabaseAPI db = new DatabaseAPI();
//            String sql = "Select * from customer";
//
//            ResultSet result = db.read(sql);
//            StringBuilder emps = new StringBuilder();
//            while(result.next()){
//                emps.append(result.getString(1)).append("\t").append(result.getString(2)).append("\t").append(result.getString(3)).append("\n");
//            }
//            System.out.println(emps);
//            db.connection.close();
//            db.connection = null;
//        } catch (ClassNotFoundException | SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
