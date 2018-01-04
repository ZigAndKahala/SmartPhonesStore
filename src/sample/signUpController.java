package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class signUpController {
    public TextField name;
    public TextField email;
    public TextField password;
    public TextField phoneNumber;
    public DatePicker dateOfBirth;
    public Button signUp;
    public Button clear;
    public Button back;

    public void initialize(){

    }

    public void clear(ActionEvent actionEvent) {
        name.setText("");
        email.setText("");
        phoneNumber.setText("");
    }

    public void backToSignIn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signInScreen.fxml"));
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }

    public void signUp(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LocalDate localDate = dateOfBirth.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);


        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.write("insert INTO customer (name, email,password,mobileNumber) VALUES ("+ "\"" + name.getText() + "\",\"" + email.getText() + "\",\"" + password.getText() + "\"," + Integer.parseInt(phoneNumber.getText()) + ");");

        ResultSet maxCid = databaseAPI.read("SELECT max(cid) from customer;");
        maxCid.next();

        int mCid = maxCid.getInt("max(cid)");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Your user ID is : " + mCid);
        alert.showAndWait();
    }

    private static void refresh() {
        try {
            DatabaseAPI db = new DatabaseAPI();
            String sql = "Select * from customer";

            ResultSet result = db.read(sql);
            StringBuilder emps = new StringBuilder();
            while(result.next()){
                emps.append(result.getString(1)).append("\t").append(result.getString(2)).append("\t").append(result.getString(3)).append("\n");
            }
            System.out.println(emps);
            db.connection.close();
            db.connection = null;
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
