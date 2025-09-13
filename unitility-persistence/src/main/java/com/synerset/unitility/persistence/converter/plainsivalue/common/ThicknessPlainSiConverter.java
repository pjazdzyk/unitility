package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.Thickness;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ThicknessPlainSiConverter implements AttributeConverter<Thickness, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Thickness.class);

    @Override
    public Double convertToDatabaseColumn(Thickness attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Thickness convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Thickness.of(dbData, DEFAULT_SI_UNIT);
    }

}