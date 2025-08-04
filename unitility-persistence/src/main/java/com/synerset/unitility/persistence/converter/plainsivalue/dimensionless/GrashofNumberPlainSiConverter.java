package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.dimensionless.GrashofNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GrashofNumberPlainSiConverter implements AttributeConverter<GrashofNumber, Double> {

    public static final GrashofNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(GrashofNumber.class);

    @Override
    public Double convertToDatabaseColumn(GrashofNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public GrashofNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : GrashofNumber.of(dbData);
    }

}