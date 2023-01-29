package com.synerset.exampleproject.process;

import com.synerset.exampleproject.state.AirFlowFactory;
import com.synerset.exampleproject.state.FlowOfDryAir;
import com.synerset.exampleproject.state.InvalidFlow;
import com.synerset.unitsystem.power.KiloWatt;
import com.synerset.unitsystem.power.Power;
import com.synerset.unitsystem.temperature.Celsius;
import com.synerset.unitsystem.temperature.InvalidTemperature;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.control.Either;

public class Heating {

    private final AirFlowFactory airFlowFactory;

    public Heating(AirFlowFactory airFlowFactory) {
        this.airFlowFactory = airFlowFactory;
    }

    public Either<InvalidHeatingProcess, HeatingResultDto> fromOutletTemperature(FlowOfDryAir inletFlow, Celsius targetTemperature) {
        double inTemp = inletFlow.temperature().getValue();
        double outTemp = targetTemperature.getValue();
        double specHeat = inletFlow.specificHeat().getValue();
        double massFlow = inletFlow.massFlow().getValue();
        KiloWatt heatOfProcess = Power.kiloWatt(massFlow * specHeat * (outTemp - inTemp));
        Either<InvalidFlow, FlowOfDryAir> outletAir = airFlowFactory.createFlowOfDryAir(targetTemperature, inletFlow.massFlow());
        if (outletAir.isLeft()) {
            return Either.left(new InvalidHeatingProcess());
        }
        return Either.right(new HeatingResultDto(outletAir.get(), heatOfProcess));
    }

    public Either<InvalidHeatingProcess, HeatingResultDto> fromInputPower(FlowOfDryAir inletFlow, Power inputPower) {
        double inTemp = inletFlow.temperature().getValue();
        double specHeat = inletFlow.specificHeat().getValue();
        double massFlow = inletFlow.massFlow().getValue();
        double heat = inputPower.toKiloWatt().getValue();
        Either<InvalidTemperature, Celsius> outletTemperature = Temperature.celsius(heat / massFlow * specHeat + inTemp);
        if (outletTemperature.isLeft()) {
            return Either.left(new InvalidHeatingProcess());
        }
        Either<InvalidFlow, FlowOfDryAir> outletAir = airFlowFactory.createFlowOfDryAir(outletTemperature.get(), inletFlow.massFlow());
        if (outletAir.isLeft()) {
            return Either.left(new InvalidHeatingProcess());
        }
        return Either.right(new HeatingResultDto(outletAir.get(), inputPower.toKiloWatt()));
    }

}
