package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum MolarEnthalpyUnits implements MolarEnthalpyUnit {

    JOULE_PER_MOLE("J/mol", val -> val, val -> val),
    KILOJOULE_PER_MOLE("kJ/mol", val -> val * 1000.0, val -> val / 1000.0),
    MEGAJOULE_PER_KILOMOLE("MJ/kmol", val -> val * 1000.0, val -> val / 1000.0),
    MILLIJOULE_PER_MILLIMOLE("mJ/mmol", val -> val, val -> val),
    BTU_PER_POUND_MOLE("BTU/lbmol", val -> val * 2.326, val -> val / 2.326),
    CALORIE_PER_MOLE("cal/mol", val -> val * 4.1868, val -> val / 4.1868),
    KILOCALORIE_PER_MOLE("kcal/mol", val -> val * 4186.8, val -> val / 4186.8);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    MolarEnthalpyUnits(String symbol, DoubleUnaryOperator toBaseConverter, 
                       DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public MolarEnthalpyUnit getBaseUnit() { return JOULE_PER_MOLE; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static MolarEnthalpyUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_MOLE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (MolarEnthalpyUnit unit : values()) {
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
                .toString();
    }
}