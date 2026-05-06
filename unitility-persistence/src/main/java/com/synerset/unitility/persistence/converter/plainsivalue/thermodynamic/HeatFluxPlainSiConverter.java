package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.HeatFlux;
import com.synerset.unitility.unitsystem.thermodynamic.HeatFluxUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HeatFluxPlainSiConverter implements AttributeConverter<HeatFlux, Double> {

    public static final HeatFluxUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(HeatFlux.class);

    @Override
    public Double convertToDatabaseColumn(HeatFlux attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public HeatFlux convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : HeatFlux.of(dbData, DEFAULT_SI_UNIT);
    }
}
