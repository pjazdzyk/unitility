package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumber;
import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumber;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
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
        // Initializing HashMap
        REGISTRY = new ConcurrentHashMap<>();

        // Common
        registerQuantityClass(Angle.class, Angle::of);
        registerQuantityClass(Area.class, Area::of);
        registerQuantityClass(Distance.class, Distance::of);
        registerQuantityClass(Mass.class, Mass::of);
        registerQuantityClass(Velocity.class, Velocity::of);
        registerQuantityClass(Volume.class, Volume::of);
        // Dimensionless
        registerQuantityClass(BypassFactor.class, (value, symbol) -> BypassFactor.of(value));
        registerQuantityClass(GrashofNumber.class, (value, symbol) -> GrashofNumber.of(value));
        registerQuantityClass(PrandtlNumber.class, (value, symbol) -> PrandtlNumber.of(value));
        registerQuantityClass(ReynoldsNumber.class, (value, symbol) -> ReynoldsNumber.of(value));
        // Flows
        registerQuantityClass(MassFlow.class, MassFlow::of);
        registerQuantityClass(VolumetricFlow.class, VolumetricFlow::of);
        // Humidity
        registerQuantityClass(HumidityRatio.class, HumidityRatio::of);
        registerQuantityClass(RelativeHumidity.class, RelativeHumidity::of);
        // Mechanical
        registerQuantityClass(Force.class, Force::of);
        registerQuantityClass(Momentum.class, Momentum::of);
        registerQuantityClass(Torque.class, Torque::of);
        // Thermodynamic
        registerQuantityClass(Density.class, Density::of);
        registerQuantityClass(DynamicViscosity.class, DynamicViscosity::of);
        registerQuantityClass(Energy.class, Energy::of);
        registerQuantityClass(KinematicViscosity.class, KinematicViscosity::of);
        registerQuantityClass(Power.class, Power::of);
        registerQuantityClass(Pressure.class, Pressure::of);
        registerQuantityClass(SpecificEnthalpy.class, SpecificEnthalpy::of);
        registerQuantityClass(SpecificHeat.class, SpecificHeat::of);
        registerQuantityClass(Temperature.class, Temperature::of);
        registerQuantityClass(ThermalConductivity.class, ThermalConductivity::of);
        registerQuantityClass(ThermalDiffusivity.class, ThermalDiffusivity::of);
    }

    private PhysicalQuantityParsingFactory() {
        throw new IllegalStateException("Utility class.");
    }

    public static <U extends Unit, Q extends PhysicalQuantity<U>> Q createParsingFromSymbol(Class<Q> targetClass,
                                                                                            double value,
                                                                                            String symbolAsString) {
        validateIfClassIsRegistered(targetClass);
        return targetClass.cast(REGISTRY.get(targetClass).apply(value, symbolAsString));
    }

    public static <U extends Unit, Q extends PhysicalQuantity<U>> Q createParsingFromEngFormat(Class<Q> targetClass,
                                                                                               String quantityInEngFormat) {

        String preparedSource = quantityInEngFormat.trim()
                .replace(" ", "")
                .replace(",", ".");
        String unitSymbol = null;
        if (preparedSource.contains("[")) {
            unitSymbol = extractSymbolFromEngFormat(targetClass, preparedSource);
        }
        double value = extractValueFromEngFormat(targetClass, preparedSource);
        return createParsingFromSymbol(targetClass, value, unitSymbol);
    }

    private static <U extends Unit, Q extends PhysicalQuantity<U>> void registerQuantityClass(Class<Q> quantityClass,
                                                                                              BiFunction<Double, String, Q> factoryFunction) {
        if (!REGISTRY.containsKey(quantityClass)) {
            REGISTRY.put(quantityClass, factoryFunction);
        } else {
            throw new UnitSystemArgumentException("Class already registered: " + quantityClass.getSimpleName());
        }
    }

    private static <U extends Unit, Q extends PhysicalQuantity<U>> void validateIfClassIsRegistered(Class<Q> targetClass) {
        if (!REGISTRY.containsKey(targetClass)) {
            throw new UnitSystemArgumentException("Class not found in the registry: " + targetClass.getSimpleName());
        }
    }

    private static double extractValueFromEngFormat(Class<?> targetClass, String input) {
        String extractedNumber;

        if (input.contains("[")) {
            int endIndex = input.indexOf('[');
            extractedNumber = input.substring(0, endIndex);
        } else {
            extractedNumber = input;
        }

        try {
            return Double.parseDouble(extractedNumber);
        } catch (Exception e) {
            throw new UnitSystemParseException("Could not extract number from input: " + input
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

    private static String extractSymbolFromEngFormat(Class<?> targetClass, String input) {
        int startIndex = input.indexOf('[');
        int endIndex = input.indexOf(']', startIndex);
        if (startIndex != -1 && endIndex != -1) {
            return input.substring(startIndex + 1, endIndex);
        } else {
            throw new UnitSystemParseException("Could not extract unit symbol from input: " + input
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

}