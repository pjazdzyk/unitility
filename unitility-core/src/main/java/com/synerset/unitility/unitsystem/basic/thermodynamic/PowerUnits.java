package com.synerset.unitility.unitsystem.basic.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.StringCleaner;

import java.util.function.DoubleUnaryOperator;

public enum PowerUnits implements PowerUnit {

    WATT("W", val -> val, val -> val),
    KILOWATT("kW", val -> val * 1000.0, val -> val / 1000.0),
    MEGAWATT("MW", val -> val * 1_000_000, val -> val / 1_000_000),
    BTU_PER_HOUR("BTU/h", val -> val * 0.2930710701722222, val -> val / 0.2930710701722222),
    HORSE_POWER("HP", val -> val * 745.69987158227022, val -> val / 745.69987158227022);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    PowerUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public PowerUnits getBaseUnit() {
        return WATT;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static PowerUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = unifySymbol(rawSymbol);
        for (PowerUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + PowerUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringCleaner.of(inputString)
                .trimAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

}
