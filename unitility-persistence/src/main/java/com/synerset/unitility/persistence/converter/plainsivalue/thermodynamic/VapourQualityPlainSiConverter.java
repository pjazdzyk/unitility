package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.VapourQuality;
import com.synerset.unitility.unitsystem.thermodynamic.VapourQualityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VapourQualityPlainSiConverter implements AttributeConverter<VapourQuality, Double> {

    public static final VapourQualityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(VapourQuality.class);

    @Override
    public Double convertToDatabaseColumn(VapourQuality attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public VapourQuality convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : VapourQuality.of(dbData, DEFAULT_SI_UNIT);
    }

}
