package com.synerset.unitility.unitsystem.hydraulic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@DisplayName("SpecificFanPower — the figure a ventilation schedule carries")
class SpecificFanPowerTest {

    @Test
    @DisplayName("watts per litre per second is a thousand times the base unit, by definition")
    void wattPerLitrePerSecondIsAThousand() {
        SpecificFanPower sfp = SpecificFanPower.ofWattPerLitrePerSecond(1.5);

        assertThat(sfp.getInWattPerCubicMeterPerSecond()).isCloseTo(1500.0, within(1.0e-9));
        assertThat(sfp.getInKilowattPerCubicMeterPerSecond()).isCloseTo(1.5, within(1.0e-12));
    }

    @Test
    @DisplayName("watts per cubic foot per minute is the reciprocal of the exact cfm definition")
    void wattPerCubicFootPerMinuteIsTheReciprocalOfCfm() {
        // 1 ft is 0.3048 m exactly, so 1 cfm is 0.3048^3 / 60 m3/s exactly, and W per cfm is the
        // reciprocal of that in W per (m3/s). Derived here rather than copied from anywhere.
        double cubicFootPerMinute = Math.pow(0.3048, 3) / 60.0;
        double expectedScale = 1.0 / cubicFootPerMinute;

        assertThat(SpecificFanPower.ofWattPerCubicFootPerMinute(1.0).getInWattPerCubicMeterPerSecond())
                .isCloseTo(expectedScale, within(1.0e-6));
    }

    @Test
    @DisplayName("a European schedule figure converts to the American one and back without drift")
    void roundTripsBetweenTheTwoConventions() {
        SpecificFanPower european = SpecificFanPower.ofWattPerLitrePerSecond(1.6);
        SpecificFanPower american = european.toWattPerCubicFootPerMinute();

        assertThat(american.getInWattPerLitrePerSecond()).isCloseTo(1.6, within(1.0e-9));
        assertThat(american.getUnit().getSymbol()).isEqualTo("W/cfm");
    }

    @Test
    @DisplayName("the base unit is watts per cubic metre per second")
    void baseUnitIsSi() {
        assertThat(SpecificFanPower.ofWattPerLitrePerSecond(1.0).getUnit().getBaseUnit())
                .isEqualTo(SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND);
    }

    @Test
    @DisplayName("symbols parse, including the empty one, which falls back to the base unit")
    void symbolsParse() {
        assertThat(SpecificFanPowerUnits.fromSymbol("W/(l/s)"))
                .isEqualTo(SpecificFanPowerUnits.WATT_PER_LITRE_PER_SECOND);
        assertThat(SpecificFanPowerUnits.fromSymbol("W/cfm"))
                .isEqualTo(SpecificFanPowerUnits.WATT_PER_CUBIC_FOOT_PER_MINUTE);
        assertThat(SpecificFanPowerUnits.fromSymbol(null))
                .isEqualTo(SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND);
    }

    @Test
    @DisplayName("an unknown symbol is refused rather than silently defaulted")
    void unknownSymbolIsRefused() {
        assertThatThrownBy(() -> SpecificFanPowerUnits.fromSymbol("inH2O"))
                .hasMessageContaining("Unsupported unit symbol");
    }

    @Test
    @DisplayName("two quantities that are the same physical value are equal whatever unit they carry")
    void equalityIsOnTheBaseValue() {
        assertThat(SpecificFanPower.ofWattPerLitrePerSecond(1.5))
                .isEqualTo(SpecificFanPower.ofWattPerCubicMeterPerSecond(1500.0));
    }
}
