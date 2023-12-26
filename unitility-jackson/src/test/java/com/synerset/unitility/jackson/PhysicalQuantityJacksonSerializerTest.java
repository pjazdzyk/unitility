package com.synerset.unitility.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.unitsystem.basic.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.parsers.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonSerializerTest {

    @Test
    void serialize_shouldSerializePhysicalQuantityToJson() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
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