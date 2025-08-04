package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.GenericDimensionless;
import com.synerset.unitility.unitsystem.dimensionless.GenericDimensionlessUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenericDimensionlessPlainSiConverter implements AttributeConverter<GenericDimensionless, Double> {

    public static final GenericDimensionlessUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(GenericDimensionless.class);

    @Override
    public Double convertToDatabaseColumn(GenericDimensionless attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public GenericDimensionless convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : GenericDimensionless.of(dbData);
    }

}