package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

/**
 * A temperature <b>interval</b>: a rise, a drop, an approach, a glide or a log-mean difference.
 *
 * <p>Distinct from {@link Temperature}, which is a point on a scale, because the two convert
 * differently. A temperature converts affinely, with an offset: 20 °C is 68 °F. An interval
 * converts by ratio alone, because the offsets at its two ends cancel: a rise of 20 °C is 36 °F.
 * Carrying a difference in a {@code Temperature} is a silent error. It compiles, it serialises,
 * and it only becomes visible when somebody asks for imperial units and a 1.15 K fan rise is
 * reported as 34 °F.</p>
 *
 * <p>A degree Celsius interval is exactly a kelvin, so {@code CELSIUS} and {@code KELVIN} share a
 * scale here, and so do {@code FAHRENHEIT} and {@code RANKINE}. That is the arithmetic saying the
 * same thing: only the size of the degree survives in a difference, never where its zero sits.</p>
 */
public class TemperatureDifference implements CalculableQuantity<TemperatureDifferenceUnit, TemperatureDifference> {

    private final double value;
    private final double baseValue;
    private final TemperatureDifferenceUnit unitType;

    public TemperatureDifference(double value, TemperatureDifferenceUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = TemperatureDifferenceUnits.KELVIN;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static TemperatureDifference of(double value, TemperatureDifferenceUnit unit) {
        return new TemperatureDifference(value, unit);
    }

    public static TemperatureDifference of(double value, String unitSymbol) {
        TemperatureDifferenceUnit resolvedUnit = TemperatureDifferenceUnits.fromSymbol(unitSymbol);
        return new TemperatureDifference(value, resolvedUnit);
    }

    public static TemperatureDifference ofKelvins(double value) {
        return new TemperatureDifference(value, TemperatureDifferenceUnits.KELVIN);
    }

    public static TemperatureDifference ofCelsius(double value) {
        return new TemperatureDifference(value, TemperatureDifferenceUnits.CELSIUS);
    }

    public static TemperatureDifference ofFahrenheit(double value) {
        return new TemperatureDifference(value, TemperatureDifferenceUnits.FAHRENHEIT);
    }

    public static TemperatureDifference ofRankine(double value) {
        return new TemperatureDifference(value, TemperatureDifferenceUnits.RANKINE);
    }

    /**
     * The interval between two temperatures, as {@code second - first}.
     *
     * <p>The conversion-safe way to build one. Both operands are taken to kelvin first, so the
     * result never carries an offset, whatever scales the two were expressed in.</p>
     *
     * @param first  the temperature subtracted from
     * @param second the temperature subtracted
     * @return the interval, in kelvin
     */
    public static TemperatureDifference between(Temperature first, Temperature second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return ofKelvins(second.getInKelvins() - first.getInKelvins());
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public TemperatureDifferenceUnit getUnit() {
        return unitType;
    }

    @Override
    public TemperatureDifference toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public TemperatureDifference toUnit(TemperatureDifferenceUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return TemperatureDifference.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public TemperatureDifference toUnit(String targetUnit) {
        TemperatureDifferenceUnit resolvedUnit = TemperatureDifferenceUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public TemperatureDifference withValue(double value) {
        return TemperatureDifference.of(value, unitType);
    }

    // Convert to target unit
    public TemperatureDifference toKelvins() {
        return toUnit(TemperatureDifferenceUnits.KELVIN);
    }

    public TemperatureDifference toCelsius() {
        return toUnit(TemperatureDifferenceUnits.CELSIUS);
    }

    public TemperatureDifference toFahrenheit() {
        return toUnit(TemperatureDifferenceUnits.FAHRENHEIT);
    }

    public TemperatureDifference toRankine() {
        return toUnit(TemperatureDifferenceUnits.RANKINE);
    }

    // Get value in target unit
    public double getInKelvins() {
        return getInUnit(TemperatureDifferenceUnits.KELVIN);
    }

    public double getInCelsius() {
        return getInUnit(TemperatureDifferenceUnits.CELSIUS);
    }

    public double getInFahrenheits() {
        return getInUnit(TemperatureDifferenceUnits.FAHRENHEIT);
    }

    public double getInRankine() {
        return getInUnit(TemperatureDifferenceUnits.RANKINE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperatureDifference that = (TemperatureDifference) o;
        return Double.compare(that.toBaseUnit().getValue(), baseValue) == 0
               && Objects.equals(unitType.getBaseUnit(), that.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "TemperatureDifference{" + value + " " + unitType.getSymbol() + '}';
    }

}
