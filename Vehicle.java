public abstract class Vehicle implements java.lang.Comparable<Vehicle> {

    protected String license_number;
    protected String make;
    protected String model;
    protected double price;

    public Vehicle(String licwnse_number, String make, String model, double price) {
        this.license_number = licwnse_number;
        this.make = make;
        this.model = model;
        this.price = price;
    }

    public String getVehicleName() {
        return make + " " + model;
    }

    public abstract String getLicenseNum();

    public abstract double calcPrice();

    @Override
    public String toString() {
        return "\n\tLicense # : " + license_number
                + "\n\tVehicle Name : " + getVehicleName()
                + "\n\tPrice : " + price;
    }
}
