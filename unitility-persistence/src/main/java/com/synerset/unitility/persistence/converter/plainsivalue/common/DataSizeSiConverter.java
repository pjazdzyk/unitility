package com.synerset.unitility.persistence.converter.plainsivalue.common;

import com.synerset.unitility.unitsystem.common.DataSize;
import com.synerset.unitility.unitsystem.common.DataSizeUnit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DataSizeSiConverter implements AttributeConverter<DataSize, Double> {

    public static final DataSizeUnit DEFAULT_SI_UNIT = PhysicalQuantityParsingFactory.getDefaultParsingFactory()
            .getDefaultUnit(DataSize.class);

    @Override
    public Double convertToDatabaseColumn(DataSize attribute) {
        return attribute == null ? null : attribute.getInUnit(DEFAULT_SI_UNIT);
    }

    @Override
    public DataSize convertToEntityAttribute(Double dbData) {
        return dbData == null ? null : DataSize.of(dbData, DEFAULT_SI_UNIT);
    }

}