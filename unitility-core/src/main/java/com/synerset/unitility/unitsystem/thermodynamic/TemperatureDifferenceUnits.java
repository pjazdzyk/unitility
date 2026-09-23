package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Units of a temperature <b>interval</b>.
 *
 * <p>An interval is not a point on a scale, and that is the whole reason this type exists.
 * {@link TemperatureUnits} is affine: converting 20 °C to Fahrenheit adds the ice-point offset and
 * gives 68 °F. A <i>rise</i> of 20 °C is 36 °F, not 68 °F, because the offsets on the two ends
 * cancel. Every unit here is therefore purely linear, and a degree Celsius interval is exactly a
 * kelvin interval.</p>
 *
 * <p>Using {@code Temperature} for a difference is a silent error: it type-checks, it serialises,
 * and it is only wrong once somebody switches to imperial units.</p>
 */
public enum TemperatureDifferenceUnits implements TemperatureDifferenceUnit {

    KELVIN("K", 1.0),
    CELSIUS("°C", 1.0),
    FAHRENHEIT("°F", UnitDefinitions.FAHRENHEIT_DEGREE),
    RANKINE("°R", UnitDefinitions.FAHRENHEIT_DEGREE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    TemperatureDifferenceUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    TemperatureDifferenceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public TemperatureDifferenceUnit getBaseUnit() {
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

    public static TemperatureDifferenceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (TemperatureDifferenceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + TemperatureDifferenceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .dropDegreeSymbols()
                .toString();
    }

}
