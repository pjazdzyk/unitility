package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.Perimeter;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PerimeterPlainSiConverter implements AttributeConverter<Perimeter, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Perimeter.class);

    @Override
    public Double convertToDatabaseColumn(Perimeter attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Perimeter convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Perimeter.of(dbData, DEFAULT_SI_UNIT);
    }

}