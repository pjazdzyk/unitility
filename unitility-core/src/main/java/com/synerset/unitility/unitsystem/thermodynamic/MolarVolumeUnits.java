package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum MolarVolumeUnits implements MolarVolumeUnit {

    CUBIC_METER_PER_MOLE("m³/mol", 1.0),
    LITER_PER_MOLE("L/mol", UnitDefinitions.LITRE),
    CUBIC_DECIMETER_PER_MOLE("dm³/mol", 1.0E-3),
    CUBIC_CENTIMETER_PER_MOLE("cm³/mol", 1.0E-6),
    MILLILITER_PER_MOLE("mL/mol", 1.0E-6),
    CUBIC_FOOT_PER_POUND_MOLE("ft³/lbmol", UnitDefinitions.CUBIC_FOOT_PER_POUND_MOLE),
    CUBIC_INCH_PER_POUND_MOLE("in³/lbmol", UnitDefinitions.CUBIC_INCH_PER_POUND_MOLE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    MolarVolumeUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    MolarVolumeUnits(String symbol, DoubleUnaryOperator toBaseConverter,
                     DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public MolarVolumeUnit getBaseUnit() {
        return CUBIC_METER_PER_MOLE;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MolarVolumeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return CUBIC_METER_PER_MOLE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MolarVolumeUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .toString();
    }
}