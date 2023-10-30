package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumber;
import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumber;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.flows.MassFlow;
import com.synerset.unitility.unitsystem.flows.VolumetricFlow;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.Momentum;
import com.synerset.unitility.unitsystem.mechanical.Torque;
import com.synerset.unitility.unitsystem.thermodynamic.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class PhysicalQuantityParsingFactory {

    private static final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> REGISTRY;

    static {
        // Initializing HashMaps:
        REGISTRY = new ConcurrentHashMap<>();

        // Common
        REGISTRY.put(Angle.class, Angle::of);
        REGISTRY.put(Area.class, Area::of);
        REGISTRY.put(Distance.class, Distance::of);
        REGISTRY.put(Mass.class, Mass::of);
        REGISTRY.put(Velocity.class, Velocity::of);
        REGISTRY.put(Volume.class, Volume::of);
        // Dimensionless
        REGISTRY.put(BypassFactor.class, (value, symbol) -> BypassFactor.of(value));
        REGISTRY.put(GrashofNumber.class, (value, symbol) -> GrashofNumber.of(value));
        REGISTRY.put(PrandtlNumber.class, (value, symbol) -> PrandtlNumber.of(value));
        REGISTRY.put(ReynoldsNumber.class, (value, symbol) -> ReynoldsNumber.of(value));
        // Flows
        REGISTRY.put(MassFlow.class, MassFlow::of);
        REGISTRY.put(VolumetricFlow.class, VolumetricFlow::of);
        // Humidity
        REGISTRY.put(HumidityRatio.class, HumidityRatio::of);
        REGISTRY.put(RelativeHumidity.class, RelativeHumidity::of);
        // Mechanical
        REGISTRY.put(Force.class, Force::of);
        REGISTRY.put(Momentum.class, Momentum::of);
        REGISTRY.put(Torque.class, Torque::of);
        // Thermodynamic
        REGISTRY.put(Density.class, Density::of);
        REGISTRY.put(DynamicViscosity.class, DynamicViscosity::of);
        REGISTRY.put(Energy.class, Energy::of);
        REGISTRY.put(KinematicViscosity.class, KinematicViscosity::of);
        REGISTRY.put(Power.class, Power::of);
        REGISTRY.put(Pressure.class, Pressure::of);
        REGISTRY.put(SpecificEnthalpy.class, SpecificEnthalpy::of);
        REGISTRY.put(SpecificHeat.class, SpecificHeat::of);
        REGISTRY.put(Temperature.class, Temperature::of);
        REGISTRY.put(ThermalConductivity.class, ThermalConductivity::of);
        REGISTRY.put(ThermalDiffusivity.class, ThermalDiffusivity::of);
    }

    private PhysicalQuantityParsingFactory() {
    }

    // Json format converters
    public static <U extends Unit, Q extends PhysicalQuantity<U>> Q createParsingFromSymbol(Class<Q> targetClass, double value,
                                                                                     String symbolAsString) {
        validateIfClassIsRegistered(targetClass);
        return targetClass.cast(REGISTRY.get(targetClass).apply(value, symbolAsString));
    }

    private static <U extends Unit, Q extends PhysicalQuantity<U>> void validateIfClassIsRegistered(Class<Q> targetClass) {
        if (!REGISTRY.containsKey(targetClass)) {
            throw new UnitSystemArgumentException("Class not found in the registry: " + targetClass.getSimpleName());
        }
    }

}