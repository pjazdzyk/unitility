package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.hydraulic.AbsoluteRoughness;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AbsoluteRoughnessPlainSiConverter implements AttributeConverter<AbsoluteRoughness, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(AbsoluteRoughness.class);

    @Override
    public Double convertToDatabaseColumn(AbsoluteRoughness attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public AbsoluteRoughness convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : AbsoluteRoughness.of(dbData, DEFAULT_SI_UNIT);
    }

}