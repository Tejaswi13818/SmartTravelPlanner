import java.util.*;

// -------------------- MODEL CLASSES --------------------

class Destination {
    private String name;
    private String location;
    private double cost;

    public Destination(String name, String location, double cost) {
        this.name = name;
        this.location = location;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (" + location + ") - $" + cost;
    }
}

class Activity {
    private String name;
    private String time;
    private double price;

    public Activity(String name, String time, double price) {
        this.name = name;
        this.time = time;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " at " + time + " - $" + price;
    }
}

// Transport Interface
interface Transport {
    String getTransportDetails();
    double getFare();
}

class Flight implements Transport {
    private String airline;
    private String time;
    private double fare;

    public Flight(String airline, String time, double fare) {
        this.airline = airline;
        this.time = time;
        this.fare = fare;
    }

    public String getTransportDetails() {
        return "Flight: " + airline + " at " + time;
    }
    public double getFare() {
        return fare;
    }
}

class Train implements Transport {
    private String trainName;
    private String time;
    private double fare;

    public Train(String trainName, String time, double fare) {
        this.trainName = trainName;
        this.time = time;
        this.fare = fare;
    }

    public String getTransportDetails() {
        return "Train: " + trainName + " at " + time;
    }
    public double getFare() {
        return fare;
    }
}

class Bus implements Transport {
    private String busType;
    private String time;
    private double fare;

    public Bus(String busType, String time, double fare) {
        this.busType = busType;
        this.time = time;
        this.fare = fare;
    }

    public String getTransportDetails() {
        return "Bus: " + busType + " at " + time;
    }
    public double getFare() {
        return fare;
    }
}

// ------------------ SERVICE CLASS ---------------------

class ItineraryPlanner {
    private ArrayList<Destination> destinations = new ArrayList<>();
    private ArrayList<Activity> activities = new ArrayList<>();
    private Transport transport;

    public void addDestination(Destination d) {
        destinations.add(d);
    }

    public void addActivity(Activity a) {
        activities.add(a);
    }

    public void chooseTransport(Transport t) {
        transport = t;
    }

    public void showItinerary() {
        System.out.println("\n========== SMART TRAVEL ITINERARY ==========");

        System.out.println("\nDestinations:");
        for (Destination d : destinations) {
            System.out.println("* " + d);
        }

        System.out.println("\nActivities:");
        for (Activity a : activities) {
            System.out.println("* " + a);
        }

        System.out.println("\nTransport:");
        System.out.println("* " + transport.getTransportDetails());

        double totalCost = transport.getFare();
        for (Destination d : destinations) totalCost += d.getCost();
        for (Activity a : activities) totalCost += a.getPrice();

        System.out.println("\nTotal Estimated Trip Cost: $" + totalCost);

        System.out.println("\n============================================");
    }
}

// ------------------ MAIN APP (WITH USER INPUT) ---------------------

public class SmartTravelPlanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItineraryPlanner planner = new ItineraryPlanner();

        System.out.println("=== SMART TRAVEL ITINERARY PLANNER ===");

        // ----------------------------------
        // INPUT DESTINATIONS
        // ----------------------------------
        System.out.print("How many destinations do you want to add? ");
        int dCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < dCount; i++) {
            System.out.println("\nEnter details for Destination " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Location: ");
            String location = sc.nextLine();

            System.out.print("Cost: ");
            double cost = sc.nextDouble();
            sc.nextLine();

            planner.addDestination(new Destination(name, location, cost));
        }

        // ----------------------------------
        // INPUT ACTIVITIES
        // ----------------------------------
        System.out.print("\nHow many activities do you want to add? ");
        int aCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < aCount; i++) {
            System.out.println("\nEnter details for Activity " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Time (Ex: 10:00 AM): ");
            String time = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            planner.addActivity(new Activity(name, time, price));
        }

        // ----------------------------------
        // SELECT TRANSPORT (POLYMORPHISM)
        // ----------------------------------
        System.out.println("\nChoose Transport:");
        System.out.println("1. Flight");
        System.out.println("2. Train");
        System.out.println("3. Bus");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        Transport t = null;

        switch (choice) {
            case 1:
                System.out.print("Airline Name: ");
                String airline = sc.nextLine();
                System.out.print("Departure Time: ");
                String ftime = sc.nextLine();
                System.out.print("Fare: ");
                double fFare = sc.nextDouble();
                t = new Flight(airline, ftime, fFare);
                break;

            case 2:
                System.out.print("Train Name: ");
                String tname = sc.nextLine();
                System.out.print("Departure Time: ");
                String ttime = sc.nextLine();
                System.out.print("Fare: ");
                double tFare = sc.nextDouble();
                t = new Train(tname, ttime, tFare);
                break;

            case 3:
                System.out.print("Bus Type (AC/Non-AC/Sleeper): ");
                String btype = sc.nextLine();
                System.out.print("Departure Time: ");
                String btime = sc.nextLine();
                System.out.print("Fare: ");
                double bFare = sc.nextDouble();
                t = new Bus(btype, btime, bFare);
                break;

            default:
                System.out.println("Invalid choice! Default selected: Bus");
                t = new Bus("AC", "9:00 AM", 500);
        }

        planner.chooseTransport(t);

        // SHOW ITINERARY
        planner.showItinerary();
    }
}