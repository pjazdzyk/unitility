package com.synerset.unitility.spring;

import com.synerset.unitility.spring.serialization.PhysicalQuantityWebMvcConverter;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivity;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantitySpringConverterTest {
    @Test
    void convert_shouldConvertStringToPhysicalQuantity() {
        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        String input_1 = "20[BTU/(h·ft·°F)]";
        String input_2 = "20[BTU p (h x ft x oF)]";
        String input_3 = "20[btu p (h . ft . oF)]";
        String input_4 = "20[BTUphftF]";
        String input_5 = "    20     [BTU / (h . ft·   °F    )   ]   ";

        PhysicalQuantityWebMvcConverter<ThermalConductivityUnit, ThermalConductivity> converter =
                new PhysicalQuantityWebMvcConverter<>(ThermalConductivity.class, parsingFactory);

        // When
        ThermalConductivity actualThermCond_1 = converter.convert(input_1);
        ThermalConductivity actualThermCond_2 = converter.convert(input_2);
        ThermalConductivity actualThermCond_3 = converter.convert(input_3);
        ThermalConductivity actualThermCond_4 = converter.convert(input_4);
        ThermalConductivity actualThermCond_5 = converter.convert(input_5);

        // Then
        ThermalConductivity expectedThermCond = ThermalConductivity.ofBTUPerHourFeetFahrenheit(20);
        assertThat(actualThermCond_1).isEqualTo(expectedThermCond);
        assertThat(actualThermCond_2).isEqualTo(expectedThermCond);
        assertThat(actualThermCond_3).isEqualTo(expectedThermCond);
        assertThat(actualThermCond_4).isEqualTo(expectedThermCond);
        assertThat(actualThermCond_5).isEqualTo(expectedThermCond);
    }

    @ParameterizedTest
    @MethodSource("geoInlineData")
    @DisplayName("ParamConverter: should deserialize geo quantities")
    void getConverter_shouldProperlyDeserializeGeoQuantities(Class<?> quantityClass,
                                                             String sourceString,
                                                             PhysicalQuantity<?> expectedQuantity) {

        // Given
        PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        FormattingConversionService formatterRegistry = new WebConversionService(new DateTimeFormatters());

        PhysicalQuantityWebMvcConfiguration config = new PhysicalQuantityWebMvcConfiguration(parsingFactory);
        config.addFormatters(formatterRegistry);

        // When
        formatterRegistry.convert(sourceString, quantityClass);

        // Then
        assertThat(expectedQuantity).isEqualTo(formatterRegistry.convert(sourceString, quantityClass));

    }

    static Stream<Arguments> geoInlineData() {
        return Stream.of(
                Arguments.of(Latitude.class, "-15.111", Latitude.ofDegrees(-15.111)),
                Arguments.of(Longitude.class, "-15.111", Longitude.ofDegrees(-15.111)),
                Arguments.of(Latitude.class, "52°14'5.123\"N", Latitude.ofDegrees(52.23475638888889)),
                Arguments.of(Latitude.class, "52deg 14min 5.123sec N", Latitude.ofDegrees(52.23475638888889)),
                Arguments.of(Latitude.class, "52deg 14min 5.123sec S", Latitude.ofDegrees(-52.23475638888889)),
                Arguments.of(Latitude.class, "52°14'5.123\"", Latitude.ofDegrees(52.23475638888889)),
                Arguments.of(Latitude.class, "52°14'", Latitude.ofDegrees(52.233333333333334)),
                Arguments.of(Latitude.class, "52°", Latitude.ofDegrees(52.0)),
                Arguments.of(Latitude.class, "52", Latitude.ofDegrees(52.0)),
                Arguments.of(Latitude.class, "52 [deg]", Latitude.ofDegrees(52.0))
        );
    }

}