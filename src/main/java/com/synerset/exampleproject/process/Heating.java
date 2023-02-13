package com.synerset.exampleproject.process;

import com.synerset.exampleproject.state.AirFlowFactory;
import com.synerset.exampleproject.state.FlowOfDryAir;
import com.synerset.unitsystem.power.KiloWatt;
import com.synerset.unitsystem.power.Power;
import com.synerset.unitsystem.temperature.Celsius;
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
        return airFlowFactory.createFlowOfDryAir(targetTemperature, inletFlow.massFlow())
                .mapLeft(l -> new InvalidHeatingProcess())
                .map(outletFlow -> new HeatingResultDto(outletFlow, heatOfProcess));
    }

    public Either<InvalidHeatingProcess, HeatingResultDto> fromInputPower(FlowOfDryAir inletFlow, Power inputPower) {
        double inTemp = inletFlow.temperature().getValue();
        double specHeat = inletFlow.specificHeat().getValue();
        double massFlow = inletFlow.massFlow().getValue();
        double heat = inputPower.toKiloWatt().getValue();
        return Temperature.celsius(heat / massFlow * specHeat + inTemp)
                .mapLeft(l -> new InvalidHeatingProcess())
                .flatMap(outletTemp -> airFlowFactory.createFlowOfDryAir(outletTemp, inletFlow.massFlow())
                        .mapLeft(l -> new InvalidHeatingProcess()))
                .map(outletFlow -> new HeatingResultDto(outletFlow, inputPower.toKiloWatt()));
    }

}
