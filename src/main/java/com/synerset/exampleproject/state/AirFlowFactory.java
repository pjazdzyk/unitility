package com.synerset.exampleproject.state;

import com.synerset.exampleproject.common.Defaults;
import com.synerset.exampleproject.properties.DryAirProperties;
import com.synerset.unitsystem.massflow.MassFlow;
import com.synerset.unitsystem.pressure.Pascal;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.control.Either;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirFlowFactory {

    protected static final Logger LOGGER = LogManager.getLogger(AirFlowFactory.class.getSimpleName());

    private final DryAirProperties dryAirProperties;

    public AirFlowFactory(DryAirProperties dryAirProperties) {
        this.dryAirProperties = dryAirProperties;
    }

    public Either<InvalidFlow, FlowOfDryAir> createFlowOfDryAir(Pressure pressure, Temperature temperature, MassFlow massFlow) {
        LOGGER.info("Trying to create instance of dry air with parameters: Ps={}, ta={}, mda={}",pressure.toPascal(), temperature.toCelsius(), massFlow.toKiloGramPerSecond());
        return dryAirProperties.density(temperature, pressure)
                .mapLeft(l -> {
                    LOGGER.error("Flow cannot be created due to invalid density.");
                    return new InvalidFlow();
                })
                .flatMap(density -> dryAirProperties.specificHeat(temperature)
                        .mapLeft(l -> {
                            LOGGER.error("Flow cannot be created due to invalid specific heat.");
                            return new InvalidFlow();
                        })
                        .map(specHeat -> new DryAirState(pressure.toPascal(),
                                temperature.toCelsius(),
                                density,
                                specHeat))
                )
                .map(dryAir -> new FlowOfDryAir(dryAir, massFlow.toKiloGramPerSecond()));
    }


    public Either<InvalidFlow, FlowOfDryAir> createFlowOfDryAir(Temperature temperature, MassFlow massFlow) {
        return createFlowOfDryAir(Defaults.DEF_ATM_PRESSURE, temperature, massFlow);
    }

}

