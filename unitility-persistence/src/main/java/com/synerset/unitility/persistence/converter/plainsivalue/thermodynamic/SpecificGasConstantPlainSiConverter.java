package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.SpecificGasConstant;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificGasConstantUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificGasConstantPlainSiConverter implements AttributeConverter<SpecificGasConstant, Double> {

    public static final SpecificGasConstantUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SpecificGasConstant.class);

    @Override
    public Double convertToDatabaseColumn(SpecificGasConstant attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SpecificGasConstant convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificGasConstant.of(dbData, DEFAULT_SI_UNIT);
    }
}
