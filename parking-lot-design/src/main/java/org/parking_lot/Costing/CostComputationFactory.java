package org.parking_lot.Costing;

import org.parking_lot.enums.PriceComputationType;
import org.parking_lot.enums.VehicleType;

public class CostComputationFactory {

    private static final CostComputationFactory INSTANCE = new CostComputationFactory();

    private CostComputationFactory() {}

    public static CostComputationFactory getInstance() {
        return INSTANCE;
    }

    public CostService getCostComputation(VehicleType type) {
        return switch (type) {
            case TWO_WHEELER -> new TwoWheelerCostServiceImpl(PriceComputationType.MINUTE);
            case FOUR_WHEELER -> new FourWheelerCostServiceImpl(PriceComputationType.HOURLY);
        };
    }
}
