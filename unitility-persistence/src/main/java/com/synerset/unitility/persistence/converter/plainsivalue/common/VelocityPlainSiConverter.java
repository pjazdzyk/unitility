package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Velocity;
import com.synerset.unitility.unitsystem.common.VelocityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VelocityPlainSiConverter implements AttributeConverter<Velocity, Double> {

    public static final VelocityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Velocity.class);

    @Override
    public Double convertToDatabaseColumn(Velocity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Velocity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Velocity.of(dbData, DEFAULT_SI_UNIT);
    }

}