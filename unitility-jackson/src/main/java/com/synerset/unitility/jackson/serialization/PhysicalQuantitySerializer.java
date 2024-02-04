package com.synerset.unitility.jackson.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.io.IOException;

/**
 * The PhysicalQuantitySerializer class is a Jackson JSON serializer for serializing {@link PhysicalQuantity} instances
 * to their JSON representations.
 */
public class PhysicalQuantitySerializer extends StdSerializer<PhysicalQuantity<Unit>> {

    public PhysicalQuantitySerializer(JavaType type) {
        super(type);
    }

    /**
     * Serializes a {@link  PhysicalQuantity<Unit>} instance to its JSON representation.
     *
     * @param quantity           The {@link PhysicalQuantity} instance to be serialized.
     * @param jsonGenerator      The JSON generator used for writing JSON content.
     * @param serializerProvider The serializer provider that can be used for accessing serializers for
     *                           object types.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @Override
    public void serialize(PhysicalQuantity<Unit> quantity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(FieldNames.JSON_FIELD_VALUE, quantity.getValue());
        if (quantity.getUnitSymbol() != null && !quantity.getUnitSymbol().isBlank()) {
            jsonGenerator.writeStringField(FieldNames.JSON_FIELD_UNIT_SYMBOL, quantity.getUnitSymbol());
        }
        jsonGenerator.writeEndObject();
    }

}