package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

import static com.synerset.unitility.unitsystem.util.Constants.DENSITY_MERCURY_10;
import static com.synerset.unitility.unitsystem.util.Constants.DENSITY_MERCURY_60;
import static com.synerset.unitility.unitsystem.util.Constants.DENSITY_MERCURY_95;
import static com.synerset.unitility.unitsystem.util.Constants.DENSITY_WATER_10;
import static com.synerset.unitility.unitsystem.util.Constants.DENSITY_WATER_60;
import static com.synerset.unitility.unitsystem.util.Constants.DENSITY_WATER_95;
import static com.synerset.unitility.unitsystem.util.Constants.GRAVITY_SI;
import static com.synerset.unitility.unitsystem.util.Constants.HECTO;
import static com.synerset.unitility.unitsystem.util.Constants.KILO;
import static com.synerset.unitility.unitsystem.util.Constants.MEGA;
import static com.synerset.unitility.unitsystem.util.Constants.MILLI;
import static com.synerset.unitility.unitsystem.util.Constants.PASCAL_PER_PSI;
import static com.synerset.unitility.unitsystem.util.Constants.PASCAL_PER_TORR;

public enum PressureUnits implements PressureUnit {

    PASCAL("Pa", val -> val, val -> val),
    HECTOPASCAL("hPa", val -> val * HECTO, val -> val / HECTO),
    KILOPASCAL("kPa", val -> val * KILO, val -> val / KILO),
    MEGAPASCAL("MPa", val -> val * MEGA, val -> val / MEGA),
    BAR("bar", val -> val * HECTO * KILO, val -> val / (HECTO * KILO)),
    MILLIBAR("mbar", val -> val * HECTO, val -> val / HECTO),
    TORR("Torr", val -> val * PASCAL_PER_TORR, val -> val / PASCAL_PER_TORR),
    PSI("psi", val -> val * PASCAL_PER_PSI, val -> val / PASCAL_PER_PSI),
    // Pressure units based on formula P = rho * g * h
    METRE_OF_WATER_10("mH₂O_10", val -> val * DENSITY_WATER_10 * GRAVITY_SI, val -> val / (DENSITY_WATER_10 * GRAVITY_SI)),
    METRE_OF_WATER_60("mH₂O_60", val -> val * DENSITY_WATER_60 * GRAVITY_SI, val -> val / (DENSITY_WATER_60 * GRAVITY_SI)),
    METRE_OF_WATER_95("mH₂O_95", val -> val * DENSITY_WATER_95 * GRAVITY_SI, val -> val / (DENSITY_WATER_95 * GRAVITY_SI)),
    MILLIMETRE_OF_MERCURY_10("mmHg_10", val -> val * DENSITY_MERCURY_10 * GRAVITY_SI * MILLI, val -> val / (DENSITY_MERCURY_10 * GRAVITY_SI * MILLI)),
    MILLIMETRE_OF_MERCURY_60("mmHg_60", val -> val * DENSITY_MERCURY_60 * GRAVITY_SI * MILLI, val -> val / (DENSITY_MERCURY_60 * GRAVITY_SI * MILLI)),
    MILLIMETRE_OF_MERCURY_95("mmHg_95", val -> val * DENSITY_MERCURY_95 * GRAVITY_SI * MILLI, val -> val / (DENSITY_MERCURY_95 * GRAVITY_SI * MILLI));

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