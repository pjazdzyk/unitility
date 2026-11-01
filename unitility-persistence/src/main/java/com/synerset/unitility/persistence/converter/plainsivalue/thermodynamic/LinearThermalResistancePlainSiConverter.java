package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.LinearThermalResistance;
import com.synerset.unitility.unitsystem.thermodynamic.LinearThermalResistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LinearThermalResistancePlainSiConverter implements AttributeConverter<LinearThermalResistance, Double> {

    public static final LinearThermalResistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(LinearThermalResistance.class);

    @Override
    public Double convertToDatabaseColumn(LinearThermalResistance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public LinearThermalResistance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : LinearThermalResistance.of(dbData, DEFAULT_SI_UNIT);
    }
}
