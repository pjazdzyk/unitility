package com.synerset.unitility.spring;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
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
        PhysicalQuantityParsingRegistry parsingRegistry = PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
        PhysicalQuantityWebMvcConfiguration webMvcConfiguration = new PhysicalQuantityWebMvcConfiguration(parsingRegistry);
        String inputQuantity = "20[oC]";

        // When
        webMvcConfiguration.addFormatters(formatterRegistry);
        Temperature actualTemperature = formatterRegistry.convert(inputQuantity, Temperature.class);

        // Then
        assertThat(actualTemperature).isEqualTo(Temperature.ofCelsius(20));

    }
}