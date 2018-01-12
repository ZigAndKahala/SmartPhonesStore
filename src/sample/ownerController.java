package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ownerController {
    public ChoiceBox employees;
    public TextField salaty;
    public TextField day;
    public TextField password;
    public ChoiceBox city;
    public TextField street;
    public AnchorPane rootPane;
    public ChoiceBox type;
    private int oid;

    public TextField name;
    public TextField number;
    public TextField email;
    public TextField id;
    public Button clear;
    public DatePicker dat;
    public Button addemployee;
    public TableView incomingTable;
    public TableView sellingTable;


    private Label emailMessage;
    private Label phoneNumberMessage;

    private Boolean isPhoneNumberCorrect = true;
    private Boolean isEmailNumberCorrect = true;


    ObservableList<String> mainTypeList = FXCollections.observableArrayList("Casher","Cleaner","seller");

    public void initialize() throws SQLException, ClassNotFoundException {
        city.getItems().addAll(FXCollections.observableArrayList(
                "بيت لاهيا","جباليا","رفح","خانيونس","دير البلح","بيت حانون","غزة","المغازي","الناصرة","عكا","نهاريا","عسقلان","الخضيرة","حيفا","يافا","طبريا","صفد","طبريا","شفا عمرو","دير الأسد","طمرة","جبل الجرمق","جبل كنعان","جبل عروس","جبل الحيدر","جبل الكرمل","سخنين","بيسان","عسفيا","الناصرة","ام الفحم","فصايل","الزبيدات","مرج نعجة","الفشخة","العوجا","الجفتلك","اريحا","حجة","فرعتا","حبلة","عزون","قلقيلية","أماتين","كفر ثلث","سنيريا","جينصافوط","الفندق","كفر لاقف","جيوس","ابو ديس","بيت لقيا","بيت عنان","بدو","عناتا","صور باهر","مخماس","الرملة","القدس","قطنة","بيت صفافا","حزما","الرام","العيزرية","شعفاط","بيت حنينا","جامعة القدس - ابوديس","دمشق","عين يبرود","دير دبوان","الطيبة","عارورة","بيتللو","الجانية","عطارة","كوبر","بيتونيا","رام الله","سلواد","كفر مالك","بتين","المزرعة الشرقية","بلعين","ترمسعيا","سنجل","بيت لقيا","المغير","عين عريك","عابود","دير عمار","بيت ريما","مزارع النوباني","المزرعة الغربية","عجول","بيت سيرا","دير غسانه","ابو فلاح","البيرة","دير ابو مشعل","دير ابزيغ","الجلزون","كفر عين","رمون","بير زيت","بيت عور الفوقا","جفنا","دير جرير","بيت عور التحتا","دير سودان","بير نبالا","عبوين","نعلين","شقبا","تل العاصور","روابي","كفر نعمة","رنتيس","جامعة بيرزيت","جمالا","جنين","بيت فجار","بيت اولا","بيت امر","حلحول","دورا","سعير","الظاهرية","الشيوخ","الفوار","اذنا","بني نعيم","الخليل","راس طورة","بيت عوا","بيت كاحل","تفوح","العروب","خاراس","ترقوميا","يطا","ديرسامت","رأس الجورة","نوبا","صوريف","السموع","خلة بطرخ","الحرم الإبراهيمي","بيت عينون","جامعة الخليل","جامعة البوليتكنيك","بيت لحم","برقة","سبسطية","بزاريا","بيت امرين","قوصين","دير شرف","جماعين","بورين","حوارة","جيت","قريوت","الساوية","مادما","طلوزة","نابلس","عصيرة الشمالية","عقربا","بيت فوريك","كفر قدوم","عورتا","سالم","قصرة","عينبوس","دوما","بيت دجن","جبل جرزيم","جبل عيبال","تلفيت","يتما","اسكاكا","ياسوف","عصيرة القبلية","الباذان","عراق بورين","صرة","قبلان","تل","ياصيد","اوصرين","بديا","جوريش","اللبن الشرقية","الناقورة","بيتا","عوريف","بيت ايبا","العقربانية","جامعة النجاح - الحرم القديم","جامعة النجاح - الحرم الجديد","نابلس الجديدة","نابلس","الخضر","تقوع","بيت لحم","أرطاس","حوسان","نحالين","العبيدية","الولجة","بيت جالا","ارطاس","بيت ساحور","بتير","جامعة بيت لحم","فقوعة","الزبابدة","عجة","الفندقومية","رمانه","مسلية","سيريس","الهاشمية","كفيرت","الجديدة","جبع (جنين)","جنين","السيلة الحارثية","قباطية","كفر راعي","ميثلون","اليامون","سيلة الظهر","صانور","برقين","يعبد","جلقموس","كفر قود","عانين","جلبون","عنزة","الرامة","العرقة","عرانة","زبوبا","عرابة","كفر دان","تعِنك","ام التوت","فحمة","قباطية","دير غزاله","رابا","دير ابو ضعيف","الجامعة العربية الأمريكية","رام الله","صيدا","رامين","كفر اللبد","علار","طولكرم","النزلات","دير الغصون","قفين","زيتا","عنبتا","سفارين","شويكة","الجاروشية","بلعا","شوفة","الكفريات","عتيل","الراس","جامعة خضوري","بيت ليد","فرعون","عقابا","تياسير","طمون","بردلة","طوباس","كفر الديك","الزاوية","دير استيا","دير بلوط","بروقين","كفل حارس","مسحة","خربة قيس","قيرة","عمورية","سلفيت","فرخة","رافات","سرطة","مردة","حارس","عمان","معان","السلط","عجلون","اربد","مادبا"
        ));
        int count=0,i;
        DatabaseAPI databaseAPI = new DatabaseAPI();


        ResultSet maxeid = databaseAPI.read("SELECT max(eid) from employee;");
        maxeid.next();
        int meid = maxeid.getInt("max(eid)");
        String[] employeesName = new String[meid];
        ResultSet emname = databaseAPI.read("SELECT name from employee;");
        while(emname.next())
        {
            employeesName[count] =emname.getString("name");
            count+=1;
        }
        ObservableList<String> mainTypeListaccessories = FXCollections.observableArrayList(employeesName);
        employees.setItems(mainTypeListaccessories);

        type.setItems(mainTypeList);
        //TODO : resize the two tables to the user Main.screenWidth & Main.screenHeight
    }

    public void clearAll(ActionEvent actionEvent) {
        name.setText("");
        number.setText("");
        password.setText("");
        dat.setValue(null);
        city.setValue(null);
        street.setText("");
        email.setText("");
        id.setText("");
    }

    @SuppressWarnings("Duplicates")
    public void signOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signInScreen.fxml"));
        Stage stage = (Stage) name.getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root,Main.screenWidth,Main.screenHeight);
        stage.setScene(scene);
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void addSalary(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        List<String> parameters = new ArrayList<>();
        parameters.add(day.getText());
        parameters.add(salaty.getText());
        DatabaseAPI databaseAPI = new DatabaseAPI();
        databaseAPI.write("INSERT INTO salary " + DatabaseAPI.generateSqlCommand("paymentDay,salaryAmount",parameters));

        ResultSet sId = databaseAPI.read("SELECT max(sid) FROM promotions");
        sId.next();
        int sid = sId.getInt(1);
        String nameEmpis = (String) employees.getValue();
        ResultSet eId = databaseAPI.read("SELECT eid FROM employee where name= \" " + nameEmpis + "\"");
         eId.next();
         int eid = eId.getInt(1);
        databaseAPI.write("UPDATE salary SET eid = " + eid + " WHERE sid = " + sid );
        databaseAPI.write("UPDATE salary SET oid = " + oid + " WHERE sid = " + sid );



    }



    public void addemployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        LocalDate localDate = dat.getValue();

        String date = null;
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

        List<String> employeeParameters = new ArrayList<>();
        employeeParameters.add(name.getText());
        employeeParameters.add(email.getText());
        employeeParameters.add(password.getText());
        employeeParameters.add(date);
        employeeParameters.add(number.getText());
        String ttype = (String) type.getValue();
        employeeParameters.add(ttype);


        ResultSet cityid = databaseAPI.read("Select cityid from city where name = " + "\"" + city.getSelectionModel().getSelectedItem().toString() + "\"");
        cityid.next();
        int cityId = cityid.getInt("cityId");

        List<String> addressParameters = new ArrayList<>();
        addressParameters.add(String.valueOf(cityId));
        addressParameters.add(street.getText());

        databaseAPI.write("INSERT INTO employee " + DatabaseAPI.generateSqlCommand("name,email,password,mobileNumber,dateOfBirth,type",employeeParameters));
        databaseAPI.write("INSERT INTO address " + DatabaseAPI.generateSqlCommand("cityId,streetName",addressParameters));
        ResultSet maxEid = databaseAPI.read("SELECT max(eid) from employee;");
        maxEid.next();
        int mEid = maxEid.getInt("max(eid)");

        ResultSet maxAid = databaseAPI.read("SELECT max(aid) from address;");
        maxAid.next();
        int mAid = maxAid.getInt("max(aid)");

        databaseAPI.write("INSERT INTO employeelivesin (eid,aid) Values (" + mEid + "," + mAid + ");");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Your user ID is : " + mEid);
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

        for (char character : number.getText().toCharArray())
            if (!Character.isDigit(character))
                isPhoneNumberCorrect = false;

        if (!isPhoneNumberCorrect) {
            if (phoneNumberMessage == null)
                phoneNumberMessage = MessageLabel.displayTheMessageBesidesTextField(MessageLabel.createLabel("Incorrect Phone Number!", 1, 20), rootPane, number);
        }else {
            if (phoneNumberMessage != null)
                rootPane.getChildren().remove(phoneNumberMessage);
            phoneNumberMessage = null;
        }
    }

    private Boolean isAllRequiredFieldsFilled(){
        return !name.getText().equals("") && !email.getText().equals("") && !number.getText().equals("") &&
                !password.getText().equals("") && dat != null && city.getSelectionModel().getSelectedIndex() != -1;
    }
}
