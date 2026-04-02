package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.IsothermalCompressibility;
import com.synerset.unitility.unitsystem.thermodynamic.IsothermalCompressibilityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IsothermalCompressibilityPlainSIConverter implements AttributeConverter<IsothermalCompressibility, Double> {
    public static final IsothermalCompressibilityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(IsothermalCompressibility.class);

    @Override
    public Double convertToDatabaseColumn(IsothermalCompressibility attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public IsothermalCompressibility convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : IsothermalCompressibility.of(dbData, DEFAULT_SI_UNIT);
    }
}