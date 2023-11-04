package com.synerset.quarkus.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.jackson.jackson.PhysicalQuantityJacksonModule;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhysicalQuantityObjectMapperCustomizer implements ObjectMapperCustomizer {
    @Override
    public void customize(ObjectMapper objectMapper) {
        objectMapper.registerModule(new PhysicalQuantityJacksonModule());
    }
}
