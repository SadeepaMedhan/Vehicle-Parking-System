package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Driver;
import model.ObserverList;
import model.Vehicle;
import view.tm.DeliveredVehicleData;
import view.tm.ParkedVehicleData;
import view.tm.VehiclesTM;

import java.io.IOException;
import java.net.URL;

public class ManagerFormController extends ObserverList {
    public AnchorPane managerFormContxt;

    public TableView tblOnDelivery;
    public ComboBox cmbBoxIsSelect;
    public TableColumn colDeliveryVehicleNumber;
    public TableColumn colDeliveryVehicleType;
    public TableColumn colLeftTime;
    public TableColumn colDeliveryDriverName;

    public TableView tblInParking;
    public TableColumn colParkedVehicleNumber;
    public TableColumn colParkedVehicleType;
    public TableColumn colParkedSlot;
    public TableColumn colParkedTime;

    public TableView tblVehicles;
    public TableColumn colVNumber;
    public TableColumn colMaxWeight;
    public TableColumn colVNoOfPassenger;
    public TableColumn colVSlot;
    public TableColumn colVType;
    public TableColumn colVDriver;

    public TableView tblDrivers;
    public TableColumn colDriverName;
    public TableColumn colDriverNIC;
    public TableColumn colLicenseNo;
    public TableColumn colAddress;
    public TableColumn colContact;

    public JFXButton btnPark;
    public JFXButton btnDelivery;
    public JFXButton btnDrivers;
    public JFXButton btnVehicles;
    public JFXButton btnAddVehicle;
    public JFXButton btnAddDriver;
    public Label lblVCount;
    public Label lblDCount;

    //private String selectOption="In Parking";

    Vehicle[] inParkTableData=new Vehicle[14];
    Vehicle[] onDeliveryArray=new Vehicle[14];

    public void initialize(){
        lblVCount.setText(String.valueOf(busCount+vanCount+lorryCount));
        lblDCount.setText(String.valueOf(driversIndex));

        /*cmbBoxIsSelect.setStyle("-fx-background-color: #d9dadc");

        resetTable();
        cmbBoxIsSelect.getItems().addAll( "In Parking", "On Delivery", "Vehicle Table", "Driver Table");
        cmbBoxIsSelect.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectOption = String.valueOf(newValue);
            resetTable();
        });*/

        colDeliveryVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colDeliveryVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDeliveryDriverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory<>("leftTime"));

        colParkedVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colParkedVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colParkedSlot.setCellValueFactory(new PropertyValueFactory<>("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory<>("parkedTime"));

        colVNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colMaxWeight.setCellValueFactory(new PropertyValueFactory<>("maximumWeight"));
        colVNoOfPassenger.setCellValueFactory(new PropertyValueFactory<>("numberOfPassengers"));
        colVDriver.setCellValueFactory(new PropertyValueFactory<>("driverNIC"));
        colVSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));

        colDriverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colDriverNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colLicenseNo.setCellValueFactory(new PropertyValueFactory<>("driverLicenceNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    }

    public void loadInParkingArray(){
        int index=0;
        for (String slot : slots) {
            if (slot != null) {
                for (Vehicle v: vehicles) {
                    if (v != null) {
                        if (v.getSlot() != 0) {
                            if (slot.equals(v.getVehicleNumber())) { inParkTableData[index++] = v;
                            }
                        }
                    }
                }
            }
        }
        /*for (int i = 0; i < vehicles.length; i++) {
            if(vehicles[i]!=null) {
                if(vehicles[i].getSlot()!=0) {
                    for (String slot : slots) {
                        if (slot != null) {
                            if (slot.equals(vehicles[i].getVehicleNumber())) { inParkTableData[i] = vehicles[i];
                            }
                        }
                    }
                }
            }
        }*/
    }

    public void loadOnDeliveryArray(){
        for (int i = 0; i < vehicles.length; i++) {
            if(vehicles[i]!=null) {
                for (String deliverVehicle : deliveredVehicle) {
                    if (deliverVehicle != null) {
                        if (vehicles[i].getVehicleNumber().equals(deliverVehicle)) { onDeliveryArray[i] = vehicles[i];
                        }
                    }
                }
            }
        }
    }

    private void loadDeliveredTable() {
        ObservableList<DeliveredVehicleData> onDeliveredObserver = FXCollections.observableArrayList();
        for(Vehicle temp:onDeliveryArray){
            if(temp!=null) {
                onDeliveredObserver.add(new DeliveredVehicleData(temp.getVehicleNumber(), temp.getVehicleType(), getDriverName(temp.getDriverNIC()) , temp.getTime()));
            }
        }
        tblOnDelivery.setItems(onDeliveredObserver);
    }

    private void loadTableData() {
        ObservableList<ParkedVehicleData> inParkObserver = FXCollections.observableArrayList();
        for(Vehicle temp:inParkTableData){
            if(temp!=null) {
                inParkObserver.add(new ParkedVehicleData(temp.getVehicleNumber(), temp.getVehicleType(), String.valueOf(temp.getSlot()), temp.getTime()));
            }
        }
        tblInParking.setItems(inParkObserver);
    }

    private void loadAllVehicles() {
        ObservableList<VehiclesTM> vehiclesTMObservableList = FXCollections.observableArrayList();
        for(Vehicle temp:getVehicleList()){
            if(temp!=null) {
                String inSlotNumber;
                if(temp.getSlot()==0){inSlotNumber="-";}else{inSlotNumber=temp.getSlot()+"";}
                vehiclesTMObservableList.add(new VehiclesTM(temp.getVehicleNumber(), temp.getVehicleType(),temp.getMaximumWeight(),temp.getNumberOfPassengers(), getDriverName(temp.getDriverNIC()),inSlotNumber));
            }
        }
        tblVehicles.setItems(vehiclesTMObservableList);
    }

    private void loadAllDrivers() {
        ObservableList<Driver> driverObservableList = FXCollections.observableArrayList();
        for(Driver temp:getDrivers()){
            if(temp!=null) {
                driverObservableList.add(new Driver(temp.getDriverName(), temp.getNIC(),temp.getDriverLicenceNumber(),temp.getAddress(),temp.getContactNumber()));
            }
        }
        tblDrivers.setItems(driverObservableList);
    }

    private String getDriverName(String driverNIC) {
        for(Driver d:getDrivers()){
            if(d != null) {
                if (d.getNIC().equals(driverNIC)) { return d.getDriverName(); }
            }
        }
        return "-";
    }

/*    public void resetTable(){
        if(selectOption.equals("In Parking")){
            loadInParkingArray();
            loadTableData();
            setTableVisible(tblInParking);
        }
        if(selectOption.equals("On Delivery")){
            loadOnDeliveryArray();
            loadDeliveredTable();
            setTableVisible(tblOnDelivery);
        }
        if(selectOption.equals("Vehicle Table")){
            loadAllVehicles();
            setTableVisible(tblVehicles);
        }
        if(selectOption.equals("Driver Table")){
            loadAllDrivers();
            setTableVisible(tblDrivers);
        }
    }*/

    private void setTableVisible(TableView table) {
        tblInParking.setVisible(false);
        tblOnDelivery.setVisible(false);
        tblVehicles.setVisible(false);
        tblDrivers.setVisible(false);
        table.setVisible(true);
    }

    public void openAddVehicleForm(ActionEvent actionEvent) throws IOException {
        changeButtonColor(btnAddVehicle);
        if(stage!=null) {
            stage.close();
        }
        openForms("../view/AddVehicleForm.fxml","Add Vehicle Details");
        lblVCount.setText(String.valueOf(busCount+vanCount+lorryCount));
    }

    public void openAddDriverForm(ActionEvent actionEvent) throws IOException {
        changeButtonColor(btnAddDriver);
        if(stage!=null) {
            stage.close();
        }
        openForms("../view/AddDriverForm.fxml","Add Driver Details");
        lblDCount.setText(String.valueOf(driversIndex));
    }

    Stage stage;
    private void openForms(String address, String title) throws IOException {
        URL resource = getClass().getResource(address);
        Parent load =FXMLLoader.load(resource);
        Scene scene= new Scene(load);
         stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        if(stage!=null) {
            stage.close();
        }
        URL resource = getClass().getResource("../view/DashBordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managerFormContxt.getScene().getWindow();
        window.setScene(new Scene(load));
    }



    public void showInParkingTable(ActionEvent actionEvent) {
        changeButtonColor(btnPark);
        loadInParkingArray();
        loadTableData();
        setTableVisible(tblInParking);
    }

    private void changeButtonColor(JFXButton btn) {
        btnPark.setStyle("-fx-background-color: #000000");
        btnDelivery.setStyle("-fx-background-color: #000000");
        btnVehicles.setStyle("-fx-background-color: #000000");
        btnDrivers.setStyle("-fx-background-color: #000000");
        btnAddVehicle.setStyle("-fx-background-color: #000000");
        btnAddDriver.setStyle("-fx-background-color: #000000");
        btn.setStyle("-fx-background-color: #5f5e5e");
    }

    public void showDeliveryTable(ActionEvent actionEvent) {
        changeButtonColor(btnDelivery);
        loadOnDeliveryArray();
        loadDeliveredTable();
        setTableVisible(tblOnDelivery);
    }

    public void showVehicles(ActionEvent actionEvent) {
        changeButtonColor(btnVehicles);
        loadAllVehicles();
        setTableVisible(tblVehicles);
    }

    public void showDrivers(ActionEvent actionEvent) {
        changeButtonColor(btnDrivers);
        loadAllDrivers();
        setTableVisible(tblDrivers);
    }

   /* public void btnDeliveryChangeColor(MouseEvent mouseEvent) {
      //  btnDelivery.setStyle("-fx-background-color: #5f5e5e");
    }

    public void btnVehiclesChangeColor(MouseEvent mouseEvent) {
       // btnVehicles.setStyle("-fx-background-color: #5f5e5e");
    }

    public void btnDriversChangeColor(MouseEvent mouseEvent) {
       // btnDrivers.setStyle("-fx-background-color: #5f5e5e");
    }

    public void btnParkChangeColor(MouseEvent mouseEvent) {
       // btnPark.setStyle("-fx-background-color: #5f5e5e");
    }

    public void btnVehiclesResetColor(MouseEvent mouseEvent) {
       // btnVehicles.setStyle("-fx-background-color: #000000");
    }

    public void btnDeliveryResetColor(MouseEvent mouseEvent) {
       // btnDelivery.setStyle("-fx-background-color: #000000");
    }

    public void btnParkResetColor(MouseEvent mouseEvent) {
        //btnPark.setStyle("-fx-background-color: #000000");
    }

    public void btnDriversResetColor(MouseEvent mouseEvent) {
        //btnDrivers.setStyle("-fx-background-color: #000000");
    }

    public void btnAddVehicleChangeColor(MouseEvent mouseEvent) {
        btnAddVehicle.setStyle("-fx-background-color: #5f5e5e");
    }

    public void btnAddVehicleResetColor(MouseEvent mouseEvent) {
        btnAddVehicle.setStyle("-fx-background-color: #000000");
    }

    public void btnAddDriverChangeColor(MouseEvent mouseEvent) {
        btnAddDriver.setStyle("-fx-background-color: #5f5e5e");
    }

    public void btnAddDriverResetColor(MouseEvent mouseEvent) {
        btnAddDriver.setStyle("-fx-background-color: #000000");
    }*/
}
