package com.synerset.unitility.unitsystem.dimensionless;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GrashofNumTest {

    @Test
    @DisplayName("should create Grashof number")
    void shouldCreateGrashofNumber(){
        // Given
        double expectedValue = 10.0;

        // When
        GrashofNum actualGrashofNum = GrashofNum.of(expectedValue);

        // Then
        GrashofNum expectedGrashofNum = GrashofNum.of(10.0);
        assertThat(actualGrashofNum.getValue()).isEqualTo(expectedValue);
        assertThat(actualGrashofNum).isEqualTo(expectedGrashofNum);
    }

}
