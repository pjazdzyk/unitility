package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.JakobNumber;
import com.synerset.unitility.unitsystem.dimensionless.JakobNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JakobNumberPlainSiConverter implements AttributeConverter<JakobNumber, Double> {

    public static final JakobNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(JakobNumber.class);

    @Override
    public Double convertToDatabaseColumn(JakobNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public JakobNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : JakobNumber.of(dbData);
    }
}
