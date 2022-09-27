package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bus;
import model.CargoLorry;
import model.ObserverList;
import model.Van;

public class AddVehicleFormController extends ObserverList {
    public AnchorPane addVehicleContext;
    public JFXTextField txtVehicleNumber;
    public JFXTextField txtMaxWeight;
    public JFXTextField txtNoOfPassengers;
    public Label lblSuccessful;
    public Label lblError;
    public Label lblError2;
    public Label lblNumberError;
    public Label lblWeightError;
    public Label lblPassengerError;
    public ComboBox cmbBoxVehicleType;
    private String selectType;

    static {
        addVehicle(new Bus("NA-3434","Bus",3500,60));
        addVehicle(new Van("KA-4563","Van",1000,7));
        addVehicle(new Van("58-3567","Van",1500,4));
        addVehicle(new Van("GF-4358","Van",800,4));
        addVehicle(new Van("CCB-3568","Van",1800,8));
        //addVehicle(new Van("LM-6679","Van",1500,4));
        //addVehicle(new Van("QA-3369","Van",1800,6));
        addVehicle(new CargoLorry("KB-3668","Cargo Lorry",2500,2));
        addVehicle(new CargoLorry("JJ-9878","Cargo Lorry",3000,2));
        addVehicle(new CargoLorry("GH-5772","Cargo Lorry",4000,3));
        addVehicle(new CargoLorry("XY-4456","Cargo Lorry",3500,2));
        addVehicle(new CargoLorry("YQ-3536","Cargo Lorry",2000,2));
        //addVehicle(new CargoLorry("CBB-3566","Cargo Lorry",2500,2));
        //addVehicle(new CargoLorry("QH-3444","Cargo Lorry",5000,4));
    }

    public void initialize(){
        txtVehicleNumber.setText("");
        txtNoOfPassengers.setText("");
        txtMaxWeight.setText("");
        cmbBoxVehicleType.getItems().addAll( "Bus", "Van", "Cargo Lorry");
        cmbBoxVehicleType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectType = String.valueOf(newValue);
        });
    }

    public void actionAddVehicle(ActionEvent actionEvent) {
        if(!txtVehicleNumber.getText().equals("")) {
            if (selectType != null) {
                try {
                    if (!txtMaxWeight.getText().equals("")) {
                        if (!txtNoOfPassengers.getText().equals("")) {
                            if (selectType.equals("Bus") & busCount < 1) {
                                addVehicle(new Bus(txtVehicleNumber.getText(), selectType, Integer.valueOf(txtMaxWeight.getText()), Integer.valueOf(txtNoOfPassengers.getText())));
                                lblSuccessful.setVisible(true);
                                clearTxt();
                            }
                            if (selectType.equals("Bus") & busCount >= 1) {
                                lblError.setVisible(true);
                            }
                            if (selectType.equals("Van") & vanCount < 6) {
                                addVehicle(new Van(txtVehicleNumber.getText(), selectType, Integer.valueOf(txtMaxWeight.getText()), Integer.valueOf(txtNoOfPassengers.getText())));
                                lblSuccessful.setVisible(true);
                                clearTxt();
                            }
                            if (selectType.equals("Van") & vanCount >= 6) {
                                lblError.setVisible(true);
                            }
                            if (selectType.equals("Cargo Lorry") & lorryCount < 7) {
                                addVehicle(new CargoLorry(txtVehicleNumber.getText(), selectType, Integer.valueOf(txtMaxWeight.getText()), Integer.valueOf(txtNoOfPassengers.getText())));
                                lblSuccessful.setVisible(true);
                                clearTxt();
                            }
                            if (selectType.equals("Cargo Lorry") & lorryCount >= 7) {
                                lblError.setVisible(true);
                            }
                        } else {
                            lblPassengerError.setVisible(true);
                        }
                    } else {
                        lblWeightError.setVisible(true);
                    }
                }catch (NumberFormatException e){
                    System.out.println(e);
                }
            }else{ lblError2.setVisible(true); }
        }else{  lblNumberError.setVisible(true); }
    }

    private void clearTxt(){
        selectType = null;
        txtMaxWeight.clear();
        txtVehicleNumber.clear();
        txtNoOfPassengers.clear();
        cmbBoxVehicleType.getSelectionModel().clearSelection();
    }

    public void hideError(MouseEvent mouseEvent) {
        lblError.setVisible(false);
        lblError2.setVisible(false);
    }

    public void hideLable(MouseEvent mouseEvent) {
        lblNumberError.setVisible(false);
        lblSuccessful.setVisible(false);
    }

    public void hideNumberError(MouseEvent mouseEvent) {
        lblWeightError.setVisible(false);
        lblPassengerError.setVisible(false);
    }

    public void moveToWeight(ActionEvent actionEvent) {
        txtMaxWeight.requestFocus();
    }

    public void moveToPassengers(ActionEvent actionEvent) {
        txtNoOfPassengers.requestFocus();
    }

    public void moveToType(ActionEvent actionEvent) {
        cmbBoxVehicleType.requestFocus();
    }

    public void closeAddVehicle(MouseEvent mouseEvent) {
        Stage stage = (Stage) addVehicleContext.getScene().getWindow();
        stage.close();
    }
}
