package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TemperaturePlainSiConverter implements AttributeConverter<Temperature, Double> {

    public static final TemperatureUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Temperature.class);

    @Override
    public Double convertToDatabaseColumn(Temperature attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Temperature convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Temperature.of(dbData, DEFAULT_SI_UNIT);
    }

}