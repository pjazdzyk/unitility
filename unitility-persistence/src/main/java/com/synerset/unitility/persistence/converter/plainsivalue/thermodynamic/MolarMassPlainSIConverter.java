package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.MolarMass;
import com.synerset.unitility.unitsystem.thermodynamic.MolarMassUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MolarMassPlainSIConverter implements AttributeConverter<MolarMass, Double> {
    public static final MolarMassUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MolarMass.class);

    @Override
    public Double convertToDatabaseColumn(MolarMass attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MolarMass convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MolarMass.of(dbData, DEFAULT_SI_UNIT);
    }
}