package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.MolarEnthalpy;
import com.synerset.unitility.unitsystem.thermodynamic.MolarEnthalpyUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MolarEnthalpyPlainSIConverter implements AttributeConverter<MolarEnthalpy, Double> {
    public static final MolarEnthalpyUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(MolarEnthalpy.class);

    @Override
    public Double convertToDatabaseColumn(MolarEnthalpy attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public MolarEnthalpy convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : MolarEnthalpy.of(dbData, DEFAULT_SI_UNIT);
    }
}