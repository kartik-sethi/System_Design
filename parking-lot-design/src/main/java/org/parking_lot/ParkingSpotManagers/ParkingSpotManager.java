package org.parking_lot.ParkingSpotManagers;
import org.parking_lot.ParkingSpots.ParkingSpot;
import org.parking_lot.Models.Vehicle;

import java.util.List;

public abstract class ParkingSpotManager<T extends ParkingSpot> {
    protected final List<T> parkingSpots;

    public ParkingSpotManager(List<T> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public T findParkingSpot() {
        return parkingSpots.stream()
                .filter(p -> !p.isOccupied())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sorry No Parking Space Available"));
    }

    public T bookParkingSpot(Vehicle vehicle) {
        T spot = findParkingSpot();
        spot.parkVehicle(vehicle);
        return spot;
    }

    public void clearParkingSpotById(String spotId) {
        for (T spot : parkingSpots) {
            if (spot.getId().equals(spotId)) {
                spot.removeVehicle();
                return;
            }
        }
        throw new IllegalArgumentException("Parking Spot not found: " + spotId);
    }

    public abstract void addParkingSpot();

    public abstract void removeParkingSpot(String id);
}
