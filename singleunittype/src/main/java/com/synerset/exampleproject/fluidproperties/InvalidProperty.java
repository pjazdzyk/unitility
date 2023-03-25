package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.PhysicalQuantity;
import io.vavr.collection.Seq;

public record InvalidProperty(PhysicalQuantity<?> physicalQuantity, String msg) {

    public InvalidProperty(String msg) {
        this(null, msg);
    }

    public static InvalidProperty merge(Seq<InvalidProperty> invalidProperties) {
        StringBuilder stringBuilder = new StringBuilder();
        invalidProperties.forEach(invalidProp -> stringBuilder.append("\n").append(invalidProp.msg));
        return new InvalidProperty(invalidProperties.last().physicalQuantity, stringBuilder.toString());
    }

}