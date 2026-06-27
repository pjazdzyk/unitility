package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.common.RatioUnit;
import com.synerset.unitility.unitsystem.thermodynamic.Emissivity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmissivityPlainSiConverter implements AttributeConverter<Emissivity, Double> {

    public static final RatioUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Emissivity.class);

    @Override
    public Double convertToDatabaseColumn(Emissivity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Emissivity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Emissivity.of(dbData, DEFAULT_SI_UNIT);
    }

}
