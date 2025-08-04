package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactorUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BypassFactorPlainSiConverter implements AttributeConverter<BypassFactor, Double> {

    public static final BypassFactorUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(BypassFactor.class);

    @Override
    public Double convertToDatabaseColumn(BypassFactor attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public BypassFactor convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : BypassFactor.of(dbData);
    }

}