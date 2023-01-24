package com.synerset.state;

import com.synerset.common.Defaults;
import com.synerset.properties.DryAirProperties;
import com.synerset.unitsystem.massflow.MassFlow;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.temperature.Temperature;

public class AirFlowFactory {

    private final DryAirProperties dryAirProperties;

    public AirFlowFactory(DryAirProperties dryAirProperties) {
        this.dryAirProperties = dryAirProperties;
    }

    public FlowOfDryAir createFlowOfDryAir(Pressure pressure, Temperature temperature, MassFlow massFlow) {
        DryAirState dryAirState = new DryAirState(
                pressure.toPascal(),
                temperature.toCelsius(),
                dryAirProperties.density(temperature, pressure),
                dryAirProperties.specificHeat(temperature)
        );
        return new FlowOfDryAir(dryAirState, massFlow.toKiloGramPerSecond());
    }

    public FlowOfDryAir createFlowOfDryAir(Temperature temperature, MassFlow massFlow) {
        return createFlowOfDryAir(Defaults.DEF_ATM_PRESSURE, temperature, massFlow);
    }

}
