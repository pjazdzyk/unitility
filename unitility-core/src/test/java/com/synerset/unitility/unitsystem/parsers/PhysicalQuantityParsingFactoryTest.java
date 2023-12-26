package com.synerset.unitility.unitsystem.parsers;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.basic.common.DistanceUnit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.basic.thermodynamic.Temperature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhysicalQuantityParsingFactoryTest {

    @Test
    @DisplayName("should create default parsing registry with registered parsers")
    void parsingRegistryTest_shouldCreateRegistry() {
        // Given
        // When
        PhysicalQuantityParsingFactory parsingRegistry = PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
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
        PhysicalQuantityParsingFactory parsingRegistry = PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;

        // When
        // Then
        assertThrows(UnitSystemClassNotSupportedException.class,
                () -> parsingRegistry.parseFromSymbol(TestClass.class, 20.0, "test"));
    }

    @Test
    @DisplayName("should fail when attempt to parse from invalid string")
    void createFromEngFormat_shouldFailIfQueriedForNonSupportedClass() {
        // Given
        PhysicalQuantityParsingFactory parsingRegistry = PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;

        // When
        // Then
        assertThrows(UnitSystemParseException.class,
                () -> parsingRegistry.parseFromEngFormat(TestClass.class, "20C"));
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