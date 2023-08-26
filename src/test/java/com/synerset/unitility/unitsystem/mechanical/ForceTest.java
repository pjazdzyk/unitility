package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.Unit;
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
        Force actualInKiloNewtons = initialForce.toUnit(ForceUnits.KILONEWTON);
        double actualInKiloNewtonsVal = initialForce.getInKiloNewtons();
        Force actualInNewtons = actualInKiloNewtons.toBaseUnit();
        double actualInNewtonsVal = actualInKiloNewtons.getInNewtons();

        // Then
        Force expectedInKiloNewtons = Force.ofKiloNewtons(1);
        assertThat(actualInKiloNewtons.getValue()).isEqualTo(actualInKiloNewtonsVal);
        assertThat(actualInNewtons.getValue()).isEqualTo(actualInNewtonsVal);
        assertThat(actualInKiloNewtons).isEqualTo(expectedInKiloNewtons);
        assertThat(actualInNewtons).isEqualTo(initialForce);
    }

    @Test
    @DisplayName("should convert N to kp and vice versa")
    void shouldProperlyConvertNewtonsToKiloPonds() {
        // Given
        Force initialForce = Force.ofNewtons(1000);

        // When
        Force actualInKiloPonds = initialForce.toUnit(ForceUnits.KILOPOND);
        double actualInKiloPondsVal = initialForce.getInKiloponds();
        Force actualInNewtons = actualInKiloPonds.toBaseUnit();

        // Then
        Force expectedInKiloPonds = Force.ofKiloponds(101.97162129779283);
        assertThat(actualInKiloPonds.getValue()).isEqualTo(actualInKiloPondsVal);
        assertThat(actualInKiloPonds.getValue()).isEqualTo(expectedInKiloPonds.getValue(), withPrecision(1E-14));
        assertThat(actualInNewtons.getValue()).isEqualTo(initialForce.getValue(), withPrecision(1E-13));
    }

    @Test
    @DisplayName("should convert N to dyn and vice versa")
    void shouldProperlyConvertNewtonsToDynes() {
        // Given
        Force initialForce = Force.ofNewtons(1.0);

        // When
        Force actualInDyn = initialForce.toUnit(ForceUnits.DYNE);
        double actualInDynVal = initialForce.getInDynes();
        Force actualInNewtons = actualInDyn.toBaseUnit();

        // Then
        Force expectedInDyn = Force.ofDynes(100_000.0);
        assertThat(actualInDyn.getValue()).isEqualTo(actualInDynVal);
        assertThat(actualInDyn.getValue()).isEqualTo(expectedInDyn.getValue(), withPrecision(1E-11));
        assertThat(actualInNewtons.getValue()).isEqualTo(initialForce.getValue(), withPrecision(1E-15));
    }

    @Test
    @DisplayName("should convert N to lbf and vice versa")
    void shouldProperlyConvertNewtonsToPoundForce() {
        // Given
        Force initialForce = Force.ofNewtons(1000);

        // When
        Force actualInPoundForce = initialForce.toUnit(ForceUnits.POUND_FORCE);
        double actualInPoundForceVal = initialForce.getInPoundsForce();
        Force actualInNewtons = actualInPoundForce.toBaseUnit();

        // Then
        Force expectedInPoundForce = Force.ofPoundForce(224.8089430997105);
        assertThat(actualInPoundForce.getValue()).isEqualTo(actualInPoundForceVal);
        assertThat(actualInPoundForce.getValue()).isEqualTo(expectedInPoundForce.getValue(), withPrecision(1E-15));
        assertThat(actualInNewtons).isEqualTo(initialForce);
    }

    @Test
    @DisplayName("should convert N to pdl and vice versa")
    void shouldProperlyConvertNewtonsToPoundal() {
        // Given
        Force initialForce = Force.ofNewtons(1000);
        // When
        Force actualInPoundal = initialForce.toUnit(ForceUnits.POUNDAL);
        double actualInPoundalVal = initialForce.getInPoundals();
        Force actualInNewtons = actualInPoundal.toBaseUnit();

        // Then
        Force expectedInPoundal = Force.ofPoundal(7233.013851209894);
        assertThat(actualInPoundal.getValue()).isEqualTo(actualInPoundalVal);
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