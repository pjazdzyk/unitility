package com.synerset.unitility.unitsystem.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class AreaTest {

    @Test
    @DisplayName("should convert m² to km² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareKilometers() {
        // Given
        Area initialArea = Area.ofSquareMeters(1_000_000);

        // When
        Area actualInSquareKilometers = initialArea.toUnit(AreaUnits.SQUARE_KILOMETER);
        double actualSquareKilometersVal = initialArea.getInSquareKilometers();
        Area actualInSquareMeters = actualInSquareKilometers.toBaseUnit();
        double actualInSquareMetersVal = actualInSquareKilometers.getInSquareMeters();

        // Then
        Area expectedInSquareKilometers = Area.ofSquareKilometers(1);
        assertThat(actualInSquareKilometers).isEqualTo(expectedInSquareKilometers);
        assertThat(actualInSquareKilometers.getValue()).isEqualTo(actualSquareKilometersVal);
        assertThat(actualInSquareMeters.getValue()).isEqualTo(actualInSquareMetersVal);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to cm² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareCentimeters() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInSquareCentimeters = initialArea.toUnit(AreaUnits.SQUARE_CENTIMETER);
        double actualSquareCentimetersVal = initialArea.getInSquareCentimeters();
        Area actualInSquareMeters = actualInSquareCentimeters.toBaseUnit();

        // Then
        Area expectedInSquareCentimeters = Area.ofSquareCentimeters(10_000);
        assertThat(actualInSquareCentimeters).isEqualTo(expectedInSquareCentimeters);
        assertThat(actualInSquareCentimeters.getValue()).isEqualTo(actualSquareCentimetersVal);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to mm² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareMillimeters() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInSquareMillimeters = initialArea.toUnit(AreaUnits.SQUARE_MILLIMETER);
        double actualInSquareMillimetersVal = initialArea.getInSquareMillimeters();
        Area actualInSquareMeters = actualInSquareMillimeters.toBaseUnit();

        // Then
        Area expectedInSquareMillimeters = Area.ofSquareMillimeters(1_000_000);
        assertThat(actualInSquareMillimeters.getValue()).isEqualTo(actualInSquareMillimetersVal);
        assertThat(actualInSquareMillimeters).isEqualTo(expectedInSquareMillimeters);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ar and vice versa")
    void shouldProperlyConvertSquareMetersToAres() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInAres = initialArea.toUnit(AreaUnits.ARE);
        double actualInAresVal = initialArea.getInAres();
        Area actualInSquareMeters = actualInAres.toBaseUnit();

        // Then
        Area expectedInAres = Area.ofAres(0.01);
        assertThat(actualInAres.getValue()).isEqualTo(actualInAresVal);
        assertThat(actualInAres).isEqualTo(expectedInAres);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ha and vice versa")
    void shouldProperlyConvertSquareMetersToHectares() {
        // Given
        Area initialArea = Area.ofSquareMeters(10_000);

        // When
        Area actualInHectares = initialArea.toUnit(AreaUnits.HECTARE);
        double actualInHectaresVal = initialArea.getInHectares();
        Area actualInSquareMeters = actualInHectares.toBaseUnit();

        // Then
        Area expectedInHectares = Area.ofHectares(1);
        assertThat(actualInHectares.getValue()).isEqualTo(actualInHectaresVal);
        assertThat(actualInHectares).isEqualTo(expectedInHectares);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to in² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareInches() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInSquareInches = initialArea.toUnit(AreaUnits.SQUARE_INCH);
        double actualInSquareInchesVal = initialArea.getInSquareInches();
        Area actualInSquareMeters = actualInSquareInches.toBaseUnit();

        // Then
        Area expectedInSquareInches = Area.ofSquareInches(1550.0031000062);
        assertThat(expectedInSquareInches.getValue()).isEqualTo(actualInSquareInchesVal, withPrecision(10E-10));
        assertThat(actualInSquareInches.getValue()).isEqualTo(expectedInSquareInches.getValue(), withPrecision(2E-13));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ft² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareFeet() {
        // Given
        Area initialArea = Area.ofSquareMeters(100);

        // When
        Area actualInSquareFeet = initialArea.toUnit(AreaUnits.SQUARE_FOOT);
        double actualInSquareFeetVal = initialArea.getInSquareFeet();
        Area actualInSquareMeters = actualInSquareFeet.toBaseUnit();

        // Then
        Area expectedInSquareFeet = Area.ofSquareFeet(1076.391041670972);
        assertThat(actualInSquareFeet.getValue()).isEqualTo(actualInSquareFeetVal);
        assertThat(actualInSquareFeet.getValue()).isEqualTo(expectedInSquareFeet.getValue(), withPrecision(1E-15));
        assertThat(actualInSquareMeters.getValue()).isEqualTo(initialArea.getValue(), withPrecision(1E-14));
    }

    @Test
    @DisplayName("should convert m² to yd² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareYards() {
        // Given
        Area initialArea = Area.ofSquareMeters(100);

        // When
        Area actualInSquareYards = initialArea.toUnit(AreaUnits.SQUARE_YARD);
        double actualInSquareYardsVal = initialArea.getInSquareYards();
        Area actualInSquareMeters = actualInSquareYards.toBaseUnit();

        // Then
        Area expectedInSquareYards = Area.ofSquareYards(119.599004630108);
        assertThat(actualInSquareYards.getValue()).isEqualTo(actualInSquareYardsVal);
        assertThat(actualInSquareYards.getValue()).isEqualTo(expectedInSquareYards.getValue(), withPrecision(1E-13));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ac and vice versa")
    void shouldProperlyConvertSquareMetersToAcres() {
        // Given
        Area initialArea = Area.ofSquareMeters(100);

        // When
        Area actualInAcres = initialArea.toUnit(AreaUnits.ACRE);
        double actualInAcresVal = initialArea.getInAcres();
        Area actualInSquareMeters = actualInAcres.toBaseUnit();

        // Then
        Area expectedInAcres = Area.ofSquareYards(0.0247105381467165);
        assertThat(actualInAcres.getValue()).isEqualTo(actualInAcresVal);
        assertThat(actualInAcres.getValue()).isEqualTo(expectedInAcres.getValue(), withPrecision(1E-15));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
        assertThat(actualInAcres).isEqualTo(Area.ofAcres(actualInAcresVal));
    }

    @Test
    @DisplayName("should convert m² to mi² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareMiles() {
        // Given
        Area initialArea = Area.ofSquareMeters(100_000);

        // When
        Area actualInSquareMiles = initialArea.toUnit(AreaUnits.SQUARE_MILE);
        double actualInSquareMilesVal = initialArea.getInSquareMiles();
        Area actualInSquareMeters = actualInSquareMiles.toBaseUnit();

        // Then
        Area expectedInSquareMiles = Area.ofSquareMiles(0.0386102158542446);
        assertThat(actualInSquareMiles.getValue()).isEqualTo(actualInSquareMilesVal);
        assertThat(actualInSquareMiles.getValue()).isEqualTo(expectedInSquareMiles.getValue(), withPrecision(1E-15));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should have m² as base unit")
    void shouldHaveSquareMeterAsBaseUnit() {
        // Given
        AreaUnits expectedBaseUnit = AreaUnits.SQUARE_METER;

        // When
        Area areaInSquareYards = Area.ofSquareYards(10);
        AreaUnits actualBaseUnit = areaInSquareYards.getUnitType().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Area expected = Area.ofSquareMeters(10.1);

        // When
        Area actual = expected.toSquareKilometers()
                .toSquareCentimeters()
                .toSquareMillimeters()
                .toAres()
                .toHectares()
                .toSquareInches()
                .toSquareFeet()
                .toSquareYards()
                .toAcres()
                .toSquareMiles()
                .toSquareMeters();
        double actualValue = expected.getInSquareMeters();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }
}