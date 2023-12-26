package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.basic.common.*;
import com.synerset.unitility.unitsystem.basic.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.basic.flows.MassFlow;
import com.synerset.unitility.unitsystem.basic.flows.VolumetricFlow;
import com.synerset.unitility.unitsystem.basic.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.basic.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.basic.mechanical.Force;
import com.synerset.unitility.unitsystem.basic.mechanical.Momentum;
import com.synerset.unitility.unitsystem.basic.mechanical.Torque;
import com.synerset.unitility.unitsystem.basic.thermodynamic.*;
import com.synerset.unitility.unitsystem.parsers.PhysicalQuantityParsingFactory;
import jakarta.ws.rs.ext.ParamConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
class PhysicalQuantityJakartaProviderTest {

    public static final PhysicalQuantityParsingFactory PARSING_REGISTRY = PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
    public static final PhysicalQuantityJakartaProvider CONVERTER_PROVIDER = new PhysicalQuantityJakartaProvider(PARSING_REGISTRY);
    public static final double TEST_VALUE = 15.1;

    @ParameterizedTest
    @MethodSource("converterInlineData")
    @DisplayName("ParamConverter: should get proper param converter for target class and convert to string and vice versa")
    void getConverter_shouldProperlyConvertTemperatureClassToString(Class<?> quantityClass,
                                                                    String sourceString,
                                                                    PhysicalQuantity<?> expectedQuantity) {


        // Given
        ParamConverter<PhysicalQuantity<Unit>> converter =
                (ParamConverter<PhysicalQuantity<Unit>>) CONVERTER_PROVIDER.getConverter(quantityClass, null, null);

        // When
        PhysicalQuantity<Unit> actualQuantity = converter.fromString(sourceString);
        String actualQuantityAsString = converter.toString(actualQuantity);

        // Then
        assertThat(actualQuantity).isEqualTo(expectedQuantity);
        assertThat(actualQuantityAsString).isEqualTo(sourceString);

    }

    static Stream<Arguments> converterInlineData() {
        return Stream.of(
                Arguments.of(Angle.class, "15.1 [rad]", Angle.ofRadians(TEST_VALUE)),
                Arguments.of(Area.class, "15.1 [m²]", Area.ofSquareMeters(TEST_VALUE)),
                Arguments.of(Distance.class, "15.1 [mi]", Distance.ofMiles(TEST_VALUE)),
                Arguments.of(Mass.class, "15.1 [g]", Mass.ofGrams(TEST_VALUE)),
                Arguments.of(Velocity.class, "15.1 [m/s]", Velocity.ofMetersPerSecond(TEST_VALUE)),
                Arguments.of(Volume.class, "15.1 [m³]", Volume.ofCubicMeters(TEST_VALUE)),
                Arguments.of(MassFlow.class, "15.1 [kg/s]", MassFlow.ofKilogramsPerSecond(TEST_VALUE)),
                Arguments.of(VolumetricFlow.class, "15.1 [gal/h]", VolumetricFlow.ofGallonsPerHour(TEST_VALUE)),
                Arguments.of(HumidityRatio.class, "15.1 [kg/kg]", HumidityRatio.ofKilogramPerKilogram(TEST_VALUE)),
                Arguments.of(RelativeHumidity.class, "15.1", RelativeHumidity.ofDecimal(TEST_VALUE)),
                Arguments.of(Force.class, "15.1 [dyn]", Force.ofDynes(TEST_VALUE)),
                Arguments.of(Momentum.class, "15.1 [g·cm/s]", Momentum.ofGramCentimetrePerSecond(TEST_VALUE)),
                Arguments.of(Torque.class, "15.1 [N·m]", Torque.ofNewtonMeters(TEST_VALUE)),
                Arguments.of(Density.class, "15.1 [kg/m³]", Density.ofKilogramPerCubicMeter(TEST_VALUE)),
                Arguments.of(DynamicViscosity.class, "15.1 [kg/(m·s)]", DynamicViscosity.ofKiloGramPerMeterSecond(TEST_VALUE)),
                Arguments.of(Energy.class, "15.1 [kcal]", Energy.ofKiloCalorie(TEST_VALUE)),
                Arguments.of(KinematicViscosity.class, "15.1 [ft²/s]", KinematicViscosity.ofSquareFootPerSecond(TEST_VALUE)),
                Arguments.of(Power.class, "15.1 [HP]", Power.ofHorsePower(TEST_VALUE)),
                Arguments.of(Pressure.class, "15.1 [bar]", Pressure.ofBar(TEST_VALUE)),
                Arguments.of(SpecificEnthalpy.class, "15.1 [BTU/lb]", SpecificEnthalpy.ofBTUPerPound(TEST_VALUE)),
                Arguments.of(SpecificHeat.class, "15.1 [kJ/(kg·K)]", SpecificHeat.ofKiloJoulePerKiloGramKelvin(TEST_VALUE)),
                Arguments.of(Temperature.class, "15.1 [°F]", Temperature.ofFahrenheit(TEST_VALUE)),
                Arguments.of(ThermalConductivity.class, "15.1 [W/(m·K)]", ThermalConductivity.ofWattsPerMeterKelvin(TEST_VALUE)),
                Arguments.of(ThermalDiffusivity.class, "15.1 [ft²/s]", ThermalDiffusivity.ofSquareFeetPerSecond(TEST_VALUE)),
                Arguments.of(BypassFactor.class, "15.1", BypassFactor.of(TEST_VALUE))
        );
    }

    @Test
    @DisplayName("ParamConverter: should handle converting malformed string source")
    void getConverter_shouldProperlyConvertTemperatureClassToString() {

        // Given
        String tempInput1 = "15.1[oC]";
        String tempInput2 = "  15.1  [  o   C   ]";
        String dynVisInput = "15,1 [ kg p (m x s) ]";
        String thermalDiffInput = "15,1 [ ft2 / s ]";

        ParamConverter<Temperature> tempConverter =
                CONVERTER_PROVIDER.getConverter(Temperature.class, null, null);

        ParamConverter<DynamicViscosity> dynamicViscosityParamConverter =
                CONVERTER_PROVIDER.getConverter(DynamicViscosity.class, null, null);

        ParamConverter<ThermalDiffusivity> thermalDiffParamConverter =
                CONVERTER_PROVIDER.getConverter(ThermalDiffusivity.class, null, null);

        // When
        Temperature actualTemperature1 = tempConverter.fromString(tempInput1);
        Temperature actualTemperature2 = tempConverter.fromString(tempInput2);
        DynamicViscosity actualDynamicViscosity = dynamicViscosityParamConverter.fromString(dynVisInput);
        ThermalDiffusivity actualThermalDiffusivity = thermalDiffParamConverter.fromString(thermalDiffInput);

        // Then
        assertThat(actualTemperature1).isEqualTo(Temperature.ofCelsius(15.1));
        assertThat(actualTemperature2).isEqualTo(Temperature.ofCelsius(15.1));
        assertThat(actualDynamicViscosity).isEqualTo(DynamicViscosity.ofKiloGramPerMeterSecond(15.1));
        assertThat(actualThermalDiffusivity).isEqualTo(ThermalDiffusivity.ofSquareFeetPerSecond(15.1));

    }

}