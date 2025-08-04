package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.SpecificHeat;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificHeatUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificHeatPlainSiConverter implements AttributeConverter<SpecificHeat, Double> {

    public static final SpecificHeatUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SpecificHeat.class);

    @Override
    public Double convertToDatabaseColumn(SpecificHeat attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SpecificHeat convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificHeat.of(dbData, DEFAULT_SI_UNIT);
    }

}