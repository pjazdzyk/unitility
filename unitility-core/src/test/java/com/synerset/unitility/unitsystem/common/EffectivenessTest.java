package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.flow.MassFlow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EffectivenessTest {
    @Test
    @DisplayName("should convert % to decimal and vice versa")
    void shouldProperlyConvertPercentToDecimalAndViceVersa() {
        // Given
        Effectiveness initialEffectiveness = Effectiveness.ofPercentage(50.5);

        // When
        Effectiveness actualInDecimal = initialEffectiveness.toUnit(EffectivenessUnits.DECIMAL);
        double actualInDecimalVal = initialEffectiveness.getInDecimal();
        Effectiveness actualInPercent = actualInDecimal.toUnit(EffectivenessUnits.PERCENT);
        double actualInPercentVal = actualInDecimal.getInPercent();

        // Then
        Effectiveness expectedInDecimal = Effectiveness.ofDecimal(0.505);
        assertThat(actualInDecimal.getValue()).isEqualTo(actualInDecimalVal);
        assertThat(actualInPercent.getValue()).isEqualTo(actualInPercentVal);
        assertThat(actualInDecimal).isEqualTo(expectedInDecimal);
        assertThat(actualInPercent).isEqualTo(initialEffectiveness);
    }

    /**
     * The base unit is the dimensionless decimal, not the percent.
     *
     * <p>It matters well beyond arithmetic. {@code DECIMAL} carries the empty symbol, so a
     * serialized decimal value has no unit text for a reader to key on, and a blank symbol
     * has to resolve to <i>something</i>. If that something were the percent, then a value
     * written as a decimal would be read back a hundred times larger, silently, and with
     * every range check in the library still passing because a fraction is a legal percent.
     * Making the decimal the base makes the blank symbol mean what it looks like it means.
     * This matches {@code RelativeHumidityUnits}, which had it right first.</p>
     */
    @Test
    @DisplayName("should have the decimal as base unit, so a blank symbol round-trips")
    void shouldHaveDecimalAsBaseUnit() {
        // Given
        EffectivenessUnit expectedBaseUnit = EffectivenessUnits.DECIMAL;

        // When
        Effectiveness effectivenessInDecimal = Effectiveness.ofDecimal(0.1);
        EffectivenessUnit actualBaseUnit = effectivenessInDecimal.getUnit().getBaseUnit();

        // Then
        assertThat(actualBaseUnit).isEqualTo(expectedBaseUnit);
    }

    @Test
    @DisplayName("should return valid result from to() and getIn() methods")
    void shouldReturnValidResultFromToAndGetInMethods() {
        // Given
        Effectiveness expected = Effectiveness.ofPercentage(10.1);

        // When
        Effectiveness actual = expected.toDecimal()
                .toPercent();

        double actualValue = expected.getInPercent();

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualValue).isEqualTo(expected.getValue());
    }

    @Test
    @DisplayName("should return valid result from two PhysicalQuantity passed as arguments")
    void shouldReturnValidResultFromTwoPhysicalQuantityPassedAsArguments() {
        // Given
        MassFlow inletMassFlow = MassFlow.ofKilogramsPerSecond(1);
        MassFlow outletMassFlow = MassFlow.ofKilogramsPerSecond(2);

        // When
        Effectiveness flowEffectiveness = Effectiveness.from(inletMassFlow, outletMassFlow);
        Effectiveness flowEffectivenessFromValues = Effectiveness.from(1, 2);

        // Then
        assertThat(flowEffectiveness).isNotNull();
        assertThat(flowEffectivenessFromValues).isEqualTo(flowEffectiveness);
        assertThat(flowEffectiveness.getInPercent()).isEqualTo(50);
        assertThat(flowEffectiveness.getInDecimal()).isEqualTo(0.5);
    }

    /**
     * The regression this convention exists to prevent.
     *
     * <p>A quantity crosses a REST boundary as a value plus its unit symbol. {@code ofDecimal}
     * produces the empty symbol, so the round trip is only lossless if a blank symbol resolves
     * back to the decimal. When it resolved to the percent instead, a fan efficiency sent as
     * {@code ofDecimal(0.65)} came back as 0.65 percent, the fan did a hundred times the shaft
     * work, and the air left at over 130 degrees Celsius. Nothing complained, because 0.0065 is
     * a perfectly legal fraction and every bounds check in the chain passed.</p>
     */
    @Test
    @DisplayName("a decimal value survives a symbol round trip without changing by a factor of 100")
    void decimalSurvivesASymbolRoundTrip() {
        for (double decimal : new double[]{0.0, 0.05, 0.505, 0.65, 0.9, 1.0}) {
            Effectiveness original = Effectiveness.ofDecimal(decimal);
            Effectiveness roundTripped = Effectiveness.of(original.getValue(), original.getUnit().getSymbol());

            assertThat(roundTripped.getInDecimal())
                    .as("decimal %s must not be re-read as a percent", decimal)
                    .isEqualTo(decimal);
            assertThat(roundTripped).isEqualTo(original);
        }
    }

    @Test
    @DisplayName("a percentage value survives a symbol round trip too")
    void percentageSurvivesASymbolRoundTrip() {
        Effectiveness original = Effectiveness.ofPercentage(65.0);
        Effectiveness roundTripped = Effectiveness.of(original.getValue(), original.getUnit().getSymbol());

        assertThat(roundTripped.getInPercent()).isEqualTo(65.0);
        assertThat(roundTripped.getInDecimal()).isEqualTo(0.65);
    }
}
