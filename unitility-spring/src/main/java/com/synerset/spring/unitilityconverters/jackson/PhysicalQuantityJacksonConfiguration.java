package com.synerset.spring.unitilityconverters.jackson;

import com.synerset.jackson.jackson.PhysicalQuantityJacksonModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
class PhysicalQuantityJacksonConfiguration {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer createPhysicalQuantityJacksonModule() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(new PhysicalQuantityJacksonModule());
    }

}