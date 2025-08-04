package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.common.Diameter;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.Height;
import com.synerset.unitility.unitsystem.common.RatioUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class SDRTest {

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        SDR expected = SDR.of(10.1);

        // When
        SDR actual = expected.toUnit(RatioUnits.PERCENT);

        double actualValue = expected.getValue();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("SDR: should return valid result from two PhysicalQuantity passed as arguments")
    void shouldReturnValidResultFromTwoPhysicalQuantityPassedAsArguments() {
        // Given
        Diameter outerDiameter = Diameter.ofMillimeters(20);
        Distance wallThickness = Distance.ofMillimeters(2);

        // When
        SDR flowSDR = SDR.from(outerDiameter, wallThickness);
        SDR flowSDRFromValues = SDR.from(20, 2);

        // Then
        assertThat(flowSDR).isNotNull();
        assertThat(flowSDRFromValues).isEqualTo(flowSDR);
        assertThat(flowSDRFromValues.getValue()).isEqualTo(10);
        assertThat(flowSDR.getInUnit(RatioUnits.PERCENT)).isEqualTo(1000);
    }

    @Test
    @DisplayName("SDR: should correctly resolve outer diameter and wall thickness from SDR")
    void shouldSuccessfullyResolveOuterDiameterAndWallThicknessFromSDR(){
        // Given
        SDR sdr = SDR.of(27.6);

        Diameter expectedOuterDiameter = Diameter.ofMillimeters(110);
        Height expectedWallThickness = Height.ofMillimeters(4.0);

        // When
        Diameter actualNominalOuterDiameter = sdr.getNominalOuterDiameter(expectedWallThickness);
        double actualNominalOuterDiameterValue = sdr.getNominalOuterDiameter(expectedWallThickness).getValue();
        Height actualWallThickness = sdr.getNominalMinWallThickness(expectedOuterDiameter);
        double actualWallThicknessValue = sdr.getNominalMinWallThickness(expectedOuterDiameter).getValue();

        // Then
        assertThat(actualNominalOuterDiameter.getValue()).isEqualTo(expectedOuterDiameter.getValue(), withPrecision(0.5));
        assertThat(actualWallThickness.getInMillimeters()).isEqualTo(expectedWallThickness.getInMillimeters(), withPrecision(0.02));

        assertThat(actualNominalOuterDiameterValue).isEqualTo(actualNominalOuterDiameter.getValue());
        assertThat(actualWallThicknessValue).isEqualTo(actualWallThickness.getValue());
    }

}