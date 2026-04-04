package com.synerset.unitility.jackson.legacy.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;

import java.io.IOException;

/**
 * The GeoDistanceSerializer class is a Jackson 2.x JSON serializer for serializing {@link GeoDistance} instances
 * to their JSON representations.
 */
public class GeoDistanceSerializer extends StdSerializer<GeoDistance> {

    public GeoDistanceSerializer(Class<GeoDistance> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(GeoDistance geoDistance, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_START_COORD);
        serializerProvider.defaultSerializeValue(geoDistance.getStartCoordinate(), jsonGenerator);
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_TARGET_COORD);
        serializerProvider.defaultSerializeValue(geoDistance.getTargetCoordinate(), jsonGenerator);
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_TRUE_BEARING);
        serializerProvider.defaultSerializeValue(geoDistance.getBearing(), jsonGenerator);
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_DISTANCE);
        serializerProvider.defaultSerializeValue(geoDistance.getDistance(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
