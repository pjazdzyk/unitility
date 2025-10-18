package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.geographic.GeoParsingHelpers;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public abstract class PhysicalQuantityAbstractParsingFactory implements PhysicalQuantityParsingFactory {

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parse(Class<Q> targetClass, String quantityAsString) {

        if(quantityAsString == null || quantityAsString.trim().isEmpty()){
            throw new UnitSystemParseException("Quantity parsing error. Input string quantity cannot be null or empty.");
        }

        String preparedInput = StringTransformer.of(quantityAsString)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .dropParentheses()
                .toString();

        ValueSymbolPair extractedPair;

        if (isGeoQuantity(targetClass) && GeoParsingHelpers.isDMSFormatOrSimilar(preparedInput)) {
            extractedPair = GeoParsingHelpers.extractValueAndSymbolFromDMSFormat(targetClass, preparedInput);
        } else{
            extractedPair = extractValueAndSymbol(preparedInput);
        }

        return extractedPair.symbol() == null || extractedPair.symbol().isBlank()
                ? parseValueWithDefaultUnit(targetClass, extractedPair.value())
                : parseValueAndSymbol(targetClass, extractedPair.value(), extractedPair.symbol());
    }

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueAndSymbol(Class<Q> targetClass,
                                                                                 double value,
                                                                                 String symbolAsString) {
        validateIfClassIsRegistered(targetClass);
        return targetClass.cast(getClassRegistry().get(targetClass).apply(value, symbolAsString));
    }

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueWithDefaultUnit(Class<Q> targetClass, double value) {
        validateIfClassIsRegistered(targetClass);

        U defaultUnit = getDefaultUnit(targetClass);

        Constructor<?>[] constructors = targetClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            if (paramTypes.length == 2
                    && paramTypes[0] == double.class
                    && paramTypes[1].isAssignableFrom(defaultUnit.getClass())) {
                try {
                    @SuppressWarnings("unchecked")
                    Constructor<Q> typedConstructor = (Constructor<Q>) constructor;
                    return typedConstructor.newInstance(value, defaultUnit);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException("Failed to instantiate quantity: " + e.getMessage(), e);
                }
            }
        }

        throw new RuntimeException("No suitable constructor found for: " + targetClass.getSimpleName());
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

    @Override
    public <U extends Unit, Q extends PhysicalQuantity<U>> U getDefaultUnit(Class<Q> targetClass) {
        @SuppressWarnings("unchecked")
        U defaultUnit = (U) getDefaultUnitRegistry().get(targetClass);
        return defaultUnit;
    }

    private boolean isGeoQuantity(Class<?> targetClass){
        return Latitude.class.isAssignableFrom(targetClass) || Longitude.class.isAssignableFrom(targetClass);
    }

    private ValueSymbolPair extractValueAndSymbol(String preparedInput){
        int indexOfLastDigit = 0;

        // Calculates where the value ends in the input string. The "e" is for case of scientific notation: -1.12345E-5
        for (char letter : preparedInput.toCharArray()) {
            if (Character.isDigit(letter) || letter == '.' || letter == '-' || letter == 'e') {
                indexOfLastDigit++;
            } else break;
        }

        String valuePart = preparedInput.substring(0, indexOfLastDigit);
        String symbolPart = preparedInput.substring(indexOfLastDigit);
        double value = ParsingHelpers.parseToDouble(valuePart);

        return new ValueSymbolPair(value, symbolPart);
    }

}