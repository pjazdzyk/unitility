package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.PhysicalQuantity;

public record InvalidQuantity(String msg, PhysicalQuantity<?> ... inputArguments) {}