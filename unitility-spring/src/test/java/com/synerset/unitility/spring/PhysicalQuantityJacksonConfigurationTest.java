package com.synerset.unitility.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonConfigurationTest {

    @Test
    @DisplayName("should register jackson module and correctly resolve input string to physical quantity")
    void createPhysicalQuantityJacksonModule() throws JsonProcessingException {
        // Given
        PhysicalQuantityJacksonConfiguration jacksonConfiguration = new PhysicalQuantityJacksonConfiguration();
        PhysicalQuantityParsingRegistry parsingRegistry = jacksonConfiguration.createPhysicalQuantityParsingFactory();
        Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer = jacksonConfiguration.createPhysicalQuantityJacksonModule(parsingRegistry);
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        String inputQuantity = "{\"value\":20.0,\"unit\":\"°C\"}";

        // When
        jacksonCustomizer.customize(objectMapperBuilder);
        ObjectMapper objectMapper = objectMapperBuilder.build();
        Temperature resolvedQuantity = objectMapper.readValue(inputQuantity, Temperature.class);

        // Then
        assertThat(resolvedQuantity).isNotNull().isEqualTo(Temperature.ofCelsius(20));
    }

}