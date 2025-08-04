package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Diameter;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DiameterPlainSiConverter implements AttributeConverter<Diameter, Double> {

    public static final DistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Diameter.class);

    @Override
    public Double convertToDatabaseColumn(Diameter attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Diameter convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Diameter.of(dbData, DEFAULT_SI_UNIT);
    }

}