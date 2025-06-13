package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.GALLONS_US_PER_CUBIC_METER;
import static com.synerset.unitility.unitsystem.util.Constants.RADIANS_PER_REVOLUTION;

public enum RotationSpeedToFlowRateRatioUnits implements RotationSpeedToFlowRateRatioUnit {

    RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND("rad·s⁻¹/m³·s⁻¹", val -> val, val -> val),
    RPM_PER_GPM("rpm/gpm", val -> val * (GALLONS_US_PER_CUBIC_METER * RADIANS_PER_REVOLUTION), val -> val / (GALLONS_US_PER_CUBIC_METER * RADIANS_PER_REVOLUTION));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

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
            return getDefaultUnit();
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
                .unifyAerialAndVol()
                .unifyMultiAndDiv()
                .toString();
    }

    public static RotationSpeedToFlowRateRatioUnit getDefaultUnit() {
        return RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND;  // Default to Pa/m
    }
}