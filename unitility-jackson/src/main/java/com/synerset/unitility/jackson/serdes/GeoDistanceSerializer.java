package com.synerset.unitility.jackson.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;

import java.io.IOException;

/**
 * The PhysicalQuantitySerializer class is a Jackson JSON serializer for serializing {@link GeoDistance} instances
 * to their JSON representations.
 */
public class GeoDistanceSerializer extends StdSerializer<GeoDistance> {

    public GeoDistanceSerializer(Class<GeoDistance> clazz) {
        super(clazz);
    }

    /**
     * Serializes a {@link  GeoDistance} instance to its JSON representation.
     *
     * @param geoDistance        The {@link GeoDistance} instance to be serialized.
     * @param jsonGenerator      The JSON generator used for writing JSON content.
     * @param serializerProvider The serializer provider that can be used for accessing serializers for
     *                           object types.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @Override
    public void serialize(GeoDistance geoDistance, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_START_COORD);
        serializerProvider.defaultSerializeValue(geoDistance.getStartCoordinate(), jsonGenerator);
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_TARGET_COORD);
        serializerProvider.defaultSerializeValue(geoDistance.getTargetCoordinate(), jsonGenerator);
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_TRUE_BEARING);
        serializerProvider.defaultSerializeValue(geoDistance.getTrueBearing(), jsonGenerator);
        jsonGenerator.writeFieldName(FieldNames.JSON_FIELD_DISTANCE);
        serializerProvider.defaultSerializeValue(geoDistance.getDistance(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}