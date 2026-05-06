package com.synerset.unitility.persistence.converter.plainsivalue.similaritynumber;

import com.synerset.unitility.unitsystem.similaritynumber.WeberNumber;
import com.synerset.unitility.unitsystem.similaritynumber.WeberNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class WeberNumberPlainSiConverter implements AttributeConverter<WeberNumber, Double> {

    public static final WeberNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(WeberNumber.class);

    @Override
    public Double convertToDatabaseColumn(WeberNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public WeberNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : WeberNumber.of(dbData);
    }
}
