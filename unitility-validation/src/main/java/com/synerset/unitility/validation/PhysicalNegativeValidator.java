package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for the {@link PhysicalNegative} constraint annotation.
 *
 * <p>Compares the quantity's own value against zero, so nothing is parsed and no unit is involved. A
 * {@code null} field passes.
 */
public class PhysicalNegativeValidator implements ConstraintValidator<PhysicalNegative, PhysicalQuantity<? extends Unit>> {

    private boolean orZero;

    @Override
    public void initialize(PhysicalNegative annotation) {
        this.orZero = annotation.orZero();
    }

    @Override
    public boolean isValid(PhysicalQuantity<? extends Unit> validatedField, ConstraintValidatorContext context) {
        if (validatedField == null) {
            return true;
        }
        return orZero ? validatedField.isNegativeOrZero() : validatedField.isNegative();
    }

}
