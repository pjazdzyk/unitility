package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class DataSize implements CalculableQuantity<DataSizeUnit, DataSize> {

    private final double value;
    private final double baseValue;
    private final DataSizeUnit unitType;

    public DataSize(double value, DataSizeUnit unitType) {
        if (unitType == null) {
            unitType = DataSizeUnits.BYTE;
        }

        validateValue(value, unitType);

        this.value = value;
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    /**
     * Enforces data size constraints:
     * 1. BIT unit: value must be a non-fractional number and a multiple of 8 (a whole number of Bytes).
     * 2. All other units (BYTE and larger): value must result in a non-fractional number of Bytes.
     * 3. Value in Bytes must not exceed the double's safe integer limit (2^53 Bytes or 1 PB) to guarantee precision.
     *
     * @param value The value provided by the user.
     * @param unit  The unit provided by the user.
     * @throws IllegalArgumentException if the validation fails.
     */
    private void validateValue(double value, DataSizeUnit unit) {

        if (unit == DataSizeUnits.BIT) {
            if (Double.compare(value, Math.rint(value)) != 0) {
                throw new IllegalArgumentException(String.format(
                        "Data size constraint violation: Value %.4f [%s] must be an exact number of bits.",
                        value, unit.getSymbol()));
            }

            long bitValue = (long) value;
            if (bitValue % 8 != 0) {
                throw new IllegalArgumentException(String.format(
                        "Data size constraint violation: Value %d [%s] is not a whole number of Bytes (multiple of 8 bits).",
                        bitValue, unit.getSymbol()));
            }
            return;
        }

        double valueInBaseUnit = unit.toValueInBaseUnit(value);

        if (Double.compare(valueInBaseUnit, Math.rint(valueInBaseUnit)) != 0) {
            throw new IllegalArgumentException(String.format(
                    "Data size constraint violation: Value %.4f [%s] results in a fractional number of Bytes (%.4f B). Data size must be an exact number of Bytes.",
                    value, unit.getSymbol(), valueInBaseUnit));
        }

        if (valueInBaseUnit > Math.pow(2, 53)) {
            throw new IllegalArgumentException(String.format(
                    "Data size constraint violation: The value (%.0f B) exceeds the safe integer precision limit of 2^53 Bytes (1 PB). Consider BigInteger for larger sizes.",
                    valueInBaseUnit));
        }
    }

    // Static factory methods
    public static DataSize of(double value, DataSizeUnit unit) {
        return new DataSize(value, unit);
    }

    public static DataSize of(double value, String unitSymbol) {
        DataSizeUnit resolvedUnit = DataSizeUnits.fromSymbol(unitSymbol);
        return new DataSize(value, resolvedUnit);
    }

    public static DataSize ofBits(double value) {
        return new DataSize(value, DataSizeUnits.BIT);
    }

    public static DataSize ofBytes(double value) {
        return new DataSize(value, DataSizeUnits.BYTE);
    }

    public static DataSize ofKilobytes(double value) {
        return new DataSize(value, DataSizeUnits.KILOBYTE);
    }

    public static DataSize ofMegabytes(double value) {
        return new DataSize(value, DataSizeUnits.MEGABYTE);
    }

    public static DataSize ofGigabytes(double value) {
        return new DataSize(value, DataSizeUnits.GIGABYTE);
    }

    public static DataSize ofTerabytes(double value) {
        return new DataSize(value, DataSizeUnits.TERABYTE);
    }

    public static DataSize ofPetabytes(double value) {
        return new DataSize(value, DataSizeUnits.PETABYTE);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue; //
    }

    @Override
    public DataSizeUnit getUnit() {
        return unitType;
    }

    @Override
    public DataSize toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public DataSize toUnit(DataSizeUnit targetUnit) {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInBaseUnit);
        return DataSize.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public DataSize toUnit(String targetUnit) {
        DataSizeUnit resolvedUnit = DataSizeUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public DataSize withValue(double value) {
        return DataSize.of(value, unitType);
    }

    // Convert to target unit
    public DataSize toBits() {
        return toUnit(DataSizeUnits.BIT);
    }

    public DataSize toBytes() {
        return toUnit(DataSizeUnits.BYTE);
    }

    public DataSize toKilobytes() {
        return toUnit(DataSizeUnits.KILOBYTE);
    }

    public DataSize toMegabytes() {
        return toUnit(DataSizeUnits.MEGABYTE);
    }

    public DataSize toGigabytes() {
        return toUnit(DataSizeUnits.GIGABYTE);
    }

    public DataSize toTerabytes() {
        return toUnit(DataSizeUnits.TERABYTE);
    }

    public DataSize toPetabytes() {
        return toUnit(DataSizeUnits.PETABYTE);
    }


    // Get value in target unit
    public double getInBits() {
        return toBits().getValue();
    }

    public double getInBytes() {
        return getInUnit(DataSizeUnits.BYTE);
    }

    public double getInKilobytes() {
        return getInUnit(DataSizeUnits.KILOBYTE);
    }

    public double getInMegabytes() {
        return getInUnit(DataSizeUnits.MEGABYTE);
    }

    public double getInGigabytes() {
        return getInUnit(DataSizeUnits.GIGABYTE);
    }

    public double getInTerabytes() {
        return getInUnit(DataSizeUnits.TERABYTE);
    }

    public double getInPetabytes() {
        return getInUnit(DataSizeUnits.PETABYTE);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSize inputQuantity = (DataSize) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        String separator = getUnit().getSymbol().equals("b") ? "" : " ";
        return "DataSize{" + value + separator + unitType.getSymbol() + '}';
    }

}