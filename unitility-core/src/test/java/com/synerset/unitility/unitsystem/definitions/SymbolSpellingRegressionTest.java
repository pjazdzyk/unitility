package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.hydraulic.LinearResistance;
import com.synerset.unitility.unitsystem.hydraulic.LinearResistanceUnits;
import com.synerset.unitility.unitsystem.thermodynamic.AirFuelRatioVolumeUnits;
import com.synerset.unitility.unitsystem.thermodynamic.VapourQualityUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Spellings a client really sends, found refused on 2026-10-06 by the EnergyFlowX unit converter: a dimensionless
 * vapour quality written "-", and a volumetric air-fuel ratio lower-cased by the caller before parsing.
 */
class SymbolSpellingRegressionTest {

    @Test
    @DisplayName("a vapour quality fraction written as a hyphen is the fraction, as for Ratio and RelativeHumidity")
    void vapourQualityHyphenIsTheFraction() {
        assertThat(VapourQualityUnits.fromSymbol("-")).isEqualTo(VapourQualityUnits.FRACTION);
        assertThat(VapourQualityUnits.fromSymbol("%")).isEqualTo(VapourQualityUnits.PERCENT);
    }

    @Test
    @DisplayName("an air-fuel ratio symbol is read in any letter case, since none of them differ only by case")
    void airFuelRatioInAnyCase() {
        assertThat(AirFuelRatioVolumeUnits.fromSymbol("nm³/nm³"))
                .isEqualTo(AirFuelRatioVolumeUnits.NORMAL_CUBIC_METER_PER_NORMAL_CUBIC_METER);
        assertThat(AirFuelRatioVolumeUnits.fromSymbol("Nm³/Nm³"))
                .isEqualTo(AirFuelRatioVolumeUnits.NORMAL_CUBIC_METER_PER_NORMAL_CUBIC_METER);
        assertThat(AirFuelRatioVolumeUnits.fromSymbol("m³/m³")).isEqualTo(AirFuelRatioVolumeUnits.CUBIC_METER_PER_CUBIC_METER);
        assertThat(AirFuelRatioVolumeUnits.fromSymbol("SCF/SCF"))
                .isEqualTo(AirFuelRatioVolumeUnits.STANDARD_CUBIC_FOOT_PER_STANDARD_CUBIC_FOOT);
    }

    @Test
    @DisplayName("a pressure gradient parses in kPa/m and in psi/100ft, the units pipe-sizing tables are printed in")
    void gradientUnitsParse() {
        assertThat(LinearResistanceUnits.fromSymbol("kPa/m")).isEqualTo(LinearResistanceUnits.KILOPASCAL_PER_METER);
        assertThat(LinearResistanceUnits.fromSymbol("psi/100ft")).isEqualTo(LinearResistanceUnits.PSI_PER_100_FEET);
        assertThat(LinearResistance.ofKilopascalPerMeter(0.3).getInPascalPerMeter()).isCloseTo(300.0, within(1e-9));
        // 1 psi per 100 ft = 6894.757293168361 Pa / 30.48 m = 226.20594793859453 Pa/m (exact rational, rounded)
        assertThat(LinearResistance.ofPsiPer100Feet(1).getInPascalPerMeter()).isCloseTo(226.20594793859453, within(1e-9));
    }
}
