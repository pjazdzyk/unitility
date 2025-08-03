package com.synerset.unitility.persistence.converter.plainsivalue;

import com.synerset.unitility.persistence.converter.plainsivalue.common.*;
import com.synerset.unitility.persistence.converter.plainsivalue.dimensionless.*;
import com.synerset.unitility.persistence.converter.plainsivalue.flow.MassFlowPlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.common.MassPlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.common.VolumePlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.flow.VolumetricFlowPlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.geographic.*;
import com.synerset.unitility.persistence.converter.plainsivalue.humidity.HumidityRatioPlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.humidity.RelativeHumidityPlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.hydraulic.*;
import com.synerset.unitility.persistence.converter.plainsivalue.mechanical.ForcePlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.mechanical.MomentumPlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.mechanical.TorquePlainSiConverter;
import com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic.*;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.geographic.*;
import com.synerset.unitility.unitsystem.thermodynamic.*;
import com.synerset.unitility.unitsystem.flow.*;
import com.synerset.unitility.unitsystem.humidity.*;
import com.synerset.unitility.unitsystem.mechanical.*;
import com.synerset.unitility.unitsystem.dimensionless.*;
import com.synerset.unitility.unitsystem.hydraulic.*;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PlainSiConverterTest {

    private static final String FOLDER_PATH = "src/main/java/com/synerset/unitility/persistence/converter/plainsivalue";
    
    @Test
    @DisplayName("Density Plain SI Converter: should successfully convert Density")
    void shouldSuccessfullyConvertDensityTest() {
        // Given
        Density density = Density.ofPoundPerCubicFoot(2.5);
        DensityUnit defaultConverterUnit = DensityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = density.getInUnit(defaultConverterUnit);

        DensityPlainSiConverter converter = new DensityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(density);
        Density actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        
        assertThat(actualQuantityFromDB.getInPoundsPerCubicFoot()).isEqualTo(density.getInPoundsPerCubicFoot(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Height Plain SI Converter: should successfully convert Height")
    void shouldSuccessfullyConvertHeightTest() {
        // Given
        Height height = Height.ofFeet(3.5);
        DistanceUnit defaultConverterUnit = HeightPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = height.getInUnit(defaultConverterUnit);

        HeightPlainSiConverter converter = new HeightPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(height);
        Height actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInFeet()).isEqualTo(height.getInFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Thermal Conductivity Plain SI Converter: should successfully convert Thermal Conductivity")
    void shouldSuccessfullyConvertThermalConductivityTest() {
        // Given
        ThermalConductivity thermalConductivity = ThermalConductivity.ofBTUPerHourFeetFahrenheit(1.5);
        ThermalConductivityUnit defaultConverterUnit = ThermalConductivityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = thermalConductivity.getInUnit(defaultConverterUnit);

        ThermalConductivityPlainSiConverter converter = new ThermalConductivityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(thermalConductivity);
        ThermalConductivity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInBTUsPerHourFeetFahrenheit()).isEqualTo(thermalConductivity.getInBTUsPerHourFeetFahrenheit(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Angle Plain SI Converter: should successfully convert Angle")
    void shouldSuccessfullyConvertAngleTest() {
        // Given
        Angle angle = Angle.ofDegrees(45.0);
        AngleUnit defaultConverterUnit = AnglePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = angle.getInUnit(defaultConverterUnit);

        AnglePlainSiConverter converter = new AnglePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(angle);
        Angle actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInDegrees()).isEqualTo(angle.getInDegrees(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Area Plain SI Converter: should successfully convert Area")
    void shouldSuccessfullyConvertAreaTest() {
        // Given
        Area area = Area.ofSquareFeet(100.0);
        AreaUnit defaultConverterUnit = AreaPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = area.getInUnit(defaultConverterUnit);

        AreaPlainSiConverter converter = new AreaPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(area);
        Area actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInSquareFeet()).isEqualTo(area.getInSquareFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Distance Plain SI Converter: should successfully convert Distance")
    void shouldSuccessfullyConvertDistanceTest() {
        // Given
        Distance distance = Distance.ofFeet(10.0);
        DistanceUnit defaultConverterUnit = DistancePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = distance.getInUnit(defaultConverterUnit);

        DistancePlainSiConverter converter = new DistancePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(distance);
        Distance actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInFeet()).isEqualTo(distance.getInFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Length Plain SI Converter: should successfully convert Length")
    void shouldSuccessfullyConvertLengthTest() {
        // Given
        Length length = Length.ofFeet(15.0);
        DistanceUnit defaultConverterUnit = LengthPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = length.getInUnit(defaultConverterUnit);

        LengthPlainSiConverter converter = new LengthPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(length);
        Length actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInFeet()).isEqualTo(length.getInFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Width Plain SI Converter: should successfully convert Width")
    void shouldSuccessfullyConvertWidthTest() {
        // Given
        Width width = Width.ofFeet(5.0);
        DistanceUnit defaultConverterUnit = WidthPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = width.getInUnit(defaultConverterUnit);

        WidthPlainSiConverter converter = new WidthPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(width);
        Width actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));
        
        assertThat(actualQuantityFromDB.getInFeet()).isEqualTo(width.getInFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Diameter Plain SI Converter: should successfully convert Diameter")
    void shouldSuccessfullyConvertDiameterTest() {
        // Given
        Diameter diameter = Diameter.ofFeet(2.0);
        DistanceUnit defaultConverterUnit = DiameterPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = diameter.getInUnit(defaultConverterUnit);

        DiameterPlainSiConverter converter = new DiameterPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(diameter);
        Diameter actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInFeet()).isEqualTo(diameter.getInFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Perimeter Plain SI Converter: should successfully convert Perimeter")
    void shouldSuccessfullyConvertPerimeterTest() {
        // Given
        Perimeter perimeter = Perimeter.ofFeet(20.0);
        DistanceUnit defaultConverterUnit = PerimeterPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = perimeter.getInUnit(defaultConverterUnit);

        PerimeterPlainSiConverter converter = new PerimeterPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(perimeter);
        Perimeter actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInFeet()).isEqualTo(perimeter.getInFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Mass Plain SI Converter: should successfully convert Mass")
    void shouldSuccessfullyConvertMassTest() {
        // Given
        Mass mass = Mass.ofPounds(150.0);
        MassUnit defaultConverterUnit = MassPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = mass.getInUnit(defaultConverterUnit);

        MassPlainSiConverter converter = new MassPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(mass);
        Mass actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPounds()).isEqualTo(mass.getInPounds(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Linear Mass Density Plain SI Converter: should successfully convert Linear Mass Density")
    void shouldSuccessfullyConvertLinearMassDensityTest() {
        // Given
        LinearMassDensity linearMassDensity = LinearMassDensity.ofPoundsPerFoot(2.0);
        LinearMassDensityUnit defaultConverterUnit = LinearMassDensityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = linearMassDensity.getInUnit(defaultConverterUnit);

        LinearMassDensityPlainSiConverter converter = new LinearMassDensityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(linearMassDensity);
        LinearMassDensity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPoundsPerFoot()).isEqualTo(linearMassDensity.getInPoundsPerFoot(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Velocity Plain SI Converter: should successfully convert Velocity")
    void shouldSuccessfullyConvertVelocityTest() {
        // Given
        Velocity velocity = Velocity.ofFeetPerSecond(5.0);
        VelocityUnit defaultConverterUnit = VelocityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = velocity.getInUnit(defaultConverterUnit);

        VelocityPlainSiConverter converter = new VelocityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(velocity);
        Velocity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInFeetPerSeconds()).isEqualTo(velocity.getInFeetPerSeconds(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Angular Velocity Plain SI Converter: should successfully convert Angular Velocity")
    void shouldSuccessfullyConvertAngularVelocityTest() {
        // Given
        AngularVelocity angularVelocity = AngularVelocity.ofDegreesPerSecond(30.0);
        AngularVelocityUnit defaultConverterUnit = AngularVelocityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = angularVelocity.getInUnit(defaultConverterUnit);

        AngularVelocityPlainSiConverter converter = new AngularVelocityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(angularVelocity);
        AngularVelocity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInDegreesPerSecond()).isEqualTo(angularVelocity.getInDegreesPerSecond(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Volume Plain SI Converter: should successfully convert Volume")
    void shouldSuccessfullyConvertVolumeTest() {
        // Given
        Volume volume = Volume.ofCubicFeet(100.0);
        VolumeUnit defaultConverterUnit = VolumePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = volume.getInUnit(defaultConverterUnit);

        VolumePlainSiConverter converter = new VolumePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(volume);
        Volume actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInCubicFeet()).isEqualTo(volume.getInCubicFeet(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Ratio Plain SI Converter: should successfully convert Ratio")
    void shouldSuccessfullyConvertRatioTest() {
        // Given
        Ratio ratio = Ratio.ofDecimal(0.75);
        RatioUnit defaultConverterUnit = RatioPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = ratio.getInUnit(defaultConverterUnit);

        RatioPlainSiConverter converter = new RatioPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(ratio);
        Ratio actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInDecimal()).isEqualTo(ratio.getInDecimal(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Curvature Plain SI Converter: should successfully convert Curvature")
    void shouldSuccessfullyConvertCurvatureTest() {
        // Given
        Curvature curvature = Curvature.ofDegreesPerMeter(0.1);
        CurvatureUnit defaultConverterUnit = CurvaturePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = curvature.getInUnit(defaultConverterUnit);

        CurvaturePlainSiConverter converter = new CurvaturePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(curvature);
        Curvature actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInDegreesPerMeter()).isEqualTo(curvature.getInDegreesPerMeter(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Generic Dimensionless Plain SI Converter: should successfully convert Generic Dimensionless")
    void shouldSuccessfullyConvertGenericDimensionlessTest() {
        // Given
        GenericDimensionless genericDimensionless = GenericDimensionless.of(0.5);
        GenericDimensionlessUnit defaultConverterUnit = GenericDimensionlessPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = genericDimensionless.getInUnit(defaultConverterUnit);

        GenericDimensionlessPlainSiConverter converter = new GenericDimensionlessPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(genericDimensionless);
        GenericDimensionless actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(genericDimensionless.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Bypass Factor Plain SI Converter: should successfully convert Bypass Factor")
    void shouldSuccessfullyConvertBypassFactorTest() {
        // Given
        BypassFactor bypassFactor = BypassFactor.of(0.2);
        BypassFactorUnit defaultConverterUnit = BypassFactorPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = bypassFactor.getInUnit(defaultConverterUnit);

        BypassFactorPlainSiConverter converter = new BypassFactorPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(bypassFactor);
        BypassFactor actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(bypassFactor.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Grashof Number Plain SI Converter: should successfully convert Grashof Number")
    void shouldSuccessfullyConvertGrashofNumberTest() {
        // Given
        GrashofNumber grashofNumber = GrashofNumber.of(1000.0);
        GrashofNumberUnit defaultConverterUnit = GrashofNumberPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = grashofNumber.getInUnit(defaultConverterUnit);

        GrashofNumberPlainSiConverter converter = new GrashofNumberPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(grashofNumber);
        GrashofNumber actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(grashofNumber.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Prandtl Number Plain SI Converter: should successfully convert Prandtl Number")
    void shouldSuccessfullyConvertPrandtlNumberTest() {
        // Given
        PrandtlNumber prandtlNumber = PrandtlNumber.of(0.7);
        PrandtlNumberUnit defaultConverterUnit = PrandtlNumberPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = prandtlNumber.getInUnit(defaultConverterUnit);

        PrandtlNumberPlainSiConverter converter = new PrandtlNumberPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(prandtlNumber);
        PrandtlNumber actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(prandtlNumber.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Reynolds Number Plain SI Converter: should successfully convert Reynolds Number")
    void shouldSuccessfullyConvertReynoldsNumberTest() {
        // Given
        ReynoldsNumber reynoldsNumber = ReynoldsNumber.of(5000.0);
        ReynoldsNumberUnit defaultConverterUnit = ReynoldsNumberPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = reynoldsNumber.getInUnit(defaultConverterUnit);

        ReynoldsNumberPlainSiConverter converter = new ReynoldsNumberPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(reynoldsNumber);
        ReynoldsNumber actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(reynoldsNumber.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Mass Flow Plain SI Converter: should successfully convert Mass Flow")
    void shouldSuccessfullyConvertMassFlowTest() {
        // Given
        MassFlow massFlow = MassFlow.ofPoundsPerSecond(1000.0);
        MassFlowUnit defaultConverterUnit = MassFlowPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = massFlow.getInUnit(defaultConverterUnit);

        MassFlowPlainSiConverter converter = new MassFlowPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(massFlow);
        MassFlow actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPoundsPerSecond()).isEqualTo(massFlow.getInPoundsPerSecond(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Volumetric Flow Plain SI Converter: should successfully convert Volumetric Flow")
    void shouldSuccessfullyConvertVolumetricFlowTest() {
        // Given
        VolumetricFlow volumetricFlow = VolumetricFlow.ofCubicFeetPerMinute(500.0);
        VolumetricFlowUnit defaultConverterUnit = VolumetricFlowPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = volumetricFlow.getInUnit(defaultConverterUnit);

        VolumetricFlowPlainSiConverter converter = new VolumetricFlowPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(volumetricFlow);
        VolumetricFlow actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInCubicFeetPerMinute()).isEqualTo(volumetricFlow.getInCubicFeetPerMinute(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Humidity Ratio Plain SI Converter: should successfully convert Humidity Ratio")
    void shouldSuccessfullyConvertHumidityRatioTest() {
        // Given
        HumidityRatio humidityRatio = HumidityRatio.ofGramPerKilogram(10.0);
        HumidityRatioUnit defaultConverterUnit = HumidityRatioPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = humidityRatio.getInUnit(defaultConverterUnit);

        HumidityRatioPlainSiConverter converter = new HumidityRatioPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(humidityRatio);
        HumidityRatio actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getGramPerKilogram()).isEqualTo(humidityRatio.getGramPerKilogram(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Relative Humidity Plain SI Converter: should successfully convert Relative Humidity")
    void shouldSuccessfullyConvertRelativeHumidityTest() {
        // Given
        RelativeHumidity relativeHumidity = RelativeHumidity.ofPercentage(50.0);
        RelativeHumidityUnit defaultConverterUnit = RelativeHumidityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = relativeHumidity.getInUnit(defaultConverterUnit);

        RelativeHumidityPlainSiConverter converter = new RelativeHumidityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(relativeHumidity);
        RelativeHumidity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPercent()).isEqualTo(relativeHumidity.getInPercent(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Linear Resistance Plain SI Converter: should successfully convert Linear Resistance")
    void shouldSuccessfullyConvertLinearResistanceTest() {
        // Given
        LinearResistance linearResistance = LinearResistance.ofPascalPerMeter(0.01);
        LinearResistanceUnit defaultConverterUnit = LinearResistancePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = linearResistance.getInUnit(defaultConverterUnit);

        LinearResistancePlainSiConverter converter = new LinearResistancePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(linearResistance);
        LinearResistance actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPascalPerMeter()).isEqualTo(linearResistance.getInPascalPerMeter(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Friction Factor Plain SI Converter: should successfully convert Friction Factor")
    void shouldSuccessfullyConvertFrictionFactorTest() {
        // Given
        FrictionFactor frictionFactor = FrictionFactor.of(0.02);
        FrictionFactorUnit defaultConverterUnit = FrictionFactorPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = frictionFactor.getInUnit(defaultConverterUnit);

        FrictionFactorPlainSiConverter converter = new FrictionFactorPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(frictionFactor);
        FrictionFactor actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(frictionFactor.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Local Loss Factor Plain SI Converter: should successfully convert Local Loss Factor")
    void shouldSuccessfullyConvertLocalLossFactorTest() {
        // Given
        LocalLossFactor localLossFactor = LocalLossFactor.of(0.5);
        LocalLossFactorUnit defaultConverterUnit = LocalLossFactorPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = localLossFactor.getInUnit(defaultConverterUnit);

        LocalLossFactorPlainSiConverter converter = new LocalLossFactorPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(localLossFactor);
        LocalLossFactor actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(localLossFactor.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Rotation Speed To Flow Rate Ratio Plain SI Converter: should successfully convert Rotation Speed To Flow Rate Ratio")
    void shouldSuccessfullyConvertRotationSpeedToFlowRateRatioTest() {
        // Given
        RotationSpeedToFlowRateRatio ratio = RotationSpeedToFlowRateRatio.ofRpmPerGpm(2.0);
        RotationSpeedToFlowRateRatioUnit defaultConverterUnit = RotationSpeedToFlowRateRatioPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = ratio.getInUnit(defaultConverterUnit);

        RotationSpeedToFlowRateRatioPlainSiConverter converter = new RotationSpeedToFlowRateRatioPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(ratio);
        RotationSpeedToFlowRateRatio actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInRpmPerGpm()).isEqualTo(ratio.getInRpmPerGpm(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("SDR Converter: should successfully convert SDR")
    void shouldSuccessfullyConvertSdrTest() {
        // Given
        SDR ratio = SDR.of(0.75);
        RatioUnit defaultConverterUnit = SDRPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = ratio.getInUnit(defaultConverterUnit);

        SDRPlainSiConverter converter = new SDRPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(ratio);
        SDR actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getValue()).isEqualTo(ratio.getValue(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Force Plain SI Converter: should successfully convert Force")
    void shouldSuccessfullyConvertForceTest() {
        // Given
        Force force = Force.ofPoundForce(100.0);
        ForceUnit defaultConverterUnit = ForcePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = force.getInUnit(defaultConverterUnit);

        ForcePlainSiConverter converter = new ForcePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(force);
        Force actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPoundsForce()).isEqualTo(force.getInPoundsForce(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Momentum Plain SI Converter: should successfully convert Momentum")
    void shouldSuccessfullyConvertMomentumTest() {
        // Given
        Momentum momentum = Momentum.ofPoundFeetPerSecond(50.0);
        MomentumUnit defaultConverterUnit = MomentumPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = momentum.getInUnit(defaultConverterUnit);

        MomentumPlainSiConverter converter = new MomentumPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(momentum);
        Momentum actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPoundFeetPerSecond()).isEqualTo(momentum.getInPoundFeetPerSecond(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Torque Plain SI Converter: should successfully convert Torque")
    void shouldSuccessfullyConvertTorqueTest() {
        // Given
        Torque torque = Torque.ofKilopondMeters(20.0);
        TorqueUnit defaultConverterUnit = TorquePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = torque.getInUnit(defaultConverterUnit);

        TorquePlainSiConverter converter = new TorquePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(torque);
        Torque actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInKilopondMeters()).isEqualTo(torque.getInKilopondMeters(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Dynamic Viscosity Plain SI Converter: should successfully convert Dynamic Viscosity")
    void shouldSuccessfullyConvertDynamicViscosityTest() {
        // Given
        DynamicViscosity dynamicViscosity = DynamicViscosity.ofPascalSecond(0.001);
        DynamicViscosityUnit defaultConverterUnit = DynamicViscosityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = dynamicViscosity.getInUnit(defaultConverterUnit);

        DynamicViscosityPlainSiConverter converter = new DynamicViscosityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(dynamicViscosity);
        DynamicViscosity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPascalsSecond()).isEqualTo(dynamicViscosity.getInPascalsSecond(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Energy Plain SI Converter: should successfully convert Energy")
    void shouldSuccessfullyConvertEnergyTest() {
        // Given
        Energy energy = Energy.ofJoules(1000.0);
        EnergyUnit defaultConverterUnit = EnergyPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = energy.getInUnit(defaultConverterUnit);

        EnergyPlainSiConverter converter = new EnergyPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(energy);
        Energy actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInJoules()).isEqualTo(energy.getInJoules(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Kinematic Viscosity Plain SI Converter: should successfully convert Kinematic Viscosity")
    void shouldSuccessfullyConvertKinematicViscosityTest() {
        // Given
        KinematicViscosity kinematicViscosity = KinematicViscosity.ofSquareMeterPerSecond(0.000001);
        KinematicViscosityUnit defaultConverterUnit = KinematicViscosityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = kinematicViscosity.getInUnit(defaultConverterUnit);

        KinematicViscosityPlainSiConverter converter = new KinematicViscosityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(kinematicViscosity);
        KinematicViscosity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInSquareFeetPerSecond()).isEqualTo(kinematicViscosity.getInSquareFeetPerSecond(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Power Plain SI Converter: should successfully convert Power")
    void shouldSuccessfullyConvertPowerTest() {
        // Given
        Power power = Power.ofWatts(500.0);
        PowerUnit defaultConverterUnit = PowerPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = power.getInUnit(defaultConverterUnit);

        PowerPlainSiConverter converter = new PowerPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(power);
        Power actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInWatts()).isEqualTo(power.getInWatts(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Pressure Plain SI Converter: should successfully convert Pressure")
    void shouldSuccessfullyConvertPressureTest() {
        // Given
        Pressure pressure = Pressure.ofPascal(101325.0);
        PressureUnit defaultConverterUnit = PressurePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = pressure.getInUnit(defaultConverterUnit);

        PressurePlainSiConverter converter = new PressurePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(pressure);
        Pressure actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInPascals()).isEqualTo(pressure.getInPascals(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Specific Enthalpy Plain SI Converter: should successfully convert Specific Enthalpy")
    void shouldSuccessfullyConvertSpecificEnthalpyTest() {
        // Given
        SpecificEnthalpy specificEnthalpy = SpecificEnthalpy.ofJoulePerKiloGram(1000.0);
        SpecificEnthalpyUnit defaultConverterUnit = SpecificEnthalpyPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = specificEnthalpy.getInUnit(defaultConverterUnit);

        SpecificEnthalpyPlainSiConverter converter = new SpecificEnthalpyPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(specificEnthalpy);
        SpecificEnthalpy actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInJoulesPerKiloGram()).isEqualTo(specificEnthalpy.getInJoulesPerKiloGram(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Specific Heat Plain SI Converter: should successfully convert Specific Heat")
    void shouldSuccessfullyConvertSpecificHeatTest() {
        // Given
        SpecificHeat specificHeat = SpecificHeat.ofJoulePerKiloGramKelvin(4200.0);
        SpecificHeatUnit defaultConverterUnit = SpecificHeatPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = specificHeat.getInUnit(defaultConverterUnit);

        SpecificHeatPlainSiConverter converter = new SpecificHeatPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(specificHeat);
        SpecificHeat actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));
        
        assertThat(actualQuantityFromDB.getInJoulePerKiloGramKelvin()).isEqualTo(specificHeat.getInJoulePerKiloGramKelvin(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Temperature Plain SI Converter: should successfully convert Temperature")
    void shouldSuccessfullyConvertTemperatureTest() {
        // Given
        Temperature temperature = Temperature.ofCelsius(25.0);
        TemperatureUnit defaultConverterUnit = TemperaturePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = temperature.getInUnit(defaultConverterUnit);

        TemperaturePlainSiConverter converter = new TemperaturePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(temperature);
        Temperature actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInCelsius()).isEqualTo(temperature.getInCelsius(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Temperature Plain SI Converter: should successfully convert Temperature")
    void shouldSuccessfullyConvertThermalDiffusivityTest() {
        // Given
        ThermalDiffusivity thermalDiffusivity = ThermalDiffusivity.ofSquareMeterPerSecond(2);
        ThermalDiffusivityUnit defaultConverterUnit = ThermalDiffusivityPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = thermalDiffusivity.getInUnit(defaultConverterUnit);

        ThermalDiffusivityPlainSiConverter converter = new ThermalDiffusivityPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(thermalDiffusivity);
        ThermalDiffusivity actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInSquareMetersPerSecond()).isEqualTo(thermalDiffusivity.getInSquareMetersPerSecond(), withPrecision(1E-11));
    }
    
    @Test
    @DisplayName("Latitude Plain SI Converter: should successfully convert Latitude")
    void shouldSuccessfullyConvertLatitudeTest() {
        // Given
        Latitude latitude = Latitude.of(45.0, AngleUnits.DEGREES);
        AngleUnit defaultConverterUnit = LatitudePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = latitude.getInUnit(defaultConverterUnit);

        LatitudePlainSiConverter converter = new LatitudePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(latitude);
        Latitude actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInDegrees()).isEqualTo(latitude.getInDegrees(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Longitude Plain SI Converter: should successfully convert Longitude")
    void shouldSuccessfullyConvertLongitudeTest() {
        // Given
        Longitude longitude = Longitude.of(90.0, AngleUnits.DEGREES);
        AngleUnit defaultConverterUnit = LongitudePlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = longitude.getInUnit(defaultConverterUnit);

        LongitudePlainSiConverter converter = new LongitudePlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(longitude);
        Longitude actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));
        
        assertThat(actualQuantityFromDB.getInDegrees()).isEqualTo(longitude.getInDegrees(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("Bearing Plain SI Converter: should successfully convert Bearing")
    void shouldSuccessfullyConvertBearingTest() {
        // Given
        Bearing bearing = Bearing.of(180.0);
        AngleUnit defaultConverterUnit = BearingPlainSiConverter.DEFAULT_SI_UNIT;
        double expectedValueFromDB = bearing.getInUnit(defaultConverterUnit);

        BearingPlainSiConverter converter = new BearingPlainSiConverter();

        // When
        Double actualValueToBePersistedInDB = converter.convertToDatabaseColumn(bearing);
        Bearing actualQuantityFromDB = converter.convertToEntityAttribute(expectedValueFromDB);

        // Then
        assertThat(actualValueToBePersistedInDB).isNotNull();
        assertThat(actualValueToBePersistedInDB).isEqualTo(expectedValueFromDB, withPrecision(1E-11));

        assertThat(actualQuantityFromDB.getInDegrees()).isEqualTo(bearing.getInDegrees(), withPrecision(1E-11));
    }

    @Test
    @DisplayName("SDR Converter: should successfully convert geo coordinate")
    void shouldSuccessfullyConvertGeoCoordinate() {
        // Given
        GeoCoordinate geoCoordinate = GeoCoordinate.of(
                Latitude.of(90.0, AngleUnits.DEGREES),
                Longitude.of(40.0, AngleUnits.DEGREES)
        );

        String decimalDegreesFormat = geoCoordinate.toDecimalDegrees();

        GeoCoordinateToStringConverter converter = new GeoCoordinateToStringConverter();
        String expectedPositionInDatabase = converter.convertToDatabaseColumn(geoCoordinate);

        assertThat(expectedPositionInDatabase).isNotNull().isEqualTo(decimalDegreesFormat, withPrecision(1E-11));

        GeoCoordinate geoCoordinateFromDB = converter.convertToEntityAttribute(decimalDegreesFormat);
        assertThat(geoCoordinateFromDB).isNotNull().isEqualTo(geoCoordinate);

    }

    @Test
    @DisplayName("GeoDistance Converter: should successfully convert geo distance")
    void shouldSuccessfullyConvertGeoDistance() {
        // Given
        GeoCoordinate startCoordinate = GeoCoordinate.of(
                Latitude.of(90.0, AngleUnits.DEGREES),
                Longitude.of(40.0, AngleUnits.DEGREES)
        );

        GeoCoordinate targetCoordinate = GeoCoordinate.of(
                Latitude.of(40.0, AngleUnits.DEGREES),
                Longitude.of(90.0, AngleUnits.DEGREES)
        );

        GeoDistance expectedGeoDistance = GeoDistance.ofKilometers(startCoordinate, targetCoordinate);
        double trueDistance = expectedGeoDistance.getValue();

        GeoDistanceToStringConverter converter = new GeoDistanceToStringConverter();
        String actualDistanceFromDB = converter.convertToDatabaseColumn(expectedGeoDistance);

        GeoDistance convertedGeoDistance = converter.convertToEntityAttribute(actualDistanceFromDB);

        assertThat(convertedGeoDistance.getValue()).isEqualTo(trueDistance, withPrecision(1E-11));

    }
    
    @Test
    void shouldFindExactly44JavaFilesRecursivelyInTheSpecifiedFolder() {
        String userDir = System.getProperty("user.dir");
        File folder = new File(userDir, FOLDER_PATH);

        Assertions.assertTrue(folder.exists(), "Source folder does not exist: " + folder.getAbsolutePath());
        Assertions.assertTrue(folder.isDirectory(), "Path does not lead to a directory: " + folder.getAbsolutePath());

        List<File> javaFiles = new ArrayList<>();
        countJavaFilesInDirectory(folder, javaFiles);

        int expectedNumberOfConverters = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
                .getClassRegistry().size() + 2;  // + 2 because GeoDistance and GeoCoordinate is not in parsing factory, but converters are provided

        System.out.println("Number of .java files found: " + javaFiles.size());
        Assertions.assertEquals(expectedNumberOfConverters, javaFiles.size(),
                "Expected number of .java files is " + expectedNumberOfConverters + ", but found " + javaFiles.size() + ".");
    }

    /**
     * Recursively counts all .java files in the given directory and its subdirectories.
     *
     * @param directory The directory to search.
     * @param fileList  The list to which the found files will be added.
     */
    private void countJavaFilesInDirectory(File directory, List<File> fileList) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    countJavaFilesInDirectory(file, fileList);
                } else if (file.getName().endsWith(".java")) {
                    fileList.add(file);
                }
            }
        }
    }

}
