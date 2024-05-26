package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TrigonometricQuantityTest {

    @Test
    @DisplayName("Sine: should correctly calculate sine of the quantity's value in radians")
    void sin_shouldCalculateSine() {
        // Given
        Angle angle = Angle.ofRadians(Math.PI / 2);

        // When
        double sinAngle = angle.sin();

        // Then
        double expectedAngle = Angle.ofRadians(Math.sin(Math.PI / 2)).getValue();
        assertThat(sinAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Cosine: should correctly calculate cosine of the quantity's value in radians")
    void cos_shouldCalculateCosine() {
        // Given
        Angle angle = Angle.ofRadians(0);

        // When
        double cosAngle = angle.cos();

        // Then
        double expectedAngle = Angle.ofRadians(Math.cos(0)).getValue();
        assertThat(cosAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Tangent: should correctly calculate tangent of the quantity's value in radians")
    void tan_shouldCalculateTangent() {
        // Given
        Angle angle = Angle.ofRadians(Math.PI / 4);

        // When
        double tanAngle = angle.tan();

        // Then
        double expectedAngle = Angle.ofRadians(Math.tan(Math.PI / 4)).getValue();
        assertThat(tanAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Arcsine: should correctly calculate arcsine of the quantity's value")
    void asin_shouldCalculateArcsine() {
        // Given
        Angle angle = Angle.ofRadians(Math.sin(Math.PI / 6));

        // When
        double asinAngle = angle.asin();

        // Then
        double expectedAngle = Angle.ofRadians(Math.asin(Math.sin(Math.PI / 6))).getValue();
        assertThat(asinAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Arcsine out of range: should throw exception when calculating arcsine for out of range value")
    void asin_shouldThrowExceptionForOutOfRangeValue() {
        // Given
        Angle angle = Angle.ofRadians(2);  // sin(2) > 1, which is out of range for asin
        // Then
        assertThatThrownBy(angle::asin)
                .isInstanceOf(UnitSystemArgumentException.class)
                .hasMessageContaining("Value out of range for arcsine");
    }

    @Test
    @DisplayName("Hyperbolic sine: should correctly calculate hyperbolic sine of the quantity's value")
    void sinh_shouldCalculateHyperbolicSine() {
        // Given
        Angle angle = Angle.ofRadians(1);

        // When
        double sinhAngle = angle.sinh();

        // Then
        double expectedAngle = Angle.ofRadians(Math.sinh(1)).getValue();
        assertThat(sinhAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Hyperbolic cosine: should correctly calculate hyperbolic cosine of the quantity's value")
    void cosh_shouldCalculateHyperbolicCosine() {
        // Given
        Angle angle = Angle.ofRadians(1);

        // When
        double coshAngle = angle.cosh();

        // Then
        double expectedAngle = Angle.ofRadians(Math.cosh(1)).getValue();
        assertThat(coshAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Cotangent: should correctly calculate cotangent of the quantity's value")
    void cot_shouldCalculateCotangent() {
        // Given
        Angle angle = Angle.ofRadians(Math.PI / 4);

        // When
        double ctgAngle = angle.cot();

        // Then
        double expectedAngle = Angle.ofRadians(1 / Math.tan(Math.PI / 4)).getValue();
        assertThat(ctgAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Hyperbolic cotangent: should correctly calculate hyperbolic cotangent of the quantity's value")
    void coth_shouldCalculateHyperbolicCotangent() {
        // Given
        Angle angle = Angle.ofRadians(1);

        // When
        double ctghAngle = angle.coth();

        // Then
        double expectedAngle = Angle.ofRadians(1 / Math.tanh(1)).getValue();
        assertThat(ctghAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Arccosine: should correctly calculate arccosine of the quantity's value")
    void acos_shouldCalculateArccosine() {
        // Given
        Angle angle = Angle.ofRadians(Math.cos(Math.PI / 3));

        // When
        double acosAngle = angle.acos();

        // Then
        double expectedAngle = Angle.ofRadians(Math.acos(Math.cos(Math.PI / 3))).getValue();
        assertThat(acosAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Arctangent: should correctly calculate arctangent of the quantity's value")
    void atan_shouldCalculateArctangent() {
        // Given
        Angle angle = Angle.ofRadians(Math.tan(Math.PI / 4));

        // When
        double atanAngle = angle.atan();

        // Then
        double expectedAngle = Angle.ofRadians(Math.atan(Math.tan(Math.PI / 4))).getValue();
        assertThat(atanAngle).isEqualTo(expectedAngle);
    }

    @Test
    @DisplayName("Arccotangent: should correctly calculate arccotangent of the quantity's value")
    void acot_shouldCalculateArccotangent() {
        // Given
        Angle angle = Angle.ofRadians(Math.tan(Math.PI / 4));

        // When
        double actgAngle = angle.acot();

        // Then
        double expectedAngle = Angle.ofRadians(1 / Math.atan(Math.tan(Math.PI / 4))).getValue();
        assertThat(actgAngle).isEqualTo(expectedAngle);
    }
}
