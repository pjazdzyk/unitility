package com.synerset.unitility.persistence.converter.plainsivalue.mechanical;

import com.synerset.unitility.unitsystem.mechanical.Momentum;
import com.synerset.unitility.unitsystem.mechanical.MomentumUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MomentumPlainSiConverter implements AttributeConverter<Momentum, Double> {

    public static final MomentumUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Momentum.class);

    @Override
    public Double convertToDatabaseColumn(Momentum attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Momentum convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Momentum.of(dbData, DEFAULT_SI_UNIT);
    }

}