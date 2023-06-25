package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.ForceUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ForceTest {

    @Test
    @DisplayName("should convert N to kN and vice versa")
    void shouldProperlyConvertNewtonsToKiloNewtons() {
        // Given
        Force initialForce = Force.ofNewtons(1000);
        // When
        Force actualInKiloNewtons = initialForce.toKiloNewtons();
        Force actualInNewtons = actualInKiloNewtons.toBaseUnit();

        // Then
        Force expectedInKiloNewtons = Force.ofKiloNewtons(1);
        assertThat(actualInKiloNewtons).isEqualTo(expectedInKiloNewtons);
        assertThat(actualInNewtons).isEqualTo(initialForce);
    }

    @Test
    @DisplayName("should convert N to kp and vice versa")
    void shouldProperlyConvertNewtonsToKiloPonds() {
        // Given
        Force initialForce = Force.ofNewtons(1000);

        // When
        Force actualInKiloPonds = initialForce.toKiloponds();
        Force actualInNewtons = actualInKiloPonds.toNewtons();

        // Then
        Force expectedInKiloPonds = Force.ofKiloponds(101.97162129779283);
        assertThat(actualInKiloPonds.getValue()).isEqualTo(expectedInKiloPonds.getValue(), withPrecision(1E-14));
        assertThat(actualInNewtons.getValue()).isEqualTo(initialForce.getValue(), withPrecision(1E-13));
    }

    @Test
    @DisplayName("should convert N to dyn and vice versa")
    void shouldProperlyConvertNewtonsToDynes() {
        // Given
        Force initialForce = Force.ofNewtons(1.0);

        // When
        Force actualInDyn = initialForce.toDynes();
        Force actualInNewtons = actualInDyn.toNewtons();

        // Then
        Force expectedInDyn = Force.ofDynes(100_000.0);
        assertThat(actualInDyn.getValue()).isEqualTo(expectedInDyn.getValue(), withPrecision(1E-11));
        assertThat(actualInNewtons.getValue()).isEqualTo(initialForce.getValue(), withPrecision(1E-15));
    }

    @Test
    @DisplayName("should convert N to lbf and vice versa")
    void shouldProperlyConvertNewtonsToPoundForce() {
        // Given
        Force initialForce = Force.ofNewtons(1000);

        // When
        Force actualInPoundForce = initialForce.toPoundForce();
        Force actualInNewtons = actualInPoundForce.toNewtons();

        // Then
        Force expectedInPoundForce = Force.ofPoundForce(224.8089430997105);
        assertThat(actualInPoundForce.getValue()).isEqualTo(expectedInPoundForce.getValue(), withPrecision(1E-15));
        assertThat(actualInNewtons).isEqualTo(initialForce);
    }

    @Test
    @DisplayName("should convert N to pdl and vice versa")
    void shouldProperlyConvertNewtonsToPoundal() {
        // Given
        Force initialForce = Force.ofNewtons(1000);
        // When
        Force actualInPoundal = initialForce.toPoundal();
        Force actualInNewtons = actualInPoundal.toBaseUnit();

        // Then
        Force expectedInPoundal = Force.ofPoundal(7233.013851209894);
        assertThat(actualInPoundal.getValue()).isEqualTo(expectedInPoundal.getValue(), withPrecision(1E-12));
        assertThat(actualInNewtons).isEqualTo(initialForce);
    }

    @Test
    @DisplayName("should have N as base unit")
    void shouldHaveNewtonAsBaseUnit() {
        // Given
        ForceUnits expectedBaseUnit = ForceUnits.NEWTON;

        // When
        Force forceInNewton = Force.ofNewtons(10);
        Unit<Force> actualBaseUnit = forceInNewton.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }
}