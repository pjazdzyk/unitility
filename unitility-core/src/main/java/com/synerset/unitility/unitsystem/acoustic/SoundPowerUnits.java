package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnits;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum SoundPowerUnits implements PowerUnit {
    // Assumed reference power = 1E-12 W.

    WATT("W", val -> val, val -> val),
    DECIBEL("dB", db -> Math.pow(10.0, db / 10.0) * 1E-12, w -> 10.0 * Math.log10(w / 1E-12));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SoundPowerUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public SoundPowerUnits getBaseUnit() {
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

        for (SoundPowerUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }

        PowerUnit foundTargetUnit = PowerUnits.fromSymbol(requestedSymbol);

        if (foundTargetUnit == null) {
            throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                    + SoundPowerUnits.class.getSimpleName());
        }

        return foundTargetUnit;
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .replace("dbl", "db")
                .toString();
    }

}
