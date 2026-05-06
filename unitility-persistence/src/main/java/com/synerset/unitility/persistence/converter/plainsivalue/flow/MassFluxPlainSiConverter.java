package com.synerset.unitility.persistence.converter.plainsivalue.flow;

import com.synerset.unitility.unitsystem.flow.MassFlux;
import com.synerset.unitility.unitsystem.flow.MassFluxUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MassFluxPlainSiConverter implements AttributeConverter<MassFlux, Double> {

    public static final MassFluxUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MassFlux.class);

    @Override
    public Double convertToDatabaseColumn(MassFlux attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MassFlux convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MassFlux.of(dbData, DEFAULT_SI_UNIT);
    }
}
