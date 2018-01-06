package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenSwitcher {

    public static void goToScreen(FXMLLoader loader, Stage thisStage) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        thisStage.setScene(scene);
    }
}
