package org.parking_lot.ParkingSpotManagers;

import org.parking_lot.enums.VehicleType;
import org.parking_lot.ParkingSpots.ParkingSpot;

import java.util.HashMap;
import java.util.Map;
public class ParkingManagerStrategy {

    private static final ParkingManagerStrategy INSTANCE = new ParkingManagerStrategy();

    private final Map<VehicleType, ParkingSpotManager<? extends ParkingSpot>> map = new HashMap<>();

    private ParkingManagerStrategy() {
        init();
    }

    public static ParkingManagerStrategy getInstance() {
        return INSTANCE;
    }

    private void init() {
        map.put(VehicleType.TWO_WHEELER, TwoWheelerParkingSpotManager.getInstance());
        map.put(VehicleType.FOUR_WHEELER, FourWheelerParkingSpotManager.getInstance());
    }

    public ParkingSpotManager<? extends ParkingSpot> getManager(VehicleType type) {
        return map.get(type);
    }
}
