package com.synerset.unitility.jackson.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

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
     * @param quantity             The {@link PhysicalQuantity} instance to be serialized.
     * @param jsonGenerator        The JSON generator used for writing JSON content.
     * @param serializationContext The serialization context used for accessing serializers for
     *                             object types.
     * @throws JacksonException If an error occurs during serialization.
     */
    @Override
    public void serialize(PhysicalQuantity<Unit> quantity, JsonGenerator jsonGenerator, SerializationContext serializationContext) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeName(FieldNames.JSON_FIELD_VALUE);
        jsonGenerator.writeNumber(quantity.getValue());
        if (quantity.getUnitSymbol() != null && !quantity.getUnitSymbol().isBlank()) {
            jsonGenerator.writeName(FieldNames.JSON_FIELD_UNIT_SYMBOL);
            jsonGenerator.writeString(quantity.getUnitSymbol());
        }
        jsonGenerator.writeEndObject();
    }

}