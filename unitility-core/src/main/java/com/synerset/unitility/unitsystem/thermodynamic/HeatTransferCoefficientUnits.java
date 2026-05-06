package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum HeatTransferCoefficientUnits implements HeatTransferCoefficientUnit {

    WATTS_PER_SQUARE_METER_KELVIN("W/(m²·K)", val -> val, val -> val),
    KILOWATTS_PER_SQUARE_METER_KELVIN("kW/(m²·K)", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_HOUR_SQUARE_FOOT_FAHRENHEIT("BTU/(h·ft²·°F)",
            val -> val * ConversionConstants.BTU_H_FT2_F_TO_W_M2_K,
            val -> val / ConversionConstants.BTU_H_FT2_F_TO_W_M2_K),
    BTU_PER_MINUTE_SQUARE_FOOT_FAHRENHEIT("BTU/(min·ft²·°F)",
            val -> val * ConversionConstants.BTU_MIN_FT2_F_TO_W_M2_K,
            val -> val / ConversionConstants.BTU_MIN_FT2_F_TO_W_M2_K);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    HeatTransferCoefficientUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public HeatTransferCoefficientUnit getBaseUnit() {
        return WATTS_PER_SQUARE_METER_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static HeatTransferCoefficientUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return WATTS_PER_SQUARE_METER_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (HeatTransferCoefficientUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + HeatTransferCoefficientUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .unifyAerialAndVol()
                .dropDegreeSymbols()
                .dropParentheses()
                .toString();
    }

    private static class ConversionConstants {
        // 1 BTU(IT)/(h·ft²·°F) ≈ 5.67826334 W/(m²·K)
        private static final double BTU_H_FT2_F_TO_W_M2_K = 5.67826334;
        private static final double BTU_MIN_FT2_F_TO_W_M2_K = BTU_H_FT2_F_TO_W_M2_K * 60.0;
    }
}