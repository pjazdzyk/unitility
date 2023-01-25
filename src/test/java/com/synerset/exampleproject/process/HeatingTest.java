package com.synerset.exampleproject.process;

import com.synerset.exampleproject.properties.DryAirProperties;
import com.synerset.exampleproject.state.AirFlowFactory;
import com.synerset.exampleproject.state.FlowOfDryAir;
import com.synerset.unitsystem.massflow.KiloGramPerSecond;
import com.synerset.unitsystem.massflow.MassFlow;
import com.synerset.unitsystem.power.KiloWatt;
import com.synerset.unitsystem.power.Power;
import com.synerset.unitsystem.temperature.Celsius;
import com.synerset.unitsystem.temperature.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HeatingTest {

    // Given
    private static final DryAirProperties DRY_AIR_PROP_FACTORY = DryAirProperties.getInstance();
    private static final AirFlowFactory FLOW_FACTORY = new AirFlowFactory(DRY_AIR_PROP_FACTORY);
    private static final Heating HEATING_PROCESS = new Heating(FLOW_FACTORY);

    @Test
    @DisplayName("should heat up the inlet air when target outlet temperature is given")
    void shouldHeatUpInletAirWhenTargetTempIsGiven() {
        // Given
        KiloGramPerSecond expectedMassFlow = MassFlow.kiloGramPerSecond(0.1);
        FlowOfDryAir inletFlow = FLOW_FACTORY.createFlowOfDryAir(Temperature.celsius(-20.0), expectedMassFlow);
        Celsius expectedTargetTemp = Temperature.celsius(18.0);

        // When
        HeatingResultDto heatingResults = HEATING_PROCESS.fromOutletTemperature(inletFlow, expectedTargetTemp);
        Celsius actualOutTemperature = heatingResults.outletFlow().temperature();
        MassFlow actualMassFlow = heatingResults.outletFlow().massFlow();
        KiloWatt actualPower = heatingResults.outputPower();

        // Then
        assertThat(actualOutTemperature).isEqualTo(expectedTargetTemp);
        assertThat(actualMassFlow).isEqualTo(expectedMassFlow);
        KiloWatt expectedPower = Power.watt(3811.4).toKiloWatt();
        assertThat(actualPower).isEqualTo(expectedPower);
    }




}