package com.synerset.unitility.persistence.converter.plainsivalue.flow;

import com.synerset.unitility.unitsystem.flow.MassFlow;
import com.synerset.unitility.unitsystem.flow.MassFlowUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MassFlowPlainSiConverter implements AttributeConverter<MassFlow, Double> {

    public static final MassFlowUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MassFlow.class);

    @Override
    public Double convertToDatabaseColumn(MassFlow attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MassFlow convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MassFlow.of(dbData, DEFAULT_SI_UNIT);
    }

}