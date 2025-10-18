package com.synerset.unitility.persistence.converter.plainsivalue.acoustic;

import com.synerset.unitility.unitsystem.acoustic.SoundPower;
import com.synerset.unitility.unitsystem.thermodynamic.PowerUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SoundPowerPlainSIConverter implements AttributeConverter<SoundPower, Double> {

    public static final PowerUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SoundPower.class);

    @Override
    public Double convertToDatabaseColumn(SoundPower attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SoundPower convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SoundPower.of(dbData, DEFAULT_SI_UNIT);
    }

}