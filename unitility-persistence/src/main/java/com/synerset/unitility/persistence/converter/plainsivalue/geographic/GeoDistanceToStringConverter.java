package com.synerset.unitility.persistence.converter.plainsivalue.geographic;

import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GeoDistanceToStringConverter implements AttributeConverter<GeoDistance, String> {

    public static final String START_TARGET_SEPARATOR = ";";
    public static final GeoCoordinateToStringConverter GEO_COORDINATE_CONVERTER = new GeoCoordinateToStringConverter();

    @Override
    public String convertToDatabaseColumn(GeoDistance geoDistance) {
        if (geoDistance == null) {
            return null;
        }

        GeoCoordinate startCoordinate = geoDistance.getStartCoordinate();
        GeoCoordinate targetCoordinate = geoDistance.getTargetCoordinate();

        return startCoordinate.toDecimalDegrees() + START_TARGET_SEPARATOR + targetCoordinate.toDecimalDegrees();
    }

    @Override
    public GeoDistance convertToEntityAttribute(String geoDistanceAsString) {
        if (geoDistanceAsString == null) {
            return null;
        }

        String[] startTargetCoordinates = geoDistanceAsString.split(START_TARGET_SEPARATOR);
        String startTargetCoordinateString = startTargetCoordinates[0];
        String targetCoordinateString = startTargetCoordinates[1];

        GeoCoordinate startCoordinate = GEO_COORDINATE_CONVERTER.convertToEntityAttribute(startTargetCoordinateString);
        GeoCoordinate targetCoordinate = GEO_COORDINATE_CONVERTER.convertToEntityAttribute(targetCoordinateString);

        return GeoDistance.ofKilometers(startCoordinate, targetCoordinate);
    }

}