package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParsingRegistryProviderTest {

    @Test
    @DisplayName("should create default parsing registry")
    void createParsingRegistryTest() {
        // Given
        ParsingRegistryProvider parsingRegistryProvider =  new ParsingRegistryProvider();
        PhysicalQuantityParsingFactory expectedRegistry = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        // When
        PhysicalQuantityParsingFactory actualParsingRegistry = parsingRegistryProvider.createParsingFactory();

        // Then
        assertThat(expectedRegistry.findAllRegisteredClasses()).isEqualTo(actualParsingRegistry.findAllRegisteredClasses());
    }
}