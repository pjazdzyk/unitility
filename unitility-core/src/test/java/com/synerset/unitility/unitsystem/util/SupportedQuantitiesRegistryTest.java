package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SupportedQuantitiesRegistryTest {

    private static final SupportedQuantitiesRegistry QUANTITY_REGISTRY = SupportedQuantitiesRegistry.getInstance();
    private static final PhysicalQuantityParsingFactory PARSING_FACTORY = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @Test
    @DisplayName("Supported quantity registry: should find all supported quantities and associated units")
    void findAllSupportedQuantities_shouldFindAllSupportedQuantitiesAndAssociatedUnits() {
        // Given
        // When
        Set<PhysicalQuantityInfo> allSupportedQuantities = QUANTITY_REGISTRY.findAllSupportedQuantities();

        // Then
        assertThat(allSupportedQuantities).isNotNull().isNotEmpty().hasSize(37);
    }

    @Test
    @DisplayName("Supported quantity registry: all parsing registry quantity classes should be included in quantity registry")
    void findAllSupportedQuantities_shouldAllParsingFactoryClassesBePresentInQuantityRegistry() {
        // Given
        Set<Class<PhysicalQuantity<Unit>>> registeredSupportedQuantityClasses = QUANTITY_REGISTRY.findAllRegisteredClasses();
        Set<Class<PhysicalQuantity<Unit>>> registeredParsingFactoryClasses = PARSING_FACTORY.findAllRegisteredClasses();

        // When
        // Then
        assertThat(registeredSupportedQuantityClasses).containsAll(registeredParsingFactoryClasses);
    }

    @Test
    @DisplayName("Supported quantity registry: should find registered quantity info based on provided class")
    void findQuantityInfoByClass_should_shouldFindRegisteredQuantityInfoWhenClassIsGiven() {
        // Given
        Class<?> temperatureClass = Temperature.class;

        // When
        PhysicalQuantityInfo physicalQuantityInfo = QUANTITY_REGISTRY.findQuantityInfoByClass(temperatureClass).orElse(null);

        // Then
        assertThat(physicalQuantityInfo).isNotNull();
        assertThat(physicalQuantityInfo.quantityClass()).isEqualTo(temperatureClass);
        assertThat(physicalQuantityInfo.supportedUnits()).isNotNull().hasSize(TemperatureUnits.values().length);
        List<String> temperatureSymbols = Arrays.stream(TemperatureUnits.values()).map(TemperatureUnits::getSymbol).toList();
        assertThat(physicalQuantityInfo.supportedUnits()).extracting(PhysicalUnitInfo::unitSymbol).containsAll(temperatureSymbols);
    }

}