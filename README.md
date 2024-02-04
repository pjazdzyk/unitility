# UNITILITY - The Physics Unit Conversion Solution for Java

Introducing <strong>UNITILITY</strong> - the Java library designed to simplify physics units conversion.
With a wide range of value objects that represent commonly used physical quantities, this solution is built using plain
Java for optimal speed and lightweight functionality.
Whether you're looking to convert units within the same type or customize to meet your specific needs, UNITILITY offers
quick and easy usage in your project, without any heavy frameworks,
or external libraries.<p>
**<span style="color: green;"> - Thread-Safe Architecture:</span>** Developed to ensure thread safety,
allowing for concurrent access without compromising data integrity through the utilization of immutable objects. <br>
**<span style="color: teal;"> - Kotlin and Groovy friendly:</span>** Developed to take some advantages of Kotlin and Groovy 
features, such as overloaded operators.

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

> AUTHOR: **Piotr Jazdzyk**, MSc Eng <br>
> LINKEDIN: https://www.linkedin.com/in/pjazdzyk <br>

## TABLE OF CONTENTS
1. [Installation](#1-installation) <br>
2. [Tech & dependencies](#2-tech-and-dependencies) <br>
3. [Supported physical quantities and units](#3-supported-physical-quantities-and-units) <br>
4. [Usage and functionality](#4-usage-and-functionality) <br>
   4.1 [Working with physical quantities](#41-working-with-physical-quantities) <br>
   4.2 [Parsing quantities from string](#42-parsing-quantities-from-string) <br>
   4.3 [Logic operations](#43-logical-operations) <br>
   4.4 [Arithmetic operations](#44-arithmetic-operations) <br>
   4.5 [Equality and sorting](#45-equality-and-sorting) <br>
5. [Unitility extension modules](#5-unitility-extension-modules) <br>
   5.1 [Jackson serializers / deserializers](#51-jackson-serializers-and-deserializers) <br>
   5.2 [Spring](#52-spring-boot-module) <br>
   5.3 [Quarkus](#53-quarkus-module) <br>
6. [Creating custom units and quantities](#6-creating-custom-quantities) <br>
   6.1 [Custom unit](#61-custom-unit) <br>
   6.2 [Registering custom quantity in Spring](#62-custom-physical-quantity) <br>
   6.3 [Registering custom quantity in Quarkus](#63-registering-custom-quantities-in-spring) <br>
7. [Compatibility with other JVM languages](#7-compatibility-with-other-jvm-languages) <br>
   7.1 [Groovy](#71-groovy---using-overloaded-operators) <br>
   7.2 [Kotlin](#72-kotlin---using-overloaded-operators) <br>
8. [Special types: geographic](#8-special-types-geographic) <br>
   8.1 [Latitude, Longitude, GeoCoordinate](#81-geographic-latitude-longitude-and-geocoordinate) <br>
   8.2 [GeoDistance (Haversine equations)](#82-geodistance---spherical-distance-between-two-coordinates) <br>
   8.3 [Parsing geographic quantities and JSON structures](#83-parsing-geographic-quantities-and-json-structures) <br>
9. [Collaboration, attribution and citation](#9-collaboration-attribution-and-citation) <br>
10. [Acknowledgments](#10-acknowledgments) <br>

## 1. INSTALLATION

Copy the Maven dependency provided below to your pom.xml file, and you are ready to go. For other package managers,
check maven central repository:
[UNITILITY](https://search.maven.org/artifact/com.synerset/unitility/2.3.0/jar?eh=).

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-core</artifactId>
    <version>2.3.0</version>
</dependency>
```
If you use frameworks to develop web applications, it is recommended to use Unitility extension modules, 
to provide PhysicalQuantity instances serialization and deserialization including path and query parameters.<br>

Extension for the Spring Boot framework:

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-spring</artifactId>
    <version>2.3.0</version>
</dependency>
```
Extension for the Quarkus framework:
```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-quarkus</artifactId>
    <version>2.3.0</version>
</dependency>
```
Extensions include CORE module, so you don't have to put it separate in your pom.
See section on [Spring](#52-spring-boot-module) or [Quarkus](#53-quarkus-module) for better explanation.
Unitility has also provided **[BOM](https://github.com/pjazdzyk/unitility/blob/master/unitility-bom/pom.xml)** which can be used for dependency management.

## 2. TECH AND DEPENDENCIES

<strong>Unitility</strong> is developed using the following technologies: <br>

Core: <br>
![image](https://img.shields.io/badge/17-Java-orange?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) &nbsp;

Extension modules:<br>
![image](https://img.shields.io/badge/Quarkus-000000?style=for-the-badge&logo=quarkus) &nbsp;
![image](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) &nbsp;

CI/CD:<br>
![image](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)
&nbsp;
![image](https://img.shields.io/badge/Sonar%20cloud-F3702A?style=for-the-badge&logo=sonarcloud&logoColor=white) &nbsp;

The Key concept of the core module is to use plain Java, to minimize any issues with compatibility, conflicting
transitive dependencies or excessive dependency version management.

## 3. SUPPORTED PHYSICAL QUANTITIES AND UNITS

At the current level of development, the following units are listed below. Each quantity includes the most popular SI 
units and at least one Imperial unit.

#### COMMON:

* Distance: meter [m], centimetre [cm], millimetre [mm], kilometre [km], mile [mi], nautical mile [nmi], feet [ft], inch [in]
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
* Energy: Joule [J], Milli joule [mJ], Kilojoule [kJ], Megajoule [MJ], BTU [BTU], Calorie [cal], Kilocalorie [kcal], Watt
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

#### SPECIAL TYPES:
#### Geographic:

* Latitude: degrees [°], radians [rad]
* Longitude: degrees [°], radians [rad]
* GeoCoordinate: [latitude, longitude]
* GeoDistance: meter [m], kilometer [km], mile [mi], nautical mile [nmi] <br>

All Geographic quantities can be constructed from DMS format (degrees-minutes-seconds), for i.e.: 20°7'22.8"S.

## 4. USAGE AND FUNCTIONALITY

### 4.1 Working with physical quantities

PhysicalQuantity of specific **type** is an aggregate of **value** and **unit** with embedded converters. 
Unitility supports all logic operations and arithmetic transformations.
It was decided to use a double type for value as for most engineering purposes BigDecimal level of accuracy is not required.

**Creating quantities:**<br>
Below is a simple example of how to work with units and convert property from one unit to another or to convert as doubles
for further calculations:

```java
// Creating temperature instance of specified units
Temperature temperature = Temperature.ofCelsius(20.5);                  // {20.50 °C}
// Getting converted value for calculations
double valueInCelsius = temperature.getInCelsius();                     // 20.50 °C
double valueInKelvins = temperature.getInKelvins();                     // 293.65 K
double valueInFahrenheits = temperature.getInFahrenheits();             // 68.90 °F
// Checking property current unit, value, and value in base unit
TemperatureUnit unit = temperature.getUnitType();                       // CELSIUS
TemperatureUnit baseUnit = unit.getBaseUnit();                          // KELVIN 
double valueInUnit = temperature.getValue();                            // 20.50 °C
double valueInBaseUnits = temperature.getBaseValue();                   // 293.65 K
// Changing property unit and converting back to base unit
Temperature tempInFahrenheits = temperature.toUnit(TemperatureUnits.FAHRENHEIT);   // {68.90 °F}
Temperature tempInKelvins = temperature.toBaseUnit();                              // {293.65 K}
```

All quantities have smart toEngineeringFormat() method, which will always adjust values decimal precision to capture by
default specified relevant digits depending on your unit type and its value.
This way you have guaranteed an elegant output without any additional effort of reformatting. Values will be rounded up
using HALF_EVEN approach.

```java
Distance bigDistance = Distance.ofMeters(10);
Distance smallDistance = Distance.ofMeters(0.000123678);
String bigOutput = bigDistance.toEngineeringFormat(3);      // outputs: 10 [m]
String smallOutput = smallDistance.toEngineeringFormat(3);  // outputs: 0.000124 [m]
```
Curious how Unitility integrates with a real project?
Explore the [hvac-engine](https://github.com/pjazdzyk/hvac-engine) project, a comprehensive library tailored for
HVAC engineers, offering advanced capabilities for psychrometrics and thermodynamic analysis of humid air. 
This project served as the driving force behind the development of the library, showcasing its proficiency in addressing
the complex needs of HVAC professionals.

For a more specific example illustrating the integration of this library, take a look at the 
[unitility-example-project](https://github.com/pjazdzyk/unitility-example-project).
This project focuses on a dry air property physics app and demonstrates the library's functionality in a functional 
programming style using the io.vavr library.

### 4.2 Parsing quantities from string

The physical quantity can be instantiated from a string representing the commonly used engineering style of writing 
values with units: "{value}[{unit}]", for example, "20.5 [K]", but it will also accept input without square brackets.
To parse a valid string into a PhysicalQuantity, you need to obtain an instance of the parsing factory provided in the core module. 
The default parsing factory includes parsers for all supported physical quantities and their related units.

```java
// Create default parsing registry
PhysicalQuantityParsingFactory parsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
// Examples of string in engineering format with unit in square brackets
String k1 = "15.1 [W p mxK)]";
String k2 = "15.1 [W/(m.K)]";
String k3 = "   1 5 .  1 [   WpmK   ]";
String k4 = "15.1 W/mK";
String k5 = "15.1"; // This will be resolved to hard-coded default unit of W/mK
// All above strings are properly resolved to Thermal Conductivity, even partially malformed k3.
ThermalConductivity thermCond1 = parsingFactory.parse(ThermalConductivity.class, k1); // {15.1 W/(m·K)}
ThermalConductivity thermCond2 = parsingFactory.parse(ThermalConductivity.class, k2); // {15.1 W/(m·K)}
ThermalConductivity thermCond3 = parsingFactory.parse(ThermalConductivity.class, k3); // {15.1 W/(m·K)}
ThermalConductivity thermCond4 = parsingFactory.parse(ThermalConductivity.class, k4); // {15.1 W/(m·K)}
ThermalConductivity thermCond5 = parsingFactory.parse(ThermalConductivity.class, k5); // {15.1 W/(m·K)}
```

Parsers are designed to interpret the numeric part as the value and the content within square brackets as the unit
symbol. Parsers allow for a limited deviation in input style, as illustrated in the example below. The table presents 
alternative ways of expressing units in an input string:

| NAME              | DEF | ALT     | EXAMPLES           |
|-------------------|-----|---------|--------------------|
| decimal separator | .   | ,       | 20.1 or 20,1       |
| degrees symbol    | °   | o, deg  | 20°, 20o, 20deg    |
| multiplication    | ·   | x  or . | Pa·m, Pa x m, Pa.m | 
| division          | /   | p       | m/s, mps           |

Please note that this method of creating quantities is designed to be used for deserializers. <Br>
**In your code, you should create units in a programmatic way, not parsing from strings.**

IMPORTANT:
When parsing from string values should be provided using dot "." as decimal separator.<br>
DO NOT USE grouping separators. <br>
This will parse properly:<br>
```text
1000000.00 [Pa]
```
But this will not: <br>
```text
1,000,0000.00 [Pa]
```


### 4.3 Logical operations

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

### 4.4 Arithmetic operations


The developer's choice determines whether calculations are performed using double values or with physical quantity
objects through the available transformation methods:

* adding or subtracting quantities of the same type:

```java
Temperature sourceTemperature = Temperature.ofCelsius(20);
Temperature temperatureToAdd = Temperature.ofKelvins(20 + 273.15);
Temperature actualTemperature = sourceTemperature.add(temperatureToAdd); // results in: 40 °C
```


Performing addition or subtraction will yield a PhysicalQuantity with the same unit. The algorithm will convert the unit
of the addend quantity to match that of the augend, ensuring that the operation is conducted within a consistent unit.
The unit of the resulting quantity will be set to match that of the augend.

* multiply or divide quantities by scalar:

```java
Temperature temperature = Temperature.ofCelsius(20);
Temperature actualTemperature = temperature.multiply(2); // results in 40 °C
```

* multiply or divide quantities by different quantity with a result as double:

```java
Temperature sourceTemperature = Temperature.ofCelsius(20);
Pressure pressure = Pressure.ofPascal(2);
double actualDivideResult = sourceTemperature.divide(pressure); // results in 40 °C
```

In the provided example, division and multiplication both yield a double value. This outcome arises from the current
absence of a unit resolver within the developmental stage.
The existing unit values are directly employed for multiplication or division operations.

### 4.5 Equality and sorting

Each PhysicalQuantity class has implemented the equals and hashCode methods and implements the Comparable interface, 
including additional methods that might be helpful during development. <br>

---

**Equality condition:** PhysicalQuantity instances are equal, if they are of the same unit Type (i.e.: TemperatureUnit) and
their values converted to the BASE unit are equal. <br>

---

In other words, equality is set to represent equality in the physical context. By default, values stored as double 
types are compared directly with their full decimal digit precision. In some cases, you might need to assume equality 
for a specified level of accuracy beyond which you will consider quantities as equal in your business application. 
Please find examples below:

```java
// Basic equality
Temperature tempInC1 = Temperature.ofCelsius(20.0);
Temperature tempInC2 = Temperature.ofCelsius(20.0);
Temperature tempInK = Temperature.ofKelvins(20 + 273.15);
tempInC1.equals(tempInC2);  // true
tempInC1.equals(tempInK);   // true

// Equality with precision
Temperature manyDigits = Temperature.ofFahrenheit(21.1234567);
Temperature fewDigits = Temperature.ofFahrenheit(21.123);
manyDigits.equals(fewDigits);                               // false
manyDigits.equalsWithPrecision(fewDigits, 0.001);           // true
```
Precision has to be provided as commonly phrased "epsilon" which simply is a double value representing required decimal
accuracy.

**Sorting** is based on the same principle as equality, quantities are compared and sorted based on their physical context,
meaning that base unit and value in base unit are used to determine the order of elements.
```java
// Unsorted array of quantities
Temperature[] temperatures = {
    Temperature.ofCelsius(54),     // equiv. of: 327.15 K
    Temperature.ofKelvins(10),     // equiv. of: 10.00 K
    Temperature.ofCelsius(-20),    // equiv. of: 253.15 K
    Temperature.ofCelsius(100)     // equiv. of: 373.15 K
};
// Sorting quantities        
Arrays.sort(temperatures);  // sorted order: 10K, -20oC, 54oC, 100oC
```
Hashcode follows the same concept and is calculated for quantity base value and base unit.

## 5. UNITILITY EXTENSION MODULES

In most web applications, especially in microservice architecture, data is frequently exchanged between services.
PhysicalQuantity will have to be represented as a JSON structure in request/response objects or even as one string, 
often used as a request/query parameter or path param/variable. To address this, additional modules have been provided 
with ready serializers/deserializers and integration with the most popular frameworks.

---

**IMPORTANT:** <br>
**a)** JSON request body of PhysicalQuantity should follow the structure shown in the section below (5.1) <br>
**b)** Sending request via path param or query param, should be used i.e.: 20.0oC, without square brackets or special characters.<br>

---

Json request body example:
```json
{
    "airTemperature": {
      "value": 20.5,
      "unit": "°C"
    },
    "airPressure": {
      "value": 101325.0,
      "unit": "Pa"
    },
    "relativeHumidity": {
      "value": 55.0,
      "unit": "%"
    }
}
```
Using as path param:
```text
/api/v1/temperatures/20.5C
```
Make sure that quantities used in path variables or query parameters are **without** square brackets. Parsers are designed to
filter them out, but some recent versions of Tomcat server have issues with "[]" so it is better to avoid using them.
Other special characters can be easily replaced with simpler equivalents, as presented in the table in [section 4.2](#42-parsing-quantities-from-string).

## 5.1 Jackson serializers and deserializers
The Jackson library is utilized in many frameworks, enabling the serialization of objects into a JSON structure and
deserialization back to Java objects. To include this module in your project, use the following dependency:

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-jackson</artifactId>
    <version>2.3.0</version>
</dependency>
```
PhysicalQuantity JSON structure for valid serialization / deserialization has been defined as in the following example:
```json
{
  "value": 10.5,
  "unit": "oC"
}
```

The Jackson module provides a configured SimpleModule with registered StdSerializer and JsonDeserializer instances. 
Each PhysicalQuantity class has its own defined deserializer. Deserializers use PhysicalQuantityParsingFactory to
obtain the appropriate parser depending on the class type. This module is part of the Spring and Quarkus modules, 
therefore, it does not need to be added explicitly if framework extensions are included in the project.

## 5.2 Spring Boot module
Module tested for Spring Boot platform version: **3.2.2** <br>
Spring Boot module includes **unitility-jackson** and **unitility-core** modules, and it will automatically
create required beans through the autoconfiguration dependency injection mechanism. To use Spring extension module, 
add the following dependency:
```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-spring</artifactId>
    <version>2.3.0</version>
</dependency>
```
Adding Spring module to the project will automatically:
- register PhysicalQuantityJacksonModule in ObjectMapper bean (JSON serialization/deserialization)
- register Converter instances in WebMvcConfigurer bean (query/path params serialization/deserialization)

This configuration allows using PhysicalQuantity types in request / response objects and in query parameters
or path variables, as in the example below:
```java
@RestController
public class DefaultUnitsController {

    @GetMapping("/temperatures/{temperature}")
    public Temperature getTemperature(@PathVariable("temperature") Temperature temperature) {
        // Some code
        return temperature;
    }

    @PostMapping("/temperatures")
    public Temperature postTemperature(@RequestBody Temperature temperature) {
        // Some code
        return temperature;
    }
    
}
```
For special cases when custom unit is created, which is not a part of standard Unitiltiy package, additional configuration
steps must be carried out to ensure that custom unit is properly resolved from JSON or path/query params. For more details
see a section: [Registering custom quantity in Spring](#63-registering-custom-quantities-in-spring).<br>

## 5.3 Quarkus module
Module tested for Quarkus platform version: **3.5.1**<br>
Quarkus module includes **unitility-jackson** and **unitility-core** modules, and it will be automatically
discovered through Jandex index and will create required CDI beans. To use Quarkus extension module,
add following dependency:
```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-quarkus</artifactId>
    <version>2.3.0</version>
</dependency>
```
Adding Quarkus module to the project will automatically:
- register PhysicalQuantityJacksonModule in ObjectMapper using ObjectMapperCustomizer bean (JSON serialization/deserialization)
- register ParamConverter instances in Jakarta ParamConverterProvider bean (query/path params serialization/deserialization)

This configuration allows using PhysicalQuantity types in request / response objects and in query parameters
or path variables, as in the example below

```java
public class DefaultUnitsResource {

    @GET
    @Path("/temperatures/{temperature}")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature getTemperature(@PathParam("temperature") Temperature temperature) {
        // Some code
        return temperature;
    }

    @POST
    @Path("/temperatures")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature postTemperature(Temperature temperature) {
        // Some code
        return temperature;
    }

}
```
For special cases when custom unit is created, which is not a part of standard Unitiltiy package, additional configuration
steps must be carried out to ensure that custom unit is properly resolved from JSON or path/query params. For more details
see a section: [Registering custom quantity in Quarkus](#64-registering-custom-quantities-in-quarkus).

## 6. CREATING CUSTOM QUANTITIES
The Unitility includes a set of the most commonly used quantities and related units with an emphasis on thermodynamics. 
However, the framework foundation can be successfully used to define almost any unit from economy, biology, electronics, 
and even for logistics to represent the quantity of bottles in different sized packages. Sooner or later, a developer 
might face a case where he would like to add a new unit or quantity to the library. I will be including requested units on
a regular basis. If this is not urgent, please go to the [ISSUES](https://github.com/pjazdzyk/unitility/issues) page and 
let me know what is needed. If you can't wait, below are instructions on how to create a custom unit and also how to ensure 
that all your custom units/quantities are registered correctly in Spring or Quarkus.

### 6.1 Custom unit
If you need to extend standard unit definitions for a given quantity, the simplest way is to create a new unit
class extending the interface of the desired unit family. To present this, let's add a new unit of [Rankine](https://en.wikipedia.org/wiki/Rankine_scale)
degree to Temperature family. Create class or enum (my preference) and extend [TemperatureUnits](https://github.com/pjazdzyk/unitility/blob/master/unitility-core/src/main/java/com/synerset/unitility/unitsystem/thermodynamic/Temperature.java)
interface and implement required methods to convert from base unit to Rankine and vice versa.
To ensure valid conversion with standard Temperature unit family, set the same base unit type as in the
library default [TemperatureUnits](https://github.com/pjazdzyk/unitility/blob/master/unitility-core/src/main/java/com/synerset/unitility/unitsystem/thermodynamic/TemperatureUnits.java):
```java
@Override
public TemperatureUnit getBaseUnit() {
   return TemperatureUnits.KELVIN;
}
```
After this is done, you can use your own custom unit in Temperature class and convert between standard library units
and your own custom units:
```java
Temperature tempInC = Temperature.ofKelvins(100);                    // 100.00 K
Temperature tempInR = Temperature.of(200, CustomTempUnits.RANKINE);  // eq. of 111.111 K
Temperature totalTemp = temperatureInC.plus(temperatureInR);         // 211.111 K
```

### 6.2 Custom physical quantity
In this section, we create new CustomAngle quantity together with new CustomAngleUnit of **Revolutions [rev]**. 
To do this, we need to create a new class, CustomAngle, and extend the CalculableQuantity<AngleUnit, CustomAngle> interface.
CalculableQuantity is PhysicalQuantity with arithmetic operations handling.
In typical case, most of the required methods are defined as default in the interface, which should be sufficient for most cases. 
However, some needs to be implemented for the new unit, as shown in the example here:
[CustomAngle](https://github.com/pjazdzyk/unitility-spring-example/blob/master/src/main/java/com/synerset/unitility/spring/examples/newquantity/customunits/CustomAngle.java).

In the next step, create your implementation of AngleUnit interface, which should include the way, how to
convert one unit from another. Here is an example based on enum to introduce revolution unit: 
[CustomAngleUnits](https://github.com/pjazdzyk/unitility-spring-example/blob/master/src/main/java/com/synerset/unitility/spring/examples/newquantity/customunits/CustomAngleUnits.java).

After this is done, we can create and use our custom angles:
```java
// Creating instance of custom unit
CustomAngle revolutions = CustomAngle.ofRevolutions(1);         // CustomAngle{1.0 rev}
// Converting to any unit implementing AngleUnit type
CustomAngle degrees = revolutions.toUnit(AngleUnits.DEGREES);   // CustomAngle{360.0°}
// All transformations work withing the units implementing AngleUnit
CustomAngle resultingRevolutions = revolutions.plus(degrees);   // CustomAngle{2.0 rev}
```

### 6.3 Registering custom quantities in SPRING

After creating a custom unit, to ensure that it is properly resolved from JSON or path/query params, the following steps
must be taken to make it work. A complete example of a new custom unit properly registered in a Spring framework can be 
found here: [unitility-spring-example](https://github.com/pjazdzyk/unitility-spring-example).

As a first step, a new parsing factory has to be created, which must include currently supported quantities and custom
user quantities:
[CustomParsingFactory](https://github.com/pjazdzyk/unitility-spring-example/blob/master/src/main/java/com/synerset/unitility/spring/examples/newquantity/CustomParsingFactoryWithAngle.java).

After a new parsing factory is created and all standard and new custom quantities parsers are properly registered,
you can now create a configuration and register new JacksonModule and new Converter in FormatterRegistry: 
[CustomAngleConfiguration](https://github.com/pjazdzyk/unitility-spring-example/blob/master/src/main/java/com/synerset/unitility/spring/examples/newquantity/CustomAngleConfiguration.java).

After this step is done, you can freely use your CustomAngle unit in your web application:
```java
@RestController
public class CustomAngleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/angles/{angle}")
    public CustomAngle getCustomAnglePath(@PathVariable("angle") CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }

    @PostMapping("/angles")
    public CustomAngle getCustomAngleBody(@RequestBody CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }
}
```

### 6.4 Registering custom quantities in Quarkus
Registering custom unit in Quarkus is a bit different compared to Spring.
A Complete example of new custom unit properly registered in a Quarkus framework can be
found here: [unitility-quarkus-example](https://github.com/pjazdzyk/unitility-quarkus-example).
In this case, a first step is the same, new parsing factory must be created to include currently supported 
and new custom quantities created by user: [CustomParsingFactory](https://github.com/pjazdzyk/unitility-quarkus-example/blob/master/src/main/java/com/synerset/unitility/quarkus/examples/newquantity/CustomParsingFactoryWithAngle.java).

After a new parsing factory is created and all standard and new custom quantities parsers are properly registered,
you can now create a new ObjectMapperCustomizer and register JacksonModule with new parsing factory: 
[CustomObjectMapperCustomizer](https://github.com/pjazdzyk/unitility-quarkus-example/blob/master/src/main/java/com/synerset/unitility/quarkus/examples/newquantity/CustomAngleObjectMapperCustomizer.java).

The Last step is to define new PathParamConverterProvider and register ParamConverters for all custom quantities:
[CustomAngleParamProvider](https://github.com/pjazdzyk/unitility-quarkus-example/blob/master/src/main/java/com/synerset/unitility/quarkus/examples/newquantity/CustomAngleProvider.java).

After this step is done, you can freely use your CustomAngle unit in your web application:
```java
@RestController
public class CustomAngleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/angles/{angle}")
    public CustomAngle getCustomAnglePath(@PathVariable("angle") CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }

    @PostMapping("/angles")
    public CustomAngle getCustomAngleBody(@RequestBody CustomAngle customAngle) {
        processCustomAngle(customAngle);
        return customAngle;
    }
}
```
In Quarkus, IntellijJ might highlight PhysicalQuantity types when used as path or query parameters, but when you run the
application, they will be correctly resolved.

## 7. COMPATIBILITY WITH OTHER JVM LANGUAGES
Unitility can be easily used with other JVM-based programming languages. There are some features of these languages that 
make using Unitility easier and more elegant, for example, through the use of overloaded operators. I am more than happy 
to provide even greater integration with other JVM languages, please use [ISSUES](https://github.com/pjazdzyk/unitility/issues)
to leave there any suggestion in that regard.

### 7.1 Groovy - using overloaded operators
Please find the below example of Unitility usage in Groovy, taking advantage of overloaded operators:
```groovy
// Temperature examples
def t1 = Temperature.ofCelsius(20)
def t2 = Temperature.ofCelsius(10)
def t3 = Temperature.ofKelvins(303.15)  // =30 oC
// Adding & subtracting: The same unit
def t4 = t1 + t2                // Temperature{30.0°C}
def t5 = t1 - t2                // Temperature{10.0°C}
def t6 = t1 + 15.5              // Temperature{35.5°C}    
// Adding & subtracting: Different units of the same quantity,
// resolving to a first addend unit type.
def t7 = t1 + t3                // Temperature{50.0°C}
def t8 = t1 - t3                // Temperature{-10.0°C}
// Multiply
def t9 = t1 * t2                // will resolve to double = 200.0
def t10 = t1 * 2                // Temperature{40.0°C}
// Divide
def t11 = t1 / t2               // will resolve to double = 2.0
def t12 = t1 / 2                // Temperature{10.0°C}
// Logical operations
def isGreater = t1 > t2         // true
def isLower = t1 < t2           // false
def isGreaterOrEq = t1 >= t2    // true
def isEqual = t1 == t1          // true
```

### 7.2 Kotlin - using overloaded operators
Usage in Kotlin is analogous, please find the below some examples:
```kotlin
// Temperature examples
val t1 = Temperature.ofCelsius(20.0)
val t2 = Temperature.ofCelsius(10.0)
val t3 = Temperature.ofKelvins(303.15) // =30 oC

// Adding & subtracting: The same unit
val t4 = t1 + t2
val t5 = t1 - t2
val t6 = t1 + 15.5
println(t4) // Temperature{30.0°C}
println(t5) // Temperature{10.0°C}
println(t6) // Temperature{35.5°C}

// Adding & subtracting: Different units of the same quantity,
// resolving to a first addend unit type.
val t7 = t1 + t3
val t8 = t1 - t3
println(t7) // Temperature{50.0°C}
println(t8) // Temperature{-10.0°C}

// Multiply
val t9 = t1 * t2
val t10 = t1 * 2.0
println(t9) // will resolve to double = 200.0
println(t10) // Temperature{40.0°C}

// Divide
val t11 = t1 / t2
val t12 = t1 / 2.0
println(t11) // will resolve to double = 2.0
println(t12) // Temperature{10.0°C}

// Logical operations
val isGreater = t1 > t2         // true
val isLower = t1 < t2           // false
val isGreaterOrEq = t1 >= t2    // true
val isEqual = t1 == t1          // true
```

## 8. SPECIAL TYPES: GEOGRAPHIC
A set of dedicated types has been provided to cover spatial types used in expressing geographic measures: Latitude, 
Longitude, GeoCoordinate and GeoDistance. These classes allow representing coordinates on Earth and real curvature
distance between these coordinates.

### 8.1 Geographic Latitude, Longitude and GeoCoordinate
The **Latitude** class includes methods that allow for easy conversion to the Degrees-Minutes-Seconds 
(DMS) format. This format provides a more popular representation of geographic coordinates, making it convenient
for various applications where DMS notation is preferred. Latitude range is: -90 to 90 degrees. <br>
The **Longitude** class, analogous to the Latitude class, represents a geographic longitude coordinate. It adheres to the 
standard range of -180 to +180 degrees, covering the westernmost point at -180 degrees and the easternmost point 
at +180 degrees.
```java
// Latitude and Longitude types are based on Angular units
Latitude latitude = Latitude.ofDegrees(-20.123);
Longitude longitude = Longitude.ofDegrees(20.123);
// Both can be reduced to a string in DMS format or in ENG format:
String latInDMS = latitude.toDMSFormat(2);          // Outputs: 20°7'22.8"S
String latInENG = latitude.toEngineeringFormat();   // Outputs: -20.123 [°]
```
You can also create Latitude or Longitude instance providing degrees, minutes and seconds:
```java
// Instance from degrees, minutes, seconds
Latitude latFromDMS = Latitude.ofDegMinSec(20, 7, 22.8, CardinalDirection.SOUTH);   // Latitude{-20.123°}
Longitude longFromDMS = Longitude.ofDegMinSec(20, 7, 22.8, CardinalDirection.EAST); // Longitude{20.123°}
```

The **GeoCoordinate** class combines both Latitude and Longitude to form a complete geographic coordinate. It facilitates 
easy management and manipulation of spatial data, allowing seamless integration into various applications requiring
precise location information.

```java
// GeoCoordinate class represents a coordinate of specific point in the globe, using Latitude and Longitude and optional name
GeoCoordinate coordinateExample = GeoCoordinate.of(latitude, longitude, "my location");
// GeoCoordinate can be reduced to DMS format, ENG format, or decimal degrees format
// Decimal degrees format with coma separating latitude from longitude is for ie: how Google Maps output cords
String geoCoordDMS = coordinateExample.toDMSFormat();             // 20°7'22.8"S, 20°7'22.8"E
String geoCoordEND = coordinateExample.toEngineeringFormat();     // -20.12 [°], 20.12 [°]
String geoCoordDEC = coordinateExample.toDecimalDegrees();        // -20.12, 20.12
```

Latitude and Longitude do not enforce any angular value limit, but GeoCoordinate will do. Make sure that your
latitude and longitude values fall withing planet Earth's acceptable limits.

### 8.2 GeoDistance - spherical distance between two coordinates
The **GeoDistance** class represents the spherical distance between two coordinates on Earth, considering 
the curvature of the Earth. It incorporates calculations involving the start and target coordinates, true bearing, 
and distance in specified units. The underlying [Haversine equations](https://en.wikipedia.org/wiki/Haversine_formula) 
serve as the basis for the curved distance calculation. To create a GeoDistance object, you can initialize it with start
and target coordinates along with the desired unit type for representing the distance. See the example below:
```java
GeoCoordinate wroclaw = GeoCoordinate.of(Latitude.ofDegrees(51.102772), Longitude.ofDegrees(16.885802));
GeoCoordinate newYork = GeoCoordinate.of(Latitude.ofDegrees(40.712671), Longitude.ofDegrees(-74.004655));

GeoDistance geoDistance = GeoDistance.ofKilometers(wroclaw, newYork);

String distanceInEng = geoDistance.toEngineeringFormat();   // 6669.896095258197 [km]
Angle trueBearing = geoDistance.getTrueBearing();           // Angle{-61.07915625042435°}
```
Please note that true bearing is provided in range <-180,+180> degrees, and it is absolute value, to Earth's true north.
Alternatively, GeoDistance can be initialized with starting coordinate, bearing and distance in that case, target
coordinate will be calculated. Provided distance must be true, curved distance. 
```java
GeoDistance toNewYork = GeoDistance.of(wroclaw, trueBearing, Distance.ofKilometers(6669.896095258197));
String targetCoordinate = toNewYork.getTargetCoordinate().toDecimalDegrees();   // 40.712671, -74.004655
```
As you can see the results, we have set heading towards New York from Wrocław with previously calculated curved distance
between these two cities, and we have reached to exactly the same spot.<br>

**Progression and translation:** <br>
The GeoDistance class supports two methods to simulate distance change:<br>
a) methods named: with(), which accepts new target coordinate or bearing and distance, retaining current starting point
coordinate and calculates distance to the new coordinates (specified or calculated):
```java
GeoCoordinate wellington = GeoCoordinate.of(Latitude.ofDegrees(-41.289463), Longitude.ofDegrees(174.774913));

GeoDistance toWellington = toNewYork.with(wellington);

Distance distance = toWellington.getDistance();                                      // 18005.6198226 km
String wroclawCoordinate = toWellington.getStartCoordinate().toDecimalDegrees();     // 51.102772, 16.885802
String wellingtonCoordinate = toWellington.getTargetCoordinate().toDecimalDegrees(); // -41.289463, 174.774913
```
Previously, toNewYork represented the distance from Wrocław to New York. Following this change, it now represents the 
distance from Wrocław to Wellington, along with the updated bearing and calculated distance. <br>


b) Methods named translate() will now accept a new target coordinate or bearing and distance. They set the previous target 
coordinate as the current start coordinate and calculate the distance to the new coordinates, whether specified or calculated.
```java
GeoDistance toWellingtonBis = toNewYork.translate(wellington);

Distance distanceBis = toWellingtonBis.getDistance();                                      // 14403.6934729 km
String wroclawCoordinateBis = toWellingtonBis.getStartCoordinate().toDecimalDegrees();     // 40.712671, -74.004655
String wellingtonCoordinateBis = toWellingtonBis.getTargetCoordinate().toDecimalDegrees(); // -41.289463, 174.774913
```
Previously, toNewYork represented the distance from Wrocław to New York. After this change, it now represents the distance
from New York to Wellington, along with the updated bearing and calculated distance. <br>

### 8.3 Parsing geographic quantities and JSON structures
Parsers have been provided mostly for the purpose of deserialization in Spring Boot or Quarkus, but they can also be used directly 
in the code:
```java
hysicalQuantityParsingFactory geoParsingFactory = PhysicalQuantityParsingFactory.getDefaultParsingFactory();
Latitude parsedLat1 = geoParsingFactory.parse(Latitude.class, "20°7'22.8\"S");
Latitude parsedLat2 = geoParsingFactory.parse(Latitude.class, "20deg 7min 22.8sec");
```
Latitude, Longitude can be used as JSON request body or as value in path variable / query param. Path param usage example:
```text
Latitude path param usage example:
/routes/latitude/20°7'22.8"S/longitude/-14°7'12.4"W
/routes/latitude/20o7min22.8secS/longitude/-14o7min12.4secW
/routes/latitude/20.123deg/longitude/-14.123deg
/routes/latitude/20.123/longitude/-14.123
```

As you can observe, the deserializer behaves liberally, accepting input in various formats to accommodate users' 
preferences. Latitude, Longitude, GeoCoordinate, and GeoDistance each have their own JSON deserializers, enabling their 
use in request bodies. A couple of simple examples are provided below. <br>

Example: Latitude in DMS format as a JSON request body:
```json
{
  "value" : "20°7'22.80000000000399\"S"
}
```
Example: Latitude as a pair of value and unit symbol:
```json
{
  "value" : -20.123456,
  "unit": "deg"
}
```
Example: Latitude as single value, which will always be resolved to decimal degrees:
```json
{
   "value" : -20.123456
}
```
The **GeoCoordinate** JSON structure follows the same pattern.<br>
Example: GeoCoordinate defined by Latitude in DMS format and longitude as a value-unit pair:
```json
{
  "latitude" : {
      "value" : "20°7'22.80\"S"
    },
  "longitude" : {
      "value" : -14.1234,
      "unit": "deg"
    }
}
```
**GeoDistance** is serialized or deserialized from the exemplary structures as presented below.<br>

Example: GeoDistance - the base case, where both start and target coordinates are known:
```json
{
    "startCoordinate" : {
        "latitude": {
            "value": -20.123,
            "unit": "deg"
        },
        "longitude": {
            "value": 20.123,
            "unit": "°"
        },
        "name": "MyStartLoc"
    },
    "targetCoordinate" : {
        "latitude": {
            "value": -20.123,
            "unit": "°"
    },
    "longitude": {
        "value": 20.123,
        "unit": "°"
      },
      "name": "MyTargetLoc"
    }
}
```
Example: GeoDistance in a case where the start coordinate, true bearing, and distance to the target are known:
```json
{
    "startCoordinate" : {
        "latitude": {
            "value": -20.123,
            "unit": "deg"
        },
        "longitude": {
            "value": 20.123,
            "unit": "°"
        },
        "name": "MyStartLoc"
    }, 
    "trueBearing": {
        "value": -20.123,
        "unit": "deg"
    }, 
    "distance": {
        "value": 1000.0,
        "unit": "km"
    }
}
```
General note: <br>
- Latitude & Longitude: field "**value**" for Latitude and Longitude is mandatory, it can accept double value or a string in DMS or ENG format,
field "**unit**" is optional, it is omitted when value is provided as DMS or ENG format. In case of value provided as a 
double, unit will be automatically resolved to decimal degrees.
- GeoCoordinate: fields "**latitude**" and "**longitude**" are mandatory, "**name**" is optional.
- GeoDistance: field "**startCoordinate**" is mandatory. For variant with two coordinates "**targetCoordinate**" must be provided,
if not available the "**trueBearing**" and "**distance**" must be provided.

Arithmetic transformations: <br>
For Latitude, Longitude, and GeoDistance, arithmetic operations function in the same manner as for other PhysicalQuantities.
GeoCoordinate does not support arithmetic operations, as it is just a composite data objects used to construct GeoDistance.
It's important to note that the natural behavior is such that the result of arithmetic operations will be based on the with()
progression. This means that the added value will maintain the starting point, adjust the distance, and recalculate the 
target coordinate in the process. <br>

```java
// Sum of two GeoQuantities
GeoCoordinate start = GeoCoordinate.of(Latitude.ofDegrees(51.1), Longitude.ofDegrees(16.9));
GeoCoordinate target = GeoCoordinate.of(Latitude.ofDegrees(40.8), Longitude.ofDegrees(-74.1));

GeoDistance firstGeoDistance = GeoDistance.ofKilometers(start, target);         // 6670.048729447209 km
GeoDistance secondGeoDistance = firstGeoDistance.translate(Distance.ofKilometers(1000)); // 1000 km

GeoDistance sumOfDistances = firstGeoDistance.plus(secondGeoDistance);          // 7670.048729447209 km and new target
GeoDistance greaterDistance = sumOfDistances.plus(Distance.ofKilometers(1000)); // 8670.048729447209 km and new target
GeoDistance evenGreaterDistance = greaterDistance.plus(1000);                   // 9670.048729447209 km and new target
```

## 9. COLLABORATION, ATTRIBUTION, AND CITATION

I welcome other developers who are interested in physics and engineering to collaborate on this project.
Any contributions or suggestions would be greatly appreciated.<br>
Feel free to contact me if yoy have any questions or comments.

---

**If there are particular units relevant to your scientific field that you'd like me to include, please inform me.<br>**
**I aim for this library to be helpful and simplify your life.**

---

This work is licensed under the terms of the MIT License with the additional requirement that proper attribution be
given
to the [Piotr Jażdżyk](https://www.linkedin.com/in/pjazdzyk) as the original author in all derivative works and
publications.

For citation and attribution, I have provided badges that you can include in your project to showcase your usage
of the library: <br>

Small shield with referenced most recent version tag:<br>
[![Unitility](https://img.shields.io/github/v/release/pjazdzyk/Unitility?label=Unitility&color=13ADF3&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI0LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)

```markdown
[![Unitility](https://img.shields.io/github/v/release/pjazdzyk/Unitility?label=Unitility&color=13ADF3&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI0LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)
```

Tech shield with version tag for manual adjustment (you can indicate which version you actually use): <br>
[![Unitility](https://img.shields.io/badge/UNITILITY-v2.1.1-13ADF3?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI4LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)

```markdown
[![Unitility](https://img.shields.io/badge/UNITILITY-v2.1.1-13ADF3?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI4LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)
```

## 10. ACKNOWLEDGMENTS

Special thanks to Kret11, VeloxDigits, Olin44, and others for all discussions on architecture we had.<br>
I extend my heartfelt gratitude to the [Silesian University of Technology](https://www.polsl.pl/en/) for imparting
invaluable knowledge to me.<br>
Thanks to [Mathieu Soysal](https://github.com/MathieuSoysal) for
his [Maven central publisher](https://github.com/MathieuSoysal/Java-maven-library-publisher). <br>
Badges used in readme: [Shields.io](https://img.shields.io)
and [Badges 4 README.md](https://github.com/alexandresanlim/Badges4-README.md-Profile).

May the force be with you.
