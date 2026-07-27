package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Requires a {@link PhysicalQuantity} to be below zero: {@code @PhysicalNegative Power heatLoss}.
 * Set {@code orZero = true} to allow zero as well.
 *
 * <p>The counterpart of {@link PhysicalPositive}, and it exists for the same reason: Jakarta's
 * {@code @Negative} has no validator for a physical quantity and fails at request time rather than at startup.
 *
 * <p>Needs no unit (a sign is unit-independent) and a {@code null} field passes, matching the rest of the
 * module. Useful where the SIGN carries meaning: an extracted duty, a demand that injects rather than draws.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhysicalNegativeValidator.class)
@Documented
public @interface PhysicalNegative {

    String message() default "The input value must be negative{orZero, choice, 0# (below zero)|1# or zero}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** Allow zero. Defaults to false, so the quantity must be strictly below zero. */
    boolean orZero() default false;
}
