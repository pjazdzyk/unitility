package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * The ParsingRegistryProvider class is a Quarkus CDI bean providing a default {@link PhysicalQuantityParsingRegistry}
 * instance for dependency injection in Quarkus applications.
 */
class ParsingRegistryProvider {

    @ApplicationScoped
    PhysicalQuantityParsingRegistry createParsingFactory() {
        return PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
    }

}