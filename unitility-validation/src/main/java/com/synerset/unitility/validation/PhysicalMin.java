package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation used to specify the minimum allowed value for a {@link PhysicalQuantity}.
 * <p>
 * To define the minimum limit, provide a physical quantity along with its unit symbol as an argument,
 * for example: {@code @PhysicalMin(value="10.1m/s")}. By default, the specified limit is inclusive. Use the
 * {@code inclusive} flag to set it as exclusive: {@code @PhysicalMin(value="10.1m/s", inclusive = false)}.
 * <p>
 * Both the minimum limit quantity and the validated quantity are converted to their base values
 * before comparison. A hardcoded maximum input length of {@value ValidationHelpers#MAX_INPUT_LENGTH} characters
 * is enforced on the annotation value to prevent excessively long inputs from reaching the parser.
 * <p>
 * If the validated field is {@code null} or the annotation value is empty, the validation passes (returns {@code true}).
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhysicalMinValidator.class)
@Documented
public @interface PhysicalMin {
    String message() default "The input value exceeds the minimum allowed limit: {value}, inclusive = {inclusive}.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";

    boolean inclusive() default true;

}
