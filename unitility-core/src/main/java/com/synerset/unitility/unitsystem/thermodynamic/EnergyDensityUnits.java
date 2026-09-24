package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

public enum EnergyDensityUnits implements EnergyDensityUnit {

    // ─── SI / metric (base J/m³) ───
    JOULE_PER_CUBIC_METER("J/m³", 1.0),
    KILOJOULE_PER_CUBIC_METER("kJ/m³", 1.0E3),
    MEGAJOULE_PER_CUBIC_METER("MJ/m³", 1.0E6),
    // Watt-hour basis — European gas-metering / billing unit (1 Wh = 3600 J exactly).
    WATT_HOUR_PER_CUBIC_METER("Wh/m³", UnitDefinitions.WATT_HOUR),
    KILOWATT_HOUR_PER_CUBIC_METER("kWh/m³", UnitDefinitions.KILOWATT_HOUR),
    // 1 kcal_IT = 4186.8 J exactly (older metric calorific unit, still common).
    KILOCALORIE_PER_CUBIC_METER("kcal/m³", UnitDefinitions.KILOCALORIE_IT),
    // ─── Imperial / US customary ───
    // US gas industry. 1 BTU_IT = 1055.05585262 J, 1 ft³ = 0.3048³ = 0.028316846592 m³ (both exact),
    // so BTU_IT/ft³ = 1055.05585262 / 0.028316846592 = 37258.945802… J/m³.
    BTU_PER_CUBIC_FOOT("BTU/ft³", UnitDefinitions.BTU_PER_CUBIC_FOOT),
    // Thousand BTU per cubic foot (US pipeline heating-value scale, MBTU/ft³).
    KILO_BTU_PER_CUBIC_FOOT("MBTU/ft³", UnitDefinitions.KILO_BTU_PER_CUBIC_FOOT);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    EnergyDensityUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

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
                .unifyAerialAndVol()
                .toString();
    }
}
