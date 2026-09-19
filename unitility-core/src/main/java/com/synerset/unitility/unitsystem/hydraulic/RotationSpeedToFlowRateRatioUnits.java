package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum RotationSpeedToFlowRateRatioUnits implements RotationSpeedToFlowRateRatioUnit {

    RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND("rad·s⁻¹/m³·s⁻¹", 1.0),
    RPM_PER_GPM("rpm/gpm", UnitDefinitions.RPM_PER_GPM);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    RotationSpeedToFlowRateRatioUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    RotationSpeedToFlowRateRatioUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public RotationSpeedToFlowRateRatioUnit getBaseUnit() {
        return RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static RotationSpeedToFlowRateRatioUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (RotationSpeedToFlowRateRatioUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + RotationSpeedToFlowRateRatioUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .replace("1/s", "s-1")
                .replace("1ps", "s-1")
                .unifyAerialAndVol()
                .dropParentheses()
                .unifyNegativeExponents()
                .unifyMultiAndDiv()
                .toString();
    }
}