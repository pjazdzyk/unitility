package com.synerset.jackson.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;

import java.io.IOException;

public class PhysicalQuantitySerializer extends StdSerializer<PhysicalQuantity<Unit>> {

    public PhysicalQuantitySerializer(JavaType type) {
        super(type);
    }

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