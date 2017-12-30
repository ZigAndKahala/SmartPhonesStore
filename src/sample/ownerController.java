package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ownerController {
    public TextField name;
    public TextField number;
    public TextField email;
    public TextField id;
    public Button clear;
    public DatePicker dat;
    public Button addemployee;
    public TableView incomingTable;
    public TableView sellingTable;

    public void initialize(){

        //TODO : resize the two tables to the user Main.screenWidth & Main.screenHeight
    }

    public void clearAll(ActionEvent actionEvent) {
        name.setText("");
        number.setText("");
        email.setText("");
        id.setText("");
    }

    @SuppressWarnings("Duplicates")
    public void signOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signInScreen.fxml"));
        Stage stage = (Stage) name.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }
}
