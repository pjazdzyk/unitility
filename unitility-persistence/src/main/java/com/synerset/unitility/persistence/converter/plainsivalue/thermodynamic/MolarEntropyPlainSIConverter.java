package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.MolarEntropy;
import com.synerset.unitility.unitsystem.thermodynamic.MolarEntropyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MolarEntropyPlainSIConverter implements AttributeConverter<MolarEntropy, Double> {
    public static final MolarEntropyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MolarEntropy.class);

    @Override
    public Double convertToDatabaseColumn(MolarEntropy attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MolarEntropy convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MolarEntropy.of(dbData, DEFAULT_SI_UNIT);
    }
}