package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.PhysicalQuantity;
import io.vavr.control.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class FluidValidators {

    private static final Logger LOGGER = LogManager.getLogger(FluidValidators.class.getSimpleName());

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidProperty, PhysicalQuantity<Q>> requireNonNull(PhysicalQuantity<Q> physicalQuantity, String variableName) {
        return Objects.isNull(physicalQuantity)
                ? Validation.invalid(invalidPropertyForNullArgument(variableName))
                : Validation.valid(physicalQuantity);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidProperty, PhysicalQuantity<Q>> requireNotExceedMinimalLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> minLimit) {
        double sourceUnitInBase = physicalQuantity.toBaseUnit().getValue();
        double limitUnitInBase = minLimit.toBaseUnit().getValue();
        return sourceUnitInBase < limitUnitInBase
                ? Validation.invalid(invalidPropertyForExceededMinLimit(physicalQuantity, minLimit))
                : Validation.valid(physicalQuantity);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidProperty, PhysicalQuantity<Q>> requireNotExceedMaximumLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> maxLimit) {
        double sourceUnitInBase = physicalQuantity.toBaseUnit().getValue();
        double limitUnitInBase = maxLimit.toBaseUnit().getValue();
        return sourceUnitInBase > limitUnitInBase
                ? Validation.invalid(invalidPropertyForExceededMaxLimit(physicalQuantity, maxLimit))
                : Validation.valid(physicalQuantity);
    }

    private static InvalidProperty invalidPropertyForNullArgument(String variableName) {
        String message = "Null passed as an argument for: " + variableName;
        LOGGER.warn(message);
        return new InvalidProperty(message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidProperty invalidPropertyForExceededMinLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> minLimit) {
        String message = "Minimal limit exceeded: minLimit = " + minLimit + ", current: " + physicalQuantity;
        LOGGER.warn(message);
        return new InvalidProperty(physicalQuantity, message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidProperty invalidPropertyForExceededMaxLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> maxLimit) {
        String message = "Maximum limit exceeded: maxLimit = " + maxLimit + ", current: " + physicalQuantity;
        LOGGER.warn(message);
        return new InvalidProperty(physicalQuantity, message);
    }


}
