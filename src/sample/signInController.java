package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signInController {
    public PasswordField password;
    public Button signIn;
    public TextField userId;

    public void signIn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = null;
        switch (userId.getText().length()){
            case 3 : loader = new FXMLLoader(getClass().getResource("ownerScreen.fxml"));break;
            case 4 : loader = new FXMLLoader(getClass().getResource("employeeScreen.fxml"));break;
            case 8 : loader = new FXMLLoader(getClass().getResource("customerScreen.fxml"));break;
        }
        Stage stage = (Stage) signIn.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,1920,1080);
        stage.setScene(scene);
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Stage stage = (Stage) signIn.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,1920,1080);
        stage.setScene(scene);
    }
}
