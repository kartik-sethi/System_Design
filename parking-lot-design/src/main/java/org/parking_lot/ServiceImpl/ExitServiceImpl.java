package org.parking_lot.ServiceImpl;

import org.parking_lot.Costing.CostComputationFactory;
import org.parking_lot.Costing.CostService;
import org.parking_lot.Models.Ticket;
import org.parking_lot.ParkingSpotManagers.ParkingManagerStrategy;
import org.parking_lot.ParkingSpotManagers.ParkingSpotManager;
import org.parking_lot.ParkingSpots.ParkingSpot;
import org.parking_lot.Services.ExitService;

import java.time.LocalDateTime;

public class ExitServiceImpl implements ExitService {
    private final ParkingManagerStrategy managerStrategy;
    private final CostComputationFactory costFactory;

    public ExitServiceImpl(){
        managerStrategy = ParkingManagerStrategy.getInstance();
        costFactory = CostComputationFactory.getInstance();
    }

    @Override
    public double exit(Ticket ticket) {
        ticket.setExitTime(LocalDateTime.now());

        ParkingSpotManager<? extends ParkingSpot> manager =
                managerStrategy.getManager(ticket.getVehicle().getVehicleType());

        manager.clearParkingSpotById(ticket.getParkingSpotId());

        CostService costService = costFactory.getCostComputation(ticket.getVehicle().getVehicleType());
        return costService.calculate(ticket.getEntryTime(), ticket.getExitTime());
    }
}
