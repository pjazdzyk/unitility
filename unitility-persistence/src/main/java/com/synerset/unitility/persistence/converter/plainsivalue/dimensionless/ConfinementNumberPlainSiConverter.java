package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.ConfinementNumber;
import com.synerset.unitility.unitsystem.dimensionless.ConfinementNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConfinementNumberPlainSiConverter implements AttributeConverter<ConfinementNumber, Double> {

    public static final ConfinementNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(ConfinementNumber.class);

    @Override
    public Double convertToDatabaseColumn(ConfinementNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public ConfinementNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : ConfinementNumber.of(dbData);
    }
}
