package com.synerset.unitility.jackson.legacy.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.io.IOException;

/**
 * The PhysicalQuantityUnitlessSiSerializer class is a Jackson 2.x JSON serializer for serializing
 * {@link PhysicalQuantity} instances to their JSON representations. This serializer writes any
 * PhysicalQuantity as plain text, without a unit symbol.
 */
public class PhysicalQuantitySerializerPlainSiValue extends StdSerializer<PhysicalQuantity<Unit>> {

    public PhysicalQuantitySerializerPlainSiValue(JavaType type) {
        super(type);
    }

    @Override
    public void serialize(PhysicalQuantity<Unit> quantity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(quantity.getBaseValue());
    }

}
