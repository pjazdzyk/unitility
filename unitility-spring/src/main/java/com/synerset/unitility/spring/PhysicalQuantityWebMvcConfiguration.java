package com.synerset.unitility.spring;

import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
class PhysicalQuantityWebMvcConfiguration implements WebMvcConfigurer {

    private final PhysicalQuantityParsingRegistry parsingFactory;

    PhysicalQuantityWebMvcConfiguration(PhysicalQuantityParsingRegistry parsingFactory) {
        this.parsingFactory = parsingFactory;
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        parsingFactory.findAllRegisteredClasses()
                .forEach(quantityClass -> registry.addConverter(
                        String.class,
                        quantityClass,
                        new PhysicalQuantityWebMvcConverter<>(quantityClass, parsingFactory))
                );
    }

}