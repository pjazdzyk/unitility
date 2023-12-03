package com.synerset.unitility.spring;

import com.synerset.unitility.jackson.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
class PhysicalQuantityJacksonConfiguration {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer createPhysicalQuantityJacksonModule(PhysicalQuantityParsingRegistry parsingFactory) {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(new PhysicalQuantityJacksonModule(parsingFactory));
    }

    @Bean
    PhysicalQuantityParsingRegistry createPhysicalQuantityParsingFactory() {
        return PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
    }

}