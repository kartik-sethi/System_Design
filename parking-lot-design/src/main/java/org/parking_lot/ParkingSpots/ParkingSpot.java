package org.parking_lot.ParkingSpots;

import org.parking_lot.Models.Vehicle;
import org.parking_lot.enums.VehicleType;

import java.util.UUID;

public abstract class ParkingSpot {
    private String id;
    private Vehicle vehicle;
    private boolean isOccupied;
    private boolean floor;

    ParkingSpot(){
        id = UUID.randomUUID().toString();
    }

    public String getId(){ return this.id;}

    public boolean isOccupied(){
        return isOccupied;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        updateIsOccupied();
    }

    public void removeVehicle(){
        this.vehicle = null;
    }

    private void updateIsOccupied(){
        this.isOccupied = vehicle != null;
    }

    public abstract VehicleType getVehicleType();
}
