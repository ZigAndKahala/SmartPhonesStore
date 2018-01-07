package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class customerController {
    private int cid;

    public AnchorPane addPhone;
    public Pane phonePane;
    public TextField weightThickness;
    public TextField OsVersion;
    public TextField storageSdSlot;
    public TextField screenSizeInInches;
    public TextField screenSizeInPixels;
    public TextField cameraPhotoResolution;
    public TextField cameraVideoResolution;
    public TextField ram;
    public TextField chipSet;
    public TextField BatteryCapacity;
    public TextField phoneName;
    public ChoiceBox choosePhone;
    public Button signOut;

    public void initialize(){
//        List<String> listOfAvaliablePhones = new ArrayList<>();
//        choosePhone.getItems().addAll()
    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.goToScreen(new FXMLLoader(getClass().getResource("signInScreen.fxml")),(Stage)signOut.getScene().getWindow());
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
