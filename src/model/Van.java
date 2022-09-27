package model;

public class Van extends ObserverList implements Vehicle{
    private String vehicleNumber;
    private String vehicleType;
    private int maximumWeight;
    private int numberOfPassengers;
    private String driverNIC;
    private String time;
    private int slot;

    public Van() { }

    @Override
    public String toString() {
        return  vehicleNumber;
    }

    public Van(String vehicleNumber, String vehicleType, int maximumWeight, int numberOfPassengers) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maximumWeight = maximumWeight;
        this.numberOfPassengers = numberOfPassengers;
    }

    @Override
    public void park(String vehicleNumber, int slot, String nowTime) {
        if(getVehicleNumber().equals(vehicleNumber)) {
            setDriverNIC(null);
            setTime(nowTime);
            setSlot(slot);
            setParkToArray(vehicleNumber, slot);
            removeDelivery(vehicleNumber);
        }
    }

    @Override
    public void leavePark(String vehicleNumber,String driverName,String time) {
        if (getVehicleNumber().equals(vehicleNumber)) {
            setDriverNIC(getNICFromName(driverName));
            setTime(time);
            setSlot(0);
            setDeliver(vehicleNumber);
            removePark(vehicleNumber);
        }
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

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getDriverNIC() {
        return driverNIC;
    }

    public void setDriverNIC(String driverNIC) {
        this.driverNIC = driverNIC;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
