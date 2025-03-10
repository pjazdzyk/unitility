package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum PressureUnits implements PressureUnit {

    PASCAL("Pa", val -> val, val -> val),
    HECTOPASCAL("hPa", val -> val * 1.0E2, val -> val / 1.0E2),
    KILOPASCAL("kPa", val -> val * 1.0E3, val -> val / 1.0E3),
    MEGAPASCAL("MPa", val -> val * 1.0E6, val -> val / 1.0E6),
    BAR("bar", val -> val * 1.0E5, val -> val / 1.0E5),
    MILLIBAR("mbar", val -> val * 100, val -> val / 100),
    TORR("Torr", val -> val * 133.322368421053, val -> val / 133.322368421053),
    PSI("psi", val -> val * 6894.757293168, val -> val / 6894.757293168),
    // Pressure units based on formula P = rho * g * h
    METRE_OF_WATER_10("mH₂O_10", val -> val * 999.5457000320766 * 9.80665, val -> val / (999.5457000320766 * 9.80665)),
    METRE_OF_WATER_60("mH₂O_60", val -> val * 982.6716124974598 * 9.80665, val -> val / (982.6716124974598 * 9.80665)),
    METRE_OF_WATER_95("mH₂O_95", val -> val * 961.269058775685 * 9.80665, val -> val / (961.269058775685 * 9.80665)),
    MILLIMETRE_OF_MERCURY_10("mmHg_10", val -> val * 13570 * 9.80665 * 0.001, val -> val / (13570 * 9.80665 * 0.001)),
    MILLIMETRE_OF_MERCURY_60("mmHg_60", val -> val * 13448 * 9.80665 * 0.001, val -> val / (13448 * 9.80665 * 0.001)),
    MILLIMETRE_OF_MERCURY_95("mmHg_95", val -> val * 13364 * 9.80665 * 0.001, val -> val / (13364 * 9.80665 * 0.001));

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

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
            return getDefaultUnit();
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

    public static PressureUnit getDefaultUnit() {
        return PASCAL;
    }

}