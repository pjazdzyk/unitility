package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.PhysicalQuantityParsingFactory;
import com.synerset.unitility.unitsystem.geographic.GeoQuantityParsingFactory;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * The ParsingRegistryProvider class is a Quarkus CDI bean providing a default {@link PhysicalQuantityParsingFactory}
 * instance for dependency injection in Quarkus applications.
 */
class ParsingRegistryProvider {

    @ApplicationScoped
    @DefaultParsingFactory
    PhysicalQuantityParsingFactory createParsingFactory() {
        return PhysicalQuantityParsingFactory.DEFAULT_PARSING_FACTORY;
    }

    @ApplicationScoped
    @GeoParsingFactory
    GeoQuantityParsingFactory createGeoParsingFactory() {
        return PhysicalQuantityParsingFactory.GEO_PARSING_FACTORY;
    }

}