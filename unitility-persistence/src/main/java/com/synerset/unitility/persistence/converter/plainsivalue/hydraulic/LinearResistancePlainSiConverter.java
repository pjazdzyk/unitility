package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.LinearResistance;
import com.synerset.unitility.unitsystem.hydraulic.LinearResistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LinearResistancePlainSiConverter implements AttributeConverter<LinearResistance, Double> {

    public static final LinearResistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(LinearResistance.class);

    @Override
    public Double convertToDatabaseColumn(LinearResistance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public LinearResistance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : LinearResistance.of(dbData, DEFAULT_SI_UNIT);
    }

}