public class Rental extends Vehicle {

    protected double rent_per_day;
    protected Customer[] rentedTo;
    protected int currentCustomerNo;
    private int index;

    public Rental(String license_number, String make, String model, double price, double rpd, int max) {
        super(license_number, make, model, price);
        rent_per_day = rpd;
        rentedTo = new Customer[max];
    }

    public void addCustomer(Customer c, int numOfDays) {
        rentedTo[currentCustomerNo] = c;
        rentedTo[currentCustomerNo].setRental_days(numOfDays);
        currentCustomerNo++;
    }

    public Customer[] getCustomer() {
        return rentedTo;
    }

    public int getCurrentCustomerNo() {
        return currentCustomerNo;
    }

    @Override
    public String getLicenseNum() {
        return license_number;
    }

    @Override
    public double calcPrice() {
        return rent_per_day * rentedTo[index++].getRental_days();
    }
    
    @Override
    public int compareTo(Vehicle v) {
        return this.license_number.compareTo(v.license_number);
    }
}
