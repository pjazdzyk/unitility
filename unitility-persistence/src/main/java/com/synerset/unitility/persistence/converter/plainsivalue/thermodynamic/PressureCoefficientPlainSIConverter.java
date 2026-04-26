package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.PressureCoefficient;
import com.synerset.unitility.unitsystem.thermodynamic.PressureCoefficientUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PressureCoefficientPlainSIConverter implements AttributeConverter<PressureCoefficient, Double> {
    public static final PressureCoefficientUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(PressureCoefficient.class);

    @Override
    public Double convertToDatabaseColumn(PressureCoefficient attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public PressureCoefficient convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : PressureCoefficient.of(dbData, DEFAULT_SI_UNIT);
    }
}
