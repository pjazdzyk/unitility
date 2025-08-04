package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumber;
import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PrandtlNumberPlainSiConverter implements AttributeConverter<PrandtlNumber, Double> {

    public static final PrandtlNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(PrandtlNumber.class);

    @Override
    public Double convertToDatabaseColumn(PrandtlNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public PrandtlNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : PrandtlNumber.of(dbData);
    }

}