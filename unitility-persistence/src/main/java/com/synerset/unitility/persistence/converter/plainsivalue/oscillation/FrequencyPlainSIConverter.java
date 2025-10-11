package com.synerset.unitility.persistence.converter.plainsivalue.oscillation;

import com.synerset.unitility.unitsystem.oscillation.Frequency;
import com.synerset.unitility.unitsystem.oscillation.FrequencyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FrequencyPlainSIConverter implements AttributeConverter<Frequency, Double> {

    public static final FrequencyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Frequency.class);

    @Override
    public Double convertToDatabaseColumn(Frequency attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Frequency convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Frequency.of(dbData, DEFAULT_SI_UNIT);
    }

}