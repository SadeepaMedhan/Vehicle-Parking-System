package model;

public class Driver {
    private String driverName;
    private String NIC;
    private String driverLicenceNumber;
    private String address;
    private String contactNumber;

    public Driver() {
    }

    public Driver(String driverName, String NIC, String driverLicenceNumber, String address, String contactNumber) {
        this.driverName = driverName;
        this.NIC = NIC;
        this.driverLicenceNumber = driverLicenceNumber;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getDriverLicenceNumber() {
        return driverLicenceNumber;
    }

    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
