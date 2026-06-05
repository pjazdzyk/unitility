# Unitility Reference — Quantity Classes & Units

Complete mapping of physical quantities, base units, and supported symbols for `com.synerset:unitility-core` v4.0.1.

## Architecture Pattern

| Artifact | Naming Convention | Example |
|----------|-------------------|---------|
| **Quantity Class** | `<Quantity>` | `Temperature` |
| **Unit Enum** | `<Quantity>Units` | `TemperatureUnits.FAHRENHEIT` |
| **Unit Interface** | `<Quantity>Unit` | `TemperatureUnit.CELSIUS` |

## Common Quantities

### Distance / Length
- **Base Unit:** meter `[m]`
- **Factory Methods:** `ofMeters()`, `ofCentimeters()`, `ofMillimeters()`, `ofKilometers()`, `ofMiles()`, `ofNauticalMiles()`, `ofFeet()`, `ofInches()`, `ofYards()`, `ofDecameters()`, `ofHectometers()`, `ofDataMiles()`
- **Aliases:** `Length`, `Width`, `Height`, `Diameter`, `Thickness`, `Perimeter` (same class, different import)

### Area
- **Base Unit:** square meter `[m²]`
- **Symbols:** `m²`, `km²`, `cm²`, `mm²`, `are [a]`, `hectare [ha]`, `in²`, `ft²`, `yd²`, `acre [ac]`, `mi²`

### Volume
- **Base Unit:** cubic meter `[m³]`
- **Symbols:** `m³`, `cm³`, `liter [L]`, `hectoliter [hL]`, `milliliter [mL]`, `ounce [fl.oz]`, `pint [pt]`, `gallon (US) [gal_us]`, `gallon (UK) [gal_uk]`

### Mass
- **Base Unit:** kilogram `[kg]`
- **Symbols:** `kg`, `g`, `mg`, `tonne [t]`, `ounce [oz]`, `pound [lb]`

### Angle
- **Base Unit:** radian `[rad]`
- **Factory Methods:** `ofDegrees(double)`, `ofRadians(double)`
- **Symbols:** `degrees [°]`, `radians [rad]`

### Ratio
- **Base Unit:** dimensionless `[-]`
- **Symbols:** `percent [%]`, `Decimal [-]`

### Linear Mass Density
- **Base Unit:** `kg/m`
- **Symbols:** `kg/m`, `t/m`, `oz/ft`, `lb/ft`

### Velocity
- **Base Unit:** `m/s`
- **Symbols:** `m/s`, `cm/s`, `km/h`, `in/s`, `ft/s`, `mph`, `knot [kn]`, `Mach [Mach]`

### Angular Velocity
- **Base Unit:** `rad/s`
- **Symbols:** `rad/s`, `rpm`, `rps`, `°/s`

### Curvature
- **Base Unit:** `rad/m`
- **Symbols:** `rad/m`, `rad/ft`, `°/m`, `°/ft`, `°/100ft`

### Data Size
- **Base Unit:** bit `[bit]`
- **Symbols:** `bit`, `byte [b]`, `kilobyte [kb]`, `megabyte [mb]`, `gigabyte [gb]`, `terabyte [tb]`, `petabyte [pb]`

---

## Mechanical Quantities

### Force
- **Base Unit:** newton `[N]`
- **Symbols:** `N`, `kilonewton [kN]`, `kilopond [kp]`, `dyne [dyn]`, `pound force [lbf]`, `poundal [pdl]`

### Momentum
- **Base Unit:** `kg·m/s`
- **Symbols:** `kg·m/s`, `g·cm/s`, `lb·ft/s`

### Torque
- **Base Unit:** `N·m`
- **Symbols:** `N·m`, `mN·m`, `kp·m`, `ft·lb`, `in·lb`

---

## Electrical Quantities

| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Capacitance | farad `[F]` | `F`, `µF`, `nF`, `pF`, `mF`, `kF`, `MF` |
| Charge | coulomb `[C]` | `C`, `pC`, `nC`, `µC`, `mC`, `kC`, `MC` |
| Conductance | siemens `[S]` | `S`, `pS`, `nS`, `µS`, `mS`, `kS` |
| Current | ampere `[A]` | `A`, `mA`, `µA`, `nA`, `kA`, `MA` |
| Resistance | ohm `[Ω]` | `Ω`, `mΩ`, `kΩ`, `MΩ` |
| Voltage | volt `[V]` | `V`, `mV`, `µV`, `nV`, `kV`, `MV` |

---

## Thermodynamic Quantities

### Temperature
- **Base Unit:** Kelvin `[K]`
- **Factory Methods:** `ofCelsius()`, `ofKelvins()`, `ofFahrenheit()`
- **Symbols:** `K`, `°C`, `°F`

### Pressure
- **Base Unit:** Pascal `[Pa]`
- **Symbols:** `Pa`, `hPa`, `kPa`, `MPa`, `bar`, `mbar`, `psi`, `Torr`
- **Temperature-dependent:** `mH₂O_10`, `mH₂O_60`, `mH₂O_95`, `mmHg_10`, `mmHg_60`, `mmHg_95`

### Energy
- **Base Unit:** Joule `[J]`
- **Symbols:** `J`, `mJ`, `kJ`, `MJ`, `BTU`, `cal`, `kcal`, `Wh`, `kWh`

### Power
- **Base Unit:** Watt `[W]`
- **Symbols:** `W`, `kW`, `MW`, `BTU/h`, `HP`

### Specific Heat
- **Base Unit:** `J/(kg·K)`
- **Symbols:** `J/(kg·K)`, `kJ/(kg·K)`, `BTU/(lb·°F)`

### Density
- **Base Unit:** `kg/m³`
- **Symbols:** `kg/m³`, `lb/ft³`, `lb/in³`, `lb/gal_US`

### Viscosity
| Type | Base Unit | Symbols |
|------|-----------|---------|
| Dynamic | `Pa·s` (= `kg/(m·s)`) | `Pa·s`, `Poise [P]` |
| Kinematic | `m²/s` | `m²/s`, `ft²/s` |

### Thermal Properties
| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Thermal Conductivity | `W/(m·K)` | `W/(m·K)`, `kW/(m·K)`, `BTU/(h·ft·°F)` |
| Thermal Diffusivity | `m²/s` | `m²/s`, `ft²/s` |

### Enthalpy & Entropy
| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Specific Enthalpy | `J/kg` | `J/kg`, `kJ/kg`, `BTU/lb` |
| Specific Entropy | `J/(kg·K)` | `J/(kg·K)`, `kJ/(kg·K)`, `BTU/(lb·°F)` |
| Molar Enthalpy | `J/mol` | `J/mol`, `kJ/mol`, `MJ/kmol`, `BTU/lb-mol`, `cal/mol`, `kcal/mol` |
| Molar Entropy | `J/(mol·K)` | `J/(mol·K)`, `kJ/(mol·K)`, `cal/(mol·K)` |

### Molar Properties
| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Molar Fraction | dimensionless `[-]` | `-` |
| Molar Mass | `g/mol` | `g/mol`, `kg/kmol`, `kg/mol`, `mg/mmol`, `lb/lb-mol`, `oz/mol` |
| Molar Volume | `m³/mol` | `m³/mol`, `L/mol`, `dm³/mol`, `cm³/mol`, `mL/mol`, `ft³/lb-mol`, `in³/lb-mol` |

### Compressibility
| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Isothermal Compressibility | `1/Pa` | `1/Pa`, `1/kPa`, `1/MPa`, `1/bar`, `1/psi`, `1/atm` |
| Compressibility Factor | dimensionless `[-]` | `-` |

---

## Flow Quantities

### Mass Flow
- **Base Unit:** `kg/s`
- **Symbols:** `kg/s`, `kg/h`, `t/h`, `lb/s`

### Volumetric Flow
- **Base Unit:** `m³/s`
- **Symbols:** `m³/s`, `m³/min`, `m³/h`, `L/s`, `L/min`, `L/h`, `gal/s_US`, `gal/min_US`, `gal/h_US`, `gal/s_UK`, `gal/min_UK`, `gal/h_UK`

---

## Humid Air Specific

### Humidity Ratio
- **Base Unit:** `kg/kg`
- **Symbols:** `kg/kg`, `g/kg`, `lb/lb`

### Relative Humidity
- **Base Unit:** Decimal `[-]`
- **Symbols:** `%`, `Decimal [-]`

---

## Hydraulic Quantities

| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Linear Resistance | `Pa/m` | `Pa/m`, `inH₂O/100ft`, `inHg/100ft` |
| Friction Factor | dimensionless | `-` |
| LocalLoss Factor | dimensionless | `-` |
| Rotation Speed / Flow Rate | `rad·s⁻¹/m³·s⁻¹` | `rad·s⁻¹/m³·s⁻¹`, `rpm/gpm` |

---

## Similarity Numbers (dimensionless)

- Grashof number
- Prandtl number
- Reynolds number
- Bypass factor

---

## Acoustic Quantities

| Quantity | Base Unit | Symbols |
|----------|-----------|---------|
| Sound Power | Watt `[W]` | `W`, `dB` |
| Sound Pressure | Pascal `[Pa]` | `Pa`, `dB` |

---

## Oscillation / Frequency

- **Base Unit:** Hertz `[Hz]`
- **Symbols:** `Hz`, `kHz`, `MHz`, `GHz`, `cpm` (cycles per minute)
