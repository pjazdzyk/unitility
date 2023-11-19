package com.synerset.jackson.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;

import java.io.IOException;

public class PhysicalQuantityDeserializer<U extends Unit, Q extends PhysicalQuantity<U>> extends JsonDeserializer<Q> {

    private final Class<Q> quantityClass;
    private final PhysicalQuantityParsingRegistry parsingFactory;

    public PhysicalQuantityDeserializer(Class<Q> quantityClass, PhysicalQuantityParsingRegistry parsingFactory) {
        this.quantityClass = quantityClass;
        this.parsingFactory = parsingFactory;
    }

    @Override
    public Q deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double value = node.get(FieldNames.JSON_FIELD_VALUE).asDouble();
        String unitSymbol = null;
        if (node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL) != null) {
            unitSymbol = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL).asText();
        }
        return parsingFactory.createFromSymbol(quantityClass, value, unitSymbol);
    }

}