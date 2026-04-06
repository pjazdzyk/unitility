package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.synerset.unitility.validation.ValidationHelpers.isEmpty;

/**
 * Validator for the {@link PhysicalMax} constraint annotation.
 * <p>
 * Parses the annotation's string value into a {@link PhysicalQuantity} using {@link PhysicalQuantityParsingFactory}
 * and compares it against the validated field's base value. Input length is limited to
 * {@value ValidationHelpers#MAX_INPUT_LENGTH} characters as a safety guard before parsing.
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PhysicalMaxValidator implements ConstraintValidator<PhysicalMax, PhysicalQuantity<? extends Unit>> {

    private String maxQuantityAsString;
    private boolean maxInclusive;
    private final PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @Override
    public void initialize(PhysicalMax annotation) {
        this.maxQuantityAsString = annotation.value();
        this.maxInclusive = annotation.inclusive();
    }

    @Override
    public boolean isValid(PhysicalQuantity<? extends Unit> validatedField, ConstraintValidatorContext context) {
        if (validatedField == null || isEmpty(maxQuantityAsString)) {
            return true;
        }

        ValidationHelpers.validateInputLength(maxQuantityAsString);
        Class<? extends PhysicalQuantity> targetClass = validatedField.getClass();

        return maxInclusive
                ? validatedField.isEqualOrLowerThan(parsingFactory.parse(targetClass, maxQuantityAsString))
                : validatedField.isLowerThan(parsingFactory.parse(targetClass, maxQuantityAsString));
    }

}