package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Mass;
import com.synerset.unitility.unitsystem.common.MassUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MassPlainSiConverter implements AttributeConverter<Mass, Double> {

    public static final MassUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Mass.class);

    @Override
    public Double convertToDatabaseColumn(Mass attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Mass convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Mass.of(dbData, DEFAULT_SI_UNIT);
    }

}