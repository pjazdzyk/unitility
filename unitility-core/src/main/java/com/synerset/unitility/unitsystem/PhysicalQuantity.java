package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public interface PhysicalQuantity<U extends Unit> extends Comparable<PhysicalQuantity<U>> {

    int DEFAULT_RELEVANT_DIGITS = 4;

    /**
     * Get the value of the physical quantity.
     *
     * @return The value of the physical quantity.
     */
    double getValue();

    /**
     * Get the value of the physical quantity in its base unit.
     *
     * @return The base value of the physical quantity.
     */
    double getBaseValue();

    /**
     * Get the unit associated with the physical quantity.
     *
     * @return The unit associated with the physical quantity.
     */
    U getUnitType();

    /**
     * Convert the physical quantity to its base unit.
     *
     * @return A new physical quantity converted to its base unit.
     */
    PhysicalQuantity<U> toBaseUnit();

    /**
     * Convert the physical quantity to a target unit.
     *
     * @param targetUnit The target unit to convert to.
     * @return A new physical quantity converted to the target unit.
     */
    PhysicalQuantity<U> toUnit(U targetUnit);

    /**
     * Create a new physical quantity with the specified value.
     *
     * @param value The value for the new physical quantity.
     * @return A new physical quantity with the specified value.
     */
    <Q extends PhysicalQuantity<U>> Q createNewWithValue(double value);

    /**
     * Get the symbol of the unit associated with the physical quantity.
     *
     * @return The symbol of the unit associated with the physical quantity.
     */
    default String getUnitSymbol() {
        return getUnitType().getSymbol();
    }

    /**
     * Returns a converted value to target unit of the same type.
     *
     * @param targetUnit TThe target unit for conversion.
     * @return A value converted to target unit.
     */
    default double getIn(U targetUnit) {
        return targetUnit.fromValueInBaseUnit(getBaseValue());
    }

    // Logical operations

    /**
     * Check if the physical quantity is greater than another quantity
     * Both quantities are converted to their respective base units for valid comparison
     *
     * @param quantity The other physical quantity for comparison.
     * @return True if this quantity is greater than the given quantity, false otherwise.
     */
    default boolean isGreaterThan(PhysicalQuantity<U> quantity) {
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
    default boolean isEqualOrGreaterThan(PhysicalQuantity<U> quantity) {
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
    default boolean isLowerThan(PhysicalQuantity<U> quantity) {
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
    default boolean isEqualOrLowerThan(PhysicalQuantity<U> quantity) {
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
    default boolean isEqualWithPrecision(PhysicalQuantity<U> quantity, double epsilon) {
        if (this == quantity) {
            return true;
        }
        if (quantity == null || getClass() != quantity.getClass()) {
            return false;
        }
        PhysicalQuantity<U> thisInBaseUnit = this.toBaseUnit();
        PhysicalQuantity<U> inputInBaseUnit = quantity.toBaseUnit();
        if (thisInBaseUnit.getUnitType() != inputInBaseUnit.getUnitType()) {
            return false;
        }
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

    // Transformations

    /**
     * Add a constant value to the physical quantity.
     * Value is added to the value of quantity current unit.
     *
     * @param value The value to add.
     * @return A new physical quantity with the added value.
     */
    default <Q extends PhysicalQuantity<U>> Q plus(double value) {
        double newValue = getValue() + value;
        return createNewWithValue(newValue);
    }

    /**
     * Add another physical quantity to this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to add.
     * @return A new physical quantity with the added value.
     */
    default <Q extends PhysicalQuantity<U>> Q plus(PhysicalQuantity<U> inputQuantity) {
        U sourceUnit = this.getUnitType();
        PhysicalQuantity<U> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() + inputInSourceUnits.getValue();
        return createNewWithValue(newValue);
    }

    /**
     * Subtract a constant value from the physical quantity.
     *
     * @param value The value to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default <Q extends PhysicalQuantity<U>> Q minus(double value) {
        double newValue = getValue() - value;
        return createNewWithValue(newValue);
    }

    /**
     * Subtract another physical quantity from this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default <Q extends PhysicalQuantity<U>> Q minus(Q inputQuantity) {
        U sourceUnit = this.getUnitType();
        PhysicalQuantity<U> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() - inputInSourceUnits.getValue();
        return createNewWithValue(newValue);
    }

    /**
     * Subtract this physical quantity's value from a constant value.
     *
     * @param value The value from which to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default <Q extends PhysicalQuantity<U>> Q subtractFromValue(double value) {
        double newValue = value - this.getValue();
        return createNewWithValue(newValue);
    }

    /**
     * Multiply the physical quantity by a constant value.
     *
     * @param value The value to multiply by.
     * @return A new physical quantity with the multiplied value.
     */
    default <Q extends PhysicalQuantity<U>> Q multiply(double value) {
        double newValue = getValue() * value;
        return createNewWithValue(newValue);
    }

    /**
     * Divide the physical quantity by a constant value.
     *
     * @param value The value to divide by.
     * @return A new physical quantity with the divided value.
     * @throws UnitSystemArgumentException If the divider value is zero.
     */
    default <Q extends PhysicalQuantity<U>> Q divide(double value) {
        if (value == 0) {
            throw new UnitSystemArgumentException("Division by zero is not allowed. Please provide a non-zero divider value.");
        }
        double newValue = getValue() / value;
        return createNewWithValue(newValue);
    }

    /**
     * Multiply this physical quantity's value by another physical quantity's value.
     *
     * @param inputQuantity The other physical quantity for multiplication.
     * @return The result of multiplying the two physical quantities' values.
     */
    default double multiply(PhysicalQuantity<? extends Unit> inputQuantity) {
        return this.getValue() * inputQuantity.getValue();
    }

    /**
     * Divide this physical quantity's value by another physical quantity's value.
     *
     * @param inputQuantity The other physical quantity for division.
     * @return The result of dividing this quantity's value by the other quantity's value.
     * @throws UnitSystemArgumentException If this quantity's value is zero.
     */
    default double divide(PhysicalQuantity<? extends Unit> inputQuantity) {
        double thisValue = getValue();
        if (thisValue == 0) {
            throw new UnitSystemArgumentException("Divider quantity value cannot be zero.");
        }
        return thisValue / inputQuantity.getValue();
    }

    // Formatters for console output

    /**
     * Returns a formatted string representation of the value associated with this object, followed by its unit symbol.
     * The value is formatted to have a specific number of relevant digits.
     *
     * @param relevantDigits number of relevant digits
     * @return A formatted string representation of the value and unit symbol.
     */
    default String toFormattedString(int relevantDigits) {
        String separator = getUnitType().getSymbol().contains("°") ? "" : " ";
        return ValueFormatter.toStringWithRelevantDigits(getValue(), relevantDigits) + separator + getUnitSymbol();
    }

    /**
     * Returns a formatted string representation of the value associated with this object, followed by its unit symbol.
     * The value is formatted to have a specific number of relevant digits (default is 4).
     *
     * @return A formatted string representation of the value and unit symbol.
     */
    default String toFormattedString() {
        return toFormattedString(DEFAULT_RELEVANT_DIGITS);
    }

    /**
     * Returns a formatted string representation of the value associated with this object, followed by its unit symbol,
     * along with the specified variable name. Example: t = 21.5 K
     *
     * @param variableName The variable name to include in the formatted string.
     * @return A formatted string representation of the variable name, value, and unit symbol.
     */
    default String toFormattedString(String variableName) {
        return variableName + " = " + toFormattedString();
    }

    /**
     * Converts the physical quantity to an engineering format where unit is placed in rectangular braces for i.e.: 20[°C].
     *
     * @return The representation in engineering format.
     */
    default String toEngineeringFormat() {
        if (getUnitSymbol().isBlank()) {
            return String.valueOf(getValue());
        }
        return String.format("%s[%s]", getValue(), getUnitSymbol());
    }

    /**
     * Converts the physical quantity to an engineering format where unit is placed in rectangular braces for i.e.: 20[°C].
     *
     * @param relevantDigits number of rounded relevant digits to show
     * @return The representation in engineering format.
     */
    default String toEngineeringFormat(int relevantDigits) {
        if (getUnitSymbol().isBlank()) {
            return String.valueOf(getValue());
        }
        return String.format("%s[%s]", ValueFormatter.toStringWithRelevantDigits(getValue(), relevantDigits), getUnitSymbol());
    }

    @Override
    default int compareTo(PhysicalQuantity<U> other) {
        if (this == other) {
            return 0;
        }
        // Convert both quantities to the same unit for comparison
        PhysicalQuantity<U> thisInOtherUnit = this.toUnit(other.getUnitType());

        // Compare the values of the two quantities
        double thisValue = thisInOtherUnit.getValue();
        double otherValue = other.getValue();

        return Double.compare(thisValue, otherValue);
    }

}