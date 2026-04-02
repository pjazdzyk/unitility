package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.MolarVolume;
import com.synerset.unitility.unitsystem.thermodynamic.MolarVolumeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MolarVolumePlainSIConverter implements AttributeConverter<MolarVolume, Double> {
    public static final MolarVolumeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MolarVolume.class);

    @Override
    public Double convertToDatabaseColumn(MolarVolume attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MolarVolume convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MolarVolume.of(dbData, DEFAULT_SI_UNIT);
    }
}