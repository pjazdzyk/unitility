package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Units of area-specific thermal resistance (R-value), base unit m²·K/W. One degree Celsius interval equals one kelvin.
 * The US R-value unit ft²·°F·h/BTU uses the International Table Btu (NIST SP 811).
 */
public enum ThermalResistanceUnits implements ThermalResistanceUnit {

    SQUARE_METER_KELVIN_PER_WATT("m²·K/W", 1.0),
    SQUARE_METER_CELSIUS_PER_WATT("m²·°C/W", 1.0),
    SQUARE_METER_KELVIN_PER_KILOWATT("m²·K/kW", 1.0E-3),
    SQUARE_FOOT_FAHRENHEIT_HOUR_PER_BTU("ft²·°F·h/BTU", UnitDefinitions.SQUARE_FOOT_FAHRENHEIT_DEGREE_HOUR_PER_BTU);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    ThermalResistanceUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    ThermalResistanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public ThermalResistanceUnit getBaseUnit() {
        return SQUARE_METER_KELVIN_PER_WATT;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    /**
     * Resolves a unit from its symbol, ignoring letter case, spaces, degree signs and the multiplication mark.
     * Returns the base unit for a null or blank symbol.
     *
     * @param rawSymbol unit symbol
     * @return the matching unit
     * @throws UnitSystemParseException when the symbol is not supported
     */
    public static ThermalResistanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return SQUARE_METER_KELVIN_PER_WATT;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (ThermalResistanceUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + ThermalResistanceUnits.class.getSimpleName());
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

}
