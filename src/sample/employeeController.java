package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class employeeController {
    public Button signOutButton;
    public TabPane tabPane;

    public AnchorPane addPhone;
    public AnchorPane addAccessory;
    public AnchorPane addPromo;
    public AnchorPane anchor;

    public TextField phoneName;
    public TextField phoneVersion;
    public ImageView photoShow;
    public DatePicker releaseDate;
    public TextField weightThikness;
    public TextField OsVersion;
    public TextField storageSdSlot;
    public TextField screenSizeInInches;
    public TextField screenSizeInPixels;
    public TextField cameraPhotoResolution;
    public TextField cameraVideoResolution;
    public TextField ram;
    public TextField chipSet;
    public TextField BatteryCapacity;
    public TextField batteryTecnolgy;
    public Button adPhone;
    public Button clear;
    public TextField retailPrice;
    public TextField wholesalePrice;
    public TextField Quantity;

    public void initialize(){
        anchor.setPrefSize(Main.screenWidth, Main.screenHeight - 40);

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

    public void addPhoneAction(ActionEvent actionEvent) {
    }

    public void clearAction(ActionEvent actionEvent) {
        phoneName.setText("");
        phoneVersion.setText("");
        photoShow.setImage(null);
        releaseDate.setValue(null);
        weightThikness.setText("");
        OsVersion.setText("");
        storageSdSlot.setText("");
        screenSizeInInches.setText("");
        screenSizeInPixels.setText("");
        cameraPhotoResolution.setText("");
        cameraVideoResolution.setText("");
        ram.setText("");
        chipSet.setText("");
        BatteryCapacity.setText("");
        batteryTecnolgy.setText("");
        retailPrice.setText("");
        wholesalePrice.setText("");
        Quantity.setText("");

    }
}
