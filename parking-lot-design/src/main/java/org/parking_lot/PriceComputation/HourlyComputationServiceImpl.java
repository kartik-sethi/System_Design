package org.parking_lot.PriceComputation;

import org.parking_lot.ParkingSpotManagers.FourWheelerParkingSpotManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyComputationServiceImpl implements PriceComputationService {
    private final double hourlyRate;

    public HourlyComputationServiceImpl(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculate(LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toHours();
        if (Duration.between(entryTime, exitTime).toMinutes() % 60 != 0) {
            hours++;
        }
        return hours * hourlyRate;
    }
}
