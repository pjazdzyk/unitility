# Changelog

## 5.0.0

A major version because two existing quantities change behaviour. Nothing is removed and no method signature
changes, so code still compiles, which is exactly why the change has to be read: it can alter numbers without
a compiler error. Two quantities are added alongside.

### Breaking: `Ratio` and `Effectiveness` are decimal-based

The base unit of `Ratio` and `Effectiveness` moves from percent to decimal, so a ratio is stored and computed
as the fraction it is (0.5), not as a percentage (50).

| What | 4.2.0 | 5.0.0 |
| --- | --- | --- |
| Base unit | `PERCENT` | `DECIMAL` |
| 50 % in the base unit | 50 | 0.5 |
| A bare number with no unit, parsed as `Ratio` or `Effectiveness` | percent: `"50"` is 50 % | decimal: `"50"` is 5000 % |
| A blank unit symbol (`fromSymbol("")`) | `PERCENT` | `DECIMAL` |

**Migrating:**

- Build ratios with an explicit unit: `Ratio.ofPercentage(50)` or `Ratio.ofDecimal(0.5)`. Both give the same
  quantity in 4.2.0 and 5.0.0.
- Anything that reads the base-unit value, or the value of a quantity converted to its base unit, now gets
  the fraction. Multiply by 100 where a percentage was meant, or convert to `RatioUnits.PERCENT` explicitly.
- Anything that parses a ratio from text or JSON should send the unit: `"50%"`. A bare `"50"` now means 50,
  that is 5000 %.

### Added

- **`TemperatureDifference`**, a temperature *interval* rather than a temperature. It converts by ratio where a
  temperature converts by offset, so a 1.15 K rise is 2.07 °F and not 34.07 °F. Units: K, °C, °F, °R. Use it for
  every rise, drop and approach, which until now had to be carried as a bare `Temperature` and read wrongly the
  moment anyone asked for imperial units.
- **`SpecificFanPower`**, fan electrical power per unit of air flow: W/(m³/s), W/(l/s), kW/(m³/s) and W/cfm, the
  figure fan selection and the energy codes both argue about.

Both are registered in `SupportedQuantitiesRegistry` and the default parsing factory, both get a plain-SI
persistence converter, and both are covered by the golden unit table. No existing conversion factor changes.

### Tests

- `Ratio` and `Effectiveness` gained their missing golden-table entries.
- The Jackson round trip now covers the blank unit symbol that `DECIMAL` uses, which serialises as an empty string
  and had no test.

## 4.2.0

Every unit conversion factor now comes from a named, sourced, exact definition, and ten factors that were wrong in
4.1.0 are corrected. **No existing API changes** (two classes are added and one unit is deprecated), but numbers do: 49 factors, listed below.
A minor version rather than a patch, so that no consumer receives a 1 % change in a valve coefficient silently.

### Corrected: ten factors that were wrong

| Unit | 4.1.0 | 4.2.0 | What was wrong |
| --- | --- | --- | --- |
| `HumidityRatioUnits.POUND_PER_POUND` | 0.45359237 kg/kg | 1 | A mass ratio has no factor (lb/lb = kg/kg); 4.1.0 divided by 2.2046, so every humidity ratio in lb/lb read 2.2 times too large. |
| `CurvatureUnits.DEGREES_PER_HUNDRED_FEET` | 5.72614584 rad/m | 5.72614584e-4 | Multiplied by 100 where it must divide: 10 000 times too large. |
| `FlowCoefficientUnits.CV` | 0.85667 Kv | 0.8649776554 | Kv/Cv = (1 US gpm in m³/h) × √(1 bar / 1 psi); 4.1.0 was 0.96 % low. |
| `LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET` | 8.16722 Pa/m | 8.1722083 | Now the conventional inch of water, 25.4 × 9.806 65 Pa (NIST SP 811: 2.490 889 E+02 Pa), per 30.48 m. |
| `PressureUnits.METRE_OF_WATER_10` | ρ = 999.5457 kg/m³ | 999.70247 | Water density from the NIST Chemistry WebBook (IAPWS-95) at 10 °C, 1 atm. |
| `PressureUnits.METRE_OF_WATER_60` | ρ = 982.6716 kg/m³ | 983.19582 | Same, at 60 °C. |
| `PressureUnits.METRE_OF_WATER_95` | ρ = 961.2691 kg/m³ | 961.88792 | Same, at 95 °C. |
| `LinearHeatFluxUnits.BTU_PER_HOUR_FOOT` | 0.96132649 W/m | 0.9615192591 | Btu_IT / h / ft; the old constant was wrong. |
| `LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT` | 57.6795894 W/m | 57.6911555 | Same constant × 60. |
| `SpecificVolumeUnits.OUNCE_PER_POUND` | 6.520391e-5 m³/kg | 6.519847e-5 | US fluid ounce = 231 in³ / 128. |

### Every changed factor

The scale of each unit to its base unit (value in the base unit of one of the unit), as a `double`, before and after.
"WRONG": relative error above 1e-5. "imprecise": 1e-9 to 1e-5, too few or wrong late digits. "rounding": a truncated
literal, harmless in practice. "last bit": the old value was one or two units in the last place off the correctly
rounded definition, from composing the factor in `double`.

| Unit | Symbol | 4.1.0 | 4.2.0 | Relative change | Class |
| --- | --- | --- | --- | --- | --- |
| `HumidityRatioUnits.POUND_PER_POUND` | `lb/lb` | 0.45359236999999913 | 1.0 | +1.20e+00 | WRONG |
| `CurvatureUnits.DEGREES_PER_HUNDRED_FEET` | `°/100ft` | 5.726145839876409 | 0.000572614583987641 | -1.00e+00 | WRONG |
| `FlowCoefficientUnits.CV` | `CV` | 0.85667 | 0.8649776554423018 | +9.70e-03 | WRONG |
| `PressureUnits.METRE_OF_WATER_95` | `mH₂O_95` | 9426.829215242571 | 9432.898170668 | +6.44e-04 | WRONG |
| `LinearResistanceUnits.INCH_OF_WATER_PER_100_FEET` | `inH₂O/100ft` | 8.16722 | 8.172208333333334 | +6.11e-04 | WRONG |
| `PressureUnits.METRE_OF_WATER_60` | `mH₂O_60` | 9636.716568698213 | 9641.857288203 | +5.33e-04 | WRONG |
| `LinearHeatFluxUnits.BTU_PER_HOUR_FOOT` | `BTU/(h·ft)` | 0.96132649 | 0.9615192590952173 | +2.01e-04 | WRONG |
| `LinearHeatFluxUnits.BTU_PER_MINUTE_FOOT` | `BTU/(min·ft)` | 57.679589400000005 | 57.69115554571304 | +2.01e-04 | WRONG |
| `PressureUnits.METRE_OF_WATER_10` | `mH₂O_10` | 9802.194839219565 | 9803.7322274255 | +1.57e-04 | WRONG |
| `SpecificVolumeUnits.OUNCE_PER_POUND` | `fl.oz/lb` | 6.520391e-05 | 6.519847228140103e-05 | -8.34e-05 | WRONG |
| `LinearResistanceUnits.INCH_OF_MERCURY_PER_100_FEET` | `inHg/100ft` | 111.10166332193332 | 111.10170603674541 | +3.84e-07 | imprecise (sourced) |
| `ThermalConductivityUnits.BTU_PER_HOUR_FOOT_FAHRENHEIT` | `BTU/(h·ft·°F)` | 1.7307352822121 | 1.7307346663713912 | -3.56e-07 | imprecise |
| `HeatFluxUnits.BTU_PER_HOUR_SQUARE_FOOT` | `BTU/(h·ft²)` | 3.15459148 | 3.154590745063049 | -2.33e-07 | imprecise |
| `HeatFluxUnits.BTU_PER_MINUTE_SQUARE_FOOT` | `BTU/(min·ft²)` | 189.2754888 | 189.27544470378294 | -2.33e-07 | imprecise |
| `DensityUnits.POUND_PER_GALLON_US` | `lb/gal_US` | 119.82640000000022 | 119.82642731689663 | +2.28e-07 | imprecise |
| `MolarVolumeUnits.CUBIC_INCH_PER_POUND_MOLE` | `in³/lbmol` | 3.612729e-08 | 3.612729200008369e-08 | +5.54e-08 | imprecise |
| `MolarVolumeUnits.CUBIC_FOOT_PER_POUND_MOLE` | `ft³/lbmol` | 6.242796e-05 | 6.242796057614462e-05 | +9.23e-09 | imprecise |
| `DensityUnits.POUND_PER_CUBIC_INCH` | `lb/in³` | 27679.904543240627 | 27679.90471020312 | +6.03e-09 | imprecise |
| `SpecificVolumeUnits.GALLON_US_PER_POUND` | `gal_US/lb` | 0.0083454045 | 0.008345404452019332 | -5.75e-09 | imprecise |
| `SpecificVolumeUnits.GALLON_UK_PER_POUND` | `gal_UK/lb` | 0.0100224129 | 0.010022412854960501 | -4.49e-09 | imprecise |
| `SpecificHeatUnits.BTU_PER_POUND_FAHRENHEIT` | `BTU/(lb·°F)` | 4186.7999934703 | 4186.8 | +1.56e-09 | imprecise |
| `SpecificVolumeUnits.CUBIC_FOOT_PER_POUND` | `ft³/lb` | 0.0624279606 | 0.06242796057614461 | -3.82e-10 | rounding |
| `VolumeUnits.CUBIC_FOOT` | `ft³` | 0.0283168466 | 0.028316846592 | -2.83e-10 | rounding |
| `HeatTransferCoefficientUnits.BTU_PER_HOUR_SQUARE_FOOT_FAHRENHEIT` | `BTU/(h·ft²·°F)` | 5.67826334 | 5.678263341113488 | +1.96e-10 | rounding |
| `HeatTransferCoefficientUnits.BTU_PER_MINUTE_SQUARE_FOOT_FAHRENHEIT` | `BTU/(min·ft²·°F)` | 340.6958004 | 340.69580046680926 | +1.96e-10 | rounding |
| `EnergyDensityUnits.KILO_BTU_PER_CUBIC_FOOT` | `MBTU/ft³` | 37258945.802 | 37258945.80783129 | +1.57e-10 | rounding |
| `EnergyDensityUnits.BTU_PER_CUBIC_FOOT` | `BTU/ft³` | 37258.945802 | 37258.94580783128 | +1.57e-10 | rounding |
| `VolumetricFlowUnits.GALLONS_PER_MINUTE_US` | `gal/min_US` | 6.309019640194602e-05 | 6.30901964e-05 | -3.08e-11 | rounding |
| `ThermalConductanceUnits.BTU_PER_HOUR_FAHRENHEIT` | `BTU/(h·°F)` | 0.527527926306 | 0.52752792631 | +7.58e-12 | rounding |
| `VolumetricFlowUnits.GALLONS_PER_SECOND_US` | `gal/s_US` | 0.003785411783973468 | 0.003785411784 | +7.01e-12 | rounding |
| `VolumetricFlowUnits.GALLONS_PER_HOUR_UK` | `gal/h_UK` | 1.2628027777725409e-06 | 1.2628027777777779e-06 | +4.15e-12 | rounding |
| `VolumetricFlowUnits.GALLONS_PER_SECOND_UK` | `gal/s_UK` | 0.004546089999981147 | 0.00454609 | +4.15e-12 | rounding |
| `VolumetricFlowUnits.GALLONS_PER_MINUTE_UK` | `gal/min_UK` | 7.576816666635246e-05 | 7.576816666666667e-05 | +4.15e-12 | rounding |
| `MassFluxUnits.POUND_PER_SQUARE_FOOT_SECOND` | `lb/(ft²·s)` | 4.8824276364 | 4.88242763638305 | -3.47e-12 | rounding |
| `MassFluxUnits.POUND_PER_SQUARE_FOOT_HOUR` | `lb/(ft²·h)` | 0.0013562298990000001 | 0.0013562298989952919 | -3.47e-12 | rounding |
| `VolumeUnits.GALLON_UK` | `gal_UK` | 0.00454608999999 | 0.00454609 | +2.20e-12 | rounding |
| `VolumetricFlowUnits.GALLONS_PER_HOUR_US` | `gal/h_US` | 1.0515032733325971e-06 | 1.0515032733333334e-06 | +7.00e-13 | rounding |
| `IsothermalCompressibilityUnits.INVERSE_PSI` | `1/psi` | 0.00014503773773021683 | 0.0001450377377302092 | -5.26e-14 | rounding |
| `PressureUnits.PSI` | `psi` | 6894.757293168 | 6894.757293168362 | +5.25e-14 | rounding |
| `DensityUnits.POUND_PER_CUBIC_FOOT` | `lb/ft³` | 16.0184633739599 | 16.018463373960138 | +1.49e-14 | rounding |
| `PressureUnits.TORR` | `Torr` | 133.322368421053 | 133.32236842105263 | -2.78e-15 | rounding |
| `VelocityUnits.KNOT` | `kn` | 0.514444444444444 | 0.5144444444444445 | +9.72e-16 | rounding |
| `IsentropicCompressibilityUnits.INVERSE_PSI` | `1/psi` | 0.00014503773773020924 | 0.0001450377377302092 | -2.76e-16 | last bit |
| `IsentropicCompressibilityUnits.INVERSE_PSF` | `1/psf` | 0.02088543423315013 | 0.020885434233150126 | -1.92e-16 | last bit |
| `CurvatureUnits.RADIANS_PER_FOOT` | `rad/ft` | 3.280839895013123 | 3.2808398950131235 | +1.52e-16 | last bit |
| `NormalVolumetricFlowUnits.STANDARD_CUBIC_METERS_PER_HOUR` | `Sm³/h` | 0.0002633177164671178 | 0.00026331771646711784 | +1.52e-16 | last bit |
| `PressureUnits.MILLIMETRE_OF_MERCURY_10` | `mmHg_10` | 133.07624049999998 | 133.0762405 | +1.50e-16 | last bit |
| `SurfaceTensionUnits.POUND_FORCE_PER_FOOT` | `lbf/ft` | 14.593902937206362 | 14.593902937206364 | +1.37e-16 | last bit |
| `NormalVolumetricFlowUnits.STANDARD_CUBIC_FEET_PER_MINUTE` | `scfm` | 0.0004465187511269536 | 0.00044651875112695363 | +6.72e-17 | last bit |

About sixty units keep their factor but now convert with different arithmetic, in either direction, which can change a
result in its last bit (never more). They include every SI sub-multiple (milli-, micro-, nano-, pico-: `val * 1e-3`
became `val / 1000`), rpm, rps, °/s, °F, the litre units, cfm, slpm, `mmHg_60` and `mmHg_95`, and
`CapacitanceUnits.NANOFARAD`, `ChargeUnits.NANOCOULOMB`, `ConductanceUnits.NANOSIEMENS`,
`CurvatureUnits.DEGREES_PER_FOOT`, `ForceUnits.DYNE`, `MomentumUnits.GRAM_CENTIMETRE_PER_SECOND` and
`VelocityUnits.KILOMETER_PER_HOUR`. A consumer that pins an exact converted value, or compares quantities with
`equals()` after a conversion, may see the last bit move. Where a scale is the reciprocal of a whole number N (a minute, an hour, milli-),
the conversions divide and multiply by N, so for example 1 m³/s is exactly 3 600 000 L/h.

Unchanged by design: the three mercury columns (`MILLIMETRE_OF_MERCURY_10/60/95`) keep their densities (13 570, 13 448
and 13 364 kg/m³). No primary source for the density of mercury at those temperatures was read, so they are marked
UNVERIFIED rather than changed; `mmHg_10` moves in its last bit only because the product is now correctly rounded.
`INCH_OF_MERCURY_PER_100_FEET` now uses the NIST inch of mercury at 32 °F exactly as published, 3.386 38 E+03 Pa (not
the conventional one, 3.386 389 E+03 Pa). 4.1.0 carried 3386.378 698... Pa per inch with no source, so the factor moves
from 111.10166332193332 to 111.10170603674541 Pa/m per inHg/100ft, +3.8e-7.

### Changed: how factors are defined

- New `com.synerset.unitility.unitsystem.definitions.UnitDefinitions`: every definition typed once, with its source
  (NIST SP 811 Appendix B.8 and Tables 6 and 9, NIST Handbook 44 Appendix C, the NIST Chemistry WebBook). Compound
  factors are expressions over those names, evaluated once at class load in `BigDecimal` (`MathContext.DECIMAL128`)
  and rounded to the nearest `double`.
- New `LinearScale`: a linear unit is declared by one number, its scale, and both converters are built from it, so an
  inverse can no longer disagree with its forward. Every linear unit of all 88 `*Units` enums uses it. The affine
  temperatures take a scale and their reading at the ice point. The logarithmic decibels and the two
  `Math.toRadians` degree units keep explicit converters.
- New tests: a golden table holding, for every unit, its reference factor from NIST SP 811 / the SI with its source,
  compared at 1 ulp for exact definitions and at the published precision otherwise; a reflection test that fails if
  any unit has no golden row or named exclusion; a reflection round-trip test over every unit; and
  `unit-scales.json`, a fixture of every unit's scale for the EnergyFlowX UI's parity test.

### Deprecated

- `VelocityUnits.MACH` (and `Velocity.ofMach`, `toMach`, `getInMach`): a Mach number is a ratio to a speed of sound
  that depends on temperature, not a unit of velocity. It still converts at 340.29 m/s, unchanged, and will be removed
  in the next major version.

### Noted, not changed

- `DataSizeUnits` labels its binary multiples `KB`, `MB`, `GB`, `TB`, `PB`. The values (powers of 1024) are the
  intended ones; in SI and IEC terms the symbols are KiB, MiB, GiB, TiB, PiB. The symbols are kept, because changing
  them would break parsing of existing strings.
- The pound-mole units (`BTU_PER_POUND_MOLE`, `POUND_PER_POUND_MOLE`, `CUBIC_FOOT_PER_POUND_MOLE`,
  `CUBIC_INCH_PER_POUND_MOLE`) assume 1 lbmol = 453.59237 mol, and the standard volumetric flows assume 15 °C
  (Sm³, slpm) and 60 °F (scf) at the normal pressure. No primary source was read for these conventions.
- Java 17 target, unchanged.
