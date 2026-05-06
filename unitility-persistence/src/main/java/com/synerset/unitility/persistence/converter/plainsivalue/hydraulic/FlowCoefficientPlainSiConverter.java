package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.FlowCoefficient;
import com.synerset.unitility.unitsystem.hydraulic.FlowCoefficientUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FlowCoefficientPlainSiConverter implements AttributeConverter<FlowCoefficient, Double> {

    public static final FlowCoefficientUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(FlowCoefficient.class);

    @Override
    public Double convertToDatabaseColumn(FlowCoefficient attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public FlowCoefficient convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : FlowCoefficient.of(dbData, DEFAULT_SI_UNIT);
    }
}
