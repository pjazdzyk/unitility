package com.synerset.unitility.jackson.modules;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.synerset.unitility.jackson.serdes.PhysicalQuantityDeserializer;
import com.synerset.unitility.jackson.serdes.PhysicalQuantitySerializer;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import com.synerset.unitility.unitsystem.Unit;

/**
 * The PhysicalQuantityJacksonModule class is a Jackson module that provides serializers and deserializers for
 * handling {@link PhysicalQuantity} instances during JSON serialization and deserialization.
 */
public class PhysicalQuantityJacksonModule extends SimpleModule {

    public PhysicalQuantityJacksonModule(PhysicalQuantityParsingFactory parsingRegistry) {
        super("PhysicalQuantityJacksonModule");

        /* SERIALIZERS */
        JavaType type = TypeFactory.defaultInstance().constructParametricType(PhysicalQuantity.class, Unit.class);
        // Handles all PhysicalQuantity implementations
        addSerializer(new PhysicalQuantitySerializer(type));

        /* DESERIALIZERS */
        parsingRegistry.findAllRegisteredClasses()
                .forEach(quantityClass -> addDeserializer(
                        quantityClass,
                        new PhysicalQuantityDeserializer<>(quantityClass, parsingRegistry))
                );
    }

}