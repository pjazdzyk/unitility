package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.PhysicalQuantity;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FluidValidators {

    private static final Logger LOGGER = LoggerFactory.getLogger(FluidValidators.class);
    private static final Temperature TEMP_MIN_LIMIT = Temperature.ofKelvins(0);

    public static Validation<InvalidQuantity, Temperature> validateTemperature(Temperature temperature) {
        return Validation.combine(
                        requireNonNull(temperature, temperature.getClass().getSimpleName()),
                        requireNotExceedMinimalLimit(temperature, TEMP_MIN_LIMIT)
                ).ap((t1, t2) -> temperature)
                .mapError(FluidValidators::combineSeqOfInvalids);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidQuantity, PhysicalQuantity<Q>> validateForNonNullAndPositive(PhysicalQuantity<Q> physicalQuantity) {
        return Validation.combine(
                        requireNonNull(physicalQuantity, physicalQuantity.getClass().getSimpleName()),
                        requirePositiveValue(physicalQuantity)
                ).ap((q1, q2) -> physicalQuantity)
                .mapError(FluidValidators::combineSeqOfInvalids);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidQuantity, PhysicalQuantity<Q>> requireNonNull(PhysicalQuantity<Q> physicalQuantity, String msg) {
        return Objects.isNull(physicalQuantity)
                ? Validation.invalid(invalidPropertyForNullArgument(msg))
                : Validation.valid(physicalQuantity);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidQuantity, PhysicalQuantity<Q>> requireNonNull(PhysicalQuantity<Q> physicalQuantity) {
        return requireNonNull(physicalQuantity, "");
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidQuantity, PhysicalQuantity<Q>> requireNotExceedMinimalLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> minLimit) {
        double sourceUnitInBase = physicalQuantity.toBaseUnit().getValue();
        double limitUnitInBase = minLimit.toBaseUnit().getValue();
        return sourceUnitInBase < limitUnitInBase
                ? Validation.invalid(invalidPropertyForExceededMinLimit(physicalQuantity, minLimit))
                : Validation.valid(physicalQuantity);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidQuantity, PhysicalQuantity<Q>> requireNotExceedMaximumLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> maxLimit) {
        double sourceUnitInBase = physicalQuantity.toBaseUnit().getValue();
        double limitUnitInBase = maxLimit.toBaseUnit().getValue();
        return sourceUnitInBase > limitUnitInBase
                ? Validation.invalid(invalidPropertyForExceededMaxLimit(physicalQuantity, maxLimit))
                : Validation.valid(physicalQuantity);
    }

    public static <Q extends PhysicalQuantity<Q>> Validation<InvalidQuantity, PhysicalQuantity<Q>> requirePositiveValue(PhysicalQuantity<Q> physicalQuantity) {
        double value = physicalQuantity.toBaseUnit().getValue();
        return value < 0
                ? Validation.invalid(invalidPropertyForNegativeValue(physicalQuantity))
                : Validation.valid(physicalQuantity);
    }

    private static InvalidQuantity invalidPropertyForNullArgument(String msg) {
        String message = "Null passed as an argument. " + msg;
        LOGGER.warn(message);
        return new InvalidQuantity(message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidQuantity invalidPropertyForExceededMinLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> minLimit) {
        String quantityName = physicalQuantity.getClass().getSimpleName();
        String message = String.format("Minimum limit exceeded: (%s)_minLimit = %s, (%s)_current = %s -> (%s)_current = %s",
                quantityName, minLimit, quantityName, physicalQuantity.toBaseUnit(), quantityName, physicalQuantity);
        LOGGER.warn(message);
        return new InvalidQuantity(message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidQuantity invalidPropertyForExceededMaxLimit(PhysicalQuantity<Q> physicalQuantity, PhysicalQuantity<Q> maxLimit) {
        String quantityName = physicalQuantity.getClass().getSimpleName();
        String message = String.format("Maximum limit exceeded: (%s)_maxLimit = %s, (%s)_current = %s -> (%s)_current = %s",
                quantityName, maxLimit, quantityName, physicalQuantity.toBaseUnit(), quantityName, physicalQuantity);
        LOGGER.warn(message);
        return new InvalidQuantity(message);
    }

    private static <Q extends PhysicalQuantity<Q>> InvalidQuantity invalidPropertyForNegativeValue(PhysicalQuantity<Q> physicalQuantity) {
        String message = "Negative value is not allowed. Current value = " + physicalQuantity;
        LOGGER.warn(message);
        return new InvalidQuantity(message);
    }

    public static InvalidQuantity combineSeqOfInvalids(Seq<InvalidQuantity> invalidProperties) {
        return invalidProperties.toStream()
                .reduce(FluidValidators::combineInvalids);
    }

    public static InvalidQuantity combineInvalids(InvalidQuantity firstInvalid, InvalidQuantity sourceInvalid){
        return new InvalidQuantity(String.join("\n", firstInvalid.msg(), sourceInvalid.msg()));
    }

}
