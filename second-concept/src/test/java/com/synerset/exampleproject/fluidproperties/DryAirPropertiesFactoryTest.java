package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.dynamicviscosity.DynamicViscosity;
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
        Pressure atmPress = Pressure.STANDARD_ATMOSPHERE;

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
        Pressure press = Pressure.STANDARD_ATMOSPHERE;

        // When
        Either<InvalidQuantity, Density> actualDensityEither = dryAirPropertiesFactory.density(airTemp, press);

        // Then
        assertThat(actualDensityEither.isLeft()).isTrue();
        InvalidQuantity actualInvalidQuantity = actualDensityEither.getLeft();
        assertThat(actualInvalidQuantity).isNotNull();
        assertThat(actualInvalidQuantity.msg()).hasLineCount(2);
    }

    @Test
    @DisplayName("should create dynamic viscosity of fluid when valid temperature is given")
    void shouldCreateDynamicViscosityProperty_whenValidTemperatureAndPressureIsGiven() {
        // Given
        Temperature fluidTemp = Temperature.ofCelsius(20);

        // When
        DynamicViscosity actualDynamicViscosity = dryAirPropertiesFactory.dynamicViscosity(fluidTemp).get();

        // Then
        DynamicViscosity expectedDynamicViscosity = DynamicViscosity.ofPascalSecond(1.8062406974316945E-5);
        assertThat(actualDynamicViscosity.getValue()).isEqualTo(expectedDynamicViscosity.getValue(), withPrecision(1E-15));
    }
    
}