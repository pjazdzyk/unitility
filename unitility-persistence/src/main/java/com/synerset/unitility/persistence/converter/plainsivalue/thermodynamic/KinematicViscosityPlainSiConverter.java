package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.KinematicViscosity;
import com.synerset.unitility.unitsystem.thermodynamic.KinematicViscosityUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class KinematicViscosityPlainSiConverter implements AttributeConverter<KinematicViscosity, Double> {

    public static final KinematicViscosityUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(KinematicViscosity.class);

    @Override
    public Double convertToDatabaseColumn(KinematicViscosity attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public KinematicViscosity convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : KinematicViscosity.of(dbData, DEFAULT_SI_UNIT);
    }

}