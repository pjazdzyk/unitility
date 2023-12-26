package com.synerset.unitility.spring;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.parsers.PhysicalQuantityParsingFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

/**
 * The PhysicalQuantityWebMvcConverter class is a Spring converter for converting strings to PhysicalQuantity instances.
 *
 * @param <U> The type of unit associated with the PhysicalQuantity.
 * @param <Q> The type of PhysicalQuantity.
 */
public class PhysicalQuantityWebMvcConverter<U extends Unit, Q extends PhysicalQuantity<U>> implements Converter<String, Q> {

    private final Class<Q> targetClass;
    private final PhysicalQuantityParsingFactory parsingFactory;

    public PhysicalQuantityWebMvcConverter(Class<Q> targetClass, PhysicalQuantityParsingFactory parsingFactory) {
        this.targetClass = targetClass;
        this.parsingFactory = parsingFactory;
    }

    /**
     * Converts a string to a PhysicalQuantity instance using the {@link PhysicalQuantityParsingFactory}.
     * Spring calls this method to convert a string input to a PhysicalQuantity instance using the
     * {@link PhysicalQuantityParsingFactory}.
     *
     * @param source The string representation of the PhysicalQuantity.
     * @return A PhysicalQuantity instance.
     */
    @Override
    public Q convert(@NonNull String source) {
        return parsingFactory.parseFromEngFormat(targetClass, source);
    }

}
