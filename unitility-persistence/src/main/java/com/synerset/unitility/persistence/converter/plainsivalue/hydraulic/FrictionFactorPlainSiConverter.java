package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.FrictionFactor;
import com.synerset.unitility.unitsystem.hydraulic.FrictionFactorUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FrictionFactorPlainSiConverter implements AttributeConverter<FrictionFactor, Double> {

    public static final FrictionFactorUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(FrictionFactor.class);

    @Override
    public Double convertToDatabaseColumn(FrictionFactor attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public FrictionFactor convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : FrictionFactor.of(dbData);
    }

}