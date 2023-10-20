package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum MomentumUnits implements Unit {
    KILOGRAM_METER_PER_SECOND("kg·m/s", val -> val, val -> val),
    POUND_FEET_PER_SECOND("lb·ft/s", val -> val * 0.138254954376, val -> val / 0.138254954376),
    GRAM_CENTIMETRE_PER_SECOND("g·cm/s", val -> val * 0.00001, val -> val / 0.00001);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MomentumUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public MomentumUnits getBaseUnit() {
        return KILOGRAM_METER_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MomentumUnits fromSymbol(String rawSymbol) {
        String inputSymbolWithoutDegreesSign = formatSymbolInput(rawSymbol);
        for (MomentumUnits unit : values()) {
            String enumSymbolWithoutDegreesSing = formatSymbolInput(unit.symbol);
            if (enumSymbolWithoutDegreesSing.equalsIgnoreCase(inputSymbolWithoutDegreesSign)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + MomentumUnits.class.getSimpleName());
    }

    private static String formatSymbolInput(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("·", "")
                .replace(".", "")
                .replace("x", "")
                .replace("/","p")
                .replace("(", "")
                .replace(")", "");
    }
    
}
