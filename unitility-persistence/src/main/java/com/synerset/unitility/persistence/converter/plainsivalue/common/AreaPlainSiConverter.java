package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Area;
import com.synerset.unitility.unitsystem.common.AreaUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AreaPlainSiConverter implements AttributeConverter<Area, Double> {

    public static final AreaUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Area.class);

    @Override
    public Double convertToDatabaseColumn(Area attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Area convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Area.of(dbData, DEFAULT_SI_UNIT);
    }

}