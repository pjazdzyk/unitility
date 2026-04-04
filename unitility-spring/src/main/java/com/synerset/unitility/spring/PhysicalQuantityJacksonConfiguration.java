package com.synerset.unitility.spring;

import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * The PhysicalQuantityJacksonConfiguration class is a Spring Boot autoconfiguration class that customizes the
 * Jackson {@link ObjectMapper} configuration for handling {@link PhysicalQuantity} instances. This is required to allow object
 * mapper to serialize / deserialize PhysicalQuantities in Request and Response objects.
 */
@AutoConfiguration
class PhysicalQuantityJacksonConfiguration {

    /**
     * Creates a {@link JsonMapperBuilderCustomizer} that registers the {@link PhysicalQuantityJacksonModule}.
     * This bean is responsible for customizing the Jackson mapper builder by adding the
     * {@link PhysicalQuantityJacksonModule} to the module registry.
     *
     * @param parsingFactory The {@link PhysicalQuantityParsingFactory} used to create the {@link PhysicalQuantityJacksonModule}.
     * @return A {@link JsonMapperBuilderCustomizer}.
     */
    @Bean
    JsonMapperBuilderCustomizer createPhysicalQuantityJacksonModule(@Qualifier("defaultParsingFactory") PhysicalQuantityParsingFactory parsingFactory) {

        return builder -> builder.addModule(
                new PhysicalQuantityJacksonModule(parsingFactory)
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
        return PhysicalQuantityParsingFactory.getDefaultParsingFactory();
    }

    @Bean
    PhysicalQuantityParsingFactory geoParsingFactory() {
        return PhysicalQuantityParsingFactory.getDefaultParsingFactory();
    }

}