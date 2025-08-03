package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivity;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalConductivityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ThermalConductivityPlainSiConverter implements AttributeConverter<ThermalConductivity, Double> {

    public static final ThermalConductivityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(ThermalConductivity.class);

    @Override
    public Double convertToDatabaseColumn(ThermalConductivity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public ThermalConductivity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : ThermalConductivity.of(dbData, DEFAULT_SI_UNIT);
    }

}
