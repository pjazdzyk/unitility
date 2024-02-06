package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation used to specify the maximum allowed value for a {@link PhysicalQuantity}.
 * To define the maximum limit, provide a physical quantity along with its unit symbol as an argument,
 * for example: {@code @PhysicalMax(value="10.1m/s")}. By default, specified limit is inclusive. Use "inclusive" flag,
 * to set it as exclusive: {@code @PhysicalMax(value="10.1m/s", inclusive = false)}.
 * Both the maximum limit quantity and the validated quantity will be converted to their base values
 * before comparison.
 * This annotation validates that the annotated element's value does not exceed the specified maximum.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhysicalMaxValidator.class)
@Documented
public @interface PhysicalMax {
    String message() default "The input value exceeds the maximum allowed limit: {value}, inclusive = {inclusive}.";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";

    boolean inclusive() default true;

}
