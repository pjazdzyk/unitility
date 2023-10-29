package com.synerset.unitility.unitsystem;

import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemParseException;
import com.synerset.unitility.unitsystem.utils.EngineeringFormatter;
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
    default boolean isEqualsWithPrecision(PhysicalQuantity<U> quantity, double epsilon) {
        if (this == quantity) return true;
        if (quantity == null || getClass() != quantity.getClass()) return false;
        PhysicalQuantity<U> thisInBaseUnit = this.toBaseUnit();
        PhysicalQuantity<U> inputInBaseUnit = quantity.toBaseUnit();
        if (thisInBaseUnit.getUnitType() != inputInBaseUnit.getUnitType()) return false;
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
    default <Q extends PhysicalQuantity<U>> Q add(double value) {
        double newValue = getValue() + value;
        return createNewWithValue(newValue);
    }

    /**
     * Add another physical quantity to this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to add.
     * @return A new physical quantity with the added value.
     */
    default <Q extends PhysicalQuantity<U>> Q add(PhysicalQuantity<U> inputQuantity) {
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
    default <Q extends PhysicalQuantity<U>> Q subtract(double value) {
        double newValue = getValue() - value;
        return createNewWithValue(newValue);
    }

    /**
     * Subtract another physical quantity from this quantity after converting it to the source unit.
     *
     * @param inputQuantity The other physical quantity to subtract.
     * @return A new physical quantity with the subtracted value.
     */
    default <Q extends PhysicalQuantity<U>> Q subtract(Q inputQuantity) {
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
        return ValueFormatter.formatDoubleToRelevantDigits(getValue(), relevantDigits) + separator + getUnitSymbol();
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
     * Converts the physical quantity to a engineering format where unit is placed in rectangular braces for i.e.: 20[°C].
     *
     * @return The value in canonical format.
     */
    default String toEngineeringFormat() {
        return EngineeringFormatter.toEngineeringFormat(this);
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

    // Static parsers

    /**
     * Creates a PhysicalQuantity of a specified type from a symbol representation of a unit and a numeric value.<p>
     * Intended to be used for deserializers.
     * For example, "oC" is considered a symbol of Temperature in Celsius degrees.
     * It can be provided in various ways and still will be resolved to the correct quantity, for i.e.: "C", "c",
     * "degC", "°C" will all be interpreted in the same way, as degree in Celsius. <p>
     *
     * @param targetClass The class type of the PhysicalQuantity.
     * @param value       The numeric value of the quantity.
     * @param unitSymbol  The symbol representation of the quantity.
     * @param <U>         The unit type associated with the PhysicalQuantity.
     * @param <Q>         The type of the PhysicalQuantity.
     * @return The newly created PhysicalQuantity.
     * @throws UnitSystemParseException    in case of any failure during parsing process.
     * @throws UnitSystemArgumentException if the target class is null or of incorrect type.
     */
    static <U extends Unit, Q extends PhysicalQuantity<U>> Q createFromSymbol(Class<Q> targetClass, double value,
                                                                              String unitSymbol) {
        if (targetClass == null) {
            throw new UnitSystemArgumentException("Invalid argument. Class cannot be null.");
        }
        return PhysicalQuantityParsingFactory.createParsingFromSymbol(targetClass, value, unitSymbol);
    }

    /**
     * Creates a PhysicalQuantity of a specified type from a unit representation and a numeric value.
     * Intended to be used for deserializers.
     * For example, "METER_PER_SECOND" is considered a unit type of Velocity in m/s.
     * It can be provided in various ways and still will be resolved to the correct quantity,
     * for i.e.: "meter per second", "meter_per_second" will all be interpreted in the same way, as velocity in m/s.<p>
     * Throws {@link UnitSystemParseException} upon parsing failure or {@link UnitSystemArgumentException} in case of
     * invalid arguments.
     *
     * @param targetClass The class type of the PhysicalQuantity.
     * @param value       The numeric value of the quantity.
     * @param unitName    The unit representation of the quantity.
     * @param <U>         The unit type associated with the PhysicalQuantity.
     * @param <Q>         The type of the PhysicalQuantity.
     * @return The newly created PhysicalQuantity.
     * @throws UnitSystemParseException    in case of any failure during parsing process.
     * @throws UnitSystemArgumentException if the target class is null or of incorrect type.
     */
    static <U extends Unit, Q extends PhysicalQuantity<U>> Q createFromUnitName(Class<Q> targetClass, double value,
                                                                                String unitName) {
        if (targetClass == null) {
            throw new UnitSystemArgumentException("Invalid argument. Target class cannot be null.");
        }
        return PhysicalQuantityParsingFactory.createParsingFromUnit(targetClass, value, unitName);
    }

    /**
     * Create a physical quantity of a specified target class by parsing a string in engineering format (i.e.: 10[m/s]).
     *
     * @param <U>                     The type of unit associated with the physical quantity.
     * @param <Q>                     The type of the physical quantity to create.
     * @param targetClass             The target class for the physical quantity to be created.
     * @param unitInEngineeringFormat A string in engineering format representing the physical quantity.
     * @return The parsed physical quantity of the specified target class.
     * @throws UnitSystemParseException    in case of any failure during parsing process.
     * @throws UnitSystemArgumentException if the target class is null or of incorrect type.
     */
    static <U extends Unit, Q extends PhysicalQuantity<U>> Q createFromEngineeringFormat(Class<Q> targetClass,
                                                                                         String unitInEngineeringFormat) {
        if (targetClass == null) {
            throw new UnitSystemArgumentException("Invalid argument. Target class cannot be null.");
        }
        return EngineeringFormatter.fromEngineeringFormat(targetClass, unitInEngineeringFormat);
    }

}