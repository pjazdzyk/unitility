package com.synerset.spring.unitilityconverters.webmvc;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import org.springframework.core.convert.converter.Converter;

class PhysicalQuantityWebMvcConverter<U extends Unit, Q extends PhysicalQuantity<U>> implements Converter<String, Q> {

    private final Class<Q> targetClass;

    PhysicalQuantityWebMvcConverter(Class<Q> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Q convert(String source) {
        return PhysicalQuantity.createFromEngineeringFormat(targetClass, source);
    }

}
