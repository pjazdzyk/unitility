package com.synerset.unitility.unitsystem.definitions;

import java.util.function.DoubleUnaryOperator;

/**
 * Builds the two converters of a linear unit from its one scale to the base unit, so that the inverse can never
 * disagree with the forward conversion.
 * <p>
 * In general the converters are {@code base = value * scale} and {@code value = base / scale}. When the scale is the
 * reciprocal of a whole number N (a minute, an hour, a milli- or centi- prefix: {@code scale == 1.0 / N}), they are
 * {@code base = value / N} and {@code value = base * N} instead. Those are the same two operations, but N is exact in
 * binary while {@code 1/N} usually is not, so this form is correctly rounded: 1 m³/s is exactly 3 600 000 L/h, not
 * 3 600 000.000 000 000 5.
 */
public final class LinearScale {

    private static final double MAX_EXACT_INTEGER = 9007199254740992.0; // 2^53

    private LinearScale() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param scaleToBase the value, in the base unit, of one of this unit
     * @return the converter from this unit to the base unit
     */
    public static DoubleUnaryOperator toBase(double scaleToBase) {
        double reciprocal = exactReciprocal(scaleToBase);
        if (reciprocal > 0.0) {
            return val -> val / reciprocal;
        }
        return val -> val * scaleToBase;
    }

    /**
     * @param scaleToBase the value, in the base unit, of one of this unit
     * @return the converter from the base unit to this unit
     */
    public static DoubleUnaryOperator fromBase(double scaleToBase) {
        double reciprocal = exactReciprocal(scaleToBase);
        if (reciprocal > 0.0) {
            return val -> val * reciprocal;
        }
        return val -> val / scaleToBase;
    }

    /**
     * The whole number N for which {@code scale} is the nearest double to 1/N, or 0 if there is none.
     */
    static double exactReciprocal(double scale) {
        if (!(scale > 0.0 && scale < 1.0)) {
            return 0.0;
        }
        double candidate = Math.rint(1.0 / scale);
        if (candidate >= 2.0 && candidate <= MAX_EXACT_INTEGER && 1.0 / candidate == scale) {
            return candidate;
        }
        return 0.0;
    }

}
