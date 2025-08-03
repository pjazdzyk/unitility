package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.ThermalDiffusivity;
import com.synerset.unitility.unitsystem.thermodynamic.ThermalDiffusivityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ThermalDiffusivityPlainSiConverter implements AttributeConverter<ThermalDiffusivity, Double> {

    public static final ThermalDiffusivityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(ThermalDiffusivity.class);

    @Override
    public Double convertToDatabaseColumn(ThermalDiffusivity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public ThermalDiffusivity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : ThermalDiffusivity.of(dbData, DEFAULT_SI_UNIT);
    }

}
