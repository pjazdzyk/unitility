package com.synerset.unitility.spring;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonConfigurationTest {

    @Test
    @DisplayName("should register jackson module and correctly resolve input string to physical quantity")
    void createPhysicalQuantityJacksonModule() {
        // Given
        PhysicalQuantityJacksonConfiguration jacksonConfiguration = new PhysicalQuantityJacksonConfiguration();
        PhysicalQuantityParsingFactory parsingRegistry = jacksonConfiguration.defaultParsingFactory();
        JsonMapperBuilderCustomizer jacksonCustomizer
                = jacksonConfiguration.createPhysicalQuantityJacksonModule(parsingRegistry);
        JsonMapper.Builder builder = JsonMapper.builder();
        String inputQuantity = "{\"value\":20.0,\"unit\":\"°C\"}";

        // When
        jacksonCustomizer.customize(builder);
        JsonMapper objectMapper = builder.build();
        Temperature resolvedQuantity = objectMapper.readValue(inputQuantity, Temperature.class);

        // Then
        assertThat(resolvedQuantity).isNotNull().isEqualTo(Temperature.ofCelsius(20));
    }

}