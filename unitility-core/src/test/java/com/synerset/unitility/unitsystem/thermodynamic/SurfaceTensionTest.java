package com.synerset.unitility.unitsystem.thermodynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SurfaceTensionTest {

    @Test
    @DisplayName("SurfaceTension: should have N/m as base unit")
    void shouldHaveNewtonsPerMeterAsBaseUnit() {
        // Given
        SurfaceTensionUnit expectedBaseUnit = SurfaceTensionUnits.NEWTON_PER_METER;

        // When
        SurfaceTension quantity = SurfaceTension.ofMillinewtonsPerMeter(10);
        SurfaceTensionUnit actualBaseUnit = quantity.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("SurfaceTension: should convert N/m to mN/m and back")
    void shouldConvertNewtonsToMillinewtonsAndBack() {
        // Given
        SurfaceTension initial = SurfaceTension.ofNewtonsPerMeter(0.0728);

        // When
        SurfaceTension inMillinewtons = initial.toMillinewtonsPerMeter();
        SurfaceTension backToBase = inMillinewtons.toBaseUnit();

        // Then
        assertThat(inMillinewtons.getValue()).isEqualTo(72.8, withPrecision(1E-12));
        assertThat(backToBase.getValue()).isEqualTo(initial.getValue(), withPrecision(1E-15));
    }

    @Test
    @DisplayName("SurfaceTension: one dyn/cm should equal one mN/m")
    void shouldTreatDynePerCentimeterAsMillinewtonPerMeter() {
        // Given
        SurfaceTension inDynes = SurfaceTension.ofDynesPerCentimeter(1.0);

        // When
        double inNewtonsPerMeter = inDynes.getInNewtonsPerMeter();
        double inMillinewtonsPerMeter = inDynes.getInMillinewtonsPerMeter();

        // Then  1 dyn/cm = 1E-5 N / 1E-2 m = 1E-3 N/m
        assertThat(inNewtonsPerMeter).isEqualTo(1E-3, withPrecision(1E-18));
        assertThat(inMillinewtonsPerMeter).isEqualTo(1.0, withPrecision(1E-15));
        assertThat(inDynes).isEqualTo(SurfaceTension.ofMillinewtonsPerMeter(1.0));
    }

    @Test
    @DisplayName("SurfaceTension: should convert lbf/ft to N/m")
    void shouldConvertPoundForcePerFootToBase() {
        // Given
        SurfaceTension onePoundForcePerFoot = SurfaceTension.ofPoundsForcePerFoot(1.0);

        // When
        double inNewtonsPerMeter = onePoundForcePerFoot.getInNewtonsPerMeter();
        SurfaceTension roundTrip = onePoundForcePerFoot.toNewtonsPerMeter().toPoundsForcePerFoot();

        // Then  1 lbf/ft = 4.4482216152605 N / 0.3048 m = 14.593902937206...
        assertThat(inNewtonsPerMeter).isEqualTo(14.5939029372064, withPrecision(1E-12));
        assertThat(roundTrip.getValue()).isEqualTo(1.0, withPrecision(1E-14));
        assertThat(onePoundForcePerFoot.getInPoundsForcePerFoot()).isEqualTo(1.0);
    }

    @Test
    @DisplayName("SurfaceTension: should resolve every unit from its symbol")
    void shouldResolveUnitsFromSymbols() {
        // When
        SurfaceTension fromNewtons = SurfaceTension.of(1.0, "N/m");
        SurfaceTension fromMillinewtons = SurfaceTension.of(1.0, "mN/m");
        SurfaceTension fromDynes = SurfaceTension.of(1.0, "dyn/cm");
        SurfaceTension fromPoundsForce = SurfaceTension.of(1.0, "lbf/ft");
        SurfaceTension fromBlank = SurfaceTension.of(1.0, "");

        // Then
        assertThat(fromNewtons.getUnit()).isEqualTo(SurfaceTensionUnits.NEWTON_PER_METER);
        assertThat(fromMillinewtons.getUnit()).isEqualTo(SurfaceTensionUnits.MILLINEWTON_PER_METER);
        assertThat(fromDynes.getUnit()).isEqualTo(SurfaceTensionUnits.DYNE_PER_CENTIMETER);
        assertThat(fromPoundsForce.getUnit()).isEqualTo(SurfaceTensionUnits.POUND_FORCE_PER_FOOT);
        assertThat(fromBlank.getUnit()).isEqualTo(SurfaceTensionUnits.NEWTON_PER_METER);
    }

    @Test
    @DisplayName("SurfaceTension: should compare equal across different units")
    void shouldCompareEqualAcrossDifferentUnits() {
        // Given
        SurfaceTension inNewtons = SurfaceTension.ofNewtonsPerMeter(0.5);
        SurfaceTension inMillinewtons = SurfaceTension.ofMillinewtonsPerMeter(500.0);

        // Then
        assertThat(inNewtons).isEqualTo(inMillinewtons);
        assertThat(inNewtons.hashCode()).isEqualTo(inMillinewtons.hashCode());
    }
}
