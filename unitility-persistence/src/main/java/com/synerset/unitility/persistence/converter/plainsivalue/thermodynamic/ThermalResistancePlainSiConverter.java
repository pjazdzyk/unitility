package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.ThermalResistance;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalResistanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ThermalResistancePlainSiConverter implements AttributeConverter<ThermalResistance, Double> {

    public static final ThermalResistanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(ThermalResistance.class);

    @Override
    public Double convertToDatabaseColumn(ThermalResistance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public ThermalResistance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : ThermalResistance.of(dbData, DEFAULT_SI_UNIT);
    }
}
