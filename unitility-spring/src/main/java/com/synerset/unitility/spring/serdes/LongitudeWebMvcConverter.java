package com.synerset.unitility.spring.serdes;

import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.geographic.DMSValidator;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

/**
 * The PhysicalQuantityWebMvcConverter class is a Spring converter for converting strings to PhysicalQuantity instances.
 */
public class LongitudeWebMvcConverter implements Converter<String, Longitude> {

    private final GeoQuantityParsingFactory geoParsingRegistry;

    public LongitudeWebMvcConverter(GeoQuantityParsingFactory parsingFactory) {
        this.geoParsingRegistry = parsingFactory;
    }

    /**
     * Converts a string to a PhysicalQuantity instance using the {@link GeoQuantityParsingFactory}.
     * Spring calls this method to convert a string input to a PhysicalQuantity instance using the
     * {@link GeoQuantityParsingFactory}.
     *
     * @param longitudeAsString The string representation of the PhysicalQuantity.
     * @return A PhysicalQuantity instance.
     */
    @Override
    public Longitude convert(@NonNull String longitudeAsString) {

        if (longitudeAsString.contains("[")) {
            return geoParsingRegistry.parseFromEngFormat(Longitude.class, longitudeAsString);
        }

        String preparedInput = ConverterHelpers.prepareInput(longitudeAsString);

        if (preparedInput != null && DMSValidator.isValidDMSFormat(preparedInput)) {
            return geoParsingRegistry.parseFromDMSFormat(Longitude.class, preparedInput);
        }

        double potentialDoubleValue = Double.parseDouble(preparedInput);

        return geoParsingRegistry.parseFromSymbol(Longitude.class, potentialDoubleValue, AngleUnits.DEGREES.getSymbol());
    }

}
