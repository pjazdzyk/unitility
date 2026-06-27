package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Thermal conductance {@code G} (units of power per temperature difference, W/K). This is the
 * lumped conductor strength in a thermal network: heat flow {@code Q = G·ΔT}. It is distinct from
 * {@link ThermalConductivity} (W/(m·K), a material property) and {@link HeatTransferCoefficient}
 * (W/(m²·K), a surface property).
 */
public enum ThermalConductanceUnits implements ThermalConductanceUnit {

    WATTS_PER_KELVIN("W/K", val -> val, val -> val),
    KILOWATTS_PER_KELVIN("kW/K", val -> val * 1000.0, val -> val / 1000.0),
    BTU_PER_HOUR_FAHRENHEIT("BTU/(h·°F)",
            val -> val * ConversionConstants.BTU_H_F_TO_W_K,
            val -> val / ConversionConstants.BTU_H_F_TO_W_K);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    ThermalConductanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ThermalConductanceUnit getBaseUnit() {
        return WATTS_PER_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static ThermalConductanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return WATTS_PER_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (ThermalConductanceUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + ThermalConductanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }

    private static class ConversionConstants {
        // 1 BTU_IT/h = 0.29307107017 W; per °F difference = per (5/9) K, so multiply by 9/5.
        private static final double BTU_H_F_TO_W_K = 0.29307107017 * 9.0 / 5.0;
    }
}
