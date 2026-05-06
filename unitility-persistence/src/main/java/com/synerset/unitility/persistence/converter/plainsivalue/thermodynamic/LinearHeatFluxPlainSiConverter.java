package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.LinearHeatFlux;
import com.synerset.unitility.unitsystem.thermodynamic.LinearHeatFluxUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LinearHeatFluxPlainSiConverter implements AttributeConverter<LinearHeatFlux, Double> {

    public static final LinearHeatFluxUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(LinearHeatFlux.class);

    @Override
    public Double convertToDatabaseColumn(LinearHeatFlux attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public LinearHeatFlux convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : LinearHeatFlux.of(dbData, DEFAULT_SI_UNIT);
    }
}
