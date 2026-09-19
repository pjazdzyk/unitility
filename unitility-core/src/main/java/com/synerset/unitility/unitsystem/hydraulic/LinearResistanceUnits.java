package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum LinearResistanceUnits implements LinearResistanceUnit {

    PASCAL_PER_METER("Pa/m", 1.0),
    // Conventional inch of water (1000 kg/m³, standard gravity), NIST SP 811 App. B.8 2.490 889 E+02 Pa, per 100 ft.
    INCH_OF_WATER_PER_100_FEET("inH₂O/100ft", UnitDefinitions.INCH_OF_WATER_PER_100_FEET),
    // Inch of mercury at 32 °F (NIST SP 811 App. B.8, 3.386 38 E+03 Pa), not the conventional inch of mercury
    // (3.386 389 E+03 Pa), per 100 ft. A property-based unit: see UnitDefinitions.INCH_OF_MERCURY_PER_100_FEET.
    INCH_OF_MERCURY_PER_100_FEET("inHg/100ft", UnitDefinitions.INCH_OF_MERCURY_PER_100_FEET);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    LinearResistanceUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    LinearResistanceUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public LinearResistanceUnit getBaseUnit() {
        return PASCAL_PER_METER;  // Define Pa/m as the base unit
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static LinearResistanceUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return PASCAL_PER_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (LinearResistanceUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + LinearResistanceUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyAerialAndVol()
                .unifyMultiAndDiv()
                .toString();
    }

}
