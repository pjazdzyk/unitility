package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumber;
import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumber;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

class PhysicalQuantityParsingFactory {

    private static final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> symbolCreatorsByClassRegistry;
    private static final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> unitCreatorsByClassRegistry;

    static {
        // Initializing HashMaps:
        symbolCreatorsByClassRegistry = new ConcurrentHashMap<>();
        unitCreatorsByClassRegistry = new ConcurrentHashMap<>();
        // Common
        registerClass(Angle.class,
                (value, symbol) -> Angle.of(value, AngleUnits.fromSymbol(symbol)),
                (value, unit) -> Angle.of(value, AngleUnits.valueOf(formatUnit(unit))));
        registerClass(Area.class,
                (value, symbol) -> Area.of(value, AreaUnits.fromSymbol(symbol)),
                (value, unit) -> Area.of(value, AreaUnits.valueOf(formatUnit(unit))));
        registerClass(Distance.class,
                (value, symbol) -> Distance.of(value, DistanceUnits.fromSymbol(symbol)),
                (value, unit) -> Distance.of(value, DistanceUnits.valueOf(formatUnit(unit))));
        registerClass(Mass.class,
                (value, symbol) -> Mass.of(value, MassUnits.fromSymbol(symbol)),
                (value, unit) -> Mass.of(value, MassUnits.valueOf(formatUnit(unit))));
        registerClass(Velocity.class,
                (value, symbol) -> Velocity.of(value, VelocityUnits.fromSymbol(symbol)),
                (value, unit) -> Velocity.of(value, VelocityUnits.valueOf(formatUnit(unit))));
        registerClass(Volume.class,
                (value, symbol) -> Volume.of(value, VolumeUnits.fromSymbol(symbol)),
                (value, unit) -> Volume.of(value, VolumeUnits.valueOf(formatUnit(unit))));
        // Dimensionless
        registerClass(BypassFactor.class,
                (value, symbol) -> BypassFactor.of(value),
                (value, unit) -> BypassFactor.of(value));
        registerClass(GrashofNumber.class,
                (value, symbol) -> GrashofNumber.of(value),
                (value, unit) -> GrashofNumber.of(value));
        registerClass(PrandtlNumber.class,
                (value, symbol) -> PrandtlNumber.of(value),
                (value, unit) -> PrandtlNumber.of(value));
        registerClass(ReynoldsNumber.class,
                (value, symbol) -> ReynoldsNumber.of(value),
                (value, unit) -> ReynoldsNumber.of(value));
        // Flows
        registerClass(MassFlow.class,
                (value, symbol) -> MassFlow.of(value, MassFlowUnits.fromSymbol(symbol)),
                (value, unit) -> MassFlow.of(value, MassFlowUnits.valueOf(formatUnit(unit))));
        registerClass(VolumetricFlow.class,
                (value, symbol) -> VolumetricFlow.of(value, VolumetricFlowUnits.fromSymbol(symbol)),
                (value, unit) -> VolumetricFlow.of(value, VolumetricFlowUnits.valueOf(formatUnit(unit))));
        // Humidity
        registerClass(HumidityRatio.class,
                (value, symbol) -> HumidityRatio.of(value, HumidityRatioUnits.fromSymbol(symbol)),
                (value, unit) -> HumidityRatio.of(value, HumidityRatioUnits.valueOf(formatUnit(unit))));
        registerClass(RelativeHumidity.class,
                (value, symbol) -> RelativeHumidity.of(value, RelativeHumidityUnits.fromSymbol(symbol)),
                (value, unit) -> RelativeHumidity.of(value, RelativeHumidityUnits.valueOf(formatUnit(unit))));
        // Mechanical
        registerClass(Force.class,
                (value, symbol) -> Force.of(value, ForceUnits.fromSymbol(symbol)),
                (value, unit) -> Force.of(value, ForceUnits.valueOf(formatUnit(unit))));
        registerClass(Momentum.class,
                (value, symbol) -> Momentum.of(value, MomentumUnits.fromSymbol(symbol)),
                (value, unit) -> Momentum.of(value, MomentumUnits.valueOf(formatUnit(unit))));
        registerClass(Torque.class,
                (value, symbol) -> Torque.of(value, TorqueUnits.fromSymbol(symbol)),
                (value, unit) -> Torque.of(value, TorqueUnits.valueOf(formatUnit(unit))));
        // Thermodynamic
        registerClass(Density.class,
                (value, symbol) -> Density.of(value, DensityUnits.fromSymbol(symbol)),
                (value, unit) -> Density.of(value, DensityUnits.valueOf(formatUnit(unit))));
        registerClass(DynamicViscosity.class,
                (value, symbol) -> DynamicViscosity.of(value, DynamicViscosityUnits.fromSymbol(symbol)),
                (value, unit) -> DynamicViscosity.of(value, DynamicViscosityUnits.valueOf(formatUnit(unit))));
        registerClass(Energy.class,
                (value, symbol) -> Energy.of(value, EnergyUnits.fromSymbol(symbol)),
                (value, unit) -> Energy.of(value, EnergyUnits.valueOf(formatUnit(unit))));
        registerClass(KinematicViscosity.class,
                (value, symbol) -> KinematicViscosity.of(value, KinematicViscosityUnits.fromSymbol(symbol)),
                (value, unit) -> KinematicViscosity.of(value, KinematicViscosityUnits.valueOf(formatUnit(unit))));
        registerClass(Power.class,
                (value, symbol) -> Power.of(value, PowerUnits.fromSymbol(symbol)),
                (value, unit) -> Power.of(value, PowerUnits.valueOf(formatUnit(unit))));
        registerClass(Pressure.class,
                (value, symbol) -> Pressure.of(value, PressureUnits.fromSymbol(symbol)),
                (value, unit) -> Pressure.of(value, PressureUnits.valueOf(formatUnit(unit))));
        registerClass(SpecificEnthalpy.class,
                (value, symbol) -> SpecificEnthalpy.of(value, SpecificEnthalpyUnits.fromSymbol(symbol)),
                (value, unit) -> SpecificEnthalpy.of(value, SpecificEnthalpyUnits.valueOf(formatUnit(unit))));
        registerClass(SpecificHeat.class,
                (value, symbol) -> SpecificHeat.of(value, SpecificHeatUnits.fromSymbol(symbol)),
                (value, unit) -> SpecificHeat.of(value, SpecificHeatUnits.valueOf(formatUnit(unit))));
        registerClass(Temperature.class,
                (value, symbol) -> Temperature.of(value, TemperatureUnits.fromSymbol(symbol)),
                (value, unit) -> Temperature.of(value, TemperatureUnits.valueOf(formatUnit(unit))));
        registerClass(ThermalConductivity.class,
                (value, symbol) -> ThermalConductivity.of(value, ThermalConductivityUnits.fromSymbol(symbol)),
                (value, unit) -> ThermalConductivity.of(value, ThermalConductivityUnits.valueOf(formatUnit(unit))));
        registerClass(ThermalDiffusivity.class,
                (value, symbol) -> ThermalDiffusivity.of(value, ThermalDiffusivityUnits.fromSymbol(symbol)),
                (value, unit) -> ThermalDiffusivity.of(value, ThermalDiffusivityUnits.valueOf(formatUnit(unit))));
    }

    private PhysicalQuantityParsingFactory() {
    }

    static <U extends Unit, Q extends PhysicalQuantity<U>> Q createParsingFromSymbol(Class<Q> clazz, double value,
                                                                                     String symbolAsString) {
        validateIfClassIsRegistered(clazz);
        return clazz.cast(symbolCreatorsByClassRegistry.get(clazz).apply(value, symbolAsString));
    }

    static <U extends Unit, Q extends PhysicalQuantity<U>> Q createParsingFromUnit(Class<Q> clazz, double value,
                                                                                   String unitAsString) {
        validateIfClassIsRegistered(clazz);
        return clazz.cast(unitCreatorsByClassRegistry.get(clazz).apply(value, unitAsString));
    }

    private static String formatUnit(String unitAsString) {
        return unitAsString
                .trim()
                .replace(" ", "_")
                .toUpperCase();
    }

    private static <U extends Unit, Q extends PhysicalQuantity<U>> void registerClass(Class<Q> clazz,
                                                                                      BiFunction<Double, String, Q> symbolFunction,
                                                                                      BiFunction<Double, String, Q> unitFunction) {
        symbolCreatorsByClassRegistry.put(clazz, symbolFunction);
        unitCreatorsByClassRegistry.put(clazz, unitFunction);
    }

    private static <U extends Unit, Q extends PhysicalQuantity<U>> void validateIfClassIsRegistered(Class<Q> clazz) {
        if (!symbolCreatorsByClassRegistry.containsKey(clazz) || !unitCreatorsByClassRegistry.containsKey(clazz)) {
            throw new UnitSystemArgumentException("Class not found in the registry: " + clazz.getSimpleName());
        }
    }

}