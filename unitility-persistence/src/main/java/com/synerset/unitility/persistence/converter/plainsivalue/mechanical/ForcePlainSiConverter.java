package com.synerset.unitility.persistence.converter.plainsivalue.mechanical;

import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.ForceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ForcePlainSiConverter implements AttributeConverter<Force, Double> {

    public static final ForceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Force.class);

    @Override
    public Double convertToDatabaseColumn(Force attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Force convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Force.of(dbData, DEFAULT_SI_UNIT);
    }

}