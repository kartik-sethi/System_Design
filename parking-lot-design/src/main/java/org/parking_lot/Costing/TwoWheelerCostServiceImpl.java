package org.parking_lot.Costing;

import org.parking_lot.PriceComputation.PriceComputationFactory;
import org.parking_lot.PriceComputation.PriceComputationService;
import org.parking_lot.enums.PriceComputationType;

import java.time.LocalDateTime;

public class TwoWheelerCostServiceImpl implements CostService {
    private final PriceComputationFactory costComputationFactory = PriceComputationFactory.getInstance();
    private final PriceComputationType priceComputationType;

    TwoWheelerCostServiceImpl(PriceComputationType priceComputationType){
        this.priceComputationType = priceComputationType;
    }

    @Override
    public double calculate(LocalDateTime enter, LocalDateTime exit){
        PriceComputationService priceComputationService = costComputationFactory.getPriceComputation(priceComputationType);
        return priceComputationService.calculate(enter, exit);
    }
}
