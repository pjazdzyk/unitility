package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

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
        U sourceUnit = this.getUnitType();
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
        U sourceUnit = this.getUnitType();
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

}