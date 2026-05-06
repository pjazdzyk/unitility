package com.synerset.unitility.persistence.converter.plainsivalue.similaritynumber;

import com.synerset.unitility.unitsystem.similaritynumber.BiotNumber;
import com.synerset.unitility.unitsystem.similaritynumber.BiotNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BiotNumberPlainSiConverter implements AttributeConverter<BiotNumber, Double> {

    public static final BiotNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(BiotNumber.class);

    @Override
    public Double convertToDatabaseColumn(BiotNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public BiotNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : BiotNumber.of(dbData);
    }
}
