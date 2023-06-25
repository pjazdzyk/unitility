package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class TorqueTest {

    @Test
    @DisplayName("should convert Nm to mN·m and vice versa")
    void shouldProperlyConvertNewtonMetersToMilliNewtonMeters() {
        // Given
        Torque initialTorque = Torque.ofNewtonMeters(1000);

        // When
        Torque actualInMilliNewtonMeters = initialTorque.toUnit(TorqueUnits.MILLINEWTON_METER);
        Torque actualInNewtonMeters = actualInMilliNewtonMeters.toBaseUnit();

        // Then
        Torque expectedInMilliNewtonMeters = Torque.ofMillinewtonMeters(1_000_000);
        assertThat(actualInMilliNewtonMeters).isEqualTo(expectedInMilliNewtonMeters);
        assertThat(actualInNewtonMeters).isEqualTo(initialTorque);
    }

    @Test
    @DisplayName("should convert Nm to kp·m and vice versa")
    void shouldProperlyConvertNewtonMetersToKilopondMeters() {
        // Given
        Torque initialTorque = Torque.ofNewtonMeters(1000);

        // When
        Torque actualInKilopondMeters = initialTorque.toKilopondMeters();
        Torque actualInNewtonMeters = actualInKilopondMeters.toBaseUnit();

        // Then
        Torque expectedInKilopondMeters = Torque.ofKilopondMeters(101.9716212977928);
        assertThat(actualInKilopondMeters.getValue()).isEqualTo(expectedInKilopondMeters.getValue(), withPrecision(1E-13));
        assertThat(actualInNewtonMeters.getValue()).isEqualTo(initialTorque.getValue(),withPrecision(1E-13));
    }

    @Test
    @DisplayName("should convert Nm to ft·lb and vice versa")
    void shouldProperlyConvertNewtonMetersToFootPounds() {
        // Given
        Torque initialTorque = Torque.ofNewtonMeters(1000);

        // When
        Torque actualInFootPounds = initialTorque.toPoundFeet();
        Torque actualInNewtonMeters = actualInFootPounds.toBaseUnit();

        // Then
        Torque expectedInFootPounds = Torque.ofFootPounds(737.56214927726536388);
        assertThat(actualInFootPounds.getValue()).isEqualTo(expectedInFootPounds.getValue(), withPrecision(1E-13));
        assertThat(actualInNewtonMeters.getValue()).isEqualTo(initialTorque.getValue(), withPrecision(1E-13));
    }

    @Test
    @DisplayName("should convert Nm to in·lb and vice versa")
    void shouldProperlyConvertNewtonMetersToInchPounds() {
        // Given
        Torque initialTorque = Torque.ofNewtonMeters(1000);

        // When
        Torque actualInInchPounds = initialTorque.toInchPounds();
        Torque actualInNewtonMeters = actualInInchPounds.toBaseUnit();

        // Then
        Torque expectedInInchPounds = Torque.ofInchPounds(8850.74579132718436654);
        assertThat(actualInInchPounds.getValue()).isEqualTo(expectedInInchPounds.getValue(), withPrecision(1E-10));
        assertThat(actualInNewtonMeters).isEqualTo(initialTorque);
    }

    @Test
    @DisplayName("should have Nm as base unit")
    void shouldHaveNewtonMetersAsBaseUnit() {
        // Given
        TorqueUnits expectedBaseUnit = TorqueUnits.NEWTON_METER;

        // When
        Torque torqueInNewtonMeters = Torque.ofNewtonMeters(10);
        Unit<Torque> actualBaseUnit = torqueInNewtonMeters.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }
}
