# UNITILITY - The Physics Unit Conversion Solution for Java

Introducing <strong>UNITILITY</strong> - the Java library designed to simplify physics units conversion.
With a wide range of value objects that represent commonly used physical quantities, this solution is built using plain
Java for optimal speed and lightweight functionality.
Whether you're looking to convert units within the same type or customize to meet your specific needs, UNITILITY offers
quick and easy usage in your project, without any heavy frameworks,
or external libraries.<p>
**<span style="color: green;"> Thread-Safe Architecture:</span>** Developed to ensure thread safety,
allowing for concurrent access without compromising data integrity through the utilization of immutable objects. <br>
**<span style="color: teal;"> Kotlin and Groovy friendly:</span>** Developed to take some advantages of Kotlin and Groovy 
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
6. [Creating custom quantities](#6-creating-custom-quantities) <br>
   6.1 [Custom unit example](#61-custom-unit-example) <br>
   6.2 [Registering custom quantity in Spring](#62-registering-custom-units-in-spring) <br>
   6.3 [Registering custom quantity in Quarkus](#63-registering-custom-units-in-quarkus) <br>
7. [Compatibility with other JVM languages](#7-compatibility-with-other-jvm-languages) <br>
   7.1 [Groovy](#71-groovy---using-overloaded-operators) <br>
   7.2 [Kotlin](#72-kotlin---using-overloaded-operators) <br>
8. [Collaboration, attribution and citation](#8-collaboration-attribution-and-citation) <br>
9. [Acknowledgments](#9-acknowledgments) <br>

## 1. INSTALLATION

Copy the Maven dependency provided below to your pom.xml file, and you are ready to go. For other package managers,
check maven central repository:
[UNITILITY](https://search.maven.org/artifact/com.synerset/unitility/1.2.0/jar?eh=).

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-core</artifactId>
    <version>2.0.0</version>
</dependency>
```
If you use frameworks to develop web applications, it is recommended to use Unitility extension modules, 
to provide PhysicalQuantity instances serialization and deserialization including path and query parameters.<br>

Extension for the Spring Boot framework:

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-spring</artifactId>
    <version>2.0.0</version>
</dependency>
```
Extension for the Quarkus framework:
```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-quarkus</artifactId>
    <version>2.0.0</version>
</dependency>
```
Extensions include CORE module, so you don't have to put it separate in your pom.
See section on [Spring]() or [Quarkus]() for better explanation.
Unitility has also provided **[BOM](https://github.com/pjazdzyk/unitility/tree/master/unitility-bom)** to be used 
for <dependecyManagement> in Maven, for easier version management.

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

At the current level of development, the following units are available:

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
String bigOutput = bigDistance.toEngineeringFormat(3);      // outputs: 10[m]
String smallOutput = smallDistance.toEngineeringFormat(3);  // outputs: 0.000124[m]
```

For more advanced use cases, take a look at the example project that has been prepared to illustrate the integration of
this library into a simple dry air property physics app.
This project has been implemented in a functional manner using io.vavr
library: [unitility-example-project](https://github.com/pjazdzyk/unitility-example-project). <br>
Also, check out the [hvac-engine](https://github.com/pjazdzyk/hvac-engine), another project of mine, for which this
library was developed. If you are interested in thermodynamics of humid air, the hvac-engine is will be a great tool for you.

### 4.2 Parsing quantities from string

The physical quantity can be instantiated from a string representing the commonly used engineering style of writing 
values with units: "{value}[{unit}]", for example, "20.5 [K]". To parse a valid string into a PhysicalQuantity, you need 
to obtain an instance of the parsing registry provided in the core module. The default parsing registry includes parsers 
for all supported physical quantities and their related units.

```java
// Create default parsing registry
PhysicalQuantityParsingRegistry parsingRegistry = PhysicalQuantityParsingRegistry.DEFAULT_PARSING_REGISTRY;
// Examples of string in engineering format with unit in square brackets
String k1 = "15.1 [W p mxK)]";
String k2 = "15.1 [W/(m.K)]";
String k3 = "   1 5 ,  1 [   WpmK   ]";
// All above strings are properly resolved to Thermal Conductivity, even partially malformed k3.
ThermalConductivity thermCond1 = parsingRegistry.fromEngFormat(ThermalConductivity.class, k1); // {15.1 W/(m·K)}
ThermalConductivity thermCond2 = parsingRegistry.fromEngFormat(ThermalConductivity.class, k2); // {15.1 W/(m·K)}
ThermalConductivity thermCond3 = parsingRegistry.fromEngFormat(ThermalConductivity.class, k3); // {15.1 W/(m·K)}
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
In your code, you should create units in a programmatic way, not parsing from strings.

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
Temperature actualTemperature = temperature.multiply(2); //results in 40 °C
```

* multiply or divide quantities by different quantity with a result as double:

```java
Temperature sourceTemperature = Temperature.ofCelsius(20);
Pressure pressure = Pressure.ofPascal(2);
double actualDivideResult = sourceTemperature.divide(pressure); //results in 40 °C
```

In the provided example, division and multiplication both yield a double value. This outcome arises from the current
absence of a unit resolver within the developmental stage.
The existing unit values are directly employed for multiplication or division operations.

### 4.5 Equality and sorting
Each PhysicalQuantity class has implemented the equals and hashCode methods and implements the Comparable interface, 
including additional methods that might be helpful during development. <br>
---
**Equality condition: PhysicalQuantity instances are equal, if they are of the same unit Type (i.e.: TemperatureUnit) and
their values converted to the BASE unit are equal.** <br>
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
**b)** Sending request via path param or query param, **engineering format** should be used i.e.: 20.0[oC]<br>

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
http://localhost:8080/api/v1/temperatures/20.5[oC]
```


## 5.1 Jackson serializers and deserializers
The Jackson library is utilized in many frameworks, enabling the serialization of objects into a JSON structure and
deserialization back to Java objects. To include this module in your project, use the following dependency:

```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-jackson</artifactId>
    <version>2.0.0</version>
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
Each PhysicalQuantity class has its own defined deserializer. Deserializers use PhysicalQuantityParsingRegistry to
obtain the appropriate parser depending on the class type. This module is part of the Spring and Quarkus modules, 
therefore, it does not need to be added explicitly if framework extensions are included in the project.

## 5.2 Spring Boot module
Module tested for Spring Boot platform version: **3.1.5** <br>
Spring Boot module includes **unitility-jackson** and **unitility-core** modules, and it will automatically
create required beans through the autoconfiguration dependency injection mechanism. To use Spring extension module, 
add the following dependency:
```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-spring</artifactId>
    <version>2.0.0</version>
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
    public Temperature getCustomAnglePathVariable(@PathVariable("temperature") Temperature temperature) {
        // Some code
        return temperature;
    }

    @PostMapping("/temperatures")
    public Temperature getCustomAngleRequestBody(@RequestBody Temperature temperature) {
        // Some code
        return temperature;
    }
    
}
```
For special cases when custom unit is created, which is not a part of standard Unitiltiy package, additional configuration
steps must be carried out to ensure that custom unit is properly resolved from JSON or path/query params. For more details
see a section: [Registering custom quantity in Spring](#62-registering-custom-units-in-spring).<br>

## 5.3 Quarkus module
Module tested for Quarkus platform version: **3.5.1**<br>
Quarkus module includes **unitility-jackson** and **unitility-core** modules, and it will be automatically
discovered through Jandex index and will create required CDI beans. To use Quarkus extension module,
add following dependency:
```xml
<dependency>
    <groupId>com.synerset</groupId>
    <artifactId>unitility-quarkus</artifactId>
    <version>2.0.0</version>
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
    public Temperature getCustomAnglePathVariable(@PathParam("temperature") Temperature temperature) {
        // Some code
        return temperature;
    }

    @POST
    @Path("/temperatures")
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature getCustomAngleRequestBody(Temperature temperature) {
        // Some code
        return temperature;
    }

}
```
For special cases when custom unit is created, which is not a part of standard Unitiltiy package, additional configuration
steps must be carried out to ensure that custom unit is properly resolved from JSON or path/query params. For more details
see a section: [Registering custom quantity in Quarkus](#63-registering-custom-units-in-quarkus).

## 6. Creating custom quantities
The Unitility includes a set of the most commonly used quantities and related units with an emphasis on thermodynamics. 
However, the framework foundation can be successfully used to define almost any unit from economic sciences, biology, or 
logistics, for example, the quantity of bottles in a package. Sooner or later, a developer might face a case where they 
would like to add a new unit to the set. In case this is not urgent, please go to the [ISSUES](https://github.com/pjazdzyk/unitility/issues)
page and let me know what is needed. I will try to update the package and release a new version as soon as possible. 
If you can't wait, below are instructions on how to create a custom unit and also how to ensure that all your custom 
units are registered correctly in Spring or Quarkus.

### 6.1 Custom unit example

In this section, we create our own custom Angle unit, which will include a new AngleUnit type: revolutions [rev]. 
To do this, we need to create a new class, CustomAngle, and extend the PhysicalQuantity<AngleUnit> interface. Most of 
the required methods are defined as default in the interface, which should be sufficient for most cases. However, a few 
need to be implemented for the new unit, as shown in the example here:
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

### 6.2 Registering custom units in SPRING

After creating a custom unit, to ensure that it is properly resolved from JSON or path/query params, the following steps
must be taken to make it work. A complete example of a new custom unit properly registered in a Spring framework can be 
found here: [unitility-spring-example](https://github.com/pjazdzyk/unitility-spring-example).

As a first step, a new parsing registry has to be created, which must include currently supported quantities and custom
user quantities:
[CustomParsingRegistry](https://github.com/pjazdzyk/unitility-spring-example/blob/master/src/main/java/com/synerset/unitility/spring/examples/newquantity/CustomParsingRegistryWithAngle.java).

After a new parsing registry is created and all standard and new custom quantities parsers are properly registered,
you can now create a configuration and register new JacksonModule and new Conveter in FormatterRegistry: 
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

### 6.3 Registering custom units in Quarkus
Registering custom unit in Quarkus is a bit different compared to Spring.
A Complete example of new custom unit properly registered in a Quarkus framework can be
found here: [unitility-quarkus-example](https://github.com/pjazdzyk/unitility-quarkus-example).
In this case, a first step is the same, new parsing registry must be created to include currently supported 
and new custom quantities created by user: [CustomParsingRegistry](https://github.com/pjazdzyk/unitility-quarkus-example/blob/master/src/main/java/com/synerset/unitility/quarkus/examples/newquantity/CustomParsingRegistryWithAngle.java).

After a new parsing registry is created and all standard and new custom quantities parsers are properly registered,
you can now create a new ObjectMapperCustomizer and register JacksonModule with new parsing registry: 
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

## 7. Compatibility with other JVM languages
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
// Adding & subtracting: Different units of the same quantity, resolving to unit type of the first addend.
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
Usage in Kotlin is very similar, however, using overloaded operators will work only if both sides are instances
of PhysicalQuantity. Adding PhysicalQuantity instance to a double does not seem to be supported in Kotlin. If you
know how to resolve it, do not hesitate to contact me. Please find the below examples in Kotlin:
```kotlin
// Temperature examples
val t1 = Temperature.ofCelsius(20.0)
val t2 = Temperature.ofCelsius(10.0)
val t3 = Temperature.ofKelvins(303.15) // =30 oC
// Adding & subtracting: The same unit
val t4 = t1 + t2                // Temperature{30.0°C}
val t5 = t1 - t2                // Temperature{10.0°C} 
// Adding & subtracting: Different units of the same quantity, resolving to a unit type of the first addend.
val t7 = t1 + t3                // Temperature{50.0°C}
val t8 = t1 - t3                // Temperature{-10.0°C}
// Multiply
val t9 = t1 * t2                // will resolve to double = 200.0
// Divide
val t11 = t1 / t2               // will resolve to double = 2.0
// Logical
val isGreater = t1 > t2         // true
val isLower = t1 < t2           // false    
val isGreaterOrEq = t1 >= t2    // true    
val isEqual = t1 == t1          // true
```

## 8. Collaboration, attribution and citation

This is an early development stage.
I welcome other developers who are interested in physics and engineering to collaborate on this project.
As we are still in the early stages of development, any contributions or suggestions would be greatly appreciated.<br>

---

**If you would like me to add any specific units for your field of science, let me know!**

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
[![Unitility](https://img.shields.io/badge/UNITILITY-v1.1.1-13ADF3?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI4LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)

```markdown
[![Unitility](https://img.shields.io/badge/UNITILITY-v1.1.1-13ADF3?style=for-the-badge&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMi41bW0iIGhlaWdodD0iMTQuNW1tIiB2aWV3Qm94PSIwIDAgMjI1MCAxNDUwIj4NCiAgPHBvbHlnb24gZmlsbD0iIzUwN0QxNCIgcG9pbnRzPSIyMjQxLjAzLDE1Ljg4IDExMzYuMzgsMTUuODQgOTA1Ljg4LDQxNS4xIDIwMTAuNTMsNDE1LjA5IiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNzFBQjIzIiBwb2ludHM9IjExMTYuMzgsMTUuODQgNjU1Ljk5LDE1Ljg0IDQ5NC4xNSwyOTYuMTcgNzI4LjM1LDY5NC44OCIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzhBQzkzNCIgcG9pbnRzPSI0ODQuMTUsMzA2LjE3IDI1NS4wNiw3MDIuOTYgMzg3LjY2LDkzMi42NCA4NDUuODMsOTMyLjYzIiAvPg0KICA8cG9seWdvbiBmaWxsPSIjNThEMEZGIiBwb2ludHM9Ii03LjE3LDE0NDAuMDkgMTA5Ny45NywxNDQwLjA4IDEzMjguNDcsMTA0MC44MyAyMjMuMzIsMTA0MC44NSIgLz4NCiAgPHBvbHlnb24gZmlsbD0iIzEzQURGMyIgcG9pbnRzPSIxNzM5LjA0LDExNjAuOTEgMTUwOS4wOSw3NjIuNjQgMTExNy45NywxNDQwLjA4IDExODYuOTMsMTQ0MC4wOCAxNTc3Ljg3LDE0NDAuMDgiIC8+DQogIDxwb2x5Z29uIGZpbGw9IiMwMzkzRDAiIHBvaW50cz0iMTk3OC44LDc1Mi45NiAxODQ2LjIsNTIzLjMgMTM4Ni42OCw1MjMuMyAxNzQ5LjA0LDExNTAuOTEiIC8+DQo8L3N2Zz4=)](https://github.com/pjazdzyk/Unitility)
```

## 9. Acknowledgments

Special thanks to Kret11, VeloxDigits, Olin44, and others for all discussions on architecture we had.<br>
I extend my heartfelt gratitude to the [Silesian University of Technology](https://www.polsl.pl/en/) for imparting
invaluable knowledge to me.<br>
Thanks to [Mathieu Soysal](https://github.com/MathieuSoysal) for
his [Maven central publisher](https://github.com/MathieuSoysal/Java-maven-library-publisher). <br>
Badges used in readme: [Shields.io](https://img.shields.io)
and [Badges 4 README.md](https://github.com/alexandresanlim/Badges4-README.md-Profile).

May the force be with you.
