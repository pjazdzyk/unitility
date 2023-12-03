package com.synerset.unitility.jackson;

/**
 * Fields names used in serialized JSON from PhysicalQuantity class.
 */
public final class FieldNames {

    static final String JSON_FIELD_VALUE = "value";
    static final String JSON_FIELD_UNIT_SYMBOL = "unit";

    private FieldNames() {
        throw new IllegalStateException("Utility class");
    }

}
