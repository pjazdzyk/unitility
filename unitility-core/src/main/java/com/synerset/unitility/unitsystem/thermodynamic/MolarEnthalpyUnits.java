package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum MolarEnthalpyUnits implements MolarEnthalpyUnit {

    JOULE_PER_MOLE("J/mol", 1.0),
    KILOJOULE_PER_MOLE("kJ/mol", 1.0E3),
    MEGAJOULE_PER_KILOMOLE("MJ/kmol", 1.0E3),
    MILLIJOULE_PER_MILLIMOLE("mJ/mmol", 1.0),
    BTU_PER_POUND_MOLE("BTU/lbmol", UnitDefinitions.BTU_PER_POUND_MOLE),
    CALORIE_PER_MOLE("cal/mol", UnitDefinitions.CALORIE_IT),
    KILOCALORIE_PER_MOLE("kcal/mol", UnitDefinitions.KILOCALORIE_IT);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    MolarEnthalpyUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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