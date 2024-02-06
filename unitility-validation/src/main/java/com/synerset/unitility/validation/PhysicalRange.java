package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation used to specify a required physical range for a {@link PhysicalQuantity}.
 * To define validation range, provide the minimum and maximum allowed values as arguments,
 * along with their respective unit symbols. For example: {@code @PhysicalRange(min="1.5E-5m/s", max="10.0km/h")}.
 * By default, specified min and max limits are inclusive. Use "minIncl" or/and "maxIncl" flags,
 * to set it as exclusive: <br> {@code @PhysicalRange(min="-1.1E-5m/s", minIncl=false, max="36.0km/h", maxIncl=false)}.<br>
 * This annotation validates that the annotated element's value falls within the specified physical range.
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
