package com.synerset.unitility.persistence.converter.plainsivalue.acoustic;

import com.synerset.unitility.unitsystem.acoustic.SoundPressure;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SoundPressurePlainSIConverter implements AttributeConverter<SoundPressure, Double> {

    public static final PressureUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SoundPressure.class);

    @Override
    public Double convertToDatabaseColumn(SoundPressure attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SoundPressure convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SoundPressure.of(dbData, DEFAULT_SI_UNIT);
    }

}