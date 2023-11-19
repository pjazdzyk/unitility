package com.synerset.unitility.quarkus.jackson;

import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ParsingRegistryProvider {

    @Produces
    public PhysicalQuantityParsingRegistry createParsingFactory() {
        return PhysicalQuantityParsingRegistry.createNewDefaultRegistry();
    }

}
