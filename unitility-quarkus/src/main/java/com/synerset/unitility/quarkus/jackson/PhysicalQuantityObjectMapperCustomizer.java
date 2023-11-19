package com.synerset.unitility.quarkus.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.jackson.jackson.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhysicalQuantityObjectMapperCustomizer implements ObjectMapperCustomizer {

    private final PhysicalQuantityParsingRegistry parsingRegistry;

    public PhysicalQuantityObjectMapperCustomizer(PhysicalQuantityParsingRegistry parsingRegistry) {

        this.parsingRegistry = parsingRegistry;
    }

    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new PhysicalQuantityJacksonModule(parsingRegistry));
    }

}