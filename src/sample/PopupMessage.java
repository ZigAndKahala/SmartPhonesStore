package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class PopupMessage {

    public static Popup createPopup(final String message, int type, int fontSize) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.setHideOnEscape(true);
        Label label = new Label(message);
        label.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                popup.hide();
            }
        });


        if (type == 0)
            label.setStyle(" -fx-background-color: #c4ffba; -fx-text-fill: green;");
        else
            label.setStyle(" -fx-background-color: #ffaca4; -fx-text-fill: red;");


        label.setStyle(label.getStyle() +
                    "    -fx-padding: 10;" +
                    "    -fx-font-size: " + fontSize + ";" +
                    "    -fx-background-radius: 10;");


        popup.getContent().add(label);
        return popup;
    }

    public static void showPopupMessageCenter(Popup popupMessage, final Stage stage) {
        final Popup popup = popupMessage;
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
                popup.setY(stage.getY() + stage.getHeight()/2 - popup.getHeight()/2);
            }
        });
        popup.show(stage);
    }

    public static void showPopupMessage(Popup popupMessage, final Stage stage, double x, double y){
        final Popup popup = popupMessage;
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(x);
                popup.setY(y);
            }
        });
        popup.show(stage);
    }

    public static Popup showPopupMessageTextField(Popup popupMessage,final Stage stage, TextField textField){
        final Popup popup = popupMessage;

        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(textField.getLayoutX() + textField.getWidth() + 20);
                popup.setY(textField.getLayoutY() + textField.getHeight()/2 + (textField.getHeight() - popup.getHeight())/2);
            }
        });
        popup.show(stage);
        return popup;
    }

    public static Popup showPopupMessageDateTemp(Popup popupMessage, final Stage stage, Node node){
        final Popup popup = popupMessage;
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(node.getLayoutX() + 306 + 20);
                popup.setY(node.getLayoutY() + 60/2 + (60 - popup.getHeight())/2);
            }
        });
        popup.show(stage);
        return popup;
    }
}
