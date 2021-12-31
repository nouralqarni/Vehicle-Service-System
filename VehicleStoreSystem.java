import java.io.*;
import java.util.*;

public class VehicleStoreSystem {

    public static void main(String[] args) throws FileNotFoundException {

        File input = new File("input.txt");
        if (!input.exists()) {
            System.out.println("File " + input.getName() + " doesn't exist");
            System.exit(0);
        }
        Scanner reader = new Scanner(input);
        File output = new File("output.txt");
        PrintWriter writer = new PrintWriter(output);

        Vehicle[] vehicle = new Vehicle[reader.nextInt()];
        Customer[] customer = new Customer[reader.nextInt()];

        writer.println("--------------- Welcome to X Vehicle store System ---------------\n");

        String command;
        int cIndex = 0, vIndex = 0;
        int max = 0;
        boolean flag = true;
        do {
            command = reader.next();
            if (!command.equals("Quit")) {
                writer.print("\nCommand " + command + ":");
            }

            if (command.equals("Add_Customer_Record")) {
                customer[cIndex] = new Customer(reader.nextInt(), reader.next(), reader.next(), reader.next().charAt(0), reader.nextInt());
                writer.println("\nAdd a new Customer record in the System" + customer[cIndex].toString());
                writer.println("-------------------------------------------------------------------------------");
                cIndex++;
            } else if (command.equals("Add_Rental")) {
                vehicle[vIndex] = new Rental(reader.next(), reader.next(), reader.next(), reader.nextDouble(), reader.nextDouble(), reader.nextInt());
                writer.println(" Add a new vehicles for rent in the System" + vehicle[vIndex].toString() + "\tRate per day: " + ((Rental) vehicle[vIndex]).rent_per_day);
                writer.print("-------------------------------------------------------------------------------");
                vIndex++;
            } else if (command.equals("Add_Sale")) {
                vehicle[vIndex] = new Sale(reader.next(), reader.next(), reader.next(), reader.nextDouble(), reader.nextDouble());
                writer.println(" Add a new vehicles for sale in the System" + vehicle[vIndex].toString());
                writer.print("-------------------------------------------------------------------------------");
                vIndex++;
            } else if (command.equals("Ass_Customer_to_Rent")) {
                writer.print(" A Customer rent a vehicle");
                Ass_Customer_to_Rent(vehicle, customer, reader, writer);
                writer.print("-------------------------------------------------------------------------------");
            } else if (command.equals("Print_Rental")) {
                writer.println("\n==================================================================");
                Print_Rental(vehicle, writer);
            } else if (command.equals("Print_Sale")) {
                writer.println("\n==================================================================");
                Print_Sale(vehicle, writer);
            } else if (command.equals("Quit")) {
                writer.println("\n\nThank you for using X Store Vehicle System, Good Bye!");
                flag = false;
            }
            writer.flush();
        } while (flag);
    }

    public static void Print_Sale(Vehicle[] vehicle, PrintWriter writer) {
        ArrayList<Sale> sales = new ArrayList<>();
        for (Vehicle vehicle1 : vehicle) {
            if (vehicle1 instanceof Sale) {
                sales.add((Sale) vehicle1);
            }
        }
        Collections.sort(sales);
        for (int i = 0; i < sales.size(); i++) {
            writer.println(sales.get(i).toString());
            writer.println("----------------------------------------------------------------");
        }
    }

    public static void Print_Rental(Vehicle[] vehicle, PrintWriter writer) {
        ArrayList<Rental> rentals = new ArrayList<>();
        for (Vehicle vehicle1 : vehicle) {
            if (vehicle1 instanceof Rental) {
                rentals.add((Rental) vehicle1);
            }
        }
        Collections.sort(rentals);
        for (int i = 0; i < rentals.size(); i++) {
            writer.println(rentals.get(i).toString() + "\tRate per day: " + rentals.get(i).rent_per_day
                    + "\n------------\nRented to:");
            if (rentals.get(i).getCustomer()[0] != null) {
                writer.println();
                for (int j = 0; j < rentals.get(i).rentedTo.length; j++) {
                    if (rentals.get(i).getCustomer()[j] != null) {
                        writer.println("\tCustomer # " + (j + 1)
                                + "\tID: " + rentals.get(i).getCustomer()[j].getId()
                                + "\tNumber of Days: " + rentals.get(i).getCustomer()[j].getRental_days()
                                + "\tPrice: " + rentals.get(i).calcPrice());
                    }
                }
            } else {
                writer.println("\tNo body");
            }
            writer.println("-------------------------------------------------------------------------------");
        }
    }

    public static void Ass_Customer_to_Rent(Vehicle[] vehicle, Customer[] customer, Scanner reader, PrintWriter writer) {
        String V = reader.next();
        int ID = reader.nextInt();
        int days = reader.nextInt();
        Rental rental1 = (Rental) searchVehicle(vehicle, V);

        if (rental1 == null) {
            writer.println("\nVehicle Not found: " + V);
            return;
        }

        Customer customer1 = searchCustomer(customer, ID);
        if (customer1 != null) {
            if (rental1.getCurrentCustomerNo() < rental1.rentedTo.length) {
                rental1.addCustomer(customer1, days);
                writer.println("\n\nCustomer: " + customer1.getId() + "\tRents Vehicle : " + rental1.getLicenseNum());
            } else {
                writer.println("\nThis vehicle reaches the max number of rent: " + rental1.getLicenseNum());
            }
        } else {
            writer.println("\nCustomer Not found: " + ID);
        }
    }

    public static Customer searchCustomer(Customer[] customers, int id) {
        for (Customer customer : customers) {
            if (customer != null) {
                if (customer.getId() == id) {
                    return customer;
                }
            }
        }
        return null;
    }

    public static Vehicle searchVehicle(Vehicle[] vehicles, String licenseNo) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Rental) {
                if (vehicle.getLicenseNum().equals(licenseNo)) {
                    return vehicle;
                }
            }
        }
        return null;
    }

}
