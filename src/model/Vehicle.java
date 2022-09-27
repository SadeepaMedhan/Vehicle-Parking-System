package model;

public interface Vehicle {
    String vehicleNumber = null;
    String vehicleType = null;
    int maximumWeight = 0;
    int numberOfPassengers = 0;
    String driverNIC = null;
    int slot = 0;

    String toString();

    void park(String vehicleNumber, int slot, String nowTime);

    void leavePark(String vehicleNumber,String driverName,String time);

    String getVehicleNumber();

    String getVehicleType();

    int getMaximumWeight();

    int getNumberOfPassengers();

    String getDriverNIC();

    void setDriverNIC(String driverNIC);

    int getSlot();

    void setSlot(int slot);

    String getTime();

    void setTime(String time);
}
