package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.SpecificFanPower;
import com.synerset.unitility.unitsystem.hydraulic.SpecificFanPowerUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificFanPowerPlainSiConverter implements AttributeConverter<SpecificFanPower, Double> {

    public static final SpecificFanPowerUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory
            .getDefaultParsingFactory()
            .getDefaultUnit(SpecificFanPower.class);

    @Override
    public Double convertToDatabaseColumn(SpecificFanPower attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SpecificFanPower convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificFanPower.of(dbData, DEFAULT_SI_UNIT);
    }

}
