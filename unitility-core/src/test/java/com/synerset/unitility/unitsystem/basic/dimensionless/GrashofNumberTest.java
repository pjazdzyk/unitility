package com.synerset.unitility.unitsystem.basic.dimensionless;

import com.synerset.unitility.unitsystem.basic.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.basic.dimensionless.GrashofNumberUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GrashofNumberTest {

    @Test
    @DisplayName("should create Grashof number")
    void shouldCreateGrashofNumber() {
        // Given
        double expectedValue = 10.0;

        // When
        GrashofNumber actualGrashofNumber = GrashofNumber.of(expectedValue);

        // Then
        GrashofNumber expectedGrashofNumber = GrashofNumber.of(10.0);
        assertThat(actualGrashofNumber.getValue()).isEqualTo(expectedValue);
        assertThat(actualGrashofNumber.getBaseValue()).isEqualTo(actualGrashofNumber.getValue());
        assertThat(actualGrashofNumber.toUnit(GrashofNumberUnits.DIMENSIONLESS)).isEqualTo(actualGrashofNumber.toBaseUnit());
        assertThat(actualGrashofNumber).isEqualTo(expectedGrashofNumber);
    }

}
