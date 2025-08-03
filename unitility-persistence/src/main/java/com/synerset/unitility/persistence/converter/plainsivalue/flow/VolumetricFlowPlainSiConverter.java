package com.synerset.unitility.persistence.converter.plainsivalue.flow;

import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.flow.VolumetricFlowUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VolumetricFlowPlainSiConverter implements AttributeConverter<VolumetricFlow, Double> {

    public static final VolumetricFlowUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(VolumetricFlow.class);

    @Override
    public Double convertToDatabaseColumn(VolumetricFlow attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public VolumetricFlow convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : VolumetricFlow.of(dbData, DEFAULT_SI_UNIT);
    }

}