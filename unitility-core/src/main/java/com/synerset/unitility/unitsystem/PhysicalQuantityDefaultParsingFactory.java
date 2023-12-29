package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumber;
import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumber;
import com.synerset.unitility.unitsystem.flows.MassFlow;
import com.synerset.unitility.unitsystem.flows.VolumetricFlow;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.Momentum;
import com.synerset.unitility.unitsystem.mechanical.Torque;
import com.synerset.unitility.unitsystem.thermodynamic.*;

import java.util.Map;
import java.util.function.BiFunction;

final class PhysicalQuantityDefaultParsingFactory implements PhysicalQuantityParsingFactory {

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> immutableRegistry;

    PhysicalQuantityDefaultParsingFactory() {
        // Initializing immutable registry
        this.immutableRegistry = Map.ofEntries(
                // Common
                Map.entry(Angle.class, Angle::of),
                Map.entry(Area.class, Area::of),
                Map.entry(Distance.class, Distance::of),
                Map.entry(Mass.class, Mass::of),
                Map.entry(Velocity.class, Velocity::of),
                Map.entry(Volume.class, Volume::of),
                // Dimensionless
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
                Map.entry(SpecificHeat.class, SpecificHeat::of),
                Map.entry(Temperature.class, Temperature::of),
                Map.entry(ThermalConductivity.class, ThermalConductivity::of),
                Map.entry(ThermalDiffusivity.class, ThermalDiffusivity::of));
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry() {
        return immutableRegistry;
    }

}