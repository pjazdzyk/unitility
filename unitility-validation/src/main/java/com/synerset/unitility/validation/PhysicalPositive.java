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
 * Requires a {@link PhysicalQuantity} to be above zero: {@code @PhysicalPositive Pressure pressure}.
 * Set {@code orZero = true} to allow zero as well.
 *
 * <p>This exists because Jakarta's own {@code @Positive} cannot validate a physical quantity: Hibernate
 * Validator resolves a validator by the declared type, finds none for {@code PhysicalQuantity}, and throws
 * {@code UnexpectedTypeException} at request time rather than at startup. Annotating a quantity with
 * {@code @Positive} therefore turns every request carrying it into a 500. Use this instead.
 *
 * <p>Unlike {@link PhysicalMin} / {@link PhysicalMax} this needs no unit: a sign is unit-independent, so the
 * check is on the quantity's own value and nothing is parsed. A {@code null} field passes, which is the same
 * convention the rest of the module follows (pair it with {@code @NotNull} when the field is required).
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhysicalPositiveValidator.class)
@Documented
public @interface PhysicalPositive {

    String message() default "The input value must be positive{orZero, choice, 0# (above zero)|1# or zero}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** Allow zero. Defaults to false, so the quantity must be strictly above zero. */
    boolean orZero() default false;
}
