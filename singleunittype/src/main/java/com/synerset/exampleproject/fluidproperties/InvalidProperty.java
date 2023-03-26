package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.PhysicalQuantity;
import io.vavr.collection.Seq;

public record InvalidProperty(PhysicalQuantity<?> physicalQuantity, String msg) {

    public InvalidProperty(String msg) {
        this(null, msg);
    }

}