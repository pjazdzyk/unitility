package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.common.RatioUnit;
import com.synerset.unitility.unitsystem.hydraulic.SDR;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SDRPlainSiConverter implements AttributeConverter<SDR, Double> {

    public static final RatioUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SDR.class);

    @Override
    public Double convertToDatabaseColumn(SDR attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SDR convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SDR.of(dbData, DEFAULT_SI_UNIT);
    }

}