package com.synerset.unitility.jackson.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

/**
 * The PhysicalQuantityUnitlessSiSerializer class is a Jackson JSON serializer for serializing {@link PhysicalQuantity} instances
 * to their JSON representations. This serializer writes any PhysicalQuantity as plain text, without a unit symbol.
 */
public class PhysicalQuantitySerializerPlainSiValue extends StdSerializer<PhysicalQuantity<Unit>> {

    public PhysicalQuantitySerializerPlainSiValue(JavaType type) {
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
        jsonGenerator.writeNumber(quantity.getBaseValue());
    }

}
