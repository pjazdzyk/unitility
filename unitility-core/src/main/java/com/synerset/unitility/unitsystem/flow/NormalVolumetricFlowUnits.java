package com.synerset.unitility.unitsystem.flow;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Units for {@link NormalVolumetricFlow}. The base unit is the normal cubic metre per second
 * (Nm³/s). Gas flows referenced to <em>different</em> states (normal vs. standard) are related by the
 * ideal-gas reference-state volume ratio {@code V₁/V₂ = (T₁/T₂)·(p₂/p₁)}; the converters below bake in
 * the temperature ratio (all reference pressures are 101.325 kPa, so the pressure ratio is unity).
 *
 * <p><b>Reference states used (change here if your industry convention differs):</b>
 * <ul>
 *   <li><b>Normal (N):</b> 0 °C = 273.15 K, 101.325 kPa (ISO 2533).</li>
 *   <li><b>Standard (S, Sm³/slpm):</b> 15 °C = 288.15 K, 101.325 kPa (ISO 13443).</li>
 *   <li><b>Standard cubic foot (scfm):</b> 60 °F = 288.70556 K, 14.696 psia (≈ 101.325 kPa);
 *       1 ft³ = 0.028316846592 m³ (geometric).</li>
 * </ul>
 * Conversion to the normal base therefore multiplies a standard volume by {@code T_normal/T_standard}.
 */
public enum NormalVolumetricFlowUnits implements NormalVolumetricFlowUnit {

    // Normal-referenced (0 °C) — pure geometric/time factors.
    NORMAL_CUBIC_METERS_PER_SECOND("Nm³/s", val -> val, val -> val),
    NORMAL_CUBIC_METERS_PER_MINUTE("Nm³/min", val -> val / 60.0, val -> val * 60.0),
    NORMAL_CUBIC_METERS_PER_HOUR("Nm³/h", val -> val / 3600.0, val -> val * 3600.0),
    NORMAL_LITERS_PER_SECOND("Nl/s", val -> val / 1000.0, val -> val * 1000.0),
    NORMAL_LITERS_PER_MINUTE("Nl/min", val -> val / 60000.0, val -> val * 60000.0),

    // Standard-referenced (15 °C) — carry the ideal-gas T-ratio 273.15/288.15 to the normal base.
    STANDARD_CUBIC_METERS_PER_HOUR("Sm³/h",
            val -> val * (273.15 / 288.15) / 3600.0,
            val -> val * 3600.0 / (273.15 / 288.15)),
    STANDARD_LITERS_PER_MINUTE("slpm",
            val -> val * (273.15 / 288.15) / 60000.0,
            val -> val * 60000.0 / (273.15 / 288.15)),

    // Standard cubic foot (60 °F) — geometric ft³ + ideal-gas T-ratio 273.15/288.70556 to normal base.
    STANDARD_CUBIC_FEET_PER_MINUTE("scfm",
            val -> val * (0.028316846592 * (273.15 / 288.7055555555556)) / 60.0,
            val -> val * 60.0 / (0.028316846592 * (273.15 / 288.7055555555556)));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    NormalVolumetricFlowUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public NormalVolumetricFlowUnit getBaseUnit() {
        return NORMAL_CUBIC_METERS_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static NormalVolumetricFlowUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return NORMAL_CUBIC_METERS_PER_SECOND;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (NormalVolumetricFlowUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: {" + rawSymbol + "}. Target class: "
                + NormalVolumetricFlowUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .dropDegreeSymbols()
                .toString();
    }
}
