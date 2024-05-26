package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

/**
 * Interface representing a calculable quantity with operations for basic arithmetic and trigonometric functions.
 *
 * @param <Q> The type of {@link PhysicalQuantity} implementing this interface.
 */
public interface TrigonometricQuantity<Q extends CalculableQuantity<AngleUnit, Q>> extends CalculableQuantity<AngleUnit, Q> {

    /**
     * Calculates the sine of the physical quantity's value in its current unit.
     * If the quantity is an instance of {@link AngleUnit}, its value will be automatically
     * converted to radians before calculating the resulting value.
     *
     * @return The sine of the current value.
     */
    default double sin() {
        return applyTrigonometricFunction(Math::sin);
    }

    /**
     * Calculates the hyperbolic sine of the physical quantity's value.
     *
     * @return The hyperbolic sine of the current value.
     */
    default double sinh() {
        return applyTrigonometricFunction(Math::sinh);
    }

    /**
     * Calculates the arcsine of the physical quantity's value.
     *
     * @return The arcsine of the current value.
     * @throws UnitSystemArgumentException if the value is out of the range [-1, 1].
     */
    default double asin() {
        double value = getValueInRadians();
        if (value < -1 || value > 1) {
            throw new UnitSystemArgumentException("Value out of range for arcsine. Valid range is [-1, 1].");
        }
        return applyTrigonometricFunction(Math::asin);
    }

    /**
     * Calculates the cosine of the physical quantity's value in its current unit.
     *
     * @return The cosine of the current value.
     */
    default double cos() {
        return applyTrigonometricFunction(Math::cos);
    }

    /**
     * Calculates the hyperbolic cosine of the physical quantity's value in its current unit.
     *
     * @return The hyperbolic cosine of the current value.
     */
    default double cosh() {
        return applyTrigonometricFunction(Math::cosh);
    }

    /**
     * Calculates the arccosine of the physical quantity's value in its current unit.
     *
     * @return The arccosine of the current value.
     * @throws UnitSystemArgumentException if the value is out of the range [-1, 1].
     */
    default double acos() {
        double value = getValueInRadians();
        if (value < -1 || value > 1) {
            throw new UnitSystemArgumentException("Value out of range for arccosine. Valid range is [-1, 1].");
        }
        return applyTrigonometricFunction(Math::acos);
    }

    /**
     * Calculates the tangent of the physical quantity's value in its current unit.
     *
     * @return The tangent of the current value.
     * @throws UnitSystemArgumentException if the value is an odd multiple of π/2.
     */
    default double tan() {
        double value = getValueInRadians();
        if (isMultipleOfPiOverTwo(value)) {
            throw new UnitSystemArgumentException("Tangent is undefined for odd multiples of π/2.");
        }
        return applyTrigonometricFunction(Math::tan);
    }

    /**
     * Calculates the hyperbolic tangent of the physical quantity's value in its current unit.
     *
     * @return The hyperbolic tangent of the current value.
     */
    default double tanh() {
        return applyTrigonometricFunction(Math::tanh);
    }

    /**
     * Calculates the arctangent of the physical quantity's value in its current unit.
     *
     * @return The arctangent of the current value.
     */
    default double atan() {
        return applyTrigonometricFunction(Math::atan);
    }

    /**
     * Calculates the cotangent of the physical quantity's value in its current unit.
     *
     * @return The cotangent of the current value.
     * @throws UnitSystemArgumentException if the value is a multiple of π.
     */
    default double cot() {
        double value = getValueInRadians();
        if (isMultipleOfPi(value)) {
            throw new UnitSystemArgumentException("Cotangent is undefined for multiples of π.");
        }
        return 1 / tan();
    }

    /**
     * Calculates the hyperbolic cotangent of the physical quantity's value in its current unit.
     *
     * @return The hyperbolic cotangent of the current value.
     * @throws UnitSystemArgumentException if the value is 0.
     */
    default double coth() {
        double value = getValueInRadians();
        if (value == 0) {
            throw new UnitSystemArgumentException("Hyperbolic cotangent is undefined for 0.");
        }
        return 1 / tanh();
    }

    /**
     * Calculates the arccotangent of the physical quantity's value in its current unit.
     *
     * @return The arccotangent of the current value.
     */
    default double acot() {
        return 1 / atan();
    }

    /**
     * Applies a trigonometric function to the physical quantity's value.
     *
     * @param unaryOperator the trigonometric function to apply.
     * @return The result of the trigonometric function.
     */
    private double applyTrigonometricFunction(DoubleUnaryOperator unaryOperator) {
        double unitInRadians = getValueInRadians();
        return unaryOperator.applyAsDouble(unitInRadians);
    }

    /**
     * Gets the value of the physical quantity in radians.
     *
     * @return The value in radians.
     */
    private double getValueInRadians() {
        return toUnit(AngleUnits.RADIANS).getValue();
    }

    /**
     * Checks if the value is a multiple of π/2.
     *
     * @param value the value to check.
     * @return True if the value is a multiple of π/2, false otherwise.
     */
    private boolean isMultipleOfPiOverTwo(double value) {
        return Math.abs((value / (Math.PI / 2)) % 1) < 1e-10;
    }

    /**
     * Checks if the value is a multiple of π.
     *
     * @param value the value to check.
     * @return True if the value is a multiple of π, false otherwise.
     */
    private boolean isMultipleOfPi(double value) {
        return Math.abs((value / Math.PI) % 1) < 1e-10;
    }

}
