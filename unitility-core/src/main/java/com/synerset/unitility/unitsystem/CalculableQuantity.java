package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.util.ValueFormatter;

/**
 * Interface representing a calculable quantity with operations for basic arithmetics.
 *
 * @param <U> The type of unit associated with the quantity.
 * @param <Q> The type of {@link PhysicalQuantity} implementing this interface.
 */
public interface CalculableQuantity<U extends Unit, Q extends CalculableQuantity<U, Q>> extends PhysicalQuantity<U> {

    /**
     * Create a new physical quantity with new value of the same unit.
     *
     * @param value The value for the new physical quantity.
     * @return A new physical quantity with the specified value.
     */
    @Override
    Q withValue(double value);

    // Handling double as an input argument

    /**
     * Add a constant value to the physical quantity.
     * Value is added to the value of quantity current unit.
     *
     * @param value The value to add.
     * @return A new physical quantity with the added value.
     */
    default Q plus(double value) {
        double newValue = getValue() + value;
        return withValue(newValue);
    }

    /**
     * Subtract a constant value from the physical quantity.
     *
     * @param value The value to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default Q minus(double value) {
        double newValue = getValue() - value;
        return withValue(newValue);
    }

    /**
     * Subtract this physical quantity's value from a constant value.
     * Useful for cases (1 - PhysicalQuantityValue).
     *
     * @param value The value from which to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default Q minusFromValue(double value) {
        double newValue = value - this.getValue();
        return withValue(newValue);
    }

    /**
     * Multiply the physical quantity by a constant value.
     *
     * @param value The value to multiply by.
     * @return A new physical quantity with the multiplied value.
     */
    default Q multiply(double value) {
        double newValue = getValue() * value;
        return withValue(newValue);
    }

    /**
     * Multiply the physical quantity by a constant value.
     * Added for Kotlin, to use overloaded operator: *
     *
     * @param value The value to multiply by.
     * @return A new physical quantity with the multiplied value.
     */
    default Q times(double value) {
        return multiply(value);
    }

    /**
     * Divide the physical quantity by a constant value.
     *
     * @param value The value to divide by.
     * @return A new physical quantity with the divided value.
     * @throws UnitSystemArgumentException If the divider value is zero.
     */
    default Q divide(double value) {
        if (value == 0) {
            throw new UnitSystemArgumentException("Division by zero is not allowed. Please provide a non-zero divider value.");
        }
        double newValue = getValue() / value;
        return withValue(newValue);
    }

    /**
     * Divide the physical quantity by a constant value.
     * Added for Kotlin, to use overloaded operator: /
     *
     * @param value The value to divide by.
     * @return A new physical quantity with the divided value.
     * @throws UnitSystemArgumentException If the divider value is zero.
     */
    default Q div(double value) {
        return divide(value);
    }

    /**
     * Takes ABS from current value in current unit. Effectively, makes always value a positive.
     *
     * @return A new physical quantity with the divided value.
     */
    default Q abs() {
        return withValue(Math.abs(getValue()));
    }

    /**
     * Multiplies the physical quantity by raising it to the power of the given exponent.<p>
     * This method calculates the value of the physical quantity raised to the specified exponent
     * and returns a new instance of the physical quantity with the updated value.
     *
     * @param exponent The exponent to which the current value is raised.
     * @return A new physical quantity with the value raised to the given exponent.
     */
    default Q power(double exponent) {
        double newValue = Math.pow(getValue(), exponent);
        return withValue(newValue);
    }

    /**
     * Calculates the square root of the physical quantity's value. <p>
     * This method computes the square root of the current value of the physical quantity
     * and returns a new instance of the physical quantity with the updated value.
     *
     * @return A new physical quantity with the value as the square root of the original value.
     */
    default Q sqrt() {
        double newValue = Math.sqrt(getValue());
        return withValue(newValue);
    }

    /**
     * Calculates the natural logarithm of the physical quantity's value.
     * This method computes the natural logarithm of the current value of the physical quantity
     * and returns a new instance of the physical quantity with the updated value. <p>
     * IMPORTANT: In some parts of the world (like Europe) natural logarithm is expressed as 'ln' symbol,
     * and log means logarithm with base of 10. In this app, consistency with Math library was maintained,
     * therefore log is natural logarithm (with e number in a base).
     *
     * @return A new physical quantity with the value as the natural logarithm of the original value.
     * @throws UnitSystemArgumentException if the current value is not greater than zero.
     */
    default Q log() {
        double value = getValue();
        if (value <= 0) {
            throw new UnitSystemArgumentException("Cannot calculate logarithm for non-positive value: " + value);
        }
        double newValue = Math.log(value);
        return withValue(newValue);
    }

    /**
     * Calculates the base-10 logarithm of the physical quantity's value.
     * This method computes the base-10 logarithm of the current value of the physical quantity
     * and returns a new instance of the physical quantity with the updated value.<p>
     * IMPORTANT: In some parts of the world (like Europe) natural logarithm is expressed as 'ln' symbol,
     * and log means logarithm with base of 10. In this app, consistency with Math library was maintained,
     * therefore log is natural logarithm (with e number in a base).<p>
     *
     * @return A new physical quantity with the value as the base-10 logarithm of the original value.
     * @throws UnitSystemArgumentException if the current value is not greater than zero.
     */
    default Q log10() {
        double value = getValue();
        if (value <= 0) {
            throw new UnitSystemArgumentException("Cannot calculate logarithm for non-positive value: " + value);
        }
        double newValue = Math.log10(value);
        return withValue(newValue);
    }

    /**
     * Calculates the custom base logarithm of the physical quantity's value.
     * This method computes the custom base logarithm of the current value of the physical quantity
     * and returns a new instance of the physical quantity with the updated value.<p>
     *
     * @return A new physical quantity with the value as the custom base logarithm of the original value.
     * @throws UnitSystemArgumentException if the current value or base is negative or zero
     */
    default Q logBase(double base) {
        double value = getValue();
        if (value <= 0 || base <= 0) {
            throw new UnitSystemArgumentException("Cannot calculate logarithm for non-positive value: " + value + ", base: " + base);
        }
        double newValue = Math.log(value) / Math.log(base);
        return withValue(newValue);
    }

    // Ceiling, and rounding up

    /**
     * Returns a new physical quantity with the value rounded up to the nearest integer. <p>
     * Examples: <p>
     * ceil() for 10.123456 -> will result to 11 <p>
     * ceil() for 0.123456 -> will result to 1 <p>
     * ceil() for -10.123456- > will result to 10 (this one is contr intuitive)
     *
     * @return A new physical quantity with the value rounded up.
     */
    default Q ceil() {
        double newValue = Math.ceil(getValue());
        return withValue(newValue);
    }

    /**
     * Returns a new physical quantity with the value rounded down to the nearest integer.<p>
     * Examples: <p>
     * floor() for 10.123456 -> will result to 10 <p>
     * floor() for 0.123456 -> will result to 0 <p>
     * floor() for -10.123456- > will result to -11 (this one is contr intuitive)
     *
     * @return A new physical quantity with the value rounded down.
     */
    default Q floor() {
        double newValue = Math.floor(getValue());
        return withValue(newValue);
    }

    /**
     * Returns a new physical quantity with the value rounded in HALF_EVEN way to the specified number of relevant digits.
     * Absolute value of an input argument for relevant digits is used.
     * Examples: <p>
     * roundHalfEven(0) for 10.123456 -> will result to 10 <p>
     * roundHalfEven(1) for 0.153456 -> will result to 0.2 <p>
     * roundHalfEven(2) for -10.123456- > will result to -10.12
     *
     * @param relevantDigits The number of relevant digits to round to.
     * @return A new physical quantity with the value rounded to the specified number of relevant digits.
     */
    default Q roundHalfEven(int relevantDigits) {
        String formattedValue = ValueFormatter.toStringWithRelevantDigits(getValue(), relevantDigits);
        double newValue = Double.parseDouble(formattedValue);
        return withValue(newValue);
    }

    // Handling PhysicalQuantity as input argument

    /**
     * Add another physical quantity to this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to add.
     * @return A new physical quantity with the added value.
     */
    default Q plus(PhysicalQuantity<U> inputQuantity) {
        if (inputQuantity == null) {
            return withValue(getValue());
        }
        U sourceUnit = this.getUnit();
        PhysicalQuantity<U> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() + inputInSourceUnits.getValue();
        return withValue(newValue);
    }

    /**
     * Subtract another physical quantity from this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default Q minus(PhysicalQuantity<U> inputQuantity) {
        if (inputQuantity == null) {
            return withValue(getValue());
        }
        U sourceUnit = this.getUnit();
        PhysicalQuantity<U> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() - inputInSourceUnits.getValue();
        return withValue(newValue);
    }

    /**
     * Multiply this physical quantity's value by another physical quantity's value. It resolves to double,
     * as multiplying one physical quantity by another result in completely different quantity.
     *
     * @param inputQuantity The other physical quantity for multiplication.
     * @return The result of multiplying the two physical quantities' values.
     */
    default <V extends Unit, T extends PhysicalQuantity<V>> double multiply(T inputQuantity) {
        if (inputQuantity == null) {
            return getValue();
        }
        return this.getValue() * inputQuantity.getValue();
    }

    /**
     * Multiply this physical quantity's value by another physical quantity's value.
     * Added for Kotlin, to use overloaded operator: *
     *
     * @param inputQuantity The other physical quantity for multiplication.
     * @return The result of multiplying the two physical quantities' values.
     */
    default <V extends Unit, T extends PhysicalQuantity<V>> double times(T inputQuantity) {
        return multiply(inputQuantity);
    }

    /**
     * Divide this physical quantity's value by another physical quantity's value. It resolves to double,
     * as dividing one physical quantity by another result in completely different quantity.
     *
     * @param inputQuantity The other physical quantity for division.
     * @return The result of dividing this quantity's value by the other quantity's value.
     * @throws UnitSystemArgumentException If this quantity's value is zero.
     */
    default <V extends Unit, T extends PhysicalQuantity<V>> double divide(T inputQuantity) {
        if (inputQuantity == null) {
            return getValue();
        }
        double thisValue = getValue();
        if (inputQuantity.getValue() == 0) {
            throw new UnitSystemArgumentException("Divider quantity value cannot be zero.");
        }
        return thisValue / inputQuantity.getValue();
    }

    /**
     * Divide this physical quantity's value by another physical quantity's value.
     * Added for Groovy, to use overloaded operator: /
     *
     * @param inputQuantity The other physical quantity for division.
     * @return The result of dividing this quantity's value by the other quantity's value.
     * @throws UnitSystemArgumentException If this quantity's value is zero.
     */
    default <V extends Unit, T extends PhysicalQuantity<V>> double div(T inputQuantity) {
        return divide(inputQuantity);
    }

    /**
     * Raises this physical quantity's value to the power of another physical quantity's value.<p>
     * This method calculates the result of raising this quantity's value to the power of the value
     * of the input quantity, and returns the result.
     *
     * @param inputQuantity The other physical quantity for raising to the power.
     * @return The result of raising this quantity's value to the power of the other quantity's value.
     */
    default <V extends Unit, T extends PhysicalQuantity<V>> double power(T inputQuantity) {
        if (inputQuantity == null) {
            return getValue();
        }
        double thisValue = getValue();
        return Math.pow(thisValue, inputQuantity.getValue());
    }

}