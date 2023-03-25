package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.density.KiloGramPerCubicMeter;
import com.synerset.unitsystem.pressure.Pascal;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.pressure.Psi;
import com.synerset.unitsystem.temperature.Celsius;
import com.synerset.unitsystem.temperature.Fahrenheit;
import com.synerset.unitsystem.temperature.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DryAirPropertiesTest {

    private final DryAirProperties DRY_AIR_PROPERTIES = DryAirProperties.getInstance();

    @Test
    @DisplayName("should return kinematic viscosity when temperature and density is given in SI")
    void shouldReturnKinematicViscosityWhenTempAndDensityIsGivenInSiUnits(){
        // Given
        Celsius airTemp = Temperature.celsius(20).get();
        Pascal airPress = Pressure.pascal(100_000);

        // When
        KiloGramPerCubicMeter actualDensity = DRY_AIR_PROPERTIES.density(airTemp, airPress).get();

        // Then
        KiloGramPerCubicMeter expectedDensity = Density.kiloGramPerCubicMeter(1.1883516829241942).get();
        assertThat(actualDensity).isEqualTo(expectedDensity);
    }

    @Test
    @DisplayName("should return kinematic viscosity when temperature and density is given in Imperial")
    void shouldReturnKinematicViscosityWhenTempAndDensityIsGivenInImperialUnits(){
        // Given
        Fahrenheit airTemp = Temperature.celsius(20).get().toFahrenheit();
        Psi airPress = Pressure.pascal(100_000).toPsi();

        // When
        KiloGramPerCubicMeter actualDensity = DRY_AIR_PROPERTIES.density(airTemp, airPress).get();

        // Then
        KiloGramPerCubicMeter expectedDensity = Density.kiloGramPerCubicMeter(1.1883516829241942).get();
        assertThat(actualDensity).isEqualTo(expectedDensity);
    }


}