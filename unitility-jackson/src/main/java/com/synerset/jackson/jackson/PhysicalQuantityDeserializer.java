package com.synerset.jackson.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingFactory;

import java.io.IOException;

public class PhysicalQuantityDeserializer<U extends Unit, Q extends PhysicalQuantity<U>> extends JsonDeserializer<Q> {

    private final Class<Q> quantityClass;

    PhysicalQuantityDeserializer(Class<Q> quantityClass) {
        this.quantityClass = quantityClass;
    }

    @Override
    public Q deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double value = node.get(FieldNames.JSON_FIELD_VALUE).asDouble();
        String unitSymbol = null;
        if (node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL) != null) {
            unitSymbol = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL).asText();
        }
        return PhysicalQuantityParsingFactory.createParsingFromSymbol(quantityClass, value, unitSymbol);
    }

}