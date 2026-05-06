package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Effectiveness;
import com.synerset.unitility.unitsystem.common.EffectivenessUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EffectivenessPlainSiConverter implements AttributeConverter<Effectiveness, Double> {

    public static final EffectivenessUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Effectiveness.class);

    @Override
    public Double convertToDatabaseColumn(Effectiveness attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Effectiveness convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Effectiveness.of(dbData, DEFAULT_SI_UNIT);
    }

}
