package model;

import java.util.Arrays;

public class ObserverList{
    private static int vehicleIndex=0;
    public static int driversIndex=0;

    public static int busCount=0;
    public static int vanCount=0;
    public static int lorryCount=0;
    public static String nowTime;

    public static Vehicle[] vehicles= new Vehicle[14];
    public static Driver[] drivers=new Driver[16];
    public static String[] slots=new String[14];
    public static String[] deliveredVehicle=new String[14];

    public static void addVehicle(Vehicle vehicle){
        if(vehicleIndex<14) {
            if ((vehicle.getVehicleType().equals("Bus") && busCount++ < 1) || (vehicle.getVehicleType().equals("Van") && vanCount++ < 6) || (vehicle.getVehicleType().equals("Cargo Lorry") && lorryCount++ < 7)) {
                vehicles[vehicleIndex++] = vehicle;
                vehicle.park(vehicle.getVehicleNumber(),getAvailableSlot(vehicle.getVehicleNumber()),nowTime);
            }
        }
    }

    public static void addDriver(Driver d){
        drivers[driversIndex++]=d;
    }

    public static Vehicle[] getVehicleList(){
        return vehicles;
    }
    public Driver[] getDrivers(){
        return drivers;
    }

    public static int getAvailableSlot(String selectVehicleNumber){
        String type=getVehicleType(selectVehicleNumber);
        switch (type){
            case "Bus" : return 14;
            case "Van" :
                for (int i = 0; i < 4; i++) { if(slots[i]==null){return i+1;}; }
                if(slots[11]==null){return 12;};
                if(slots[12]==null){return 13;};
            case "Cargo Lorry" :
                for (int i = 4; i < 11; i++) { if(slots[i]==null){return i+1;}; }
        }
        return 0;
    }

    private static String getVehicleType(String selectVehicleNumber) {
        for (Vehicle temp : getVehicleList()) {
            if (selectVehicleNumber.equalsIgnoreCase(temp.getVehicleNumber())) {
                return temp.getVehicleType();
            }
        }
        return null;
    }

    public void setParkToArray(String vehicleNumber, int slot){
        slots[slot-1]=vehicleNumber;
        System.out.println(vehicleNumber + " - add to park");
    }

    public void setDeliver(String vehicleNumber){
        for (int i = 0; i < deliveredVehicle.length; i++) {
            if(deliveredVehicle[i] == null){
                deliveredVehicle[i]=vehicleNumber;
                System.out.println(vehicleNumber + " - add delivery");
                break;
            }
        }
    }

    public boolean isOnPark(String vehicleNumber){
        for(String slot:slots){
            if(slot!=null) {
                if (slot.equals(vehicleNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void removePark(String vehicleNumber){
        for(int i=0;i<slots.length;i++){
            if(slots[i]!=null) {
                if (slots[i].equals(vehicleNumber)) {
                    slots[i] = null;
                    //System.out.println(vehicleNumber+" remove park");
                }
            }
        }
    }

    public boolean isOnDelivery(String vehicleNumber){
        for(String del:deliveredVehicle){
            if(del!=null) {
                if (del.equals(vehicleNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSelectDriverIsOn(String selectedDriverName) {
        String tempNIC=getNICFromName(selectedDriverName);
        for (Vehicle vehicle : vehicles) {
            if(vehicle!=null){
                if(vehicle.getDriverNIC()!=null){
                    if (vehicle.getDriverNIC().equalsIgnoreCase(tempNIC)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getNICFromName(String selectedDriverName) {
        for(Driver d:getDrivers()){
            if(d != null){
                if (d.getDriverName().equals(selectedDriverName)) {
                    return d.getNIC();
                }
            }
        }
        return null;
    }

    public void removeDelivery(String vehicleNumber) {
        for(int i=0;i<deliveredVehicle.length;i++){
            if(deliveredVehicle[i]!=null) {
                if (deliveredVehicle[i].equals(vehicleNumber)) {
                    deliveredVehicle[i] = null;
                    //deliveryIndex--;
                    //System.out.println(vehicleNumber+" remove delivery");
                }
            }
        }
    }

    public int getVehicleIndex(){
        return vehicleIndex;
    }
    public int getDriversIndex(){
        return driversIndex;
    }
}
