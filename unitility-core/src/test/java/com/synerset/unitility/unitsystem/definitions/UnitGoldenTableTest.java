package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Every unit against its golden reference ({@link GoldenTable}), plus coverage: no unit may exist without a golden
 * row or a stated, named exclusion.
 */
class UnitGoldenTableTest {

    private static final double RELATIVE_TOLERANCE = 1e-12;
    private static final Pattern FIRST_NUMBER = Pattern.compile("[0-9]+(\\.[0-9]+)?([Ee][-+]?[0-9]+)?");

    private static final Map<String, Unit> UNITS = UnitCatalog.allUnits();

    @TestFactory
    @DisplayName("every unit agrees with its golden reference")
    Stream<DynamicTest> everyUnitAgreesWithItsGoldenReference() {
        return GoldenTable.ROWS.values().stream()
                .map(row -> DynamicTest.dynamicTest(row.key() + " (" + row.source() + ")", () -> check(row)));
    }

    @TestFactory
    @DisplayName("every golden reference agrees with its published cross-check")
    Stream<DynamicTest> everyReferenceAgreesWithItsCrossCheck() {
        return GoldenTable.ROWS.values().stream()
                .filter(row -> row.crossCheck() != null)
                .map(row -> DynamicTest.dynamicTest(row.key() + " vs " + row.crossCheck(), () -> {
                    BigDecimal reference = ReferenceExpression.evaluate(row.expression());
                    BigDecimal published = ReferenceExpression.evaluate(row.crossCheck());
                    double tolerance = publishedRelativeTolerance(row.crossCheck());
                    assertThat(relativeDifference(reference.doubleValue(), published.doubleValue()))
                            .as("reference %s against the published %s", reference, row.crossCheck())
                            .isLessThanOrEqualTo(tolerance);
                }));
    }

    @Test
    @DisplayName("every unit of every *Units enum has a golden row or a named exclusion, and nothing else")
    void everyUnitHasAGoldenRowOrANamedExclusion() {
        Set<String> allKeys = new TreeSet<>(UNITS.keySet());
        Set<String> goldenKeys = new TreeSet<>(GoldenTable.ROWS.keySet());
        Set<String> excluded = GoldenTable.excludedKeys();

        Set<String> uncovered = new TreeSet<>(allKeys);
        uncovered.removeAll(goldenKeys);
        uncovered.removeAll(excluded);

        Set<String> stale = new TreeSet<>(goldenKeys);
        stale.addAll(excluded);
        stale.removeAll(allKeys);

        Set<String> both = new HashSet<>(goldenKeys);
        both.retainAll(excluded);

        assertThat(uncovered).as("units without a golden row: add a row citing its source").isEmpty();
        assertThat(stale).as("golden rows or exclusions naming no existing unit").isEmpty();
        assertThat(both).as("units that are both golden and excluded").isEmpty();
        assertThat(GoldenTable.UNVERIFIED_UNITS).as("the named UNVERIFIED list").containsOnlyKeys(
                "PressureUnits.MILLIMETRE_OF_MERCURY_10",
                "PressureUnits.MILLIMETRE_OF_MERCURY_60",
                "PressureUnits.MILLIMETRE_OF_MERCURY_95",
                "SoundPowerUnits.DECIBEL");
        assertThat(GoldenTable.NOT_A_UNIT).as("the named NOT A UNIT list").containsOnlyKeys("VelocityUnits.MACH");
    }

    @Test
    @DisplayName("the catalog finds all 88 *Units enums")
    void catalogFindsAllUnitEnums() {
        List<String> names = UnitCatalog.allUnitEnums().stream().map(Class::getSimpleName).collect(Collectors.toList());
        assertThat(names).hasSize(88).contains("PressureUnits", "VapourQualityUnits", "NormalVolumetricFlowUnits");
    }

    private static void check(GoldenTable.Row row) {
        Unit unit = UNITS.get(row.key());
        assertThat(unit).as("unit %s exists", row.key()).isNotNull();
        switch (row.kind()) {
            case EXACT:
                checkExact(row, unit);
                break;
            case PUBLISHED:
                checkPublished(row, unit);
                break;
            case AFFINE:
                checkAffine(row, unit);
                break;
            case NONLINEAR:
                checkNonlinear(row, unit);
                break;
            default:
                throw new IllegalStateException("Unknown kind " + row.kind());
        }
    }

    private static void checkExact(GoldenTable.Row row, Unit unit) {
        double reference = Double.parseDouble(ReferenceExpression.evaluate(row.expression()).toString());
        double actual = unit.toValueInBaseUnit(1.0);
        assertThat(Math.abs(actual - reference))
                .as("%s: scale %s against the exact %s (%s)", row.key(), actual, reference, row.expression())
                .isLessThanOrEqualTo(Math.ulp(reference));
        assertLinear(row.key(), unit, actual);
    }

    private static void checkPublished(GoldenTable.Row row, Unit unit) {
        double reference = ReferenceExpression.evaluate(row.expression()).doubleValue();
        double actual = unit.toValueInBaseUnit(1.0);
        double tolerance = publishedRelativeTolerance(row.expression());
        assertThat(relativeDifference(actual, reference))
                .as("%s: scale %s against the published %s (%s)", row.key(), actual, reference, row.expression())
                .isLessThanOrEqualTo(tolerance);
        assertLinear(row.key(), unit, actual);
    }

    private static void checkAffine(GoldenTable.Row row, Unit unit) {
        BigDecimal scale = ReferenceExpression.evaluate(row.expression());
        BigDecimal offset = ReferenceExpression.evaluate(row.offsetExpression());
        for (double value : new double[]{-40.0, 0.0, 32.0, 100.0, 1000.0, -273.0}) {
            double reference = new BigDecimal(value).multiply(scale, ReferenceExpression.MC)
                    .add(offset, ReferenceExpression.MC).doubleValue();
            double actual = unit.toValueInBaseUnit(value);
            assertThat(relativeDifference(actual, reference))
                    .as("%s at %s: %s against %s", row.key(), value, actual, reference)
                    .isLessThanOrEqualTo(RELATIVE_TOLERANCE);
        }
    }

    private static void checkNonlinear(GoldenTable.Row row, Unit unit) {
        for (double value : new double[]{-10.0, 0.0, 20.0, 94.0, 120.0}) {
            double reference = row.formula().applyAsDouble(value);
            double actual = unit.toValueInBaseUnit(value);
            assertThat(relativeDifference(actual, reference))
                    .as("%s at %s: %s against %s", row.key(), value, actual, reference)
                    .isLessThanOrEqualTo(RELATIVE_TOLERANCE);
        }
    }

    /** A unit given a scale must BE linear: f(x) = x * scale, f(0) = 0. Catches an affine unit filed as linear. */
    private static void assertLinear(String key, Unit unit, double scale) {
        assertThat(unit.toValueInBaseUnit(0.0)).as("%s maps 0 to 0", key).isEqualTo(0.0);
        for (double value : new double[]{-3.7, 2.0, 1234.5}) {
            assertThat(relativeDifference(unit.toValueInBaseUnit(value), value * scale))
                    .as("%s is linear at %s", key, value)
                    .isLessThanOrEqualTo(RELATIVE_TOLERANCE);
        }
    }

    /** Half a unit in the last published digit of the expression's first number, relative to that number. */
    private static double publishedRelativeTolerance(String expression) {
        Matcher matcher = FIRST_NUMBER.matcher(expression);
        if (!matcher.find()) {
            throw new IllegalArgumentException("No published number in " + expression);
        }
        BigDecimal published = new BigDecimal(matcher.group());
        return published.ulp().doubleValue() / 2.0 / published.abs().doubleValue();
    }

    private static double relativeDifference(double actual, double reference) {
        if (reference == 0.0) {
            return Math.abs(actual);
        }
        return Math.abs(actual - reference) / Math.abs(reference);
    }

}
