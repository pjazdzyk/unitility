package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.Width;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class WidthPlainSiConverter implements AttributeConverter<Width, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Width.class);

    @Override
    public Double convertToDatabaseColumn(Width attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Width convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Width.of(dbData, DEFAULT_SI_UNIT);
    }

}