package com.synerset.exampleproject.fluidproperties;

import com.synerset.exampleproject.common.Defaults;
import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.control.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DryAirPropertiesFactoryTest {

    private static final DryAirPropertiesFactory dryAirPropertiesFactory = DryAirPropertiesFactory.ofDefaultEquations();

    @Test
    @DisplayName("should create dry air density when valid temperature and pressure is given")
    void shouldCreateDensityProperty_whenValidTemperatureAndPressureIsGiven() {
        // Given
        Temperature airTemp = Temperature.ofCelsius(20);
        Pressure atmPress = Defaults.DEF_PAT;

        // When
        Density actualDensity = dryAirPropertiesFactory.density(airTemp, atmPress).get();

        // Then
        Density expectedDensity = Density.ofKilogramPerCubicMeter(1.2040973427229398);
        assertThat(actualDensity).isEqualTo(expectedDensity);
    }

    @Test
    @DisplayName("should fail to create dry air density when invalid temperature and pressure is given")
    void shouldFailToCreateDensityProperty_whenInvalidTemperatureAndPressureIsGiven() {
        // Given
        Temperature airTemp = Temperature.ofCelsius(-10000);

        // When
        Either<InvalidProperty, Density> actualDensityEither = dryAirPropertiesFactory.density(airTemp, null);

        // Then
        assertThat(actualDensityEither.isLeft()).isTrue();
        InvalidProperty actualInvalidProperty = actualDensityEither.getLeft();
        assertThat(actualInvalidProperty).isNotNull();
        assertThat(actualInvalidProperty.msg()).contains("-10000");
        assertThat(actualInvalidProperty.msg()).contains("Null");
        assertThat(actualInvalidProperty.msg()).hasLineCount(3);
    }
}