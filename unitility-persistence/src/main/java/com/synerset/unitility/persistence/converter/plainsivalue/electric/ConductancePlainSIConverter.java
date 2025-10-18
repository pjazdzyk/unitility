package com.synerset.unitility.persistence.converter.plainsivalue.electric;

import com.synerset.unitility.unitsystem.electric.Conductance;
import com.synerset.unitility.unitsystem.electric.ConductanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConductancePlainSIConverter implements AttributeConverter<Conductance, Double> {

    public static final ConductanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Conductance.class);

    @Override
    public Double convertToDatabaseColumn(Conductance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Conductance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Conductance.of(dbData, DEFAULT_SI_UNIT);
    }

}