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
     * Create a new physical quantity with new value of the same unit.
     *
     * @param value The value for the new physical quantity.
     * @return A new physical quantity with the specified value.
     */
    <Q extends PhysicalQuantity<U>> Q withValue(double value);

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
    default boolean greaterThan(PhysicalQuantity<U> quantity) {
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
    default boolean equalOrGreaterThan(PhysicalQuantity<U> quantity) {
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
    default boolean lowerThan(PhysicalQuantity<U> quantity) {
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
    default boolean equalOrLowerThan(PhysicalQuantity<U> quantity) {
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
    default boolean equalsWithPrecision(PhysicalQuantity<U> quantity, double epsilon) {
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
    default boolean positive() {
        return getValue() > 0;
    }

    /**
     * Check if the physical quantity has a positive value or is zero.
     *
     * @return True if the value of the physical quantity is greater than or equal to zero, false otherwise.
     */
    default boolean positiveOrZero() {
        return getValue() >= 0;
    }

    /**
     * Check if the physical quantity has a negative value.
     *
     * @return True if the value of the physical quantity is less than zero, false otherwise.
     */
    default boolean negative() {
        return getValue() < 0;
    }

    /**
     * Check if the physical quantity has a negative value or is zero.
     *
     * @return True if the value of the physical quantity is less than or equal to zero, false otherwise.
     */
    default boolean negativeOrZero() {
        return getValue() <= 0;
    }

    /**
     * Check if the physical quantity has a value of zero.
     *
     * @return True if the value of the physical quantity is zero, false otherwise.
     */
    default boolean equalsZero() {
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
        return withValue(newValue);
    }

    /**
     * Add another physical quantity to this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to add.
     * @return A new physical quantity with the added value.
     */
    default <Q extends PhysicalQuantity<U>> Q plus(Q inputQuantity) {
        U sourceUnit = this.getUnitType();
        PhysicalQuantity<U> inputInSourceUnits = inputQuantity.toUnit(sourceUnit);
        double newValue = this.getValue() + inputInSourceUnits.getValue();
        return withValue(newValue);
    }

    /**
     * Subtract a constant value from the physical quantity.
     *
     * @param value The value to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default <Q extends PhysicalQuantity<U>> Q minus(double value) {
        double newValue = getValue() - value;
        return withValue(newValue);
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
        return withValue(newValue);
    }

    /**
     * Subtract this physical quantity's value from a constant value.
     *
     * @param value The value from which to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default <Q extends PhysicalQuantity<U>> Q subtractFromValue(double value) {
        double newValue = value - this.getValue();
        return withValue(newValue);
    }

    /**
     * Multiply the physical quantity by a constant value.
     *
     * @param value The value to multiply by.
     * @return A new physical quantity with the multiplied value.
     */
    default <Q extends PhysicalQuantity<U>> Q multiply(double value) {
        double newValue = getValue() * value;
        return withValue(newValue);
    }

    /**
     * Divide the physical quantity by a constant value.
     *
     * @param value The value to divide by.
     * @return A new physical quantity with the divided value.
     * @throws UnitSystemArgumentException If the divider value is zero.
     */
    default <Q extends PhysicalQuantity<U>> Q div(double value) {
        if (value == 0) {
            throw new UnitSystemArgumentException("Division by zero is not allowed. Please provide a non-zero divider value.");
        }
        double newValue = getValue() / value;
        return withValue(newValue);
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
     * Multiply this physical quantity's value by another physical quantity's value.
     * Added to provide "*" operator support in Kotlin.
     *
     * @param inputQuantity The other physical quantity for multiplication.
     * @return The result of multiplying the two physical quantities' values.
     */
    default double times(PhysicalQuantity<? extends Unit> inputQuantity){
        return multiply(inputQuantity);
    }

    /**
     * Divide this physical quantity's value by another physical quantity's value.
     *
     * @param inputQuantity The other physical quantity for division.
     * @return The result of dividing this quantity's value by the other quantity's value.
     * @throws UnitSystemArgumentException If this quantity's value is zero.
     */
    default double div(PhysicalQuantity<? extends Unit> inputQuantity) {
        double thisValue = getValue();
        if (thisValue == 0) {
            throw new UnitSystemArgumentException("Divider quantity value cannot be zero.");
        }
        return thisValue / inputQuantity.getValue();
    }

    // Formatters for console output

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * for example, 20.123456789[°C].
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
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * with variable name, for example, t_atm = 20.123456789[°C].
     *
     * @param variableName The name of the variable for which the engineering format is generated.
     * @return The representation in engineering format.
     */
    default String toEngineeringFormat(String variableName) {
        if (getUnitSymbol().isBlank()) {
            return String.valueOf(getValue());
        }
        return variableName + " = " + toEngineeringFormat();
    }

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * truncated to relevantDigits: for example, 120.123[F]
     *
     * @param relevantDigits The number of rounded relevant digits to show.
     * @return The representation in engineering format.
     */
    default String toEngineeringFormat(int relevantDigits) {
        if (getUnitSymbol().isBlank()) {
            return String.valueOf(getValue());
        }
        return String.format("%s[%s]", ValueFormatter.toStringWithRelevantDigits(getValue(), relevantDigits), getUnitSymbol());
    }

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * with variable name, truncated to relevantDigits: for example, t_atm = 120.123[F]
     *
     * @param variableName   The name of the variable for which the engineering format is generated.
     * @param relevantDigits The number of rounded relevant digits to show.
     * @return The representation in engineering format.
     */
    default String toEngineeringFormat(String variableName, int relevantDigits) {
        if (getUnitSymbol().isBlank()) {
            return String.valueOf(getValue());
        }
        return variableName + " = " + toEngineeringFormat(relevantDigits);
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