package com.synerset.unitility.unitsystem.utils;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public interface PhysicalQuantityParsingRegistry {

    Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getRegistryMap();

    default <U extends Unit, Q extends PhysicalQuantity<U>> Q createFromSymbol(Class<Q> targetClass,
                                                                               double value,
                                                                               String symbolAsString) {
        validateIfClassIsRegistered(targetClass);
        return targetClass.cast(getRegistryMap().get(targetClass).apply(value, symbolAsString));
    }

    default <U extends Unit, Q extends PhysicalQuantity<U>> Q createFromEngFormat(Class<Q> targetClass,
                                                                                  String quantityInEngFormat) {

        String preparedSource = quantityInEngFormat.trim()
                .replace(" ", "")
                .replace(",", ".");
        String unitSymbol = null;
        if (preparedSource.contains("[")) {
            unitSymbol = extractSymbolFromEngFormat(targetClass, preparedSource);
        }
        double value = extractValueFromEngFormat(targetClass, preparedSource);
        return createFromSymbol(targetClass, value, unitSymbol);
    }

    default <U extends Unit, Q extends PhysicalQuantity<U>> void overrideQuantityClass(Class<Q> quantityClass,
                                                                                       BiFunction<Double, String, Q> factoryFunction) {
        getRegistryMap().put(quantityClass, factoryFunction);
    }

    default boolean containsClass(Class<?> targetClass) {
        return getRegistryMap().containsKey(targetClass);
    }

    @SuppressWarnings("unchecked")
    default <U extends Unit, Q extends PhysicalQuantity<U>> Set<Class<Q>> findAllRegisteredClasses() {
        Set<Class<Q>> quantityClasses = new HashSet<>();
        getRegistryMap().keySet().forEach(clazz -> quantityClasses.add((Class<Q>) clazz));
        return quantityClasses;
    }

    private <U extends Unit, Q extends PhysicalQuantity<U>> void validateIfClassIsRegistered(Class<Q> targetClass) {
        if (!containsClass(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Class not found in the registry: " + targetClass.getSimpleName());
        }
    }

    private double extractValueFromEngFormat(Class<?> targetClass, String input) {
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

    private String extractSymbolFromEngFormat(Class<?> targetClass, String input) {
        int startIndex = input.indexOf('[');
        int endIndex = input.indexOf(']', startIndex);
        if (startIndex != -1 && endIndex != -1) {
            return input.substring(startIndex + 1, endIndex);
        } else {
            throw new UnitSystemParseException("Could not extract unit symbol from input: " + input
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

    static PhysicalQuantityParsingRegistry createNewDefaultRegistry() {
        return new PhysicalQuantityDefaultParsingRegistry();
    }

}
