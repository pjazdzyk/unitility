package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum DataSizeUnits implements DataSizeUnit {

    BYTE("B", val -> val, val -> val),
    BIT("bit", val -> val / 8.0, val -> val * 8.0),
    KILOBYTE("KB", val -> val * 1024.0, val -> val / 1024.0),
    MEGABYTE("MB", val -> val * Math.pow(1024.0, 2), val -> val / Math.pow(1024.0, 2)),
    GIGABYTE("GB", val -> val * Math.pow(1024.0, 3), val -> val / Math.pow(1024.0, 3)),
    TERABYTE("TB", val -> val * Math.pow(1024.0, 4), val -> val / Math.pow(1024.0, 4)),
    PETABYTE("PB", val -> val * Math.pow(1024.0, 5), val -> val / Math.pow(1024.0, 5)); // Max safe unit to fit in double (1 PB = 2^50 Bytes)

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    DataSizeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public DataSizeUnits getBaseUnit() {
        return BYTE;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static DataSizeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return BYTE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (DataSizeUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + DataSizeUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }
}