package com.synerset.unitility.unitsystem.mechanical;

import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum TorqueUnits implements Unit {

    NEWTON_METER("N·m", val -> val, val -> val),
    MILLINEWTON_METER("mN·m", val -> val * 0.001, val -> val * 1000),
    KILOPOND_METER("kp·m", val -> val * 9.80665, val -> val / 9.80665),
    FOOT_POUND("ft·lb", val -> val * 1.3558179483314004, val -> val / 1.3558179483314004),
    INCH_POUND("in·lb", val -> val * 0.1129848290276167, val -> val / 0.1129848290276167);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    TorqueUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public TorqueUnits getBaseUnit() {
        return NEWTON_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static TorqueUnits fromSymbol(String rawSymbol) {
        String inputSymbolWithoutDegreesSign = formatSymbolInput(rawSymbol);
        for (TorqueUnits unit : values()) {
            String enumSymbolWithoutDegreesSing = formatSymbolInput(unit.symbol);
            if (enumSymbolWithoutDegreesSing.equalsIgnoreCase(inputSymbolWithoutDegreesSign)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + TorqueUnits.class.getSimpleName());
    }

    private static String formatSymbolInput(String inputString) {
        return inputString
                .trim()
                .toLowerCase()
                .replace(" ", "")
                .replace("·", "")
                .replace(".", "")
                .replace("x", "")
                .replace("/","p");
    }
    
}
