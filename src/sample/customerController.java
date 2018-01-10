package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class customerController {
    private int cid;

    public Label releaseDate;
    public TextField screenSizeAndResolution;
    public TextField description;
    public TextField warrantyPeriod;
    public TextField quantity;
    public TextField wholesalePrice;
    public TextField promotion;
    public TextField status;
    public TextField RAMAndChipSet;
    public TextField weightAndThickness;
    public ImageView phoneImage;
    public ComboBox chooseAccessory;
    public AnchorPane addPhone;
    public Pane phonePane;
    public TextField OsVersion;
    public TextField storageSdSlot;
    public TextField cameraPhotoResolution;
    public TextField BatteryCapacity;
    public ChoiceBox choosePhone;
    public Button signOut;

    private List<Integer> phonesIdIndexes = new ArrayList<>();
    private List<Integer> accessoryIdIndexes = new ArrayList<>();
    private double phonePrice;
    private double promoValue;
    private int pid;

    public void initialize() throws SQLException, ClassNotFoundException {
        phonePane.setVisible(false);
        //Adding phones List
        List<String> phonesList = new ArrayList<>();

        DatabaseAPI databaseAPI = new DatabaseAPI();
        ResultSet listOfAvailablePhones = databaseAPI.read("SELECT pid,name,phoneVersion FROM phone WHERE quantity != 0;");
        while (listOfAvailablePhones.next()) {
            phonesIdIndexes.add(listOfAvailablePhones.getInt(1));
            phonesList.add(listOfAvailablePhones.getString(2) + " " + listOfAvailablePhones.getString(3));
        }


        choosePhone.getItems().addAll(phonesList);

        //Adding accessory List
        List<String> accessoryList = new ArrayList<>();

        ResultSet listOfAvailableAccessories = databaseAPI.read("SELECT otherId,name,type FROM others WHERE quantity != 0;");
        while (listOfAvailablePhones.next()){
            accessoryIdIndexes.add(listOfAvailableAccessories.getInt(1));
            accessoryList.add(listOfAvailablePhones.getString(2) + " " + listOfAvailablePhones.getString(3));
        }

        chooseAccessory.getItems().addAll(accessoryList);
    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.goToScreen(new FXMLLoader(getClass().getResource("signInScreen.fxml")),(Stage)signOut.getScene().getWindow());
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void viewSelectedPhone(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (choosePhone.getSelectionModel().getSelectedIndex() == -1) {
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Please Choose a Phone First", 1, 40), (Stage) choosePhone.getScene().getWindow());
            return;
        }

        DatabaseAPI databaseAPI = new DatabaseAPI();
        ResultSet phoneData = databaseAPI.read("SELECT proId, description, wholesalePrice," +
                " status, quantity, warrantyPeriod, releasDate, weightAndThikness, OSVersion," +
                " storageAndSDSlot, screenSizeAndResolution, CameraPhotoAndVideo, RAMAndChipset, batteryCapacityAndTechnology," +
                " imageLink,pid FROM phone WHERE pid = " + phonesIdIndexes.get(choosePhone.getSelectionModel().getSelectedIndex()));

        phoneData.next();
        int promId = phoneData.getInt(1);
        ResultSet promotionValue = databaseAPI.read("SELECT percentage,startDate,endDate FROM promotions WHERE proId = " + promId);
        if(promotionValue.next()) {
            promoValue = promotionValue.getDouble(1);
            promotion.setText("Promotion : " + promoValue + "% | End Date : " + promotionValue.getString(3));
            promotion.setVisible(true);
            promotion.setStyle("-fx-text-fill: green");
        }
        else {
            promotion.setVisible(false);
        }

        description.setText(phoneData.getString(2));
        phonePrice = phoneData.getDouble(3);
        wholesalePrice.setText("Price : " + phonePrice);
        status.setText("Status : " + phoneData.getString(4));
        quantity.setText("Quantity X " + phoneData.getInt(5));
        warrantyPeriod.setText("Warranty Period : " + phoneData.getString(6));
        String releaseDate = phoneData.getString(7);
        this.releaseDate.setText("Released " + releaseDate.split("-")[0] + " - " + releaseDate.split("-")[1]);
        weightAndThickness.setText(phoneData.getString(8));
        OsVersion.setText(phoneData.getString(9));
        storageSdSlot.setText(phoneData.getString(10));
        screenSizeAndResolution.setText(phoneData.getString(11));
        cameraPhotoResolution.setText(phoneData.getString(12));
        RAMAndChipSet.setText(phoneData.getString(13));
        BatteryCapacity.setText(phoneData.getString(14));
        phoneImage.setImage(new Image(phoneData.getString(15)));
        pid = phoneData.getInt(16);

        phonePane.setVisible(true);
    }

    public void buy(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Buy Confirmation");
        alert.setContentText("Are you sure you want to Buy this device?");
        ButtonType okButton = new ButtonType("Buy", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.getText().equals("Buy")) {
                try {
                    buyDevice();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (type.getText().equals("Cancel")) {
                PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Canceled Buying",1,30),(Stage)phonePane.getScene().getWindow());
            }
        });
    }

    private void buyDevice() throws SQLException, ClassNotFoundException {
        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet resultSet = databaseAPI.read("Select cid From buyPhone Where cid = " + cid + " && pid = " + pid);
        if (resultSet.next()){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("You Already Bought this Device",1,30),(Stage)phonePane.getScene().getWindow());
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        List<String> paymentData = new ArrayList<>();

        if(promotion.isVisible())
            paymentData.add(String.valueOf(phonePrice*(promoValue/100)));
        else
            paymentData.add(String.valueOf(phonePrice));

        paymentData.add(String.valueOf(dateFormat.format(date)));

        if(promotion.isVisible())
            paymentData.add(String.valueOf(phonePrice*(promoValue/100)));
        else
            paymentData.add(String.valueOf(phonePrice));

        paymentData.add(String.valueOf(dateFormat.format(date)));

        databaseAPI.write("Insert Into payment (paidAmount,dateOfPayment,actualPaidAmount,dateOfActualPayment) Values " + DatabaseAPI.convertToSqlFormat(paymentData));

        ResultSet paymentId = databaseAPI.read("Select max(yid) From payment");
        paymentId.next();
        int yid = paymentId.getInt(1);
        List<String> buyTableData = new ArrayList<>();
        buyTableData.add(String.valueOf(cid));
        buyTableData.add(String.valueOf(pid));
        buyTableData.add(String.valueOf(yid));
        //TODO : check these inputs below
//        buyTableData.add("1.4");
//        buyTableData.add("Dollar");

        databaseAPI.write("Insert Into buyPhone (cid,pid,yid) values " + DatabaseAPI.convertToSqlFormat(buyTableData));

        PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Buying Process Completed Successfully",0,30),(Stage)phonePane.getScene().getWindow());
    }

    public void reserve(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reserve Confirmation");
        alert.setContentText("Are you sure you want to reserve this device?");
        ButtonType okButton = new ButtonType("Reserve", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.getText().equals("Reserve")) {
                try {
                    reserveDevice();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (type.getText().equals("Cancel")) {
                PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Canceled Reserving",1,30),(Stage)phonePane.getScene().getWindow());
            }
        });
    }

    private void reserveDevice() throws SQLException, ClassNotFoundException {
        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet resultSet = databaseAPI.read("Select cid from reservePhone where cid = " + cid + " && pid = " + pid);

        if (resultSet.next()){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("You Already Reserved this Device",1,30),(Stage)phonePane.getScene().getWindow());
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        List<String> reserveData = new ArrayList<>();
        reserveData.add(String.valueOf(cid));
        reserveData.add(String.valueOf(pid));
        reserveData.add(String.valueOf(dateFormat.format(date)));
        reserveData.add("1");

        databaseAPI.write("Insert Into reservePhone (cid,pid,reserveDate,isCompleted) Values " + DatabaseAPI.convertToSqlFormat(reserveData));

        PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Reserving Process Completed Successfully",0,30),(Stage)phonePane.getScene().getWindow());
    }
}
