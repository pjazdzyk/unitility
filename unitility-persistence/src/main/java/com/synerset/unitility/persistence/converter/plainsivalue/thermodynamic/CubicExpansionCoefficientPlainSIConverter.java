package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.CubicExpansionCoefficient;
import com.synerset.unitility.unitsystem.thermodynamic.CubicExpansionCoefficientUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CubicExpansionCoefficientPlainSIConverter implements AttributeConverter<CubicExpansionCoefficient, Double> {
    public static final CubicExpansionCoefficientUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(CubicExpansionCoefficient.class);

    @Override
    public Double convertToDatabaseColumn(CubicExpansionCoefficient attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public CubicExpansionCoefficient convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : CubicExpansionCoefficient.of(dbData, DEFAULT_SI_UNIT);
    }
}
