package com.synerset.unitility.unitsystem.definitions;

import com.synerset.unitility.unitsystem.common.CurvatureUnits;
import com.synerset.unitility.unitsystem.common.SpecificVolumeUnits;
import com.synerset.unitility.unitsystem.humidity.HumidityRatioUnits;
import com.synerset.unitility.unitsystem.hydraulic.FlowCoefficientUnits;
import com.synerset.unitility.unitsystem.hydraulic.LinearResistanceUnits;
import com.synerset.unitility.unitsystem.thermodynamic.LinearHeatFluxUnits;
import com.synerset.unitility.unitsystem.thermodynamic.PressureUnits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pins the ten factors that were WRONG in 4.1.0 (error above 1e-5, 01_audit.md), each at its exact value: the
 * nearest double to the value computed from the cited definitions. Each comment gives the 4.1.0 value and the source.
 * The expected literals were computed from the definitions in 34-digit decimal arithmetic, not read from Unitility.
 */
class CorrectedFactorsTest {

    @Test
    @DisplayName("°/100ft: (π/180) / (100 × 0.3048) rad/m, was 10 000 times too large")
    void degreesPerHundredFeet() {
        // 4.1.0: 5.72614584 rad/m (multiplied by 100 where it must divide).
        // Source: NIST SP 811 Table 6, 1° = (π/180) rad; App. B.8, foot 3.048 E-01 exact.
        assertThat(CurvatureUnits.DEGREES_PER_HUNDRED_FEET.toValueInBaseUnit(1.0)).isEqualTo(5.72614583987641E-4);
    }

    @Test
    @DisplayName("lb/lb: a mass ratio has no factor, was divided by 2.2046")
    void poundPerPound() {
        // 4.1.0: 0.45359237 kg/kg per lb/lb. Live on the MCP server: 0.01 kg/kg read as 0.022046 lb/lb.
        // Source: [S0] dimensional reasoning, lb/lb = kg/kg.
        assertThat(HumidityRatioUnits.POUND_PER_POUND.toValueInBaseUnit(1.0)).isEqualTo(1.0);
        assertThat(HumidityRatioUnits.POUND_PER_POUND.fromValueInBaseUnit(0.01)).isEqualTo(0.01);
    }

    @Test
    @DisplayName("Cv: 1 Cv = 0.864 977 655 Kv, was 0.85667")
    void flowCoefficientCv() {
        // 4.1.0: 0.85667 Kv per Cv (0.96 % low). Live on the MCP server.
        // Kv/Cv = (1 US gpm in m³/h) × √(1 bar / 1 psi) = 0.227 124 707 04 × √14.503 773 773 = 0.864 977 655 442 3...
        // Sources: [S6] Cv = US gpm at 1 psi, Kv = m³/h at 1 bar ("Kv = 0.865 Cv"); NIST Handbook 44 gallon = 231 in³;
        // NIST SP 811 App. B.8 inch, pound-force (fn 23), Table 9 bar.
        assertThat(FlowCoefficientUnits.CV.toValueInBaseUnit(1.0)).isEqualTo(0.8649776554423018);
    }

    @Test
    @DisplayName("inH₂O/100ft: 249.088 91 Pa / 30.48 m, was 8.16722 Pa/m")
    void inchOfWaterPerHundredFeet() {
        // 4.1.0: 8.16722 Pa/m (matches no inch of water).
        // Source: NIST SP 811 App. B.8, millimeter of water, conventional 9.806 65 Pa exact, so the conventional inch
        // of water is 25.4 × 9.806 65 = 249.088 91 Pa (B.8 lists 2.490 889 E+02); foot 3.048 E-01 exact.
        assertThat(LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET.toValueInBaseUnit(1.0))
                .isEqualTo(8.172208333333334);
    }

    @Test
    @DisplayName("mH₂O at 10, 60, 95 °C: NIST WebBook water densities × g_n")
    void metreOfWaterAtTemperature() {
        // 4.1.0: 999.5457, 982.6716 and 961.2691 kg/m³.
        // Source: [S5] NIST Chemistry WebBook, water (IAPWS-95) at 0.101325 MPa: 999.70247 (10 °C), 983.19582 (60 °C),
        // 961.88792 kg/m³ (95 °C); NIST SP 811 standard gravity 9.806 65 m/s² exact.
        assertThat(PressureUnits.METRE_OF_WATER_10.toValueInBaseUnit(1.0)).isEqualTo(9803.7322274255);
        assertThat(PressureUnits.METRE_OF_WATER_60.toValueInBaseUnit(1.0)).isEqualTo(9641.857288203);
        assertThat(PressureUnits.METRE_OF_WATER_95.toValueInBaseUnit(1.0)).isEqualTo(9432.898170668);
    }

    @Test
    @DisplayName("BTU/(h·ft) and BTU/(min·ft): Btu_IT / h / ft, was 0.96132649")
    void btuPerHourAndMinuteFoot() {
        // 4.1.0: 0.96132649 and 57.6795894 W/m.
        // Source: NIST SP 811 App. B.8 fn 9, Btu_IT = 1055.055 852 62 J exactly (Btu_IT/h 2.930 711 E-01 W); hour
        // 3600 s, minute 60 s (Table 6); foot 3.048 E-01 exact.
        assertThat(LinearHeatFluxUnits.BTU_PER_HOUR_FOOT.toValueInBaseUnit(1.0)).isEqualTo(0.9615192590952173);
        assertThat(LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT.toValueInBaseUnit(1.0)).isEqualTo(57.69115554571304);
    }

    @Test
    @DisplayName("fl.oz/lb: (231 in³ / 128) / 0.453 592 37 kg, was 6.520391e-5")
    void fluidOuncePerPound() {
        // 4.1.0: 6.520391E-5 m³/kg (a wrong US fluid ounce).
        // Source: NIST Handbook 44, 1 gallon = 231 in³ = 128 fluid ounces; NIST SP 811 App. B.8 inch exact, fl oz
        // 2.957 353 E-05 m³, fn 22 pound 0.453 592 37 kg exactly.
        assertThat(SpecificVolumeUnits.OUNCE_PER_POUND.toValueInBaseUnit(1.0)).isEqualTo(6.519847228140103E-5);
    }

}
