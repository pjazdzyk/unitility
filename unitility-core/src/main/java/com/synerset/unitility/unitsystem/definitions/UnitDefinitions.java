package com.synerset.unitility.unitsystem.definitions;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The named, exact definitions every unit conversion factor in Unitility is built from, and the compound factors
 * derived from them.
 * <p>
 * <b>Rules.</b> A definition is typed once, here, with its source. A compound factor is an expression over the
 * definitions, never a literal: nobody types {@code 3.15459148} again. Each compound is evaluated once, at class
 * load, in {@link BigDecimal} at {@link MathContext#DECIMAL128} (34 significant digits), and only then rounded to the
 * nearest {@code double}, so that a factor is the correctly rounded value of its definition rather than the result
 * of a chain of {@code double} roundings. Every factor is the value, in the base unit of its quantity, of one of the
 * unit it names.
 * <p>
 * <b>Sources</b> (quoted in full in the Unitility exact-conversions plan, {@code 01_audit.md}):
 * <ul>
 *     <li>[SI] SI prefixes, exact by definition.</li>
 *     <li>[S1] NIST SP 811 (2008), Appendix B.8, where factors in boldface are exact.</li>
 *     <li>[S1-T6] NIST SP 811 Table 6, non-SI units accepted for use with the SI: 1 min = 60 s, 1 h = 3600 s,
 *     1 d = 86 400 s, 1° = (π/180) rad, 1 L = 10⁻³ m³, 1 ha = 10⁴ m², 1 t = 10³ kg.</li>
 *     <li>[S1-T9] NIST SP 811 Table 9: 1 nautical mile = 1852 m, 1 knot = (1852/3600) m/s, 1 bar = 10⁵ Pa.</li>
 *     <li>[S2] NIST Handbook 44 (2024), Appendix C: 1 gallon = 231 in³ = 8 pints = 128 fluid ounces, 1 pint =
 *     28.875 in³, 16 ounces = 1 pound, 1 acre = 43 560 ft², 1 mile = 5280 ft.</li>
 *     <li>[S5] NIST Chemistry WebBook, fluid properties of water (IAPWS-95), isobar 0.101325 MPa.</li>
 *     <li>[S6] "Cv and Kv Flow Coefficients" (a vendor document, secondary): Cv is US gpm of water per 1 psi drop,
 *     Kv is m³/h of water per 1 bar drop.</li>
 *     <li>[DEF-UNVERIFIED] A convention for which no primary source was read. Kept, and flagged.</li>
 *     <li>[UNVERIFIED] A property value for which no primary source was read. Kept unchanged, and flagged.</li>
 * </ul>
 * This class holds no state and is safe to use from any thread.
 */
public final class UnitDefinitions {

    private static final MathContext MC = MathContext.DECIMAL128;

    /** The exact definitions, in SI base units. Never exposed as {@code double} before they are combined. */
    private static final class Exact {

        private Exact() {
        }

        // --- Length ---
        /** International foot, [S1] foot 3.048 E-01 exact (1959). */
        static final BigDecimal FOOT = new BigDecimal("0.3048");
        /** [S1] inch 2.54 E-02 exact, which is FOOT / 12. */
        static final BigDecimal INCH = new BigDecimal("0.0254");
        /** [S1] yard 9.144 E-01 exact, which is 3 FOOT. */
        static final BigDecimal YARD = mul(new BigDecimal("3"), FOOT);
        /** [S2] mile = 5280 feet; [S1] mile 1.609 344 E+03 exact. */
        static final BigDecimal MILE = mul(new BigDecimal("5280"), FOOT);
        /** [S1-T9] 1 nautical mile = 1852 m. */
        static final BigDecimal NAUTICAL_MILE = new BigDecimal("1852");
        /** [DEF-UNVERIFIED] data mile = 6000 ft, a radar convention; no primary source was read. */
        static final BigDecimal DATAMILE = mul(new BigDecimal("6000"), FOOT);

        // --- Mass and amount ---
        /** [S1] footnote 22, avoirdupois pound = 0.453 592 37 kg exactly; also [S2]. */
        static final BigDecimal POUND = new BigDecimal("0.45359237");
        /** [S2] 16 ounces = 1 pound (avoirdupois). */
        static final BigDecimal OUNCE = div(POUND, new BigDecimal("16"));
        /** [DEF-UNVERIFIED] pound-mole = 453.59237 mol, found only on secondary sites. */
        static final BigDecimal POUND_MOLE = new BigDecimal("453.59237");

        // --- Time ---
        /** [S1-T6] 1 min = 60 s. */
        static final BigDecimal MINUTE = new BigDecimal("60");
        /** [S1-T6] 1 h = 3600 s. */
        static final BigDecimal HOUR = new BigDecimal("3600");
        /** [S1-T6] 1 d = 86 400 s. */
        static final BigDecimal DAY = new BigDecimal("86400");

        // --- Volume ---
        /** [S1-T6] 1 L = 10⁻³ m³. */
        static final BigDecimal LITRE = new BigDecimal("0.001");
        /** [S2] US gallon = 231 cubic inches exactly. */
        static final BigDecimal US_GALLON = mul(new BigDecimal("231"), INCH, INCH, INCH);
        /** [S2] 1 gallon = 8 pints; 1 pint = 28.875 in³. */
        static final BigDecimal US_PINT = div(US_GALLON, new BigDecimal("8"));
        /** [S2] 1 gallon = 128 fluid ounces. */
        static final BigDecimal US_FLUID_OUNCE = div(US_GALLON, new BigDecimal("128"));
        /** [S1] gallon [Canadian and U.K. (Imperial)] 4.546 09 E-03 exact. */
        static final BigDecimal UK_GALLON = new BigDecimal("0.00454609");
        static final BigDecimal CUBIC_FOOT = mul(FOOT, FOOT, FOOT);
        static final BigDecimal CUBIC_INCH = mul(INCH, INCH, INCH);

        // --- Force and pressure ---
        /** [S1] acceleration of free fall, standard, 9.806 65 m/s² exact. Also kilogram-force 9.806 65 N exact. */
        static final BigDecimal STANDARD_GRAVITY = new BigDecimal("9.80665");
        /** Pound-force = POUND × STANDARD_GRAVITY, [S1] footnote 23: 4.448 221 615 260 5 N exactly. */
        static final BigDecimal POUND_FORCE = mul(POUND, STANDARD_GRAVITY);
        /** Poundal = 1 lb·ft/s², [S1] poundal 1.382 550 E-01 N. */
        static final BigDecimal POUNDAL = mul(POUND, FOOT);
        /** [S1] dyne 1.0 E-05 N exact. */
        static final BigDecimal DYNE = new BigDecimal("1E-5");
        /** [S1] atmosphere, standard, 1.013 25 E+05 Pa exact. */
        static final BigDecimal ATMOSPHERE = new BigDecimal("101325");
        /** [S1-T9] 1 bar = 10⁵ Pa. */
        static final BigDecimal BAR = new BigDecimal("100000");
        /** [S1] torr = (101 325/760) Pa. */
        static final BigDecimal TORR = div(ATMOSPHERE, new BigDecimal("760"));
        /** Pound-force per square inch, [S1] psi 6.894 757 E+03 Pa. */
        static final BigDecimal PSI = div(POUND_FORCE, INCH, INCH);
        /** Pound-force per square foot, [S1] 4.788 026 E+01 Pa. */
        static final BigDecimal PSF = div(POUND_FORCE, FOOT, FOOT);
        /**
         * Conventional density of water, 1000 kg/m³: [S1] millimeter of water, conventional = 9.806 65 Pa exactly,
         * which is 1 mm × 1000 kg/m³ × STANDARD_GRAVITY.
         */
        static final BigDecimal CONVENTIONAL_WATER_DENSITY = new BigDecimal("1000");
        /** Inch of water, conventional; [S1] gives 2.490 889 E+02 Pa, which this agrees with at every digit. */
        static final BigDecimal INCH_OF_WATER_CONVENTIONAL = mul(INCH, CONVENTIONAL_WATER_DENSITY, STANDARD_GRAVITY);
        /**
         * Inch of mercury at 32 °F, a property-based unit: [S1] inch of mercury (32 °F) 3.386 38 E+03 Pa, all the
         * digits NIST publishes. [S1] footnote 12: manometer units at a stated temperature do not justify more.
         * It is not the conventional inch of mercury (3.386 389 E+03 Pa). 4.1.0 carried a 20-digit value
         * (3386.378 698...) with no source; this is the sourced one, 3.8e-7 higher (see CHANGELOG).
         */
        static final BigDecimal INCH_OF_MERCURY_32F = new BigDecimal("3386.38");

        // --- Energy ---
        /** [S1] footnote 9, International Table Btu = 1.055 055 852 62 kJ exactly. */
        static final BigDecimal BTU_IT = new BigDecimal("1055.05585262");
        /** [S1] calorie_IT 4.1868 J exact. */
        static final BigDecimal CALORIE_IT = new BigDecimal("4.1868");
        /** [S1] watt hour 3.6 E+03 J exact. */
        static final BigDecimal WATT_HOUR = HOUR;

        // --- Temperature ---
        /** [S1] T/K = t/°C + 273.15. */
        static final BigDecimal ICE_POINT = new BigDecimal("273.15");
        /** [S1] t/°C = (t/°F − 32)/1.8: a degree Fahrenheit (and Rankine) interval is 1/1.8 K. */
        static final BigDecimal FAHRENHEIT_DEGREE = div(BigDecimal.ONE, new BigDecimal("1.8"));
        /** [S1] T/K = (t/°F + 459.67)/1.8. */
        static final BigDecimal FAHRENHEIT_ZERO_IN_RANKINE = new BigDecimal("459.67");

        // --- Angle ---
        /**
         * π to the precision of {@link Math#PI} (about 1.2e-16 relative). It is a mathematical constant, not a
         * measured value, and a {@code double} factor cannot carry more.
         */
        static final BigDecimal PI = new BigDecimal(Math.PI);
        /** [S1-T6] 1° = (π/180) rad. */
        static final BigDecimal DEGREE = div(PI, new BigDecimal("180"));
        /** One revolution = 2π rad. */
        static final BigDecimal REVOLUTION = mul(new BigDecimal("2"), PI);

        // --- Power ---
        /** [S1] horsepower (550 ft·lbf/s), 7.456 999 E+02 W. */
        static final BigDecimal HORSEPOWER = mul(new BigDecimal("550"), FOOT, POUND_FORCE);

        // --- Property-based values ---
        /** [S5] NIST Chemistry WebBook, water at 10 °C, 0.101325 MPa: 999.70247 kg/m³. */
        static final BigDecimal WATER_DENSITY_10C = new BigDecimal("999.70247");
        /** [S5] NIST Chemistry WebBook, water at 60 °C, 0.101325 MPa: 983.19582 kg/m³. */
        static final BigDecimal WATER_DENSITY_60C = new BigDecimal("983.19582");
        /** [S5] NIST Chemistry WebBook, water at 95 °C, 0.101325 MPa: 961.88792 kg/m³. */
        static final BigDecimal WATER_DENSITY_95C = new BigDecimal("961.88792");
        /**
         * [UNVERIFIED] Mercury densities at 10, 60 and 95 °C, kg/m³, as shipped in 4.1.0 and unchanged. No primary
         * source for the density of mercury at these temperatures was read.
         */
        static final BigDecimal MERCURY_DENSITY_10C = new BigDecimal("13570");
        static final BigDecimal MERCURY_DENSITY_60C = new BigDecimal("13448");
        static final BigDecimal MERCURY_DENSITY_95C = new BigDecimal("13364");

        // --- Reference conditions of the normal and standard volumetric flows ---
        /** [DEF-UNVERIFIED] normal reference temperature 0 °C, as declared by the library. */
        static final BigDecimal NORMAL_TEMPERATURE = ICE_POINT;
        /** [DEF-UNVERIFIED] standard reference temperature 15 °C, as declared by the library. */
        static final BigDecimal STANDARD_TEMPERATURE = ICE_POINT.add(new BigDecimal("15"));
        /** [DEF-UNVERIFIED] standard cubic foot reference temperature 60 °F, in kelvin per [S1]. */
        static final BigDecimal STANDARD_CUBIC_FOOT_TEMPERATURE =
                mul(new BigDecimal("60").add(FAHRENHEIT_ZERO_IN_RANKINE), FAHRENHEIT_DEGREE);

        // --- Flow coefficient ---
        /**
         * Kv per Cv: with [S6] Cv = US gpm per √psi and Kv = m³/h per √bar, the same valve gives
         * Kv/Cv = (1 US gpm in m³/h) × √(1 bar / 1 psi) = 0.864 977 655... . [S6] rounds it to 0.865.
         */
        static final BigDecimal CV_IN_KV = mul(div(US_GALLON, MINUTE), HOUR, div(BAR, PSI).sqrt(MC));
    }

    private UnitDefinitions() {
        throw new IllegalStateException("Utility class");
    }

    // ================= Base definitions, as doubles =================

    public static final double FOOT = round(Exact.FOOT);
    public static final double INCH = round(Exact.INCH);
    public static final double YARD = round(Exact.YARD);
    public static final double MILE = round(Exact.MILE);
    public static final double NAUTICAL_MILE = round(Exact.NAUTICAL_MILE);
    /** [DEF-UNVERIFIED] 6000 ft. */
    public static final double DATAMILE = round(Exact.DATAMILE);
    public static final double POUND = round(Exact.POUND);
    public static final double OUNCE = round(Exact.OUNCE);
    public static final double MINUTE = round(Exact.MINUTE);
    public static final double HOUR = round(Exact.HOUR);
    public static final double DAY = round(Exact.DAY);
    public static final double LITRE = round(Exact.LITRE);
    public static final double US_GALLON = round(Exact.US_GALLON);
    public static final double US_PINT = round(Exact.US_PINT);
    public static final double US_FLUID_OUNCE = round(Exact.US_FLUID_OUNCE);
    public static final double UK_GALLON = round(Exact.UK_GALLON);
    public static final double CUBIC_FOOT = round(Exact.CUBIC_FOOT);
    public static final double SQUARE_FOOT = round(mul(Exact.FOOT, Exact.FOOT));
    public static final double SQUARE_INCH = round(mul(Exact.INCH, Exact.INCH));
    public static final double SQUARE_YARD = round(mul(Exact.YARD, Exact.YARD));
    public static final double SQUARE_MILE = round(mul(Exact.MILE, Exact.MILE));
    /** [S2] 1 acre = 43 560 ft². */
    public static final double ACRE = round(mul(new BigDecimal("43560"), Exact.FOOT, Exact.FOOT));
    public static final double STANDARD_GRAVITY = round(Exact.STANDARD_GRAVITY);
    /** Kilogram-force (kilopond), [S1] 9.806 65 N exact. */
    public static final double KILOGRAM_FORCE = round(Exact.STANDARD_GRAVITY);
    public static final double POUND_FORCE = round(Exact.POUND_FORCE);
    public static final double POUNDAL = round(Exact.POUNDAL);
    public static final double DYNE = round(Exact.DYNE);
    public static final double ATMOSPHERE = round(Exact.ATMOSPHERE);
    public static final double BAR = round(Exact.BAR);
    public static final double TORR = round(Exact.TORR);
    public static final double PSI = round(Exact.PSI);
    public static final double PSF = round(Exact.PSF);
    public static final double BTU_IT = round(Exact.BTU_IT);
    public static final double CALORIE_IT = round(Exact.CALORIE_IT);
    /** [S1] kilocalorie_IT 4.1868 E+03 J exact. */
    public static final double KILOCALORIE_IT = round(mul(new BigDecimal("1000"), Exact.CALORIE_IT));
    public static final double WATT_HOUR = round(Exact.WATT_HOUR);
    /** [S1] kilowatt hour 3.6 E+06 J exact. */
    public static final double KILOWATT_HOUR = round(mul(new BigDecimal("1000"), Exact.WATT_HOUR));
    public static final double ICE_POINT = round(Exact.ICE_POINT);
    /** One degree Fahrenheit (or Rankine) interval in kelvin, 1/1.8. */
    public static final double FAHRENHEIT_DEGREE = round(Exact.FAHRENHEIT_DEGREE);
    /** The Fahrenheit reading at the ice point, 32 °F ([S1] t/°C = (t/°F − 32)/1.8). */
    public static final double FAHRENHEIT_AT_ICE_POINT = 32.0;
    /** 1 K interval in degrees Fahrenheit (or Rankine), 1.8. */
    public static final double PER_FAHRENHEIT_DEGREE = round(div(BigDecimal.ONE, Exact.FAHRENHEIT_DEGREE));
    public static final double DEGREE = round(Exact.DEGREE);
    public static final double REVOLUTION = round(Exact.REVOLUTION);
    public static final double HORSEPOWER = round(Exact.HORSEPOWER);

    // ================= Data size (binary multiples, as declared by the library) =================

    /** A byte of 8 bits. */
    public static final double BIT = 0.125;
    /** 2^10 bytes. The symbol "KB" the library uses is the IEC "KiB" in SI terms; see DataSizeUnits. */
    public static final double KIBIBYTE = 1024.0;
    public static final double MEBIBYTE = round(new BigDecimal("1024").pow(2));
    public static final double GIBIBYTE = round(new BigDecimal("1024").pow(3));
    public static final double TEBIBYTE = round(new BigDecimal("1024").pow(4));
    public static final double PEBIBYTE = round(new BigDecimal("1024").pow(5));

    // ================= Compound factors =================

    // Angle and rotation
    public static final double REVOLUTION_PER_MINUTE = round(div(Exact.REVOLUTION, Exact.MINUTE));
    public static final double RADIAN_PER_FOOT = round(div(BigDecimal.ONE, Exact.FOOT));
    public static final double DEGREE_PER_FOOT = round(div(Exact.DEGREE, Exact.FOOT));
    public static final double DEGREE_PER_HUNDRED_FEET = round(div(Exact.DEGREE, mul(new BigDecimal("100"), Exact.FOOT)));
    /** (1 rev/min) per (1 US gal/min) = 2π / US gallon. */
    public static final double RPM_PER_GPM = round(div(Exact.REVOLUTION, Exact.US_GALLON));

    // Velocity
    public static final double KILOMETER_PER_HOUR = round(div(new BigDecimal("1000"), Exact.HOUR));
    public static final double FOOT_PER_MINUTE = round(div(Exact.FOOT, Exact.MINUTE));
    public static final double MILE_PER_HOUR = round(div(Exact.MILE, Exact.HOUR));
    /** [S1-T9] 1 knot = (1852/3600) m/s. */
    public static final double KNOT = round(div(Exact.NAUTICAL_MILE, Exact.HOUR));

    // Mass-based
    public static final double OUNCE_PER_FOOT = round(div(Exact.OUNCE, Exact.FOOT));
    public static final double POUND_PER_FOOT = round(div(Exact.POUND, Exact.FOOT));
    public static final double POUND_PER_CUBIC_FOOT = round(div(Exact.POUND, Exact.CUBIC_FOOT));
    public static final double POUND_PER_CUBIC_INCH = round(div(Exact.POUND, Exact.CUBIC_INCH));
    public static final double POUND_PER_US_GALLON = round(div(Exact.POUND, Exact.US_GALLON));
    public static final double CUBIC_FOOT_PER_POUND = round(div(Exact.CUBIC_FOOT, Exact.POUND));
    public static final double US_GALLON_PER_POUND = round(div(Exact.US_GALLON, Exact.POUND));
    public static final double UK_GALLON_PER_POUND = round(div(Exact.UK_GALLON, Exact.POUND));
    public static final double US_FLUID_OUNCE_PER_POUND = round(div(Exact.US_FLUID_OUNCE, Exact.POUND));
    public static final double POUND_PER_SQUARE_FOOT_SECOND = round(div(Exact.POUND, Exact.FOOT, Exact.FOOT));
    public static final double POUND_PER_SQUARE_FOOT_HOUR = round(div(Exact.POUND, Exact.FOOT, Exact.FOOT, Exact.HOUR));
    public static final double POUND_FOOT_PER_SECOND = round(mul(Exact.POUND, Exact.FOOT));
    /** lb/lbmol, [DEF-UNVERIFIED] pound-mole. */
    public static final double POUND_PER_POUND_MOLE = round(div(Exact.POUND, Exact.POUND_MOLE));
    /** ft³/lbmol, [DEF-UNVERIFIED] pound-mole. */
    public static final double CUBIC_FOOT_PER_POUND_MOLE = round(div(Exact.CUBIC_FOOT, Exact.POUND_MOLE));
    /** in³/lbmol, [DEF-UNVERIFIED] pound-mole. */
    public static final double CUBIC_INCH_PER_POUND_MOLE = round(div(Exact.CUBIC_INCH, Exact.POUND_MOLE));

    // Flow
    public static final double PER_MINUTE = round(div(BigDecimal.ONE, Exact.MINUTE));
    public static final double PER_HOUR = round(div(BigDecimal.ONE, Exact.HOUR));
    public static final double TONNE_PER_HOUR = round(div(new BigDecimal("1000"), Exact.HOUR));
    public static final double CUBIC_FOOT_PER_MINUTE = round(div(Exact.CUBIC_FOOT, Exact.MINUTE));
    public static final double LITRE_PER_MINUTE = round(div(Exact.LITRE, Exact.MINUTE));
    public static final double LITRE_PER_HOUR = round(div(Exact.LITRE, Exact.HOUR));
    public static final double US_GALLON_PER_MINUTE = round(div(Exact.US_GALLON, Exact.MINUTE));
    public static final double US_GALLON_PER_HOUR = round(div(Exact.US_GALLON, Exact.HOUR));
    public static final double UK_GALLON_PER_MINUTE = round(div(Exact.UK_GALLON, Exact.MINUTE));
    public static final double UK_GALLON_PER_HOUR = round(div(Exact.UK_GALLON, Exact.HOUR));
    /** Sm³/h in Nm³/s: (T_normal / T_standard) / hour, ideal gas at equal pressure. [DEF-UNVERIFIED] conditions. */
    public static final double STANDARD_CUBIC_METER_PER_HOUR =
            round(div(Exact.NORMAL_TEMPERATURE, Exact.STANDARD_TEMPERATURE, Exact.HOUR));
    /** slpm in Nm³/s. [DEF-UNVERIFIED] conditions. */
    public static final double STANDARD_LITRE_PER_MINUTE =
            round(div(mul(Exact.LITRE, Exact.NORMAL_TEMPERATURE), Exact.STANDARD_TEMPERATURE, Exact.MINUTE));
    /** scfm in Nm³/s. [DEF-UNVERIFIED] conditions (60 °F). */
    public static final double STANDARD_CUBIC_FOOT_PER_MINUTE =
            round(div(mul(Exact.CUBIC_FOOT, Exact.NORMAL_TEMPERATURE), Exact.STANDARD_CUBIC_FOOT_TEMPERATURE,
                    Exact.MINUTE));

    // Pressure and pressure-based
    public static final double PER_ATMOSPHERE = round(div(BigDecimal.ONE, Exact.ATMOSPHERE));
    public static final double PER_PSI = round(div(BigDecimal.ONE, Exact.PSI));
    public static final double PER_PSF = round(div(BigDecimal.ONE, Exact.PSF));
    public static final double PSI_PER_FAHRENHEIT_DEGREE = round(div(Exact.PSI, Exact.FAHRENHEIT_DEGREE));
    public static final double POUND_FORCE_PER_FOOT = round(div(Exact.POUND_FORCE, Exact.FOOT));
    public static final double POUND_FORCE_FOOT = round(mul(Exact.POUND_FORCE, Exact.FOOT));
    public static final double POUND_FORCE_INCH = round(mul(Exact.POUND_FORCE, Exact.INCH));
    /** [S5] water at 10 °C × standard gravity × 1 m. */
    public static final double METRE_OF_WATER_10C = round(mul(Exact.WATER_DENSITY_10C, Exact.STANDARD_GRAVITY));
    /** [S5] water at 60 °C × standard gravity × 1 m. */
    public static final double METRE_OF_WATER_60C = round(mul(Exact.WATER_DENSITY_60C, Exact.STANDARD_GRAVITY));
    /** [S5] water at 95 °C × standard gravity × 1 m. */
    public static final double METRE_OF_WATER_95C = round(mul(Exact.WATER_DENSITY_95C, Exact.STANDARD_GRAVITY));
    /** [UNVERIFIED] mercury at 10 °C × standard gravity × 1 mm. */
    public static final double MILLIMETRE_OF_MERCURY_10C =
            round(mul(Exact.MERCURY_DENSITY_10C, Exact.STANDARD_GRAVITY, new BigDecimal("0.001")));
    /** [UNVERIFIED] mercury at 60 °C × standard gravity × 1 mm. */
    public static final double MILLIMETRE_OF_MERCURY_60C =
            round(mul(Exact.MERCURY_DENSITY_60C, Exact.STANDARD_GRAVITY, new BigDecimal("0.001")));
    /** [UNVERIFIED] mercury at 95 °C × standard gravity × 1 mm. */
    public static final double MILLIMETRE_OF_MERCURY_95C =
            round(mul(Exact.MERCURY_DENSITY_95C, Exact.STANDARD_GRAVITY, new BigDecimal("0.001")));
    /** Conventional inch of water per 100 ft, in Pa/m. */
    public static final double INCH_OF_WATER_PER_100_FEET =
            round(div(Exact.INCH_OF_WATER_CONVENTIONAL, mul(new BigDecimal("100"), Exact.FOOT)));
    /** Inch of mercury at 32 °F per 100 ft, in Pa/m ([S1] 3386.38 Pa per inch, see INCH_OF_MERCURY_32F). */
    public static final double INCH_OF_MERCURY_PER_100_FEET =
            round(div(Exact.INCH_OF_MERCURY_32F, mul(new BigDecimal("100"), Exact.FOOT)));
    /** Kv per Cv, see [S6]. */
    public static final double CV_IN_KV = round(Exact.CV_IN_KV);

    // Energy-based
    public static final double BTU_PER_HOUR = round(div(Exact.BTU_IT, Exact.HOUR));
    public static final double BTU_PER_FAHRENHEIT_DEGREE = round(div(Exact.BTU_IT, Exact.FAHRENHEIT_DEGREE));
    public static final double BTU_PER_HOUR_FAHRENHEIT_DEGREE =
            round(div(Exact.BTU_IT, Exact.HOUR, Exact.FAHRENHEIT_DEGREE));
    public static final double BTU_PER_HOUR_FOOT = round(div(Exact.BTU_IT, Exact.HOUR, Exact.FOOT));
    public static final double BTU_PER_MINUTE_FOOT = round(div(Exact.BTU_IT, Exact.MINUTE, Exact.FOOT));
    public static final double BTU_PER_HOUR_SQUARE_FOOT = round(div(Exact.BTU_IT, Exact.HOUR, Exact.FOOT, Exact.FOOT));
    public static final double BTU_PER_MINUTE_SQUARE_FOOT =
            round(div(Exact.BTU_IT, Exact.MINUTE, Exact.FOOT, Exact.FOOT));
    public static final double BTU_PER_HOUR_SQUARE_FOOT_FAHRENHEIT_DEGREE =
            round(div(Exact.BTU_IT, Exact.HOUR, Exact.FOOT, Exact.FOOT, Exact.FAHRENHEIT_DEGREE));
    public static final double BTU_PER_MINUTE_SQUARE_FOOT_FAHRENHEIT_DEGREE =
            round(div(Exact.BTU_IT, Exact.MINUTE, Exact.FOOT, Exact.FOOT, Exact.FAHRENHEIT_DEGREE));
    public static final double BTU_PER_HOUR_FOOT_FAHRENHEIT_DEGREE =
            round(div(Exact.BTU_IT, Exact.HOUR, Exact.FOOT, Exact.FAHRENHEIT_DEGREE));
    public static final double BTU_PER_CUBIC_FOOT = round(div(Exact.BTU_IT, Exact.CUBIC_FOOT));
    public static final double KILO_BTU_PER_CUBIC_FOOT = round(div(mul(new BigDecimal("1000"), Exact.BTU_IT),
            Exact.CUBIC_FOOT));
    /** [S1] Btu_IT per pound, 2.326 E+03 J/kg exact. */
    public static final double BTU_PER_POUND = round(div(Exact.BTU_IT, Exact.POUND));
    /** [S1] Btu_IT per pound degree Fahrenheit (or Rankine), 4.1868 E+03 J/(kg·K) exact. */
    public static final double BTU_PER_POUND_FAHRENHEIT_DEGREE =
            round(div(Exact.BTU_IT, Exact.POUND, Exact.FAHRENHEIT_DEGREE));
    /** BTU/lbmol, [DEF-UNVERIFIED] pound-mole. */
    public static final double BTU_PER_POUND_MOLE = round(div(Exact.BTU_IT, Exact.POUND_MOLE));
    /** [S1] calorie_IT per gram 4.1868 E+03 J/kg exact. */
    public static final double CALORIE_PER_GRAM = round(div(Exact.CALORIE_IT, new BigDecimal("0.001")));

    // ================= Property-based, non-linear and deprecated =================

    /**
     * Reference sound pressure of the decibel, 20 µPa, as declared by the library. The 20 lg form is NIST SP 811
     * Sec. 8.7; this reference value was not verified against a primary source.
     */
    public static final double SOUND_PRESSURE_REFERENCE = 2E-5;
    /**
     * [UNVERIFIED] Reference sound power of the decibel, 1 pW, as declared by the library. The 10 lg form is NIST
     * SP 811 Sec. 8.7; this reference value was not found in a primary source.
     */
    public static final double SOUND_POWER_REFERENCE = 1E-12;
    /**
     * The speed of sound Unitility 4.1.0 used for its Mach "unit", 340.29 m/s. A speed of sound depends on the
     * temperature, so a Mach number is a ratio, not a unit of velocity. Kept only for the deprecated
     * {@code VelocityUnits.MACH}.
     */
    public static final double MACH_LEGACY_SPEED_OF_SOUND = 340.29;

    // ================= Evaluation =================

    private static BigDecimal mul(BigDecimal first, BigDecimal... others) {
        BigDecimal result = first;
        for (BigDecimal other : others) {
            result = result.multiply(other, MC);
        }
        return result;
    }

    private static BigDecimal div(BigDecimal numerator, BigDecimal... denominators) {
        BigDecimal result = numerator;
        for (BigDecimal denominator : denominators) {
            result = result.divide(denominator, MC);
        }
        return result;
    }

    /** The nearest {@code double} to an exact value ({@link Double#parseDouble} rounds correctly). */
    private static double round(BigDecimal exact) {
        return Double.parseDouble(exact.toString());
    }

}
