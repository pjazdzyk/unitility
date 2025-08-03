package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Volume;
import com.synerset.unitility.unitsystem.common.VolumeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VolumePlainSiConverter implements AttributeConverter<Volume, Double> {

    public static final VolumeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Volume.class);

    @Override
    public Double convertToDatabaseColumn(Volume attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Volume convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Volume.of(dbData, DEFAULT_SI_UNIT);
    }

}