package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.IsentropicCompressibility;
import com.synerset.unitility.unitsystem.thermodynamic.IsentropicCompressibilityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IsentropicCompressibilityPlainSIConverter implements AttributeConverter<IsentropicCompressibility, Double> {
    public static final IsentropicCompressibilityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(IsentropicCompressibility.class);

    @Override
    public Double convertToDatabaseColumn(IsentropicCompressibility attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public IsentropicCompressibility convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : IsentropicCompressibility.of(dbData, DEFAULT_SI_UNIT);
    }
}
