package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.JOULES_PER_BTU;
import static com.synerset.unitility.unitsystem.util.Constants.JOULES_PER_CALORIE;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.MEGA;
import static com.synerset.unitility.unitsystem.util.Constants.MILLI;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_HOUR;

public enum EnergyUnits implements EnergyUnit {

    JOULE("J", val -> val, val -> val),
    MILLIJOULE("mJ", val -> val * MILLI, val -> val / MILLI),
    KILOJOULE("kJ", val -> val * KILO, val -> val / KILO),
    MEGAJOULE("MJ", val -> val * MEGA, val -> val / MEGA),
    BTU("BTU", val -> val * JOULES_PER_BTU, val -> val / JOULES_PER_BTU),
    CALORIE("cal", val -> val * JOULES_PER_CALORIE, val -> val / JOULES_PER_CALORIE),
    KILOCALORIE("kcal", val -> val * KILO * JOULES_PER_CALORIE, val -> val / (KILO * JOULES_PER_CALORIE)),
    WATT_HOUR("Wh", val -> val * SECONDS_PER_HOUR, val -> val / SECONDS_PER_HOUR),
    KILOWATT_HOUR("kWh", val -> val * (KILO * SECONDS_PER_HOUR), val -> val / (KILO * SECONDS_PER_HOUR));

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
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (EnergyUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + EnergyUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }

}