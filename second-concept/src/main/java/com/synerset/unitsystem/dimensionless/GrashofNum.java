package com.synerset.unitsystem.dimensionless;

import com.synerset.unitsystem.BareQuantity;
import com.synerset.unitsystem.utils.ValueFormatter;

import java.util.Objects;

public class GrashofNum implements BareQuantity {

    public static final byte TO_STRING_PRECISION = 8;
    private final double value;

    private GrashofNum(double value) {
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
        GrashofNum that = (GrashofNum) o;
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

    public static GrashofNum of(double value){
        return new GrashofNum(value);
    }

}
