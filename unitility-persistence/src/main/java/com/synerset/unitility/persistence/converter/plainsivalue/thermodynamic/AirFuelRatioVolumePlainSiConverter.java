package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.AirFuelRatioVolume;
import com.synerset.unitility.unitsystem.thermodynamic.AirFuelRatioVolumeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AirFuelRatioVolumePlainSiConverter implements AttributeConverter<AirFuelRatioVolume, Double> {
    public static final AirFuelRatioVolumeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(AirFuelRatioVolume.class);

    @Override
    public Double convertToDatabaseColumn(AirFuelRatioVolume attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public AirFuelRatioVolume convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : AirFuelRatioVolume.of(dbData, DEFAULT_SI_UNIT);
    }
}
