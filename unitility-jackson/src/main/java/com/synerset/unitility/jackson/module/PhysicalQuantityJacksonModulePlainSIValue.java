package com.synerset.unitility.jackson.module;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.synerset.unitility.jackson.serialization.GeoDistanceDeserializer;
import com.synerset.unitility.jackson.serialization.GeoDistanceSerializer;
import com.synerset.unitility.jackson.serialization.PhysicalQuantityDeserializerPlainSiValue;
import com.synerset.unitility.jackson.serialization.PhysicalQuantitySerializerPlainSiValue;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;

/**
 * The PhysicalQuantityJacksonModule class is a Jackson module that provides plain SI serializers and deserializers for
 * handling {@link PhysicalQuantity} instances during JSON serialization and deserialization. Module dedicated for handling
 * plain SI single value quantity representation.
 */
public class PhysicalQuantityJacksonModulePlainSIValue extends SimpleModule {

    public PhysicalQuantityJacksonModulePlainSIValue(PhysicalQuantityParsingFactory parsingRegistry) {
        super("PhysicalQuantityJacksonModulePlainSiValue");

        /* SERIALIZERS */
        JavaType type = TypeFactory.defaultInstance().constructParametricType(PhysicalQuantity.class, Unit.class);
        // Handles all PhysicalQuantity implementations
        addSerializer(new PhysicalQuantitySerializerPlainSiValue(type));
        // Special type
        addSerializer(new GeoDistanceSerializer(GeoDistance.class));

        /* DESERIALIZERS */
        parsingRegistry.findAllRegisteredClasses()
                .forEach(quantityClass -> addDeserializer(
                        quantityClass,
                        new PhysicalQuantityDeserializerPlainSiValue<>(quantityClass, parsingRegistry))
                );

        addDeserializer(GeoDistance.class, new GeoDistanceDeserializer(parsingRegistry));
    }

}