package org.parking_lot.ServiceImpl;

import org.parking_lot.Costing.CostComputationFactory;
import org.parking_lot.Models.Ticket;
import org.parking_lot.Models.Vehicle;
import org.parking_lot.ParkingSpotManagers.ParkingManagerStrategy;
import org.parking_lot.ParkingSpotManagers.ParkingSpotManager;
import org.parking_lot.ParkingSpots.ParkingSpot;
import org.parking_lot.Services.EntryService;

public class EntryServiceImpl implements EntryService {
    private final ParkingManagerStrategy managerStrategy;
    private final CostComputationFactory costFactory;

    public EntryServiceImpl(){
        managerStrategy = ParkingManagerStrategy.getInstance();
        costFactory = CostComputationFactory.getInstance();
    }

    @Override
    public Ticket enterVehicle(Vehicle vehicle){
        ParkingSpotManager<? extends ParkingSpot> parkingSpotManager = managerStrategy.getManager(vehicle.getVehicleType());
        ParkingSpot parkingSpot = parkingSpotManager.bookParkingSpot(vehicle);
        Ticket ticket = generateTicket(vehicle, parkingSpot.getId());
        return ticket;
    }

    private Ticket generateTicket(Vehicle vehicle, String parkingSpotId) {
        Ticket ticket = new Ticket(parkingSpotId, vehicle);
        return ticket;
    }
}
