package com.synerset.unitility.persistence.converter.plainsivalue.geographic;

import com.synerset.unitility.unitsystem.common.AngleUnit;
import com.synerset.unitility.unitsystem.geographic.Bearing;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BearingPlainSiConverter implements AttributeConverter<Bearing, Double> {

    public static final AngleUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(Bearing.class);

    @Override
    public Double convertToDatabaseColumn(Bearing attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public Bearing convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : Bearing.of(dbData, DEFAULT_SI_UNIT.getSymbol());
    }

}
