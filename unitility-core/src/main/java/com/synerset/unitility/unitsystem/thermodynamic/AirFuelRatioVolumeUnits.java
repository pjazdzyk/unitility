package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

/**
 * Volumetric air-fuel ratio (volume of air per volume of fuel gas). A volume ratio is unit-invariant, the
 * numeric value is identical in any consistent pair of volume units, so all unit converters are identity.
 */
public enum AirFuelRatioVolumeUnits implements AirFuelRatioVolumeUnit {

    CUBIC_METER_PER_CUBIC_METER("m³/m³", val -> val, val -> val),                          // Base SI unit
    NORMAL_CUBIC_METER_PER_NORMAL_CUBIC_METER("Nm³/Nm³", val -> val, val -> val),
    CUBIC_FOOT_PER_CUBIC_FOOT("ft³/ft³", val -> val, val -> val),
    STANDARD_CUBIC_FOOT_PER_STANDARD_CUBIC_FOOT("scf/scf", val -> val, val -> val);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    AirFuelRatioVolumeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public AirFuelRatioVolumeUnit getBaseUnit() {
        return CUBIC_METER_PER_CUBIC_METER;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static AirFuelRatioVolumeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return CUBIC_METER_PER_CUBIC_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (AirFuelRatioVolumeUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equals(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + rawSymbol);
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimAndClean()
                .unifyMultiAndDiv()
                .toString();
    }
}
