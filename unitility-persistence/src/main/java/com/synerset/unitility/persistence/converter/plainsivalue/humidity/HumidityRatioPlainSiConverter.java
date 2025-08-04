package com.synerset.unitility.persistence.converter.plainsivalue.humidity;

import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.HumidityRatioUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HumidityRatioPlainSiConverter implements AttributeConverter<HumidityRatio, Double> {

    public static final HumidityRatioUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(HumidityRatio.class);

    @Override
    public Double convertToDatabaseColumn(HumidityRatio attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public HumidityRatio convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : HumidityRatio.of(dbData, DEFAULT_SI_UNIT);
    }

}