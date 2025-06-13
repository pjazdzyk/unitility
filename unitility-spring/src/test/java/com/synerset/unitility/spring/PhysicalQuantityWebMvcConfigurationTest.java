package com.synerset.unitility.spring;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.FormattingConversionService;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityWebMvcConfigurationTest {

    @Test
    @DisplayName("should register formatters and parse input string to physical quantity")
    void addFormatters_shouldRegisterFormattersAndResolveInputToQuantity() {
        // Given
        FormattingConversionService formatterRegistry = new FormattingConversionService();
        PhysicalQuantityParsingFactory parsingRegistry = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
        PhysicalQuantityWebMvcConfiguration webMvcConfiguration = new PhysicalQuantityWebMvcConfiguration(parsingRegistry);
        String inputQuantity = "20[oC]";
        String inputSingleValueQuantity = "20"; // Will be resolved to basic SI temperature unit: Kelvins

        // When
        webMvcConfiguration.addFormatters(formatterRegistry);
        Temperature actualTemperature = formatterRegistry.convert(inputQuantity, Temperature.class);
        Temperature actualTemperatureFromSingleValue = formatterRegistry.convert(inputSingleValueQuantity, Temperature.class);

        // Then
        assertThat(actualTemperature).isEqualTo(Temperature.ofCelsius(20));
        assertThat(actualTemperatureFromSingleValue).isEqualTo(Temperature.ofKelvins(20));

    }

}