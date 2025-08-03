package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AnglePlainSiConverter implements AttributeConverter<Angle, Double> {

    public static final AngleUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Angle.class);

    @Override
    public Double convertToDatabaseColumn(Angle attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Angle convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Angle.of(dbData, DEFAULT_SI_UNIT);
    }

}