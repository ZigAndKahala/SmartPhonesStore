package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ownerController {
    public TextField name;
    public TextField number;
    public TextField email;
    public TextField id;
    public Button clear;
    public DatePicker dat;
    public Button addemployee;

    public void clearall(ActionEvent actionEvent) {
        name.setText("");
        number.setText("");
        email.setText("");
        id.setText("");
    }
}
