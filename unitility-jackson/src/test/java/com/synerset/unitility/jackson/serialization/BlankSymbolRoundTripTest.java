package com.synerset.unitility.jackson.serialization;

import com.synerset.unitility.jackson.module.PhysicalQuantityJacksonModule;
import com.synerset.unitility.unitsystem.common.Effectiveness;
import com.synerset.unitility.unitsystem.common.Ratio;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A dimensionless quantity whose unit symbol is the empty string must survive a JSON round trip.
 *
 * <h2>Why this test is at the serializer level and not at the unit level</h2>
 *
 * <p>{@link PhysicalQuantitySerializer} <b>omits the unit field entirely</b> when the symbol is
 * blank, so a decimal-unit quantity goes over the wire as a bare {@code {"value": 0.65}}. The
 * deserializer then has no symbol to resolve and falls back to the quantity's <b>default parsing
 * unit</b>, which is a third place, separate from the enum's base unit and from its blank-symbol
 * handling, where the same convention has to be stated.</p>
 *
 * <p>All three disagreed for {@code Effectiveness} and {@code Ratio}: the base unit was the
 * percent, the blank symbol resolved to the percent, and the default parsing unit was the percent,
 * while the decimal carried the empty symbol. A fan efficiency sent as {@code ofDecimal(0.65)}
 * arrived as 0.65 percent, so the fan did a hundred times the shaft work and delivered air at
 * 405 K, and nothing complained because 0.0065 is a legal fraction. Unit-level round-trip tests
 * passed throughout, because they never dropped the symbol. Only this level catches it.</p>
 *
 * <p>{@link RelativeHumidity} is included because it had the arrangement right first and is the
 * reference the other two were brought into line with. If it ever fails here, the convention moved
 * underneath all three.</p>
 */
@DisplayName("A blank unit symbol survives a JSON round trip as the decimal it looks like")
class BlankSymbolRoundTripTest {

    private final JsonMapper mapper = JsonMapper.builder()
            .addModule(new PhysicalQuantityJacksonModule(
                    PhysicalQuantityParsingFactory.getDefaultParsingFactory()))
            .build();

    @Test
    @DisplayName("Effectiveness written as a decimal is not read back as a percent")
    void effectivenessDecimalRoundTrips() {
        for (double decimal : new double[]{0.05, 0.505, 0.65, 0.9, 1.0}) {
            Effectiveness original = Effectiveness.ofDecimal(decimal);
            String json = mapper.writeValueAsString(original);

            assertThat(json)
                    .as("the serializer drops a blank symbol, which is the whole trap")
                    .doesNotContain("unit");
            assertThat(mapper.readValue(json, Effectiveness.class).getInDecimal())
                    .as("round trip of %s", json)
                    .isEqualTo(decimal);
        }
    }

    @Test
    @DisplayName("Ratio written as a decimal is not read back as a percent")
    void ratioDecimalRoundTrips() {
        Ratio original = Ratio.ofDecimal(0.65);
        assertThat(mapper.readValue(mapper.writeValueAsString(original), Ratio.class).getInDecimal())
                .isEqualTo(0.65);
    }

    @Test
    @DisplayName("RelativeHumidity, the quantity that had it right first, still round trips")
    void relativeHumidityDecimalRoundTrips() {
        RelativeHumidity original = RelativeHumidity.ofDecimal(0.5);
        assertThat(mapper.readValue(mapper.writeValueAsString(original), RelativeHumidity.class)
                .getInDecimal())
                .isEqualTo(0.5);
    }

    @Test
    @DisplayName("an explicit percent symbol still means a percent, on all three")
    void percentSymbolIsUnaffected() {
        assertThat(mapper.writeValueAsString(Effectiveness.ofPercentage(65.0)))
                .contains("\"unit\":\"%\"");
        assertThat(mapper.readValue(mapper.writeValueAsString(Effectiveness.ofPercentage(65.0)),
                Effectiveness.class).getInDecimal()).isEqualTo(0.65);
        assertThat(mapper.readValue(mapper.writeValueAsString(Ratio.ofPercentage(65.0)),
                Ratio.class).getInDecimal()).isEqualTo(0.65);
        assertThat(mapper.readValue(mapper.writeValueAsString(RelativeHumidity.ofPercentage(50.0)),
                RelativeHumidity.class).getInDecimal()).isEqualTo(0.5);
    }
}
