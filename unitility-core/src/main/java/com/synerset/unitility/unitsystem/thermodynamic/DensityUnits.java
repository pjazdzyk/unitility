package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.CUBIC_FEET_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.CUBIC_INCHES_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.KILOGRAMS_PER_POUND;

public enum DensityUnits implements DensityUnit {

    KILOGRAM_PER_CUBIC_METER("kg/m³", val -> val, val -> val),
    POUND_PER_CUBIC_FOOT("lb/ft³", val -> val * (CUBIC_FEET_PER_CUBIC_METER * KILOGRAMS_PER_POUND), val -> val / (CUBIC_FEET_PER_CUBIC_METER * KILOGRAMS_PER_POUND)),
    POUND_PER_CUBIC_INCH("lb/in³", val -> val * (CUBIC_INCHES_PER_CUBIC_METER * KILOGRAMS_PER_POUND), val -> val / (CUBIC_INCHES_PER_CUBIC_METER * KILOGRAMS_PER_POUND));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    DensityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public DensityUnit getBaseUnit() {
        return KILOGRAM_PER_CUBIC_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static DensityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return getDefaultUnit();
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (DensityUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + DensityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .toString();
    }

    public static DensityUnit getDefaultUnit() {
        return KILOGRAM_PER_CUBIC_METER;
    }

}