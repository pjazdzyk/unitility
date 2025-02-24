package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.flow.MassFlow;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.hydraulic.LinearResistance;
import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.Momentum;
import com.synerset.unitility.unitsystem.mechanical.Torque;
import com.synerset.unitility.unitsystem.thermodynamic.*;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
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

    public static final PhysicalQuantityParsingFactory PARSING_REGISTRY = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
    public static final PhysicalQuantityJakartaProvider CONVERTER_PROVIDER = new PhysicalQuantityJakartaProvider(PARSING_REGISTRY);
    public static final double TEST_VALUE = 15.1;

    @ParameterizedTest
    @MethodSource("converterInlineData")
    @DisplayName("ParamConverter: should get proper param converter for target class and convert to string and vice versa")
    void getConverter_shouldProperlyDeserializePhysicalQuantity(Class<?> quantityClass,
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
                Arguments.of(RelativeHumidity.class, "15.1 [%]", RelativeHumidity.ofPercentage(TEST_VALUE)),
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
                Arguments.of(BypassFactor.class, "15.1", BypassFactor.of(TEST_VALUE)),
                Arguments.of(LinearResistance.class, "15.1 [inH₂O/100ft]", LinearResistance.ofInchOfWaterPer100Feet(TEST_VALUE))
        );
    }

    @ParameterizedTest
    @MethodSource("geoInlineData")
    @DisplayName("ParamConverter: should deserialize geo quantities")
    void getConverter_shouldProperlyDeserializeGeoQuantities(Class<?> quantityClass,
                                                             String sourceString,
                                                             PhysicalQuantity<?> expectedQuantity) {

        // Given
        ParamConverter<PhysicalQuantity<Unit>> converter =
                (ParamConverter<PhysicalQuantity<Unit>>) CONVERTER_PROVIDER.getConverter(quantityClass, null, null);

        // When
        PhysicalQuantity<Unit> actualQuantity = converter.fromString(sourceString);

        // Then
        assertThat(actualQuantity).isEqualTo(expectedQuantity);

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

    @Test
    @DisplayName("ParamConverter: should handle converting malformed string source")
    void getConverter_shouldProperlyConvertTemperatureClassToString() {

        // Given
        String tempInput1 = "15.1[oC]";
        String tempInput2 = "  15.1  [  o   C   ]";
        String dynVisInput = "15,1 [ kg p (m x s) ]";
        String thermalDiffInput = "15,1 [ ft2 / s ]";
        String linearResistance = "15,1 [ in h2o p 100 ft ]";
        String linearResistance2 = "15,1 [ pa p m ]";

        ParamConverter<Temperature> tempConverter =
                CONVERTER_PROVIDER.getConverter(Temperature.class, null, null);

        ParamConverter<DynamicViscosity> dynamicViscosityParamConverter =
                CONVERTER_PROVIDER.getConverter(DynamicViscosity.class, null, null);

        ParamConverter<ThermalDiffusivity> thermalDiffParamConverter =
                CONVERTER_PROVIDER.getConverter(ThermalDiffusivity.class, null, null);

        ParamConverter<LinearResistance> linearResistanceParamConverter =
                CONVERTER_PROVIDER.getConverter(LinearResistance.class, null, null);

        // When
        Temperature actualTemperature1 = tempConverter.fromString(tempInput1);
        Temperature actualTemperature2 = tempConverter.fromString(tempInput2);
        DynamicViscosity actualDynamicViscosity = dynamicViscosityParamConverter.fromString(dynVisInput);
        ThermalDiffusivity actualThermalDiffusivity = thermalDiffParamConverter.fromString(thermalDiffInput);
        LinearResistance actualLinearResistance = linearResistanceParamConverter.fromString(linearResistance);
        LinearResistance actualLinearResistance2 = linearResistanceParamConverter.fromString(linearResistance2);

        // Then
        assertThat(actualTemperature1).isEqualTo(Temperature.ofCelsius(15.1));
        assertThat(actualTemperature2).isEqualTo(Temperature.ofCelsius(15.1));
        assertThat(actualDynamicViscosity).isEqualTo(DynamicViscosity.ofKiloGramPerMeterSecond(15.1));
        assertThat(actualThermalDiffusivity).isEqualTo(ThermalDiffusivity.ofSquareFeetPerSecond(15.1));
        assertThat(actualLinearResistance).isEqualTo(LinearResistance.ofInchOfWaterPer100Feet(15.1));
        assertThat(actualLinearResistance2).isEqualTo(LinearResistance.ofPascalPerMeter(15.1));

    }

}