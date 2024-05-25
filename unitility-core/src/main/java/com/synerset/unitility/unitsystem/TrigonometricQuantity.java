package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.function.DoubleUnaryOperator;

/**
 * Interface representing a calculable quantity with operations for basic arithmetics.
 *
 * @param <Q> The type of {@link PhysicalQuantity} implementing this interface.
 */
public interface TrigonometricQuantity<Q extends CalculableQuantity<AngleUnit, Q>> extends CalculableQuantity<AngleUnit, Q> {

    // Trigonometric
    /**
     * Calculate the sine of the physical quantity's value in a current unit.<p>
     * If the quantity is an instance of {@link AngleUnit} its value will be automatically
     * converted to radians before calculating resulting value.
     *
     * @return A new physical quantity with the sine of the current value in a current unit.
     */
    default Q sin() {
        return applyTrigonometricFunction(Math::sin);
    }

    /**
     * Calculate the hyperbolic sine of the physical quantity's value.
     *
     * @return A new physical quantity with the hyperbolic sine of the current value.
     */
    default Q sinh() {
        return applyTrigonometricFunction(Math::sinh);
    }

    /**
     * Calculate the arcsine of the physical quantity's value.
     *
     * @return A new physical quantity with the arcsine of the current value.
     * @throws UnitSystemArgumentException if the value is out of the range [-1, 1].
     */
    default Q asin() {
        double value = getValueInRadians();
        if (value < -1 || value > 1) {
            throw new UnitSystemArgumentException("Value out of range for arcsine. Valid range is [-1, 1].");
        }
        return applyTrigonometricFunction(Math::asin);
    }

    /**
     * Calculate the cosine of the physical quantity's value in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the cosine of the current value.
     */
    default Q cos() {
        return applyTrigonometricFunction(Math::cos);
    }

    /**
     * Calculate the hyperbolic cosine of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the hyperbolic cosine of the current value.
     */
    default Q cosh() {
        return applyTrigonometricFunction(Math::cosh);
    }

    /**
     * Calculate the arccosine of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the arccosine of the current value.
     * @throws UnitSystemArgumentException if the value is out of the range [-1, 1].
     */
    default Q acos() {
        double value = getValueInRadians();
        if (value < -1 || value > 1) {
            throw new UnitSystemArgumentException("Value out of range for arccosine. Valid range is [-1, 1].");
        }
        return applyTrigonometricFunction(Math::acos);
    }

    /**
     * Calculate the tangent of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the tangent of the current value.
     * @throws UnitSystemArgumentException if the value is an odd multiple of π/2.
     */
    default Q tan() {
        double value = getValueInRadians();
        if (isMultipleOfPiOverTwo(value)) {
            throw new UnitSystemArgumentException("Tangent is undefined for odd multiples of π/2.");
        }
        return applyTrigonometricFunction(Math::tan);
    }

    /**
     * Calculate the hyperbolic tangent of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the hyperbolic tangent of the current value.
     */
    default Q tanh() {
        return applyTrigonometricFunction(Math::tanh);
    }

    /**
     * Calculate the arctangent of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the arctangent of the current value.
     */
    default Q atan() {
        return applyTrigonometricFunction(Math::atan);
    }

    /**
     * Calculate the cotangent of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the cotangent of the current value.
     * @throws UnitSystemArgumentException if the value is a multiple of π.
     */
    default Q cot() {
        double value = getValueInRadians();
        if (isMultipleOfPi(value)) {
            throw new UnitSystemArgumentException("Cotangent is undefined for multiples of π.");
        }
        return withValue(1 / tan().getValue());
    }

    /**
     * Calculate the hyperbolic cotangent of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the hyperbolic cotangent of the current value.
     * @throws UnitSystemArgumentException if the value is 0.
     */
    default Q coth() {
        double value = getValueInRadians();
        if (value == 0) {
            throw new UnitSystemArgumentException("Hyperbolic cotangent is undefined for 0.");
        }
        return withValue(1 / tanh().getValue());
    }

    /**
     * Calculate the arccotangent of the physical quantity's in a current unit.
     * Use toUnit(Unit) if intended to convert value in other supported unit.
     *
     * @return A new physical quantity with the arccotangent of the current value.
     */
    default Q acot() {
        return withValue(1 / atan().getValue());
    }

    @SuppressWarnings("unchecked")
    private Q applyTrigonometricFunction(DoubleUnaryOperator unaryOperator) {
        PhysicalQuantity<AngleUnit> unitInRadians = toUnit(AngleUnits.RADIANS);
        double resultingValue = unaryOperator.applyAsDouble(unitInRadians.getValue());
        return (Q) unitInRadians.withValue(resultingValue).toUnit(getUnitType());
    }

    private double getValueInRadians() {
        return toUnit(AngleUnits.RADIANS).getValue();
    }

    private boolean isMultipleOfPiOverTwo(double value) {
        return Math.abs((value / (Math.PI / 2)) % 1) < 1e-10;
    }

    private boolean isMultipleOfPi(double value) {
        return Math.abs((value / Math.PI) % 1) < 1e-10;
    }
}
