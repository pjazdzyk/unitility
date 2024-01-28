package com.synerset.unitility.jackson.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.synerset.unitility.unitsystem.common.AngleUnits;
import com.synerset.unitility.unitsystem.geographic.DMSValidator;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.Latitude;

import java.io.IOException;

import static com.synerset.unitility.jackson.serdes.SerdesHelpers.containsNonDigitChars;
import static com.synerset.unitility.jackson.serdes.SerdesHelpers.prepareInput;

public class LatitudeDeserializer extends JsonDeserializer<Latitude> {

    protected final GeoQuantityParsingFactory geoParsingFactory;

    public LatitudeDeserializer(GeoQuantityParsingFactory geoParsingFactory) {
        super();
        this.geoParsingFactory = geoParsingFactory;
    }

    @Override
    public Latitude deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode quantityNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode valueFieldNode = quantityNode.get(FieldNames.JSON_FIELD_VALUE);

        if (valueFieldNode == null) {
            throw new IOException("Deserialization failure. Field not found: " + FieldNames.JSON_FIELD_VALUE);
        }

        String quantityValue = valueFieldNode.asText();
        String preparedQuantityValue = prepareInput(quantityValue);

        if (preparedQuantityValue != null && DMSValidator.isValidDMSFormat(preparedQuantityValue)) {
            return deserializeFromDMSFormat(preparedQuantityValue);
        }

        if (quantityValue != null && containsNonDigitChars(quantityValue)) {
            return deserializeFromEngineeringFormat(quantityValue);
        }

        return deserializeFromSymbolAndValue(quantityNode);

    }

    private Latitude deserializeFromDMSFormat(String preparedValue) {
        return geoParsingFactory.parseFromDMSFormat(Latitude.class, preparedValue);
    }

    private Latitude deserializeFromEngineeringFormat(String value) {
        return geoParsingFactory.parseFromEngFormat(Latitude.class, value);
    }

    private Latitude deserializeFromSymbolAndValue(JsonNode node) {
        double value = node.get(FieldNames.JSON_FIELD_VALUE).asDouble();
        String unitSymbol;
        if (node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL) != null) {
            unitSymbol = node.get(FieldNames.JSON_FIELD_UNIT_SYMBOL).asText();
        } else {
            unitSymbol = AngleUnits.DEGREES.getSymbol();
        }
        return geoParsingFactory.parseFromSymbol(Latitude.class, value, unitSymbol);
    }

}