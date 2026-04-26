package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum SpecificInternalEnergyUnits implements SpecificInternalEnergyUnit {

    JOULE_PER_KILOGRAM("J/kg", val -> val, val -> val),
    KILOJOULE_PER_KILOGRAM("kJ/kg", val -> val * 1000.0, val -> val / 1000.0),
    MEGAJOULE_PER_KILOGRAM("MJ/kg", val -> val * 1_000_000.0, val -> val / 1_000_000.0),
    BTU_PER_POUND("BTU/lb", val -> val * 2326.0, val -> val / 2326.0),
    CALORIE_PER_GRAM("cal/g", val -> val * 4186.8, val -> val / 4186.8);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    SpecificInternalEnergyUnits(String symbol,
                                DoubleUnaryOperator toBaseConverter,
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
    public SpecificInternalEnergyUnit getBaseUnit() {
        return JOULE_PER_KILOGRAM;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificInternalEnergyUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_KILOGRAM;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificInternalEnergyUnits unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: {" + rawSymbol + "}. Target class: "
                + SpecificInternalEnergyUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyMultiAndDiv()
                .toString();
    }
}