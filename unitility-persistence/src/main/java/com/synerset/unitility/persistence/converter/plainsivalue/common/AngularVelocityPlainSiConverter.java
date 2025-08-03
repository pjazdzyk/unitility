package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.AngularVelocity;
import com.synerset.unitility.unitsystem.common.AngularVelocityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AngularVelocityPlainSiConverter implements AttributeConverter<AngularVelocity, Double> {

    public static final AngularVelocityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(AngularVelocity.class);

    @Override
    public Double convertToDatabaseColumn(AngularVelocity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public AngularVelocity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : AngularVelocity.of(dbData, DEFAULT_SI_UNIT);
    }

}