package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.SurfaceTension;
import com.synerset.unitility.unitsystem.thermodynamic.SurfaceTensionUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SurfaceTensionPlainSiConverter implements AttributeConverter<SurfaceTension, Double> {

    public static final SurfaceTensionUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SurfaceTension.class);

    @Override
    public Double convertToDatabaseColumn(SurfaceTension attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SurfaceTension convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SurfaceTension.of(dbData, DEFAULT_SI_UNIT);
    }

}
