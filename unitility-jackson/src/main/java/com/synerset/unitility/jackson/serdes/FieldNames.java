package com.synerset.unitility.jackson.serdes;

/**
 * Fields names used in serialized JSON from PhysicalQuantity class.
 */
final class FieldNames {

    // Basic physical quantities
    static final String JSON_FIELD_VALUE = "value";
    static final String JSON_FIELD_UNIT_SYMBOL = "unit";

    // GeoDistance specific
    static final String JSON_FIELD_START_COORD = "startCoordinate";
    static final String JSON_FIELD_TARGET_COORD = "targetCoordinate";
    static final String JSON_FIELD_TRUE_BEARING = "trueBearing";
    static final String JSON_FIELD_DISTANCE = "distance";

    private FieldNames() {
        throw new IllegalStateException("Utility class");
    }

}
