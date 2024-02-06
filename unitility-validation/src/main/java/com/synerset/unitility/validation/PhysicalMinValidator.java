package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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

        Class<? extends PhysicalQuantity> targetClass = validatedField.getClass();

        return minInclusive
                ? validatedField.equalsOrGreaterThan(parsingFactory.parse(targetClass, minQuantityAsString))
                : validatedField.isGreaterThan(parsingFactory.parse(targetClass, minQuantityAsString));
    }

}