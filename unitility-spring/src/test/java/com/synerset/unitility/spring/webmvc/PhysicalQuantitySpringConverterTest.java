package com.synerset.unitility.spring.webmvc;

import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivity;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivityUnit;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PhysicalQuantitySpringConverterTest {
    @Test
    void convert_shouldConvertStringToPhysicalQuantity() {
        // Given
        PhysicalQuantityParsingRegistry parsingFactory = PhysicalQuantityParsingRegistry.createNewDefaultRegistry();

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

}