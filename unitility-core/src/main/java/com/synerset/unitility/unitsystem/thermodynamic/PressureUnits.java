package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum PressureUnits implements PressureUnit {

    PASCAL("Pa", 1.0),
    HECTOPASCAL("hPa", 1.0E2),
    KILOPASCAL("kPa", 1.0E3),
    MEGAPASCAL("MPa", 1.0E6),
    BAR("bar", UnitDefinitions.BAR),
    MILLIBAR("mbar", 1.0E2),
    TORR("Torr", UnitDefinitions.TORR),
    PSI("psi", UnitDefinitions.PSI),
    // Property-based units, p = rho * g_n * h: the density is a property at a stated temperature, not a definition.
    // Water: NIST Chemistry WebBook densities at 1 atm (IAPWS-95), see UnitDefinitions.
    METRE_OF_WATER_10("mH₂O_10", UnitDefinitions.METRE_OF_WATER_10C),
    METRE_OF_WATER_60("mH₂O_60", UnitDefinitions.METRE_OF_WATER_60C),
    METRE_OF_WATER_95("mH₂O_95", UnitDefinitions.METRE_OF_WATER_95C),
    // Mercury: UNVERIFIED. No primary source for the density of mercury at 10, 60 or 95 °C was read, so these
    // factors are the 4.1.0 values, unchanged. NIST SP 811 fn 12: such units do not justify many digits anyway.
    MILLIMETRE_OF_MERCURY_10("mmHg_10", UnitDefinitions.MILLIMETRE_OF_MERCURY_10C),
    MILLIMETRE_OF_MERCURY_60("mmHg_60", UnitDefinitions.MILLIMETRE_OF_MERCURY_60C),
    MILLIMETRE_OF_MERCURY_95("mmHg_95", UnitDefinitions.MILLIMETRE_OF_MERCURY_95C);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    PressureUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    PressureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public PressureUnit getBaseUnit() {
        return PASCAL;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static PressureUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return PASCAL;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (PressureUnit unit : values()) {
            if (hasMatchBeenFound(unit, requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                                           + PressureUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyAerialAndVol()
                .toString();
    }

    private static boolean hasMatchBeenFound(PressureUnit currentUnit, String requestedSymbol) {
        String currentSymbol = unifySymbol(currentUnit.getSymbol());
        if(currentUnit == PressureUnits.METRE_OF_WATER_10 || currentUnit == PressureUnits.MILLIMETRE_OF_MERCURY_10){
            String truncatedSymbol = currentSymbol.replace("10", "");
            return truncatedSymbol.equalsIgnoreCase(requestedSymbol) || currentSymbol.equalsIgnoreCase(requestedSymbol);
        }
        return currentSymbol.equalsIgnoreCase(requestedSymbol);
    }

}