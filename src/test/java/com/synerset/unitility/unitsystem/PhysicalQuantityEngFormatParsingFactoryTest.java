package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityEngFormatParsingFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PhysicalQuantityEngFormatParsingFactoryTest {

    @Test
    void convertFromCanonicalString() {
        // Given
        Temperature temperature = Temperature.ofCelsius(20.1234);

        // When
        String tempAsString = PhysicalQuantityEngFormatParsingFactory.toEngFormatString(temperature);

        // Then
        assertThat(tempAsString).isEqualTo("20.1234[°C]");
    }

    @Test
    void convertToEngineeringFormat() {
        // Given
        String tempAsString = "20.1234[°C]";

        // When
        Temperature temperature = PhysicalQuantityEngFormatParsingFactory.createParsingFromEngFormat(Temperature.class, tempAsString);

        // Then
        assertThat(temperature).isEqualTo(Temperature.ofCelsius(20.1234));
    }

}