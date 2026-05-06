package com.synerset.unitility.persistence.converter.plainsivalue.dimensionless;

import com.synerset.unitility.unitsystem.dimensionless.TwoPhaseMultiplier;
import com.synerset.unitility.unitsystem.dimensionless.TwoPhaseMultiplierUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TwoPhaseMultiplierPlainSiConverter implements AttributeConverter<TwoPhaseMultiplier, Double> {

    public static final TwoPhaseMultiplierUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(TwoPhaseMultiplier.class);

    @Override
    public Double convertToDatabaseColumn(TwoPhaseMultiplier attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public TwoPhaseMultiplier convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : TwoPhaseMultiplier.of(dbData);
    }
}
