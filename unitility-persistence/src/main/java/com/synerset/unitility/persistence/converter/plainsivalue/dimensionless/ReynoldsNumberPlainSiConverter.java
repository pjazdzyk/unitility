package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumber;
import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumberUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ReynoldsNumberPlainSiConverter implements AttributeConverter<ReynoldsNumber, Double> {

    public static final ReynoldsNumberUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(ReynoldsNumber.class);

    @Override
    public Double convertToDatabaseColumn(ReynoldsNumber attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public ReynoldsNumber convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : ReynoldsNumber.of(dbData);
    }

}