package com.synerset.unitility.quarkus.serdes;

import com.synerset.unitility.jackson.serdes.SerdesHelpers;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.geographic.DMSValidator;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import jakarta.ws.rs.ext.ParamConverter;

/**
 * The PhysicalQuantityParamJakartaConverter class is a Jakarta RS {@link ParamConverter} for converting string
 * representations to {@link Longitude} instances and vice versa.
 */
public class LongitudeParamJakartaConverter implements ParamConverter<Longitude> {

    private final GeoQuantityParsingFactory geoParsingRegistry;

    public LongitudeParamJakartaConverter(GeoQuantityParsingFactory parsingRegistry) {
        this.geoParsingRegistry = parsingRegistry;
    }

    /**
     * Converts a string representation to a {@link Longitude} instance.
     * This method is called by Jakarta RS to convert a string representation to a {@link Longitude} instance
     * using the {@link GeoQuantityParsingFactory}.
     *
     * @param longitudeAsString The string representation of the {@link Longitude}.
     * @return A {@link Longitude} instance.
     */
    @Override
    public Longitude fromString(String longitudeAsString) {

        if (longitudeAsString == null) {
            throw new IllegalArgumentException("Jakarta param deserialization failure. Input cannot be null");
        }

        String preparedInput = SerdesHelpers.prepareInput(longitudeAsString);

        if (preparedInput != null && DMSValidator.isValidDMSFormat(preparedInput)) {
            return geoParsingRegistry.parseFromDMSFormat(Longitude.class, preparedInput);
        }

        if (SerdesHelpers.containsNonDigitChars(longitudeAsString)) {
            return geoParsingRegistry.parseFromEngFormat(Longitude.class, longitudeAsString);
        }

        double potentialDoubleValue = Double.parseDouble(preparedInput);

        return geoParsingRegistry.parseFromSymbol(Longitude.class, potentialDoubleValue, AngleUnits.DEGREES.getSymbol());

    }


    /**
     * Converts a {@link Longitude} instance to its string representation.
     * Jakarta calls this method RS to convert a {@link Longitude} instance to its string representation
     * using the toEngineeringFormat() method.
     *
     * @param longitude The {@link Longitude} instance to be converted.
     * @return The string representation of the {@link Longitude}.
     */
    @Override
    public String toString(Longitude longitude) {
        return longitude.toEngineeringFormat();
    }

}