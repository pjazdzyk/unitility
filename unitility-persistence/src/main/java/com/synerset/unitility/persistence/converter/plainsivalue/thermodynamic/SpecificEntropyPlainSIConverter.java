package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.SpecificEntropy;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificEntropyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificEntropyPlainSIConverter implements AttributeConverter<SpecificEntropy, Double> {
    public static final SpecificEntropyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SpecificEntropy.class);

    @Override
    public Double convertToDatabaseColumn(SpecificEntropy attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SpecificEntropy convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificEntropy.of(dbData, DEFAULT_SI_UNIT);
    }
}