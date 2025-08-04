package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.Density;
import com.synerset.unitility.unitsystem.thermodynamic.DensityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DensityPlainSiConverter implements AttributeConverter<Density, Double> {

    public static final DensityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Density.class);

    @Override
    public Double convertToDatabaseColumn(Density attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Density convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Density.of(dbData, DEFAULT_SI_UNIT);
    }

}
