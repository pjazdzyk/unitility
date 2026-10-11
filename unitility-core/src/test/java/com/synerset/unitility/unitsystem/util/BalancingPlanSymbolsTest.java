package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.Ratio;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.hydraulic.FlowCoefficient;
import com.synerset.unitility.unitsystem.thermodynamic.Power;
import com.synerset.unitility.unitsystem.thermodynamic.Pressure;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureDifference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * The unit symbols the network balancing and sizing plan writes in its examples and asks its parsers to accept.
 * Every row states the value of one such unit in the base unit of its quantity, derived here from the defining
 * numbers and not read from {@code UnitDefinitions}, so a wrong factor and a wrong symbol both fail.
 */
class BalancingPlanSymbolsTest {

    private static final double INCH = 0.0254;
    private static final double G_N = 9.80665;
    private static final double WATER_10C = 999.70247;
    private static final double US_GALLON = 231 * INCH * INCH * INCH;

    private static Arguments row(Class<? extends PhysicalQuantity<?>> quantity, String symbol, double baseValue) {
        return Arguments.of(quantity, symbol, baseValue);
    }

    static Stream<Arguments> planSymbols() {
        return Stream.of(
                row(Pressure.class, "Pa", 1.0),
                row(Pressure.class, "kPa", 1.0E3),
                row(Pressure.class, "mbar", 1.0E2),
                row(Pressure.class, "bar", 1.0E5),
                row(Pressure.class, "psi", 4.4482216152605 / (INCH * INCH)),
                // The bare inch of water is the conventional one, 25.4 mm of the 9.80665 Pa conventional millimetre.
                // Only the explicit inH2O_10 is the 10 °C inch, and the bare mH2O stays the 10 °C metre.
                row(Pressure.class, "inH2O", INCH * 1000.0 * G_N),
                row(Pressure.class, "inH₂O", INCH * 1000.0 * G_N),
                row(Pressure.class, "in H2O", INCH * 1000.0 * G_N),
                row(Pressure.class, "inWC", INCH * 1000.0 * G_N),
                row(Pressure.class, "inWG", INCH * 1000.0 * G_N),
                row(Pressure.class, "inH2O_10", INCH * WATER_10C * G_N),
                row(Pressure.class, "inH₂O_10", INCH * WATER_10C * G_N),
                row(Pressure.class, "mH2O", WATER_10C * G_N),
                row(VolumetricFlow.class, "l/h", 1.0E-3 / 3600),
                row(VolumetricFlow.class, "m3/h", 1.0 / 3600),
                row(VolumetricFlow.class, "l/s", 1.0E-3),
                row(VolumetricFlow.class, "L/s", 1.0E-3),
                row(VolumetricFlow.class, "gpm", US_GALLON / 60),
                row(VolumetricFlow.class, "GPM", US_GALLON / 60),
                row(VolumetricFlow.class, "US gpm", US_GALLON / 60),
                row(VolumetricFlow.class, "gal/min_US", US_GALLON / 60),
                // A bare gal/min stays the UK gallon, as before.
                row(VolumetricFlow.class, "gal/min", 0.00454609 / 60),
                row(FlowCoefficient.class, "Kv", 1.0),
                row(FlowCoefficient.class, "Cv", US_GALLON / 60 * 3600 * Math.sqrt(1.0E5 / (4.4482216152605 / (INCH * INCH)))),
                // Av in m²: q = Av·√(Δp/ρ) at 1 bar and 1000 kg/m³ gives q = Av · 10 m³/s = Av · 36 000 m³/h.
                row(FlowCoefficient.class, "Av", 36000.0),
                row(FlowCoefficient.class, "m²", 36000.0),
                row(FlowCoefficient.class, "m2", 36000.0),
                row(Temperature.class, "K", 1.0),
                row(Temperature.class, "kelvin", 1.0),
                row(Temperature.class, "°C", 274.15),
                row(Temperature.class, "oC", 274.15),
                row(TemperatureDifference.class, "K", 1.0),
                row(TemperatureDifference.class, "kelvin", 1.0),
                row(TemperatureDifference.class, "°C", 1.0),
                row(Distance.class, "mm", 1.0E-3),
                row(Distance.class, "cm", 1.0E-2),
                row(Distance.class, "in", INCH),
                row(Angle.class, "rad", 1.0),
                row(Angle.class, "deg", Math.PI / 180),
                row(Angle.class, "°", Math.PI / 180),
                row(Ratio.class, "%", 0.01),
                row(Power.class, "W", 1.0),
                row(Power.class, "kW", 1.0E3)
        );
    }

    @ParameterizedTest(name = "{0}: {1}")
    @MethodSource("planSymbols")
    @DisplayName("every unit symbol the balancing plan writes parses, to its derived value")
    void everyPlanSymbolParsesToItsDerivedValue(Class<? extends PhysicalQuantity<?>> quantity, String symbol, double expectedBase) {
        // Given
        PhysicalQuantityParsingFactory factory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        // When
        PhysicalQuantity<?> parsed = factory.parseValueAndSymbol((Class) quantity, 1.0, symbol);

        // Then
        assertThat(parsed.getBaseValue()).isCloseTo(expectedBase, within(Math.abs(expectedBase) * 1E-14));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("planStrings")
    @DisplayName("a quantity written the way the plan's examples write it parses as a whole string")
    void planStringsParse(String text, Class<? extends PhysicalQuantity<?>> quantity, double expectedBase) {
        // Given
        PhysicalQuantityParsingFactory factory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

        // When
        PhysicalQuantity<?> parsed = factory.parse((Class) quantity, text);

        // Then
        assertThat(parsed.getBaseValue()).isCloseTo(expectedBase, within(Math.abs(expectedBase) * 1E-14));
    }

    static Stream<Arguments> planStrings() {
        return Stream.of(
                Arguments.of("12 inH2O", Pressure.class, 12 * INCH * 1000.0 * G_N),
                Arguments.of("12 inH2O_10", Pressure.class, 12 * INCH * WATER_10C * G_N),
                Arguments.of("3 gpm", VolumetricFlow.class, 3 * US_GALLON / 60),
                Arguments.of("62l/h", VolumetricFlow.class, 62 * 1.0E-3 / 3600),
                Arguments.of("46.2 kPa", Pressure.class, 46200.0),
                Arguments.of("2K", TemperatureDifference.class, 2.0),
                Arguments.of("2 kelvin", TemperatureDifference.class, 2.0),
                Arguments.of("0.0001 Av", FlowCoefficient.class, 3.6),
                Arguments.of("15mm", Distance.class, 0.015)
        );
    }
}
