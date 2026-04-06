package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum MolarMassUnits implements MolarMassUnit {
    KILOGRAM_PER_MOLE("kg/mol", val -> val, val -> val),   // Base SI unit
    GRAM_PER_MOLE("g/mol", val -> val * 1e-3, val -> val / 1e-3),
    KILOGRAM_PER_KILOMOLE("kg/kmol", val -> val * 1e-3, val -> val / 1e-3),
    MILLIGRAM_PER_MILLIMOLE("mg/mmol", val -> val * 1e-3, val -> val / 1e-3),
    POUND_PER_POUND_MOLE("lb/lbmol", val -> val * 1e-3, val -> val / 1e-3),
    // 1 oz ≈ 0.0283495 kg, 1 oz/mol = 0.0283495 kg/mol
    OUNCE_PER_MOLE("oz/mol", val -> val * 0.028349523125, val -> val / 0.028349523125);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MolarMassUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public MolarMassUnits getBaseUnit() { return KILOGRAM_PER_MOLE; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MolarMassUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return KILOGRAM_PER_MOLE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MolarMassUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimAndClean()
                .unifyMultiAndDiv()
                .toString();
    }
}