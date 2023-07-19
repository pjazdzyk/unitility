package com.synerset.unitility.unitsystem.dimensionless;

import com.synerset.unitility.unitsystem.BareQuantity;

import java.util.Objects;

public class BypassFactor implements BareQuantity {

    public static final BypassFactor BYPASS_MIN_VALUE = BypassFactor.of(0);
    public static final BypassFactor BYPASS_MAX_VALUE = BypassFactor.of(1);

    private final double value;

    private BypassFactor(double value) {
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
        BypassFactor that = (BypassFactor) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public String toString() {
        return "Bypass factor{" +
                "value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public static BypassFactor of(double value) {
        return new BypassFactor(value);
    }

}
