package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Every unit of every {@code *Units} enum, found by reflection, converts to its base unit and back to the value it
 * started from. This replaces the per-class spot checks of the inverse lambdas.
 */
class UnitRoundTripTest {

    private static final double[] VALUES = {-1.0E3, -1.0, 0.0, 1.0E-6, 0.5, 1.0, 123.456, 1.0E6};
    private static final double RELATIVE_TOLERANCE = 1e-12;

    @TestFactory
    @DisplayName("every unit round-trips through its base unit")
    Stream<DynamicTest> everyUnitRoundTripsThroughItsBaseUnit() {
        return UnitCatalog.allUnits().entrySet().stream()
                .map(entry -> DynamicTest.dynamicTest(entry.getKey(), () -> roundTrip(entry.getKey(), entry.getValue())));
    }

    private static void roundTrip(String key, Unit unit) {
        for (double value : VALUES) {
            double inBase = unit.toValueInBaseUnit(value);
            if (!Double.isFinite(inBase)) {
                // A logarithmic unit overflows at 10^(1e6/10); there is nothing to invert.
                continue;
            }
            double back = unit.fromValueInBaseUnit(inBase);
            assertThat(Math.abs(back - value))
                    .as("%s: %s -> %s -> %s", key, value, inBase, back)
                    .isLessThanOrEqualTo(RELATIVE_TOLERANCE * Math.max(1.0, Math.abs(value)));
        }
    }

}
