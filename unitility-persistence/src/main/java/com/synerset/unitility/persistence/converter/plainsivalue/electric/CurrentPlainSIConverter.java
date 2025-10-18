package com.synerset.unitility.persistence.converter.plainsivalue.electric;

import com.synerset.unitility.unitsystem.electric.Current;
import com.synerset.unitility.unitsystem.electric.CurrentUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CurrentPlainSIConverter implements AttributeConverter<Current, Double> {

    public static final CurrentUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Current.class);

    @Override
    public Double convertToDatabaseColumn(Current attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Current convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Current.of(dbData, DEFAULT_SI_UNIT);
    }

}