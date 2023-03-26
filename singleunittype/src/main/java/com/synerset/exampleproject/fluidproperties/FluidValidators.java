package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class FluidValidators {

    private static final Logger LOGGER = LogManager.getLogger(FluidValidators.class.getSimpleName());
    private static final Temperature TEMP_MIN_LIMIT = Temperature.ofKelvins(0);

    public static Validation<InvalidProperty, Temperature> validateTemperature(Temperature temperature) {
        return Validation.combine(
                        requireNonNull(temperature, temperature.getClass().getSimpleName()),
                        requireNotExceedMinimalLimit(temperature, TEMP_MIN_LIMIT)
                ).ap((t1, t2) -> temperature)
                .mapError(FluidValidators::combineInvalids);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidProperty, PhysicalQuantity<Q>> validateForNonNullAndPositive(PhysicalQuantity<Q> physicalQuantity) {
        return Validation.combine(
                        requireNonNull(physicalQuantity, physicalQuantity.getClass().getSimpleName()),
                        requirePositiveValue(physicalQuantity)
                ).ap((q1, q2) -> physicalQuantity)
                .mapError(FluidValidators::combineInvalids);
    }

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

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidProperty, PhysicalQuantity<Q>> requirePositiveValue(PhysicalQuantity<Q> physicalQuantity) {
        double value = physicalQuantity.toBaseUnit().getValue();
        return value < 0
                ? Validation.invalid(invalidPropertyForNegativeValue(physicalQuantity))
                : Validation.valid(physicalQuantity);
    }

    private static InvalidProperty invalidPropertyForNullArgument(String variableName) {
        String message = "Null passed as an argument for: " + variableName;
        LOGGER.warn(message);
        return new InvalidProperty(message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidProperty invalidPropertyForExceededMinLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> minLimit) {
        String quantityName = physicalQuantity.getClass().getSimpleName();
        String message = String.format("Minimum limit exceeded: (%s)_minLimit = %s, (%s)_current = %s -> (%s)_current = %s",
                quantityName, minLimit, quantityName, physicalQuantity.toBaseUnit(), quantityName, physicalQuantity);
        LOGGER.warn(message);
        return new InvalidProperty(physicalQuantity, message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidProperty invalidPropertyForExceededMaxLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> maxLimit) {
        String quantityName = physicalQuantity.getClass().getSimpleName();
        String message = String.format("Maximum limit exceeded: (%s)_maxLimit = %s, (%s)_current = %s -> (%s)_current = %s",
                quantityName, maxLimit, quantityName, physicalQuantity.toBaseUnit(), quantityName, physicalQuantity);
        LOGGER.warn(message);
        return new InvalidProperty(physicalQuantity, message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidProperty invalidPropertyForNegativeValue(PhysicalQuantity<Q> physicalQuantity) {
        String message = "Negative value is not allowed. Current value = " + physicalQuantity;
        LOGGER.warn(message);
        return new InvalidProperty(physicalQuantity, message);
    }

    public static InvalidProperty combineInvalids(Seq<InvalidProperty> invalidProperties) {
        StringBuilder stringBuilder = new StringBuilder();
        invalidProperties.forEach(invalidProp -> stringBuilder.append("\n").append(invalidProp.msg()));
        String combinedMessage = stringBuilder.toString();
        PhysicalQuantity<?> lastPhysicalQuantity = invalidProperties.last().physicalQuantity();
        return new InvalidProperty(lastPhysicalQuantity, combinedMessage);
    }

}
