package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum PressureCoefficientUnits implements PressureCoefficientUnit {

    PASCAL_PER_KELVIN("Pa/K", val -> val, val -> val),
    KILOPASCAL_PER_KELVIN("kPa/K", val -> val * 1000.0, val -> val / 1000.0),
    MEGAPASCAL_PER_KELVIN("MPa/K", val -> val * 1_000_000.0, val -> val / 1_000_000.0),
    BAR_PER_KELVIN("bar/K", val -> val * 100_000.0, val -> val / 100_000.0),
    ATMOSPHERE_PER_KELVIN("atm/K", val -> val * 101325.0, val -> val / 101325.0),
    PSI_PER_RANKINE("psi/°R", val -> val * 12410.56312770305, val -> val / 12410.56312770305),
    PSI_PER_FAHRENHEIT("psi/°F", val -> val * 12410.56312770305, val -> val / 12410.56312770305);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    PressureCoefficientUnits(String symbol, DoubleUnaryOperator toBaseConverter,
                             DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public PressureCoefficientUnit getBaseUnit() {
        return PASCAL_PER_KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static PressureCoefficientUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return PASCAL_PER_KELVIN;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (PressureCoefficientUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .dropDegreeSymbols()
                .toString();
    }
}
