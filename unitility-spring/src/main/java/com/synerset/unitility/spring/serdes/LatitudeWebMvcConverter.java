package com.synerset.unitility.spring.serdes;

import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.geographic.DMSValidator;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

/**
 * The PhysicalQuantityWebMvcConverter class is a Spring converter for converting strings to PhysicalQuantity instances.
 */
public class LatitudeWebMvcConverter implements Converter<String, Latitude> {

    private final GeoQuantityParsingFactory geoParsingRegistry;

    public LatitudeWebMvcConverter(GeoQuantityParsingFactory parsingFactory) {
        this.geoParsingRegistry = parsingFactory;
    }

    /**
     * Converts a string to a PhysicalQuantity instance using the {@link GeoQuantityParsingFactory}.
     * Spring calls this method to convert a string input to a PhysicalQuantity instance using the
     * {@link GeoQuantityParsingFactory}.
     *
     * @param latitudeAsString The string representation of the PhysicalQuantity.
     * @return A PhysicalQuantity instance.
     */
    @Override
    public Latitude convert(@NonNull String latitudeAsString) {

        if (latitudeAsString.contains("[")) {
            return geoParsingRegistry.parseFromEngFormat(Latitude.class, latitudeAsString);
        }

        String preparedInput = ConverterHelpers.prepareInput(latitudeAsString);

        if (preparedInput != null && DMSValidator.isValidDMSFormat(preparedInput)) {
            return geoParsingRegistry.parseFromDMSFormat(Latitude.class, preparedInput);
        }

        double potentialDoubleValue = Double.parseDouble(preparedInput);

        return geoParsingRegistry.parseFromSymbol(Latitude.class, potentialDoubleValue, AngleUnits.DEGREES.getSymbol());
    }

}
