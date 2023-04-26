package com.synerset.unitility.unitsystem.distance;

import com.synerset.unitility.unitsystem.Unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public enum DistanceUnits implements Unit<Distance> {

    METER("m", val -> val, val -> val),
    CENTIMETER("cm", val -> val.divide(new BigDecimal( 100), RoundingMode.HALF_UP), val -> val.multiply(new BigDecimal( 100))),
    MILLIMETER("mm", val -> val.divide(new BigDecimal( 1000), RoundingMode.HALF_UP), val -> val.multiply(new BigDecimal( 1000))),
    KILOMETER("km", val -> val.multiply(new BigDecimal( 1000)), val -> val.divide(new BigDecimal( 1000), RoundingMode.HALF_UP)),
    MILE("mi",  val -> val.multiply(new BigDecimal( "1609.344")), val -> val.divide(new BigDecimal( "1609.344"), RoundingMode.HALF_UP)),
    FEET("ft", val -> val.multiply(new BigDecimal( "0.3048")), val -> val.divide(new BigDecimal( "0.3048"), RoundingMode.HALF_UP)),
    INCH("in", val -> val.multiply(new BigDecimal( "0.0254")), val -> val.divide(new BigDecimal( "0.0254"), RoundingMode.HALF_UP));



    private final String symbol;
    private final UnaryOperator<BigDecimal> toBaseConverter;
    private final UnaryOperator<BigDecimal> fromBaseToUnitConverter;

    DistanceUnits(String symbol, UnaryOperator<BigDecimal> toBaseConverter, UnaryOperator<BigDecimal> fromBaseToUnitConverter) {
        this.symbol = symbol;
        this.toBaseConverter = toBaseConverter;
        this.fromBaseToUnitConverter = fromBaseToUnitConverter;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Unit<Distance> getBaseUnit() {
        return METER;
    }

    @Override
    public BigDecimal toBaseUnit(BigDecimal valueInThisUnit) {
        return toBaseConverter.apply(valueInThisUnit);
    }

    @Override
    public BigDecimal fromBaseToThisUnit(BigDecimal valueInBaseUnit) {
        return fromBaseToUnitConverter.apply(valueInBaseUnit);
    }
}
