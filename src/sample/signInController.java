package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signInController {
    public PasswordField password;
    public Button signIn;
    public TextField userId;
    public AnchorPane rootPane;
    public TextField forfname;
    public Label d;
    public Label ll;

    private int userID;

    public void signIn(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if(password.getText().equals("") || userId.getText().equals("")){
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Please Fill All The Data",1,40),(Stage)password.getScene().getWindow());
            return;
        }

        Boolean isUserIdNumber = true;

        for (Character character : userId.getText().toCharArray()) {
            if (!Character.isDigit(character)) {
                isUserIdNumber = false;
                break;
            }
        }

        int determinant;

        if (isUserIdNumber) {
            determinant = userId.getText().length();
            if(determinant != 3 && determinant != 4 && determinant != 8)
                determinant = 2;
            if (getUserId() == 1)
                determinant = 2;
        }else {
            determinant = typeOfUser();
        }

        FXMLLoader loader = null;
        Object controller;
        switch (determinant){
            case 1 : displayEmailError();return;
            case 2 : displayIdError();return;
            case 3 : loader = new FXMLLoader(getClass().getResource("ownerScreen.fxml"));break;
            case 4 : loader = new FXMLLoader(getClass().getResource("employeeScreen.fxml"));break;
            case 8 : loader = new FXMLLoader(getClass().getResource("customerScreen.fxml"));break;
        }
        Stage stage = (Stage) signIn.getScene().getWindow();
        Parent root = loader.load();

        controller = loader.getController();
        switch (determinant){
            case 3 : ((ownerController)controller).setOid(userID);break;
            case 4 : ((employeeController)controller).setEid(userID);break;
            case 8 : ((customerController)controller).setCid(userID);break;
        }

        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Stage stage = (Stage) signIn.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }

    private int typeOfUser() throws SQLException, ClassNotFoundException {
        ResultSet resultSet;

        DatabaseAPI databaseAPI = new DatabaseAPI();

        resultSet = databaseAPI.read("SELECT oid FROM owner WHERE email = \"" + userId.getText() + "\" && password = \"" + password.getText() + "\";");
        if(resultSet.next()){
            userID = resultSet.getInt(1);
            return 3;
        }

        resultSet = databaseAPI.read("SELECT eid FROM employee WHERE email = \"" + userId.getText() + "\" && password = \"" + password.getText() + "\";");
        if(resultSet.next()){
            userID = resultSet.getInt(1);
            return 4;
        }

        resultSet = databaseAPI.read("SELECT cid FROM customer WHERE email = \"" + userId.getText() + "\" && password = \"" + password.getText() + "\";");
        if(resultSet.next()){
            userID = resultSet.getInt(1);
            return 8;
        }

        return 1;
    }

    private int getUserId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet;

        DatabaseAPI databaseAPI = new DatabaseAPI();

        if (userId.getText().length() == 3){
            resultSet = databaseAPI.read("SELECT oid FROM owner WHERE oid = " + userId.getText() + " && password = \"" + password.getText() + "\";");
            if (resultSet.next()) {
                userID = resultSet.getInt(1);
                return 0;
            }
        }else if(userId.getText().length() == 4){
            resultSet = databaseAPI.read("SELECT eid FROM employee WHERE eid = " + userId.getText() + " && password = \"" + password.getText() + "\";");
            if (resultSet.next()) {
                userID = resultSet.getInt(1);
                return 0;
            }
        }else if (userId.getText().length() == 8){
            resultSet = databaseAPI.read("SELECT cid FROM customer WHERE cid = " + userId.getText() + " && password = \"" + password.getText() + "\";");
            if (resultSet.next()) {
                userID = resultSet.getInt(1);
                return 0;
            }
        }
        return 1;
    }

    private void displayEmailError(){
        PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Wrong Email Address Or Password",1,40),(Stage)password.getScene().getWindow());
    }

    private void displayIdError(){
        PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Wrong User Id Or Password",1,40),(Stage)password.getScene().getWindow());
    }

    public void remember(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DatabaseAPI databaseAPI = new DatabaseAPI();

        ResultSet customernam = databaseAPI.read("SELECT cid from customer where name= \"" + forfname.getText() +"\"");
        customernam.next();
        int gj = customernam.getInt(1);
        d.setText(Integer.toString(gj));
        ResultSet customernam2 = databaseAPI.read("SELECT email from customer where name= \"" + forfname.getText() +"\"");
        customernam2.next();
        ll.setText(customernam2.getString(1));

    }
}
