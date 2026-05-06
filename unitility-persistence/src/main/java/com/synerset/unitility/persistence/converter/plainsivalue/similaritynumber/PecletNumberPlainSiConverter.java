package com.synerset.unitility.persistence.converter.plainsivalue.similaritynumber;

import com.synerset.unitility.unitsystem.similaritynumber.PecletNumber;
import com.synerset.unitility.unitsystem.similaritynumber.PecletNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PecletNumberPlainSiConverter implements AttributeConverter<PecletNumber, Double> {

    public static final PecletNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(PecletNumber.class);

    @Override
    public Double convertToDatabaseColumn(PecletNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public PecletNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : PecletNumber.of(dbData);
    }
}
