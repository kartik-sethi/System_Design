package org.parking_lot.ParkingSpots;

import org.parking_lot.enums.VehicleType;

public class FourWheelerSpot extends ParkingSpot {

    @Override
    public VehicleType getVehicleType(){
        return VehicleType.FOUR_WHEELER;
    }
}
