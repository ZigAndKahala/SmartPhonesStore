package sample;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    public ChoiceBox city;
    public TextField street;
    public AnchorPane rootPane;

    private Label emailMessage;
    private Label phoneNumberMessage;

    private Boolean isPhoneNumberCorrect = true;
    private Boolean isEmailNumberCorrect = true;

    private String date = null;

    public void initialize(){
        city.getItems().addAll(FXCollections.observableArrayList(
                "بيت لاهيا","جباليا","رفح","خانيونس","دير البلح","بيت حانون","غزة","المغازي","الناصرة","عكا","نهاريا","عسقلان","الخضيرة","حيفا","يافا","طبريا","صفد","طبريا","شفا عمرو","دير الأسد","طمرة","جبل الجرمق","جبل كنعان","جبل عروس","جبل الحيدر","جبل الكرمل","سخنين","بيسان","عسفيا","الناصرة","ام الفحم","فصايل","الزبيدات","مرج نعجة","الفشخة","العوجا","الجفتلك","اريحا","حجة","فرعتا","حبلة","عزون","قلقيلية","أماتين","كفر ثلث","سنيريا","جينصافوط","الفندق","كفر لاقف","جيوس","ابو ديس","بيت لقيا","بيت عنان","بدو","عناتا","صور باهر","مخماس","الرملة","القدس","قطنة","بيت صفافا","حزما","الرام","العيزرية","شعفاط","بيت حنينا","جامعة القدس - ابوديس","دمشق","عين يبرود","دير دبوان","الطيبة","عارورة","بيتللو","الجانية","عطارة","كوبر","بيتونيا","رام الله","سلواد","كفر مالك","بتين","المزرعة الشرقية","بلعين","ترمسعيا","سنجل","بيت لقيا","المغير","عين عريك","عابود","دير عمار","بيت ريما","مزارع النوباني","المزرعة الغربية","عجول","بيت سيرا","دير غسانه","ابو فلاح","البيرة","دير ابو مشعل","دير ابزيغ","الجلزون","كفر عين","رمون","بير زيت","بيت عور الفوقا","جفنا","دير جرير","بيت عور التحتا","دير سودان","بير نبالا","عبوين","نعلين","شقبا","تل العاصور","روابي","كفر نعمة","رنتيس","جامعة بيرزيت","جمالا","جنين","بيت فجار","بيت اولا","بيت امر","حلحول","دورا","سعير","الظاهرية","الشيوخ","الفوار","اذنا","بني نعيم","الخليل","راس طورة","بيت عوا","بيت كاحل","تفوح","العروب","خاراس","ترقوميا","يطا","ديرسامت","رأس الجورة","نوبا","صوريف","السموع","خلة بطرخ","الحرم الإبراهيمي","بيت عينون","جامعة الخليل","جامعة البوليتكنيك","بيت لحم","برقة","سبسطية","بزاريا","بيت امرين","قوصين","دير شرف","جماعين","بورين","حوارة","جيت","قريوت","الساوية","مادما","طلوزة","نابلس","عصيرة الشمالية","عقربا","بيت فوريك","كفر قدوم","عورتا","سالم","قصرة","عينبوس","دوما","بيت دجن","جبل جرزيم","جبل عيبال","تلفيت","يتما","اسكاكا","ياسوف","عصيرة القبلية","الباذان","عراق بورين","صرة","قبلان","تل","ياصيد","اوصرين","بديا","جوريش","اللبن الشرقية","الناقورة","بيتا","عوريف","بيت ايبا","العقربانية","جامعة النجاح - الحرم القديم","جامعة النجاح - الحرم الجديد","نابلس الجديدة","نابلس","الخضر","تقوع","بيت لحم","أرطاس","حوسان","نحالين","العبيدية","الولجة","بيت جالا","ارطاس","بيت ساحور","بتير","جامعة بيت لحم","فقوعة","الزبابدة","عجة","الفندقومية","رمانه","مسلية","سيريس","الهاشمية","كفيرت","الجديدة","جبع (جنين)","جنين","السيلة الحارثية","قباطية","كفر راعي","ميثلون","اليامون","سيلة الظهر","صانور","برقين","يعبد","جلقموس","كفر قود","عانين","جلبون","عنزة","الرامة","العرقة","عرانة","زبوبا","عرابة","كفر دان","تعِنك","ام التوت","فحمة","قباطية","دير غزاله","رابا","دير ابو ضعيف","الجامعة العربية الأمريكية","رام الله","صيدا","رامين","كفر اللبد","علار","طولكرم","النزلات","دير الغصون","قفين","زيتا","عنبتا","سفارين","شويكة","الجاروشية","بلعا","شوفة","الكفريات","عتيل","الراس","جامعة خضوري","بيت ليد","فرعون","عقابا","تياسير","طمون","بردلة","طوباس","كفر الديك","الزاوية","دير استيا","دير بلوط","بروقين","كفل حارس","مسحة","خربة قيس","قيرة","عمورية","سلفيت","فرخة","رافات","سرطة","مردة","حارس","عمان","معان","السلط","عجلون","اربد","مادبا"
        ));
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

        if (localDate != null)
            date = localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth();

        if(!isAllRequiredFieldsFilled()){
            Stage thisStage = (Stage) name.getScene().getWindow();
            PopupMessage.showPopupMessageCenter(PopupMessage.createPopup("Please Fill All The Fields!",1,30),thisStage);
            return;
        }else if(!isEmailNumberCorrect || !isPhoneNumberCorrect){
            return;
        }

        DatabaseAPI databaseAPI = new DatabaseAPI();

        List<String> customerParameters = new ArrayList<>();
        customerParameters.add(name.getText());
        customerParameters.add(email.getText());
        customerParameters.add(password.getText());
        customerParameters.add(phoneNumber.getText());
        customerParameters.add(date);

        ResultSet cityid = databaseAPI.read("Select cityid from city where name = " + "\"" + city.getSelectionModel().getSelectedItem().toString() + "\"");
        cityid.next();
        int cityId = cityid.getInt("cityId");

        List<String> addressParameters = new ArrayList<>();
        addressParameters.add(String.valueOf(cityId));
        addressParameters.add(street.getText());

        databaseAPI.write("INSERT INTO customer " + DatabaseAPI.generateSqlCommand("name,email,password,mobileNumber,dateOfBirth",customerParameters));
        databaseAPI.write("INSERT INTO address " + DatabaseAPI.generateSqlCommand("cityId,streetName",addressParameters));

        ResultSet maxCid = databaseAPI.read("SELECT max(cid) from customer;");
        maxCid.next();
        int mCid = maxCid.getInt("max(cid)");

        ResultSet maxAid = databaseAPI.read("SELECT max(aid) from address;");
        maxAid.next();
        int mAid = maxAid.getInt("max(aid)");

        databaseAPI.write("INSERT INTO customerlivesin (cid,aid) Values (" + mCid + "," + mAid + ");");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Your user ID is : " + mCid);
        alert.showAndWait();
    }

    public void editingEmail(KeyEvent keyEvent) {
        if (!(email.getText().contains("@") && email.getText().contains("."))) {
            if (emailMessage == null)
                emailMessage = MessageLabel.displayTheMessageBesidesTextField(MessageLabel.createLabel("Incorrect Email Address!",1,20),rootPane,email);
            isEmailNumberCorrect = false;
        }else {
            isEmailNumberCorrect = true;
            if(emailMessage != null)
                rootPane.getChildren().remove(emailMessage);
            emailMessage = null;
        }
    }

    public void editingNumber(KeyEvent keyEvent) {
        isPhoneNumberCorrect = true;

        for (char character : phoneNumber.getText().toCharArray())
            if (!Character.isDigit(character))
                isPhoneNumberCorrect = false;

        if (!isPhoneNumberCorrect) {
            if (phoneNumberMessage == null)
                phoneNumberMessage = MessageLabel.displayTheMessageBesidesTextField(MessageLabel.createLabel("Incorrect Phone Number!", 1, 20), rootPane, phoneNumber);
        }else {
            if (phoneNumberMessage != null)
                rootPane.getChildren().remove(phoneNumberMessage);
            phoneNumberMessage = null;
        }
    }

    private Boolean isAllRequiredFieldsFilled(){
        return !name.getText().equals("") && !email.getText().equals("") && !phoneNumber.getText().equals("") &&
                !password.getText().equals("") && date != null && city.getSelectionModel().getSelectedIndex() != -1;
    }

    public void showPassword(MouseEvent mouseEvent) {
    // Todo
//        password.
    }
}
