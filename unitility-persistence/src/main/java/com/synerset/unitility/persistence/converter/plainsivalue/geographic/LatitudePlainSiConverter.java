package com.synerset.unitility.persistence.converter.plainsivalue.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LatitudePlainSiConverter implements AttributeConverter<Latitude, Double> {

    public static final AngleUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Latitude.class);

    @Override
    public Double convertToDatabaseColumn(Latitude attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Latitude convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Latitude.of(dbData, DEFAULT_SI_UNIT);
    }

}