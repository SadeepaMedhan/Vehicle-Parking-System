package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import model.ObserverList;

public class AddDriverFormController extends ObserverList {
    public AnchorPane addDriverContext;
    public JFXTextField txtDriverName;
    public JFXTextField txtDriverNIC;
    public JFXTextField txtDriverLicenceNo;
    public JFXTextField txtDriverAddress;
    public JFXTextField txtDriverContact;
    public Label lblSuccessful;
    public Label lblError;
    public Label lblError2;

    static {
        addDriver(new Driver("Sumith Kumara","7835348345V","B6474845","Panadura","0725637456"));
        addDriver(new Driver("Amila Pathirana ","8826253734V","B3354674","Galle","0717573583"));
        addDriver(new Driver("Jithmal Perera","9283289272V","B3674589","Horana","0772452457"));
        addDriver(new Driver("Sumith Dissanayaka","9425245373V","B8366399","Kaluthara","0782686390"));
        addDriver(new Driver("Sumanasiri Herath","8976544373V","B3537538","Beruwala","0772534436"));
        addDriver(new Driver("Awantha Fernando","9173537839V","B3554789","Colombo 5","0714534356"));
        addDriver(new Driver("Charith Sudara","9573536833V","B6835836","Baththaramulla","0771536662"));
        addDriver(new Driver("Prashan Dineth","9362426738V","B2683536","Wadduwa","0715353434"));
        addDriver(new Driver("Chethiya Dilan","9162353436V","B6836836","Panadura","0772436737"));
        addDriver(new Driver("Dushantha Perera","9255556343V","B3334435","Matara","0777245343"));
        addDriver(new Driver("Sumith Udayanga","8735354355V","B3573783","Galle","0703635442"));
        addDriver(new Driver("Dinesh Udara","9026344373V","B5343783","Hettimulla","0713456878"));
        //addDriver(new Driver("Udana Chathuranga","9692653338V","B7888632","Kottawa","0772442444"));
        //addDriver(new Driver("Mohommad Riaz","9124537733V","B3638537","Kaluthara","0777544222"));
        //addDriver(new Driver("Sandun Kumara","9563524267V","B2263333","Panadura","0772325544"));
        //addDriver(new Driver("Priyanga Perera","9135343537V","B3853753","Matara","0723344562"));
    }

    public void initialize(){
        txtDriverName.setText("");
   }

    public void actionAddDriver(ActionEvent actionEvent) {
        if((!txtDriverName.getText().equals("")) & getDriversIndex()<16) {
            addDriver(new Driver(txtDriverName.getText(), txtDriverNIC.getText(), txtDriverLicenceNo.getText(), txtDriverAddress.getText(), txtDriverContact.getText()));
            lblSuccessful.setVisible(true);
            clearTxt();
        }else if((txtDriverName.getText().equals("")) & getDriversIndex()<16){
            lblError2.setVisible(true);
        }else if(getDriversIndex()<16){
            lblError.setVisible(true);
        }
    }

    private void clearTxt() {
        txtDriverName.clear();
        txtDriverNIC.clear();
        txtDriverLicenceNo.clear();
        txtDriverAddress.clear();
        txtDriverContact.clear();
    }

    public void hideLabel(MouseEvent mouseEvent) {
        lblError2.setVisible(false);
        lblSuccessful.setVisible(false);
    }

    public void moveToNIC(ActionEvent actionEvent) {
        txtDriverNIC.requestFocus();
    }

    public void moveToLicence(ActionEvent actionEvent) {
        txtDriverLicenceNo.requestFocus();
    }

    public void moveToAddress(ActionEvent actionEvent) {
        txtDriverAddress.requestFocus();
    }

    public void moveToContact(ActionEvent actionEvent) {
        txtDriverContact.requestFocus();
    }

    public void closeAddDriver(MouseEvent mouseEvent) {
        Stage stage = (Stage) addDriverContext.getScene().getWindow();
        stage.close();
    }
}
