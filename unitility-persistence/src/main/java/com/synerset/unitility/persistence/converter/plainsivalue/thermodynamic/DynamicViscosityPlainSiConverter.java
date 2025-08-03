package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.DynamicViscosity;
import com.synerset.unitility.unitsystem.thermodynamic.DynamicViscosityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DynamicViscosityPlainSiConverter implements AttributeConverter<DynamicViscosity, Double> {

    public static final DynamicViscosityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(DynamicViscosity.class);

    @Override
    public Double convertToDatabaseColumn(DynamicViscosity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public DynamicViscosity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : DynamicViscosity.of(dbData, DEFAULT_SI_UNIT);
    }

}