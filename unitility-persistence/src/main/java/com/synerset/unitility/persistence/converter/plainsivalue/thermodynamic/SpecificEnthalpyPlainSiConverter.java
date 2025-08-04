package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.SpecificEnthalpy;
import com.synerset.unitility.unitsystem.thermodynamic.SpecificEnthalpyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SpecificEnthalpyPlainSiConverter implements AttributeConverter<SpecificEnthalpy, Double> {

    public static final SpecificEnthalpyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(SpecificEnthalpy.class);

    @Override
    public Double convertToDatabaseColumn(SpecificEnthalpy attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public SpecificEnthalpy convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : SpecificEnthalpy.of(dbData, DEFAULT_SI_UNIT);
    }

}