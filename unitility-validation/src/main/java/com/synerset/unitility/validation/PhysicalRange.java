package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation used to specify a required physical range for a {@link PhysicalQuantity}.
 * <p>
 * To define the validation range, provide the minimum and maximum allowed values as arguments,
 * along with their respective unit symbols. For example: {@code @PhysicalRange(min="1.5E-5m/s", max="10.0km/h")}.
 * By default, both min and max limits are inclusive. Use the {@code minIncl} and/or {@code maxIncl} flags
 * to set them as exclusive:
 * <br>{@code @PhysicalRange(min="-1.1E-5m/s", minIncl=false, max="36.0km/h", maxIncl=false)}.
 * <p>
 * Both limit quantities and the validated quantity are converted to their base values before comparison.
 * A hardcoded maximum input length of {@value ValidationHelpers#MAX_INPUT_LENGTH} characters is enforced
 * on both min and max values to prevent excessively long inputs from reaching the parser.
 * <p>
 * If the validated field is {@code null} or both min and max are empty, the validation passes (returns {@code true}).
 * If only one bound is specified, only that bound is validated.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhysicalRangeValidator.class)
@Documented
public @interface PhysicalRange {
    String message() default "The input value exceeds the allowed value range of: " +
            "<min = {min}, inclusive = {minIncl} | max = {max}, inclusive = {maxIncl}>.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String min() default "";

    boolean minIncl() default true;

    String max() default "";

    boolean maxIncl() default true;

}
