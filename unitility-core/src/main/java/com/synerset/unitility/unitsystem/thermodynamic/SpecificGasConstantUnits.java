package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum SpecificGasConstantUnits implements SpecificGasConstantUnit {

    JOULE_PER_KILOGRAM_KELVIN("J/(kg·K)", val -> val, val -> val),
    KILOJOULE_PER_KILOGRAM_KELVIN("kJ/(kg·K)", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_POUND_RANKINE("BTU/(lb·°R)",
            val -> val * ConversionConstants.BTU_LB_R_TO_J_KG_K,
            val -> val / ConversionConstants.BTU_LB_R_TO_J_KG_K),
    BTU_PER_POUND_FAHRENHEIT("BTU/(lb·°F)",
            val -> val * ConversionConstants.BTU_LB_R_TO_J_KG_K,
            val -> val / ConversionConstants.BTU_LB_R_TO_J_KG_K);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificGasConstantUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public SpecificGasConstantUnit getBaseUnit() {
        return JOULE_PER_KILOGRAM_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificGasConstantUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_KILOGRAM_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificGasConstantUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + SpecificGasConstantUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .dropDegreeSymbols()
                .toString();
    }

    private static class ConversionConstants {
        // 1 BTU/(lb·°R) = 1 BTU/(lb·°F) = 4186.8 J/(kg·K) [Exact]
        private static final double BTU_LB_R_TO_J_KG_K = 4186.8;
    }
}