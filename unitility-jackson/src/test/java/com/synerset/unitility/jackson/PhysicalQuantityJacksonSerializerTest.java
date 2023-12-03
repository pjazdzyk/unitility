package com.synerset.unitility.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonSerializerTest {

    @Test
    void serialize_shouldSerializePhysicalQuantityToJson() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingRegistry parsingFactory = PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
        Temperature temperature = Temperature.ofCelsius(20);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingFactory));

        // When
        String temperatureAsJson = objectMapper.writeValueAsString(temperature);

        // Then
        String expectedJsonTemp = "{\"value\":20.0,\"unit\":\"°C\"}";
        assertThat(temperatureAsJson).isEqualTo(expectedJsonTemp);
    }

}