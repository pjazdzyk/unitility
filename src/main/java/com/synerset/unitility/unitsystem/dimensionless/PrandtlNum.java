package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.BareQuantity;
import com.synerset.unitility.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public class PrandtlNum implements BareQuantity {
    private final double value;

    private PrandtlNum(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrandtlNum that = (PrandtlNum) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return ValueFormatter.formatDoubleToRelevantPrecision(value, TO_STRING_PRECISION);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static PrandtlNum of(double value){
        return new PrandtlNum(value);
    }

}
