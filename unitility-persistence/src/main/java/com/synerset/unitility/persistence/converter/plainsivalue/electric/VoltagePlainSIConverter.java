package com.synerset.unitility.persistence.converter.plainsivalue.electric;

import com.synerset.unitility.unitsystem.electric.Voltage;
import com.synerset.unitility.unitsystem.electric.VoltageUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VoltagePlainSIConverter implements AttributeConverter<Voltage, Double> {

    public static final VoltageUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Voltage.class);

    @Override
    public Double convertToDatabaseColumn(Voltage attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Voltage convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Voltage.of(dbData, DEFAULT_SI_UNIT);
    }

}