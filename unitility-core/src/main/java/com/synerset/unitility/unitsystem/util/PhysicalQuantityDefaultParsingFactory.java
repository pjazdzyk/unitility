package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.acoustic.SoundPower;
import com.synerset.unitility.unitsystem.acoustic.SoundPowerUnits;
import com.synerset.unitility.unitsystem.acoustic.SoundPressure;
import com.synerset.unitility.unitsystem.acoustic.SoundPressureUnits;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.*;
import com.synerset.unitility.unitsystem.electric.*;
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
import com.synerset.unitility.unitsystem.oscillation.Frequency;
import com.synerset.unitility.unitsystem.oscillation.FrequencyUnits;
import com.synerset.unitility.unitsystem.thermodynamic.*;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificEntropy;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificEntropyUnits;
import com.synerset.unitility.unitsystem.thermodynamic.IsothermalCompressibility;

import java.util.Map;
import java.util.function.BiFunction;

final class PhysicalQuantityDefaultParsingFactory extends PhysicalQuantityAbstractParsingFactory {

    private static final PhysicalQuantityParsingFactory PARSING_FACTORY = new PhysicalQuantityDefaultParsingFactory();

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> immutableParsingRegistry;

    private final Map<Class<?>, Unit> immutableDefaultUnitRegistry;

    private PhysicalQuantityDefaultParsingFactory() {
        // Initializing immutable registry
        this.immutableParsingRegistry = Map.<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>>ofEntries(
                // Common
                Map.entry(Angle.class, Angle::of),
                Map.entry(Area.class, Area::of),
                Map.entry(Distance.class, Distance::of),
                Map.entry(Length.class, Length::of),
                Map.entry(Width.class, Width::of),
                Map.entry(Height.class, Height::of),
                Map.entry(Diameter.class, Diameter::of),
                Map.entry(Perimeter.class, Perimeter::of),
                Map.entry(Thickness.class, Thickness::of),
                Map.entry(Mass.class, Mass::of),
                Map.entry(LinearMassDensity.class, LinearMassDensity::of),
                Map.entry(Velocity.class, Velocity::of),
                Map.entry(AngularVelocity.class, AngularVelocity::of),
                Map.entry(Volume.class, Volume::of),
                Map.entry(Ratio.class, Ratio::of),
                Map.entry(Curvature.class, Curvature::of),
                Map.entry(DataSize.class, DataSize::of),
                // Dimensionless
                Map.entry(GenericDimensionless.class, (value, symbol) -> GenericDimensionless.of(value)),
                Map.entry(BypassFactor.class, (value, symbol) -> BypassFactor.of(value)),
                Map.entry(GrashofNumber.class, (value, symbol) -> GrashofNumber.of(value)),
                Map.entry(PrandtlNumber.class, (value, symbol) -> PrandtlNumber.of(value)),
                Map.entry(ReynoldsNumber.class, (value, symbol) -> ReynoldsNumber.of(value)),
                // Flows
                Map.entry(MassFlow.class, MassFlow::of),
                Map.entry(VolumetricFlow.class, VolumetricFlow::of),
                // Humidity
                Map.entry(HumidityRatio.class, HumidityRatio::of),
                Map.entry(RelativeHumidity.class, RelativeHumidity::of),
                // Hydraulic
                Map.entry(LinearResistance.class, LinearResistance::of),
                Map.entry(FrictionFactor.class, (value, symbol) -> FrictionFactor.of(value)),
                Map.entry(LocalLossFactor.class, (value, symbol) -> LocalLossFactor.of(value)),
                Map.entry(RotationSpeedToFlowRateRatio.class, RotationSpeedToFlowRateRatio::of),
                Map.entry(SDR.class, SDR::of),
                Map.entry(AbsoluteRoughness.class, AbsoluteRoughness::of),
                // Mechanical
                Map.entry(Force.class, Force::of),
                Map.entry(Momentum.class, Momentum::of),
                Map.entry(Torque.class, Torque::of),
                // Thermodynamic
                Map.entry(Density.class, Density::of),
                Map.entry(DynamicViscosity.class, DynamicViscosity::of),
                Map.entry(Energy.class, Energy::of),
                Map.entry(KinematicViscosity.class, KinematicViscosity::of),
                Map.entry(Power.class, Power::of),
                Map.entry(Pressure.class, Pressure::of),
                Map.entry(SpecificEnthalpy.class, SpecificEnthalpy::of),
                Map.entry(SpecificEntropy.class, SpecificEntropy::of),
                Map.entry(SpecificHeat.class, SpecificHeat::of),
                Map.entry(Temperature.class, Temperature::of),
                Map.entry(ThermalConductivity.class, ThermalConductivity::of),
                Map.entry(ThermalDiffusivity.class, ThermalDiffusivity::of),
                Map.entry(IsothermalCompressibility.class, IsothermalCompressibility::of),
                // Geographic
                Map.entry(Latitude.class, Latitude::of),
                Map.entry(Longitude.class, Longitude::of),
                Map.entry(Bearing.class, Bearing::of),
                // Acoustic
                Map.entry(SoundPower.class, SoundPower::of),
                Map.entry(SoundPressure.class, SoundPressure::of),
                // Oscillation
                Map.entry(Frequency.class, Frequency::of),
                // Electric
                Map.entry(Capacitance.class, Capacitance::of),
                Map.entry(Charge.class, Charge::of),
                Map.entry(Conductance.class, Conductance::of),
                Map.entry(Current.class, Current::of),
                Map.entry(Resistance.class, Resistance::of),
                Map.entry(Voltage.class, Voltage::of)
        );

        // Initializing immutable default unit registry
        this.immutableDefaultUnitRegistry = Map.<Class<?>, Unit>ofEntries(
                // Common (16)
                Map.entry(Angle.class, AngleUnits.RADIANS),
                Map.entry(AngularVelocity.class, AngularVelocityUnits.RADIANS_PER_SECOND),
                Map.entry(Area.class, AreaUnits.SQUARE_METER),
                Map.entry(Curvature.class, CurvatureUnits.RADIANS_PER_METER),
                Map.entry(Distance.class, DistanceUnits.METER),
                Map.entry(Diameter.class, DistanceUnits.METER),
                Map.entry(Height.class, DistanceUnits.METER),
                Map.entry(Length.class, DistanceUnits.METER),
                Map.entry(Width.class, DistanceUnits.METER),
                Map.entry(LinearMassDensity.class, LinearMassDensityUnits.KILOGRAM_PER_METER),
                Map.entry(Mass.class, MassUnits.KILOGRAM),
                Map.entry(Perimeter.class, DistanceUnits.METER),
                Map.entry(Ratio.class, RatioUnits.PERCENT),
                Map.entry(Thickness.class, DistanceUnits.METER),
                Map.entry(Velocity.class, VelocityUnits.METER_PER_SECOND),
                Map.entry(Volume.class, VolumeUnits.CUBIC_METER),
                Map.entry(DataSize.class, DataSizeUnits.BYTE),
                // Dimensionless (5)
                Map.entry(GenericDimensionless.class, GenericDimensionlessUnits.DIMENSIONLESS),
                Map.entry(BypassFactor.class, BypassFactorUnits.DIMENSIONLESS),
                Map.entry(GrashofNumber.class, GrashofNumberUnits.DIMENSIONLESS),
                Map.entry(PrandtlNumber.class, PrandtlNumberUnits.DIMENSIONLESS),
                Map.entry(ReynoldsNumber.class, ReynoldsNumberUnits.DIMENSIONLESS),
                // Flows (2)
                Map.entry(MassFlow.class, MassFlowUnits.KILOGRAM_PER_SECOND),
                Map.entry(VolumetricFlow.class, VolumetricFlowUnits.CUBIC_METERS_PER_SECOND),
                // Humidity (2)
                Map.entry(HumidityRatio.class, HumidityRatioUnits.KILOGRAM_PER_KILOGRAM),
                Map.entry(RelativeHumidity.class, RelativeHumidityUnits.DECIMAL),
                // Hydraulic (5)
                Map.entry(AbsoluteRoughness.class, DistanceUnits.METER),
                Map.entry(FrictionFactor.class, FrictionFactorUnits.DIMENSIONLESS),
                Map.entry(LinearResistance.class, LinearResistanceUnits.PASCAL_PER_METER),
                Map.entry(LocalLossFactor.class, LocalLossFactorUnits.DIMENSIONLESS),
                Map.entry(RotationSpeedToFlowRateRatio.class, RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND),
                Map.entry(SDR.class, RatioUnits.DECIMAL),
                // Mechanical (3)
                Map.entry(Force.class, ForceUnits.NEWTON),
                Map.entry(Momentum.class, MomentumUnits.KILOGRAM_METER_PER_SECOND),
                Map.entry(Torque.class, TorqueUnits.NEWTON_METER),
                // Thermodynamic (13)
                Map.entry(Density.class, DensityUnits.KILOGRAM_PER_CUBIC_METER),
                Map.entry(DynamicViscosity.class, DynamicViscosityUnits.PASCAL_SECOND),
                Map.entry(Energy.class, EnergyUnits.JOULE),
                Map.entry(KinematicViscosity.class, KinematicViscosityUnits.SQUARE_METER_PER_SECOND),
                Map.entry(Power.class, PowerUnits.WATT),
                Map.entry(Pressure.class, PressureUnits.PASCAL),
                Map.entry(SpecificEnthalpy.class, SpecificEnthalpyUnits.JOULE_PER_KILOGRAM),
                Map.entry(SpecificEntropy.class, SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN),
                Map.entry(SpecificHeat.class, SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN),
                Map.entry(Temperature.class, TemperatureUnits.KELVIN),
                Map.entry(ThermalConductivity.class, ThermalConductivityUnits.WATTS_PER_METER_KELVIN),
                Map.entry(ThermalDiffusivity.class, ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND),
                Map.entry(IsothermalCompressibility.class, IsothermalCompressibilityUnits.INVERSE_PASCAL),
                // Geographic (3)
                Map.entry(Latitude.class, AngleUnits.DEGREES),
                Map.entry(Longitude.class, AngleUnits.DEGREES),
                Map.entry(Bearing.class, AngleUnits.DEGREES),
                // Acoustic (2)
                Map.entry(SoundPower.class, SoundPowerUnits.WATT),
                Map.entry(SoundPressure.class, SoundPressureUnits.PASCAL),
                // Oscillation (1)
                Map.entry(Frequency.class, FrequencyUnits.HERTZ),
                // Electric (6)
                Map.entry(Capacitance.class, CapacitanceUnits.FARAD),
                Map.entry(Charge.class, ChargeUnits.COULOMB),
                Map.entry(Conductance.class, ConductanceUnits.SIEMENS),
                Map.entry(Current.class, CurrentUnits.AMPERE),
                Map.entry(Resistance.class, ResistanceUnits.OHM),
                Map.entry(Voltage.class, VoltageUnits.VOLT)
        );
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry() {
        return immutableParsingRegistry;
    }

    @Override
    public Map<Class<?>, Unit> getDefaultUnitRegistry() {
        return immutableDefaultUnitRegistry;
    }

    static PhysicalQuantityParsingFactory getInstance() {
        return PARSING_FACTORY;
    }

}
