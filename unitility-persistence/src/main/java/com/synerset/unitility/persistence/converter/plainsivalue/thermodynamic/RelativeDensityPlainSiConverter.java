package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.RelativeDensity;
import com.synerset.unitility.unitsystem.thermodynamic.RelativeDensityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RelativeDensityPlainSiConverter implements AttributeConverter<RelativeDensity, Double> {

    public static final RelativeDensityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(RelativeDensity.class);

    @Override
    public Double convertToDatabaseColumn(RelativeDensity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public RelativeDensity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : RelativeDensity.of(dbData, DEFAULT_SI_UNIT);
    }

}
