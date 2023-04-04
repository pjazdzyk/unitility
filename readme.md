# UNITILITY - The Physics Unit Conversion Solution for Java

Introducing <strong>UNITILITY</strong> - the Java library designed to simplify physics units conversion. 
With a wide range of value objects that represent commonly used physical quantities, this solution is built using plain Java for optimal speed and lightweight functionality. 
Whether you're looking to convert units within the same type or customize to meet your specific needs, UNITILITY offers quick and easy usage in your project, without any heavy frameworks,
or external libraries.

![Build And Test](https://github.com/pjazdzyk/unitility/actions/workflows/build-test-analyze.yml/badge.svg) &nbsp;
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility) &nbsp;
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility) &nbsp;
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pjazdzyk_unitility&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=pjazdzyk_unitility) &nbsp;

> AUTHOR: <b>Piotr Jażdżyk</b> <br>
> LINKEDIN: https://www.linkedin.com/in/pjazdzyk <br>

## Example
Below is a simple example how to work with units and convert to another type of unit:
```java
Temperature tempInCelsius = Temperature.ofCelsius(20.5);
Temperature tempInKelvin = tempInCelsius.toKelvin();
Temperature temInFahrenheit = tempInKelvin.toFahrenheit();
System.out.println(tempInCelsius  + " | " + tempInKelvin + " | " + temInFahrenheit);

OUTPUT: 20.5 °C | 293.65 K | 68.9 °F
```
All quantities have smart toString() method, which will always adjust values decimal precision to capture by default 3 relevant digits depending on your unit type and its value. 
This way you have guaranteed an elegant output from toString() without any additional effort of reformatting. Values will be rounded up using HALF_UP approach. 

```java
Distance bigDistance = Distance.ofMeters(10);
Distance smallDistance = Distance.ofMeters(0.000123678);
System.out.println(bigDistance + " | " + smallDistance);

OUTPUT: 10 m | 0.000124 m
```

## Development status & collaboration
This is an early development stage.
I welcome other developers who are interested in physics and engineering to collaborate on this project. 
As we are still in the early stages of development, any contributions or suggestions would be greatly appreciated.<br>
<strong>If you would like me to add any specific units for your field of science just let me know!</strong>

## Tech

<strong>Unitility</strong> is developed using following technologies: <br>

Core: <br>
![image](https://img.shields.io/badge/17-Java-orange?style=for-the-badge) &nbsp;
![image](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white) &nbsp;

Testing:<br>
![image](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white) &nbsp;

CI/CD:<br>
![image](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white) &nbsp;
![image](https://img.shields.io/badge/Sonar%20cloud-F3702A?style=for-the-badge&logo=sonarcloud&logoColor=white) &nbsp;

## Specification
At current level of development following units are available:

#### COMMON:
* Distance: meter [m], centimetre [cm], millimetre [mm], kilometre [km], mile [mi], feet [ft], inch [in]
* Area: square meter [m²], square kilometre [km²], square centimetre [cm²], square millimetre [mm²], are [a], hectare [ha], square inch [in²], square foot [ft²], square yard [yd²], acre [ac], square mile [mi²]
* Volume: cubic meter [m³], cubic centimetre [L], liter [L], hectolitre [hL], millilitre [mL], ounce [fl.oz], pint [pt], gallon [gal]
* Mass: kilogram [kg], gram [g], milligram [mg], tonne [t], ounce [oz], pound [lb]
* Angle: degrees [°], radians [rad]
#### MECHANICAL:
* Force: newton [N], kilonewton [kN], kilopond [kp], dyne [dyn], pound force [lbf], poundal [pdl]
#### THERMODYNAMIC:
* Temperature: Kelvin [K], Celsius [°C], Fahrenheit [°F]
* Pressure: Pascal [Pa], Hectopascal [hPa], Megapascal [MPa], Bar [bar], Milli Bar [mbar] PSI [psi], Torr [Torr]
* Energy: Joule [J], Millijoule [mJ], Kilojoule [kJ], Megajoule [MJ], BTU [BTU], Calorie [cal], Kilocalorie [kcal], Watt hour [Wh], Kilowatt hour [kWh]
* Power: Watt [W], Kilowatt [kW], Megawatt [MW], BTU/hour [BTU/h], Horse Power [HP]
* Specific heat: Joules per kilogram Kelvin [J/(kg·K)], Kilojoules per kilogram Kelvin [kJ/(kg·K)], BTU per pound Fahrenheit [BTU/(lb·°F)]
* Density: Kilogram per cubic meter [kg/m³], Pound per cubic foot [lb/ft³], Pounds per cubic inch [lb/in³]
* Dynamic viscosity: Kilogram per meter second [kg/(m·s)], Pascal second [Pa·s], Poise [P]
* Kinematic viscosity: Square meter per second [m²/s], Square foot per second [ft²/s]
* Specific enthalpy: Joule per kilogram [J/kg], Kilojoule per kilogram [kJ/kg], BTU per pound [BTU/lb]
* Thermal conductivity: Watts per meter Kelvin [W/(m·K)], Kilowatts per meter Kelvin [kW/(m·K)], BTU per hour foot Fahrenheit [BTU/(h·ft·°F)]
#### FLOWS:
* Mass flow: Kilogram per second [kg/s], Kilogram per hour [kg/h], Tonne per hour [t/h], Pound per second [lb/s], 
* Volumetric flow: Cubic meters per second [m³/s], Cubic meters per minute [m³/min], Cubic meters per hour [m³/h], Litre per second [L/s], Litre per minute [L/min], Litre per hour [L/h], 
* Gallons per second [gal/s], Gallons per minute [gal/min], Gallons per hour [gal/h]
#### HUMID AIR SPECIFIC:
* Humidity ratio: Kilogram per kilogram [kg/kg], Pound per pound [lb/lb]
* Relative humidity: Percent [%], Decimal [-]
#### DIMENSIONLESS:
* Grashof number, Prandtl number, Reynolds number

Each quantity consists most popular SI units and at least one Imperial unit.

## License
GNU GENERAL PUBLIC LICENSE V3<br>

## Acknowledgments
Special thanks to Kret11, VeloxDigits, Olin44, and others for their help and contribution.<br>
Badges used in readme: [Shields.io](https://img.shields.io) and [Badges 4 README.md](https://github.com/alexandresanlim/Badges4-README.md-Profile).