package com.synerset.unitility.persistence.converter.plainsivalue.geographic;

import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GeoCoordinateToStringConverter implements AttributeConverter<GeoCoordinate, String> {

    @Override
    public String convertToDatabaseColumn(GeoCoordinate geoCoordinate) {
        return geoCoordinate == null ? null : geoCoordinate.toDecimalDegrees();
    }

    @Override
    public GeoCoordinate convertToEntityAttribute(String stringDecimalDegrees) {
        if (stringDecimalDegrees == null) {
            return null;
        }

        String[] parts = stringDecimalDegrees.trim().split(",");

        Latitude latitude = Latitude.ofDegrees(Double.parseDouble(parts[0]));
        Longitude longitude = Longitude.ofDegrees(Double.parseDouble(parts[1]));

        return GeoCoordinate.of(latitude, longitude);

    }
}