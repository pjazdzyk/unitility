package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum LinearHeatFluxUnits implements LinearHeatFluxUnit {

    WATTS_PER_METER("W/m", val -> val, val -> val),
    KILOWATTS_PER_METER("kW/m", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_HOUR_FOOT("BTU/(h·ft)",
            val -> val * ConversionConstants.BTU_H_FT_TO_W_M,
            val -> val / ConversionConstants.BTU_H_FT_TO_W_M),
    BTU_PER_MINUTE_FOOT("BTU/(min·ft)",
            val -> val * ConversionConstants.BTU_MIN_FT_TO_W_M,
            val -> val / ConversionConstants.BTU_MIN_FT_TO_W_M);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    LinearHeatFluxUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public LinearHeatFluxUnit getBaseUnit() {
        return WATTS_PER_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static LinearHeatFluxUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return WATTS_PER_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (LinearHeatFluxUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + LinearHeatFluxUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

    private static class ConversionConstants {
        // 1 BTU(IT)/(h·ft) ≈ 0.96132649 W/m
        private static final double BTU_H_FT_TO_W_M = 0.96132649;
        private static final double BTU_MIN_FT_TO_W_M = BTU_H_FT_TO_W_M * 60.0;
    }
}