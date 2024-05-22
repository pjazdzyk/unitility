package com.synerset.unitility.validation;

import com.synerset.unitility.unitsystem.common.Velocity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalMaxValidatorTest {

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    private record ExclusiveSample(
            @PhysicalMax(value = "36.0km/h", inclusive = false) Velocity velocity
    ) {
    }

    private record InclusiveSample(
            @PhysicalMax(value = "36.0km/h") Velocity velocity
    ) {
    }

    @Test
    @DisplayName("should cause result in violations when range is not inclusive and input value is equals to max limit")
    void shouldCauseViolationsWhenMaxLimitNotInclusiveAndValuesAreEqualsToMaxLimits() {
        // Given
        ExclusiveSample insideRange = new ExclusiveSample(Velocity.ofKilometersPerHour(20));
        ExclusiveSample upperBound = new ExclusiveSample(Velocity.ofKilometersPerHour(36));

        // When

        Set<ConstraintViolation<ExclusiveSample>> internalBoundViolations = validator.validate(insideRange);
        Set<ConstraintViolation<ExclusiveSample>> upperBoundViolations = validator.validate(upperBound);

        // Then
        assertThat(internalBoundViolations).isEmpty();
        assertThat(upperBoundViolations).hasSize(1);
    }

    @Test
    @DisplayName("should cause result in violations when range is inclusive and input value is equals to max limit")
    void shouldNotCauseViolationsWhenMaxLimitIsInclusiveAndValuesAreEqualsToMaxLimits() {
        // Given
        InclusiveSample lowerBound = new InclusiveSample(Velocity.ofMetersPerSecond(-1.1E-5));
        InclusiveSample upperBound = new InclusiveSample(Velocity.ofKilometersPerHour(36));
        InclusiveSample exceededBound = new InclusiveSample(Velocity.ofKilometersPerHour(37));

        // When
        Set<ConstraintViolation<InclusiveSample>> lowerBoundViolations = validator.validate(lowerBound);
        Set<ConstraintViolation<InclusiveSample>> upperBoundViolations = validator.validate(upperBound);
        Set<ConstraintViolation<InclusiveSample>> exceededBoundViolations = validator.validate(exceededBound);

        // Then
        assertThat(lowerBoundViolations).isEmpty();
        assertThat(upperBoundViolations).isEmpty();
        assertThat(exceededBoundViolations).hasSize(1);
    }

}