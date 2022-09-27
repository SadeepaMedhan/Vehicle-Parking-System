package view.tm;

public class ParkedVehicleData {
    private String vehicleNumber;
    private String vehicleType;
    private String parkingSlot;
    private String parkedTime;

    public ParkedVehicleData() {
    }

    public ParkedVehicleData(String vehicleNumber, String vehicleType, String parkingSlot, String parkedTime) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.parkingSlot = parkingSlot;
        this.parkedTime = parkedTime;
    }


    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getDriverNIC() {
        return null;
    }

    public void setDriverNIC(String driverNIC) {

    }

    public int getSlot() {
        return 0;
    }

    public void setSlot(int slot) {

    }

    public String getTime() {
        return null;
    }

    public void setTime(String time) {

    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(String parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(String parkedTime) {
        this.parkedTime = parkedTime;
    }
}
