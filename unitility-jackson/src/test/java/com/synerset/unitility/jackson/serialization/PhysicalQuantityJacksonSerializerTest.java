package com.synerset.unitility.jackson.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModulePlainSIValue;
import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonSerializerTest {

    @Test
    void serialize_shouldSerializePhysicalQuantityToJson() throws JsonProcessingException {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);
        Latitude latitude = Latitude.ofDegrees(-30.11);
        Longitude longitude = Longitude.ofDegrees(-30.11);

        PhysicalQuantityParsingFactory DEFAULT_PARSING_FACTORY = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(DEFAULT_PARSING_FACTORY));

        // When
        String temperatureAsJson = objectMapper.writeValueAsString(temperature);
        String latitudeAsString = objectMapper.writeValueAsString(latitude);
        String longitudeAsString = objectMapper.writeValueAsString(longitude);

        // Then
        String expectedJsonTemp = "{\"value\":20.0,\"unit\":\"°C\"}";
        String expectedJsonLat = "{\"value\":-30.11,\"unit\":\"°\"}";
        String expectedJsonLon = "{\"value\":-30.11,\"unit\":\"°\"}";
        assertThat(temperatureAsJson).isEqualTo(expectedJsonTemp);
        assertThat(latitudeAsString).isEqualTo(expectedJsonLat);
        assertThat(longitudeAsString).isEqualTo(expectedJsonLon);
    }

    @Test
    void serialize_shouldSerializeGeoGraphicQuantitiesToJSON() throws JsonProcessingException {
        // Given
        GeoCoordinate start = GeoCoordinate.of(Latitude.ofDegrees(20), Longitude.ofDegrees(-20));
        GeoCoordinate target = GeoCoordinate.of(Latitude.ofDegrees(40), Longitude.ofDegrees(60));
        GeoDistance geoDistance = GeoDistance.ofKilometers(start, target);

        PhysicalQuantityParsingFactory DEFAULT_PARSING_FACTORY = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        // When
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(DEFAULT_PARSING_FACTORY));

        // Then
        String geoDistanceAsString = objectMapper.writeValueAsString(geoDistance);
        assertThat(geoDistanceAsString).isEqualTo(
                "{\"startCoordinate\":{\"latitude\":{\"value\":20.0,\"unit\":\"°\"}," +
                        "\"longitude\":{\"value\":-20.0,\"unit\":\"°\"},\"name\":null}," +
                        "\"targetCoordinate\":{\"latitude\":{\"value\":40.0,\"unit\":\"°\"}," +
                        "\"longitude\":{\"value\":60.0,\"unit\":\"°\"},\"name\":null}," +
                        "\"trueBearing\":{\"value\":53.485522918566915,\"unit\":\"°\"}," +
                        "\"distance\":{\"value\":7764.473273338867,\"unit\":\"km\"}}"
        );
    }

    @Test
    void serialize_shouldSerializePhysicalQuantityToJsonWithoutUnitsIfDirected() throws JsonProcessingException {
        // Given
        Temperature temperature = Temperature.ofCelsius(20);
        Latitude latitude = Latitude.ofRadians(-0.311);
        Longitude longitude = Longitude.ofRadians(-0.311);

        GeoCoordinate geoCoordinate = GeoCoordinate.of(latitude, longitude);

        PhysicalQuantityParsingFactory defaultParsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new PhysicalQuantityJacksonModulePlainSIValue(defaultParsingFactory));

        // When
        String temperatureAsJson = objectMapper.writeValueAsString(temperature);
        String latitudeAsString = objectMapper.writeValueAsString(latitude);
        String longitudeAsString = objectMapper.writeValueAsString(longitude);
        String geoCoordinateAsString = objectMapper.writeValueAsString(geoCoordinate);

        // Then
        String expectedJsonTemp = "293.15";
        String expectedJsonLat = "-17.8189874285686"; //deg
        String expectedJsonLon = "-17.8189874285686"; //deg
        String expectedJsonGeoDistance = "{\"latitude\":-17.8189874285686,\"longitude\":-17.8189874285686,\"name\":null}";
        assertThat(temperatureAsJson).isEqualTo(expectedJsonTemp);
        assertThat(latitudeAsString).isEqualTo(expectedJsonLat);
        assertThat(longitudeAsString).isEqualTo(expectedJsonLon);
        assertThat(geoCoordinateAsString).isEqualTo(expectedJsonGeoDistance);
    }

}