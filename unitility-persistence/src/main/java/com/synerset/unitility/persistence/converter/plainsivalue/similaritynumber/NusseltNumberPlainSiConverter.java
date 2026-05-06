package com.synerset.unitility.persistence.converter.plainsivalue.similaritynumber;

import com.synerset.unitility.unitsystem.similaritynumber.NusseltNumber;
import com.synerset.unitility.unitsystem.similaritynumber.NusseltNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NusseltNumberPlainSiConverter implements AttributeConverter<NusseltNumber, Double> {

    public static final NusseltNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(NusseltNumber.class);

    @Override
    public Double convertToDatabaseColumn(NusseltNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public NusseltNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : NusseltNumber.of(dbData);
    }
}
