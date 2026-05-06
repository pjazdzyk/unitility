package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.MartinelliParameter;
import com.synerset.unitility.unitsystem.dimensionless.MartinelliParameterUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MartinelliParameterPlainSiConverter implements AttributeConverter<MartinelliParameter, Double> {

    public static final MartinelliParameterUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MartinelliParameter.class);

    @Override
    public Double convertToDatabaseColumn(MartinelliParameter attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MartinelliParameter convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MartinelliParameter.of(dbData);
    }
}
