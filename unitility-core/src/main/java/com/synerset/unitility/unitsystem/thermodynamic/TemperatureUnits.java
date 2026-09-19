package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum TemperatureUnits implements TemperatureUnit {

    KELVIN("K", 1.0),
    // Affine units, per NIST SP 811 App. B.8: T/K = t/°C + 273.15, and t/°C = (t/°F - 32)/1.8.
    CELSIUS("°C", 1.0, 0.0),
    FAHRENHEIT("°F", UnitDefinitions.FAHRENHEIT_DEGREE, UnitDefinitions.FAHRENHEIT_AT_ICE_POINT);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    TemperatureUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    /**
     * An affine unit, declared by its scale to the kelvin and its reading at the ice point:
     * {@code T/K = (t - valueAtIcePoint) * scaleToKelvin + 273.15}. This is the same line as
     * {@code base = t * scale + offset} with {@code offset = 273.15 - valueAtIcePoint * scale}, but anchored at the
     * ice point, so that 0 °C and 32 °F convert to exactly 273.15 K and back without a rounding residue.
     */
    TemperatureUnits(String symbol, double scaleToKelvin, double valueAtIcePoint) {
        this(symbol,
                val -> (val - valueAtIcePoint) * scaleToKelvin + UnitDefinitions.ICE_POINT,
                val -> (val - UnitDefinitions.ICE_POINT) / scaleToKelvin + valueAtIcePoint);
    }

    TemperatureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public TemperatureUnit getBaseUnit() {
        return KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static TemperatureUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (TemperatureUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + TemperatureUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .dropDegreeSymbols()
                .toString();
    }

}

