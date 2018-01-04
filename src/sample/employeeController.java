package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class employeeController {
    public Button signOutButton;
    public TabPane tabPane;

    public AnchorPane addPhone;
    public AnchorPane addAccessory;
    public AnchorPane addPromo;

    public void initialize(){
        addPhone.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
        addAccessory.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
        addPromo.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
    }

    @SuppressWarnings("Duplicates")
    public void signOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signInScreen.fxml"));
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }
}
