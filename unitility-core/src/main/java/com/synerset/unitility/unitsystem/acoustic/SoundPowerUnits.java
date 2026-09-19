package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnits;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum SoundPowerUnits implements PowerUnit {
    WATT("W", 1.0),
    // Non-linear, so it keeps explicit converters: a power level, L = 10 lg(P / P0) dB (NIST SP 811 Sec. 8.7).
    // The reference power P0 = 1 pW is UNVERIFIED: it was not found in a primary source (see UnitDefinitions).
    DECIBEL("dB",
            db -> Math.pow(10.0, db / 10.0) * UnitDefinitions.SOUND_POWER_REFERENCE,
            w -> 10.0 * Math.log10(w / UnitDefinitions.SOUND_POWER_REFERENCE));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    SoundPowerUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
