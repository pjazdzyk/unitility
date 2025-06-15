package com.synerset.unitility.jackson.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.ParsingHelpers;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;

import java.io.IOException;

/**
 * The {@link PhysicalQuantityDeserializerPlainSiValue} class is a Jackson JSON deserializer for deserializing JSON representations
 * of {@link PhysicalQuantity} instances. Deserializer dedicated for handling plain SI single value quantity representation.
 *
 * @param <U> The type of unit associated with the PhysicalQuantity.
 * @param <Q> The type of PhysicalQuantity.
 */
public class PhysicalQuantityDeserializerPlainSiValue<U extends Unit, Q extends PhysicalQuantity<U>> extends JsonDeserializer<Q> {

    private final Class<Q> quantityClass;
    protected final PhysicalQuantityParsingFactory parsingFactory;

    public PhysicalQuantityDeserializerPlainSiValue(Class<Q> quantityClass, PhysicalQuantityParsingFactory parsingFactory) {
        super();
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

        if(node.getNodeType().equals(JsonNodeType.NUMBER)) {
            return parsingFactory.parseValueWithDefaultUnit(quantityClass, node.asDouble());
        }

        if(node.getNodeType().equals(JsonNodeType.STRING)) {
            String inputQuantityAsString = node.asText();
            return parsingFactory.parse(quantityClass, inputQuantityAsString);
        }

        throw new IOException("Deserialization failure. Unsupported node type: " + node.getNodeType());
    }

}
