# UNITILITY - The Physics Unit Conversion Solution for Java

Introducing <strong>UNITILITY</strong> - the Java library designed to simplify physics units conversion.
With a wide range of value objects that represent commonly used physical quantities, this solution is built using plain
Java for optimal speed and lightweight functionality.
Whether you're looking to convert units within the same type or customize to meet your specific needs, UNITILITY offers
quick and easy usage in your project, without any heavy frameworks,
or external libraries.<p>
**<span style="color: green;">Thread-Safe Architecture:</span>** This library is developed to ensure thread safety,
allowing for concurrent access without
compromising data integrity through the utilization of immutable objects.

[![Unitility](https://img.shields.io/github/v/release/pjazdzyk/Unitility?label=Unitility&color=13ADF3&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI0LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)
&nbsp;
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.synerset/unitility/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.synerset/unitility)
&nbsp;
![Build And Test](https://github.com/pjazdzyk/unitility/actions/workflows/build-test-analyze.yml/badge.svg) <br>
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility)
&nbsp;
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility)
&nbsp;
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility)
&nbsp;
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=coverage)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility)
&nbsp;

> AUTHOR: <b>Piotr Jazdzyk</b> <br>
> LINKEDIN: https://www.linkedin.com/in/pjazdzyk <br>

### INSTALLATION

Just copy Maven dependency provided below to your pom.xml file, and you are ready to go. For other package managers,
check maven central repository:
[UNITILITY](https://search.maven.org/artifact/com.synerset/unitility/1.2.0/jar?eh=).

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility</artifactId>
    <version>1.2.0</version>
</dependency>
```

## Tech

<strong>Unitility</strong> is developed using following technologies: <br>

Core: <br>
![image](https://img.shields.io/badge/17-Java-orange?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) &nbsp;

Testing:<br>
![image](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) &nbsp;

CI/CD:<br>
![image](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)
&nbsp;
![image](https://img.shields.io/badge/Sonar%20cloud-F3702A?style=for-the-badge&logo=sonarcloud&logoColor=white) &nbsp;

## Examples & user guide

**Creating quantities:**<br>
Below is a simple example how to work with units and convert property from one unit to another or to convert as doubles
for further calculations:

```java
// Creating temperature instance of specified units
Temperature temperature = Temperature.ofCelsius(20.5);         // {20.50 °C}
// Getting converted value for calculations
double valueInCelsius = temperature.getInCelsius();            // 20.50 °C
double valueInKelvins = temperature.getInKelvins();            // 293.65 K
double valueInFahrenheits = temperature.getInFahrenheits();    // 68.90 °F
// Checking property current unit, value, and value in base unit
TemperatureUnits unit = temperature.getUnit();                 // CELSIUS
TemperatureUnits baseUnit = unit.getBaseUnit();                // KELVIN 
double valueInUnit = temperature.getValue();                   // 20.50 °C
double valueInBaseUnits = temperature.getBaseValue();          // 293.65 K
// Changing property unit and converting back to base unit
Temperature tempInFahrenheits = temperature.toUnit(TemperatureUnits.FAHRENHEIT); // {68.90 °F}
Temperature tempInKelvins = temperature.toBaseUnit();                            // {293.65 K}
```

All quantities have smart toFormattedString() method, which will always adjust values decimal precision to capture by
default specified relevant digits depending on your unit type and its value.
This way you have guaranteed an elegant output without any additional effort of reformatting. Values will be rounded up
using HALF_EVEN approach.

```java
Distance bigDistance=Distance.ofMeters(10);
        Distance smallDistance=Distance.ofMeters(0.000123678);
        String bigOutput=bigDistance.toFormattedString(3);      // outputs: 10.0 m
        String smallOutput=smallDistance.toFormattedString(3);  // outputs: 0.00124 m
```

For more advanced use cases, take a look at the example project that has been prepared to illustrate the integration of
this library into a simple dry air property physics app.
This project has been implemented in a functional manner using io.vavr
library: [unitility-example-project](https://github.com/pjazdzyk/unitility-example-project).

Since version **1.0.2** [Unitility](https://github.com/pjazdzyk/unitility) supports basic transformation and logic
operations.

**Transformations:**<br>
The choice of the developer determines whether calculations are performed using double values or with physical quantity
objects through the available transformation methods:

* adding or subtracting quantities of the same type:

```java
Temperature sourceTemperature = Temperature.ofCelsius(20);
Temperature temperatureToAdd = Temperature.ofKelvins(20 + 273.15);
Temperature actualTemperature = sourceTemperature.add(temperatureToAdd); // results in: 40 °C
```

Performing addition or subtraction will yield a PhysicalQuantity with the same unit. The algorithm will convert the unit
of the addend quantity to match that of the augend,
ensuring that the operation is conducted within a consistent unit. The unit of the resulting quantity will be set to
match that of the augend

* multiply or divide quantities by scalar:

```java
Temperature temperature = Temperature.ofCelsius(20);
Temperature actualTemperature = temperature.multiply(2); //results in 40 °C
```

* multiply or divide quantities by different quantity with result as double:

```java
Temperature sourceTemperature = Temperature.ofCelsius(20);
Pressure pressure = Pressure.ofPascal(2);
double actualDivideResult = sourceTemperature.divide(pressure); //results in 40 °C
```

In the given example, division and multiplication both yield a double value. This outcome arises from the current
absence of a unit resolver within the developmental stage.
The existing unit values are directly employed for multiplication or division operations.

**Logic operations:**<br>

* basic logic operations

```java
Temperature smallerTemp = Temperature.ofCelsius(-20.0);             
Temperature greaterTemp = Temperature.ofCelsius(0.0);               
Temperature smallerOrEqualTemp = Temperature.ofCelsius(-20.0);      
Temperature greaterOrEqualTemp = Temperature.ofCelsius(0.0);        
smallerTemp.isLowerThan(greaterTemp);                            // is true   
greaterTemp.isGreaterThan(smallerTemp);                          // is true
smallerTemp.isEqualOrLowerThan(smallerOrEqualTemp);              // is true
greaterTemp.isEqualOrGreaterThan(greaterOrEqualTemp);            // is true
```

Only quantities of the same type can be compared. When conducting a comparison, the algorithm will harmonize the target
unit with the source unit to ensure a valid comparison.

## Development status & collaboration

This is an early development stage.
I welcome other developers who are interested in physics and engineering to collaborate on this project.
As we are still in the early stages of development, any contributions or suggestions would be greatly appreciated.<br>
<strong>If you would like me to add any specific units for your field of science just let me know!</strong>

## Specification

At current level of development following units are available:

#### COMMON:

* Distance: meter [m], centimetre [cm], millimetre [mm], kilometre [km], mile [mi], feet [ft], inch [in]
* Area: square meter [m²], square kilometre [km²], square centimetre [cm²], square millimetre [mm²], are [a],
  hectare [ha], square inch [in²], square foot [ft²], square yard [yd²], acre [ac], square mile [mi²]
* Volume: cubic meter [m³], cubic centimetre [L], liter [L], hectolitre [hL], millilitre [mL], ounce [fl.oz], pint [pt],
  gallon [gal]
* Mass: kilogram [kg], gram [g], milligram [mg], tonne [t], ounce [oz], pound [lb]
* Angle: degrees [°], radians [rad]

#### MECHANICAL:

* Force: newton [N], kilonewton [kN], kilopond [kp], dyne [dyn], pound force [lbf], poundal [pdl]
* Momentum: Kilogram meter per second [kg·m/s], Gram centimeter per second [g·cm/s], Pound feet per second [lb·ft/s]
* Torque: Newton meter [N·m], Millinewton meter [mN·m], Kilopond meter [kp·m], Foot pound [ft·lb], Inch pound [in·lb]

#### THERMODYNAMIC:

* Temperature: Kelvin [K], Celsius [°C], Fahrenheit [°F]
* Pressure: Pascal [Pa], Hectopascal [hPa], Megapascal [MPa], Bar [bar], Milli Bar [mbar] PSI [psi], Torr [Torr]
* Energy: Joule [J], Millijoule [mJ], Kilojoule [kJ], Megajoule [MJ], BTU [BTU], Calorie [cal], Kilocalorie [kcal], Watt
  hour [Wh], Kilowatt hour [kWh]
* Power: Watt [W], Kilowatt [kW], Megawatt [MW], BTU/hour [BTU/h], Horse Power [HP]
* Specific heat: Joules per kilogram Kelvin [J/(kg·K)], Kilojoules per kilogram Kelvin [kJ/(kg·K)], BTU per pound
  Fahrenheit [BTU/(lb·°F)]
* Density: Kilogram per cubic meter [kg/m³], Pound per cubic foot [lb/ft³], Pounds per cubic inch [lb/in³]
* Dynamic viscosity: Kilogram per meter second [kg/(m·s)], Pascal second [Pa·s], Poise [P]
* Kinematic viscosity: Square meter per second [m²/s], Square foot per second [ft²/s]
* Specific enthalpy: Joule per kilogram [J/kg], Kilojoule per kilogram [kJ/kg], BTU per pound [BTU/lb]
* Thermal conductivity: Watts per meter Kelvin [W/(m·K)], Kilowatts per meter Kelvin [kW/(m·K)], BTU per hour foot
  Fahrenheit [BTU/(h·ft·°F)]
* Thermal diffusivity: Square meter per second [m²/s], Square feet per second [ft²/s]

#### FLOWS:

* Mass flow: Kilogram per second [kg/s], Kilogram per hour [kg/h], Tonne per hour [t/h], Pound per second [lb/s],
* Volumetric flow: Cubic meters per second [m³/s], Cubic meters per minute [m³/min], Cubic meters per hour [m³/h], Litre
  per second [L/s], Litre per minute [L/min], Litre per hour [L/h],
* Gallons per second [gal/s], Gallons per minute [gal/min], Gallons per hour [gal/h]

#### HUMID AIR SPECIFIC:

* Humidity ratio: Kilogram per kilogram [kg/kg], Pound per pound [lb/lb]
* Relative humidity: Percent [%], Decimal [-]

#### DIMENSIONLESS:

* Grashof number, Prandtl number, Reynolds number, Bypass factor

Each quantity consists most popular SI units and at least one Imperial unit.

## License

MIT LICENSE.<br>
This work is licensed under the terms of the MIT License with the additional requirement that proper attribution be
given
to the [Piotr Jażdżyk](https://www.linkedin.com/in/pjazdzyk) as the original author in all derivative works and
publications.

## Attribution

I have provided badges that you can include in your project to showcase your usage of our library: <br>

Small shield with referenced most recent version tag:<br>
[![Unitility](https://img.shields.io/github/v/release/pjazdzyk/Unitility?label=Unitility&color=13ADF3&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI0LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)

```markdown
[![Unitility](https://img.shields.io/github/v/release/pjazdzyk/Unitility?label=Unitility&color=13ADF3&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI0LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)
```

Tech shield with version tag for manual adjustment (you can indicate which version you actually use): <br>
[![Unitility](https://img.shields.io/badge/UNITILITY-v1.1.1-13ADF3?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI4LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)

```markdown
[![Unitility](https://img.shields.io/badge/UNITILITY-v1.1.1-13ADF3?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI4LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)
```

## Acknowledgments

Special thanks to Kret11, VeloxDigits, Olin44, and others for all discussions on architecture we had.<br>
I extend my heartfelt gratitude to the [Silesian University of Technology](https://www.polsl.pl/en/) for imparting
invaluable knowledge to me.<br>
Thanks to [Mathieu Soysal](https://github.com/MathieuSoysal) for
his [Maven central publisher](https://github.com/MathieuSoysal/Java-maven-library-publisher). <br>
Badges used in readme: [Shields.io](https://img.shields.io)
and [Badges 4 README.md](https://github.com/alexandresanlim/Badges4-README.md-Profile).