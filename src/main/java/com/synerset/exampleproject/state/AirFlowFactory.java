package com.synerset.exampleproject.state;

import com.synerset.exampleproject.common.Defaults;
import com.synerset.exampleproject.properties.DryAirProperties;
import com.synerset.unitsystem.density.InvalidDensity;
import com.synerset.unitsystem.density.KiloGramPerCubicMeter;
import com.synerset.unitsystem.massflow.MassFlow;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.specificheat.InvalidSpecificHeat;
import com.synerset.unitsystem.specificheat.KiloJoulePerKilogramKelvin;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.control.Either;

public class AirFlowFactory {

    private final DryAirProperties dryAirProperties;

    public AirFlowFactory(DryAirProperties dryAirProperties) {
        this.dryAirProperties = dryAirProperties;
    }

    public Either<InvalidFlow, FlowOfDryAir> createFlowOfDryAir(Pressure pressure, Temperature temperature, MassFlow massFlow) {
        Either<InvalidDensity, KiloGramPerCubicMeter> density = dryAirProperties.density(temperature, pressure);
        if (density.isLeft()) {
            return Either.left(new InvalidFlow());
        }
        Either<InvalidSpecificHeat, KiloJoulePerKilogramKelvin> specHeat = dryAirProperties.specificHeat(temperature);
        if(specHeat.isLeft()){
            return Either.left(new InvalidFlow());
        }
        DryAirState dryAirState = new DryAirState(
                pressure.toPascal(),
                temperature.toCelsius(),
                density.get(),
                specHeat.get()
        );
        return Either.right(new FlowOfDryAir(dryAirState, massFlow.toKiloGramPerSecond()));
    }

    public Either<InvalidFlow, FlowOfDryAir> createFlowOfDryAir(Temperature temperature, MassFlow massFlow) {
        return createFlowOfDryAir(Defaults.DEF_ATM_PRESSURE, temperature, massFlow);
    }

}

