package com.synerset.unitility.jackson.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;

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
     * @param geoDistance          The {@link GeoDistance} instance to be serialized.
     * @param jsonGenerator        The JSON generator used for writing JSON content.
     * @param serializationContext The serialization context used for accessing serializers for
     *                             object types.
     * @throws JacksonException If an error occurs during serialization.
     */
    @Override
    public void serialize(GeoDistance geoDistance, JsonGenerator jsonGenerator, SerializationContext serializationContext) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeName(FieldNames.JSON_FIELD_START_COORD);
        serializationContext.writeValue(jsonGenerator, geoDistance.getStartCoordinate());
        jsonGenerator.writeName(FieldNames.JSON_FIELD_TARGET_COORD);
        serializationContext.writeValue(jsonGenerator, geoDistance.getTargetCoordinate());
        jsonGenerator.writeName(FieldNames.JSON_FIELD_TRUE_BEARING);
        serializationContext.writeValue(jsonGenerator, geoDistance.getBearing());
        jsonGenerator.writeName(FieldNames.JSON_FIELD_DISTANCE);
        serializationContext.writeValue(jsonGenerator, geoDistance.getDistance());
        jsonGenerator.writeEndObject();
    }
}