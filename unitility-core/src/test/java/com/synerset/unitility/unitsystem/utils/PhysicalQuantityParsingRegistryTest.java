package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhysicalQuantityParsingRegistryTest {

    @Test
    @DisplayName("should create default parsing registry with registered parsers")
    void parsingRegistryTest_shouldCreateRegistry() {
        // Given
        // When
        PhysicalQuantityParsingRegistry parsingRegistry = PhysicalQuantityParsingRegistry.DEFAULT_PARSING_REGISTRY;
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> registryMap = parsingRegistry.getRegistryMap();
        Set<Class<PhysicalQuantity<Unit>>> registeredClasses = parsingRegistry.findAllRegisteredClasses();
        boolean status = parsingRegistry.containsClass(Temperature.class);

        // Then
        assertThat(parsingRegistry).isNotNull();
        assertThat(registryMap).isNotNull().isNotEmpty().containsKey(Temperature.class);
        assertThat(registeredClasses).isNotNull().isNotEmpty().hasSizeGreaterThan(27);
        assertThat(status).isTrue();
    }

    @Test
    @DisplayName("should fail when attempt to parse for not registered class")
    void createFromSymbol_shouldFailIfQueriedForNonSupportedClass() {
        // Given
        PhysicalQuantityParsingRegistry parsingRegistry = PhysicalQuantityParsingRegistry.DEFAULT_PARSING_REGISTRY;

        // When
        // Then
        assertThrows(UnitSystemClassNotSupportedException.class,
                () -> parsingRegistry.fromSymbol(TestClass.class, 20.0, "test"));
    }

    @Test
    @DisplayName("should fail when attempt to parse from invalid string")
    void createFromEngFormat_shouldFailIfQueriedForNonSupportedClass() {
        // Given
        PhysicalQuantityParsingRegistry parsingRegistry = PhysicalQuantityParsingRegistry.DEFAULT_PARSING_REGISTRY;

        // When
        // Then
        assertThrows(UnitSystemParseException.class,
                () -> parsingRegistry.fromEngFormat(TestClass.class, "20C"));
    }


    /**
     * Test class, non-existent in parsing registry, created to test failure cases.
     */
    static class TestClass implements PhysicalQuantity<DistanceUnit> {

        @Override
        public double getValue() {
            return 0;
        }

        @Override
        public double getBaseValue() {
            return 0;
        }

        @Override
        public DistanceUnit getUnitType() {
            return null;
        }

        @Override
        public PhysicalQuantity<DistanceUnit> toBaseUnit() {
            return null;
        }

        @Override
        public PhysicalQuantity<DistanceUnit> toUnit(DistanceUnit targetUnit) {
            return null;
        }

        @Override
        public <Q extends PhysicalQuantity<DistanceUnit>> Q withValue(double value) {
            return null;
        }
    }

}