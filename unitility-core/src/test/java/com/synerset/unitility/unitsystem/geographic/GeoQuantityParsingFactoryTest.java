package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.BiFunction;

import static com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory.GEO_PARSING_FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeoQuantityParsingFactoryTest {

    @Test
    @DisplayName("should parse from DMS format to latitude or longitude")
    void parseFromDMSFormat_shouldParseFromDMSFormatToLatitudeOrLongitude() {
        // Given
        String lat1 = "52°14'5.123\"N";
        String lon1 = "21°4'3.986\"W";
        String lat2 = " 52o 14min 5.123sec N";
        String lon2 = "21deg 4' 3.986\"   w";
        String lat3 = "52°14'5.123\"N";
        String lon3 = "-21°4'3.986\"";
        String lat4 = "52°14'N";
        String lon4 = "21°4'W";
        String lat5 = "52°N";
        String lon5 = "21°4'W";
        String lat6 = "52deg N";
        String lon6 = "21o 4'W";

        // When
        Latitude actualLat1 = GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat1);
        Longitude actualLon1 = GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon1);
        Latitude actualLat2 = GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat2);
        Longitude actualLon2 = GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon2);
        Latitude actualLat3 = GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat3);
        Longitude actualLon3 = GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon3);
        Latitude actualLat4 = GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat4);
        Longitude actualLon4 = GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon4);
        Latitude actualLat5 = GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat5);
        Longitude actualLon5 = GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon5);
        Latitude actualLat6 = GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat6);
        Longitude actualLon6 = GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon6);

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
    }

    @Test
    @DisplayName("should be immutable map, clear should not be possible")
    void getClassRegistry_shouldBeImmutableMap() {
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> classRegistry = GEO_PARSING_FACTORY.getClassRegistry();
        assertThrows(UnsupportedOperationException.class, classRegistry::clear);
    }

    @Test
    @DisplayName("should fail on invalid or malformed DMS format")
    void parseFromDMSFormat_shouldFailOnInvalidOrMalformedDMSFormat() {
        // Given
        String lat1 = "52°14'5.123\"E";
        String lon1 = "21°4'3.986\"N";
        String lat2 = "52";
        String lon2 = "21°4'3.986\"N";

        // When // Then
        assertThrows(UnitSystemParseException.class, () -> GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat1));
        assertThrows(UnitSystemParseException.class, () -> GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon1));
        assertThrows(UnitSystemParseException.class, () -> GEO_PARSING_FACTORY.parseFromDMSFormat(Latitude.class, lat2));
        assertThrows(UnitSystemParseException.class, () -> GEO_PARSING_FACTORY.parseFromDMSFormat(Longitude.class, lon2));
    }

}