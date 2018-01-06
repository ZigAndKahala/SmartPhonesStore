package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class signUpController {
    public TextField name;
    public TextField email;
    public TextField password;
    public TextField phoneNumber;
    public DatePicker dateOfBirth;
    public Button signUp;
    public Button clear;
    public Button back;

    private Popup emailPopup;
    private Popup phoneNumberPopup;

    private Boolean isPhoneNumberCorrect = true;
    private Boolean isEmailNumberCorrect = true;

    public void initialize(){

    }


    public void clear(ActionEvent actionEvent) {
        name.setText("");
        email.setText("");
        phoneNumber.setText("");
        password.setText("");
        dateOfBirth.setValue(null);
    }

    public void backToSignIn(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.goToScreen(new FXMLLoader(getClass().getResource("signInScreen.fxml")),(Stage)back.getScene().getWindow());
    }

    public void signUp(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LocalDate localDate = dateOfBirth.getValue();
        String date = null;
        if (localDate != null)
            date = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

        if(name.getText().equals("") || email.getText().equals("") || phoneNumber.getText().equals("") || password.getText().equals("") || date == null){
            Stage thisStage = (Stage) name.getScene().getWindow();
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Please Fill All The Fields!",1,30),thisStage);
            return;
        }else if(!isEmailNumberCorrect || !isPhoneNumberCorrect){
            if (!isEmailNumberCorrect){
                if (emailPopup != null)
                    emailPopup.hide();
                emailPopup = PopupMessage.showPopupMessageTextField(PopupMessage.createPopup("Incorrect Email Address!", 1, 16), (Stage) email.getScene().getWindow(), email);
            }
            else {
                if (phoneNumberPopup != null)
                    phoneNumberPopup.hide();
                phoneNumberPopup = PopupMessage.showPopupMessageTextField(PopupMessage.createPopup("Incorrect Number!", 1, 16), (Stage) email.getScene().getWindow(), phoneNumber);
            }
            return;
        }

        List<String> parameters = new ArrayList<>();
        parameters.add(name.getText());
        parameters.add(email.getText());
        parameters.add(password.getText());
        parameters.add(phoneNumber.getText());
        parameters.add(date);

        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.write("INSERT INTO customer " + DatabaseAPI.generateSqlCommand("name,email,password,mobileNumber,dateOfBirth",parameters));
        ResultSet maxCid = databaseAPI.read("SELECT max(cid) from customer;");
        maxCid.next();

        int mCid = maxCid.getInt("max(cid)");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Your user ID is : " + mCid);
        alert.showAndWait();
    }

    public void editingEmail(KeyEvent actionEvent) {
        if (emailPopup != null)
            emailPopup.hide();

        if (!(email.getText().contains("@") && email.getText().contains("."))) {
            emailPopup = PopupMessage.showPopupMessageTextField(PopupMessage.createPopup("Incorrect Email Address!", 1, 16), (Stage) email.getScene().getWindow(), email);
            isEmailNumberCorrect = false;
        }else {
            emailPopup = PopupMessage.showPopupMessageTextField(PopupMessage.createPopup("correct", 0, 16), (Stage) email.getScene().getWindow(), email);
            isEmailNumberCorrect = true;
        }
    }

    public void editingNumber(KeyEvent keyEvent) {
        if(phoneNumberPopup != null)
            phoneNumberPopup.hide();

        isPhoneNumberCorrect = true;

        for (char character : phoneNumber.getText().toCharArray())
            if (!Character.isDigit(character))
                isPhoneNumberCorrect = false;

        if (!isPhoneNumberCorrect)
            phoneNumberPopup = PopupMessage.showPopupMessageTextField(PopupMessage.createPopup("Incorrect Number!", 1, 16), (Stage) email.getScene().getWindow(), phoneNumber);
    }
}
