package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.EnergyDensity;
import com.synerset.unitility.unitsystem.thermodynamic.EnergyDensityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EnergyDensityPlainSIConverter implements AttributeConverter<EnergyDensity, Double> {
    public static final EnergyDensityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(EnergyDensity.class);

    @Override
    public Double convertToDatabaseColumn(EnergyDensity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public EnergyDensity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : EnergyDensity.of(dbData, DEFAULT_SI_UNIT);
    }
}
