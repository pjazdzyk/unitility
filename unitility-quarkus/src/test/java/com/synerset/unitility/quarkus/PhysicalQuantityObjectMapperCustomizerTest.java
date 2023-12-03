package com.synerset.unitility.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityObjectMapperCustomizerTest {

    @Test
    @DisplayName("should configure object mapper and resolve input string to physical quantity")
    void customizeTest_shouldRegisterPhysicalQuantitiesInObjectMapper() throws JsonProcessingException {
        // Given
        ParsingRegistryProvider registryProvider = new ParsingRegistryProvider();
        PhysicalQuantityParsingRegistry parsingRegistry = registryProvider.createParsingFactory();
        PhysicalQuantityObjectMapperCustomizer customizer = new PhysicalQuantityObjectMapperCustomizer(parsingRegistry);
        ObjectMapper objectMapper = new ObjectMapper();
        String inputQuantity = "{\"value\":20.0,\"unit\":\"°C\"}";

        // When
        customizer.customize(objectMapper);
        Temperature resolvedQuantity = objectMapper.readValue(inputQuantity, Temperature.class);

        // Then
        assertThat(resolvedQuantity).isNotNull().isEqualTo(Temperature.ofCelsius(20));
    }

}