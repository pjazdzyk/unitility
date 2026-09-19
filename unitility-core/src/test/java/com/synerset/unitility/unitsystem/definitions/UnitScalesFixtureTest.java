package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The unit-scales fixture: every unit's scale to its base unit (and offset, for an affine unit), as JSON, for the
 * UI's parity test (plan U4). The UI's catalog checks it agrees with this file; NIST decides which is right.
 * <p>
 * This test fails when the checked-in file no longer matches the library. To regenerate it after a deliberate change:
 * {@code mvn test -Dtest=UnitScalesFixtureTest -Dunitility.writeUnitScales=true}.
 * <p>
 * Format, one entry per unit, keyed {@code "EnumSimpleName.CONSTANT"}:
 * {@code {"symbol": "psi", "scale": 6894.757293168362, "offset": 0}}, where {@code base = value * scale + offset}.
 * A logarithmic unit has {@code "nonlinear": true} and no scale. {@code "unverified": true} marks a factor with no
 * primary source (the mercury columns), {@code "deprecated": true} a deprecated unit (Mach).
 */
class UnitScalesFixtureTest {

    static final Path FIXTURE = Paths.get("src", "test", "resources", "unit-scales.json");
    private static final String WRITE_PROPERTY = "unitility.writeUnitScales";

    @Test
    @DisplayName("the checked-in unit-scales.json matches every unit of the library")
    void fixtureMatchesTheLibrary() throws IOException {
        // Given
        String expected = render();

        // When
        if (Boolean.getBoolean(WRITE_PROPERTY)) {
            Files.createDirectories(FIXTURE.getParent());
            Files.write(FIXTURE, expected.getBytes(StandardCharsets.UTF_8));
        }

        // Then
        assertThat(FIXTURE).as("run with -D%s=true to regenerate", WRITE_PROPERTY).exists();
        String actual = new String(Files.readAllBytes(FIXTURE), StandardCharsets.UTF_8);
        assertThat(actual).as("unit-scales.json is stale: run with -D%s=true to regenerate", WRITE_PROPERTY)
                .isEqualTo(expected);
    }

    static String render() {
        Map<String, Unit> units = new TreeMap<>(UnitCatalog.allUnits());
        StringBuilder json = new StringBuilder("{\n");
        int index = 0;
        for (Map.Entry<String, Unit> entry : units.entrySet()) {
            String key = entry.getKey();
            Unit unit = entry.getValue();
            json.append("  \"").append(key).append("\": {\"symbol\": \"").append(escape(unit.getSymbol())).append('"');
            if (isNonlinear(unit)) {
                json.append(", \"nonlinear\": true");
            } else if (unit.toValueInBaseUnit(0.0) != 0.0) {
                // Affine (°C, °F): the scale and offset of its definition, correctly rounded. Reading them back off
                // the converters would cost a digit to cancellation. UnitGoldenTableTest checks the library against
                // the same definition.
                GoldenTable.Row row = GoldenTable.ROWS.get(key);
                double scale = ReferenceExpression.evaluate(row.expression()).doubleValue();
                double offset = ReferenceExpression.evaluate(row.offsetExpression()).doubleValue();
                json.append(", \"scale\": ").append(scale).append(", \"offset\": ").append(offset);
            } else {
                json.append(", \"scale\": ").append(unit.toValueInBaseUnit(1.0)).append(", \"offset\": 0");
            }
            if (GoldenTable.UNVERIFIED_UNITS.containsKey(key)) {
                json.append(", \"unverified\": true");
            }
            if (GoldenTable.NOT_A_UNIT.containsKey(key)) {
                json.append(", \"deprecated\": true");
            }
            json.append('}');
            json.append(++index < units.size() ? ",\n" : "\n");
        }
        return json.append("}\n").toString();
    }

    /** A logarithmic unit: its steps are not equal. Linear and affine units have equal steps. */
    private static boolean isNonlinear(Unit unit) {
        double first = unit.toValueInBaseUnit(1.0) - unit.toValueInBaseUnit(0.0);
        double second = unit.toValueInBaseUnit(2.0) - unit.toValueInBaseUnit(1.0);
        return Math.abs(second - first) > 1e-9 * Math.abs(first);
    }

    private static String escape(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

}
