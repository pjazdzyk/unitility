package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.flows.MassFlow;
import com.synerset.unitility.unitsystem.flows.VolumetricFlow;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.Momentum;
import com.synerset.unitility.unitsystem.mechanical.Torque;
import com.synerset.unitility.unitsystem.thermodynamic.*;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"rawtypes", "unchecked"})
class PhysicalQuantityParsingFactoryTest {

    private static final double TEST_VALUE = 11.5;

    @ParameterizedTest
    @MethodSource("inlineSymbolsData")
    @DisplayName("Should create physical quantity when symbol as string is given")
    void createParsingFromSymbol_shouldResolveToQuantity_whenSymbolAsStringIsGiven(Class clazz,
                                                                                   String symbolAsString,
                                                                                   PhysicalQuantity expected) {
        // Given
        double value = 11.5;
        // When
        PhysicalQuantity actualUnit = PhysicalQuantityParsingFactory.createParsingFromSymbol(clazz, value, symbolAsString);
        // Then
        assertThat(actualUnit).isEqualTo(expected);
    }

    static Stream<Arguments> inlineSymbolsData() {
        return Stream.of(
                // Deliberately symbols are provided in malformed way, to check if resolver will handle this
                Arguments.of(Angle.class, "rad", Angle.ofRadians(TEST_VALUE)),
                Arguments.of(Area.class, "m² ", Area.ofSquareMeters(TEST_VALUE)),
                Arguments.of(Distance.class, "mi", Distance.ofMiles(TEST_VALUE)),
                Arguments.of(Mass.class, " G ", Mass.ofGrams(TEST_VALUE)),
                Arguments.of(Velocity.class, " m p s ", Velocity.ofMetersPerSecond(TEST_VALUE)),
                Arguments.of(Volume.class, "m3", Volume.ofCubicMeters(TEST_VALUE)),
                Arguments.of(MassFlow.class, "kg / s", MassFlow.ofKilogramsPerSecond(TEST_VALUE)),
                Arguments.of(VolumetricFlow.class, "galph", VolumetricFlow.ofGallonsPerHour(TEST_VALUE)),
                Arguments.of(HumidityRatio.class, "kg pkg", HumidityRatio.ofKilogramPerKilogram(TEST_VALUE)),
                Arguments.of(RelativeHumidity.class, null, RelativeHumidity.ofDecimal(TEST_VALUE)),
                Arguments.of(Force.class, "dyn", Force.ofDynes(TEST_VALUE)),
                Arguments.of(Momentum.class, "(g.cm)ps", Momentum.ofGramCentimetrePerSecond(TEST_VALUE)),
                Arguments.of(Momentum.class, "gcmps", Momentum.ofGramCentimetrePerSecond(TEST_VALUE)),
                Arguments.of(Momentum.class, "(g x cm)p s", Momentum.ofGramCentimetrePerSecond(TEST_VALUE)),
                Arguments.of(Momentum.class, "g x cm p s", Momentum.ofGramCentimetrePerSecond(TEST_VALUE)),
                Arguments.of(Torque.class, "N x m", Torque.ofNewtonMeters(TEST_VALUE)),
                Arguments.of(Density.class, "kg/m³", Density.ofKilogramPerCubicMeter(TEST_VALUE)),
                Arguments.of(DynamicViscosity.class, "kgpms", DynamicViscosity.ofKiloGramPerMeterSecond(TEST_VALUE)),
                Arguments.of(Energy.class, "kcal", Energy.ofKiloCalorie(TEST_VALUE)),
                Arguments.of(KinematicViscosity.class, "ft2ps", KinematicViscosity.ofSquareFootPerSecond(TEST_VALUE)),
                Arguments.of(Power.class, "hp", Power.ofHorsePower(TEST_VALUE)),
                Arguments.of(Pressure.class, "Bar", Pressure.ofBar(TEST_VALUE)),
                Arguments.of(SpecificEnthalpy.class, "BTU p lb", SpecificEnthalpy.ofBTUPerPound(TEST_VALUE)),
                Arguments.of(SpecificHeat.class, "kJp(kg.k)", SpecificHeat.ofKiloJoulePerKiloGramKelvin(TEST_VALUE)),
                Arguments.of(Temperature.class, "   f   ", Temperature.ofFahrenheit(TEST_VALUE)),
                Arguments.of(Temperature.class, "  deg f   ", Temperature.ofFahrenheit(TEST_VALUE)),
                Arguments.of(ThermalConductivity.class, "W/(m·K)", ThermalConductivity.ofWattsPerMeterKelvin(TEST_VALUE)),
                Arguments.of(ThermalDiffusivity.class, "ft2ps", ThermalDiffusivity.ofSquareFeetPerSecond(TEST_VALUE)),
                Arguments.of(BypassFactor.class, " any pointless symbol --", BypassFactor.of(TEST_VALUE))
        );
    }

}