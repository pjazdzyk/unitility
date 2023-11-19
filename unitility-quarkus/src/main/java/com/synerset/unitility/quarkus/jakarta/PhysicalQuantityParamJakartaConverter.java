package com.synerset.unitility.quarkus.jakarta;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.ws.rs.ext.ParamConverter;

public class PhysicalQuantityParamJakartaConverter<U extends Unit, Q extends PhysicalQuantity<U>> implements ParamConverter<Q> {

    private final Class<Q> targetClass;
    private final PhysicalQuantityParsingRegistry parsingRegistry;

    public PhysicalQuantityParamJakartaConverter(Class<Q> targetClass, PhysicalQuantityParsingRegistry parsingRegistry) {
        this.targetClass = targetClass;
        this.parsingRegistry = parsingRegistry;
    }

    @Override
    public Q fromString(String quantityAsString) {
        return parsingRegistry.createFromEngFormat(targetClass, quantityAsString);
    }

    @Override
    public String toString(Q physicalQuantity) {
        return physicalQuantity.toEngineeringFormat();
    }

}
