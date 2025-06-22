package org.parking_lot.ParkingSpotManagers;

import org.parking_lot.ParkingSpots.TwoWheelerSpot;
import org.parking_lot.enums.VehicleType;

import java.util.ArrayList;

public class TwoWheelerParkingSpotManager extends ParkingSpotManager<TwoWheelerSpot> {

    private static final TwoWheelerParkingSpotManager INSTANCE = new TwoWheelerParkingSpotManager();

    TwoWheelerParkingSpotManager(){
        super(new ArrayList<>());
    }

    public static TwoWheelerParkingSpotManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void addParkingSpot() {
        parkingSpots.add(new TwoWheelerSpot());
    }

    @Override
    public void removeParkingSpot(String id) {
        parkingSpots.removeIf(spot -> spot.getId().equals(id));
    }
}
