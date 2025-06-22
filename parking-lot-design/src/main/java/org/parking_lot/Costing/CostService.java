package org.parking_lot.Costing;

import java.time.LocalDateTime;

public interface CostService {

    double calculate(LocalDateTime entryTime, LocalDateTime exitTime);
}
