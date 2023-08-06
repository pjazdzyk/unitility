package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

/**
 * This interface represents a physical quantity with a value and associated unit.
 *
 * @param <Q> The type of quantity, used to maintain type safety.
 */
public interface PhysicalQuantity<Q> {

    /**
     * Get the value of the physical quantity.
     *
     * @return The value of the physical quantity.
     */
    double getValue();

    /**
     * Get the unit associated with the physical quantity.
     *
     * @return The unit associated with the physical quantity.
     */
    Unit<Q> getUnit();

    /**
     * Convert the physical quantity to its base unit.
     *
     * @return A new physical quantity converted to its base unit.
     */
    PhysicalQuantity<Q> toBaseUnit();

    /**
     * Convert the physical quantity to a target unit.
     *
     * @param targetUnit The target unit to convert to.
     * @return A new physical quantity converted to the target unit.
     */
    PhysicalQuantity<Q> toUnit(Unit<Q> targetUnit);

    /**
     * Create a new physical quantity with the specified value.
     *
     * @param value The value for the new physical quantity.
     * @return A new physical quantity with the specified value.
     */
    PhysicalQuantity<Q> createNewWithValue(double value);

    /**
     * Get the symbol of the unit associated with the physical quantity.
     *
     * @return The symbol of the unit associated with the physical quantity.
     */
    default String getUnitSymbol() {
        return getUnit().getSymbol();
    }

    /**
     * Get a string representation of the physical quantity with a specified number of relevant digits.
     *
     * @param relevantDigits The number of relevant digits for formatting.
     * @return A string representation of the physical quantity with the specified number of relevant digits.
     */
    default String toStringWithRelevantDigits(int relevantDigits) {
        return ValueFormatter.formatDoubleToRelevantDigits(getValue(), relevantDigits) + " " + getUnit().getSymbol();
    }

    // Comparing methods

    /**
     * Check if the physical quantity is greater than another quantity.<>br</>
     * Both quantities are converted to their respective base units for valid comparison.
     *
     * @param quantity The other physical quantity for comparison.
     * @return True if this quantity is greater than the given quantity, false otherwise.
     */
    default boolean isGreaterThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition > 0.0;
    }

    /**
     * Check if the physical quantity is equal to or greater than another quantity.
     * Both quantities are converted to their respective base units for valid comparison.
     *
     * @param quantity The other physical quantity for comparison.
     * @return True if this quantity is equal to or greater than the given quantity, false otherwise.
     */
    default boolean isEqualOrGreaterThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition >= 0.0;
    }

    /**
     * Check if the physical quantity is lower than another quantity.
     * Both quantities are converted to their respective base units for valid comparison.
     *
     * @param quantity The other physical quantity for comparison.
     * @return True if this quantity is lower than the given quantity, false otherwise.
     */
    default boolean isLowerThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition < 0.0;
    }

    /**
     * Check if the physical quantity is equal to or lower than another quantity.
     * Both quantities are converted to their respective base units for valid comparison.
     *
     * @param quantity The other physical quantity for comparison.
     * @return True if this quantity is equal to or lower than the given quantity, false otherwise.
     */
    default boolean isEqualOrLowerThan(PhysicalQuantity<Q> quantity) {
        if (Objects.isNull(quantity)) {
            return false;
        }
        double condition = this.toBaseUnit().getValue() - quantity.toBaseUnit().getValue();
        return condition <= 0.0;
    }

    /**
     * Check if the physical quantity is equal to another quantity within a specified epsilon.
     * Both quantities are converted to their respective base units for valid comparison.
     *
     * @param quantity The other physical quantity for comparison.
     * @param epsilon  The maximum difference allowed between the quantities.
     * @return True if this quantity is equal to the given quantity within the specified epsilon, false otherwise.
     */
    default boolean isEqualsWithPrecision(PhysicalQuantity<Q> quantity, double epsilon) {
        if (this == quantity) return true;
        if (quantity == null || getClass() != quantity.getClass()) return false;
        PhysicalQuantity<Q> thisInBaseUnit = this.toBaseUnit();
        PhysicalQuantity<Q> inputInBaseUnit = quantity.toBaseUnit();
        if (thisInBaseUnit.getUnit() != inputInBaseUnit.getUnit()) return false;
        double thisValue = thisInBaseUnit.getValue();
        double inputValue = inputInBaseUnit.getValue();
        return Math.abs(thisValue - inputValue) < epsilon;
    }

    /**
     * Check if the physical quantity has a positive value.
     *
     * @return True if the value of the physical quantity is greater than zero, false otherwise.
     */
    default boolean isPositive() {
        return getValue() > 0;
    }

    /**
     * Check if the physical quantity has a positive value or is zero.
     *
     * @return True if the value of the physical quantity is greater than or equal to zero, false otherwise.
     */
    default boolean isPositiveOrZero() {
        return getValue() >= 0;
    }

    /**
     * Check if the physical quantity has a negative value.
     *
     * @return True if the value of the physical quantity is less than zero, false otherwise.
     */
    default boolean isNegative() {
        return getValue() < 0;
    }

    /**
     * Check if the physical quantity has a negative value or is zero.
     *
     * @return True if the value of the physical quantity is less than or equal to zero, false otherwise.
     */
    default boolean isNegativeOrZero() {
        return getValue() <= 0;
    }

    /**
     * Check if the physical quantity has a value of zero.
     *
     * @return True if the value of the physical quantity is zero, false otherwise.
     */
    default boolean isZero() {
        return getValue() == 0;
    }

    // Transformation methods

    /**
     * Add a constant value to the physical quantity.
     * Value is added to the value of quantity current unit.
     *
     * @param value The value to add.
     * @return A new physical quantity with the added value.
     */
    default PhysicalQuantity<Q> add(double value) {
        double newValue = getValue() + value;
        return createNewWithValue(newValue);
    }

    /**
     * Add another physical quantity to this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to add.
     * @return A new physical quantity with the added value.
     */
    default PhysicalQuantity<Q> add(PhysicalQuantity<Q> inputQuantity) {
        Unit<Q> sourceUnit = this.getUnit();
        PhysicalQuantity<Q> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() + inputInSourceUnits.getValue();
        return createNewWithValue(newValue);
    }

    /**
     * Subtract a constant value from the physical quantity.
     *
     * @param value The value to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default PhysicalQuantity<Q> subtract(double value) {
        double newValue = getValue() - value;
        return createNewWithValue(newValue);
    }

    /**
     * Subtract another physical quantity from this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default PhysicalQuantity<Q> subtract(PhysicalQuantity<Q> inputQuantity) {
        Unit<Q> sourceUnit = this.getUnit();
        PhysicalQuantity<Q> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() - inputInSourceUnits.getValue();
        return createNewWithValue(newValue);
    }

    /**
     * Subtract this physical quantity's value from a constant value.
     *
     * @param value The value from which to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default PhysicalQuantity<Q> subtractFromValue(double value) {
        double newValue = value - this.getValue();
        return createNewWithValue(newValue);
    }

    /**
     * Multiply the physical quantity by a constant value.
     *
     * @param value The value to multiply by.
     * @return A new physical quantity with the multiplied value.
     */
    default PhysicalQuantity<Q> multiply(double value) {
        double newValue = getValue() * value;
        return createNewWithValue(newValue);
    }

    /**
     * Multiply this physical quantity's value by another physical quantity's value.
     *
     * @param inputQuantity The other physical quantity for multiplication.
     * @return The result of multiplying the two physical quantities' values.
     */
    default double multiply(PhysicalQuantity<?> inputQuantity) {
        return this.getValue() * inputQuantity.getValue();
    }

    /**
     * Divide the physical quantity by a constant value.
     *
     * @param value The value to divide by.
     * @return A new physical quantity with the divided value.
     * @throws UnitSystemArgumentException If the divider value is zero.
     */
    default PhysicalQuantity<Q> divide(double value) {
        if (value == 0) {
            throw new UnitSystemArgumentException("Divider value cannot be zero.");
        }
        double newValue = getValue() / value;
        return createNewWithValue(newValue);
    }

    /**
     * Divide this physical quantity's value by another physical quantity's value.
     *
     * @param inputQuantity The other physical quantity for division.
     * @return The result of dividing this quantity's value by the other quantity's value.
     * @throws UnitSystemArgumentException If this quantity's value is zero.
     */
    default double divide(PhysicalQuantity<?> inputQuantity) {
        double thisValue = getValue();
        if (thisValue == 0) {
            throw new UnitSystemArgumentException("Divider quantity value cannot be zero.");
        }
        return thisValue / inputQuantity.getValue();
    }
}
