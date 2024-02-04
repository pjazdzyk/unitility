package com.synerset.unitility.quarkus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * The PhysicalQuantityObjectMapperCustomizer class is a Quarkus {@link ObjectMapperCustomizer} that customizes the
 * {@link ObjectMapper} configuration by registering the {@link PhysicalQuantityJacksonModule}. This is required to
 * allow object mapper to serialize / deserialize PhysicalQuantities in Request and Response objects.
 */
@ApplicationScoped
class PhysicalQuantityObjectMapperCustomizer implements ObjectMapperCustomizer {

    private final PhysicalQuantityParsingFactory parsingFactory;

    public PhysicalQuantityObjectMapperCustomizer(@DefaultParsingFactory PhysicalQuantityParsingFactory parsingFactory) {

        this.parsingFactory = parsingFactory;
    }

    /**
     * Customizes the provided {@link ObjectMapper} by registering the {@link PhysicalQuantityJacksonModule}.
     * Quarkus calls this method during the customization of the {@link ObjectMapper} configuration.
     * It registers the {@link PhysicalQuantityJacksonModule}, enabling the serialization and deserialization of
     * {@link PhysicalQuantity} instances based on parsers registered in {@link PhysicalQuantityParsingFactory}.
     *
     * @param objectMapper The injected {@link ObjectMapper} to be customized.
     */
    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingFactory));
    }

}