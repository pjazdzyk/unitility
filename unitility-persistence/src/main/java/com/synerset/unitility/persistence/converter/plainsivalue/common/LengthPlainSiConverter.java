package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Length;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LengthPlainSiConverter implements AttributeConverter<Length, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Length.class);

    @Override
    public Double convertToDatabaseColumn(Length attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Length convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Length.of(dbData, DEFAULT_SI_UNIT);
    }

}