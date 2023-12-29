package com.synerset.unitility.spring;

import com.synerset.unitility.spring.serdes.LatitudeWebMvcConverter;
import com.synerset.unitility.spring.serdes.LongitudeWebMvcConverter;
import com.synerset.unitility.spring.serdes.PhysicalQuantityWebMvcConverter;
import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The PhysicalQuantityWebMvcConfiguration class is a Spring Boot autoconfiguration class that configures
 * WebMvcConfigurer to add converters for PhysicalQuantity instances. This mechanism is required for
 * deserializing PhysicalQuantity from PathParams or QueryParams based on parsers registered in
 * {@link PhysicalQuantityParsingFactory}.
 */
@AutoConfiguration
class PhysicalQuantityWebMvcConfiguration implements WebMvcConfigurer {

    private final PhysicalQuantityParsingFactory parsingFactory;
    private final GeoQuantityParsingFactory geoParsingFactory;

    PhysicalQuantityWebMvcConfiguration(@Qualifier("defaultParsingFactory") PhysicalQuantityParsingFactory parsingFactory,
                                        @Qualifier("geoParsingFactory") GeoQuantityParsingFactory geoParsingFactory) {

        this.parsingFactory = parsingFactory;
        this.geoParsingFactory = geoParsingFactory;
    }

    /**
     * Adds converters for PhysicalQuantity instances to the FormatterRegistry.
     * Spring calls this method to add converters for converting strings to PhysicalQuantity instances
     * and vice versa. It uses the {@link PhysicalQuantityWebMvcConverter} for each registered PhysicalQuantity class.
     *
     * @param registry The FormatterRegistry to which converters are added.
     */
    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        parsingFactory.findAllRegisteredClasses()
                .forEach(quantityClass -> registry.addConverter(
                        String.class,
                        quantityClass,
                        new PhysicalQuantityWebMvcConverter<>(quantityClass, parsingFactory))
                );
        registry.addConverter(new LatitudeWebMvcConverter(geoParsingFactory));
        registry.addConverter(new LongitudeWebMvcConverter(geoParsingFactory));
    }

}