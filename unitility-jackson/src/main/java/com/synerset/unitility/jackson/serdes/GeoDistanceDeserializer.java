package com.synerset.unitility.jackson.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.common.Distance;
import com.synerset.unitility.unitsystem.common.DistanceUnit;
import com.synerset.unitility.unitsystem.common.DistanceUnits;
import com.synerset.unitility.unitsystem.geographic.GeoCoordinate;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;

import java.io.IOException;

public class GeoDistanceDeserializer extends JsonDeserializer<GeoDistance> {

    protected final GeoQuantityParsingFactory geoParsingFactory;

    public GeoDistanceDeserializer(GeoQuantityParsingFactory geoParsingFactory) {
        super();
        this.geoParsingFactory = geoParsingFactory;
    }

    @Override
    public GeoDistance deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        JsonNode startCoordinateNode = node.get(FieldNames.JSON_FIELD_START_COORD);
        if (startCoordinateNode == null) {
            throw new IOException("Deserialization failure. Missing field: " + FieldNames.JSON_FIELD_START_COORD);
        }

        GeoCoordinate startCoordinate = startCoordinateNode.traverse(parser.getCodec()).readValueAs(GeoCoordinate.class);

        if (startCoordinate == null) {
            throw new IOException("Deserialization failure. Could not extract: " + FieldNames.JSON_FIELD_START_COORD);
        }

        JsonNode targetCoordinateNode = node.get(FieldNames.JSON_FIELD_TARGET_COORD);

        GeoCoordinate targetCoordinate = null;
        if (targetCoordinateNode != null) {
            targetCoordinate = targetCoordinateNode.traverse(parser.getCodec()).readValueAs(GeoCoordinate.class);
        }

        JsonNode unitTypeNode = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL);

        if (targetCoordinate != null && unitTypeNode != null) {
            DistanceUnit distanceUnit = DistanceUnits.fromSymbol(unitTypeNode.asText());
            return GeoDistance.of(startCoordinate, targetCoordinate, distanceUnit);
        }

        if (targetCoordinate != null) {
            return GeoDistance.ofKilometers(startCoordinate, targetCoordinate);
        }

        Angle trueBearing = node.get(FieldNames.JSON_FIELD_TRUE_BEARING).traverse(parser.getCodec()).readValueAs(Angle.class);
        Distance distance = node.get(FieldNames.JSON_FIELD_DISTANCE).traverse(parser.getCodec()).readValueAs(Distance.class);

        return GeoDistance.of(startCoordinate, trueBearing, distance);
    }

}