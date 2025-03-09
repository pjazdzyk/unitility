package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.*;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.flow.MassFlow;
import com.synerset.unitility.unitsystem.flow.MassFlowUnits;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.flow.VolumetricFlowUnits;
import com.synerset.unitility.unitsystem.geographic.Bearing;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.HumidityRatioUnits;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidityUnits;
import com.synerset.unitility.unitsystem.hydraulic.*;
import com.synerset.unitility.unitsystem.mechanical.*;
import com.synerset.unitility.unitsystem.thermodynamic.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhysicalQuantityParsingFactoryTest {

    private static final PhysicalQuantityParsingFactory PARSING_FACTORY = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @Test
    @DisplayName("should create default parsing registry with registered parsers")
    void getClassRegistry_shouldCreateRegistry() {
        // Given
        // When
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> registryMap = PARSING_FACTORY.getClassRegistry();
        Set<Class<PhysicalQuantity<Unit>>> registeredClasses = PARSING_FACTORY.findAllRegisteredClasses();
        boolean status = PARSING_FACTORY.containsClass(Temperature.class);

        // Then
        assertThat(PARSING_FACTORY).isNotNull();
        assertThat(registryMap).isNotNull().isNotEmpty().containsKey(Temperature.class);
        assertThat(registeredClasses).isNotNull().isNotEmpty().hasSizeGreaterThan(27);
        assertThat(status).isTrue();
    }

    @Test
    @DisplayName("should be immutable map, clear should not be possible")
    void getClassRegistry_shouldBeImmutableMap() {
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> classRegistry = PARSING_FACTORY.getClassRegistry();
        assertThrows(UnsupportedOperationException.class, classRegistry::clear);
    }

    @Test
    @DisplayName("should fail when attempt to parse for not registered class")
    void createFromSymbol_shouldFailIfQueriedForNonSupportedClass() {
        // When
        // Then
        assertThrows(UnitSystemClassNotSupportedException.class,
                () -> PARSING_FACTORY.parseValueAndSymbol(TestClass.class, 20.0, "test"));
    }

    @Test
    @DisplayName("should fail when attempt to parse from invalid string")
    void parse_shouldFailIfQueriedForNonSupportedClass() {
        // When
        // Then
        assertThrows(UnitSystemClassNotSupportedException.class,
                () -> PARSING_FACTORY.parse(TestClass.class, "20C"));
    }

    @Test
    @DisplayName("should parse from DMS format to latitude or longitude")
    void parse_shouldParseFromDMSFormatToLatitudeOrLongitude() {
        // Given
        String lat1 = "52°14'5.123\"N";
        String lon1 = "21°4'3.986\"W";
        String lat2 = " 52o 14min 5.123sec N";
        String lon2 = "21deg 4' 3.986\"   w";
        String lat3 = "52°14'5.123\"N";
        String lon3 = "-21°4'3.986\"";
        String lat4 = "52°14'N";
        String lon4 = "21°4'W";
        String lat5 = "52°N";
        String lon5 = "21°4'W";
        String lat6 = "52deg N";
        String lon6 = "21o 4'W";

        // When
        Latitude actualLat1 = PARSING_FACTORY.parse(Latitude.class, lat1);
        Longitude actualLon1 = PARSING_FACTORY.parse(Longitude.class, lon1);
        Latitude actualLat2 = PARSING_FACTORY.parse(Latitude.class, lat2);
        Longitude actualLon2 = PARSING_FACTORY.parse(Longitude.class, lon2);
        Latitude actualLat3 = PARSING_FACTORY.parse(Latitude.class, lat3);
        Longitude actualLon3 = PARSING_FACTORY.parse(Longitude.class, lon3);
        Latitude actualLat4 = PARSING_FACTORY.parse(Latitude.class, lat4);
        Longitude actualLon4 = PARSING_FACTORY.parse(Longitude.class, lon4);
        Latitude actualLat5 = PARSING_FACTORY.parse(Latitude.class, lat5);
        Longitude actualLon5 = PARSING_FACTORY.parse(Longitude.class, lon5);
        Latitude actualLat6 = PARSING_FACTORY.parse(Latitude.class, lat6);
        Longitude actualLon6 = PARSING_FACTORY.parse(Longitude.class, lon6);

        // Then
        assertThat(actualLat1).isEqualTo(Latitude.ofDegrees(52.23475638888889));
        assertThat(actualLon1).isEqualTo(Longitude.ofDegrees(-21.06777388888889));
        assertThat(actualLat2).isEqualTo(Latitude.ofDegrees(52.23475638888889));
        assertThat(actualLon2).isEqualTo(Longitude.ofDegrees(-21.06777388888889));
        assertThat(actualLat3).isEqualTo(Latitude.ofDegrees(52.23475638888889));
        assertThat(actualLon3).isEqualTo(Longitude.ofDegrees(-21.06777388888889));
        assertThat(actualLat4).isEqualTo(Latitude.ofDegrees(52.233333333333334));
        assertThat(actualLon4).isEqualTo(Longitude.ofDegrees(-21.066666666666666));
        assertThat(actualLat5).isEqualTo(Latitude.ofDegrees(52.0));
        assertThat(actualLon5).isEqualTo(Longitude.ofDegrees(-21.066666666666666));
        assertThat(actualLat6).isEqualTo(Latitude.ofDegrees(52.0));
        assertThat(actualLon6).isEqualTo(Longitude.ofDegrees(-21.066666666666666));
    }

    @Test
    @DisplayName("should fail on invalid or malformed DMS format")
    void parse_shouldFailOnInvalidOrMalformedDMSFormat() {
        // Given
        String lat1 = "52°14'5.123\"E";
        String lon1 = "21°4'3.986\"N";
        String lon2 = "21°4'3.986\"N";
        // When // Then
        assertThrows(UnitSystemParseException.class, () -> PARSING_FACTORY.parse(Latitude.class, lat1));
        assertThrows(UnitSystemParseException.class, () -> PARSING_FACTORY.parse(Longitude.class, lon1));
        assertThrows(UnitSystemParseException.class, () -> PARSING_FACTORY.parse(Longitude.class, lon2));
    }

    @Test
    @DisplayName("should parse from single value only and resolve to quantity with default unit")
    void parse_shouldParseWithoutSymbolAndResolveToDefaultUnit() {
        // Given
        String singleValueInput = "-10.5E-5";
        double expectedValue = Double.parseDouble(singleValueInput);

        // When
        Angle actualAngle = PARSING_FACTORY.parse(Angle.class, singleValueInput);
        Area actualArea = PARSING_FACTORY.parse(Area.class, singleValueInput);
        Distance actualDistance = PARSING_FACTORY.parse(Distance.class, singleValueInput);
        Length actualLength = PARSING_FACTORY.parse(Length.class, singleValueInput);
        Width actualWidth = PARSING_FACTORY.parse(Width.class, singleValueInput);
        Height actualHeight = PARSING_FACTORY.parse(Height.class, singleValueInput);
        Diameter actualDiameter = PARSING_FACTORY.parse(Diameter.class, singleValueInput);
        Perimeter actualPerimeter = PARSING_FACTORY.parse(Perimeter.class, singleValueInput);
        Mass actualMass = PARSING_FACTORY.parse(Mass.class, singleValueInput);
        LinearMassDensity actualLinearMassDensity = PARSING_FACTORY.parse(LinearMassDensity.class, singleValueInput);
        Velocity actualVelocity = PARSING_FACTORY.parse(Velocity.class, singleValueInput);
        Volume actualVolume = PARSING_FACTORY.parse(Volume.class, singleValueInput);
        BypassFactor actualBfFactor = PARSING_FACTORY.parse(BypassFactor.class, singleValueInput);
        GrashofNumber actualGrNumber = PARSING_FACTORY.parse(GrashofNumber.class, singleValueInput);
        PrandtlNumber actualPrNumber = PARSING_FACTORY.parse(PrandtlNumber.class, singleValueInput);
        ReynoldsNumber actualReNumber = PARSING_FACTORY.parse(ReynoldsNumber.class, singleValueInput);
        MassFlow actualMassFlow = PARSING_FACTORY.parse(MassFlow.class, singleValueInput);
        VolumetricFlow actualVolFlow = PARSING_FACTORY.parse(VolumetricFlow.class, singleValueInput);
        HumidityRatio actualHumRatio = PARSING_FACTORY.parse(HumidityRatio.class, singleValueInput);
        RelativeHumidity actualRelHum = PARSING_FACTORY.parse(RelativeHumidity.class, singleValueInput);

        LinearResistance actualLinearResistance = PARSING_FACTORY.parse(LinearResistance.class, singleValueInput);
        FrictionFactor actualFrictionFactor = PARSING_FACTORY.parse(FrictionFactor.class, singleValueInput);
        LocalLossFactor actualLocalLossFactor = PARSING_FACTORY.parse(LocalLossFactor.class, singleValueInput);

        Force actualForce = PARSING_FACTORY.parse(Force.class, singleValueInput);
        Momentum actualMomentum = PARSING_FACTORY.parse(Momentum.class, singleValueInput);
        Torque actualTorque = PARSING_FACTORY.parse(Torque.class, singleValueInput);
        Density actualDensity = PARSING_FACTORY.parse(Density.class, singleValueInput);
        DynamicViscosity actualDynVis = PARSING_FACTORY.parse(DynamicViscosity.class, singleValueInput);
        Energy actualEnergy = PARSING_FACTORY.parse(Energy.class, singleValueInput);
        KinematicViscosity actualKinVis = PARSING_FACTORY.parse(KinematicViscosity.class, singleValueInput);
        Power actualPower = PARSING_FACTORY.parse(Power.class, singleValueInput);

        Pressure actualPressure = PARSING_FACTORY.parse(Pressure.class, singleValueInput);

        SpecificEnthalpy actualSpecEnthalpy = PARSING_FACTORY.parse(SpecificEnthalpy.class, singleValueInput);
        SpecificHeat actualSpecHeat = PARSING_FACTORY.parse(SpecificHeat.class, singleValueInput);
        Temperature actualTemperature = PARSING_FACTORY.parse(Temperature.class, singleValueInput);
        ThermalConductivity actualThermCond = PARSING_FACTORY.parse(ThermalConductivity.class, singleValueInput);
        ThermalDiffusivity actualThermDiff = PARSING_FACTORY.parse(ThermalDiffusivity.class, singleValueInput);
        Latitude actualLatitude = PARSING_FACTORY.parse(Latitude.class, singleValueInput);
        Longitude actualLongitude = PARSING_FACTORY.parse(Longitude.class, singleValueInput);
        Ratio actualRatio = PARSING_FACTORY.parse(Ratio.class, singleValueInput);
        Bearing actualBearing = PARSING_FACTORY.parse(Bearing.class, singleValueInput);

        // Then
        assertThat(actualAngle).isEqualTo(Angle.of(expectedValue, AngleUnits.getDefaultUnit()));
        assertThat(actualArea).isEqualTo(Area.of(expectedValue, AreaUnits.getDefaultUnit()));
        assertThat(actualDistance).isEqualTo(Distance.of(expectedValue, DistanceUnits.getDefaultUnit()));
        assertThat(actualLength).isEqualTo(Length.of(expectedValue, DistanceUnits.getDefaultUnit()));
        assertThat(actualWidth).isEqualTo(Width.of(expectedValue, DistanceUnits.getDefaultUnit()));
        assertThat(actualHeight).isEqualTo(Height.of(expectedValue, DistanceUnits.getDefaultUnit()));
        assertThat(actualDiameter).isEqualTo(Diameter.of(expectedValue, DistanceUnits.getDefaultUnit()));
        assertThat(actualPerimeter).isEqualTo(Perimeter.of(expectedValue, DistanceUnits.getDefaultUnit()));
        assertThat(actualMass).isEqualTo(Mass.of(expectedValue, MassUnits.getDefaultUnit()));
        assertThat(actualLinearMassDensity).isEqualTo(LinearMassDensity.of(expectedValue, LinearMassDensityUnits.getDefaultUnit()));
        assertThat(actualVelocity).isEqualTo(Velocity.of(expectedValue, VelocityUnits.getDefaultUnit()));
        assertThat(actualVolume).isEqualTo(Volume.of(expectedValue, VolumeUnits.getDefaultUnit()));
        assertThat(actualBfFactor).isEqualTo(BypassFactor.of(expectedValue));
        assertThat(actualGrNumber).isEqualTo(GrashofNumber.of(expectedValue));
        assertThat(actualPrNumber).isEqualTo(PrandtlNumber.of(expectedValue));
        assertThat(actualReNumber).isEqualTo(ReynoldsNumber.of(expectedValue));
        assertThat(actualMassFlow).isEqualTo(MassFlow.of(expectedValue, MassFlowUnits.getDefaultUnit()));
        assertThat(actualVolFlow).isEqualTo(VolumetricFlow.of(expectedValue, VolumetricFlowUnits.getDefaultUnit()));
        assertThat(actualHumRatio).isEqualTo(HumidityRatio.of(expectedValue, HumidityRatioUnits.getDefaultUnit()));
        assertThat(actualRelHum).isEqualTo(RelativeHumidity.of(expectedValue, RelativeHumidityUnits.getDefaultUnit()));
        // Hydraulic
        assertThat(actualFrictionFactor).isEqualTo(FrictionFactor.of(expectedValue));
        assertThat(actualLinearResistance).isEqualTo(LinearResistance.of(expectedValue, LinearResistanceUnits.getDefaultUnit()));
        assertThat(actualLocalLossFactor).isEqualTo(LocalLossFactor.of(expectedValue));

        assertThat(actualForce).isEqualTo(Force.of(expectedValue, ForceUnits.getDefaultUnit()));
        assertThat(actualMomentum).isEqualTo(Momentum.of(expectedValue, MomentumUnits.getDefaultUnit()));
        assertThat(actualTorque).isEqualTo(Torque.of(expectedValue, TorqueUnits.getDefaultUnit()));
        assertThat(actualDensity).isEqualTo(Density.of(expectedValue, DensityUnits.getDefaultUnit()));
        assertThat(actualDynVis).isEqualTo(DynamicViscosity.of(expectedValue, DynamicViscosityUnits.getDefaultUnit()));
        assertThat(actualEnergy).isEqualTo(Energy.of(expectedValue, EnergyUnits.getDefaultUnit()));
        assertThat(actualKinVis).isEqualTo(KinematicViscosity.of(expectedValue, KinematicViscosityUnits.getDefaultUnit()));
        assertThat(actualPower).isEqualTo(Power.of(expectedValue, PowerUnits.getDefaultUnit()));
        assertThat(actualPressure).isEqualTo(Pressure.of(expectedValue, PressureUnits.getDefaultUnit()));
        assertThat(actualSpecEnthalpy).isEqualTo(SpecificEnthalpy.of(expectedValue, SpecificEnthalpyUnits.getDefaultUnit()));
        assertThat(actualSpecHeat).isEqualTo(SpecificHeat.of(expectedValue, SpecificHeatUnits.getDefaultUnit()));
        assertThat(actualTemperature).isEqualTo(Temperature.of(expectedValue, TemperatureUnits.getDefaultUnit()));
        assertThat(actualThermCond).isEqualTo(ThermalConductivity.of(expectedValue, ThermalConductivityUnits.getDefaultUnit()));
        assertThat(actualThermDiff).isEqualTo(ThermalDiffusivity.of(expectedValue, ThermalDiffusivityUnits.getDefaultUnit()));
        assertThat(actualLatitude).isEqualTo(Latitude.of(expectedValue, AngleUnits.getDefaultUnit()));
        assertThat(actualLongitude).isEqualTo(Longitude.of(expectedValue, AngleUnits.getDefaultUnit()));
        assertThat(actualRatio).isEqualTo(Ratio.of(expectedValue, RatioUnits.getDefaultUnit()));
        assertThat(actualBearing).isEqualTo(Bearing.of(expectedValue));
    }

    /**
     * Test class, non-existent in parsing registry, created to test failure cases.
     */
    static class TestClass implements CalculableQuantity<DistanceUnit, TestClass> {

        @Override
        public double getValue() {
            return 0;
        }

        @Override
        public double getBaseValue() {
            return 0;
        }

        @Override
        public DistanceUnit getUnit() {
            return null;
        }

        @Override
        public PhysicalQuantity<DistanceUnit> toBaseUnit() {
            return null;
        }

        @Override
        public PhysicalQuantity<DistanceUnit> toUnit(DistanceUnit targetUnit) {
            return null;
        }

        @Override
        public PhysicalQuantity<DistanceUnit> toUnit(String targetUnit) {
            return null;
        }

        @Override
        public TestClass withValue(double value) {
            return null;
        }
    }

}