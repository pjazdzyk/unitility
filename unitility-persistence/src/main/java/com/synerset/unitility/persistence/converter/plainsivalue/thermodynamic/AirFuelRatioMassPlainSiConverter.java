package com.synerset.unitility.persistence.converter.plainsivalue.thermodynamic;

import com.synerset.unitility.unitsystem.thermodynamic.AirFuelRatioMass;
import com.synerset.unitility.unitsystem.thermodynamic.AirFuelRatioMassUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AirFuelRatioMassPlainSiConverter implements AttributeConverter<AirFuelRatioMass, Double> {
    public static final AirFuelRatioMassUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(AirFuelRatioMass.class);

    @Override
    public Double convertToDatabaseColumn(AirFuelRatioMass attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public AirFuelRatioMass convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : AirFuelRatioMass.of(dbData, DEFAULT_SI_UNIT);
    }
}
