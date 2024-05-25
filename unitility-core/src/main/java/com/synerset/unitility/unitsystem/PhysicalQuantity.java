package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.util.ValueFormatter;

import java.util.Objects;

/**
 * Interface representing a physical quantity with a specific {@link Unit}.
 *
 * @param <U> The type of {@link Unit} associated with the physical quantity.
 */
public interface PhysicalQuantity<U extends Unit> extends Comparable<PhysicalQuantity<U>> {

    /**
     * Create a new physical quantity with new value of the same unit.
     *
     * @param value The value for the new physical quantity.
     * @return A new physical quantity with the specified value.
     */
    PhysicalQuantity<U> withValue(double value);

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
    default double getInUnit(U targetUnit) {
        return targetUnit.fromValueInBaseUnit(getBaseValue());
    }

    /**
     * Returns {@link PhysicalQuantity} converted to unit of target quantity.
     *
     * @param targetUnit target physical quantity
     * @return converted to unit of target quantity
     */
    @SuppressWarnings("unchecked")
    default <Q extends PhysicalQuantity<U>> Q toUnitFrom(Q targetUnit) {
        U targetUnitUnitT = targetUnit.getUnitType();
        return (Q) toUnit(targetUnitUnitT);
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
    default boolean isEqualZero() {
        return getValue() == 0;
    }

    /**
     * Check if the physical quantity has a value close to zero. If the value is lover than 1E-12,
     * method will return true.
     *
     * @return True if the value of the physical quantity is zero, false otherwise.
     */
    default boolean isCloseToZero() {
        return Math.abs(getValue()) < 1E-12;
    }

    /**
     * Check if the physical quantity has a value close to zero.
     *
     * @param epsilon if value is below this threshold, method will return {@code true}
     * @return True if the value of the physical quantity is zero, false otherwise.
     */
    default boolean isCloseToZero(double epsilon) {
        return Math.abs(getValue()) < epsilon;
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
        return String.format("%s [%s]", getValue(), getUnitSymbol());
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
            return ValueFormatter.toStringWithRelevantDigits(getValue(), relevantDigits);
        }
        return String.format("%s [%s]", ValueFormatter.toStringWithRelevantDigits(getValue(), relevantDigits), getUnitSymbol());
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
        return variableName + " = " + toEngineeringFormat(relevantDigits);
    }

    @Override
    default int compareTo(PhysicalQuantity<U> other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare to null");
        }

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