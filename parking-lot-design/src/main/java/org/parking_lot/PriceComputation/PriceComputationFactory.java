package org.parking_lot.PriceComputation;

import org.parking_lot.enums.PriceComputationType;

public class PriceComputationFactory {

    private static final PriceComputationFactory INSTANCE = new PriceComputationFactory();

    private PriceComputationFactory() {}

    public static PriceComputationFactory getInstance() {
        return INSTANCE;
    }

    public PriceComputationService getPriceComputation(PriceComputationType type) {
        return switch (type) {
            case HOURLY -> new HourlyComputationServiceImpl(40);
            case MINUTE -> new MinuteViseComputationServiceImpl(1);
        };
    }
}
