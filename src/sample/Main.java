package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.SQLException;

public class Main extends Application {
    static public int screenWidth;
    static public int screenHeight;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        screenWidth = screenSize.width;
        screenHeight = screenSize.height - 30;

        Parent root = FXMLLoader.load(getClass().getResource("signInScreen.fxml"));
        primaryStage.setTitle("Mobile Store");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
}
