package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.HeatTransferCoefficient;
import com.synerset.unitility.unitsystem.thermodynamic.HeatTransferCoefficientUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HeatTransferCoefficientPlainSiConverter implements AttributeConverter<HeatTransferCoefficient, Double> {

    public static final HeatTransferCoefficientUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(HeatTransferCoefficient.class);

    @Override
    public Double convertToDatabaseColumn(HeatTransferCoefficient attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public HeatTransferCoefficient convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : HeatTransferCoefficient.of(dbData, DEFAULT_SI_UNIT);
    }
}
