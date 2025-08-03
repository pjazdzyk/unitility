package com.synerset.unitility.persistence.converter.plainsivalue.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LongitudePlainSiConverter implements AttributeConverter<Longitude, Double> {

    public static final AngleUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Longitude.class);

    @Override
    public Double convertToDatabaseColumn(Longitude attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Longitude convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Longitude.of(dbData, DEFAULT_SI_UNIT);
    }

}