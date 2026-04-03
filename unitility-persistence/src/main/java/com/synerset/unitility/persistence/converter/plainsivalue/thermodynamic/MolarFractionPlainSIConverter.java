package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.MolarFraction;
import com.synerset.unitility.unitsystem.thermodynamic.MolarFractionUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MolarFractionPlainSIConverter implements AttributeConverter<MolarFraction, Double> {
    public static final MolarFractionUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MolarFraction.class);

    @Override
    public Double convertToDatabaseColumn(MolarFraction attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MolarFraction convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MolarFraction.of(dbData, DEFAULT_SI_UNIT);
    }
}