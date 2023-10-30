package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

public enum TemperatureUnits implements TemperatureUnit {

    KELVIN("K", val -> val, val -> val),
    CELSIUS("°C", val -> val + 273.15, val -> val - 273.15),
    FAHRENHEIT("°F", val -> (val - 32.0) * (5.0 / 9.0) + 273.15, val -> (val - 273.15) * (9.0 / 5.0) + 32.0);

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    TemperatureUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public TemperatureUnit getBaseUnit() {
        return KELVIN;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static TemperatureUnit fromSymbol(String rawSymbol) {
        String requestedSymbol = formatSymbol(rawSymbol).trim();
        for (TemperatureUnits unit : values()) {
            String currentSymbol = formatSymbol(unit.symbol);
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemArgumentException("Unsupported symbol: " + rawSymbol + ", class: "
                + TemperatureUnits.class.getSimpleName());
    }

    private static String formatSymbol(String inputString) {
        return inputString
                .toLowerCase()
                .replace(" ", "")
                .replace("o", "")
                .replace("°", "")
                .replace("deg", "");
    }

}

