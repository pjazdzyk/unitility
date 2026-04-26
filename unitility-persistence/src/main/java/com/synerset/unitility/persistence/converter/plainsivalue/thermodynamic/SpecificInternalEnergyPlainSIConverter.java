package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.SpecificInternalEnergy;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificInternalEnergyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificInternalEnergyPlainSIConverter implements AttributeConverter<SpecificInternalEnergy, Double> {
    public static final SpecificInternalEnergyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SpecificInternalEnergy.class);

    @Override
    public Double convertToDatabaseColumn(SpecificInternalEnergy attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SpecificInternalEnergy convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificInternalEnergy.of(dbData, DEFAULT_SI_UNIT);
    }
}
