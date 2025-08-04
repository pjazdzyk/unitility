package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.Pressure;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PressurePlainSiConverter implements AttributeConverter<Pressure, Double> {

    public static final PressureUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Pressure.class);

    @Override
    public Double convertToDatabaseColumn(Pressure attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Pressure convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Pressure.of(dbData, DEFAULT_SI_UNIT);
    }

}