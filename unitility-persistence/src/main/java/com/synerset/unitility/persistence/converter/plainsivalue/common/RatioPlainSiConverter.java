package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Ratio;
import com.synerset.unitility.unitsystem.common.RatioUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RatioPlainSiConverter implements AttributeConverter<Ratio, Double> {

    public static final RatioUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Ratio.class);

    @Override
    public Double convertToDatabaseColumn(Ratio attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Ratio convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Ratio.of(dbData, DEFAULT_SI_UNIT);
    }

}