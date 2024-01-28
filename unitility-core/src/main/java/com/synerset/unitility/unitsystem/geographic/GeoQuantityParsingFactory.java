package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.utils.StringTransformer;

import java.util.Map;
import java.util.function.BiFunction;

public class GeoQuantityParsingFactory implements PhysicalQuantityParsingFactory {

    private final Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> immutableRegistry;

    public GeoQuantityParsingFactory() {
        // Initializing HashMap
        this.immutableRegistry = Map.ofEntries(
                // Geographic
                Map.entry(Latitude.class, Latitude::of),
                Map.entry(Longitude.class, Longitude::of)
        );
    }

    /**
     * Parses a geographical coordinate or in Degrees-Minutes-Seconds (DMS) format
     * into a specific physical quantity object of {@link Latitude} or {@link Longitude}.
     *
     * @param <U>                 The type of unit associated with the physical quantity.
     * @param <Q>                 The type of physical quantity to parse.
     * @param targetClass         The class object representing the type of physical quantity to parse into.
     * @param quantityInDMSFormat The string representation of the quantity in DMS format.
     * @return A physical quantity object of type {@code Q} representing the parsed DMS value.
     * @throws UnitSystemParseException If the provided string is not a valid DMS format, or if the latitude or
     *                                  longitude direction is inconsistent with the specified target class.
     * @see DMSValidator#isValidDMSFormat(String)
     * @see DMSParserHelper#extractDegreesFromDMSFormat(String)
     * @see StringTransformer
     */
    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parseFromDMSFormat(Class<Q> targetClass,
                                                                                String quantityInDMSFormat) {


        String preparedInput = StringTransformer.of(quantityInDMSFormat)
                .trimLowerAndClean()
                .dropParentheses()
                .replaceCommaForDot()
                .unifyDMSNotationSymbols()
                .toString();

        if (!DMSValidator.isValidDMSFormat(preparedInput)) {
            throw new UnitSystemParseException("Provided string is not valid DMS format. Input: " + quantityInDMSFormat
                    + ", target class: " + targetClass.getSimpleName());
        }

        if (Latitude.class.isAssignableFrom(targetClass)
                && (preparedInput.contains("e") || preparedInput.contains("w"))) {
            throw new UnitSystemParseException("Invalid latitude direction. Expected: N or S. Input string: " + quantityInDMSFormat);
        } else if (Longitude.class.isAssignableFrom(targetClass)
                && (preparedInput.contains("n") || preparedInput.contains("s"))) {
            throw new UnitSystemParseException("Invalid longitude direction. Expected: W or E. Input string: " + quantityInDMSFormat);
        }

        double valueInDegrees = DMSParserHelper.extractDegreesFromDMSFormat(preparedInput);
        return parseFromSymbol(targetClass, valueInDegrees, AngleUnits.DEGREES.getSymbol());
    }

    @Override
    public Map<Class<?>, BiFunction<Double, String, ? extends PhysicalQuantity<?>>> getClassRegistry() {
        return immutableRegistry;
    }
}