public class Sale extends Vehicle {

    private double sell_price;
    private double discount;
    private double rate;

    public Sale(String license_number, String make, String model, double price, double rate) {
        super(license_number, make, model, price);
        this.discount = rate;
    }

    @Override
    public String getLicenseNum() {
        return license_number;
    }

    @Override
    public double calcPrice() {
        return super.price - ((discount / 100.0) * super.price);
    }

    @Override
    public int compareTo(Vehicle v) {
        return this.license_number.compareTo(v.license_number);
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tDiscount rate	:" + discount + "%"
                + "\n\tSelling Price 	:" + calcPrice();
    }

}
