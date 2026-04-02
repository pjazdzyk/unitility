package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum MolarVolumeUnits implements MolarVolumeUnit {

    CUBIC_METER_PER_MOLE("m³/mol", val -> val, val -> val),
    LITER_PER_MOLE("L/mol", val -> val * 0.001, val -> val / 0.001),
    CUBIC_DECIMETER_PER_MOLE("dm³/mol", val -> val * 0.001, val -> val / 0.001),
    CUBIC_CENTIMETER_PER_MOLE("cm³/mol", val -> val * 1e-6, val -> val / 1e-6),
    MILLILITER_PER_MOLE("mL/mol", val -> val * 1e-6, val -> val / 1e-6),
    CUBIC_FOOT_PER_POUND_MOLE("ft³/lbmol", val -> val * 0.00006242796, val -> val / 0.00006242796),
    CUBIC_INCH_PER_POUND_MOLE("in³/lbmol", val -> val * 0.00000003612729, val -> val / 0.00000003612729);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MolarVolumeUnits(String symbol, DoubleUnaryOperator toBaseConverter,
                     DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public MolarVolumeUnit getBaseUnit() {
        return CUBIC_METER_PER_MOLE;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MolarVolumeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return CUBIC_METER_PER_MOLE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MolarVolumeUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .toString();
    }
}