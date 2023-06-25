package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.BareQuantity;

import java.util.Objects;

public class PrandtlNumber implements BareQuantity {
    private final double value;

    private PrandtlNumber(double value) {
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
        PrandtlNumber that = (PrandtlNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "PrandtlNumber{" +
                "value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static PrandtlNumber of(double value){
        return new PrandtlNumber(value);
    }

}
