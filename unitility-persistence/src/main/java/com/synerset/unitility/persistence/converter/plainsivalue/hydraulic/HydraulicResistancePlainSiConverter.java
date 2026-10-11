package com.synerset.unitility.persistence.converter.plainsivalue.hydraulic;

import com.synerset.unitility.unitsystem.hydraulic.HydraulicResistance;
import com.synerset.unitility.unitsystem.hydraulic.HydraulicResistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HydraulicResistancePlainSiConverter implements AttributeConverter<HydraulicResistance, Double> {

    public static final HydraulicResistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(HydraulicResistance.class);

    @Override
    public Double convertToDatabaseColumn(HydraulicResistance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public HydraulicResistance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : HydraulicResistance.of(dbData, DEFAULT_SI_UNIT);
    }

}