package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.parsers.PhysicalQuantityParsingFactory;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * The ParsingRegistryProvider class is a Quarkus CDI bean providing a default {@link PhysicalQuantityParsingFactory}
 * instance for dependency injection in Quarkus applications.
 */
class ParsingRegistryProvider {

    @ApplicationScoped
    PhysicalQuantityParsingFactory createParsingFactory() {
        return PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
    }

}