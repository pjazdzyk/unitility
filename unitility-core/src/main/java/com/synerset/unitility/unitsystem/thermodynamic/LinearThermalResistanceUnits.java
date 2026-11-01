package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Units of thermal resistance per unit length of pipe, base unit m·K/W. One degree Celsius interval equals one kelvin.
 * The US unit ft·°F·h/BTU uses the International Table Btu (NIST SP 811).
 */
public enum LinearThermalResistanceUnits implements LinearThermalResistanceUnit {

    METER_KELVIN_PER_WATT("m·K/W", 1.0),
    METER_CELSIUS_PER_WATT("m·°C/W", 1.0),
    FOOT_FAHRENHEIT_HOUR_PER_BTU("ft·°F·h/BTU", UnitDefinitions.FOOT_FAHRENHEIT_DEGREE_HOUR_PER_BTU);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    LinearThermalResistanceUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    LinearThermalResistanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public LinearThermalResistanceUnit getBaseUnit() {
        return METER_KELVIN_PER_WATT;
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
    public static LinearThermalResistanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return METER_KELVIN_PER_WATT;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (LinearThermalResistanceUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + LinearThermalResistanceUnits.class.getSimpleName());
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
