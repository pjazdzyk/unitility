package com.synerset.unitility.jackson.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModulePlainSIValue;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.AngularVelocity;
import com.synerset.unitility.unitsystem.common.Curvature;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.GenericDimensionless;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.geographic.Bearing;
import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.hydraulic.RotationSpeedToFlowRateRatio;
import com.synerset.unitility.unitsystem.hydraulic.SDR;
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
        String volFlowm3Min = "{\"value\":20.0,\"unit\":\"m3/min\"}";
        String humRatio = "{\"value\":2.0,\"unit\":\"kg.wv/kg.da\"}";
        String humRatio2 = "{\"value\":2.0,\"unit\":\"kgwv/kgda\"}";
        String bearing = "{\"value\":270.0}";
        String distance = "1.0";
        String curvatureInput1 = "{\"value\":0.1,\"unit\":\"°/ft\"}";
        String curvatureInput2 = "{\"value\":0.1,\"unit\":\"deg  /ft\"}"; ;
        String curvatureInput3 = "{\"value\":0.1,\"unit\":\"degpft\"}";
        String angularVelInput1 = "{\"value\":0.1,\"unit\":\"deg p s\"}";
        String angularVelInput2 = "{\"value\":0.1,\"unit\":\"rps\"}";
        String rotSpeedToFlowRateRatio1 = "{\"value\":0.1,\"unit\":\"rad·s⁻¹/m³·s-1\"}";
        String rotSpeedToFlowRateRatio2 = "{\"value\":0.1,\"unit\":\"(    rad x 1ps) / (m3 · s-1) \"}";
        String rotSpeedToFlowRateRatio3 = "{\"value\":0.1,\"unit\":\"radx1pspm3xs-1\"}";
        String expectedGenericDimensionless1 = "{\"value\":20,\"unit\":\"\"}";
        String expectedGenericDimensionless2 = "{\"value\":20}";
        String expectedSDR = "{\"value\":27.6}";

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
        VolumetricFlow actualVolFlowM3m = objectMapper.readValue(volFlowm3Min, VolumetricFlow.class);
        HumidityRatio actualHumidityRatio = objectMapper.readValue(humRatio, HumidityRatio.class);
        HumidityRatio actualHumidityRatio2 = objectMapper.readValue(humRatio2, HumidityRatio.class);
        Bearing actualBearing = objectMapper.readValue(bearing, Bearing.class);
        Distance actualDistance = objectMapper.readValue(distance, Distance.class);
        Curvature actualCurvature1 = objectMapper.readValue(curvatureInput1, Curvature.class);
        Curvature actualCurvature2 = objectMapper.readValue(curvatureInput2, Curvature.class);
        Curvature actualCurvature3 = objectMapper.readValue(curvatureInput3, Curvature.class);
        AngularVelocity actualAngularVel1 = objectMapper.readValue(angularVelInput1, AngularVelocity.class);
        AngularVelocity actualAngularVel2 = objectMapper.readValue(angularVelInput2, AngularVelocity.class);
        RotationSpeedToFlowRateRatio actualRotSpeedToFlowRatio1 = objectMapper.readValue(rotSpeedToFlowRateRatio1, RotationSpeedToFlowRateRatio.class);
        RotationSpeedToFlowRateRatio actualRotSpeedToFlowRatio2 = objectMapper.readValue(rotSpeedToFlowRateRatio2, RotationSpeedToFlowRateRatio.class);
        RotationSpeedToFlowRateRatio actualRotSpeedToFlowRatio3 = objectMapper.readValue(rotSpeedToFlowRateRatio3, RotationSpeedToFlowRateRatio.class);
        GenericDimensionless actualGenericDimensionless1 = objectMapper.readValue(expectedGenericDimensionless1, GenericDimensionless.class);
        GenericDimensionless actualGenericDimensionless2 = objectMapper.readValue(expectedGenericDimensionless2, GenericDimensionless.class);
        SDR actualSDR = objectMapper.readValue(expectedSDR, SDR.class);

        // Then
        Temperature expetedTemperature = Temperature.ofCelsius(20);
        ThermalConductivity expectedThermalCond = ThermalConductivity.ofBTUPerHourFeetFahrenheit(20);
        BypassFactor expectedBypassFactor = BypassFactor.of(20);
        VolumetricFlow expectedVolFlow = VolumetricFlow.ofCubicFeetPerMinute(20);
        VolumetricFlow expectedVolFlowM3min = VolumetricFlow.ofCubicMetersPerMinute(20);
        HumidityRatio expectedHumRatio = HumidityRatio.ofKilogramPerKilogram(2.0);
        Distance expectedDistance = Distance.ofMeters(1.0);
        Curvature expectedCurvature = Curvature.ofDegreesPerFoot(0.1);
        AngularVelocity expectedAngularVel1 = AngularVelocity.ofDegreesPerSecond(0.1);
        AngularVelocity expectedAngularVel2 = AngularVelocity.ofRevolutionsPerSecond(0.1);
        RotationSpeedToFlowRateRatio expectedRotSpeedToFlowRatio = objectMapper.readValue(rotSpeedToFlowRateRatio1, RotationSpeedToFlowRateRatio.class);
        SDR expectedSdr = SDR.of(27.6);

        assertThat(actualTemp1).isEqualTo(expetedTemperature);
        assertThat(actualTemp2).isEqualTo(expetedTemperature);
        assertThat(actualTemp3).isEqualTo(expetedTemperature);
        assertThat(actualTemp4).isEqualTo(Temperature.of(-120000.0, TemperatureUnits.KELVIN));
        assertThat(actualTemp5).isEqualTo(Temperature.of(-120000.0, TemperatureUnits.CELSIUS));
        assertThat(actualThermCond4).isEqualTo(expectedThermalCond);
        assertThat(actualThermCond5).isEqualTo(expectedThermalCond);
        assertThat(actualBypassFactor6).isEqualTo(expectedBypassFactor);
        assertThat(actualBypassFactor7).isEqualTo(expectedBypassFactor);
        assertThat(actualBypassFactor8).isEqualTo(expectedBypassFactor);
        assertThat(actualVolFlowCFM).isEqualTo(expectedVolFlow);
        assertThat(actualVolFlowFt3m).isEqualTo(expectedVolFlow);
        assertThat(actualVolFlowM3m).isEqualTo(expectedVolFlowM3min);
        assertThat(actualHumidityRatio).isEqualTo(expectedHumRatio);
        assertThat(actualHumidityRatio2).isEqualTo(expectedHumRatio);
        assertThat(actualBearing).isEqualTo(Bearing.of(270));
        assertThat(actualDistance).isEqualTo(expectedDistance);
        assertThat(actualCurvature1).isEqualTo(expectedCurvature);
        assertThat(actualCurvature2).isEqualTo(expectedCurvature);
        assertThat(actualCurvature3).isEqualTo(expectedCurvature);
        assertThat(actualAngularVel1).isEqualTo(expectedAngularVel1);
        assertThat(actualAngularVel2).isEqualTo(expectedAngularVel2);
        assertThat(actualRotSpeedToFlowRatio1).isEqualTo(expectedRotSpeedToFlowRatio);
        assertThat(actualRotSpeedToFlowRatio2).isEqualTo(expectedRotSpeedToFlowRatio);
        assertThat(actualRotSpeedToFlowRatio3).isEqualTo(expectedRotSpeedToFlowRatio);
        assertThat(actualGenericDimensionless1).isEqualTo(GenericDimensionless.of(20));
        assertThat(actualGenericDimensionless2).isEqualTo(GenericDimensionless.of(20));
        assertThat(actualSDR).isEqualTo(expectedSdr);
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

    @Test
    void deserialize_shouldDeserializeFormPlainSiValue() throws JsonProcessingException {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModulePlainSIValue(parsingFactory));

        // When
        Temperature temperature = objectMapper.readValue("293.15", Temperature.class);
        Angle angle = objectMapper.readValue("-0.311", Angle.class);
        Latitude latitude = objectMapper.readValue("-21.222", Latitude.class);
        GeoCoordinate geoCoordinate = objectMapper.readValue("{\"latitude\":-17.8189874285686,\"longitude\":-17.8189874285686,\"name\":null}", GeoCoordinate.class);

        // Then
        assertThat(temperature).isEqualTo(Temperature.ofCelsius(20));
        assertThat(angle).isEqualTo(Angle.ofDegrees(-17.8189874285686));
        assertThat(latitude).isEqualTo(Latitude.ofDegrees(-21.222));
        assertThat(geoCoordinate).isEqualTo(GeoCoordinate.of(Latitude.ofDegrees(-17.8189874285686), Longitude.ofDegrees(-17.8189874285686)));

    }

}