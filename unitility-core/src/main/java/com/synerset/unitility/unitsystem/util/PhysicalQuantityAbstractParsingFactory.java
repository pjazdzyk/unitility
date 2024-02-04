package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.geographic.DMSParserHelper;
import com.synerset.unitility.unitsystem.geographic.DMSValidator;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;

import java.util.HashSet;
import java.util.Set;

public abstract class PhysicalQuantityAbstractParsingFactory implements PhysicalQuantityParsingFactory {

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueAndSymbol(Class<Q> targetClass,
                                                                                 double value,
                                                                                 String symbolAsString) {
        validateIfClassIsRegistered(targetClass);
        return targetClass.cast(getClassRegistry().get(targetClass).apply(value, symbolAsString));
    }

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parse(Class<Q> targetClass, String quantityAsString) {

        String preparedInput = StringTransformer.of(quantityAsString)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .dropParentheses()
                .toString();

        Pair extractedPair;

        if (isGeoQuantity(targetClass) && DMSValidator.isValidDMSFormat(preparedInput)) {
            extractedPair = extractValueAndSymbolFromDMSFormat(targetClass, preparedInput);
        } else{
            extractedPair = extractValueAndSymbol(preparedInput);
        }

        return parseValueAndSymbol(targetClass, extractedPair.value, extractedPair.symbol);

    }


    @Override
    public boolean containsClass(Class<?> targetClass) {
        return getClassRegistry().containsKey(targetClass);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <U extends Unit, Q extends PhysicalQuantity<U>> Set<Class<Q>> findAllRegisteredClasses() {
        Set<Class<Q>> quantityClasses = new HashSet<>();
        getClassRegistry().keySet().forEach(quantityClass -> quantityClasses.add((Class<Q>) quantityClass));
        return quantityClasses;
    }

    private <U extends Unit, Q extends PhysicalQuantity<U>> void validateIfClassIsRegistered(Class<Q> targetClass) {
        if (!containsClass(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Class not found in the registry: " + targetClass.getSimpleName());
        }
    }

    private boolean isGeoQuantity(Class<?> targetClass){
        return Latitude.class.isAssignableFrom(targetClass) || Longitude.class.isAssignableFrom(targetClass);
    }

    private Pair extractValueAndSymbol(String preparedInput){
        int indexOfLastDigit = 0;

        // Calculates where value ends in the input string. "e" is for case of scientific notation: -1.12345E-5
        for (char letter : preparedInput.toCharArray()) {
            if (Character.isDigit(letter) || letter == '.' || letter == '-' || letter == 'e') {
                indexOfLastDigit++;
            } else break;
        }

        String valuePart = preparedInput.substring(0, indexOfLastDigit);
        String symbolPart = preparedInput.substring(indexOfLastDigit);
        double value = ParsingHelpers.parseToDouble(valuePart);

        return new Pair(value, symbolPart);
    }

    private Pair extractValueAndSymbolFromDMSFormat(Class<?> targetClass, String partiallyPreparedInput){
        String preparedInput = StringTransformer.of(partiallyPreparedInput)
                .unifyDMSNotationSymbols()
                .toString();

        if (Latitude.class.isAssignableFrom(targetClass) && (preparedInput.contains("e") || preparedInput.contains("w"))) {
            throw new UnitSystemParseException("Invalid latitude direction. Expected: N or S. Input: " + preparedInput);
        } else if (Longitude.class.isAssignableFrom(targetClass) && (preparedInput.contains("n") || preparedInput.contains("s"))) {
            throw new UnitSystemParseException("Invalid longitude direction. Expected: W or E. Input: " + preparedInput);
        }
        
        double valueInDegrees = DMSParserHelper.extractDegreesFromDMSFormat(preparedInput);
        return new Pair(valueInDegrees, AngleUnits.DEGREES.getSymbol());
    }

    record Pair(Double value, String symbol) {}

}
