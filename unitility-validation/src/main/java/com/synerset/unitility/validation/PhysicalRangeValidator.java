package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PhysicalRangeValidator implements ConstraintValidator<PhysicalRange, PhysicalQuantity<? extends Unit>> {

    private String minQuantityAsString;
    private boolean minInclusive;
    private String maxQuantityAsString;
    private boolean maxInclusive;
    private final PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @Override
    public void initialize(PhysicalRange annotation) {
        this.minQuantityAsString = annotation.min();
        this.minInclusive = annotation.minIncl();
        this.maxQuantityAsString = annotation.max();
        this.maxInclusive = annotation.maxIncl();
    }

    @Override
    public boolean isValid(PhysicalQuantity<? extends Unit> validatedField, ConstraintValidatorContext context) {
        if (validatedField == null || ValidationHelpers.isEmpty(minQuantityAsString) && ValidationHelpers.isEmpty(maxQuantityAsString)) {
            return true;
        }

        Class<? extends PhysicalQuantity> targetClass = validatedField.getClass();

        if (ValidationHelpers.isNotEmpty(minQuantityAsString) && ValidationHelpers.isEmpty(maxQuantityAsString)) {
            return validateForMinThreshold(validatedField, targetClass);
        }

        if (ValidationHelpers.isEmpty(minQuantityAsString) && ValidationHelpers.isNotEmpty(maxQuantityAsString)) {
            return validateForMaxThreshold(validatedField, targetClass);
        }

        return validateForMinThreshold(validatedField, targetClass)
                && validateForMaxThreshold(validatedField, targetClass);
    }

    private boolean validateForMinThreshold(PhysicalQuantity<? extends Unit> validatedField,
                                            Class<? extends PhysicalQuantity> targetClass) {

        return minInclusive
                ? validatedField.isEqualOrGreaterThan(parsingFactory.parse(targetClass, minQuantityAsString))
                : validatedField.isGreaterThan(parsingFactory.parse(targetClass, minQuantityAsString));
    }

    private boolean validateForMaxThreshold(PhysicalQuantity<? extends Unit> validatedField,
                                            Class<? extends PhysicalQuantity> targetClass) {

        return maxInclusive
                ? validatedField.isEqualOrLowerThan(parsingFactory.parse(targetClass, maxQuantityAsString))
                : validatedField.isLowerThan(parsingFactory.parse(targetClass, maxQuantityAsString));
    }

}