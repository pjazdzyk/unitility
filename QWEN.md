# QWEN.md — Unitility Project Context

## Project Overview

**Unitility** is a pure-Java library for representing physical quantities with their associated measurement units. It provides immutable, thread-safe value objects that encapsulate both a numeric value and a unit, with built-in conversion logic across 40+ physical quantity types (distance, temperature, pressure, force, electrical, thermodynamic, hydraulic, acoustic, geographic, etc.).

- **Author:** Piotr Jażdżyk (SYNERSET)
- **License:** Apache 2.0
- **Repository:** https://github.com/pjazdzyk/unitility
- **Current Version:** 4.0.2
- **Java:** 17+
- **Build:** Maven multi-module

## Module Structure

| Module | Purpose |
|--------|---------|
| `unitility-core` | Core quantity/unit classes, parsing, formatting — zero external dependencies |
| `unitility-jackson` | Jackson 3.x serializers/deserializers for physical quantities |
| `unitility-jackson-legacy` | Jackson 2.x serializers for frameworks not yet on Jackson 3.x |
| `unitility-spring` | Spring Boot 4.x autoconfiguration for web MVC path/query parameter binding |
| `unitility-quarkus` | Quarkus 3.x support and extension |
| `unitility-validation` | Jakarta Validation 4.x integration (custom constraints) |
| `unitility-persistence` | JPA/Hibernate `AttributeConverter` implementations for database persistence |
| `unitility-bom` | Bill of Materials for unified dependency version management |

## Core Architecture

### Key Interfaces
- **`PhysicalQuantity<U>`** — base interface for all quantities (value, unit, base-value access)
- **`CalculableQuantity<U, Q>`** — extends `PhysicalQuantity` with arithmetic operations (add, subtract, multiply, divide, power, log, trig)

### Quantity Class Pattern
Every physical quantity (e.g., `Temperature`, `Distance`, `Pressure`) follows a consistent pattern:
1. **Immutable** with `final` fields: `value`, `baseValue`, `unitType`
2. **Static factory methods:** `of(value, unit)`, `ofMeters(value)`, `ofCelsius(value)`
3. **Conversion methods:** `toMeter()`, `toKilometer()`, `toBaseUnit()`, `toUnit(targetUnit)`
4. **Value getters:** `getInMeters()`, `getInKilometers()`, `getValue()`, `getBaseValue()`
5. **`equals()`/`hashCode()`** compare base values (not raw values)
6. **`toEngineeringFormat(relevantDigits)`** for context-aware number formatting

### Unit Enum Pattern
Unit enums (e.g., `TemperatureUnits`, `DistanceUnits`) implement a typed unit interface:
- Store `symbol`, `toBaseConverter`, `fromBaseToUnitConverter` as `DoubleUnaryOperator`
- Provide `fromSymbol(String)` for case-insensitive symbol matching via `StringTransformer`
- Declare base unit via `getBaseUnit()`

### Parsing System
- **`PhysicalQuantityParsingFactory`** — parses strings like `"20.5 [°C]"` into quantity instances
- **`PhysicalQuantityDefaultParsingFactory`** — pre-registered parsers for all built-in quantities
- **`SupportedQuantitiesRegistry`** — maps quantity classes to their available units
- Tolerant of whitespace, alternative unit symbols (`m2` → `m²`, `Pa.m` → `Pa·m`)

## Build / Test Commands

```bash
# Full build with tests
mvn clean install

# Run tests only
mvn test

# Run single test class
mvn test -Dtest=TemperatureTest

# Skip tests
mvn clean install -DskipTests

# Build + Sonar analysis
mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=pjazdzyk_unitility

# Coverage report
# Generated in target/site/jacoco/ after running tests
```

## Dependencies

- **Core:** Pure Java 17, no external runtime dependencies
- **Tests:** JUnit Jupiter 6.0.0, AssertJ 3.27.7, JaCoCo 0.8.13
- **Jackson module:** Jackson Databind 3.1.1
- **Spring module:** Spring Boot 4.0.5
- **Quarkus module:** Quarkus 3.34.2
- **Validation module:** Jakarta Validation 4.0.0-M1, Hibernate Validator 9.0.1

## Code Conventions

### Testing
- **Given/When/Then** structure in test methods
- Test class naming: `*Test.java` (integration tests excluded: `*IntegrationTest.java`)
- AssertJ assertions with `withPrecision(1E-13)` for floating-point comparisons
- Tests co-located with source in `src/test/java`

### Coding Style
- All public APIs documented with **Javadoc**
- Quantity classes are **immutable** (final fields, no setters)
- Unit symbols use Unicode where applicable (°, ·, ², ³)
- Package structure: `com.synerset.unitility.unitsystem.<category>` (e.g., `common`, `mechanical`, `thermodynamic`)

### Adding New Quantities
See detailed step-by-step guide in [AGENTS.md](AGENTS.md) under "How to Add a New Physical Quantity". In summary:
1. Create unit interface (`YourQuantityUnit.java`)
2. Create units enum (`YourQuantityUnits.java`)
3. Create quantity class (`YourQuantity.java`)
4. Register in `PhysicalQuantityDefaultParsingFactory` and `SupportedQuantitiesRegistry`
5. Create JPA converter in `unitility-persistence`
6. Write tests covering conversions, equality, and edge cases

## CI/CD

- **GitHub Actions:** `build-test-analyze.yml` runs on push/PR to `master`
- **JDK 17 (Zulu)** with Maven caching
- **SonarCloud** for code quality, coverage, and vulnerability analysis
- **Maven Central** deployment via `centralDeploy` profile with GPG signing

## Key Files

| File | Description |
|------|-------------|
| `pom.xml` | Root multi-module Maven POM with shared dependency/plugin management |
| `AGENTS.md` | Detailed guide for AI agents working with this codebase |
| `README.md` | Comprehensive user documentation with usage examples |
| `CONTRIBUTING.md` | Contribution guidelines and workflow |
| `SECURITY.md` | Security policy and vulnerability reporting |
