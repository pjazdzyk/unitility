package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnit;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnits;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum SoundPressureUnits implements PressureUnit {
    // Assumed reference pressure = 2E-5 Pa.

    PASCAL("Pa", val -> val, val -> val),
    DECIBEL("dB", db -> Math.pow(10.0, db / 20.0) * 2E-5, pa -> 20.0 * Math.log10(pa / 2E-5));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SoundPressureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public SoundPressureUnits getBaseUnit() {
        return PASCAL;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static PressureUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return PASCAL;
        }
        String requestedSymbol = unifySymbol(rawSymbol);

        for (SoundPressureUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        PressureUnit foundTargetUnit = PressureUnits.fromSymbol(requestedSymbol);

        if (foundTargetUnit == null) {
            throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                    + SoundPressureUnits.class.getSimpleName());
        }

        return foundTargetUnit;
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .replace("dba", "db")
                .unifyAerialAndVol()
                .toString();
    }


}