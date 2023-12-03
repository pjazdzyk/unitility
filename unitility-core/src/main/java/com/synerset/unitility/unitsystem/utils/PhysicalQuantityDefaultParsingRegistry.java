package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
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

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

final class PhysicalQuantityDefaultParsingRegistry implements PhysicalQuantityParsingRegistry {

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> immutableRegistry;

    public PhysicalQuantityDefaultParsingRegistry() {
        // Initializing HashMap
        Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> registry = new ConcurrentHashMap<>();
        // Common
        registry.put(Angle.class, Angle::of);
        registry.put(Area.class, Area::of);
        registry.put(Distance.class, Distance::of);
        registry.put(Mass.class, Mass::of);
        registry.put(Velocity.class, Velocity::of);
        registry.put(Volume.class, Volume::of);
        // Dimensionless
        registry.put(BypassFactor.class, (value, symbol) -> BypassFactor.of(value));
        registry.put(GrashofNumber.class, (value, symbol) -> GrashofNumber.of(value));
        registry.put(PrandtlNumber.class, (value, symbol) -> PrandtlNumber.of(value));
        registry.put(ReynoldsNumber.class, (value, symbol) -> ReynoldsNumber.of(value));
        // Flows
        registry.put(MassFlow.class, MassFlow::of);
        registry.put(VolumetricFlow.class, VolumetricFlow::of);
        // Humidity
        registry.put(HumidityRatio.class, HumidityRatio::of);
        registry.put(RelativeHumidity.class, RelativeHumidity::of);
        // Mechanical
        registry.put(Force.class, Force::of);
        registry.put(Momentum.class, Momentum::of);
        registry.put(Torque.class, Torque::of);
        // Thermodynamic
        registry.put(Density.class, Density::of);
        registry.put(DynamicViscosity.class, DynamicViscosity::of);
        registry.put(Energy.class, Energy::of);
        registry.put(KinematicViscosity.class, KinematicViscosity::of);
        registry.put(Power.class, Power::of);
        registry.put(Pressure.class, Pressure::of);
        registry.put(SpecificEnthalpy.class, SpecificEnthalpy::of);
        registry.put(SpecificHeat.class, SpecificHeat::of);
        registry.put(Temperature.class, Temperature::of);
        registry.put(ThermalConductivity.class, ThermalConductivity::of);
        registry.put(ThermalDiffusivity.class, ThermalDiffusivity::of);
        // Creating immutable registry map
        this.immutableRegistry = Collections.unmodifiableMap(registry);
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getRegistryMap() {
        return immutableRegistry;
    }

}