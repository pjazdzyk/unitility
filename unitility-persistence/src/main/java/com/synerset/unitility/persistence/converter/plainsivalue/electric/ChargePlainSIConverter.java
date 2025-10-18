package com.synerset.unitility.persistence.converter.plainsivalue.electric;

import com.synerset.unitility.unitsystem.electric.Charge;
import com.synerset.unitility.unitsystem.electric.ChargeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ChargePlainSIConverter implements AttributeConverter<Charge, Double> {

    public static final ChargeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Charge.class);

    @Override
    public Double convertToDatabaseColumn(Charge attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Charge convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Charge.of(dbData, DEFAULT_SI_UNIT);
    }

}