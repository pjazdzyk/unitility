package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.SpecificVolume;
import com.synerset.unitility.unitsystem.common.SpecificVolumeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificVolumePlainSIConverter implements AttributeConverter<SpecificVolume, Double> {
    public static final SpecificVolumeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SpecificVolume.class);

    @Override
    public Double convertToDatabaseColumn(SpecificVolume attribute) {
        return attribute == null ? null : attribute.getInCubicMeterPerKilogram();
    }

    @Override
    public SpecificVolume convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificVolume.of(dbData, DEFAULT_SI_UNIT);
    }
}