package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.BoilingNumber;
import com.synerset.unitility.unitsystem.dimensionless.BoilingNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoilingNumberPlainSiConverter implements AttributeConverter<BoilingNumber, Double> {

    public static final BoilingNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(BoilingNumber.class);

    @Override
    public Double convertToDatabaseColumn(BoilingNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public BoilingNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : BoilingNumber.of(dbData);
    }
}
