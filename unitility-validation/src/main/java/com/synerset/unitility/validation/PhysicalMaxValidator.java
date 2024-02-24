package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.synerset.unitility.validation.ValidationHelpers.isEmpty;

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

        Class<? extends PhysicalQuantity> targetClass = validatedField.getClass();

        return maxInclusive
                ? validatedField.isEqualOrLowerThan(parsingFactory.parse(targetClass, maxQuantityAsString))
                : validatedField.isLowerThan(parsingFactory.parse(targetClass, maxQuantityAsString));
    }

}