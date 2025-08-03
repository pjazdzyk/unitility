package com.synerset.unitility.persistence.converter.plainsivalue.mechanical;

import com.synerset.unitility.unitsystem.mechanical.Torque;
import com.synerset.unitility.unitsystem.mechanical.TorqueUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TorquePlainSiConverter implements AttributeConverter<Torque, Double> {

    public static final TorqueUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Torque.class);

    @Override
    public Double convertToDatabaseColumn(Torque attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Torque convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Torque.of(dbData, DEFAULT_SI_UNIT);
    }

}