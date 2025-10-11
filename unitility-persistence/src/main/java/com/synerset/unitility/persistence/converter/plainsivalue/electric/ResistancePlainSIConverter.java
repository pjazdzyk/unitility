package com.synerset.unitility.persistence.converter.plainsivalue.electric;

import com.synerset.unitility.unitsystem.electric.Resistance;
import com.synerset.unitility.unitsystem.electric.ResistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ResistancePlainSIConverter implements AttributeConverter<Resistance, Double> {

    public static final ResistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Resistance.class);

    @Override
    public Double convertToDatabaseColumn(Resistance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Resistance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Resistance.of(dbData, DEFAULT_SI_UNIT);
    }

}