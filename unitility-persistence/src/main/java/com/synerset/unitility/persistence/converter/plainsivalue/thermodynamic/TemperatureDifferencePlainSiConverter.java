package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.TemperatureDifference;
import com.synerset.unitility.unitsystem.thermodynamic.TemperatureDifferenceUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TemperatureDifferencePlainSiConverter implements AttributeConverter<TemperatureDifference, Double> {

    public static final TemperatureDifferenceUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(TemperatureDifference.class);

    @Override
    public Double convertToDatabaseColumn(TemperatureDifference attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public TemperatureDifference convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : TemperatureDifference.of(dbData, DEFAULT_SI_UNIT);
    }

}
