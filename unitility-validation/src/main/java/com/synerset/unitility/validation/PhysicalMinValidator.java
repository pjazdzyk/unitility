package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link PhysicalMin} constraint annotation.
 * <p>
 * Parses the annotation's string value into a {@link PhysicalQuantity} using {@link PhysicalQuantityParsingFactory}
 * and compares it against the validated field's base value. Input length is limited to
 * {@value ValidationHelpers#MAX_INPUT_LENGTH} characters as a safety guard before parsing.
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PhysicalMinValidator implements ConstraintValidator<PhysicalMin, PhysicalQuantity<? extends Unit>> {

    private String minQuantityAsString;
    private boolean minInclusive;

    private final PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @Override
    public void initialize(PhysicalMin annotation) {
        this.minQuantityAsString = annotation.value();
        this.minInclusive = annotation.inclusive();
    }

    @Override
    public boolean isValid(PhysicalQuantity<? extends Unit> validatedField, ConstraintValidatorContext context) {
        if (validatedField == null || ValidationHelpers.isEmpty(minQuantityAsString)) {
            return true;
        }

        ValidationHelpers.validateInputLength(minQuantityAsString);
        Class<? extends PhysicalQuantity> targetClass = validatedField.getClass();

        return minInclusive
                ? validatedField.isEqualOrGreaterThan(parsingFactory.parse(targetClass, minQuantityAsString))
                : validatedField.isGreaterThan(parsingFactory.parse(targetClass, minQuantityAsString));
    }

}