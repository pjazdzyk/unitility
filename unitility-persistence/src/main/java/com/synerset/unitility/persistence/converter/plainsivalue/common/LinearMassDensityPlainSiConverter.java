package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.LinearMassDensity;
import com.synerset.unitility.unitsystem.common.LinearMassDensityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LinearMassDensityPlainSiConverter implements AttributeConverter<LinearMassDensity, Double> {

    public static final LinearMassDensityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(LinearMassDensity.class);

    @Override
    public Double convertToDatabaseColumn(LinearMassDensity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public LinearMassDensity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : LinearMassDensity.of(dbData, DEFAULT_SI_UNIT);
    }

}