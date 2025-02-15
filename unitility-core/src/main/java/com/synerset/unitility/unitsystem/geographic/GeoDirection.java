package com.synerset.unitility.unitsystem.geographic;

/**
 * The {@code GeoDirection} interface represents a geographical direction.
 * It provides methods to retrieve the direction symbol and the true bearing of the direction.
 * Implementations of this interface will define the specific symbol used to represent the direction
 * and how to calculate or provide the true bearing value.
 *
 */
public interface GeoDirection {

    /**
     * Returns the symbol that represents the direction (e.g., "N" for North, "S" for South).
     *
     * @return the symbol representing the direction
     */
    String getDirectionSymbol();

    /**
     * Returns the true {@link Bearing} of the geographical direction.
     * The true bearing is typically represented as an angle from North (0°) in degrees.
     *
     * @return the true bearing of the direction
     */
    Bearing getTrueBearing();
}
