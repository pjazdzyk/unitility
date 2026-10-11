package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

/** Units of the quadratic loss coefficient K of a flow path, dp = K * mdot^2. */
public enum HydraulicResistanceUnits implements HydraulicResistanceUnit {

    // K of dp = K * mdot^2: the SI unit is Pa*s^2/kg^2 (= 1/(kg*m)).
    PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED("Pa·s²/kg²", 1.0),
    KILOPASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED("kPa·s²/kg²", 1.0E3),
    // Bar and hour per tonne: 1 bar·h²/t² = 10⁵ Pa × 3600² s² / (10³ kg)² = 1.296E6 Pa·s²/kg², exactly.
    BAR_HOUR_SQUARED_PER_TONNE_SQUARED("bar·h²/t²", UnitDefinitions.BAR_HOUR_SQUARED_PER_TONNE_SQUARED);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    HydraulicResistanceUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    HydraulicResistanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public HydraulicResistanceUnit getBaseUnit() {
        return PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static HydraulicResistanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return PASCAL_SECOND_SQUARED_PER_KILOGRAM_SQUARED;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (HydraulicResistanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + HydraulicResistanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyAerialAndVol()
                .unifyMultiAndDiv()
                .toString();
    }

}
