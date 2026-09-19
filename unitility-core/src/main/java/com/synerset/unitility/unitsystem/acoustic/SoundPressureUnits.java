package com.synerset.unitility.unitsystem.acoustic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnit;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnits;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum SoundPressureUnits implements PressureUnit {
    PASCAL("Pa", 1.0),
    // Non-linear, so it keeps explicit converters: a field level, L = 20 lg(p / p0) dB (NIST SP 811 Sec. 8.7),
    // with the reference pressure p0 = 20 µPa as declared (see UnitDefinitions).
    DECIBEL("dB",
            db -> Math.pow(10.0, db / 20.0) * UnitDefinitions.SOUND_PRESSURE_REFERENCE,
            pa -> 20.0 * Math.log10(pa / UnitDefinitions.SOUND_PRESSURE_REFERENCE));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    SoundPressureUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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