package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Time;
import com.synerset.unitility.unitsystem.common.TimeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TimePlainSiConverter implements AttributeConverter<Time, Double> {

    public static final TimeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Time.class);

    @Override
    public Double convertToDatabaseColumn(Time attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Time convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Time.of(dbData, DEFAULT_SI_UNIT);
    }

}
