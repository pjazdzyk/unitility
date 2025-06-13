package com.synerset.unitility.unitsystem.util;

public class Constants {

    //Scale Factors
    public static final double MILLI = 1e-3;
    public static final double CENTI = 0.01;
    public static final double DECI = 0.1;
    public static final double DECA = 10;
    public static final double HECTO = 100;
    public static final double KILO = 1e3;
    public static final double MEGA = 1e6;

    public static final double SQUARE_MILLI = 1e-6;
    public static final double SQUARE_CENTI = 1e-4;
    public static final double SQUARE_DECA = 100;
    public static final double SQUARE_HECTO = 1e4;
    public static final double SQUARE_KILO = 1e6;

    public static final double CUBIC_CENTI = 1e-6;
    public static final double CUBIC_DECI = 1e-3;

    // Canonical Time
    public static final double SECONDS_PER_MINUTE = 60;
    public static final double MINUTES_PER_HOUR = 60;
    public static final double SECONDS_PER_HOUR = 3600;

    // Canonical Angle
    public static final double RADIANS_PER_REVOLUTION = 2 * StrictMath.PI;
    public static final double DEGREES_PER_REVOLUTION = 360;
    // Derived Angle
    public static final double DEGREES_PER_RADIAN = DEGREES_PER_REVOLUTION / RADIANS_PER_REVOLUTION;

    // Canonical Length
    public static final double FEET_PER_YARD = 3;
    public static final double INCHES_PER_FOOT = 12;
    public static final double INCHES_PER_METER = 1 / 0.0254;
    public static final double CUBIC_INCHES_PER_GALLON_US = 231;
    public static final double FEET_PER_MILE = 5280;
    public static final double METERS_PER_NAUTICAL_MILE = 1852;

    // Derived Length
    public static final double METERS_PER_MILE = FEET_PER_MILE * INCHES_PER_FOOT / INCHES_PER_METER;
    public static final double MILES_PER_METER = 1/METERS_PER_MILE;
    public static final double FEET_PER_METER = INCHES_PER_METER / INCHES_PER_FOOT;
    public static final double YARDS_PER_METER = FEET_PER_METER / FEET_PER_YARD;

    // Canonical Area
    public static final double SQUARE_YARDS_PER_ACRE = 4840;
    // Derived Area
    public static final double SQUARE_YARD_PER_SQUARE_METER = YARDS_PER_METER * YARDS_PER_METER;
    public static final double SQUARE_FEET_PER_SQUARE_METER = FEET_PER_METER * FEET_PER_METER;
    public static final double SQUARE_INCHES_PER_SQUARE_METER = INCHES_PER_METER * INCHES_PER_METER;
    public static final double SQUARE_ACRES_PER_SQUARE_METER = SQUARE_YARD_PER_SQUARE_METER / SQUARE_YARDS_PER_ACRE;
    public static final double SQUARE_MILES_PER_SQUARE_METER = 1 / (METERS_PER_MILE * METERS_PER_MILE);

    // Canonical Volume
    public static final double LITERS_PER_CUBIC_METER = 1000;
    public static final double GALLONS_UK_PER_LITER = 1 / 4.54609;
    public static final double PINTS_PER_GALLON_UK = 8;
    public static final double QUARTS_PER_GALLON_UK = 4;
    public static final double OUNCE_PER_GALLON_UK = 160;

    // Derived Volume
    public static final double CUBIC_FEET_PER_CUBIC_METER = FEET_PER_METER * FEET_PER_METER * FEET_PER_METER;
    public static final double CUBIC_INCHES_PER_CUBIC_METER = INCHES_PER_METER * INCHES_PER_METER * INCHES_PER_METER;
    public static final double GALLONS_US_PER_CUBIC_METER = CUBIC_INCHES_PER_CUBIC_METER / CUBIC_INCHES_PER_GALLON_US;
    public static final double GALLONS_UK_PER_CUBIC_METER = LITERS_PER_CUBIC_METER * GALLONS_UK_PER_LITER;
    public static final double PINTS_PER_CUBIC_METER = GALLONS_UK_PER_CUBIC_METER * PINTS_PER_GALLON_UK;
    public static final double OUNCE_PER_CUBIC_METER = GALLONS_UK_PER_CUBIC_METER * OUNCE_PER_GALLON_UK;
    public static final double HECTOLITRES_PER_CUBIC_METER = LITERS_PER_CUBIC_METER / HECTO;
    public static final double MILLIITRES_PER_CUBIC_METER = LITERS_PER_CUBIC_METER / MILLI;

    // Canonical Mass
    public static final double KILOGRAMS_PER_POUND = 0.45359237;
    public static final double OUNCES_PER_POUND = 16;

    // Derived Mass
    public static final double POUNDS_PER_KILOGRAM = 1 / KILOGRAMS_PER_POUND;
    public static final double OUNCES_PER_KILOGRAM = POUNDS_PER_KILOGRAM * OUNCES_PER_POUND;

    // Derived Linear Mass
    public static final double POUND_PER_FOOT_FOR_KILOGRAM_PER_METER = POUNDS_PER_KILOGRAM / FEET_PER_METER;
    public static final double OUNCE_PER_FOOT_FOR_KILOGRAM_PER_METER = OUNCES_PER_KILOGRAM / FEET_PER_METER;

    // Canonical Temperature
    public static final double CELSIUS_KELVIN_OFFSET = 273.15;
    public static final double FAHRENHEIT_CELSIUS_OFFSET = 32;
    public static final double FAHRENHEIT_CELSIUS_RATIO = 9d / 5d;


    // Canonical Physical Constants
    public static final double GRAVITY_SI = 9.80665;
    public static final double GRAVITY_FEET_PER_SECOND_SQUARED = 32.1740;
    public static final double POUNDAL_PER_NEWTON_HISTORICAL = 0.138254954376;
    public static final double JOULES_PER_BRITISH_THERMAL_UNIT = 1055.05585262;

    // Derived Force
    public static final double POUND_FORCE_PER_NEWTON = KILOGRAMS_PER_POUND * GRAVITY_SI;
    // Floating point math makes using a canonical
    public static final double POUNDAL_PER_NEWTON = KILOGRAMS_PER_POUND * GRAVITY_SI / GRAVITY_FEET_PER_SECOND_SQUARED;

    // Derived Torque
    public static final double FOOT_POUND_FORCE_PER_NEWTON_METER = POUND_FORCE_PER_NEWTON / FEET_PER_METER;
    public static final double INCH_POUND_FORCE_PER_NEWTON_METER = POUND_FORCE_PER_NEWTON / INCHES_PER_METER;

    // Canonical Pressure
    public static final double PASCAL_PER_ATMOSPHERE = 101325;
    public static final double TORR_PER_ATMOSPHERE = 760;
    public static final double PASCAL_PER_TORR = PASCAL_PER_ATMOSPHERE / TORR_PER_ATMOSPHERE;
    public static final double DENSITY_WATER_10 = 999.5457000320766;
    public static final double DENSITY_WATER_60 = 982.6716124974598;
    public static final double DENSITY_WATER_95 = 961.269058775685;
    public static final double DENSITY_MERCURY_10 = 13570;
    public static final double DENSITY_MERCURY_60 = 13448;
    public static final double DENSITY_MERCURY_95 = 13364;
    // This is technically derived, but with floating point error, a static definition is more accurate
    public static final double PASCAL_PER_PSI = 6894.757293168;
    // public static final double PASCAL_PER_PSI = 1/(POUNDS_PER_KILOGRAM * FEET_PER_METER/ GRAVITY_FEET_PER_SECOND_SQUARED  / SQUARE_INCHES_PER_SQUARE_METER);

    // Canonical Linear Resistance
    public static final double PASCAL_PER_INCH_OF_WATER_PER_100_FEET = 8.16722;
    public static final double PASCAL_PER_INCH_OF_MERCURY_PER_100_FEET = 111.10166332193331;

    // Canonical Power
    // Horsepower 33,000 ft.lbf/min
    public static final double HORSEPOWER = 33000;
    public static final double IMPERIAL_HORSEPOWER_WATTS = 745.69987158227022;
    public static final double WATTS_PER_HORSEPOWER = HORSEPOWER / SECONDS_PER_MINUTE / FEET_PER_METER / POUNDS_PER_KILOGRAM * GRAVITY_SI;
    public static final double IMPERIAL_SPECIFIC_HEAT_RATIO = 4186.7999934703;
    // Derived Speed
    public static final double KNOT_FOR_METER_PER_SECOND = METERS_PER_NAUTICAL_MILE / SECONDS_PER_HOUR;
    // The speed of sound in dry air at a standard temperature of 15 °C (59 °F) at mean sea level is approximately 340.29 m/s
    public static final double MACH_METERS_PER_SECOND = 340.29;

}
