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

    public Either<InvalidHeatingProcess, HeatingResultDto> fromOutletTemperature(FlowOfDryAir inletFlow, Celsius targetTemp) {
        double t_in = inletFlow.temperature().getValue();
        double t_out = targetTemp.getValue();
        double cp = inletFlow.specificHeat().getValue();
        double mda = inletFlow.massFlow().getValue();
        KiloWatt heatOfProcess = Power.kiloWatt(mda * cp * (t_out - t_in));
        return airFlowFactory.createFlowOfDryAir(targetTemp, inletFlow.massFlow())
                .mapLeft(l -> new InvalidHeatingProcess())
                .map(outletFlow -> new HeatingResultDto(outletFlow, heatOfProcess));
    }

    public Either<InvalidHeatingProcess, HeatingResultDto> fromInputPower(FlowOfDryAir inletFlow, Power inputPower) {
        double t_in = inletFlow.temperature().getValue();
        double cp = inletFlow.specificHeat().getValue();
        double mda = inletFlow.massFlow().getValue();
        double Q = inputPower.toKiloWatt().getValue();
        return Temperature.celsius(Q / mda * cp + t_in)
                .mapLeft(l -> new InvalidHeatingProcess())
                .flatMap(outletTemp -> airFlowFactory.createFlowOfDryAir(outletTemp, inletFlow.massFlow())
                        .mapLeft(l -> new InvalidHeatingProcess()))
                .map(outletFlow -> new HeatingResultDto(outletFlow, inputPower.toKiloWatt()));
    }

}
