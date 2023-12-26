package com.synerset.unitility.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.parsers.PhysicalQuantityParsingFactory;

import java.io.IOException;

/**
 * The {@link PhysicalQuantityDeserializer} class is a Jackson JSON deserializer for deserializing JSON representations
 * of {@link PhysicalQuantity} instances.
 *
 * @param <U> The type of unit associated with the PhysicalQuantity.
 * @param <Q> The type of PhysicalQuantity.
 */
public class PhysicalQuantityDeserializer<U extends Unit, Q extends PhysicalQuantity<U>> extends JsonDeserializer<Q> {

    private final Class<Q> quantityClass;
    private final PhysicalQuantityParsingFactory parsingFactory;

    public PhysicalQuantityDeserializer(Class<Q> quantityClass, PhysicalQuantityParsingFactory parsingFactory) {
        this.quantityClass = quantityClass;
        this.parsingFactory = parsingFactory;
    }

    /**
     * Deserializes a JSON representation to a {@link PhysicalQuantity} instance.
     * This method is called by Jackson's ObjectMapper during the deserialization process. It reads the JSON
     * representation and uses the {@link PhysicalQuantityParsingFactory} to create a {@link PhysicalQuantity} instance.
     *
     * @param jsonParser             The {@link JsonParser} used to read JSON data.
     * @param deserializationContext The {@link DeserializationContext} used during deserialization.
     * @return A {@link PhysicalQuantity} instance.
     * @throws IOException If an I/O error occurs during the deserialization process.
     */
    @Override
    public Q deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        double value = node.get(FieldNames.JSON_FIELD_VALUE).asDouble();
        String unitSymbol = null;
        if (node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL) != null) {
            unitSymbol = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL).asText();
        }
        return parsingFactory.parseFromSymbol(quantityClass, value, unitSymbol);
    }

}