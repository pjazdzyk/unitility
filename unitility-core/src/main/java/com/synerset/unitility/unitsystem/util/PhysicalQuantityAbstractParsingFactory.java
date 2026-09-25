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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhysicalQuantityAbstractParsingFactory implements PhysicalQuantityParsingFactory {

    /**
     * A value followed by a reciprocal unit, {@code "4.5e-10 1/Pa"}. Spaces are removed before the value is
     * split from the symbol, which would read that as 4.51 per pascal, so the space is honoured first here.
     */
    private static final Pattern VALUE_THEN_RECIPROCAL = Pattern.compile("^([-+]?[0-9][0-9.,eE+-]*)\\s+(1\\s*/.+)$");

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parse(Class<Q> targetClass, String quantityAsString) {

        if(quantityAsString == null || quantityAsString.trim().isEmpty()){
            throw new UnitSystemParseException("Quantity parsing error. Input string quantity cannot be null or empty.");
        }

        Matcher reciprocal = VALUE_THEN_RECIPROCAL.matcher(quantityAsString.trim());
        if (reciprocal.matches()) {
            double value = ParsingHelpers.parseToDouble(reciprocal.group(1).toLowerCase().replace(",", "."));
            String symbol = reciprocal.group(2);
            return parseValueAndSymbol(targetClass, value, StringTransformer.of(symbol).trimAndClean().toString(),
                    StringTransformer.of(symbol).trimLowerAndClean().dropParentheses().toString());
        }

        String preparedInput = StringTransformer.of(quantityAsString)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .dropParentheses()
                .toString();

        ValueSymbolPair extractedPair;

        if (isGeoQuantity(targetClass) && GeoParsingHelpers.isDMSFormatOrSimilar(preparedInput)) {
            extractedPair = GeoParsingHelpers.extractValueAndSymbolFromDMSFormat(targetClass, preparedInput);
            return extractedPair.symbol() == null || extractedPair.symbol().isBlank()
                    ? parseValueWithDefaultUnit(targetClass, extractedPair.value())
                    : parseValueAndSymbol(targetClass, extractedPair.value(), extractedPair.symbol());
        }
        extractedPair = extractValueAndSymbol(preparedInput);
        if (extractedPair.symbol() == null || extractedPair.symbol().isBlank()) {
            return parseValueWithDefaultUnit(targetClass, extractedPair.value());
        }

        // The symbol as written, case and parentheses kept: the lower-cased one above is what the unit enums have
        // always been handed, and it cannot tell a megajoule from a millijoule.
        String cleanedInput = StringTransformer.of(quantityAsString).trimAndClean().replaceCommaForDot().toString();
        String writtenSymbol = cleanedInput.substring(numericPrefixLength(cleanedInput.toLowerCase()));
        return parseValueAndSymbol(targetClass, extractedPair.value(), writtenSymbol, extractedPair.symbol());
    }

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueAndSymbol(Class<Q> targetClass,
                                                                                 double value,
                                                                                 String symbolAsString) {
        return parseValueAndSymbol(targetClass, value, symbolAsString, symbolAsString);
    }

    /**
     * Resolves the unit from the symbol as written, case-aware ({@link UnitSymbolResolver}), and only when that
     * cannot decide hands the symbol to the unit enum, which knows its own aliases.
     *
     * @param writtenSymbol the symbol as the caller wrote it
     * @param enumSymbol    the symbol the unit enum is handed if the resolver cannot decide
     */
    private <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueAndSymbol(Class<Q> targetClass, double value,
                                                                                  String writtenSymbol,
                                                                                  String enumSymbol) {
        validateIfClassIsRegistered(targetClass);
        Optional<List<? extends Unit>> units = SupportedQuantitiesRegistry.getInstance().findUnitsByClass(targetClass);
        if (units.isPresent()) {
            Optional<? extends Unit> resolved = UnitSymbolResolver.resolve(units.get(), writtenSymbol);
            if (resolved.isPresent()) {
                Q constructed = construct(targetClass, value, resolved.get());
                if (constructed != null) {
                    return constructed;
                }
            }
        }
        return targetClass.cast(getClassRegistry().get(targetClass).apply(value, enumSymbol));
    }

    public <U extends Unit, Q extends PhysicalQuantity<U>> Q parseValueWithDefaultUnit(Class<Q> targetClass, double value) {
        validateIfClassIsRegistered(targetClass);

        U defaultUnit = getDefaultUnit(targetClass);
        Q constructed = construct(targetClass, value, defaultUnit);
        if (constructed == null) {
            throw new RuntimeException("No suitable constructor found for: " + targetClass.getSimpleName());
        }
        return constructed;
    }

    /** The quantity built from a value and a unit object, or null when the class has no such constructor. */
    private static <Q> Q construct(Class<Q> targetClass, double value, Unit unit) {
        for (Constructor<?> constructor : targetClass.getConstructors()) {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            if (paramTypes.length == 2
                    && paramTypes[0] == double.class
                    && paramTypes[1].isAssignableFrom(unit.getClass())) {
                try {
                    @SuppressWarnings("unchecked")
                    Constructor<Q> typedConstructor = (Constructor<Q>) constructor;
                    return typedConstructor.newInstance(value, unit);
                } catch (InvocationTargetException e) {
                    // The quantity's own validation refused the value, which must reach the caller as it would
                    // from the unit enum's path.
                    if (e.getCause() instanceof RuntimeException runtime) {
                        throw runtime;
                    }
                    throw new RuntimeException("Failed to instantiate quantity: " + e.getMessage(), e);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("Failed to instantiate quantity: " + e.getMessage(), e);
                }
            }
        }
        return null;
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
        int indexOfLastDigit = numericPrefixLength(preparedInput);

        String valuePart = preparedInput.substring(0, indexOfLastDigit);
        String symbolPart = preparedInput.substring(indexOfLastDigit);
        double value = ParsingHelpers.parseToDouble(valuePart);

        return new ValueSymbolPair(value, symbolPart);
    }

    /** Where the value ends in a lower-cased input. The "e" is for scientific notation: -1.12345e-5. */
    private static int numericPrefixLength(String lowerCasedInput) {
        int indexOfLastDigit = 0;
        for (char letter : lowerCasedInput.toCharArray()) {
            if (Character.isDigit(letter) || letter == '.' || letter == '-' || letter == 'e') {
                indexOfLastDigit++;
            } else break;
        }
        return indexOfLastDigit;
    }

}