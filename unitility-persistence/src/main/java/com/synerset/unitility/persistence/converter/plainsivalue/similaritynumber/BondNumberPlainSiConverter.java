package com.synerset.unitility.persistence.converter.plainsivalue.similaritynumber;

import com.synerset.unitility.unitsystem.similaritynumber.BondNumber;
import com.synerset.unitility.unitsystem.similaritynumber.BondNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BondNumberPlainSiConverter implements AttributeConverter<BondNumber, Double> {

    public static final BondNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(BondNumber.class);

    @Override
    public Double convertToDatabaseColumn(BondNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public BondNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : BondNumber.of(dbData);
    }
}
