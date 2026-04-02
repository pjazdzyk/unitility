package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.CompressibilityFactor;
import com.synerset.unitility.unitsystem.dimensionless.CompressibilityFactorUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CompressibilityFactorPlainSIConverter implements AttributeConverter<CompressibilityFactor, Double> {
    public static final CompressibilityFactorUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(CompressibilityFactor.class);

    @Override
    public Double convertToDatabaseColumn(CompressibilityFactor attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public CompressibilityFactor convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : CompressibilityFactor.of(dbData);
    }
}