package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.common.RatioUnit;
import com.synerset.unitility.unitsystem.thermodynamic.Absorptivity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AbsorptivityPlainSiConverter implements AttributeConverter<Absorptivity, Double> {

    public static final RatioUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Absorptivity.class);

    @Override
    public Double convertToDatabaseColumn(Absorptivity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Absorptivity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Absorptivity.of(dbData, DEFAULT_SI_UNIT);
    }

}
