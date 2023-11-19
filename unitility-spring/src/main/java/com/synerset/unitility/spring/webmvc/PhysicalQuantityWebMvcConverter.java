package com.synerset.unitility.spring.webmvc;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.springframework.core.convert.converter.Converter;

public class PhysicalQuantityWebMvcConverter<U extends Unit, Q extends PhysicalQuantity<U>> implements Converter<String, Q> {

    private final Class<Q> targetClass;
    private final PhysicalQuantityParsingRegistry parsingFactory;

    public PhysicalQuantityWebMvcConverter(Class<Q> targetClass, PhysicalQuantityParsingRegistry parsingFactory) {
        this.targetClass = targetClass;
        this.parsingFactory = parsingFactory;
    }

    @Override
    public Q convert(String source) {
        return parsingFactory.createFromEngFormat(targetClass, source);
    }

}
