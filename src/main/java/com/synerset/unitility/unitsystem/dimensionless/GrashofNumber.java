package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.BareQuantity;

import java.util.Objects;

public class GrashofNumber implements BareQuantity {

    private final double value;

    private GrashofNumber(double value) {
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
        GrashofNumber that = (GrashofNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "GrashofNumber{" +
                "value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static GrashofNumber of(double value){
        return new GrashofNumber(value);
    }

}
