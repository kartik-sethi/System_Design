package org.parking_lot.PriceComputation;

import java.time.LocalDateTime;

public interface PriceComputationService {
    double calculate(LocalDateTime entryTime, LocalDateTime exitTime);
}
