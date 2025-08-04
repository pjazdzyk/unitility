package com.synerset.unitility.persistence.converter.plainsivalue.humidity;

import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RelativeHumidityPlainSiConverter implements AttributeConverter<RelativeHumidity, Double> {

    public static final RelativeHumidityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(RelativeHumidity.class);

    @Override
    public Double convertToDatabaseColumn(RelativeHumidity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public RelativeHumidity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : RelativeHumidity.of(dbData, DEFAULT_SI_UNIT);
    }

}