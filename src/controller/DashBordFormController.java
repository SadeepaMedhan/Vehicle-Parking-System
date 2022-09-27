package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DashBordFormController extends ObserverList{
    public AnchorPane dashBoardContext;
    public ComboBox cmbBoxDriver;
    public ComboBox cmbBoxSelectVehicle;
    public Label lblParkingSlotNumber;
    public Label lblVehicleType;
    public Label lblDate;
    public Label lblTime;
    public Label lblDriverError;
    public Label lblDriverError2;
    public JFXButton btnPark;
    public JFXButton btnDelivery;
    public JFXButton btnManagement;
    public JFXButton btnClose;
    public ImageView errorImage;

    private String selectVehicleNumber;
    private String selectedDriverName;
    private int availableSlot;
    private static boolean stop = false;

    String[] driverName=new String[getDriversIndex()];
    String[] vehicleNumber=new String[getVehicleIndex()];

    private void timeNow(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm:ss aa");
            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    //System.out.println(e);
                }
                final String time = formatterTime.format(new Date());
                Platform.runLater(()->{
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }

    public void logout(){
        stop=true;
        Stage stage = (Stage) dashBoardContext.getScene().getWindow();
        stage.close();
    }


    public void initialize(){
        timeNow();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm:ss aa");
        lblDate.setText(formatter.format(new Date()));
        nowTime=formatter.format(new Date())+" - "+formatterTime.format(new Date());
        
        btnPark.setDisable(true);
        btnDelivery.setDisable(true);
        btnPark.setStyle("-fx-background-color: #16a085;-fx-background-radius: 10;-fx-background-insets: 0");
        btnDelivery.setStyle("-fx-background-color: #16a085;-fx-background-radius: 10;-fx-background-insets: 0");

        for (int i = 0; i < getVehicleIndex(); i++) { vehicleNumber[i] = getVehicleList()[i].getVehicleNumber(); }

        cmbBoxSelectVehicle.getItems().addAll(vehicleNumber);
        cmbBoxSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectVehicleNumber = String.valueOf(newValue);
            try {
                check();
                //cmbBoxDriver.getSelectionModel().clearSelection();
                selectedDriverName=null;
            }catch (Exception e){}
        });

        for (int i = 0; i < getDriversIndex(); i++) { driverName[i]=getDrivers()[i].getDriverName(); }

        cmbBoxDriver.getItems().addAll(driverName);
        cmbBoxDriver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedDriverName = String.valueOf(newValue);
        });
    }


    private void clearDetails(){
        lblVehicleType.setText("");
        cmbBoxSelectVehicle.getSelectionModel().clearSelection();
        cmbBoxDriver.getSelectionModel().clearSelection();
    }

    private void check() {
        if(isOnPark(selectVehicleNumber)){
            System.out.println(selectVehicleNumber+" is on parked");
            goDeliveryStep();
        }else if(isOnDelivery(selectVehicleNumber)){
            System.out.println(selectVehicleNumber+" is on delivery");
            goPark();
        }
    }

    private void goPark() {
        availableSlot=getAvailableSlot(selectVehicleNumber);
        lblParkingSlotNumber.setText(String.valueOf(availableSlot));
        cmbBoxDriver.getSelectionModel().select(getDriverNameFromVehicle(selectVehicleNumber));
        btnPark.setDisable(false);
        btnDelivery.setDisable(true);
        setVehicleType();
    }

    public String getDriverNameFromVehicle(String selectVehicleNumber) {
        for (Vehicle tempVehicle:getVehicleList()){
            if(tempVehicle.getVehicleNumber().equals(selectVehicleNumber)){
                for(Driver tempDriver:getDrivers()){
                    if(tempVehicle.getDriverNIC().equals(tempDriver.getNIC())){
                        //System.out.println(tempDriver.getDriverName());
                        return tempDriver.getDriverName();
                    }
                }
            }
        }
        return null;
    }

    private void goDeliveryStep() {
        cmbBoxDriver.getSelectionModel().clearSelection();
        btnPark.setDisable(true);
        btnDelivery.setDisable(false);
        lblParkingSlotNumber.setText("--"/*String.valueOf(getSlot(selectVehicleNumber))*/);
        setVehicleType();
    }

    public void setVehicleType() {
        for (Vehicle temp : getVehicleList()) {
            if (selectVehicleNumber.equalsIgnoreCase(temp.getVehicleNumber())) {
                lblVehicleType.setText(temp.getVehicleType());
            }
        }
    }

    public void selectedVehiclePark(ActionEvent actionEvent){
        for(Vehicle v1:vehicles) {
            if (v1 != null) {
                v1.park(selectVehicleNumber, availableSlot, nowTime);
            }
        }
        btnPark.setDisable(true);
        clearDetails();
        lblParkingSlotNumber.setText("--");
        //System.out.println("Parked "+Arrays.toString(slots));
    }

    public void selectedVehicleDelivery(ActionEvent actionEvent) throws IOException{
        if (selectedDriverName != null) {
            gotoDelivery();
        } else {
            lblDriverError.setVisible(true);
            errorImage.setVisible(true);
        }
    }

    private void gotoDelivery(){
        if(!checkSelectDriverIsOn(selectedDriverName)){
            for(Vehicle v1:vehicles) {
                if(v1 != null) {
                    v1.leavePark(selectVehicleNumber, selectedDriverName, nowTime);
                }
            }
            btnDelivery.setDisable(true);
            selectedDriverName=null;
            clearDetails();
        }else{
            lblDriverError2.setVisible(true);
            errorImage.setVisible(true);
        }
    }

    public void openManagementForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ManagementLoginForm.fxml"));
        Parent parent = loader.load();
        ManagementLoginFormController controller = loader.getController();
        Scene scene= new Scene(parent);
        Stage stage = new Stage();
        controller.setStageClose(stage,dashBoardContext);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void hideDriverError(MouseEvent mouseEvent) {
        lblDriverError.setVisible(false);
        lblDriverError2.setVisible(false);
        errorImage.setVisible(false);
    }

    public void openParkingSlots(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/ParkingSlotsForm.fxml");
        Parent load =FXMLLoader.load(resource);
        Scene scene= new Scene(load);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void closeWindow(ActionEvent actionEvent) {
        logout();
    }

    public void changeClourRed(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #d41717;");
    }

    public void changeClourBlue(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color:  #130f40;");
    }
}
