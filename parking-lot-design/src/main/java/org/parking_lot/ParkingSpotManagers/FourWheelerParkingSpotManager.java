package org.parking_lot.ParkingSpotManagers;

import org.parking_lot.ParkingSpots.FourWheelerSpot;

import java.util.ArrayList;

public class FourWheelerParkingSpotManager extends ParkingSpotManager<FourWheelerSpot> {
    private static final FourWheelerParkingSpotManager INSTANCE = new FourWheelerParkingSpotManager();

    private FourWheelerParkingSpotManager(){
        super(new ArrayList<>());
    }

    public static FourWheelerParkingSpotManager getInstance() {
        return INSTANCE;
    }

    @Override
    public void addParkingSpot() {
        parkingSpots.add(new FourWheelerSpot()); // you'll need to make parkingSpots protected or use a getter
    }

    @Override
    public void removeParkingSpot(String id) {
        parkingSpots.removeIf(spot -> spot.getId().equals(id));
    }
}
