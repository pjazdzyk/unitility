package com.synerset.unitility.unitsystem;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // SI Prefix Factors
    public static final double PICO = 1e-12;
    public static final double NANO = 1e-9;
    public static final double MICRO = 1e-6;
    public static final double MILLI = 1e-3;
    public static final double CENTI = 1e-2;
    public static final double DECI = 1e-1;

    public static final double DECA = 1e1;
    public static final double HECTO = 1e2;
    public static final double KILO = 1e3;
    public static final double MEGA = 1e6;
    public static final double GIGA = 1e9;
    public static final double TERA = 1e12;

    // Physical constants
    public static final double GRAVITY_SI = 9.80665;

    // Canonical Time
    public static final double SECONDS_IN_MINUTE = 60;
    public static final double SECONDS_IN_HOUR = 3600;

}