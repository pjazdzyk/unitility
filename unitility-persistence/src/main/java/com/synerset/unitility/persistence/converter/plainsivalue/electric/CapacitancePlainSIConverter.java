package com.synerset.unitility.persistence.converter.plainsivalue.electric;

import com.synerset.unitility.unitsystem.electric.Capacitance;
import com.synerset.unitility.unitsystem.electric.CapacitanceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CapacitancePlainSIConverter implements AttributeConverter<Capacitance, Double> {

    public static final CapacitanceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Capacitance.class);

    @Override
    public Double convertToDatabaseColumn(Capacitance attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Capacitance convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Capacitance.of(dbData, DEFAULT_SI_UNIT);
    }

}