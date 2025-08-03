package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DistancePlainSiConverter implements AttributeConverter<Distance, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Distance.class);

    @Override
    public Double convertToDatabaseColumn(Distance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Distance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Distance.of(dbData, DEFAULT_SI_UNIT);
    }

}