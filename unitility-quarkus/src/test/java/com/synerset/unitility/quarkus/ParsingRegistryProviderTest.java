package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParsingRegistryProviderTest {

    @Test
    @DisplayName("should create default parsing registry")
    void createParsingRegistryTest() {
        // Given
        ParsingRegistryProvider parsingRegistryProvider =  new ParsingRegistryProvider();
        PhysicalQuantityParsingFactory expectedRegistry = PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
        PhysicalQuantityParsingFactory expectedGeoRegistry = PhysicalQuantityParsingFactory.GEO_PARSING_FACTORY;

        // When
        PhysicalQuantityParsingFactory actualParsingRegistry = parsingRegistryProvider.createParsingFactory();
        PhysicalQuantityParsingFactory actualGeoParsingRegistry = parsingRegistryProvider.createGeoParsingFactory();

        // Then
        assertThat(expectedRegistry.findAllRegisteredClasses()).isEqualTo(actualParsingRegistry.findAllRegisteredClasses());
        assertThat(expectedGeoRegistry.findAllRegisteredClasses()).isEqualTo(actualGeoParsingRegistry.findAllRegisteredClasses());
    }
}