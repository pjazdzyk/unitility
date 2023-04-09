package com.synerset.unitility.unitsystem.area;

import com.synerset.unitility.unitsystem.Unit;
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
        Area actualInSquareKilometers = initialArea.toSquareKilometers();
        Area actualInSquareMeters = actualInSquareKilometers.toBaseUnit();

        // Then
        Area expectedInSquareKilometers = Area.ofSquareKilometers(1);
        assertThat(actualInSquareKilometers).isEqualTo(expectedInSquareKilometers);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to cm² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareCentimeters() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInSquareCentimeters = initialArea.toSquareCentimeters();
        Area actualInSquareMeters = actualInSquareCentimeters.toBaseUnit();

        // Then
        Area expectedInSquareCentimeters = Area.ofSquareCentimeters(10_000);
        assertThat(actualInSquareCentimeters).isEqualTo(expectedInSquareCentimeters);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to mm² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareMillimeters() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInSquareMillimeters = initialArea.toSquareMillimeters();
        Area actualInSquareMeters = actualInSquareMillimeters.toBaseUnit();

        // Then
        Area expectedInSquareMillimeters = Area.ofSquareMillimeters(1_000_000);
        assertThat(actualInSquareMillimeters).isEqualTo(expectedInSquareMillimeters);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ar and vice versa")
    void shouldProperlyConvertSquareMetersToAres() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInAres = initialArea.toAres();
        Area actualInSquareMeters = actualInAres.toBaseUnit();

        // Then
        Area expectedInAres = Area.ofAres(0.01);
        assertThat(actualInAres).isEqualTo(expectedInAres);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ha and vice versa")
    void shouldProperlyConvertSquareMetersToHectares() {
        // Given
        Area initialArea = Area.ofSquareMeters(10_000);

        // When
        Area actualInHectares = initialArea.toHectares();
        Area actualInSquareMeters = actualInHectares.toSquareMeters();

        // Then
        Area expectedInHectares = Area.ofHectares(1);
        assertThat(actualInHectares).isEqualTo(expectedInHectares);
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to in² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareInches() {
        // Given
        Area initialArea = Area.ofSquareMeters(1);

        // When
        Area actualInSquareInches = initialArea.toSquareInches();
        Area actualInSquareMeters = actualInSquareInches.toBaseUnit();

        // Then
        Area expectedInSquareInches = Area.ofSquareInches(1550.0031000062);
        assertThat(actualInSquareInches.getValue()).isEqualTo(expectedInSquareInches.getValue(), withPrecision(2E-13));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ft² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareFeet() {
        // Given
        Area initialArea = Area.ofSquareMeters(100);

        // When
        Area actualInSquareFeet = initialArea.toSquareFeet();
        Area actualInSquareMeters = actualInSquareFeet.toBaseUnit();

        // Then
        Area expectedInSquareFeet = Area.ofSquareFeet(1076.391041670972);
        assertThat(actualInSquareFeet.getValue()).isEqualTo(expectedInSquareFeet.getValue(), withPrecision(1E-15));
        assertThat(actualInSquareMeters.getValue()).isEqualTo(initialArea.getValue(), withPrecision(1E-14));
    }

    @Test
    @DisplayName("should convert m² to yd² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareYards() {
        // Given
        Area initialArea = Area.ofSquareMeters(100);

        // When
        Area actualInSquareYards = initialArea.toSquareYards();
        Area actualInSquareMeters = actualInSquareYards.toBaseUnit();

        // Then
        Area expectedInSquareYards = Area.ofSquareYards(119.599004630108);
        assertThat(actualInSquareYards.getValue()).isEqualTo(expectedInSquareYards.getValue(), withPrecision(1E-13));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to ac and vice versa")
    void shouldProperlyConvertSquareMetersToAcres() {
        // Given
        Area initialArea = Area.ofSquareMeters(100);

        // When
        Area actualInAcres = initialArea.toAcres();
        Area actualInSquareMeters = actualInAcres.toBaseUnit();

        // Then
        Area expectedInAcres = Area.ofSquareYards(0.0247105381467165);
        assertThat(actualInAcres.getValue()).isEqualTo(expectedInAcres.getValue(), withPrecision(1E-15));
        assertThat(actualInSquareMeters).isEqualTo(initialArea);
    }

    @Test
    @DisplayName("should convert m² to mi² and vice versa")
    void shouldProperlyConvertSquareMetersToSquareMiles() {
        // Given
        Area initialArea = Area.ofSquareMeters(100_000);

        // When
        Area actualInSquareMiles = initialArea.toSquareMiles();
        Area actualInSquareMeters = actualInSquareMiles.toBaseUnit();

        // Then
        Area expectedInSquareMiles = Area.ofSquareMiles(0.0386102158542446);
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
        Unit<Area> actualBaseUnit = areaInSquareYards.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

}