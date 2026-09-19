package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.util.StringTransformer;
import com.synerset.unitility.unitsystem.definitions.LinearScale;
import com.synerset.unitility.unitsystem.definitions.UnitDefinitions;

import java.util.function.DoubleUnaryOperator;

/**
 * Data size units. The multiples are binary (1 KB = 1024 B, ..., 1 PB = 2^50 B), so in SI and IEC terms the symbols
 * "KB", "MB", "GB", "TB", "PB" denote KiB, MiB, GiB, TiB, PiB (in SI, 1 kB = 1000 B). The values are the intended
 * ones and are kept. The symbols are kept too, because changing them would break parsing of existing strings.
 */
public enum DataSizeUnits implements DataSizeUnit {

    BYTE("B", 1.0),
    BIT("bit", UnitDefinitions.BIT),
    KILOBYTE("KB", UnitDefinitions.KIBIBYTE),
    MEGABYTE("MB", UnitDefinitions.MEBIBYTE),
    GIGABYTE("GB", UnitDefinitions.GIBIBYTE),
    TERABYTE("TB", UnitDefinitions.TEBIBYTE),
    PETABYTE("PB", UnitDefinitions.PEBIBYTE); // Max safe unit to fit in double (1 PB = 2^50 Bytes)

    private final String symbol;
    private final DoubleUnaryOperator toBaseConverter;
    private final DoubleUnaryOperator fromBaseToUnitConverter;

    /**
     * A linear unit, declared by its scale to the base unit ({@code base = value * scaleToBase}). Both
     * converters are built from that one number (see {@link LinearScale}), so the inverse cannot disagree
     * with the forward.
     */
    DataSizeUnits(String symbol, double scaleToBase) {
        this(symbol, LinearScale.toBase(scaleToBase), LinearScale.fromBase(scaleToBase));
    }

    DataSizeUnits(String symbol, DoubleUnaryOperator toBaseConverter, DoubleUnaryOperator fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public DataSizeUnits getBaseUnit() {
        return BYTE;
    }

    @Override
    public double toValueInBaseUnit(double valueInThisUnit) {
        return toBaseConverter.applyAsDouble(valueInThisUnit);
    }

    @Override
    public double fromValueInBaseUnit(double valueInBaseUnit) {
        return fromBaseToUnitConverter.applyAsDouble(valueInBaseUnit);
    }

    public static DataSizeUnit fromSymbol(String rawSymbol) {
        if (rawSymbol == null || rawSymbol.isBlank()) {
            return BYTE;
        }
        String requestedSymbol = unifySymbol(rawSymbol);
        for (DataSizeUnit unit : values()) {
            String currentSymbol = unifySymbol(unit.getSymbol());
            if (currentSymbol.equalsIgnoreCase(requestedSymbol)) {
                return unit;
            }
        }
        throw new UnitSystemParseException("Unsupported unit symbol: " + "{" + rawSymbol + "}." + " Target class: "
                + DataSizeUnits.class.getSimpleName());
    }

    private static String unifySymbol(String inputString) {
        return StringTransformer.of(inputString)
                .trimLowerAndClean()
                .toString();
    }
}