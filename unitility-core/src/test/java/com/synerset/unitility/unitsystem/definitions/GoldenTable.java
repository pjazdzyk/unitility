package com.synerset.unitility.unitsystem.definitions;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.DoubleUnaryOperator;

/**
 * The golden table: for every unit of every {@code *Units} enum, the reference factor to its base unit, written as
 * the published numbers of its source, never as a Unitility constant.
 * <p>
 * Sources (full quotes in {@code energy-flow-x-ui/reports/plans/completed/unitility-exact-conversions/01_audit.md}):
 * <ul>
 *     <li>[SI] SI prefixes, exact by definition.</li>
 *     <li>[S0] Dimensional reasoning, no factor: a base unit, a pure number, or a ratio whose unit cancels.</li>
 *     <li>[S1] NIST SP 811 (2008), Appendix B.8, where boldface factors are exact. Exact: foot 3.048 E-01, inch
 *     2.54 E-02, yard 9.144 E-01, mile 1.609 344 E+03, square foot 9.290 304 E-02, square inch 6.4516 E-04, foot per
 *     minute 5.08 E-03, standard atmosphere 1.013 25 E+05, UK gallon 4.546 09 E-03, standard gravity and
 *     kilogram-force 9.806 65, dyne 1.0 E-05, poise 1.0 E-01, are 1.0 E+02, hectare 1.0 E+04, tonne 1.0 E+03,
 *     watt hour 3.6 E+03, kilowatt hour 3.6 E+06, calorie_IT 4.1868, kilocalorie_IT 4.1868 E+03, Btu_IT per pound
 *     2.326 E+03, Btu_IT per pound degree Fahrenheit (and Rankine) 4.1868 E+03, calorie_IT per gram 4.1868 E+03,
 *     millimeter of water, conventional 9.806 65 E+00, T/K = (t/°F + 459.67)/1.8, T/K = t/°C + 273.15.
 *     Footnote 9: Btu_IT = 1.055 055 852 62 kJ exactly. Footnote 22: pound = 0.453 592 37 kg exactly.
 *     Footnote 23: pound-force = 4.448 221 615 260 5 N exactly. Seven-digit (not exact) factors are used as
 *     cross-checks.</li>
 *     <li>[S1-T6] NIST SP 811 Table 6: 1 min = 60 s, 1 h = 3600 s, 1 d = 86 400 s, 1° = (π/180) rad,
 *     1 L = 10⁻³ m³, 1 ha = 10⁴ m², 1 t = 10³ kg.</li>
 *     <li>[S1-T9] NIST SP 811 Table 9: 1 nautical mile = 1852 m, 1 knot = (1852/3600) m/s, 1 bar = 10⁵ Pa.</li>
 *     <li>[S1-8.7] NIST SP 811 Sec. 8.7: a field level is 20 lg(F/F0) dB, a power level 10 lg(P/P0) dB.</li>
 *     <li>[S2] NIST Handbook 44 (2024), Appendix C: gallon = 231 in³ = 8 pints = 128 fluid ounces, pint = 28.875
 *     in³, 16 ounces = 1 pound, acre = 43 560 square feet, mile = 5280 feet.</li>
 *     <li>[S5] NIST Chemistry WebBook, water (IAPWS-95), isobar 0.101325 MPa: 999.70247 (10 °C), 983.19582
 *     (60 °C), 961.88792 kg/m³ (95 °C).</li>
 *     <li>[S6] Cv = US gpm of water per 1 psi, Kv = m³/h of water per 1 bar (secondary source), which also states
 *     "Kv = 0.86497767 Cv".</li>
 *     <li>[DEF-UNVERIFIED] A definition with no primary source read: the pound-mole (453.59237 mol), the data mile
 *     (6000 ft), the standard reference temperatures of the standard volumetric flows.</li>
 * </ul>
 */
final class GoldenTable {

    /** How a row's reference is compared with the library. */
    enum Kind {
        /** An exact definition: the library must agree to within 1 ulp of the correctly rounded reference. */
        EXACT,
        /** A published, rounded value: compared at the precision it is published to. */
        PUBLISHED,
        /** An affine unit: {@code base = value * scale + offset}, compared at 1e-12 relative. */
        AFFINE,
        /** A logarithmic unit: compared point by point with the reference formula, at 1e-12 relative. */
        NONLINEAR
    }

    record Row(String key, Kind kind, String expression, String offsetExpression, String crossCheck,
               DoubleUnaryOperator formula, String source) {
    }

    /** Units that have no golden row, each for a stated reason. The coverage test checks this list too. */
    static final Map<String, String> UNVERIFIED_UNITS;

    /** Units that are not units, kept deprecated for compatibility. The coverage test checks this list too. */
    static final Map<String, String> NOT_A_UNIT;

    static final Map<String, Row> ROWS;

    private static final String FT3 = "(0.3048 * 0.3048 * 0.3048)";
    private static final String IN3 = "(0.0254 * 0.0254 * 0.0254)";
    private static final String US_GAL = "(231 * " + IN3 + ")";
    private static final String PSI = "(4.4482216152605 / (0.0254 * 0.0254))";
    private static final String PSF = "(4.4482216152605 / (0.3048 * 0.3048))";

    private static final String BASE = "[S0] base unit";
    private static final String SI = "[SI] SI prefix, exact";
    private static final String UNIT_FREE = "[S0] ratio of like units, no factor";
    private static final String LBMOL = "[DEF-UNVERIFIED] pound-mole = 453.59237 mol, no primary source; ";
    private static final String STD_CONDITIONS = "[DEF-UNVERIFIED] reference temperatures as declared by the "
            + "library (normal 0 °C, standard 15 °C, standard cubic foot 60 °F, equal pressure, ideal gas), "
            + "no primary standard read; °F to K per [S1]; ";

    private static final Map<String, Row> rows = new LinkedHashMap<>();

    static {
        // --- Acoustic ---
        base("SoundPowerUnits.WATT");
        base("SoundPressureUnits.PASCAL");
        nonlinear("SoundPressureUnits.DECIBEL", db -> 2E-5 * Math.pow(10.0, db / 20.0),
                "[S1-8.7] field level L = 20 lg(p/p0) dB; p0 = 20 µPa as declared by the library, "
                        + "reference value not verified against a primary source");

        // --- Common ---
        base("AngleUnits.RADIANS");
        exact("AngleUnits.DEGREES", "pi / 180", "1.745329E-02", "[S1-T6] 1° = (π/180) rad");

        base("AngularVelocityUnits.RADIANS_PER_SECOND");
        exact("AngularVelocityUnits.REVOLUTIONS_PER_SECOND", "2 * pi", "6.283185", "[S1] revolution = 2π rad");
        exact("AngularVelocityUnits.REVOLUTIONS_PER_MINUTE", "2 * pi / 60", "1.047198E-01", "[S1] rpm; [S1-T6] min");
        exact("AngularVelocityUnits.DEGREES_PER_SECOND", "pi / 180", "1.745329E-02", "[S1-T6] 1° = (π/180) rad");

        base("AreaUnits.SQUARE_METER");
        exact("AreaUnits.SQUARE_KILOMETER", "1E6", null, SI);
        exact("AreaUnits.SQUARE_CENTIMETER", "1E-4", null, SI);
        exact("AreaUnits.SQUARE_MILLIMETER", "1E-6", null, SI);
        exact("AreaUnits.ARE", "100", null, "[S1] are 1.0 E+02 exact");
        exact("AreaUnits.HECTARE", "1E4", null, "[S1-T6] 1 ha = 10⁴ m²");
        exact("AreaUnits.SQUARE_INCH", "0.0254 * 0.0254", null, "[S1] square inch 6.4516 E-04 exact");
        exact("AreaUnits.SQUARE_FOOT", "0.3048 * 0.3048", null, "[S1] square foot 9.290 304 E-02 exact");
        exact("AreaUnits.SQUARE_YARD", "0.9144 * 0.9144", "8.361274E-01", "[S1] yard 9.144 E-01 exact");
        exact("AreaUnits.ACRE", "43560 * 0.3048 * 0.3048", null, "[S2] acre = 43 560 ft²; [S1] foot");
        exact("AreaUnits.SQUARE_MILE", "1609.344 * 1609.344", "2.589988E+06", "[S1] mile 1.609 344 E+03 exact");

        base("CurvatureUnits.RADIANS_PER_METER");
        exact("CurvatureUnits.RADIANS_PER_FOOT", "1 / 0.3048", null, "[S1] foot");
        exact("CurvatureUnits.DEGREES_PER_METER", "pi / 180", null, "[S1-T6] degree");
        exact("CurvatureUnits.DEGREES_PER_FOOT", "pi / 180 / 0.3048", null, "[S1-T6] degree; [S1] foot");
        exact("CurvatureUnits.DEGREES_PER_HUNDRED_FEET", "pi / 180 / (100 * 0.3048)", null,
                "[S1-T6] degree; [S1] foot. Was 10 000x too large in 4.1.0");

        base("DataSizeUnits.BYTE");
        String binary = "[S0] binary multiple 2^(10n) bytes as declared; the symbol is the IEC binary one "
                + "(KiB, MiB...) in SI terms, see 01_audit.md label note";
        exact("DataSizeUnits.BIT", "1 / 8", null, "[S0] a byte of 8 bits as declared");
        exact("DataSizeUnits.KILOBYTE", "1024", null, binary);
        exact("DataSizeUnits.MEGABYTE", "1024 * 1024", null, binary);
        exact("DataSizeUnits.GIGABYTE", "1024 * 1024 * 1024", null, binary);
        exact("DataSizeUnits.TERABYTE", "1024 * 1024 * 1024 * 1024", null, binary);
        exact("DataSizeUnits.PETABYTE", "1024 * 1024 * 1024 * 1024 * 1024", null, binary);

        base("DistanceUnits.METER");
        exact("DistanceUnits.CENTIMETER", "0.01", null, SI);
        exact("DistanceUnits.MILLIMETER", "0.001", null, SI);
        exact("DistanceUnits.KILOMETER", "1000", null, SI);
        exact("DistanceUnits.MILE", "1609.344", null, "[S1] mile 1.609 344 E+03 exact; [S2] 5280 ft");
        exact("DistanceUnits.NAUTICAL_MILE", "1852", null, "[S1-T9] 1 nautical mile = 1852 m");
        exact("DistanceUnits.FEET", "0.3048", null, "[S1] foot 3.048 E-01 exact");
        exact("DistanceUnits.INCH", "0.0254", null, "[S1] inch 2.54 E-02 exact");
        exact("DistanceUnits.YARD", "0.9144", null, "[S1] yard 9.144 E-01 exact");
        exact("DistanceUnits.DECAMETER", "10", null, SI);
        exact("DistanceUnits.HECTOMETER", "100", null, SI);
        exact("DistanceUnits.DATAMILE", "6000 * 0.3048", null,
                "[DEF-UNVERIFIED] data mile = 6000 ft, no primary source; [S1] foot");

        base("EffectivenessUnits.DECIMAL");
        exact("EffectivenessUnits.PERCENT", "0.01", null, "[S0] 1 % = 0.01");

        base("LinearMassDensityUnits.KILOGRAM_PER_METER");
        exact("LinearMassDensityUnits.TONNE_PER_METER", "1000", null, "[S1-T6] 1 t = 10³ kg");
        exact("LinearMassDensityUnits.OUNCE_PER_FOOT", "0.45359237 / 16 / 0.3048", null,
                "[S2] 16 oz = 1 lb; [S1] pound, foot");
        exact("LinearMassDensityUnits.POUND_PER_FOOT", "0.45359237 / 0.3048", "1.488164", "[S1] pound, foot");

        base("MassUnits.KILOGRAM");
        exact("MassUnits.GRAM", "0.001", null, SI);
        exact("MassUnits.MILLIGRAM", "1E-6", null, SI);
        exact("MassUnits.TONNE_SI", "1000", null, "[S1-T6] 1 t = 10³ kg");
        exact("MassUnits.OUNCE", "0.45359237 / 16", "2.834952E-02", "[S2] 16 oz = 1 lb; [S1] pound");
        exact("MassUnits.POUND", "0.45359237", null, "[S1] fn 22, pound 0.453 592 37 kg exactly");

        base("RatioUnits.DECIMAL");
        exact("RatioUnits.PERCENT", "0.01", null, "[S0] 1 % = 0.01");

        base("SpecificVolumeUnits.CUBIC_METER_PER_KILOGRAM");
        exact("SpecificVolumeUnits.CUBIC_CENTIMETER_PER_KILOGRAM", "1E-6", null, SI);
        exact("SpecificVolumeUnits.CUBIC_DECIMETER_PER_KILOGRAM", "1E-3", null, SI);
        exact("SpecificVolumeUnits.LITER_PER_KILOGRAM", "1E-3", null, "[S1-T6] 1 L = 10⁻³ m³");
        exact("SpecificVolumeUnits.HECTOLITER_PER_KILOGRAM", "0.1", null, "[S1-T6] litre; [SI] hecto");
        exact("SpecificVolumeUnits.MILLILITER_PER_KILOGRAM", "1E-6", null, "[S1-T6] litre; [SI] milli");
        exact("SpecificVolumeUnits.CUBIC_FOOT_PER_POUND", FT3 + " / 0.45359237", null, "[S1] foot, pound");
        exact("SpecificVolumeUnits.GALLON_US_PER_POUND", US_GAL + " / 0.45359237", null,
                "[S2] gallon = 231 in³; [S1] inch, pound");
        exact("SpecificVolumeUnits.GALLON_UK_PER_POUND", "0.00454609 / 0.45359237", null,
                "[S1] UK gallon 4.546 09 E-03 exact, pound");
        exact("SpecificVolumeUnits.OUNCE_PER_POUND", US_GAL + " / 128 / 0.45359237", "2.957353E-05 / 0.45359237",
                "[S2] 128 fl oz = 1 gallon = 231 in³; [S1] inch, pound, fl oz 2.957 353 E-05");

        base("TimeUnits.SECOND");
        exact("TimeUnits.MILLISECOND", "1E-3", null, SI);
        exact("TimeUnits.MINUTE", "60", null, "[S1-T6] 1 min = 60 s");
        exact("TimeUnits.HOUR", "3600", null, "[S1-T6] 1 h = 3600 s");
        exact("TimeUnits.DAY", "86400", null, "[S1-T6] 1 d = 86 400 s");

        base("VelocityUnits.METER_PER_SECOND");
        exact("VelocityUnits.CENTIMETER_PER_SECOND", "0.01", null, SI);
        exact("VelocityUnits.KILOMETER_PER_HOUR", "1000 / 3600", null, "[SI] kilo; [S1-T6] hour");
        exact("VelocityUnits.INCH_PER_SECOND", "0.0254", null, "[S1] inch per second 2.54 E-02 exact");
        exact("VelocityUnits.FEET_PER_SECOND", "0.3048", null, "[S1] foot per second 3.048 E-01 exact");
        exact("VelocityUnits.FEET_PER_MINUTE", "0.3048 / 60", null, "[S1] foot per minute 5.08 E-03 exact");
        exact("VelocityUnits.MILES_PER_HOUR", "1609.344 / 3600", null, "[S1] mile per hour 1.609 344 km/h exact");
        exact("VelocityUnits.KNOT", "1852 / 3600", "5.144444E-01", "[S1-T9] 1 knot = (1852/3600) m/s");

        base("VolumeUnits.CUBIC_METER");
        exact("VolumeUnits.CUBIC_CENTIMETER", "1E-6", null, SI);
        exact("VolumeUnits.CUBIC_DECIMETER", "1E-3", null, SI);
        exact("VolumeUnits.CUBIC_FOOT", FT3, "2.831685E-02", "[S1] foot");
        exact("VolumeUnits.LITRE", "1E-3", null, "[S1-T6] 1 L = 10⁻³ m³");
        exact("VolumeUnits.HECTOLITRE", "0.1", null, "[S1-T6] litre; [SI] hecto");
        exact("VolumeUnits.MILLILITRE", "1E-6", null, "[S1-T6] litre; [SI] milli");
        exact("VolumeUnits.OUNCE", US_GAL + " / 128", "2.957353E-05", "[S2] 128 fl oz = 1 gallon = 231 in³");
        exact("VolumeUnits.PINT", "28.875 * " + IN3, "4.731765E-04", "[S2] pint = 28.875 in³");
        exact("VolumeUnits.GALLON_US", US_GAL, "3.785412E-03", "[S2] gallon = 231 in³; [S1] inch");
        exact("VolumeUnits.GALLON_UK", "0.00454609", null, "[S1] UK gallon 4.546 09 E-03 exact");

        // --- Dimensionless and similarity numbers ---
        base("BoilingNumberUnits.DIMENSIONLESS");
        base("ConfinementNumberUnits.DIMENSIONLESS");
        base("JakobNumberUnits.DIMENSIONLESS");
        base("MartinelliParameterUnits.DIMENSIONLESS");
        base("TwoPhaseMultiplierUnits.DIMENSIONLESS");
        base("BiotNumberUnits.DIMENSIONLESS");
        base("BondNumberUnits.DIMENSIONLESS");
        base("GenericDimensionlessUnits.DIMENSIONLESS");
        base("GrashofNumberUnits.DIMENSIONLESS");
        base("NusseltNumberUnits.DIMENSIONLESS");
        base("PecletNumberUnits.DIMENSIONLESS");
        base("PrandtlNumberUnits.DIMENSIONLESS");
        base("RayleighNumberUnits.DIMENSIONLESS");
        base("ReynoldsNumberUnits.DIMENSIONLESS");
        base("WeberNumberUnits.DIMENSIONLESS");
        base("BypassFactorUnits.DIMENSIONLESS");
        base("FrictionFactorUnits.DIMENSIONLESS");
        base("LocalLossFactorUnits.DIMENSIONLESS");
        base("CompressibilityFactorUnits.DIMENSIONLESS");
        base("MolarFractionUnits.DIMENSIONLESS");
        base("RelativeDensityUnits.DIMENSIONLESS");

        // --- Electric ---
        base("CapacitanceUnits.FARAD");
        exact("CapacitanceUnits.PICOFARAD", "1E-12", null, SI);
        exact("CapacitanceUnits.NANOFARAD", "1E-9", null, SI);
        exact("CapacitanceUnits.MICROFARAD", "1E-6", null, SI);
        exact("CapacitanceUnits.MILLIFARAD", "1E-3", null, SI);
        exact("CapacitanceUnits.KILOFARAD", "1E3", null, SI);
        exact("CapacitanceUnits.MEGAFARAD", "1E6", null, SI);
        base("ChargeUnits.COULOMB");
        exact("ChargeUnits.PICOCOULOMB", "1E-12", null, SI);
        exact("ChargeUnits.NANOCOULOMB", "1E-9", null, SI);
        exact("ChargeUnits.MICROCOULOMB", "1E-6", null, SI);
        exact("ChargeUnits.MILLICOULOMB", "1E-3", null, SI);
        exact("ChargeUnits.KILOCOULOMB", "1E3", null, SI);
        exact("ChargeUnits.MEGACOULOMB", "1E6", null, SI);
        base("ConductanceUnits.SIEMENS");
        exact("ConductanceUnits.PICOSIEMENS", "1E-12", null, SI);
        exact("ConductanceUnits.NANOSIEMENS", "1E-9", null, SI);
        exact("ConductanceUnits.MICROSIEMENS", "1E-6", null, SI);
        exact("ConductanceUnits.MILLISIEMENS", "1E-3", null, SI);
        exact("ConductanceUnits.KILOSIEMENS", "1E3", null, SI);
        base("CurrentUnits.AMPERE");
        exact("CurrentUnits.MICROAMPERE", "1E-6", null, SI);
        exact("CurrentUnits.MILLIAMPERE", "1E-3", null, SI);
        exact("CurrentUnits.KILOAMPERE", "1E3", null, SI);
        base("ResistanceUnits.OHM");
        exact("ResistanceUnits.MILLIOHM", "1E-3", null, SI);
        exact("ResistanceUnits.KILOOHM", "1E3", null, SI);
        exact("ResistanceUnits.MEGAOHM", "1E6", null, SI);
        base("VoltageUnits.VOLT");
        exact("VoltageUnits.MICROVOLT", "1E-6", null, SI);
        exact("VoltageUnits.MILLIVOLT", "1E-3", null, SI);
        exact("VoltageUnits.KILOVOLT", "1E3", null, SI);
        exact("VoltageUnits.MEGAVOLT", "1E6", null, SI);
        exact("VoltageUnits.GIGAVOLT", "1E9", null, SI);

        // --- Flow ---
        base("MassFlowUnits.KILOGRAM_PER_SECOND");
        exact("MassFlowUnits.KILOGRAM_PER_HOUR", "1 / 3600", null, "[S1-T6] hour");
        exact("MassFlowUnits.TONNE_PER_HOUR", "1000 / 3600", null, "[S1-T6] tonne, hour");
        exact("MassFlowUnits.POUND_PER_SECOND", "0.45359237", "4.535924E-01", "[S1] pound");

        base("MassFluxUnits.KILOGRAM_PER_SQUARE_METER_SECOND");
        exact("MassFluxUnits.GRAM_PER_SQUARE_METER_SECOND", "1E-3", null, SI);
        exact("MassFluxUnits.POUND_PER_SQUARE_FOOT_SECOND", "0.45359237 / (0.3048 * 0.3048)", "4.882428",
                "[S1] pound, foot");
        exact("MassFluxUnits.POUND_PER_SQUARE_FOOT_HOUR", "0.45359237 / (0.3048 * 0.3048) / 3600", null,
                "[S1] pound, foot; [S1-T6] hour");

        base("NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_SECOND");
        exact("NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_MINUTE", "1 / 60", null, "[S1-T6] minute");
        exact("NormalVolumetricFlowUnits.NORMAL_CUBIC_METERS_PER_HOUR", "1 / 3600", null, "[S1-T6] hour");
        exact("NormalVolumetricFlowUnits.NORMAL_LITERS_PER_SECOND", "1E-3", null, "[S1-T6] litre");
        exact("NormalVolumetricFlowUnits.NORMAL_LITERS_PER_MINUTE", "1E-3 / 60", null, "[S1-T6] litre, minute");
        exact("NormalVolumetricFlowUnits.STANDARD_CUBIC_METERS_PER_HOUR", "273.15 / 288.15 / 3600", null,
                STD_CONDITIONS + "[S1-T6] hour");
        exact("NormalVolumetricFlowUnits.STANDARD_LITERS_PER_MINUTE", "1E-3 * 273.15 / 288.15 / 60", null,
                STD_CONDITIONS + "[S1-T6] litre, minute");
        exact("NormalVolumetricFlowUnits.STANDARD_CUBIC_FEET_PER_MINUTE",
                FT3 + " * 273.15 / ((60 + 459.67) / 1.8) / 60", null, STD_CONDITIONS + "[S1] foot");

        base("VolumetricFlowUnits.CUBIC_METERS_PER_SECOND");
        exact("VolumetricFlowUnits.CUBIC_METERS_PER_MINUTE", "1 / 60", null, "[S1-T6] minute");
        exact("VolumetricFlowUnits.CUBIC_METERS_PER_HOUR", "1 / 3600", null, "[S1-T6] hour");
        exact("VolumetricFlowUnits.CUBIC_FEET_PER_MINUTE", FT3 + " / 60", "4.719474E-04", "[S1] foot; minute");
        exact("VolumetricFlowUnits.LITRE_PER_SECOND", "1E-3", null, "[S1-T6] litre");
        exact("VolumetricFlowUnits.LITRE_PER_MINUTE", "1E-3 / 60", null, "[S1-T6] litre, minute");
        exact("VolumetricFlowUnits.LITRE_PER_HOUR", "1E-3 / 3600", null, "[S1-T6] litre, hour");
        exact("VolumetricFlowUnits.GALLONS_PER_SECOND_US", US_GAL, null, "[S2] gallon = 231 in³");
        exact("VolumetricFlowUnits.GALLONS_PER_MINUTE_US", US_GAL + " / 60", "6.309020E-05",
                "[S2] gallon; [S1-T6] minute; [S1] gpm 6.309 020 E-05");
        exact("VolumetricFlowUnits.GALLONS_PER_HOUR_US", US_GAL + " / 3600", null, "[S2] gallon; [S1-T6] hour");
        exact("VolumetricFlowUnits.GALLONS_PER_SECOND_UK", "0.00454609", null, "[S1] UK gallon exact");
        exact("VolumetricFlowUnits.GALLONS_PER_MINUTE_UK", "0.00454609 / 60", null, "[S1] UK gallon; minute");
        exact("VolumetricFlowUnits.GALLONS_PER_HOUR_UK", "0.00454609 / 3600", null, "[S1] UK gallon; hour");

        // --- Humidity ---
        base("HumidityRatioUnits.KILOGRAM_PER_KILOGRAM");
        exact("HumidityRatioUnits.GRAM_PER_KILOGRAM", "1E-3", null, SI);
        exact("HumidityRatioUnits.POUND_PER_POUND", "0.45359237 / 0.45359237", null,
                "[S0] a mass ratio has no factor, lb/lb = kg/kg. Was 1/2.2046 in 4.1.0");
        base("RelativeHumidityUnits.DECIMAL");
        exact("RelativeHumidityUnits.PERCENT", "0.01", null, "[S0] 1 % = 0.01");

        // --- Hydraulic ---
        base("FlowCoefficientUnits.KV");
        // Cross-checked against [S6]'s "Kv = 0.865 Cv". [S6] also gives 0.86497767, which is 1 in its last
        // digit away from the value its own definitions give (0.8649776554...), so it is not used as a check.
        exact("FlowCoefficientUnits.CV", US_GAL + " * 60 * sqrt(100000 / " + PSI + ")", "0.865",
                "[S6] Cv = US gpm per 1 psi, Kv = m³/h per 1 bar, so Kv/Cv = (gpm in m³/h) * sqrt(bar/psi); "
                        + "[S2] gallon; [S1] inch, pound-force; [S1-T9] bar. Was 0.85667 in 4.1.0");
        base("LinearResistanceUnits.PASCAL_PER_METER");
        exact("LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET", "25.4 * 9.80665 / (100 * 0.3048)",
                "249.0889 / 30.48", "[S1] millimeter of water, conventional 9.806 65 Pa exact (so an inch is 25.4 "
                        + "of them), inch of water, conventional 2.490 889 E+02; foot. Was 8.16722 in 4.1.0");
        published("LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET", "3386.38 / (100 * 0.3048)",
                "[S1] inch of mercury (32 °F) 3.386 38 E+03 Pa, a property-based unit (fn 12)");
        base("SpecificFanPowerUnits.WATT_PER_CUBIC_METER_PER_SECOND");
        exact("SpecificFanPowerUnits.WATT_PER_LITRE_PER_SECOND", "1000", null,
                "W per (l/s): a litre per second is a thousandth of a cubic metre per second, [S1] litre");
        exact("SpecificFanPowerUnits.KILOWATT_PER_CUBIC_METER_PER_SECOND", "1000", null,
                "The same number as W per (l/s), written the other way round; [S1] SI prefix kilo");
        exact("SpecificFanPowerUnits.WATT_PER_CUBIC_FOOT_PER_MINUTE", "60 / (0.3048 * 0.3048 * 0.3048)",
                "2118.88", "The reciprocal of a cubic foot per minute; [S1] foot 0.3048 exact, minute");
        base("RotationSpeedToFlowRateRatioUnits.RADIAN_PER_SECOND_PER_CUBIC_METER_PER_SECOND");
        exact("RotationSpeedToFlowRateRatioUnits.RPM_PER_GPM", "2 * pi / " + US_GAL, null,
                "(2π rad / min) / (US gal / min); [S2] gallon");

        // --- Mechanical ---
        base("ForceUnits.NEWTON");
        exact("ForceUnits.KILONEWTON", "1000", null, SI);
        exact("ForceUnits.KILOPOND", "9.80665", null, "[S1] kilogram-force 9.806 65 exact");
        exact("ForceUnits.DYNE", "1E-5", null, "[S1] dyne 1.0 E-05 exact");
        exact("ForceUnits.POUND_FORCE", "4.4482216152605", null, "[S1] fn 23, pound-force exact");
        exact("ForceUnits.POUNDAL", "0.45359237 * 0.3048", "1.382550E-01", "[S1] poundal = lb·ft/s²");
        base("MomentumUnits.KILOGRAM_METER_PER_SECOND");
        exact("MomentumUnits.POUND_FEET_PER_SECOND", "0.45359237 * 0.3048", null, "[S1] pound, foot");
        exact("MomentumUnits.GRAM_CENTIMETRE_PER_SECOND", "1E-5", null, SI);
        base("TorqueUnits.NEWTON_METER");
        exact("TorqueUnits.MILLINEWTON_METER", "1E-3", null, SI);
        exact("TorqueUnits.KILOPOND_METER", "9.80665", null, "[S1] kilogram-force meter 9.806 65 exact");
        exact("TorqueUnits.FOOT_POUND", "4.4482216152605 * 0.3048", "1.355818",
                "[S1] pound-force foot (lb in a torque is pound-force)");
        exact("TorqueUnits.INCH_POUND", "4.4482216152605 * 0.0254", "1.129848E-01", "[S1] pound-force inch");

        // --- Oscillation ---
        base("FrequencyUnits.HERTZ");
        exact("FrequencyUnits.KILOHERTZ", "1E3", null, SI);
        exact("FrequencyUnits.MEGAHERTZ", "1E6", null, SI);
        exact("FrequencyUnits.GIGAHERTZ", "1E9", null, SI);
        exact("FrequencyUnits.CYCLES_PER_MINUTE", "1 / 60", null, "[S1-T6] minute");

        // --- Thermodynamic ---
        base("AirFuelRatioMassUnits.KILOGRAM_PER_KILOGRAM");
        exact("AirFuelRatioMassUnits.GRAM_PER_GRAM", "1", null, UNIT_FREE);
        exact("AirFuelRatioMassUnits.POUND_PER_POUND", "1", null, UNIT_FREE);
        exact("AirFuelRatioMassUnits.OUNCE_PER_OUNCE", "1", null, UNIT_FREE);
        base("AirFuelRatioVolumeUnits.CUBIC_METER_PER_CUBIC_METER");
        exact("AirFuelRatioVolumeUnits.NORMAL_CUBIC_METER_PER_NORMAL_CUBIC_METER", "1", null, UNIT_FREE);
        exact("AirFuelRatioVolumeUnits.CUBIC_FOOT_PER_CUBIC_FOOT", "1", null, UNIT_FREE);
        exact("AirFuelRatioVolumeUnits.STANDARD_CUBIC_FOOT_PER_STANDARD_CUBIC_FOOT", "1", null, UNIT_FREE);

        base("CubicExpansionCoefficientUnits.INVERSE_KELVIN");
        exact("CubicExpansionCoefficientUnits.INVERSE_CELSIUS", "1", null, "[S1] °C interval = 1 K exact");
        exact("CubicExpansionCoefficientUnits.INVERSE_MILLIKELVIN", "1000", null, SI);
        exact("CubicExpansionCoefficientUnits.INVERSE_RANKINE", "1.8", null, "[S1] °R interval = K/1.8");
        exact("CubicExpansionCoefficientUnits.INVERSE_FAHRENHEIT", "1.8", null, "[S1] °F interval = K/1.8");

        base("DensityUnits.KILOGRAM_PER_CUBIC_METER");
        exact("DensityUnits.POUND_PER_CUBIC_FOOT", "0.45359237 / " + FT3, "1.601846E+01", "[S1] pound, foot");
        exact("DensityUnits.POUND_PER_CUBIC_INCH", "0.45359237 / " + IN3, "2.767990E+04", "[S1] pound, inch");
        exact("DensityUnits.POUND_PER_GALLON_US", "0.45359237 / " + US_GAL, "1.198264E+02",
                "[S1] pound; [S2] gallon");

        base("DynamicViscosityUnits.KILOGRAM_PER_METER_SECOND");
        exact("DynamicViscosityUnits.PASCAL_SECOND", "1", null, "[S0] Pa·s = kg/(m·s)");
        exact("DynamicViscosityUnits.POISE", "0.1", null, "[S1] poise 1.0 E-01 exact");

        base("EnergyDensityUnits.JOULE_PER_CUBIC_METER");
        exact("EnergyDensityUnits.KILOJOULE_PER_CUBIC_METER", "1E3", null, SI);
        exact("EnergyDensityUnits.MEGAJOULE_PER_CUBIC_METER", "1E6", null, SI);
        exact("EnergyDensityUnits.WATT_HOUR_PER_CUBIC_METER", "3600", null, "[S1] watt hour 3.6 E+03 exact");
        exact("EnergyDensityUnits.KILOWATT_HOUR_PER_CUBIC_METER", "3.6E6", null, "[S1] kilowatt hour exact");
        exact("EnergyDensityUnits.KILOCALORIE_PER_CUBIC_METER", "4186.8", null, "[S1] kilocalorie_IT exact");
        exact("EnergyDensityUnits.BTU_PER_CUBIC_FOOT", "1055.05585262 / " + FT3, "3.725895E+04",
                "[S1] fn 9 Btu_IT; foot");
        exact("EnergyDensityUnits.KILO_BTU_PER_CUBIC_FOOT", "1000 * 1055.05585262 / " + FT3, null,
                "[S1] fn 9 Btu_IT; foot; thousand Btu");

        base("EnergyUnits.JOULE");
        exact("EnergyUnits.MILLIJOULE", "1E-3", null, SI);
        exact("EnergyUnits.KILOJOULE", "1E3", null, SI);
        exact("EnergyUnits.MEGAJOULE", "1E6", null, SI);
        exact("EnergyUnits.BTU", "1055.05585262", null, "[S1] fn 9, Btu_IT = 1.055 055 852 62 kJ exactly");
        exact("EnergyUnits.CALORIE", "4.1868", null, "[S1] calorie_IT 4.1868 exact");
        exact("EnergyUnits.KILOCALORIE", "4186.8", null, "[S1] kilocalorie_IT 4.1868 E+03 exact");
        exact("EnergyUnits.WATT_HOUR", "3600", null, "[S1] watt hour 3.6 E+03 exact");
        exact("EnergyUnits.KILOWATT_HOUR", "3.6E6", null, "[S1] kilowatt hour 3.6 E+06 exact");

        base("HeatCapacityUnits.JOULES_PER_KELVIN");
        exact("HeatCapacityUnits.KILOJOULES_PER_KELVIN", "1E3", null, SI);
        exact("HeatCapacityUnits.BTU_PER_FAHRENHEIT", "1055.05585262 * 1.8", "1.899101E+03",
                "[S1] fn 9 Btu_IT; °F interval = K/1.8");

        base("HeatFluxUnits.WATTS_PER_SQUARE_METER");
        exact("HeatFluxUnits.KILOWATTS_PER_SQUARE_METER", "1E3", null, SI);
        exact("HeatFluxUnits.BTU_PER_HOUR_SQUARE_FOOT", "1055.05585262 / 3600 / (0.3048 * 0.3048)", "3.154591",
                "[S1] fn 9 Btu_IT; hour; foot");
        exact("HeatFluxUnits.BTU_PER_MINUTE_SQUARE_FOOT", "1055.05585262 / 60 / (0.3048 * 0.3048)", null,
                "[S1] fn 9 Btu_IT; minute; foot");

        base("HeatTransferCoefficientUnits.WATTS_PER_SQUARE_METER_KELVIN");
        exact("HeatTransferCoefficientUnits.KILOWATTS_PER_SQUARE_METER_KELVIN", "1E3", null, SI);
        exact("HeatTransferCoefficientUnits.BTU_PER_HOUR_SQUARE_FOOT_FAHRENHEIT",
                "1055.05585262 / 3600 / (0.3048 * 0.3048) * 1.8", "5.678263",
                "[S1] fn 9 Btu_IT; hour; foot; °F interval");
        exact("HeatTransferCoefficientUnits.BTU_PER_MINUTE_SQUARE_FOOT_FAHRENHEIT",
                "1055.05585262 / 60 / (0.3048 * 0.3048) * 1.8", null, "[S1] fn 9 Btu_IT; minute; foot; °F interval");

        base("IsentropicCompressibilityUnits.INVERSE_PASCAL");
        exact("IsentropicCompressibilityUnits.INVERSE_KILOPASCAL", "1E-3", null, SI);
        exact("IsentropicCompressibilityUnits.INVERSE_MEGAPASCAL", "1E-6", null, SI);
        exact("IsentropicCompressibilityUnits.INVERSE_BAR", "1E-5", null, "[S1-T9] bar");
        exact("IsentropicCompressibilityUnits.INVERSE_ATMOSPHERE", "1 / 101325", null, "[S1] atmosphere exact");
        exact("IsentropicCompressibilityUnits.INVERSE_PSI", "1 / " + PSI, null, "[S1] pound-force, inch");
        exact("IsentropicCompressibilityUnits.INVERSE_PSF", "1 / " + PSF, null, "[S1] pound-force, foot");

        base("IsothermalCompressibilityUnits.INVERSE_PASCAL");
        exact("IsothermalCompressibilityUnits.INVERSE_KILOPASCAL", "1E-3", null, SI);
        exact("IsothermalCompressibilityUnits.INVERSE_MEGAPASCAL", "1E-6", null, SI);
        exact("IsothermalCompressibilityUnits.INVERSE_BAR", "1E-5", null, "[S1-T9] bar");
        exact("IsothermalCompressibilityUnits.INVERSE_PSI", "1 / " + PSI, null, "[S1] pound-force, inch");
        exact("IsothermalCompressibilityUnits.INVERSE_ATMOSPHERE", "1 / 101325", null, "[S1] atmosphere exact");

        base("KinematicViscosityUnits.SQUARE_METER_PER_SECOND");
        exact("KinematicViscosityUnits.SQUARE_FOOT_PER_SECOND", "0.3048 * 0.3048", null,
                "[S1] square foot per second 9.290 304 E-02 exact");

        base("LinearHeatFluxUnits.WATTS_PER_METER");
        exact("LinearHeatFluxUnits.KILOWATTS_PER_METER", "1E3", null, SI);
        exact("LinearHeatFluxUnits.BTU_PER_HOUR_FOOT", "1055.05585262 / 3600 / 0.3048", "0.2930711 / 0.3048",
                "[S1] fn 9 Btu_IT, Btu_IT/h 2.930 711 E-01; hour; foot. Was 0.96132649 in 4.1.0");
        exact("LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT", "1055.05585262 / 60 / 0.3048", null,
                "[S1] fn 9 Btu_IT; minute; foot. Was 57.6795894 in 4.1.0");

        base("MolarEnthalpyUnits.JOULE_PER_MOLE");
        exact("MolarEnthalpyUnits.KILOJOULE_PER_MOLE", "1E3", null, SI);
        exact("MolarEnthalpyUnits.MEGAJOULE_PER_KILOMOLE", "1E3", null, SI);
        exact("MolarEnthalpyUnits.MILLIJOULE_PER_MILLIMOLE", "1", null, SI);
        exact("MolarEnthalpyUnits.BTU_PER_POUND_MOLE", "1055.05585262 / 453.59237", null, LBMOL + "[S1] Btu_IT");
        exact("MolarEnthalpyUnits.CALORIE_PER_MOLE", "4.1868", null, "[S1] calorie_IT exact");
        exact("MolarEnthalpyUnits.KILOCALORIE_PER_MOLE", "4186.8", null, "[S1] kilocalorie_IT exact");

        base("MolarEntropyUnits.JOULE_PER_MOLE_KELVIN");
        exact("MolarEntropyUnits.KILOJOULE_PER_MOLE_KELVIN", "1E3", null, SI);
        exact("MolarEntropyUnits.CALORIE_PER_MOLE_KELVIN", "4.1868", null, "[S1] calorie_IT exact");

        base("MolarMassUnits.KILOGRAM_PER_MOLE");
        exact("MolarMassUnits.GRAM_PER_MOLE", "1E-3", null, SI);
        exact("MolarMassUnits.KILOGRAM_PER_KILOMOLE", "1E-3", null, SI);
        exact("MolarMassUnits.MILLIGRAM_PER_MILLIMOLE", "1E-3", null, SI);
        exact("MolarMassUnits.POUND_PER_POUND_MOLE", "0.45359237 / 453.59237", null, LBMOL + "[S1] pound");
        exact("MolarMassUnits.OUNCE_PER_MOLE", "0.45359237 / 16", null, "[S2] 16 oz = 1 lb; [S1] pound");

        base("MolarVolumeUnits.CUBIC_METER_PER_MOLE");
        exact("MolarVolumeUnits.LITER_PER_MOLE", "1E-3", null, "[S1-T6] litre");
        exact("MolarVolumeUnits.CUBIC_DECIMETER_PER_MOLE", "1E-3", null, SI);
        exact("MolarVolumeUnits.CUBIC_CENTIMETER_PER_MOLE", "1E-6", null, SI);
        exact("MolarVolumeUnits.MILLILITER_PER_MOLE", "1E-6", null, "[S1-T6] litre; [SI] milli");
        exact("MolarVolumeUnits.CUBIC_FOOT_PER_POUND_MOLE", FT3 + " / 453.59237", null, LBMOL + "[S1] foot");
        exact("MolarVolumeUnits.CUBIC_INCH_PER_POUND_MOLE", IN3 + " / 453.59237", null, LBMOL + "[S1] inch");

        base("PowerUnits.WATT");
        exact("PowerUnits.KILOWATT", "1E3", null, SI);
        exact("PowerUnits.MEGAWATT", "1E6", null, SI);
        exact("PowerUnits.BTU_PER_HOUR", "1055.05585262 / 3600", "2.930711E-01", "[S1] fn 9 Btu_IT; hour");
        exact("PowerUnits.HORSE_POWER", "550 * 0.3048 * 4.4482216152605", "7.456999E+02",
                "[S1] horsepower (550 ft·lbf/s); foot; fn 23 pound-force");

        base("PressureCoefficientUnits.PASCAL_PER_KELVIN");
        exact("PressureCoefficientUnits.KILOPASCAL_PER_KELVIN", "1E3", null, SI);
        exact("PressureCoefficientUnits.MEGAPASCAL_PER_KELVIN", "1E6", null, SI);
        exact("PressureCoefficientUnits.BAR_PER_KELVIN", "1E5", null, "[S1-T9] bar");
        exact("PressureCoefficientUnits.ATMOSPHERE_PER_KELVIN", "101325", null, "[S1] atmosphere exact");
        exact("PressureCoefficientUnits.PSI_PER_RANKINE", PSI + " * 1.8", null, "[S1] psi; °R interval = K/1.8");
        exact("PressureCoefficientUnits.PSI_PER_FAHRENHEIT", PSI + " * 1.8", null, "[S1] psi; °F interval = K/1.8");

        base("PressureUnits.PASCAL");
        exact("PressureUnits.HECTOPASCAL", "100", null, SI);
        exact("PressureUnits.KILOPASCAL", "1E3", null, SI);
        exact("PressureUnits.MEGAPASCAL", "1E6", null, SI);
        exact("PressureUnits.BAR", "1E5", null, "[S1-T9] 1 bar = 10⁵ Pa");
        exact("PressureUnits.MILLIBAR", "100", null, "[S1-T9] bar; [SI] milli");
        exact("PressureUnits.TORR", "101325 / 760", "1.333224E+02", "[S1] torr = (101 325/760) Pa");
        exact("PressureUnits.PSI", PSI, "6.894757E+03", "[S1] fn 23 pound-force; inch");
        published("PressureUnits.METRE_OF_WATER_10", "999.70247 * 9.80665",
                "[S5] water 999.70247 kg/m³ at 10 °C, 1 atm; [S1] standard gravity. Was 999.5457 kg/m³ in 4.1.0");
        published("PressureUnits.METRE_OF_WATER_60", "983.19582 * 9.80665",
                "[S5] water 983.19582 kg/m³ at 60 °C, 1 atm; [S1] standard gravity. Was 982.6716 kg/m³ in 4.1.0");
        published("PressureUnits.METRE_OF_WATER_95", "961.88792 * 9.80665",
                "[S5] water 961.88792 kg/m³ at 95 °C, 1 atm; [S1] standard gravity. Was 961.2691 kg/m³ in 4.1.0");

        base("SpecificEnthalpyUnits.JOULE_PER_KILOGRAM");
        exact("SpecificEnthalpyUnits.KILOJOULE_PER_KILOGRAM", "1E3", null, SI);
        exact("SpecificEnthalpyUnits.BTU_PER_POUND", "2326", null, "[S1] Btu_IT per pound 2.326 E+03 exact");

        base("SpecificEntropyUnits.JOULE_PER_KILOGRAM_KELVIN");
        exact("SpecificEntropyUnits.KILOJOULE_PER_KILOGRAM_KELVIN", "1E3", null, SI);
        exact("SpecificEntropyUnits.MILLIJOULE_PER_GRAM_KELVIN", "1", null, SI);
        exact("SpecificEntropyUnits.MEGAJOULE_PER_TONNE_KELVIN", "1E3", null, "[SI] mega; [S1-T6] tonne");
        exact("SpecificEntropyUnits.BTU_PER_POUND_RANKINE", "4186.8", null,
                "[S1] Btu_IT per pound degree Rankine 4.1868 E+03 exact");
        exact("SpecificEntropyUnits.BTU_PER_POUND_FAHRENHEIT", "4186.8", null,
                "[S1] Btu_IT per pound degree Fahrenheit 4.1868 E+03 exact");

        base("SpecificGasConstantUnits.JOULE_PER_KILOGRAM_KELVIN");
        exact("SpecificGasConstantUnits.KILOJOULE_PER_KILOGRAM_KELVIN", "1E3", null, SI);
        exact("SpecificGasConstantUnits.BTU_PER_POUND_RANKINE", "4186.8", null,
                "[S1] Btu_IT per pound degree Rankine 4.1868 E+03 exact");
        exact("SpecificGasConstantUnits.BTU_PER_POUND_FAHRENHEIT", "4186.8", null,
                "[S1] Btu_IT per pound degree Fahrenheit 4.1868 E+03 exact");

        base("SpecificHeatUnits.JOULES_PER_KILOGRAM_KELVIN");
        exact("SpecificHeatUnits.KILOJOULES_PER_KILOGRAM_KELVIN", "1E3", null, SI);
        exact("SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT", "4186.8", null,
                "[S1] Btu_IT per pound degree Fahrenheit 4.1868 E+03 exact");

        base("SpecificInternalEnergyUnits.JOULE_PER_KILOGRAM");
        exact("SpecificInternalEnergyUnits.KILOJOULE_PER_KILOGRAM", "1E3", null, SI);
        exact("SpecificInternalEnergyUnits.MEGAJOULE_PER_KILOGRAM", "1E6", null, SI);
        exact("SpecificInternalEnergyUnits.BTU_PER_POUND", "2326", null, "[S1] Btu_IT per pound 2.326 E+03 exact");
        exact("SpecificInternalEnergyUnits.CALORIE_PER_GRAM", "4186.8", null, "[S1] calorie_IT per gram exact");

        base("SurfaceTensionUnits.NEWTON_PER_METER");
        exact("SurfaceTensionUnits.MILLINEWTON_PER_METER", "1E-3", null, SI);
        exact("SurfaceTensionUnits.DYNE_PER_CENTIMETER", "1E-5 / 0.01", null, "[S1] dyne exact; [SI] centi");
        exact("SurfaceTensionUnits.POUND_FORCE_PER_FOOT", "4.4482216152605 / 0.3048", "1.459390E+01",
                "[S1] fn 23 pound-force; foot");

        base("TemperatureUnits.KELVIN");
        affine("TemperatureUnits.CELSIUS", "1", "273.15", "[S1] T/K = t/°C + 273.15");
        affine("TemperatureUnits.FAHRENHEIT", "1 / 1.8", "459.67 / 1.8", "[S1] T/K = (t/°F + 459.67)/1.8");

        base("TemperatureDifferenceUnits.KELVIN");
        exact("TemperatureDifferenceUnits.CELSIUS", "1", null,
                "[S0] a degree Celsius interval IS a kelvin; only the degree size survives in a difference");
        exact("TemperatureDifferenceUnits.FAHRENHEIT", "1 / 1.8", null,
                "[S0] a degree Fahrenheit interval is 5/9 K, with no ice-point offset");
        exact("TemperatureDifferenceUnits.RANKINE", "1 / 1.8", null,
                "[S0] a rankine interval is the same size as a Fahrenheit one");

        base("ThermalConductanceUnits.WATTS_PER_KELVIN");
        exact("ThermalConductanceUnits.KILOWATTS_PER_KELVIN", "1E3", null, SI);
        exact("ThermalConductanceUnits.BTU_PER_HOUR_FAHRENHEIT", "1055.05585262 / 3600 * 1.8", null,
                "[S1] fn 9 Btu_IT; hour; °F interval");

        base("ThermalConductivityUnits.WATTS_PER_METER_KELVIN");
        exact("ThermalConductivityUnits.KILOWATTS_PER_METER_KELVIN", "1E3", null, SI);
        exact("ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT", "1055.05585262 / 3600 / 0.3048 * 1.8",
                "1.730735", "[S1] fn 9 Btu_IT; hour; foot; °F interval");

        base("ThermalDiffusivityUnits.SQUARE_METER_PER_SECOND");
        exact("ThermalDiffusivityUnits.SQUARE_FEET_PER_SECOND", "0.3048 * 0.3048", null,
                "[S1] square foot per second 9.290 304 E-02 exact");

        base("VapourQualityUnits.FRACTION");
        exact("VapourQualityUnits.PERCENT", "0.01", null, "[S0] 1 % = 0.01");

        ROWS = Collections.unmodifiableMap(rows);

        Map<String, String> unverified = new LinkedHashMap<>();
        unverified.put("PressureUnits.MILLIMETRE_OF_MERCURY_10",
                "no primary source for the density of mercury at 10 °C was read; SP 811 fn 12");
        unverified.put("PressureUnits.MILLIMETRE_OF_MERCURY_60",
                "no primary source for the density of mercury at 60 °C was read; SP 811 fn 12");
        unverified.put("PressureUnits.MILLIMETRE_OF_MERCURY_95",
                "no primary source for the density of mercury at 95 °C was read; SP 811 fn 12");
        unverified.put("SoundPowerUnits.DECIBEL",
                "reference power 1 pW not found in a primary source read; the 10 lg form is [S1-8.7]");
        UNVERIFIED_UNITS = Collections.unmodifiableMap(unverified);

        Map<String, String> notAUnit = new LinkedHashMap<>();
        notAUnit.put("VelocityUnits.MACH", "a Mach number is a ratio to a temperature-dependent speed of sound, "
                + "not a unit of velocity; deprecated in 4.2.0");
        NOT_A_UNIT = Collections.unmodifiableMap(notAUnit);
    }

    private GoldenTable() {
    }

    static Set<String> excludedKeys() {
        Set<String> keys = new TreeSet<>(UNVERIFIED_UNITS.keySet());
        keys.addAll(NOT_A_UNIT.keySet());
        return keys;
    }

    private static void base(String key) {
        add(new Row(key, Kind.EXACT, "1", null, null, null, BASE));
    }

    private static void exact(String key, String expression, String crossCheck, String source) {
        add(new Row(key, Kind.EXACT, expression, null, crossCheck, null, source));
    }

    private static void published(String key, String expression, String source) {
        add(new Row(key, Kind.PUBLISHED, expression, null, null, null, source));
    }

    private static void affine(String key, String scale, String offset, String source) {
        add(new Row(key, Kind.AFFINE, scale, offset, null, null, source));
    }

    private static void nonlinear(String key, DoubleUnaryOperator toBase, String source) {
        add(new Row(key, Kind.NONLINEAR, null, null, null, toBase, source));
    }

    private static void add(Row row) {
        if (rows.put(row.key(), row) != null) {
            throw new IllegalStateException("Duplicate golden row: " + row.key());
        }
    }

}
