package com.synerset.unitility.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.jackson.modules.GeoQuantityJacksonModule;
import com.synerset.unitility.jackson.modules.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * The PhysicalQuantityJacksonConfiguration class is a Spring Boot autoconfiguration class that customizes the
 * Jackson {@link ObjectMapper} configuration for handling {@link PhysicalQuantity} instances. This is required to allow object
 * mapper to serialize / deserialize PhysicalQuantities in Request and Response objects.
 */
@AutoConfiguration
class PhysicalQuantityJacksonConfiguration {

    /**
     * Creates a {@link Jackson2ObjectMapperBuilderCustomizer} that registers the {@link PhysicalQuantityJacksonModule}.
     * This bean is responsible for customizing the Jackson {@link ObjectMapper} by adding the
     * {@link PhysicalQuantityJacksonModule} to the module registry.
     *
     * @param parsingFactory The {@link PhysicalQuantityJacksonModule} used to create the {@link PhysicalQuantityJacksonModule}.
     * @return A {@link Jackson2ObjectMapperBuilderCustomizer}.
     */
    @Bean
    Jackson2ObjectMapperBuilderCustomizer createPhysicalQuantityJacksonModule(@Qualifier("defaultParsingFactory") PhysicalQuantityParsingFactory parsingFactory,
                                                                              @Qualifier("geoParsingFactory") GeoQuantityParsingFactory geoParsingFactory) {

        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
                new PhysicalQuantityJacksonModule(parsingFactory),
                new GeoQuantityJacksonModule(geoParsingFactory)
        );
    }

    /**
     * Creates a default {@link PhysicalQuantityParsingFactory} bean.
     * This bean is responsible for creating and providing a default {@link PhysicalQuantityParsingFactory} instance.
     *
     * @return A default {@link PhysicalQuantityParsingFactory}.
     */
    @Bean
    PhysicalQuantityParsingFactory defaultParsingFactory() {
        return PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
    }

    @Bean
    GeoQuantityParsingFactory geoParsingFactory() {
        return PhysicalQuantityParsingFactory.GEO_PARSING_FACTORY;
    }

}