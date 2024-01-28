package com.synerset.unitility.quarkus.serdes;

import com.synerset.unitility.jackson.serdes.SerdesHelpers;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.geographic.DMSValidator;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import jakarta.ws.rs.ext.ParamConverter;

/**
 * The PhysicalQuantityParamJakartaConverter class is a Jakarta RS {@link ParamConverter} for converting string
 * representations to {@link Latitude} instances and vice versa.
 */
public class LatitudeParamJakartaConverter implements ParamConverter<Latitude> {

    private final GeoQuantityParsingFactory geoParsingRegistry;

    public LatitudeParamJakartaConverter(GeoQuantityParsingFactory parsingRegistry) {
        this.geoParsingRegistry = parsingRegistry;
    }

    /**
     * Converts a string representation to a {@link Latitude} instance.
     * This method is called by Jakarta RS to convert a string representation to a {@link Latitude} instance
     * using the {@link GeoQuantityParsingFactory}.
     *
     * @param latitudeAsString The string representation of the {@link Latitude}.
     * @return A {@link Latitude} instance.
     */
    @Override
    public Latitude fromString(String latitudeAsString) {

        if (latitudeAsString == null) {
            throw new IllegalArgumentException("Jakarta param deserialization failure. Input cannot be null");
        }

        String preparedInput = SerdesHelpers.prepareInput(latitudeAsString);

        if (preparedInput != null && DMSValidator.isValidDMSFormat(preparedInput)) {
            return geoParsingRegistry.parseFromDMSFormat(Latitude.class, preparedInput);
        }

        if (SerdesHelpers.containsNonDigitChars(latitudeAsString)) {
            return geoParsingRegistry.parseFromEngFormat(Latitude.class, latitudeAsString);
        }

        double potentialDoubleValue = Double.parseDouble(preparedInput);

        return geoParsingRegistry.parseFromSymbol(Latitude.class, potentialDoubleValue, AngleUnits.DEGREES.getSymbol());

    }


    /**
     * Converts a {@link PhysicalQuantity} instance to its string representation.
     * Jakarta calls this method RS to convert a {@link PhysicalQuantity} instance to its string representation
     * using the toEngineeringFormat() method.
     *
     * @param latitude The {@link PhysicalQuantity} instance to be converted.
     * @return The string representation of the {@link PhysicalQuantity}.
     */
    @Override
    public String toString(Latitude latitude) {
        return latitude.toEngineeringFormat();
    }

}