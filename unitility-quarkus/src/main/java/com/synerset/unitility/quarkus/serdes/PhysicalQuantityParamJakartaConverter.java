package com.synerset.unitility.quarkus.serdes;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import com.synerset.unitility.unitsystem.Unit;
import jakarta.ws.rs.ext.ParamConverter;

/**
 * The PhysicalQuantityParamJakartaConverter class is a Jakarta RS {@link ParamConverter} for converting string
 * representations to {@link PhysicalQuantity} instances and vice versa.
 *
 * @param <U> The type of unit associated with the {@link PhysicalQuantity}.
 * @param <Q> The type of {@link PhysicalQuantity}.
 */
public class PhysicalQuantityParamJakartaConverter<U extends Unit, Q extends PhysicalQuantity<U>> implements ParamConverter<Q> {

    private final Class<Q> targetClass;
    private final PhysicalQuantityParsingFactory parsingRegistry;

    public PhysicalQuantityParamJakartaConverter(Class<Q> targetClass, PhysicalQuantityParsingFactory parsingRegistry) {
        this.targetClass = targetClass;
        this.parsingRegistry = parsingRegistry;
    }

    /**
     * Converts a string representation to a {@link PhysicalQuantity} instance.
     * This method is called by Jakarta RS to convert a string representation to a {@link PhysicalQuantity} instance
     * using the {@link PhysicalQuantityParsingFactory}.
     *
     * @param quantityAsString The string representation of the {@link PhysicalQuantity}.
     * @return A {@link PhysicalQuantity} instance.
     */
    @Override
    public Q fromString(String quantityAsString) {
        return parsingRegistry.parseFromEngFormat(targetClass, quantityAsString);
    }

    /**
     * Converts a {@link PhysicalQuantity} instance to its string representation.
     * Jakarta calls this method RS to convert a {@link PhysicalQuantity} instance to its string representation
     * using the toEngineeringFormat() method.
     *
     * @param physicalQuantity The {@link PhysicalQuantity} instance to be converted.
     * @return The string representation of the {@link PhysicalQuantity}.
     */
    @Override
    public String toString(Q physicalQuantity) {
        return physicalQuantity.toEngineeringFormat();
    }

}