package org.parking_lot.ParkingLotApplication;

import org.parking_lot.Models.Ticket;
import org.parking_lot.Models.Vehicle;
import org.parking_lot.Services.EntryService;
import org.parking_lot.Services.ExitService;
import org.parking_lot.enums.ApplicationInput;
import org.parking_lot.enums.VehicleType;
import org.parking_lot.ParkingSpotManagers.ParkingManagerStrategy;
import org.parking_lot.ServiceImpl.EntryServiceImpl;
import org.parking_lot.ServiceImpl.ExitServiceImpl;

import java.util.*;

/**
 * Main application class that handles the command-line interface for the Parking Lot System.
 * It supports vehicle entry, exit, parking spot management, and ticket generation.
 * <p>
 * Responsibilities:
 * - Accept user input and process commands
 * - Interface with services for entry and exit
 * - Manage ticket lifecycle
 * </p>
 *
 * @author Kartik Sethi
 */
public class ParkingLotApplication {

    private final Scanner sc;
    private final Set<Ticket> tickets = new HashSet<>();
    private final EntryService entryService;
    private final ExitService exitService;
    private final ParkingManagerStrategy managerStrategy;

    /**
     * Constructs a ParkingLotApplication instance and starts the application flow.
     *
     * @param sc Scanner object used to read user input.
     */
    public ParkingLotApplication(Scanner sc) {
        this.sc = sc;
        this.entryService = new EntryServiceImpl();
        this.exitService = new ExitServiceImpl();
        this.managerStrategy = ParkingManagerStrategy.getInstance();
        setUp();
        start();
    }

    /**
     * Initializes the system with 10 default parking spots each for
     * TWO_WHEELER and FOUR_WHEELER vehicles.
     */
    private void setUp() {
        for (int i = 0; i < 10; i++) {
            managerStrategy.getManager(VehicleType.TWO_WHEELER).addParkingSpot();
            managerStrategy.getManager(VehicleType.FOUR_WHEELER).addParkingSpot();
        }
        System.out.println("Initialized 10 spots each for TWO_WHEELER and FOUR_WHEELER.");
    }

    /**
     * Starts the main application loop which listens to user inputs and executes actions.
     */
    public void start() {
        while (true) {
            try {
                displayMenu();
                int inputKey = sc.nextInt();
                sc.nextLine(); // consume newline
                ApplicationInput command = ApplicationInput.findInputFromInputKey(inputKey);

                switch (command) {
                    case STOP -> {
                        System.out.println("Shutting down...");
                        return;
                    }

                    case ENTRY -> {
                        Vehicle vehicle = inputVehicle();
                        Ticket ticket = entryService.enterVehicle(vehicle);
                        tickets.add(ticket);
                        System.out.println("Ticket issued: " + ticket);
                    }

                    case EXIT -> {
                        System.out.println("Enter Ticket ID to unpark:");
                        String ticketId = sc.nextLine();
                        Ticket ticket = findTicket(ticketId);
                        if (ticket == null) {
                            System.out.println("Ticket not found.");
                            break;
                        }
                        double price = exitService.exit(ticket);
                        System.out.println("Unparked. Total price: ₹" + price);
                    }

                    case ADD_SPOT -> {
                        System.out.println("Enter Vehicle Type of Spot (TWO_WHEELER / FOUR_WHEELER):");
                        VehicleType type = VehicleType.valueOf(sc.nextLine().trim().toUpperCase());
                        managerStrategy.getManager(type).addParkingSpot();
                        System.out.println("Spot added.");
                    }

                    case REMOVE_SPOT -> {
                        System.out.println("Enter Vehicle Type of Spot (TWO_WHEELER / FOUR_WHEELER):");
                        VehicleType type = VehicleType.valueOf(sc.nextLine().trim().toUpperCase());
                        System.out.println("Enter Spot ID to remove:");
                        String id = sc.nextLine();
                        managerStrategy.getManager(type).removeParkingSpot(id);
                        System.out.println("Spot removed.");
                    }

                    default -> System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Takes input from the user to create a Vehicle instance.
     *
     * @return a Vehicle object constructed from user input.
     */
    private Vehicle inputVehicle() {
        System.out.println("Enter Vehicle Number:");
        String number = sc.nextLine();
        System.out.println("Enter Vehicle Type (TWO_WHEELER / FOUR_WHEELER):");
        VehicleType type = VehicleType.valueOf(sc.nextLine().trim().toUpperCase());
        return new Vehicle(number, type);
    }

    /**
     * Finds a ticket from the current ticket set based on the given ticket ID.
     *
     * @param ticketId The ID of the ticket to be searched.
     * @return The matching Ticket object, or null if not found.
     */
    private Ticket findTicket(String ticketId) {
        return tickets.stream()
                .filter(t -> t.getTicketId().equals(ticketId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Displays all the available user input options on the console.
     */
    private void displayMenu() {
        System.out.println("\nAvailable Options:");
        for (ApplicationInput input : ApplicationInput.values()) {
            System.out.println(input.getInputKey() + " -> " + input.getName());
        }
        System.out.print("Choose an option: ");
    }
}
