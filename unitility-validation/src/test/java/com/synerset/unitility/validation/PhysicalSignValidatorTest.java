package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.thermodynamic.Power;
import com.synerset.unitility.unitsystem.thermodynamic.Pressure;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The sign constraints. Jakarta's own {@code @Positive} / {@code @Negative} cannot validate a physical
 * quantity (no validator for the type, so it throws at request time), which is what these replace.
 */
class PhysicalSignValidatorTest {

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    private record StrictlyPositive(@PhysicalPositive Pressure pressure) {}

    private record PositiveOrZero(@PhysicalPositive(orZero = true) Pressure pressure) {}

    private record StrictlyNegative(@PhysicalNegative Power duty) {}

    private record NegativeOrZero(@PhysicalNegative(orZero = true) Power duty) {}

    @Test
    @DisplayName("should accept a quantity above zero and reject zero or below")
    void shouldValidateStrictlyPositive() {
        assertThat(validator.validate(new StrictlyPositive(Pressure.ofPascal(101_325)))).isEmpty();
        assertThat(validator.validate(new StrictlyPositive(Pressure.ofPascal(0)))).hasSize(1);
        assertThat(validator.validate(new StrictlyPositive(Pressure.ofPascal(-10)))).hasSize(1);
    }

    @Test
    @DisplayName("should accept zero when orZero is set")
    void shouldAcceptZeroWhenAllowed() {
        assertThat(validator.validate(new PositiveOrZero(Pressure.ofPascal(0)))).isEmpty();
        assertThat(validator.validate(new PositiveOrZero(Pressure.ofPascal(-1)))).hasSize(1);
    }

    @Test
    @DisplayName("should accept a quantity below zero and reject zero or above")
    void shouldValidateStrictlyNegative() {
        assertThat(validator.validate(new StrictlyNegative(Power.ofKiloWatts(-5)))).isEmpty();
        assertThat(validator.validate(new StrictlyNegative(Power.ofKiloWatts(0)))).hasSize(1);
        assertThat(validator.validate(new StrictlyNegative(Power.ofKiloWatts(5)))).hasSize(1);
        assertThat(validator.validate(new NegativeOrZero(Power.ofKiloWatts(0)))).isEmpty();
    }

    @Test
    @DisplayName("should pass on a null field, like the rest of the module")
    void shouldPassOnNull() {
        assertThat(validator.validate(new StrictlyPositive(null))).isEmpty();
        assertThat(validator.validate(new StrictlyNegative(null))).isEmpty();
    }

    @Test
    @DisplayName("should judge the SIGN, not the unit: -10 °C is negative, 263.15 K is not")
    void shouldJudgeTheSignInTheQuantitysOwnUnit() {
        // A sign is unit-independent for an interval-free scale, but temperature has a shifted zero, so the
        // constraint reads the value as stated. Documented rather than surprising: convert before annotating
        // when a scale has an offset.
        record CelsiusSample(@PhysicalNegative Temperature temperature) {}
        record KelvinSample(@PhysicalNegative Temperature temperature) {}
        assertThat(validator.validate(new CelsiusSample(Temperature.ofCelsius(-10)))).isEmpty();
        assertThat(validator.validate(new KelvinSample(Temperature.ofKelvins(263.15)))).hasSize(1);
    }
}
