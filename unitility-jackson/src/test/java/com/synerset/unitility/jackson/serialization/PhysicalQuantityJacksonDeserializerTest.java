package com.synerset.unitility.jackson.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureUnits;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonDeserializerTest {

    @Test
    void deserialize_shouldDeserializeJsonToPhysicalQuantity() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingFactory));

        String tempInput1 = "{\"value\":20.0,\"unit\":\"°C\"}";
        String tempInput2 = "{\"value\":20,\"unit\":\"oC\"}";
        String tempInput3 = "{\"value\":20,\"unit\":\"c\"}";
        String tempInput4 = "{\"value\":-1.20E5}";
        String tempInput5 = "{\"value\":\"-1.20E5oC\",\"unit\":\"K\"}";
        String thermalCond4 = "{\"value\":20,\"unit\":\"BTU/(h·ft·°F)\"}";
        String thermalCond5 = "{\"value\":20,\"unit\":\"BTUp(h x ft x f) \"}";
        String bfFactor6 = "{\"value\":20,\"unit\":\"\"}";
        String bfFactor7 = "{\"value\":20,\"unit\":\"  Celcius   \"}";
        String bfFactor8 = "{\"value\":20}";
        String volFlowCFM = "{\"value\":20.0,\"unit\":\"CFM\"}";
        String volFlowFt3m = "{\"value\":20.0,\"unit\":\"ft3/min\"}";


        // When
        Temperature actualTemp1 = objectMapper.readValue(tempInput1, Temperature.class);
        Temperature actualTemp2 = objectMapper.readValue(tempInput2, Temperature.class);
        Temperature actualTemp3 = objectMapper.readValue(tempInput3, Temperature.class);
        Temperature actualTemp4 = objectMapper.readValue(tempInput4, Temperature.class);
        Temperature actualTemp5 = objectMapper.readValue(tempInput5, Temperature.class);
        ThermalConductivity actualThermCond4 = objectMapper.readValue(thermalCond4, ThermalConductivity.class);
        ThermalConductivity actualThermCond5 = objectMapper.readValue(thermalCond5, ThermalConductivity.class);
        BypassFactor actualBypassFactor6 = objectMapper.readValue(bfFactor6, BypassFactor.class);
        BypassFactor actualBypassFactor7 = objectMapper.readValue(bfFactor7, BypassFactor.class);
        BypassFactor actualBypassFactor8 = objectMapper.readValue(bfFactor8, BypassFactor.class);
        VolumetricFlow actualVolFlowCFM = objectMapper.readValue(volFlowCFM, VolumetricFlow.class);
        VolumetricFlow actualVolFlowFt3m = objectMapper.readValue(volFlowFt3m, VolumetricFlow.class);

        // Then
        Temperature expetedTemperature = Temperature.ofCelsius(20);
        ThermalConductivity expectedThermalCond = ThermalConductivity.ofBTUPerHourFeetFahrenheit(20);
        BypassFactor expectedBypassFactor = BypassFactor.of(20);
        VolumetricFlow expectedVolFlow = VolumetricFlow.ofCubicFeetPerMinute(20);

        assertThat(actualTemp1).isEqualTo(expetedTemperature);
        assertThat(actualTemp2).isEqualTo(expetedTemperature);
        assertThat(actualTemp3).isEqualTo(expetedTemperature);
        assertThat(actualTemp4).isEqualTo(Temperature.of(-120000.0, TemperatureUnits.getDefaultUnit()));
        assertThat(actualTemp5).isEqualTo(Temperature.of(-120000.0, TemperatureUnits.getDefaultUnit()));
        assertThat(actualThermCond4).isEqualTo(expectedThermalCond);
        assertThat(actualThermCond5).isEqualTo(expectedThermalCond);
        assertThat(actualBypassFactor6).isEqualTo(expectedBypassFactor);
        assertThat(actualBypassFactor7).isEqualTo(expectedBypassFactor);
        assertThat(actualBypassFactor8).isEqualTo(expectedBypassFactor);
        assertThat(actualVolFlowCFM).isEqualTo(expectedVolFlow);
        assertThat(actualVolFlowFt3m).isEqualTo(expectedVolFlow);
    }

    @Test
    void deserialize_shouldDeserializeJsonToPhysicalQuantityFromEngFormat() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingFactory));

        String tempInput1 = "{\"value\":\"20.123 [°C]\"}";
        String tempInput2 = "{\"value\":\"20.123 [°C]\", \"unit\":\"K\"}";

        // When
        Temperature actualTemp1 = objectMapper.readValue(tempInput1, Temperature.class);
        Temperature actualTemp2 = objectMapper.readValue(tempInput2, Temperature.class);

        Temperature expetedTemperature = Temperature.ofCelsius(20.123);
        assertThat(actualTemp1).isEqualTo(expetedTemperature);
        assertThat(actualTemp2).isEqualTo(expetedTemperature);
    }

    @Test
    void deserialize_shouldDeserializeJsonToPhysicalQuantityFromEngFormatWithoutBrackets() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingFactory));

        String tempInput1 = "{\"value\":\"20.123 °C\"}";
        String tempInput2 = "{\"value\":\"20.123 °C\", \"unit\":\"K\"}";

        // When
        Temperature actualTemp1 = objectMapper.readValue(tempInput1, Temperature.class);
        Temperature actualTemp2 = objectMapper.readValue(tempInput2, Temperature.class);

        Temperature expetedTemperature = Temperature.ofCelsius(20.123);
        assertThat(actualTemp1).isEqualTo(expetedTemperature);
        assertThat(actualTemp2).isEqualTo(expetedTemperature);
    }

    @Test
    void deserialize_shouldDeserializeJsonToLatitudeAndLongitude() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingFactory));

        String lat1 = "{\"value\":\"52°14'5.123\\\"N\"}";
        String lon1 = "{\"value\":\"21°4'3.986\\\"W\"}";
        String lat2 = "{\"value\":\" 52o 14min 5.123sec N\"}";
        String lon2 = "{\"value\":\"21deg 4' 3.986\\\"   w\"}";
        String lat3 = "{\"value\":\"52°14'5.123\\\"N\"}";
        String lon3 = "{\"value\":\"-21°4'3.986\\\"\"}";
        String lat4 = "{\"value\":\"52°14'N\"}";
        String lon4 = "{\"value\":\"21°4'W\"}";
        String lat5 = "{\"value\":\"52°N\"}";
        String lon5 = "{\"value\":\"21°4'W\"}";
        String lat6 = "{\"value\":\"52deg N\"}";
        String lon6 = "{\"value\":\"21o 4'W\"}";

        String lat7 = "{\"value\":-21.222}";
        String lon7 = "{\"value\":-21.222}";

        String lat8 = "{\"value\":\"-21.222 [deg]\"}";
        String lon8 = "{\"value\":\"-21.222 [o]\"}";

        // When
        Latitude actualLat1 = objectMapper.readValue(lat1, Latitude.class);
        Longitude actualLon1 = objectMapper.readValue(lon1, Longitude.class);

        Latitude actualLat2 = objectMapper.readValue(lat2, Latitude.class);
        Longitude actualLon2 = objectMapper.readValue(lon2, Longitude.class);

        Latitude actualLat3 = objectMapper.readValue(lat3, Latitude.class);
        Longitude actualLon3 = objectMapper.readValue(lon3, Longitude.class);

        Latitude actualLat4 = objectMapper.readValue(lat4, Latitude.class);
        Longitude actualLon4 = objectMapper.readValue(lon4, Longitude.class);

        Latitude actualLat5 = objectMapper.readValue(lat5, Latitude.class);
        Longitude actualLon5 = objectMapper.readValue(lon5, Longitude.class);

        Latitude actualLat6 = objectMapper.readValue(lat6, Latitude.class);
        Longitude actualLon6 = objectMapper.readValue(lon6, Longitude.class);

        Latitude actualLat7 = objectMapper.readValue(lat7, Latitude.class);
        Longitude actualLon7 = objectMapper.readValue(lon7, Longitude.class);

        Latitude actualLat8 = objectMapper.readValue(lat8, Latitude.class);
        Longitude actualLon8 = objectMapper.readValue(lon8, Longitude.class);

        // Then
        assertThat(actualLat1).isEqualTo(Latitude.ofDegrees(52.23475638888889));
        assertThat(actualLon1).isEqualTo(Longitude.ofDegrees(-21.06777388888889));
        assertThat(actualLat2).isEqualTo(Latitude.ofDegrees(52.23475638888889));
        assertThat(actualLon2).isEqualTo(Longitude.ofDegrees(-21.06777388888889));
        assertThat(actualLat3).isEqualTo(Latitude.ofDegrees(52.23475638888889));
        assertThat(actualLon3).isEqualTo(Longitude.ofDegrees(-21.06777388888889));
        assertThat(actualLat4).isEqualTo(Latitude.ofDegrees(52.233333333333334));
        assertThat(actualLon4).isEqualTo(Longitude.ofDegrees(-21.066666666666666));
        assertThat(actualLat5).isEqualTo(Latitude.ofDegrees(52.0));
        assertThat(actualLon5).isEqualTo(Longitude.ofDegrees(-21.066666666666666));
        assertThat(actualLat6).isEqualTo(Latitude.ofDegrees(52.0));
        assertThat(actualLon6).isEqualTo(Longitude.ofDegrees(-21.066666666666666));
        assertThat(actualLat7).isEqualTo(Latitude.ofDegrees(-21.222));
        assertThat(actualLon7).isEqualTo(Longitude.ofDegrees(-21.222));
        assertThat(actualLat8).isEqualTo(Latitude.ofDegrees(-21.222));
        assertThat(actualLon8).isEqualTo(Longitude.ofDegrees(-21.222));
    }

}