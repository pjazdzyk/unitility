package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.utils.StringTransformer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * The PhysicalQuantityParsingRegistry interface defines a registry for parsing and creating instances of
 * PhysicalQuantity based on their symbolic representation.
 */
public interface PhysicalQuantityParsingFactory {

    PhysicalQuantityParsingFactory DEFAULT_PARSING_FACTORY = new PhysicalQuantityDefaultParsingFactory();
    GeoQuantityParsingFactory GEO_PARSING_FACTORY = new GeoQuantityParsingFactory();

    /**
     * Retrieves the registry map that maps physical quantity classes to functions capable of creating instances
     * from a value and a symbol.
     *
     * @return The registry map.
     */
    Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry();

    /**
     * Creates a PhysicalQuantity instance from the provided target class, value, and unit symbol.
     *
     * @param targetClass    The class of the physical quantity.
     * @param value          The value of the physical quantity.
     * @param symbolAsString The symbolic representation of the unit.
     * @param <U>            The type of unit associated with the physical quantity.
     * @param <Q>            The type of physical quantity.
     * @return A new instance of the specified physical quantity.
     * @throws UnitSystemClassNotSupportedException If the provided class is not registered in the registry.
     */
    default <U extends Unit, Q extends PhysicalQuantity<U>> Q parseFromSymbol(Class<Q> targetClass,
                                                                              double value,
                                                                              String symbolAsString) {
        validateIfClassIsRegistered(targetClass);
        return targetClass.cast(getClassRegistry().get(targetClass).apply(value, symbolAsString));
    }

    /**
     * Creates a PhysicalQuantity instance from the provided class and a string engineering format (20.0[C]).
     *
     * @param targetClass         The class of the physical quantity.
     * @param quantityInEngFormat The string representation of the quantity in engineering format, i.e.: 20.0[C]
     * @param <U>                 The type of unit associated with the physical quantity.
     * @param <Q>                 The type of physical quantity.
     * @return A new instance of the specified physical quantity.
     * @throws UnitSystemParseException If parsing the English format fails.
     */
    default <U extends Unit, Q extends PhysicalQuantity<U>> Q parseFromEngFormat(Class<Q> targetClass,
                                                                                 String quantityInEngFormat) {

        String preparedSource = StringTransformer.of(quantityInEngFormat)
                .trimLowerAndClean()
                .toString();

        String unitSymbol = null;
        if (preparedSource.contains("[")) {
            unitSymbol = extractSymbolFromEngFormat(targetClass, preparedSource);
        }
        double value = extractValueFromEngFormat(targetClass, preparedSource);
        return parseFromSymbol(targetClass, value, unitSymbol);
    }

    /**
     * Checks if the target class is registered in parsing registry.
     *
     * @param targetClass The class to check for in the registry.
     * @return True if the class is registered, false otherwise.
     */
    default boolean containsClass(Class<?> targetClass) {
        return getClassRegistry().containsKey(targetClass);
    }

    /**
     * Retrieves a set of all registered physical quantity classes.
     *
     * @param <U> The type of unit associated with the physical quantity.
     * @param <Q> The type of physical quantity.
     * @return A set containing all registered physical quantity classes.
     */
    @SuppressWarnings("unchecked")
    default <U extends Unit, Q extends PhysicalQuantity<U>> Set<Class<Q>> findAllRegisteredClasses() {
        Set<Class<Q>> quantityClasses = new HashSet<>();
        getClassRegistry().keySet().forEach(quantityClass -> quantityClasses.add((Class<Q>) quantityClass));
        return quantityClasses;
    }

    private <U extends Unit, Q extends PhysicalQuantity<U>> void validateIfClassIsRegistered(Class<Q> targetClass) {
        if (!containsClass(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Class not found in the registry: " + targetClass.getSimpleName());
        }
    }

    private double extractValueFromEngFormat(Class<?> targetClass, String inputString) {
        String perparedString = StringTransformer.of(inputString)
                .replaceCommaForDot()
                .toString();

        String extractedNumber;
        if (perparedString.contains("[")) {
            int endIndex = perparedString.indexOf('[');
            extractedNumber = perparedString.substring(0, endIndex);
        } else {
            extractedNumber = perparedString;
        }

        try {
            return Double.parseDouble(extractedNumber);
        } catch (Exception e) {
            throw new UnitSystemParseException("Could not extract number from input: " + perparedString
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

    private String extractSymbolFromEngFormat(Class<?> targetClass, String input) {
        int startIndex = input.indexOf('[');
        int endIndex = input.indexOf(']', startIndex);
        if (startIndex != -1 && endIndex != -1) {
            return input.substring(startIndex + 1, endIndex);
        } else {
            throw new UnitSystemParseException("Invalid input string. Could not extract unit symbol from: " + input
                    + ", target class: " + targetClass.getSimpleName());
        }
    }

}