package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductance;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ThermalConductancePlainSiConverter implements AttributeConverter<ThermalConductance, Double> {

    public static final ThermalConductanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(ThermalConductance.class);

    @Override
    public Double convertToDatabaseColumn(ThermalConductance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public ThermalConductance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : ThermalConductance.of(dbData, DEFAULT_SI_UNIT);
    }

}
