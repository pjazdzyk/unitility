package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.HeatCapacity;
import com.synerset.unitility.unitsystem.thermodynamic.HeatCapacityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HeatCapacityPlainSiConverter implements AttributeConverter<HeatCapacity, Double> {

    public static final HeatCapacityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(HeatCapacity.class);

    @Override
    public Double convertToDatabaseColumn(HeatCapacity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public HeatCapacity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : HeatCapacity.of(dbData, DEFAULT_SI_UNIT);
    }

}
