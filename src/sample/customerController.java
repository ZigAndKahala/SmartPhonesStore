package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    public AnchorPane accessoryPane;
    public ImageView accessoryImage;
    public TextField acsName;
    public TextField acsType;
    public TextField acsPrice;
    public TextField acsQuantity;
    public TextField acsPromotion;
    public TextField acsDescription;
    public TextField acsStatus;
    public TableView acsPurchase;
    public TableView phonePurchase;
    public TableColumn phoneClmn;
    public TableColumn phoneBuyDateClmn;
    public TableColumn acessNameClmn;
    public TableColumn acessBuyDateClmn;
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
    private double PpromoValue;
    private double ApromoValue;
    private int pid;
    private int otherId;

    public void initialize() throws SQLException, ClassNotFoundException {
        phonePane.setVisible(false);
        accessoryPane.setVisible(false);

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
        while (listOfAvailableAccessories.next()){
            accessoryIdIndexes.add(listOfAvailableAccessories.getInt(1));
            accessoryList.add(listOfAvailableAccessories.getString(2) + " | " + listOfAvailableAccessories.getString(3));
        }

        chooseAccessory.getItems().addAll(accessoryList);
    }

    public void signOut(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.goToScreen(new FXMLLoader(getClass().getResource("signInScreen.fxml")),(Stage)signOut.getScene().getWindow());
    }

    public void setCid(int cid) throws SQLException, ClassNotFoundException {
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
                " imageLink FROM phone WHERE pid = " + phonesIdIndexes.get(choosePhone.getSelectionModel().getSelectedIndex()));

        phoneData.next();
        int promId = phoneData.getInt(1);
        setPromotion(promId,true,promotion);

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
        pid = phonesIdIndexes.get(choosePhone.getSelectionModel().getSelectedIndex());

        phonePane.setVisible(true);
    }

    public void viewSelectedAccessory(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (chooseAccessory.getSelectionModel().getSelectedIndex() == -1) {
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Please Choose an Accessory First", 1, 40), (Stage) choosePhone.getScene().getWindow());
            return;
        }

        DatabaseAPI databaseAPI = new DatabaseAPI();
        ResultSet accessoryData = databaseAPI.read("SELECT proId, name, description, wholesalePrice, quantity, type, status, imageLink FROM others WHERE otherId = " +
                accessoryIdIndexes.get(chooseAccessory.getSelectionModel().getSelectedIndex()));

        accessoryData.next();
        int promId = accessoryData.getInt(1);
        setPromotion(promId,false,acsPromotion);

        acsName.setText(accessoryData.getString(2));
        acsDescription.setText(accessoryData.getString(3));
        acsPrice.setText("Price : " + String.valueOf(accessoryData.getDouble(4)) + "$");
        acsQuantity.setText("Quantity : " + String.valueOf(accessoryData.getInt(5)));
        acsType.setText(accessoryData.getString(6));
        acsStatus.setText(accessoryData.getString(7));
        accessoryImage.setImage(new Image(accessoryData.getString(8)));
        otherId = accessoryIdIndexes.get(chooseAccessory.getSelectionModel().getSelectedIndex());

        accessoryPane.setVisible(true);
    }

    private void setPromotion(int promotionId,Boolean isPhone, TextField textField) throws SQLException, ClassNotFoundException {
        DatabaseAPI databaseAPI = new DatabaseAPI();
        ResultSet promotionValue = databaseAPI.read("SELECT percentage,startDate,endDate FROM promotions WHERE proId = " + promotionId);
        if(promotionValue.next()) {
            double promoValue = promotionValue.getDouble(1);
            if (isPhone)
                PpromoValue = promoValue;
            else
                ApromoValue = promoValue;

            textField.setText("Promotion : " + promoValue + "% | End Date : " + promotionValue.getString(3));
            textField.setVisible(true);
            textField.setStyle("-fx-text-fill: green");
        }
        else {
            textField.setVisible(false);
        }
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

        //TODO : delete -1 from quantity
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        List<String> paymentData = new ArrayList<>();

        if(promotion.isVisible())
            paymentData.add(String.valueOf(phonePrice*(PpromoValue/100)));
        else
            paymentData.add(String.valueOf(phonePrice));

        paymentData.add(String.valueOf(dateFormat.format(date)));

        if(promotion.isVisible())
            paymentData.add(String.valueOf(phonePrice*(PpromoValue/100)));
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

        ResultSet resultSet1 = databaseAPI.read("Select cid From buyPhone Where cid = " + cid + " && pid = " + pid);
        if (resultSet1.next()){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Why to reserve you own phone *_^ ?",1,30),(Stage)phonePane.getScene().getWindow());
            return;
        }

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

    public void buyAccessory(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Buy Confirmation");
        alert.setContentText("Are you sure you want to Buy this Accessory?");
        ButtonType okButton = new ButtonType("Buy", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.getText().equals("Buy")) {
                try {
                    buyAccess();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (type.getText().equals("Cancel")) {
                PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Canceled Buying",1,30),(Stage)phonePane.getScene().getWindow());
            }
        });
    }

    private void buyAccess() throws SQLException, ClassNotFoundException {
        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet resultSet = databaseAPI.read("Select cid From buyOthers Where cid = " + cid + " && otherId = " + otherId);
        if (resultSet.next()){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("You Already Bought this Device",1,30),(Stage)phonePane.getScene().getWindow());
            return;
        }


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        List<String> paymentData = new ArrayList<>();

        if(acsPromotion.isVisible())
            paymentData.add(String.valueOf(Double.parseDouble(acsPrice.getText())*(ApromoValue/100)));
        else
            paymentData.add(acsPrice.getText());

        paymentData.add(String.valueOf(dateFormat.format(date)));

        if(acsPromotion.isVisible())
            paymentData.add(String.valueOf(Double.parseDouble(acsPrice.getText())*(ApromoValue/100)));
        else
            paymentData.add(acsPrice.getText());

        paymentData.add(String.valueOf(dateFormat.format(date)));

        databaseAPI.write("Insert Into payment (paidAmount,dateOfPayment,actualPaidAmount,dateOfActualPayment) Values " + DatabaseAPI.convertToSqlFormat(paymentData));

        ResultSet paymentId = databaseAPI.read("Select max(yid) From payment");
        paymentId.next();
        int yid = paymentId.getInt(1);

        List<String> buyOthersInput = new ArrayList<>();
        buyOthersInput.add(String.valueOf(cid));
        buyOthersInput.add(String.valueOf(otherId));
        buyOthersInput.add(String.valueOf(yid));

        databaseAPI.write("Insert Into buyOthers (cid,otherId,yid) values " + DatabaseAPI.convertToSqlFormat(buyOthersInput));

        PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Buying Process Completed Successfully",0,30),(Stage)phonePane.getScene().getWindow());
    }

    public void reserveAccessory(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reserve Confirmation");
        alert.setContentText("Are you sure you want to reserve this Accessory?");
        ButtonType okButton = new ButtonType("Reserve", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alert.showAndWait().ifPresent(type -> {
            if (type.getText().equals("Reserve")) {
                try {
                    reserveAccess();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (type.getText().equals("Cancel")) {
                PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Canceled Reserving",1,30),(Stage)phonePane.getScene().getWindow());
            }
        });
    }

    private void reserveAccess() throws SQLException, ClassNotFoundException {
        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet resultSet1 = databaseAPI.read("Select cid From buyOthers Where cid = " + cid + " && otherId = " + otherId);
        if (resultSet1.next()){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Why to reserve you own Accessory *_^ ?",1,30),(Stage)phonePane.getScene().getWindow());
            return;
        }

        ResultSet resultSet = databaseAPI.read("Select cid from reserveOthers where cid = " + cid + " && otherId = " + otherId);

        if (resultSet.next()){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("You Already Reserved this Accessory",1,30),(Stage)phonePane.getScene().getWindow());
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        List<String> reserveData = new ArrayList<>();
        reserveData.add(String.valueOf(cid));
        reserveData.add(String.valueOf(otherId));
        reserveData.add(String.valueOf(dateFormat.format(date)));
        //ToDo : add successful in employee screen
        //ToDo : edit description length
        reserveData.add("1");

        databaseAPI.write("Insert Into reserveOthers (cid,otherId,reserveDate,isCompleted) Values " + DatabaseAPI.convertToSqlFormat(reserveData));

        PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Reserving Process Completed Successfully",0,30),(Stage)phonePane.getScene().getWindow());
    }

    private ObservableList<phonePurchase> setPhonePurchase() throws SQLException, ClassNotFoundException {
        ObservableList<phonePurchase> phonePurchase = FXCollections.observableArrayList();

        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet resultSet = databaseAPI.read("SELECT pid,yid FROM buyphone WHERE cid = " + cid);
        while (resultSet.next()){
            ResultSet phoneName = databaseAPI.read("SELECT name,phoneVersion FROM phone WHERE pid = " + resultSet.getInt(1));
            phoneName.next();
            ResultSet dayOfPayment = databaseAPI.read("SELECT dateOfPayment FROM payment WHERE yid = " + resultSet.getInt(2));
            dayOfPayment.next();
            phonePurchase.add(new phonePurchase(phoneName.getString(1) + " " + phoneName.getString(2),dayOfPayment.getString(1)));
        }

        return phonePurchase;
    }

    private ObservableList<accessoryPurchase> setAcessPurchase() throws SQLException, ClassNotFoundException {
        ObservableList<accessoryPurchase> acessPurchase = FXCollections.observableArrayList();

        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet resultSet = databaseAPI.read("SELECT otherId,yid FROM buyothers WHERE cid = " + cid);
        while (resultSet.next()){
            ResultSet acessName = databaseAPI.read("SELECT name,type FROM others WHERE otherId = " + resultSet.getInt(1));
            acessName.next();
            ResultSet dayOfPayment = databaseAPI.read("SELECT dateOfPayment FROM payment WHERE yid = " + resultSet.getInt(2));
            dayOfPayment.next();
            acessPurchase.add(new accessoryPurchase(acessName.getString(1) + " | " + acessName.getString(2),dayOfPayment.getString(1)));
        }

        return acessPurchase;
    }

    public void selectionChanged(Event event) throws SQLException, ClassNotFoundException {
        phoneClmn.setCellValueFactory(new PropertyValueFactory("phone"));
        phoneBuyDateClmn.setCellValueFactory(new PropertyValueFactory("phoneBuyDate"));
        phonePurchase.setItems(setPhonePurchase());

        acessNameClmn.setCellValueFactory(new PropertyValueFactory("acsName"));
        acessBuyDateClmn.setCellValueFactory(new PropertyValueFactory("acsBuyDate"));
        acsPurchase.setItems(setAcessPurchase());
    }
}