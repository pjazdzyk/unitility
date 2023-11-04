package com.synerset.quarkus.jakarta;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import jakarta.ws.rs.ext.ParamConverter;

public class PhysicalQuantityParamJakartaConverter<U extends Unit, Q extends PhysicalQuantity<U>> implements ParamConverter<Q> {

    private final Class<Q> targetClass;

    public PhysicalQuantityParamJakartaConverter(Class<Q> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Q fromString(String quantityAsString) {
        return PhysicalQuantity.createFromEngineeringFormat(targetClass, quantityAsString);
    }

    @Override
    public String toString(Q physicalQuantity) {
        return physicalQuantity.toEngineeringFormat();
    }

}
