package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.Energy;
import com.synerset.unitility.unitsystem.thermodynamic.EnergyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EnergyPlainSiConverter implements AttributeConverter<Energy, Double> {

    public static final EnergyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Energy.class);

    @Override
    public Double convertToDatabaseColumn(Energy attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Energy convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Energy.of(dbData, DEFAULT_SI_UNIT);
    }

}