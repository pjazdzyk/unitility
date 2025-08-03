package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.Curvature;
import com.synerset.unitility.unitsystem.common.CurvatureUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CurvaturePlainSiConverter implements AttributeConverter<Curvature, Double> {

    public static final CurvatureUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Curvature.class);

    @Override
    public Double convertToDatabaseColumn(Curvature attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Curvature convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Curvature.of(dbData, DEFAULT_SI_UNIT);
    }

}