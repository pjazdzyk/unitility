package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;

import java.util.function.DoubleUnaryOperator;

public enum EnergyDensityUnits implements EnergyDensityUnit {

    // ─── SI / metric (base J/m³) ───
    JOULE_PER_CUBIC_METER("J/m³", val -> val, val -> val),
    KILOJOULE_PER_CUBIC_METER("kJ/m³", val -> val * 1.0E3, val -> val / 1.0E3),
    MEGAJOULE_PER_CUBIC_METER("MJ/m³", val -> val * 1.0E6, val -> val / 1.0E6),
    // Watt-hour basis — European gas-metering / billing unit (1 Wh = 3600 J exactly).
    WATT_HOUR_PER_CUBIC_METER("Wh/m³", val -> val * 3.6E3, val -> val / 3.6E3),
    KILOWATT_HOUR_PER_CUBIC_METER("kWh/m³", val -> val * 3.6E6, val -> val / 3.6E6),
    // 1 kcal_IT = 4186.8 J exactly (older metric calorific unit, still common).
    KILOCALORIE_PER_CUBIC_METER("kcal/m³", val -> val * 4186.8, val -> val / 4186.8),
    // ─── Imperial / US customary ───
    // US gas industry. 1 BTU_IT = 1055.05585262 J, 1 ft³ = 0.3048³ = 0.028316846592 m³ (both exact),
    // so BTU_IT/ft³ = 1055.05585262 / 0.028316846592 = 37258.945802… J/m³.
    BTU_PER_CUBIC_FOOT("BTU/ft³", val -> val * 37258.9458020, val -> val / 37258.9458020),
    // Thousand BTU per cubic foot (US pipeline heating-value scale, MBTU/ft³).
    KILO_BTU_PER_CUBIC_FOOT("MBTU/ft³", val -> val * 37258945.8020, val -> val / 37258945.8020);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    EnergyDensityUnits(String symbol, DoubleUnaryOperator toBaseConverter,
                       DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() { return symbol; }

    @Override
    public EnergyDensityUnit getBaseUnit() { return JOULE_PER_CUBIC_METER; }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static EnergyDensityUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return JOULE_PER_CUBIC_METER;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (EnergyDensityUnit unit : values()) {
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
