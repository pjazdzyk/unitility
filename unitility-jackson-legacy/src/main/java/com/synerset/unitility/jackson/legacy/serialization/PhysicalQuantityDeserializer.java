package com.synerset.unitility.jackson.legacy.serialization;

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
 * The {@link PhysicalQuantityDeserializer} class is a Jackson 2.x JSON deserializer for deserializing JSON
 * representations of {@link PhysicalQuantity} instances.
 *
 * @param <U> The type of unit associated with the PhysicalQuantity.
 * @param <Q> The type of PhysicalQuantity.
 */
public class PhysicalQuantityDeserializer<U extends Unit, Q extends PhysicalQuantity<U>> extends JsonDeserializer<Q> {

    private final Class<Q> quantityClass;
    protected final PhysicalQuantityParsingFactory parsingFactory;

    public PhysicalQuantityDeserializer(Class<Q> quantityClass, PhysicalQuantityParsingFactory parsingFactory) {
        super();
        this.quantityClass = quantityClass;
        this.parsingFactory = parsingFactory;
    }

    @Override
    public Q deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        if(node.getNodeType().equals(JsonNodeType.NUMBER)) {
            return parsingFactory.parseValueWithDefaultUnit(quantityClass, node.asDouble());
        }

        JsonNode valueFieldNode = node.get(FieldNames.JSON_FIELD_VALUE);
        JsonNode symbolFieldNode = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL);

        if (valueFieldNode == null) {
            throw new IOException("Deserialization failure. Field not found: " + FieldNames.JSON_FIELD_VALUE);
        }

        String inputQuantityAsString = valueFieldNode.asText();

        if (symbolFieldNode == null || ParsingHelpers.containsNonDigitChars(inputQuantityAsString)) {
            return parsingFactory.parse(quantityClass, inputQuantityAsString);
        }

        double value = node.get(FieldNames.JSON_FIELD_VALUE).asDouble();
        String unitSymbol = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL).asText();

        return parsingFactory.parseValueAndSymbol(quantityClass, value, unitSymbol);

    }

}
