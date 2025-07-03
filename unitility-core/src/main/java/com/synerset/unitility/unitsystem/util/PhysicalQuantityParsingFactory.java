package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * The PhysicalQuantityParsingRegistry interface defines a registry for parsing and creating instances of
 * PhysicalQuantity based on their symbolic representation.
 */
public interface PhysicalQuantityParsingFactory {

    Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry();

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
    <U extends Unit, Q extends PhysicalQuantity<U>> Q parse(Class<Q> targetClass, String quantityInEngFormat);

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
    <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueAndSymbol(Class<Q> targetClass, double value, String symbolAsString);

    /**
     * Retrieves the default unit for the specified physical quantity class.
     *
     * @param targetClass The class of the physical quantity.
     * @param <U>         The type of unit associated with the physical quantity.
     * @param <Q>         The type of physical quantity.
     * @return The default unit for the specified physical quantity class.
     * @throws UnitSystemClassNotSupportedException If the provided class is not registered in the registry.
     */
    <U extends Unit, Q extends PhysicalQuantity<U>> U getDefaultUnit(Class<Q> targetClass);

    /**
     * Creates a PhysicalQuantity instance from the provided target class and value, assuming predefined default unit.
     *
     * @param targetClass    The class of the physical quantity.
     * @param value          The value of the physical quantity.
     * @param <U>            The type of unit associated with the physical quantity.
     * @param <Q>            The type of physical quantity.
     * @return A new instance of the specified physical quantity.
     * @throws UnitSystemClassNotSupportedException If the provided class is not registered in the registry.
     */
    <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueWithDefaultUnit(Class<Q> targetClass, double value);

    /**
     * Checks if the target class is registered in parsing registry.
     *
     * @param targetClass The class to check for in the registry.
     * @return True if the class is registered, false otherwise.
     */
    boolean containsClass(Class<?> targetClass);

    /**
     * Retrieves a set of all registered physical quantity classes.
     *
     * @param <U> The type of unit associated with the physical quantity.
     * @param <Q> The type of physical quantity.
     * @return A set containing all registered physical quantity classes.
     */
    <U extends Unit, Q extends PhysicalQuantity<U>> Set<Class<Q>> findAllRegisteredClasses();

    /**
     * Retrieves a map of all default units for each physical quantity class registered in the factory.
     *
     * @return A map where the key is the physical quantity class and the value is its default unit.
     */
    Map<Class<?>, Unit> getDefaultUnitRegistry();

    static PhysicalQuantityParsingFactory getDefaultParsingFactory(){
        return PhysicalQuantityDefaultParsingFactory.getInstance();
    }

}
