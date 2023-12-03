package com.synerset.unitility.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;

/**
 * The PhysicalQuantityJacksonModule class is a Jackson module that provides serializers and deserializers for
 * handling {@link PhysicalQuantity} instances during JSON serialization and deserialization.
 */
public class PhysicalQuantityJacksonModule extends SimpleModule {

    public PhysicalQuantityJacksonModule(PhysicalQuantityParsingRegistry parsingRegistry) {
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