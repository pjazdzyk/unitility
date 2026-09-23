package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Specific fan power: electrical power per unit of volumetric flow.
 *
 * <p>Dimensionally this is a pressure, W/(m³/s) being J/m³ being Pa, and it is deliberately not a
 * {@code Pressure}. An engineer reads it as a fan power and schedules it as one, the imperial
 * figure a US schedule carries is W/cfm rather than an inch of water, and no unit set on a pressure
 * would ever produce that.</p>
 */
public enum SpecificFanPowerUnits implements SpecificFanPowerUnit {

    WATT_PER_CUBIC_METER_PER_SECOND("W/(m³/s)", 1.0),
    /**
     * Watts per litre per second. The same number as kW per (m³/s), and the form most European
     * ventilation schedules are written in.
     */
    WATT_PER_LITRE_PER_SECOND("W/(l/s)", 1000.0),
    KILOWATT_PER_CUBIC_METER_PER_SECOND("kW/(m³/s)", 1000.0),
    /**
     * Watts per cubic foot per minute. Built from the exact definition of the foot, so the factor
     * is the reciprocal of the cubic-foot-per-minute definition already in the catalog rather than
     * a number typed in from somewhere.
     */
    WATT_PER_CUBIC_FOOT_PER_MINUTE("W/cfm", 1.0 / UnitDefinitions.CUBIC_FOOT_PER_MINUTE);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}).
     * Both converters are built from that one number (see {@link LinearScale}), so the inverse
     * cannot disagree with the forward.
     */
    SpecificFanPowerUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    SpecificFanPowerUnits(String symbol, DoubleUnaryOperator toBaseConverter,
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
    public SpecificFanPowerUnit getBaseUnit() {
        return WATT_PER_CUBIC_METER_PER_SECOND;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static SpecificFanPowerUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return WATT_PER_CUBIC_METER_PER_SECOND;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (SpecificFanPowerUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}."
                                           + " Target class: "
                                           + SpecificFanPowerUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .unifyAerialAndVol()
                .unifyMultiAndDiv()
                .toString();
    }

}
