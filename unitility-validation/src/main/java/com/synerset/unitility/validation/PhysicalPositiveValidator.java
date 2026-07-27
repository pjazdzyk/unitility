package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link PhysicalPositive} constraint annotation.
 *
 * <p>Compares the quantity's own value against zero, so nothing is parsed and no unit is involved: a sign is
 * the same in every unit of a given quantity. A {@code null} field passes.
 */
public class PhysicalPositiveValidator implements ConstraintValidator<PhysicalPositive, PhysicalQuantity<? extends Unit>> {

    private boolean orZero;

    @Override
    public void initialize(PhysicalPositive annotation) {
        this.orZero = annotation.orZero();
    }

    @Override
    public boolean isValid(PhysicalQuantity<? extends Unit> validatedField, ConstraintValidatorContext context) {
        if (validatedField == null) {
            return true;
        }
        return orZero ? validatedField.isPositiveOrZero() : validatedField.isPositive();
    }

}
