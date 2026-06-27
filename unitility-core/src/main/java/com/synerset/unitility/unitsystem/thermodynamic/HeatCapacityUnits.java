package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Heat capacity {@code C} (extensive, units of energy per temperature, J/K): the thermal mass of a
 * lumped node, where {@code Q = C·dT/dt}. Distinct from {@link SpecificHeat} (J/(kg·K), per unit mass).
 */
public enum HeatCapacityUnits implements HeatCapacityUnit {

    JOULES_PER_KELVIN("J/K", val -> val, val -> val),
    KILOJOULES_PER_KELVIN("kJ/K", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_FAHRENHEIT("BTU/°F",
            val -> val * ConversionConstants.BTU_F_TO_J_K,
            val -> val / ConversionConstants.BTU_F_TO_J_K);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    HeatCapacityUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public HeatCapacityUnit getBaseUnit() {
        return JOULES_PER_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static HeatCapacityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULES_PER_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (HeatCapacityUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + HeatCapacityUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

    private static class ConversionConstants {
        // 1 BTU_IT = 1055.05585262 J; per °F difference = per (5/9) K, so multiply by 9/5.
        private static final double BTU_F_TO_J_K = 1055.05585262 * 9.0 / 5.0;
    }
}
