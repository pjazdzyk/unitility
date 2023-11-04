package com.synerset.jackson.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonDeserializerTest {

    @Test
    void deserialize_shouldDeserializeJsonToPhysicalQuantity() throws JsonProcessingException {
        // Given
        String tempInput1 = "{\"value\":20.0,\"unit\":\"°C\"}";
        String tempInput2 = "{\"value\":20,\"unit\":\"oC\"}";
        String tempInput3 = "{\"value\":20,\"unit\":\"c\"}";
        String thermalCond4 = "{\"value\":20,\"unit\":\"BTU/(h·ft·°F)\"}";
        String thermalCond5 = "{\"value\":20,\"unit\":\"BTUp(h x ft x f) \"}";
        String bfFactor6 = "{\"value\":20,\"unit\":\"\"}";
        String bfFactor7 = "{\"value\":20,\"unit\":\"  Celcius   \"}";
        String bfFactor8 = "{\"value\":20}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule());

        // When
        Temperature actualTemp1 = objectMapper.readValue(tempInput1, Temperature.class);
        Temperature actualTemp2 = objectMapper.readValue(tempInput2, Temperature.class);
        Temperature actualTemp3 = objectMapper.readValue(tempInput3, Temperature.class);
        ThermalConductivity actualThermCond4 = objectMapper.readValue(thermalCond4, ThermalConductivity.class);
        ThermalConductivity actualThermCond5 = objectMapper.readValue(thermalCond5, ThermalConductivity.class);
        BypassFactor actualBypassFactor6 = objectMapper.readValue(bfFactor6, BypassFactor.class);
        BypassFactor actualBypassFactor7 = objectMapper.readValue(bfFactor7, BypassFactor.class);
        BypassFactor actualBypassFactor8 = objectMapper.readValue(bfFactor8, BypassFactor.class);

        // Then
        Temperature expetedTemperature = Temperature.ofCelsius(20);
        ThermalConductivity expectedThermalCond = ThermalConductivity.ofBTUPerHourFeetFahrenheit(20);
        BypassFactor expectedBypassFactor = BypassFactor.of(20);
        assertThat(actualTemp1).isEqualTo(expetedTemperature);
        assertThat(actualTemp2).isEqualTo(expetedTemperature);
        assertThat(actualTemp3).isEqualTo(expetedTemperature);
        assertThat(actualThermCond4).isEqualTo(expectedThermalCond);
        assertThat(actualThermCond5).isEqualTo(expectedThermalCond);
        assertThat(actualBypassFactor6).isEqualTo(expectedBypassFactor);
        assertThat(actualBypassFactor7).isEqualTo(expectedBypassFactor);
        assertThat(actualBypassFactor8).isEqualTo(expectedBypassFactor);
    }

}