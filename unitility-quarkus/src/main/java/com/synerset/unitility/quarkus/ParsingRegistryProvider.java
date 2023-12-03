package com.synerset.unitility.quarkus;

import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
class ParsingRegistryProvider {

    @Produces
    PhysicalQuantityParsingRegistry createParsingFactory() {
        return PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
    }

}