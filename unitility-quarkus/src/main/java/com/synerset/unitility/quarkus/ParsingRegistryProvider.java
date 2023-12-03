package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.enterprise.context.ApplicationScoped;

class ParsingRegistryProvider {

    //@Produces
    @ApplicationScoped
    PhysicalQuantityParsingRegistry createParsingFactory() {
        return PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
    }

}