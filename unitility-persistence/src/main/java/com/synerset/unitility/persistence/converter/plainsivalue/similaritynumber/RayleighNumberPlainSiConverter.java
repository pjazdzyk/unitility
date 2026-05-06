package com.synerset.unitility.persistence.converter.plainsivalue.similaritynumber;

import com.synerset.unitility.unitsystem.similaritynumber.RayleighNumber;
import com.synerset.unitility.unitsystem.similaritynumber.RayleighNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RayleighNumberPlainSiConverter implements AttributeConverter<RayleighNumber, Double> {

    public static final RayleighNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(RayleighNumber.class);

    @Override
    public Double convertToDatabaseColumn(RayleighNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public RayleighNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : RayleighNumber.of(dbData);
    }
}
