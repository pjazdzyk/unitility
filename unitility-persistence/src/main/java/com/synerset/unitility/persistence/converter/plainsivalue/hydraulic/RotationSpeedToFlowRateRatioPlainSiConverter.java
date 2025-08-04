package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.RotationSpeedToFlowRateRatio;
import com.synerset.unitility.unitsystem.hydraulic.RotationSpeedToFlowRateRatioUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RotationSpeedToFlowRateRatioPlainSiConverter implements AttributeConverter<RotationSpeedToFlowRateRatio, Double> {

    public static final RotationSpeedToFlowRateRatioUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(RotationSpeedToFlowRateRatio.class);

    @Override
    public Double convertToDatabaseColumn(RotationSpeedToFlowRateRatio attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public RotationSpeedToFlowRateRatio convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : RotationSpeedToFlowRateRatio.of(dbData, DEFAULT_SI_UNIT);
    }

}