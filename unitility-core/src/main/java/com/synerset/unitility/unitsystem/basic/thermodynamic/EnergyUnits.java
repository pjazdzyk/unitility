package com.synerset.unitility.unitsystem.basic.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.StringCleaner;

import java.util.function.DoubleUnaryOperator;

public enum EnergyUnits implements EnergyUnit {

    JOULE("J", val -> val, val -> val),
    MILLIJOULE("mJ", val -> val * 0.001, val -> val / 0.001),
    KILOJOULE("kJ", val -> val * 1000, val -> val / 1000),
    MEGAJOULE("MJ", val -> val * 1_000_000, val -> val / 1_000_000),
    BTU("BTU", val -> val * 1055.05585262, val -> val / 1055.05585262),
    CALORIE("cal", val -> val * 4.184, val -> val / 4.184),
    KILOCALORIE("kcal", val -> val * 4184, val -> val / 4184),
    WATT_HOUR("Wh", val -> val * 3600, val -> val / 3600),
    KILOWATT_HOUR("kWh", val -> val * 3_600_000, val -> val / 3_600_000);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    EnergyUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public EnergyUnit getBaseUnit() {
        return JOULE;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static EnergyUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = unifySymbol(rawSymbol);
        for (EnergyUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + EnergyUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringCleaner.of(inputString)
                .trimAndClean()
                .toString();
    }
    
}