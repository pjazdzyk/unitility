---
name: python-iapws
description: >
  Use this skill whenever you need to verify, validate, or cross-check thermodynamic property
  calculations for water/steam, ice Ih, humid air against IAPWS reference standards. Triggers
  include: implementing a new IAPWS equation (IF97, R10-06, R14-08), verifying computed
  properties against reference values, generating test data points, checking sublimation/melting
  curves, or comparing Java implementation results with Python iapws library. Also trigger when
  the user mentions iapws, Wagner et al., IAPWS releases (R7-97, R10-06, R14-08), or asks to
  verify thermodynamic calculations.
---

# Python iapws Verification Skill

## Instructions

### When to Activate

Activate this skill when:
- Implementing a new IAPWS equation and you need reference values for test assertions
- Reviewing thermodynamic code and you need to cross-check computed results against authoritative sources
- Generating verification data points (T, p, ρ, h, s, cp) for Java unit tests
- Debugging discrepancies between Java implementation outputs and expected physical values

### Installation (One-Time Setup)

```bash
pip install iapws
```

Verify: `python -c "from iapws._iapws import _Sublimation_Pressure; print(_Sublimation_Pressure(230)*1e6)"` — should output `8.94735...`

### Core Verification Functions

All functions below are from `iapws._iapws`. Import with:
```python
from iapws._iapws import _Sublimation_Pressure, _Melting_Pressure, _Ice
```

#### 1. Sublimation Pressure — Ice Ih (Wagner et al. 2011, Eq.(6))

```python
p_mpa = _Sublimation_Pressure(T_kelvin)          # Returns MPa
p_pa    = _Sublimation_Pressure(T_kelvin) * 1e6   # Convert to Pa for Java comparison
```

Valid: T ∈ [50 K, 273.16 K]. Coefficients: a=[−21.2144006, 27.3203819, −6.10598130], b=[3.333e-3, 1.20667, 1.70333].

#### 2. Melting Pressure — All Ice Phases (Wagner et al. 2011, Eq.(7))

```python
p_mpa = _Melting_Pressure(T_kelvin)              # Ice Ih default
p_mpa = _Melting_Pressure(T_kelvin, "III")       # Ice III
# Phases: Ih [251.165–273.16K], III (251.165–256.164K], V (256.164–273.31K], VI (273.31–355K], VII (355–715K]
```

#### 3. Ice Ih Full State Properties (Feistel & Wagner 2006, IAPWS-R10-06)

```python
props = _Ice(T_kelvin, P_megapascal)             # Returns dict with all properties
```

Available keys: `rho` (kg/m³), `h` (kJ/kg), `u` (kJ/kg), `s` (kJ/(kg·K)), `cp` (kJ/(kg·K)), `alfav` (1/K), `beta` (MPa/K), `xkappa` (1/MPa), `ks` (1/MPa), `gt`, `gtt`, `gp`, `gpp`, `gtp`.

### Unit Conversion Rules — Critical

iapws uses different units than Java. Always apply these conversions before comparing:

| Property | iapws Unit | Java Unit | Conversion |
|----------|-----------|-----------|------------|
| Pressure (input) | MPa | Pa | × 1e6 |
| Pressure (output from _Sublimation_Pressure) | MPa | Pa | × 1e6 |
| Density | kg/m³ | kg/m³ | Direct — no conversion |
| Enthalpy, Internal Energy, Gibbs, Helmholtz | kJ/kg | J/kg | × 1000 |
| Entropy | kJ/(kg·K) | J/(kg·K) | × 1000 |
| Heat capacity (cp) | kJ/(kg·K) | J/(kg·K) | × 1000 |
| Isothermal compressibility | 1/MPa | 1/Pa | × 1e-6 |
| Isentropic compressibility | 1/MPa | 1/Pa | × 1e-6 |
| Cubic expansion coefficient | 1/K | 1/K | Direct — no conversion |
| Pressure coefficient (beta) | MPa/K | Pa/K | × 1e6 |

### Verification Workflow

**Step 1:** Run iapws to get reference value(s).  
**Step 2:** Convert units per table above.  
**Step 3:** Compare against Java output with appropriate tolerance.

Tolerance guidelines:
- Coefficient-level equations (sublimation, melting): relative error < 1e-6
- Gibbs EOS derived properties: relative error < 1e-7 for most quantities; < 1e-3 absolute J/kg for large-magnitude values (h, u ~ 10⁵–10⁶)
- Triple-point exact conditions: expect exact equality when same constant is used

### Common Pitfalls — Avoid These

| ❌ Wrong | ✅ Correct | Reason |
|----------|-----------|--------|
| `p = _Sublimation_Pressure(230)` compared directly to Java Pa value | `p = _Sublimation_Pressure(230) * 1e6` | iapws returns **MPa** |
| `_Ice(100, 100)["h"]` vs Java J/kg without ×1000 | Multiply by 1000 | iapws enthalpy is **kJ/kg** |
| `P=0.1` meaning 0.1 Pa in `_Ice()` | Use `P=0.1e6` for 0.1 MPa = 100 kPa | Input pressure is **MPa** |
| Using `IAPWS95` for ice properties | Use `_Ice()` — IAPWS-95 covers liquid/vapour only | Ice phases not in R7-97/IAPWS-95 |

## Examples

### Example 1: Verify Sublimation Pressure at T=230 K

```python
from iapws._iapws import _Sublimation_Pressure

p_ref_pa = _Sublimation_Pressure(230) * 1e6   # → 8.947352740189152 Pa
print(f"Reference: {p_ref_pa:.6f} Pa")
# Java assertion: assertThat(javaResult).isCloseTo(8.94735, within(5e-6))
```

### Example 2: Verify Triple Point Consistency (Sublimation Curve ↔ Gibbs EOS)

```python
from iapws._iapws import _Ice, _Sublimation_Pressure

# Sublimation pressure at triple point
p_subl = _Sublimation_Pressure(273.16) * 1e6   # → 611.657 Pa

# Gibbs EOS properties at triple point
tp = _Ice(273.16, 611.657e-6)                  # P in MPa
print(f"rho = {tp['rho']:.12f} kg/m³")         # → 916.709492...
print(f"h = {tp['h']*1000:.9f} J/kg")           # → -333444.253966...
print(f"cp = {tp['cp']*1000:.12f} J/(kg·K)")    # → 2096.784316...

# Verify: p_subl must equal IceIhGibbsEquations.P_T (611.657)
assert abs(p_subl - 611.657) < 1e-9, "Triple point inconsistency!"
```

### Example 3: Generate Full Test Data for IceIhGibbsEquationsTest

```python
from iapws._iapws import _Ice

# R10-06 Table 6 verification points — generate Java assertion values
test_points = [
    (273.16, 611.657e-6),     # Triple point
    (273.152519, 0.101325),   # Normal-pressure melting point
    (100.0, 100.0),           # Low T, high P
]

for T, P_MPa in test_points:
    p = _Ice(T, P_MPa)
    print(f"// T={T}K, P={P_MPa*1e6:.3f} Pa")
    print(f"assertThat(IceIhGibbsEquations.density({P_MPa*1e6}, {T-273.15}))")
    print(f"    .as(\"rho at T={T}K\").isCloseTo({p['rho']:.12f}, within(1e-7));")
    print(f"assertThat(IceIhGibbsEquations.specificEnthalpy({P_MPa*1e6}, {T-273.15}))")
    print(f"    .as(\"h at T={T}K\").isCloseTo({p['h']*1000:.9f}, within(1e-3));")
```

### Example 4: Quick Spot-Check During Development

```bash
# One-liners for rapid verification — no script needed
python -c "from iapws._iapws import _Sublimation_Pressure; print(f'p_subl(230K) = {_Sublimation_Pressure(230)*1e6:.6f} Pa')"

python -c "from iapws._iapws import _Ice; p=_Ice(100,100); print(f'rho={p[\"rho\"]}, h={p[\"h\"]*1000}, s={p[\"s\"]*1000}')"

python -c "from iapws._iapws import _Melting_Pressure; print(f'melt P(260K, Ih) = {_Melting_Pressure(260)*1e6:.6f} Pa')"
```

### Example 5: Cross-Validate Java Implementation Against iapws Formula Directly

When you need to verify the formula implementation (not just output values), use this script as `target/verify_ice_properties.py`:

```python
#!/usr/bin/env python3
"""Verify Java ice property implementations against iapws reference."""
from math import exp

Tt, Pt_Pa = 273.16, 611.657
A, B = [-0.212144006e2, 0.273203819e2, -0.61059813e1], [0.333333333e-2, 1.20666667, 1.70333333]

def ref_p_subl(T):
    theta = T / Tt
    s = sum(ai * theta**bi for ai, bi in zip(A, B))
    return exp(s / theta) * Pt_Pa

# Compare against Java results (replace with actual values from Java)
java_results = {230: 8.947353, 273.16: 611.657}
for T, p_java in java_results.items():
    p_ref = ref_p_subl(T)
    err = abs(p_java - p_ref) / p_ref * 100 if p_ref else float('inf')
    status = "PASS" if err < 1e-4 else "FAIL"
    print(f"T={T:>7.2f}K | ref={p_ref:.6f} Pa | java={p_java:.6f} Pa | err={err:.6e}% [{status}]")
```

Run: `python target/verify_ice_properties.py`

## Reference Files

| File | Purpose |
|------|---------|
| `scripts/verify_ice_properties.py` | Full automated cross-validation script (create from Example 5 template) |
| `reference.md` | Detailed coefficient tables and equation derivations (optional extension) |

## External References

- **iapws PyPI:** https://pypi.org/project/iapws/
- **iapws GitHub:** https://github.com/jjgomera/iapws (source code for `_iapws.py`)
- **IAPWS Official:** http://www.iapws.org/
- **R14-08 (Melt/Subl):** http://iapws.org/relguide/MeltSub.html
- **R10-06 (Ice Ih):** http://iapws.org/relguide/Ice-2009.html
- **Wagner et al. 2011:** DOI: 10.1063/1.3657937
