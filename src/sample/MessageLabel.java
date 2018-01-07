package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MessageLabel {

    public static Label createLabel(String message, int type, int fontSize){
        Label label = new Label(message);

        if (type == 0)
            label.setStyle(" -fx-background-color: #c4ffba; -fx-text-fill: green;");
        else
            label.setStyle(" -fx-background-color: #ffaca4; -fx-text-fill: red;");

        label.setStyle(label.getStyle() +
                "    -fx-padding: 10;" +
                "    -fx-font-size: " + fontSize + ";" +
                "    -fx-background-radius: 10;");

        return label;
    }

    public static Label displayTheMessageBesidesTextField(Label label, AnchorPane anchorPane, TextField textField){
        label.setLayoutX(textField.getLayoutX() + textField.getWidth() + 20);
        label.setLayoutY(textField.getLayoutY());

        anchorPane.getChildren().add(label);
        return label;
    }
}
