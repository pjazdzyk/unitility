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

class PhysicalRangeValidatorTest {

    private final Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    private record ExclusiveSample(
            @PhysicalRange(min = "-1.1E-5m/s", minIncl = false, max = "36.0km/h", maxIncl = false) Velocity velocity
    ) {}

    private record InclusiveSample(
            @PhysicalRange(min = "-1.1E-5m/s", max = "36.0km/h") Velocity velocity
    ) {}

    @Test
    @DisplayName("should cause result in violations when range is not inclusive and values are equals to limiting values")
    void shouldCauseViolationsWhenRangeNotInclusiveAndValuesAreEqualsToRangeLimits() {
        // Given
        ExclusiveSample lowerBound = new ExclusiveSample(Velocity.ofMetersPerSecond(-1.1E-5));
        ExclusiveSample insideRange = new ExclusiveSample(Velocity.ofKilometersPerHour(20));
        ExclusiveSample upperBound = new ExclusiveSample(Velocity.ofKilometersPerHour(36));

        // When
        Set<ConstraintViolation<ExclusiveSample>> lowerBoundViolations = validator.validate(lowerBound);
        Set<ConstraintViolation<ExclusiveSample>> internalBoundViolations = validator.validate(insideRange);
        Set<ConstraintViolation<ExclusiveSample>> upperBoundViolations = validator.validate(upperBound);

        // Then
        assertThat(lowerBoundViolations).hasSize(1);
        assertThat(internalBoundViolations).isEmpty();
        assertThat(upperBoundViolations).hasSize(1);
    }

    @Test
    @DisplayName("should not cause result in violations when range is inclusive and values are equals to limiting values")
    void shouldNotCauseViolationsWhenRangeIsInclusiveAndValuesAreEqualsToRangeLimits() {
        // Given
        InclusiveSample lowerBound = new InclusiveSample(Velocity.ofMetersPerSecond(-1.1E-5));
        InclusiveSample insideRange = new InclusiveSample(Velocity.ofKilometersPerHour(20));
        InclusiveSample upperBound = new InclusiveSample(Velocity.ofKilometersPerHour(36));

        // When
        Set<ConstraintViolation<InclusiveSample>> lowerBoundViolations = validator.validate(lowerBound);
        Set<ConstraintViolation<InclusiveSample>> internalBoundViolations = validator.validate(insideRange);
        Set<ConstraintViolation<InclusiveSample>> upperBoundViolations = validator.validate(upperBound);

        // Then
        assertThat(lowerBoundViolations).isEmpty();
        assertThat(internalBoundViolations).isEmpty();
        assertThat(upperBoundViolations).isEmpty();
    }

}