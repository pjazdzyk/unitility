package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.flows.MassFlow;
import com.synerset.unitility.unitsystem.flows.MassFlowUnits;
import com.synerset.unitility.unitsystem.flows.VolumetricFlow;
import com.synerset.unitility.unitsystem.flows.VolumetricFlowUnits;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.HumidityRatioUnits;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidityUnits;
import com.synerset.unitility.unitsystem.mechanical.*;
import com.synerset.unitility.unitsystem.thermodynamic.*;
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

    @ParameterizedTest
    @MethodSource("inlineUnitsData")
    @DisplayName("Should create physical quantity when symbol as string is given")
    void createParsingFromUnit_shouldResolveToQuantity_whenUnitAsStringIsGiven(Class clazz,
                                                                               String unitAsString,
                                                                               PhysicalQuantity expected) {
        // Given
        double value = 11.5;
        // When
        PhysicalQuantity actualUnit = PhysicalQuantityParsingFactory.createParsingFromUnit(clazz, value, unitAsString);
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

    static Stream<Arguments> inlineUnitsData() {
        return Stream.of(
                Arguments.of(Angle.class, AngleUnits.RADIANS.toString(), Angle.ofRadians(TEST_VALUE)),
                Arguments.of(Area.class, AreaUnits.SQUARE_METER.toString(), Area.ofSquareMeters(TEST_VALUE)),
                Arguments.of(Distance.class, DistanceUnits.MILE.toString(), Distance.ofMiles(TEST_VALUE)),
                Arguments.of(Mass.class, MassUnits.GRAM.toString(), Mass.ofGrams(TEST_VALUE)),
                Arguments.of(Velocity.class, VelocityUnits.METER_PER_SECOND.toString(), Velocity.ofMetersPerSecond(TEST_VALUE)),
                Arguments.of(Volume.class, VolumeUnits.CUBIC_METER.toString(), Volume.ofCubicMeters(TEST_VALUE)),
                Arguments.of(MassFlow.class, MassFlowUnits.KILOGRAM_PER_SECOND.toString(), MassFlow.ofKilogramsPerSecond(TEST_VALUE)),
                Arguments.of(VolumetricFlow.class, VolumetricFlowUnits.GALLONS_PER_HOUR.toString(), VolumetricFlow.ofGallonsPerHour(TEST_VALUE)),
                Arguments.of(HumidityRatio.class, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM.toString(), HumidityRatio.ofKilogramPerKilogram(TEST_VALUE)),
                Arguments.of(RelativeHumidity.class, RelativeHumidityUnits.DECIMAL.toString(), RelativeHumidity.ofDecimal(TEST_VALUE)),
                Arguments.of(Force.class, ForceUnits.DYNE.toString(), Force.ofDynes(TEST_VALUE)),
                Arguments.of(Momentum.class, MomentumUnits.GRAM_CENTIMETRE_PER_SECOND.toString(), Momentum.ofGramCentimetrePerSecond(TEST_VALUE)),
                Arguments.of(Torque.class, TorqueUnits.NEWTON_METER.toString(), Torque.ofNewtonMeters(TEST_VALUE)),
                Arguments.of(Density.class, DensityUnits.KILOGRAM_PER_CUBIC_METER.toString(), Density.ofKilogramPerCubicMeter(TEST_VALUE)),
                Arguments.of(DynamicViscosity.class, DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND.toString(), DynamicViscosity.ofKiloGramPerMeterSecond(TEST_VALUE)),
                Arguments.of(Energy.class, EnergyUnits.KILOCALORIE.toString(), Energy.ofKiloCalorie(TEST_VALUE)),
                Arguments.of(KinematicViscosity.class, KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND.toString(), KinematicViscosity.ofSquareFootPerSecond(TEST_VALUE)),
                Arguments.of(Power.class, PowerUnits.HORSE_POWER.toString(), Power.ofHorsePower(TEST_VALUE)),
                Arguments.of(Pressure.class, PressureUnits.BAR.toString(), Pressure.ofBar(TEST_VALUE)),
                Arguments.of(SpecificEnthalpy.class, SpecificEnthalpyUnits.BTU_PER_POUND.toString(), SpecificEnthalpy.ofBTUPerPound(TEST_VALUE)),
                Arguments.of(SpecificHeat.class, SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN.toString(), SpecificHeat.ofKiloJoulePerKiloGramKelvin(TEST_VALUE)),
                Arguments.of(Temperature.class, TemperatureUnits.FAHRENHEIT.toString(), Temperature.ofFahrenheit(TEST_VALUE)),
                Arguments.of(ThermalConductivity.class, ThermalConductivityUnits.WATTS_PER_METER_KELVIN.toString(), ThermalConductivity.ofWattsPerMeterKelvin(TEST_VALUE)),
                Arguments.of(ThermalDiffusivity.class, ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND.toString(), ThermalDiffusivity.ofSquareFeetPerSecond(TEST_VALUE)),
                Arguments.of(ThermalDiffusivity.class, "square feet per second", ThermalDiffusivity.ofSquareFeetPerSecond(TEST_VALUE)),
                Arguments.of(ThermalDiffusivity.class, "   square feet per second   ", ThermalDiffusivity.ofSquareFeetPerSecond(TEST_VALUE)),
                Arguments.of(BypassFactor.class, " any pointless unit --", BypassFactor.of(TEST_VALUE))
        );
    }

}