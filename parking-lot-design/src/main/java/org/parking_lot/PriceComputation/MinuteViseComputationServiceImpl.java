package org.parking_lot.PriceComputation;

import java.time.Duration;
import java.time.LocalDateTime;

public class MinuteViseComputationServiceImpl implements PriceComputationService{

    private final double perMinuteRate;

    public MinuteViseComputationServiceImpl(double perMinuteRate) {
        this.perMinuteRate = perMinuteRate;
    }

    @Override
    public double calculate(LocalDateTime entryTime, LocalDateTime exitTime) {
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        return minutes * perMinuteRate;
    }
}
