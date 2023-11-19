package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum SpecificEnthalpyUnits implements SpecificEnthalpyUnit {

    JOULE_PER_KILOGRAM("J/kg", val -> val, val -> val),
    KILOJOULE_PER_KILOGRAM("kJ/kg", val -> val * 1000, val -> val / 1000),
    BTU_PER_POUND("BTU/lb", val -> val * 2326, val -> val / 2326.0);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificEnthalpyUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public SpecificEnthalpyUnit getBaseUnit() {
        return JOULE_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificEnthalpyUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = formatSymbol(rawSymbol);
        for (SpecificEnthalpyUnit unit : values()) {
            String currentSymbol = formatSymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + SpecificEnthalpyUnits.class.getSimpleName());
    }

    private static String formatSymbol(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("/", "p");
    }

}