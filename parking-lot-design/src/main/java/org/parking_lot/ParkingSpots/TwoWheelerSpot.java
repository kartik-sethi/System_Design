package org.parking_lot.ParkingSpots;

import org.parking_lot.enums.VehicleType;

public class TwoWheelerSpot extends ParkingSpot {

    @Override
    public VehicleType getVehicleType(){
        return VehicleType.TWO_WHEELER;
    }
}
