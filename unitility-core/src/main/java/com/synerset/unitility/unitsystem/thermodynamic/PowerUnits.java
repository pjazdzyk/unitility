package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.WATTS_PER_IMPERIAL_HORSEPOWER;
import static com.synerset.unitility.unitsystem.util.Constants.JOULES_PER_BTU;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.MEGA;
import static com.synerset.unitility.unitsystem.util.Constants.SECONDS_PER_HOUR;

public enum PowerUnits implements PowerUnit {

    WATT("W", val -> val, val -> val),
    KILOWATT("kW", val -> val * KILO, val -> val / KILO),
    MEGAWATT("MW", val -> val * MEGA, val -> val / MEGA),
    BTU_PER_HOUR("BTU/h", val -> val * (JOULES_PER_BTU / SECONDS_PER_HOUR), val -> val / (JOULES_PER_BTU / SECONDS_PER_HOUR)),
    HORSE_POWER("HP", val -> val * WATTS_PER_IMPERIAL_HORSEPOWER, val -> val / WATTS_PER_IMPERIAL_HORSEPOWER);

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
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return WATT;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (PowerUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + PowerUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

}
