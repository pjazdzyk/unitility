package com.synerset.unitility.persistence.converter.plainsivalue.flow;

import com.synerset.unitility.unitsystem.flow.NormalVolumetricFlow;
import com.synerset.unitility.unitsystem.flow.NormalVolumetricFlowUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NormalVolumetricFlowPlainSiConverter implements AttributeConverter<NormalVolumetricFlow, Double> {

    public static final NormalVolumetricFlowUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(NormalVolumetricFlow.class);

    @Override
    public Double convertToDatabaseColumn(NormalVolumetricFlow attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public NormalVolumetricFlow convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : NormalVolumetricFlow.of(dbData, DEFAULT_SI_UNIT);
    }

}
