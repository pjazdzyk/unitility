package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

/**
 * Thermal conductance {@code G} (units of power per temperature difference, W/K). This is the
 * lumped conductor strength in a thermal network: heat flow {@code Q = G·ΔT}. It is distinct from
 * {@link ThermalConductivity} (W/(m·K), a material property) and {@link HeatTransferCoefficient}
 * (W/(m²·K), a surface property).
 */
public enum ThermalConductanceUnits implements ThermalConductanceUnit {

    WATTS_PER_KELVIN("W/K", 1.0),
    KILOWATTS_PER_KELVIN("kW/K", 1.0E3),
    BTU_PER_HOUR_FAHRENHEIT("BTU/(h·°F)", UnitDefinitions.BTU_PER_HOUR_FAHRENHEIT_DEGREE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    ThermalConductanceUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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

}
