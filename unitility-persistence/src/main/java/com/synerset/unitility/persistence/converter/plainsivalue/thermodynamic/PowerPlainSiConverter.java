package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.Power;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PowerPlainSiConverter implements AttributeConverter<Power, Double> {

    public static final PowerUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Power.class);

    @Override
    public Double convertToDatabaseColumn(Power attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Power convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Power.of(dbData, DEFAULT_SI_UNIT);
    }

}