/**
 * An interface representing a unit of measurement in a unit system.
 *
 * @param <Q> The quantity type associated with the unit (e.g., Length, Mass, Time). It is used to ensure proper unit type usage in physical quantities.
 */
package com.synerset.unitility.unitsystem;

public interface Unit {
    /**
     * Get the symbol representing the unit.
     *
     * @return The symbol associated with the unit.
     */
    String getSymbol();

    /**
     * Get the base unit for the quantity type associated with this unit.
     *
     * @return The base unit for the quantity type.
     */
    Unit getBaseUnit();

    /**
     * Convert a value from this unit to the equivalent value in the base unit.
     *
     * @param valueInThisUnit The value to be converted, in this unit.
     * @return The equivalent value in the base unit.
     */
    double toValueInBaseUnit(double valueInThisUnit);

    /**
     * Convert a value from the base unit to the equivalent value in this unit.
     *
     * @param valueInBaseUnit The value to be converted, in the base unit.
     * @return The equivalent value in this unit.
     */
    double fromValueInBaseUnit(double valueInBaseUnit);
}
