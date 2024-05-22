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

class PhysicalMinValidatorTest {

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    private record ExclusiveSample(
            @PhysicalMin(value = "36.0km/h", inclusive = false) Velocity velocity
    ) {}

    private record InclusiveSample(
            @PhysicalMin(value = "36.0km/h") Velocity velocity
    ) {}

    @Test
    @DisplayName("should cause result in violations when range is not inclusive and input value is equals to min limit")
    void shouldCauseViolationsWhenMinLimitNotInclusiveAndValuesAreEqualsToMinLimits() {
        // Given
        ExclusiveSample insideRange = new ExclusiveSample(Velocity.ofKilometersPerHour(50));
        ExclusiveSample upperBound = new ExclusiveSample(Velocity.ofKilometersPerHour(36));

        // When
        Set<ConstraintViolation<ExclusiveSample>> internalBoundViolations = validator.validate(insideRange);
        Set<ConstraintViolation<ExclusiveSample>> upperBoundViolations = validator.validate(upperBound);

        // Then
        assertThat(internalBoundViolations).isEmpty();
        assertThat(upperBoundViolations).hasSize(1);
    }

    @Test
    @DisplayName("should cause result in violations when range is inclusive and input value is equals to min limit")
    void shouldNotCauseViolationsWhenMinLimitIsInclusiveAndValuesAreEqualsToMinLimits() {
        // Given
        InclusiveSample lowerBound = new InclusiveSample(Velocity.ofMetersPerSecond(-1.1E-5));
        InclusiveSample upperBound = new InclusiveSample(Velocity.ofKilometersPerHour(36));
        InclusiveSample exceededBound = new InclusiveSample(Velocity.ofKilometersPerHour(37));

        // When
        Set<ConstraintViolation<InclusiveSample>> lowerBoundViolations = validator.validate(lowerBound);
        Set<ConstraintViolation<InclusiveSample>> upperBoundViolations = validator.validate(upperBound);
        Set<ConstraintViolation<InclusiveSample>> exceededBoundViolations = validator.validate(exceededBound);

        // Then
        assertThat(lowerBoundViolations).hasSize(1);
        assertThat(upperBoundViolations).isEmpty();
        assertThat(exceededBoundViolations).isEmpty();
    }

}