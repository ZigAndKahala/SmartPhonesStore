package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeController {
    public TextField phoneLink;
    public TextField accessoryLink;
    private int eid;

    public Button signOutButton;
    public TabPane tabPane;

    public AnchorPane addPhone;
    public AnchorPane addAccessory;
    public AnchorPane addPromo;
    public AnchorPane anchor;
    public TextField phoneName;
    public TextField phoneVersion;
    public DatePicker releaseDate;
    public TextField weightThikness;
    public TextField OsVersion;
    public TextField storageSdSlot;
    public TextField screenSizeAndResolution;
    public TextField cameraPhotoResolution;
    public TextField RAMAndChipset;
    public TextField status;
    public TextField BatteryCapacity;
    public TextField warrantyPeriod;
    public TextField retailPrice;
    public TextField wholesalePrice;
    public TextField Quantity;
    public TextField description;
    public ImageView phoneImage;
    
    
    public TextField accessoryName;
    public ChoiceBox type;
    public TextField accessorystatus;
    public TextField accessoryRetialPrice;
    public TextField accessorywholesalePrice;
    public TextField accessoryQuantity;
    public TextField accessoryDescription;
    public ImageView accessoryImage;
    
    
    public RadioButton phoneRadioButton;
    public ToggleGroup product;
    public RadioButton accessorisRadioButton;
    public TextField promotionPercentage;
    public DatePicker finishDate;
    public ChoiceBox producrIs;
    public DatePicker startDate;


    ObservableList<String> mainTypeList = FXCollections.observableArrayList("Cases","Smart Watches","Bluetooth Headsets","Cables & Adapters","Screen Protectors","Chargers & Cradles","Bluetooth Portable Speakers","Car Mounts","Batteries","Screen Digitizers");
    private boolean isImageLinkValid;

    public void initialize(){
        anchor.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
        addPhone.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
        addAccessory.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
        addPromo.setPrefSize(Main.screenWidth, Main.screenHeight - 40);
        type.setItems(mainTypeList);
    }


    @SuppressWarnings("Duplicates")
    public void signOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signInScreen.fxml"));
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }

    public void addPhoneAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException  {
        String reldate = null;
        LocalDate localDate = releaseDate.getValue();
        if(localDate != null) {
            reldate = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();
        }

        if(checkImage(phoneLink.getText()) == null){
            return;
        }

        List<String> parameters = new ArrayList<>();
        parameters.add(phoneName.getText());
        parameters.add(phoneVersion.getText());
        parameters.add(description.getText());
        parameters.add(wholesalePrice.getText());
        parameters.add(status.getText());
        parameters.add(retailPrice.getText());
        parameters.add(Quantity.getText());
        parameters.add(warrantyPeriod.getText());
        parameters.add(reldate);
        parameters.add(weightThikness.getText());
        parameters.add(OsVersion.getText());
        parameters.add(storageSdSlot.getText());
        parameters.add(screenSizeAndResolution.getText());
        parameters.add(cameraPhotoResolution.getText());
        parameters.add(RAMAndChipset.getText());
        parameters.add(BatteryCapacity.getText());
        parameters.add(phoneLink.getText());


        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.write("INSERT INTO phone " + DatabaseAPI.generateSqlCommand("name,phoneVersion,description,wholesalePrice,status,retailPrice,quantity,warrantyPeriod,releasDate,weightAndThickness,OSVersion,storageAndSDSlot,screenSizeAndResolution,CameraPhotoAndVideo,RAMAndChipset,batteryCapacityAndTechnology,imageLink",parameters));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Done ^_^");
        alert.showAndWait();


    }

    public void clearAction(ActionEvent actionEvent) {
        phoneImage.setImage(null);
        phoneName.setText("");
        phoneVersion.setText("");
        releaseDate.setValue(null);
        weightThikness.setText("");
        OsVersion.setText("");
        storageSdSlot.setText("");
        screenSizeAndResolution.setText("");
        cameraPhotoResolution.setText("");
        RAMAndChipset.setText("");
        status.setText("");
        BatteryCapacity.setText("");
        warrantyPeriod.setText("");
        retailPrice.setText("");
        wholesalePrice.setText("");
        Quantity.setText("");
        description.setText("");


    }

    public void addAccessoryOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(checkImage(accessoryLink.getText()) == null){
            return;
        }

        List<String> parameters = new ArrayList<>();
        String typeIs;
        typeIs = (String) type.getValue();
        parameters.add(accessoryName.getText());
        parameters.add(accessoryDescription.getText());
        parameters.add(accessorywholesalePrice.getText());
        parameters.add(accessoryQuantity.getText());
        parameters.add(accessoryRetialPrice.getText());
        parameters.add(typeIs);
        parameters.add(accessorystatus.getText());
        parameters.add(accessoryLink.getText());

        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.write("INSERT INTO others " + DatabaseAPI.generateSqlCommand("name,description,wholesalePrice,quantity,retailPrice,type,status,imageLink",parameters));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Done ^_^");
        alert.showAndWait();
    }

    public void clearAccessory(ActionEvent actionEvent) {
        accessoryName.setText("");
        type.setValue(null);
        accessorystatus.setText("");
        accessoryRetialPrice.setText("");
        accessorywholesalePrice.setText("");
        accessoryQuantity.setText("");
        accessoryDescription.setText("");
        accessoryImage.setImage(null);

    }

    public void addPromotion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LocalDate localDate = startDate.getValue();
        String stardate = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();
        LocalDate localDate1 = finishDate.getValue();
        String finisdate = localDate1.getYear() + "-" + localDate1.getMonthValue() + "-" + localDate1.getDayOfMonth();
        List<String> parameters = new ArrayList<>();
        parameters.add(stardate);
        parameters.add(finisdate);
        parameters.add(promotionPercentage.getText());

        DatabaseAPI databaseAPI = new DatabaseAPI();



        databaseAPI.write("INSERT INTO promotions " + DatabaseAPI.generateSqlCommand("startDate,endDate,percentage",parameters));

        ResultSet proId = databaseAPI.read("SELECT max(proId) FROM promotions");
        proId.next();
        int proID = proId.getInt(1);

        if(phoneRadioButton.isSelected()) {
            String phoneName2 = (String) producrIs.getValue();
            String phoneName = phoneName2.split(" ")[0];
            databaseAPI.write("UPDATE phone SET proId = " + proID + " WHERE name = \"" + phoneName + "\"");
        }else {
            String accessoryFullName = (String) producrIs.getValue();
            String accessoryFirstName = accessoryFullName.split(" ")[0];
            databaseAPI.write("UPDATE others SET proId = " + proID + " WHERE name = \"" + accessoryFirstName + "\"");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Done ^_^");
        alert.showAndWait();
    }

    public void addProductPhone(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int count=0;
        DatabaseAPI databaseAPI = new DatabaseAPI();


        ResultSet maxpid = databaseAPI.read("SELECT max(pid) from phone;");
        maxpid.next();
        int mpid = maxpid.getInt("max(pid)");
        String[] phonesH = new String[mpid];
        ResultSet phname = databaseAPI.read("SELECT name from phone;");
        ResultSet phVersion = databaseAPI.read("SELECT phoneVersion from phone;");
        while(phname.next() && phVersion.next())
        {
            phonesH[count] = phname.getString("name") + " " + phVersion.getString(1);
            count+=1;
        }
        ObservableList<String> mainTypeListphone = FXCollections.observableArrayList(phonesH);
        producrIs.setItems(mainTypeListphone);

    }

    public void addProductAccessory(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int count=0,i;
        DatabaseAPI databaseAPI = new DatabaseAPI();


        ResultSet maxoid = databaseAPI.read("SELECT max(otherId) from others;");
        maxoid.next();
        int moid = maxoid.getInt("max(otherId)");
        String[] accessoriesH = new String[moid];
        ResultSet acname = databaseAPI.read("SELECT name from others;");
        ResultSet acType = databaseAPI.read("SELECT type from others;");
        while(acname.next() && acType.next())
        {
            accessoriesH[count] =acname.getString("name") + " " + acType.getString(1);
            count+=1;
        }
        ObservableList<String> mainTypeListaccessories = FXCollections.observableArrayList(accessoriesH);
        producrIs.setItems(mainTypeListaccessories);


    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public void loadPhoneImage(ActionEvent actionEvent) {
        phoneImage.setImage(checkImage(phoneLink.getText()));
    }

    public void loadAccessoryImage(ActionEvent actionEvent) {
        accessoryImage.setImage(checkImage(accessoryLink.getText()));
    }

    private Image checkImage(String URL){
        Image image = new Image(URL);
        if (image.isError()) {
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Invalid Image URL!!", 1, 40), (Stage) phoneImage.getScene().getWindow());
            isImageLinkValid = false;
            return null;
        }
        isImageLinkValid = true;
        return image;
    }
}
