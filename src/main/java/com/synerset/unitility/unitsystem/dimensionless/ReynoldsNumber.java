package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.BareQuantity;

import java.util.Objects;

public class ReynoldsNumber implements BareQuantity {

    public static final ReynoldsNumber PIPE_TURBULENT_THRESHOLD = ReynoldsNumber.of(2300);
    public static final ReynoldsNumber PLATE_TURBULENT_THRESHOLD = ReynoldsNumber.of(5 * 10E5);
    public static final ReynoldsNumber AERO_TURBULENT_THRESHOLD = ReynoldsNumber.of(2 * 10E6);

    private final double value;

    private ReynoldsNumber(double value) {
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
        ReynoldsNumber that = (ReynoldsNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "ReynoldsNumber{" +
                "value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static ReynoldsNumber of(double value) {
        return new ReynoldsNumber(value);
    }

}
