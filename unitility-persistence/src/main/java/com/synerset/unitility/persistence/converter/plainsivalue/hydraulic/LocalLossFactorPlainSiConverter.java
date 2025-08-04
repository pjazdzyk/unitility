package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.LocalLossFactor;
import com.synerset.unitility.unitsystem.hydraulic.LocalLossFactorUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LocalLossFactorPlainSiConverter implements AttributeConverter<LocalLossFactor, Double> {

    public static final LocalLossFactorUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(LocalLossFactor.class);

    @Override
    public Double convertToDatabaseColumn(LocalLossFactor attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public LocalLossFactor convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : LocalLossFactor.of(dbData);
    }

}